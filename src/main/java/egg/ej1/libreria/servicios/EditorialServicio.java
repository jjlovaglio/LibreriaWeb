package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Editorial;
import egg.ej1.libreria.excepciones.EditorialExcepcion;
import egg.ej1.libreria.repositorios.EditorialRepositorio;
import java.util.List;
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
    public Editorial buscarPorId(String id) throws EditorialExcepcion {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new EditorialExcepcion("No se ha encontrado la editorial solicitada.");
        }

    }

    @Transactional
    public void darBaja(String id) throws EditorialExcepcion {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial autor = respuesta.get();
            autor.setAlta(false);
            editorialRepositorio.save(autor);
        } else {
            throw new EditorialExcepcion("No se ha encontrado el editorial solicitado.");
        }

    }

    
    @Transactional
    public List<Editorial> listarActivos() throws EditorialExcepcion {

        try {
            return editorialRepositorio.findAllActive();
        } catch (Exception e) {
            throw new EditorialExcepcion("Hubo un problema para traer las editoriales activas.");
        }

    }

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

        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        } else {
            System.out.println("Error: No existe un editorial con el id solicitado");
        }

    }

}
