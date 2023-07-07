package com.backend.apirest.personas.models.services;

import java.util.List;
import com.backend.apirest.personas.models.entity.PersonaEntity;



public interface IPersonaService {
	
	public List<PersonaEntity> findAll();
		
	public PersonaEntity findByID(long id);
	
	public PersonaEntity save(PersonaEntity persona);
	
	public void delete(long id);
	

}
