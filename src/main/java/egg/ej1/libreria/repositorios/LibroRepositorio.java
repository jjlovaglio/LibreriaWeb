/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.repositorios;

import egg.ej1.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author josejlovaglio
 * 
 * en este paquete se crearán los repositorios que servirán como interfaces
 * entre el modelo de objetos y la base de datos relacional.
 * 
 * 
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
}
