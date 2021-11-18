/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.entidades.Libro;
import egg.ej1.libreria.repositorios.AutorRepositorio;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import egg.ej1.libreria.repositorios.LibroRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author josejlovaglio
 * 
 * Esta clase tiene la responsabilidad de llevar adelante las funcionalidades 
 * necesarias para administrar libros (consulta, creación, modificación 
 * y dar de baja).
 * 
 */
@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void cargar(
        String isbn,
        String titulo,
        String anio,
        String ejemplares,
        String idAutor,
        String idEditorial
        ) {
        
        Autor autor = autorRepositorio.getById(idAutor);
        Editorial editorial = editorialRepositorio.getById(idEditorial);
        
        Long lIsbn = Long.parseLong(isbn);
        Integer iAnio = Integer.parseInt(anio);
        Integer iEjemplares = Integer.parseInt(ejemplares);
        
        Libro libro = new Libro();
        libro.setAlta(Boolean.TRUE);
        libro.setIsbn(lIsbn);
        libro.setTitulo(titulo);
        libro.setAnio(iAnio);
        libro.setEjemplares(iEjemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(iEjemplares);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        // sanity check
        System.out.println(libro);
        
        libroRepositorio.save(libro);
        
    }
    
    
}
