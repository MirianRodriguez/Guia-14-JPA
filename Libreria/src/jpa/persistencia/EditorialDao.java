/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import jpa.entidad.Editorial;

/**
 *
 * @author mirod
 */
public class EditorialDao {
    
        
    private final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();//permite crear, borrar, actualizar y consultar entidades
    
    public void insertar(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();//inicia la transaccion
            em.persist(editorial);//guarda el objeto en la base de datos
            em.getTransaction().commit();//confirma la transaccion
        } catch (Exception e) {
            em.getTransaction().rollback();//deshace los cambios si algo fallo
            throw new Exception("Error al insertar editorial");
        }
    }

    public void actualizar(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(editorial);//merge actualiza un objeto que ya existe
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar editorial");
        }
    }

    public void eliminar(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(editorial);//elimina el objeto
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al eliminar editorial");
        }
    }

    public Editorial obtenerPorId(Integer id) throws Exception {
        try {
            Editorial editorial = em.find(Editorial.class, id);/*find busca y devuelve un objeto. Se le pasa la
            clase de objeto buscado y la clave primaria
            */
            return editorial;
        } catch (Exception e) {
            throw new Exception("Error al buscar editorial por identifcador");
        }
    }

    public List<Editorial> obtenerTodos() throws Exception {
        try {
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e", Editorial.class)
                    .getResultList(); /*createQuery permite armar consultas dinamicas.
            No es lenguaje SQL sino JPQL. Se usa el nombre de la clase, no el nombre de la tabla.
            GetResultList devuelve la lista de resultados de la consulta. Se debe guardar en un List.
            */
            return editoriales;
        } catch (Exception e) {
            throw new Exception("Error al buscar editoriales");
        }
    }

}
