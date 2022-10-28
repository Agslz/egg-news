package com.egg.news.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.news.entidades.Noticia;
import com.egg.news.servicios.NoticiaServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
	
	@Autowired
	private NoticiaServicio noticiaServicio;
	
	@GetMapping("/")
	public String index(Noticia noticia, Model modelo) {
		modelo.addAttribute("noticias",noticiaServicio.listarNoticias());
		return "index";	
	}


}
