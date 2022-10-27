package com.egg.news.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="noticia")
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String cuerpo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date alta;
	
	//@NotEmpty
    //private String imagen;
	
}
