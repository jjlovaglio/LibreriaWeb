/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ej1.libreria.servicios;

import egg.ej1.libreria.entidades.Autor;
import egg.ej1.libreria.excepciones.AutorExcepcion;
import egg.ej1.libreria.repositorios.AutorRepositorio;
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
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public List<Autor> listarActivos() throws AutorExcepcion {

        try {
            return autorRepositorio.findAllActive();
        } catch (Exception e) {
            throw new AutorExcepcion("Hubo un problema para traer los autores activos.");
        }

    }

    @Transactional
    public Autor buscarPorId(String id) throws AutorExcepcion {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new AutorExcepcion("No se ha encontrado el autor solicitado.");
        }

    }

    @Transactional
    public void darBaja(String id) throws AutorExcepcion {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
        } else {
            throw new AutorExcepcion("No se ha encontrado el autor solicitado.");
        }

    }

    @Transactional
    public void cargar(
            String nombre
    ) {

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);

        autorRepositorio.save(autor);

    }

    @Transactional
    public void modificar(
            String idAutor,
            String nombre
    ) {

        // validaciones aquí
        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();
            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        } else {
            System.out.println("Error: No existe un Autor con el id solicitado");
        }

    }

}
