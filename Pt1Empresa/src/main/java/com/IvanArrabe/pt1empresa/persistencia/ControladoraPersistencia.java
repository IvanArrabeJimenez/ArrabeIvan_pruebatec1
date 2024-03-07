package com.IvanArrabe.pt1empresa.persistencia;

import com.IvanArrabe.pt1empresa.logica.Empleado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empJpa = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) {
        empJpa.create(empleado);
    }

    public List<Empleado> listarEmpleado() {
        return empJpa.findEmpleadoEntities();
    }

    public Empleado buscarEmpleado(int editarEmpleado) {
        return empJpa.findEmpleado(editarEmpleado);
    }

    public void editarEmpleado(Empleado empleadoEditar) {
        try {
            empJpa.edit(empleadoEditar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarEmpleado(int idEmpleado) {
        empJpa.softDeleteEmpleado(idEmpleado);
    }

    public List<Empleado> listaEmpleadoNoBorrado() {
        return empJpa.listEmpleadoNoBorrado();
    }

    public List<Empleado> buscarCargoEmpleado(String cargo) {
        return empJpa.findRoleEmpleado(cargo);
    }

}
