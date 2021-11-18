/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.controladores;

import egg.ej1.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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


        return "autor.html";
    }

      @PostMapping("/")
    public String cargarLibro(
            @RequestParam String nombre,
            ModelMap model) {
        
        
        autorServicio.cargar(
                nombre);
        
        

        return "libro.html";
    }  
    
}
