/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jpa.entidad.Autor;
import jpa.entidad.Editorial;
import jpa.entidad.Libro;
import jpa.persistencia.LibroDao;

/**
 *
 * @author mirod
 */
public class LibroServicio {

    private final LibroDao libroDao;

    public LibroServicio() {
        libroDao = new LibroDao();
    }

    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {
        try {
            if (isbn == null || isbn.toString().trim().isEmpty()) {
                throw new Exception("El isbn es obligatorio");
            }

            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("El titulo es obligatorio");
            }

            if (anio == null || anio.toString().trim().isEmpty()) {
                throw new Exception("El año es obligatorio");
            }

            if (ejemplares == null || ejemplares.toString().trim().isEmpty()) {
                throw new Exception("La cantidad de ejemplares es obligatoria");
            }

            if (ejemplaresPrestados == null || ejemplaresPrestados.toString().trim().isEmpty()) {
                throw new Exception("La cantidad de ejemplares prestados es obligatoria");
            }

            if (ejemplaresRestantes == null || ejemplaresRestantes.toString().trim().isEmpty()) {
                throw new Exception("La cantidad de ejemplares restantes es obligatoria");
            }

            if (autor == null) {
                throw new Exception("El autor es obligatorio");
            }

            if (editorial == null) {
                throw new Exception("La editorial es obligatoria");
            }

            Libro libro = new Libro();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libroDao.insertar(libro);

        } catch (Exception e) {
            throw new Exception("Error al crear libro.");
        }
    }

    public void modificarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {
        try {
            if (isbn == null || isbn.toString().trim().isEmpty()) {
                throw new Exception("El isbn es obligatorio");
            }

            Libro libroAModificar = libroDao.obtenerPorIsbn(isbn);
            Libro libro = new Libro();
            libro.setIsbn(isbn);

            if (titulo == null || titulo.trim().isEmpty()) {
                libro.setTitulo(libroAModificar.getTitulo());
            } else {
                libro.setTitulo(titulo);
            }

            if (anio == null || anio.toString().trim().isEmpty()) {
                libro.setAnio(libroAModificar.getAnio());
            } else {
                libro.setAnio(anio);
            }

            if (ejemplares == null || ejemplares.toString().trim().isEmpty()) {
                libro.setEjemplares(libroAModificar.getEjemplares());
            } else {
                libro.setEjemplares(ejemplares);
            }

            if (ejemplaresPrestados == null || ejemplaresPrestados.toString().trim().isEmpty()) {
                libro.setEjemplaresPrestados(libroAModificar.getEjemplaresPrestados());
            } else {
                libro.setEjemplaresPrestados(ejemplaresPrestados);
            }

            if (ejemplaresRestantes == null || ejemplaresRestantes.toString().trim().isEmpty()) {
                libro.setEjemplaresRestantes(libroAModificar.getEjemplaresRestantes());
            } else {
                libro.setEjemplaresRestantes(ejemplaresRestantes);
            }

            if (autor == null) {
                libro.setAutor(libroAModificar.getAutor());
            } else {
                libro.setAutor(autor);
            }

            if (editorial == null) {
                libro.setEditorial(libroAModificar.getEditorial());
            } else {
                libro.setEditorial(editorial);
            }

            libroDao.actualizar(libro);

        } catch (Exception e) {
            throw new Exception("Error al modificar libro.");
        }
    }

    public void eliminarLibro(Long isbn) throws Exception {
        try {
            if (isbn == null) {
                throw new Exception("Debe indicar el isbn del libro a eliminar");
            }
            Libro libroAEliminar;
            libroAEliminar = libroDao.obtenerPorIsbn(isbn);
            libroDao.eliminar(libroAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar editorial");
        }
    }

    public void imprimirLibros(List<Libro> libros) throws Exception {
        try {
            if (libros.isEmpty()) {
                System.out.println("No existen libros");
            } else {
                System.out.println("LISTA DE LIBROS");
                System.out.printf("%-20s%-50s%-20s%-20s%-20s%-20s\n", "ISBN", "TITULO", "AÑO", "EJEMPLARES", "AUTOR", "EDITORIAL");
                for (Libro libro : libros) {
                    System.out.printf("%-20s%-50s%-20s%-20s%-20s%-20s\n", libro.getIsbn(), libro.getTitulo(), libro.getAnio(), libro.getEjemplares(), libro.getAutor().getNombre(), libro.getEditorial().getNombre());
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error al mostrar libros");
        }
    }

    public void buscarPorIsbn(Long isbn) throws Exception {
        try {
            Libro libro = libroDao.obtenerPorIsbn(isbn);
            System.out.println("Libro con el isbn " + isbn);
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "ISBN", "TITULO", "AÑO", "EJEMPLARES", "AUTOR", "EDITORIAL");
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", libro.getIsbn(), libro.getTitulo(), libro.getAnio(), libro.getEjemplares(), libro.getAutor().getNombre(), libro.getEditorial().getNombre());
        } catch (Exception e) {
            throw new Exception("Error al mostrar libros por isbn");
        }
    }

    public List<Libro> obtenerTodos() throws Exception {
        try {
            return libroDao.obtenerTodos();
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }

    public void buscarPorTitulo(String titulo) throws Exception {
        try {
            List<Libro> libros = libroDao.obtenerPorTitulo(titulo);
            System.out.println("Libros titulados " + titulo);
            imprimirLibros(libros);
        } catch (Exception e) {
            throw new Exception("Error al buscar por titulo");
        }
    }

    public void buscarPorAutor(String autor) throws Exception {
        try {
            List<Libro> libros = Arrays.asList();
            libros = libroDao.obtenerPorAutor(autor);
            System.out.println("Libros de " + autor);
            imprimirLibros(libros);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error al buscar por autor");
        }
    }

    public void buscarPorEditorial(String editorial) throws Exception {
        try {
            List<Libro> libros = Arrays.asList();
            libros = libroDao.obtenerPorEditorial(editorial);
            System.out.println("Libros de la editorial " + editorial);
            imprimirLibros(libros);
        } catch (Exception e) {
            throw new Exception("Error al buscar por editorial");
        }
    }
}
