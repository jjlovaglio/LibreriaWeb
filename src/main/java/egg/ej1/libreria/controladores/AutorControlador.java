/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.repositorios.AutorRepositorio;
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

    @Autowired
    AutorRepositorio autorRepositorio;

    @GetMapping("/")
    public String listarAutores(
            ModelMap model) {

        List<Autor> autores = autorRepositorio.findAll();
        model.put("autores", autores);

        return "autor.html";
    }

    @PostMapping("/")
    public String cargarAutor(
            @RequestParam String nombre,
            ModelMap model) {

        autorServicio.cargar(
                nombre);

        List<Autor> autores = autorRepositorio.findAll();
        model.put("autores", autores);
        
        return "autor.html";
    }

    @GetMapping("/{idAutor}")
    public String editarAutor(
            @PathVariable("idAutor") String id,
            ModelMap model) {

        List<Autor> autores = autorRepositorio.findAll();
        model.put("autores", autores);

        Autor a = autorRepositorio.getById(id);
        model.put("autor", a);

        return "autorEditar.html";
    }

    @PostMapping("/{idAutor}")
    public String actualizarAutor(
            @PathVariable("idAutor") String id,
            @RequestParam String nombre,
            ModelMap model
    ) {

        autorServicio.modificar(id, nombre);

        List<Autor> autores = autorRepositorio.findAll();
        model.put("autores", autores);
        
        Autor a = autorRepositorio.getById(id);

        model.put("autor", a);
        
        return "autorEditar.html";

    }
    
}
