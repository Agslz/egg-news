package com.egg.news.servicios;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorios.NoticiaRepositorio;

import net.iharder.Base64;

@Service
public class NoticiaServicio {

	@Autowired
	private NoticiaRepositorio noticiaRepositorio;

	@Transactional
	public void crearNoticia(String titulo, String cuerpo, MultipartFile imagen) throws MiException {
		
		validar(titulo,cuerpo);

		Noticia noticia = new Noticia();
		
		String image="";
		
		if(!imagen.isEmpty()) {
			try {
				image = Base64.encodeBytes(imagen.getBytes());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		noticia.setTitulo(titulo);
		noticia.setCuerpo(cuerpo);
		noticia.setImagen(image);
		noticia.setAlta(new Date());

		noticiaRepositorio.save(noticia);

	}

	@Transactional(readOnly = true)
	public List<Noticia> listarNoticias() {

		return noticiaRepositorio.ordenarPorFechaDesc();

	}

	@Transactional(readOnly = true)
	public void modificarNoticia(Long id, String titulo, String cuerpo,MultipartFile imagen) throws MiException {

		validar(titulo,cuerpo);
		
		String image="";
		
		if (!imagen.isEmpty()) {
			
			try {
				image = Base64.encodeBytes(imagen.getBytes());
			} catch (IOException e) {
				image="No hay imagen";
				e.printStackTrace();
			}
		}
		
		Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
		
		if (respuesta.isPresent()) {

			Noticia noticia = respuesta.get();

			noticia.setTitulo(titulo);
			noticia.setCuerpo(cuerpo);
			noticia.setImagen(image);

			noticiaRepositorio.save(noticia);

		}

	}
	
	public Optional<Noticia> findById(Long id) {
		return noticiaRepositorio.findById(id);
	}

	private void validar(String titulo, String cuerpo) throws MiException {


		if (titulo == null || titulo.isEmpty()) {
			throw new MiException("El titulo no puede ser nulo o estar vacio");
		}

		if (cuerpo == null || cuerpo.isEmpty()) {
			throw new MiException("El cuerpo no puede ser nulo o estar vacio");
		}

	}

}