/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.entidades.Libro;
import egg.ej1.libreria.excepciones.AutorExcepcion;
import egg.ej1.libreria.excepciones.EditorialExcepcion;
import egg.ej1.libreria.excepciones.LibroExcepcion;
import egg.ej1.libreria.servicios.AutorServicio;
import egg.ej1.libreria.servicios.EditorialServicio;
import egg.ej1.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author josejlovaglio
 *
 * Esta clase tiene la responsabilidad de llevar adelante las funcionalidades
 * necesarias para operar con la vista del usuario diseñada para la gestión de
 * libros (guardar/modificar libro, listar libros, dar de baja).
 *
 *
 */
@Controller
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/")
    public String listarLibros(
            ModelMap model) {

        try {
            List<Libro> libros = libroServicio.listarActivos();
            model.put("libros", libros);
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
        } catch (LibroExcepcion | AutorExcepcion | EditorialExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }

        return "libro.html";
    }

    @PostMapping("/")
    public String cargarLibro(
            @RequestParam String isbn,
            @RequestParam String titulo,
            @RequestParam String anio,
            @RequestParam String ejemplares,
            @RequestParam String idAutor,
            @RequestParam String idEditorial,
            ModelMap model) {

        try {
            
            libroServicio.cargar(
                    isbn,
                    titulo,
                    anio,
                    ejemplares,
                    idAutor,
                    idEditorial);

            model.put("exito", "Libro añadido con éxito!");
            
            List<Libro> libros = libroServicio.listarActivos();
            model.put("libros", libros);
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
        } catch (LibroExcepcion | AutorExcepcion | EditorialExcepcion e) {

            model.put("error", "Error:" + e.getMessage());
        }

        return "libro";

    }

    @GetMapping("/{idLibro}/eliminar")
    public String eliminarLibro(
            @PathVariable("idLibro") String id,
            ModelMap model
    ) {

        try {
            libroServicio.darBaja(id);
            return "redirect: ";  // todo: arreglar esta ruta para que lleve a la pagina ppal
        } catch (LibroExcepcion e) {
            model.put("error", "Error:" + e.getMessage());
        }
        return "listarLibros(model)";

    }

    @GetMapping("/{idLibro}")
    public String editarLibro(
            @PathVariable("idLibro") String id,
            ModelMap model) {

        try {
            List<Libro> libros = libroServicio.listarActivos();
            model.put("libros", libros);
            Libro l = libroServicio.buscarPorId(id);
            model.put("libro", l);
        } catch (LibroExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }
        return "libroEditar.html";

    }

    @PostMapping("/{idLibro}")
    public String actualizarLibro(
            @PathVariable("idLibro") String idLibro,
            @RequestParam String isbn,
            @RequestParam String titulo,
            @RequestParam String anio,
            @RequestParam String ejemplares,
            ModelMap model
    ) {

        libroServicio.modificar(
                idLibro,
                isbn,
                titulo,
                anio,
                ejemplares
        );

        try {
            List<Libro> libros = libroServicio.listarActivos();
            model.put("libros", libros);
            Libro l = libroServicio.buscarPorId(idLibro);
            model.put("libro", l);
        } catch (LibroExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }

        return "redirect:";

    }

}
