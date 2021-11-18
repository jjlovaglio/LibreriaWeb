/*

ok-todo: poder cargar datos de un libro
ok-todo: poder listar libros
todo: poder modificar datos de un libro
todo: poder dar de baja libros

ok-todo: poder cargar datos de un autor
todo: poder modificar datos de un autor
ok-todo: poder listar autores
todo: poder dar de baja autores

ok-todo: poder cargar datos de  editorial
todo: poder modificar datos de  editorial
ok-todo: poder listar editoriales
todo: poder dar de baja editoriales

todo: manejo de excepciones como en el tinder de mascotas
todo: devolver feedback de errores al usuario

 */
package egg.ej1.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalControlador {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    
    

    
}
