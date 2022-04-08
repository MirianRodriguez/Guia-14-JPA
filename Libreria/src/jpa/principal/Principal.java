/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jpa.entidad.Autor;
import jpa.entidad.Editorial;
import jpa.entidad.Libro;
import jpa.servicio.AutorServicio;
import jpa.servicio.EditorialServicio;
import jpa.servicio.LibroServicio;

/**
 *
 * @author mirod
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> autores = new ArrayList<>(Arrays.asList("Horacio Quiroga", "Jorge Luis Borges", "Gabriel Garcia Marquez"));
        List<String> editoriales = new ArrayList<>(Arrays.asList("Kapeluz", "Perfil", "Columba"));

        try {
            AutorServicio autorServicio = new AutorServicio();
            EditorialServicio editorialServicio = new EditorialServicio();
            LibroServicio libroServicio = new LibroServicio();
            //carga de elementos
            for (String autor : autores) {
                autorServicio.crearAutor(autor);
            }
            for (String editorial : editoriales) {
                editorialServicio.crearEditorial(editorial);
            }
            //recuperacion de autores y editoriales
            List<Autor> autoresCargados = autorServicio.obtenerAutores();
            List<Editorial> editorialesCargadas = editorialServicio.obtenerEditoriales();
            //carga de libros
            libroServicio.crearLibro(9789295055021l, "Cuentos de la selva", 1996, 6, 3, 3, autoresCargados.get(0), editorialesCargadas.get(0));
            libroServicio.crearLibro(9789295055022l, "Ficciones", 1944, 10, 7, 3, autoresCargados.get(1), editorialesCargadas.get(1));
            libroServicio.crearLibro(9789295055023l, "La biblioteca de babel", 1941, 8, 7, 1, autoresCargados.get(1), editorialesCargadas.get(1));
            libroServicio.crearLibro(9789295055024l, "El sur", 1953, 5, 3, 2, autoresCargados.get(1), editorialesCargadas.get(1));
            libroServicio.crearLibro(9789295055025l, "Cien anios de soledad", 1967, 7, 3, 4, autoresCargados.get(2), editorialesCargadas.get(2));
            libroServicio.crearLibro(9789295055026l, "El amor en tiempos del colera", 1985, 3, 2, 1, autoresCargados.get(2), editorialesCargadas.get(2));
            List<Libro> librosCargados = libroServicio.obtenerTodos();
            //muestra autores, editoriales y libros
            autorServicio.imprimirAutores(autoresCargados);
            editorialServicio.imprimirEditoriales(editorialesCargadas);
            libroServicio.imprimirLibros(librosCargados);
            //buscar por isbn
            libroServicio.buscarPorIsbn(9789295055022l);
            //buscar por titulo
            libroServicio.buscarPorTitulo("El sur");
            //buscar por autor
            libroServicio.buscarPorAutor("pedro");
            //buscar por editorial
            libroServicio.buscarPorEditorial("kapeluz");
            //eliminar un libro
            libroServicio.eliminarLibro(9789295055024l);
            librosCargados = libroServicio.obtenerTodos();
            libroServicio.imprimirLibros(librosCargados);
            //modificar un libro
            libroServicio.modificarLibro(9789295055026l, "El amor en LOS tiempos del colera", 1986, null, null, null, null, null);
            librosCargados = libroServicio.obtenerTodos();
            libroServicio.imprimirLibros(librosCargados);
            //modificar un autor
            autorServicio.modificarAutor(1, "Pedro Perez");
            autoresCargados = autorServicio.obtenerAutores();
            autorServicio.imprimirAutores(autoresCargados);
            //modificar una editorial
            editorialServicio.modificarEditorial(2, "Perfiles");
            editorialesCargadas = editorialServicio.obtenerEditoriales();
            editorialServicio.imprimirEditoriales(editorialesCargadas);


            //eliminar editorial
            //editorialServicio.eliminarEditorial(3);
            //editorialesCargadas = editorialServicio.obtenerEditoriales();
            //editorialServicio.imprimirEditoriales(editorialesCargadas);
            
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

}
