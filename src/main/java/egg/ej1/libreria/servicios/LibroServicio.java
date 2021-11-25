/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.entidades.Libro;
import egg.ej1.libreria.excepciones.LibroExcepcion;
import egg.ej1.libreria.repositorios.AutorRepositorio;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import egg.ej1.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author josejlovaglio
 *
 * Esta clase tiene la responsabilidad de llevar adelante las funcionalidades
 * necesarias para administrar libros (consulta, creación, modificación y dar de
 * baja).
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
    public List<Libro> listarTodos() throws LibroExcepcion {
        
        try {
            return libroRepositorio.findAll();
        } catch (Exception e) {
            throw new LibroExcepcion("Hubo un problema para traer todos los libros.");
        }
    
    }
    
    @Transactional
    public List<Libro> listarActivos() throws LibroExcepcion {
        
        try {         
            return libroRepositorio.findAllActive();
        } catch (Exception e) {
            throw new LibroExcepcion("Hubo un problema para traer los libros activos.");
        }
    
    }
    
    @Transactional
    public Libro buscarPorId(String id) throws LibroExcepcion {
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new LibroExcepcion("No se ha encontrado el libro solicitado.");
        }
        
    }
    
    @Transactional
    public void darBaja(String id) throws LibroExcepcion {
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);
            libroRepositorio.save(libro);
        } else {
            throw new LibroExcepcion("No se ha encontrado el libro solicitado.");
        }
        
    }
    
    
    
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
        System.out.println("Carga Libro: ");
        System.out.println(libro);

        libroRepositorio.save(libro);

    }

    @Transactional
    public void modificar(
            String idLibro,
            String isbn,
            String titulo,
            String anio,
            String ejemplares

    ) {

        // validaciones aquí
        Optional<Libro> respuesta = libroRepositorio.findById(idLibro);

        if (respuesta.isPresent()) {

            Libro l = respuesta.get();


            Long lIsbn = Long.parseLong(isbn);
            Integer iAnio = Integer.parseInt(anio);
            Integer iEjemplares = Integer.parseInt(ejemplares);

            l.setIsbn(lIsbn);
            l.setTitulo(titulo);
            l.setAnio(iAnio);
            l.setEjemplares(iEjemplares);
 
                    
            // sanity check
            System.out.println("Actualizacion Libro");
            System.out.println(l);
            
            libroRepositorio.save(l);
            
            
            
        } else {
            System.out.println("Error: No existe un libro con el id solicitado");
        }

    }
}
