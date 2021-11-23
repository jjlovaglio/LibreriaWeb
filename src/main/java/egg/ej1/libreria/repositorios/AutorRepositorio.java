/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.repositorios;

import egg.ej1.libreria.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author josejlovaglio
 */
@Repository
public interface AutorRepositorio extends JpaRepository <Autor, String> {
    
    @Query("SELECT c FROM Autor c WHERE c.alta IS TRUE ")
    public List<Autor> findAllActive();
    
    
}
