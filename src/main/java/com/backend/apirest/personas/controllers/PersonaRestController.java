package com.backend.apirest.personas.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.apirest.personas.models.entity.PersonaEntity;
import com.backend.apirest.personas.models.services.IPersonaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonaRestController {
	
	@Autowired
	private IPersonaService personaService;
	
	/**
	 * Devuelve una lista todos los registros en la tabla personas.
	 *
	 ** @return Lista de entidades PersonaEntity
	 */
	@GetMapping("/personas")
	public List<PersonaEntity> index(){
		try {
	        return personaService.findAll();
	    } catch (DataAccessException e) {
	        System.out.println("Error: " + e.getMessage());
	        return new ArrayList<>(); 
	    }		
	}
	
	/**
	 * Obtiene los detalles de una persona por su ID.
	 *
	 * @param id el ID de la persona a buscar
	 * @return ResponseEntity con los detalles de la persona si se encuentra, o un mensaje de error si ocurre algún problema
	 */
	@GetMapping("/personas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		PersonaEntity persona = null;
		Map<String, Object> response  = new HashMap<>();
		try {
			persona = personaService.findByID(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 	
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		if(persona == null) {
			response.put("mensaje", "El registro con ID ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PersonaEntity>(persona,HttpStatus.OK);
	}
	
	/**
	 * Crea una nueva persona.
	 *
	 * @param persona la entidad de la persona a crear
	 * @param result el resultado de la validación de los datos deacuerdo a la entity
	 * @return ResponseEntity  resultado de la creación o mensajes de error 
	 */
	@PostMapping("/personas")
	public  ResponseEntity<?> create(@Valid @RequestBody PersonaEntity persona, BindingResult result) {
		PersonaEntity nuevaPersona = null;
		Map<String, Object> response  = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError err : result.getFieldErrors()) {
				errors.add("El campo '"+err.getField()+"' "+err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			nuevaPersona = personaService.save(persona);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 	
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido creado con éxito");
		response.put("persona", nuevaPersona);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);		
	}
	
	/**
	 * Elimina una persona.
	 *
	 * @param id Es el ID de la persona a eliminar
	 * @return ResponseEntity  resultado de la eliminación, ya sea correecta o incorrecta 
	 */
	@DeleteMapping("personas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response  = new HashMap<>();
		try {
			personaService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 	
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/personas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PersonaEntity persona, BindingResult result, @PathVariable Long id) {
		PersonaEntity personaActual = personaService.findByID(id);
		PersonaEntity personaUpdated = null;
		Map<String, Object> response  = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->{
						return "El campo '"+err.getField()+"' "+err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		if(personaActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el registro con ID ".concat(id.toString().
					concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			personaActual.setNombre(persona.getNombre());
			personaActual.setApellido(persona.getApellido());
			personaActual.setEmail(persona.getEmail());	
			personaUpdated = personaService.save(personaActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 	
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El registro ha sido actualizado con éxito");
		response.put("persona", personaUpdated);		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	

}
