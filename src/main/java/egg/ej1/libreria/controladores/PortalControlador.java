/*

ok: poder cargar datos de un libro
ok: poder listar libros
ok: poder modificar datos de un libro
todo: poder dar de baja libros

ok-todo: poder cargar datos de un autor
ok-todo: poder modificar datos de un autor
ok-todo: poder listar autores
todo: poder dar de baja autores

ok-todo: poder cargar datos de  editorial
ok-todo: poder modificar datos de  editorial
ok-todo: poder listar editoriales
todo: poder dar de baja editoriales

todo: implementar manejo de excepciones (video YT tinder de mascotas)
todo: devolver feedback de errores al usuario (videos YT tinder Mascotas)
todo: ver anidado de plantillas HTML en Tymeleaf
todo: repasar curso de Tymeleaf (clase en drive)
todo: repasar curso de relaciones entre entidades (clase en drive)


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
