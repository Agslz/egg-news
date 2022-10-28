package com.egg.news.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.NoticiaServicio;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

	@Autowired
	private NoticiaServicio noticiaServicio;

	@GetMapping("/crear") // localhost:8080/noticia/crear
	public String crear() {

		return "noticiaForm";
	}

	@PostMapping("/crear")
	public String registro(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) { // String imagen

		try {
			noticiaServicio.crearNoticia(titulo, cuerpo);
			modelo.put("exito","La noticia fue cargada correctamente");
		} catch (MiException e) {
			modelo.put("error", e.getMessage());
			System.out.println(e);
			return "noticiaForm";
		}
		return "noticiaForm";

	}
	
	@GetMapping("/editar/{id}")
	public String modificar(@PathVariable Long id, ModelMap modelo) {
		modelo.put("noticia", noticiaServicio.findById(id));
		return "noticiaModificar";
	}
	
	@PostMapping("/editar/{id}")
	public String modificar(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo) throws MiException {
		try {
			noticiaServicio.modificarNoticia(id, titulo, cuerpo);	
			return "redirect:../index";
		}catch(MiException e) {
			modelo.put("error", e.getMessage());
			return "noticiaModificar";
		}
	}
}