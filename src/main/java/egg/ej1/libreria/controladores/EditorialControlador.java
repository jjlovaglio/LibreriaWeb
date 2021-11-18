/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import egg.ej1.libreria.servicios.EditorialServicio;
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
 */
@Controller
@RequestMapping("/editoriales")
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @GetMapping("/")
    public String listarEditoriales(
            ModelMap model) {

        List<Editorial> editoriales = editorialRepositorio.findAll();
        model.put("editoriales", editoriales);

        return "editorial.html";
    }

    @PostMapping("/")
    public String cargarLibro(
            @RequestParam String nombre,
            ModelMap model) {

        editorialServicio.cargar(
                nombre);

        return "libro.html";
    }

    @GetMapping("/{idAutor}")
    public String editarAutor(
            @PathVariable("idAutor") String id,
            ModelMap model) {

        List<Editorial> editoriales = editorialRepositorio.findAll();
        model.put("editoriales", editoriales);

        Editorial e = editorialRepositorio.getById(id);
        model.put("editorial", e);

        return "editorialEditar.html";
    }

}
