package com.LibreriaApp.servicios;

import com.LibreriaApp.entidades.Editorial;
import com.LibreriaApp.entidades.Libro;
import com.LibreriaApp.errores.ErrorServicio;
import com.LibreriaApp.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aleidy Alfonzo
 */
@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

     @Transactional
    public void crearEditorial(String nombre) throws ErrorServicio {

        validarDatos(nombre);

        Editorial editorial = new Editorial();
//         List<Libro> listaLibros = new ArrayList();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

       editorialRepositorio.save(editorial);
    }
    
 @Transactional
    public Editorial modificarEditorial(String id, String nombre) throws ErrorServicio {
         //Validamos
        validarDatos(nombre);

        //Comprobamos si el usuario existe
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
             //Modificamos los valores
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

             //Guardamos los valores en la DB
           return editorialRepositorio.save(editorial);

        } else {
            throw new ErrorServicio("No se encontro la editorial con el id solicitado");
        }
    }

     @Transactional
    public void DarDeBajaEditorial(String id) throws ErrorServicio {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);

            editorialRepositorio.save(editorial);

        } else {
            throw new ErrorServicio("No se encontro la editorial con el id solicitado");
        }
    }
    
     public void validarDatos(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        }
    }
       
      @Transactional(readOnly = true)
    public List<Editorial> listarTodos(){
        return editorialRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public Editorial buscaPorNombre(String nombre){
        return editorialRepositorio.buscarEditorialPorNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public Editorial buscarEditorialPorId(String id) {        
        return editorialRepositorio.buscarEditorialPorId(id);
    }
    
    @Transactional(readOnly = true)
    public Editorial getOne(String id) {
        return editorialRepositorio.getOne(id);
    }
    
    
    
    
    
}


//@Transactional
//    public void DarDeAltaEditorial(String id) throws ErrorServicio {
//
//        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
//        if (respuesta.isPresent()) {
//            Editorial editorial = respuesta.get();
//            editorial.setAlta(true);
//
//            editorialRepositorio.save(editorial);
//
//        } else {
//            throw new ErrorServicio("No se encontro la editorial con el id solicitado");
//        }
//    }