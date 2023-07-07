package com.backend.apirest.personas.models.entity;

import java.io.Serializable;
import java.util.Date;


import jakarta.validation.constraints.*;

import jakarta.persistence.*;


@Entity
@Table(name="Personas")
public class PersonaEntity  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@NotBlank(message = "no puede estar vacio :(")
	@Column(nullable = false)
	private String nombre;
	
	@NotBlank(message = "no puede estar vacio :(")
	private String apellido;
	
	@NotBlank(message = "no puede estar vacio :(")
	@Email(message = "no es una dirección de correo bien formada :(")
	@Column(nullable = false,unique = false)
	private String email;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	private static final long serialVersionUID = 1L;

}
