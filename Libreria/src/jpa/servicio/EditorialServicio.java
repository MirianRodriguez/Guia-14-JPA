/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.servicio;

import java.util.List;
import jpa.entidad.Editorial;
import jpa.persistencia.EditorialDao;

/**
 *
 * @author mirod
 */
public class EditorialServicio {
    
    private final EditorialDao editorialDao;

    public EditorialServicio() {
        editorialDao = new EditorialDao();
    }

    public void crearEditorial(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);

            editorialDao.insertar(editorial);
        } catch (Exception e) {
            throw new Exception("Error al crear editorial.");
        }
    }

    public void modificarEditorial(Integer id, String nombre) throws Exception {
        try {
            if (id == null){
                throw new Exception("Debe indicar el id de la editorial");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            
            Editorial editorialAModificar = new Editorial();
            editorialAModificar.setId(id);
            editorialAModificar.setNombre(nombre);
            
            editorialDao.actualizar(editorialAModificar);
        } catch (Exception e) {
            throw new Exception("Error al modificar la editorial");
        }
    }

    public void eliminarEditorial(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar el id de editorial");
            }
            Editorial editorialAEliminar;
            editorialAEliminar = editorialDao.obtenerPorId(id);
            editorialDao.eliminar(editorialAEliminar);
        } catch (Exception e) {
            throw new Exception("Error al eliminar editorial");
        }
    }

    public void imprimirEditoriales(List<Editorial> editoriales) throws Exception {
        try {
            
            if (editoriales.isEmpty()) {
                System.out.println("No existen editoriales");
            } else {
                System.out.println("LISTA DE EDITORIALES");
                System.out.printf("%-10s%-20s\n", "ID", "NOMBRE");
                for (Editorial editorial : editoriales) {
                    System.out.printf("%-10s%-20s\n", editorial.getId(), editorial.getNombre());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al mostrar editoriales");
        }
    }
    
    public List<Editorial> obtenerEditoriales() throws Exception{
        try{
            return editorialDao.obtenerTodos();
        }catch(Exception e){
            throw new Exception("Error al obtener editoriales");
        }
    }
    
}
