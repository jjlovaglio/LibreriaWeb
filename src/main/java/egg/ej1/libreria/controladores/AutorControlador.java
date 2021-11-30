/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.excepciones.AutorExcepcion;
import egg.ej1.libreria.servicios.AutorServicio;
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
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    AutorServicio autorServicio;

    @GetMapping("/")
    public String listarAutores(
            ModelMap model) {

        try {
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
        } catch (AutorExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }

        return "autor.html";
    }

    @PostMapping("/")
    public String cargarAutor(
            @RequestParam String nombre,
            ModelMap model) {

        try {
            autorServicio.cargar(nombre);
            model.put("exito", "Autor cargado con éxito!");
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
        } catch (AutorExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }

        return "autor";
    }

    @GetMapping("/{idAutor}")
    public String editarAutor(
            @PathVariable("idAutor") String id,
            ModelMap model) {

        try {
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
            Autor a = autorServicio.buscarPorId(id);
            model.put("autor", a);
        } catch (AutorExcepcion e) {
            model.put("error", "Error: " + e.getMessage());
        }

        return "autorEditar.html";
    }

    @PostMapping("/{idAutor}")
    public String actualizarAutor(
            @PathVariable("idAutor") String id,
            @RequestParam String nombre,
            ModelMap model
    ) {

        try {
            autorServicio.modificar(id, nombre);
            List<Autor> autores = autorServicio.listarActivos();
            model.put("autores", autores);
            Autor a = autorServicio.buscarPorId(id);
            model.put("autor", a);
        } catch (AutorExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }

        return "redirect:";

    }

    @GetMapping("/{idAutor}/eliminar")
    public String eliminarAutor(
            @PathVariable("idAutor") String id, 
            ModelMap model
    ) {

        try {
            autorServicio.darBaja(id);
        } catch (AutorExcepcion e) {
            model.put("error", "error: " + e.getMessage());
        }

        return "redirect:/autores/";

    }

}
