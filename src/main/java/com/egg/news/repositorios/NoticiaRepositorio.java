package com.egg.news.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.news.entidades.Noticia;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
	
	@Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
	public Noticia buscarPorTitulo(@Param("titulo") String titulo);
	
	//ToDo Hacer Query para buscar por Autor
	
	@Query(value = "SELECT * FROM noticia ORDER BY alta ASC LIMIT 9", nativeQuery = true)
	public List<Noticia> ordenarPorFechaDesc();

}
