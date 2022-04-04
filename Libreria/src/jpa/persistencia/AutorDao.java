/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import jpa.entidad.Autor;

/**
 *
 * @author mirod
 */
public class AutorDao {
    
    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();//permite crear, borrar, actualizar y consultar entidades
    
    public void insertar(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();//inicia la transaccion
            em.persist(autor);//guarda el objeto en la base de datos
            em.getTransaction().commit();//confirma la transaccion
        } catch (Exception e) {
            em.getTransaction().rollback();//deshace los cambios si algo fallo
            throw new Exception("Error al insertar autor");
        }
    }

    public void actualizar(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(autor);//merge actualiza un autor que ya existe
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar autor");
        }
    }

    public void eliminar(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(autor);//elimina un autor
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al eliminar autor");
        }
    }

    public Autor obtenerPorId(Integer id) throws Exception {
        try {
            Autor autor = em.find(Autor.class, id);/*find busca y devuelve un objeto. Se le pasa la
            clase de objeto buscado y la clave primaria
            */
            return autor;
        } catch (Exception e) {
            throw new Exception("Error al buscar autor por identifcador");
        }
    }

    public List<Autor> obtenerTodos() throws Exception {
        try {
            List<Autor> autors = em.createQuery("SELECT a FROM Autor a", Autor.class)
                    .getResultList(); /*createQuery permite armar consultas dinamicas.
            No es lenguaje SQL sino JPQL. Se usa el nombre de la clase, no el nombre de la tabla.
            GetResultList devuelve la lista de resultados de la consulta. Se debe guardar en un List.
            */
            return autors;
        } catch (Exception e) {
            throw new Exception("Error al buscar autores");
        }
    }

    public List<Autor> obtenerPorNombre(String nombre) throws Exception {
        try {
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :name", Autor.class)
                    .setParameter("name", nombre)
                    .getResultList();//SetParameter: hace que el parametro "name" se reemplace por el dato pasado "nombre"
            return autores;
        } catch (Exception e) {
            throw new Exception("Error al buscar autores por nombre");
        }
    }
}
