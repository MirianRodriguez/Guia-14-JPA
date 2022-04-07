/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.servicio;

import java.util.List;
import jpa.persistencia.AutorDao;
import jpa.entidad.Autor;

/**
 *
 * @author mirod
 */
public class AutorServicio {

    private final AutorDao autorDao;

    public AutorServicio() {
        autorDao = new AutorDao();
    }

    public void crearAutor(String nombre) throws Exception {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            Autor autor = new Autor();
            autor.setNombre(nombre);

            autorDao.insertar(autor);
        } catch (Exception e) {
            throw new Exception("Error al crear autor.");
        }
    }

    public void modificarAutor(Integer id, String nombre) throws Exception {
        try {
            if (id == null){
                throw new Exception("Debe indicar el id del autor");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            
            Autor autorAModificar = new Autor();
            autorAModificar.setId(id);
            autorAModificar.setNombre(nombre);
            
            autorDao.actualizar(autorAModificar);
        } catch (Exception e) {
            throw new Exception("Error al modificar el autor");
        }
    }

    public void eliminarAutor(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de autor");
            }
            Autor autorAEliminar;
            autorAEliminar = autorDao.obtenerPorId(id);
            autorDao.eliminar(autorAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el autor");
        }
    }

    public void imprimirAutores(List<Autor> autores) throws Exception {
        try {
            if (autores.isEmpty()) {
                System.out.println("No existen editoriales");
            } else {
                System.out.println("LISTA DE AUTORES");
                System.out.printf("%-10s%-20s\n", "ID", "NOMBRE");
                for (Autor autor : autores) {
                    System.out.printf("%-10s%-20s\n", autor.getId(), autor.getNombre());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al imprimir autores");
        }
    }
    
    public List<Autor> obtenerAutores()throws Exception{
        try{
            return autorDao.obtenerTodos();
        }catch(Exception e){
            throw new Exception("Error al obtener autores");
        }
    }
}
