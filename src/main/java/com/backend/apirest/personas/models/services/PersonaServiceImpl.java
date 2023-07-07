package com.backend.apirest.personas.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.apirest.personas.models.dao.IPersonaDao;
import com.backend.apirest.personas.models.entity.PersonaEntity;


@Service
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<PersonaEntity> findAll() {
		return (List<PersonaEntity>) personaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public PersonaEntity findByID(long id) {
		return personaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PersonaEntity save(PersonaEntity persona) {
		return personaDao.save(persona);
	}

	@Override
	@Transactional
	public void delete(long id) {
		personaDao.deleteById(id);
	}	
		

}
