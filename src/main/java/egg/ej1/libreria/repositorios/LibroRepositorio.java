
package egg.ej1.libreria.repositorios;

import egg.ej1.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    
    @Query("SELECT c FROM Libro c WHERE c.alta IS TRUE ")
    public List<Libro> findAllActive();
   
    
    
}
