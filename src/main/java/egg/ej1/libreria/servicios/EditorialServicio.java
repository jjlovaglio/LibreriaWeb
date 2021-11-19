/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import java.util.Optional;
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
    
        @Transactional
    public void modificar(
            String idEditorial,
            String nombre
            ) {
        
        // validaciones aquí
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(idEditorial);
        
        if(respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        } else {
            System.out.println("Error: No existe un editorial con el id solicitado");
        }
        
        
    }
    
    
    
    
    
}