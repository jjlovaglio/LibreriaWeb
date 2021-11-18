/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author josejlovaglio
 */
@Service
public class EditorialServicio {
    
    @Autowired            
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void cargar(
        String nombre
        ) {
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial);
        
        
    }
}