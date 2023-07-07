package com.backend.apirest.personas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.apirest.personas.models.entity.PersonaEntity;



public interface IPersonaDao extends JpaRepository<PersonaEntity, Long>{

}
