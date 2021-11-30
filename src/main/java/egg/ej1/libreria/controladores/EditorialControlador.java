/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.excepciones.EditorialExcepcion;
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

    @GetMapping("/")
    public String listarEditoriales(
            ModelMap model) {

        try {
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
        } catch (EditorialExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }

        return "editorial.html";
    }

    @PostMapping("/")
    public String crearEditorial(
            @RequestParam String nombre,
            ModelMap model) {

        try {
            editorialServicio.cargar(nombre);
            model.put("exito", "Editorial cargada con éxito!");
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
        } catch (EditorialExcepcion e) {
            model.put("error", "error" + e.getMessage());
        }

        return "editorial";
    }

    @GetMapping("/{idEditorial}")
    public String editarEditorial(
            @PathVariable("idEditorial") String id,
            ModelMap model) {

        try {
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
            Editorial e = editorialServicio.buscarPorId(id);
            model.put("editorial", e);
        } catch (EditorialExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }

        return "editorialEditar.html";
    }

    @PostMapping("/{idEditorial}")
    public String actualizarEditorial(
            @PathVariable("idEditorial") String id,
            @RequestParam String nombre,
            ModelMap model
    ) {

        try {
            editorialServicio.modificar(id, nombre);
            List<Editorial> editoriales = editorialServicio.listarActivos();
            model.put("editoriales", editoriales);
            Editorial e = editorialServicio.buscarPorId(id);
            model.put("editorial", e);
        } catch (EditorialExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }

        return "redirect:";

    }

    @GetMapping("/{idEditorial}/eliminar")
    public String eliminarEditorial(
            @PathVariable("idEditorial") String id,
            ModelMap model
    ) {
        
        try {
            editorialServicio.darBaja(id);
        } catch (EditorialExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }
        return "redirect:/editoriales/";

    }

}
