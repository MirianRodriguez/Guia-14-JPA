/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import jpa.entidad.Libro;

/**
 *
 * @author mirod
 */
public class LibroDao {
    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();//permite crear, borrar, actualizar y consultar entidades
    
    public void insertar(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();//inicia la transaccion
            em.persist(libro);//guarda el objeto en la base de datos
            em.getTransaction().commit();//confirma la transaccion
        } catch (Exception e) {
            em.getTransaction().rollback();//deshace los cambios si algo fallo
            throw new Exception("Error al insertar libro");
        }
    }

    public void actualizar(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(libro);//merge actualiza un libro que ya existe
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar libro");
        }
    }

    public void eliminar(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(libro);//elimina un libro
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al eliminar libro");
        }
    }

    public Libro obtenerPorIsbn(long id) throws Exception {
        try {
            Libro libro = em.find(Libro.class, id);/*find busca y devuelve un objeto. Se le pasa la
            clase de objeto buscado y la clave primaria
            */
            return libro;
        } catch (Exception e) {
            throw new Exception("Error al buscar libro por isbn");
        }
    }

    public List<Libro> obtenerTodos() throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class)
                    .getResultList(); /*createQuery permite armar consultas dinamicas.
            No es lenguaje SQL sino JPQL. Se usa el nombre de la clase, no el nombre de la tabla.
            GetResultList devuelve la lista de resultados de la consulta. Se debe guardar en un List.
            */
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }

    public List<Libro> obtenerPorTitulo(String titulo) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :t", Libro.class)
                    .setParameter("t", "%" + titulo + "%")
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros por titulo");
        }
    }
    
    public List<Libro> obtenerPorAutor(String autor) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :autor", Libro.class)
                    .setParameter("autor", "%" + autor + "%")
                    .getResultList();
            //CONCAT('%',:autor ,'%')
            return libros;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error al buscar libros por nombre de autor");
        }
    }
    
    public List<Libro> obtenerPorEditorial(String editorial) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :editorial", Libro.class)
                    .setParameter("editorial", "%" + editorial + "%")
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros por nombre de autor");
        }
    }
}
