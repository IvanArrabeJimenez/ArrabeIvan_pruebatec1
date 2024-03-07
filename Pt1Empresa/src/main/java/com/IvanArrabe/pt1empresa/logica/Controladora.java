package com.IvanArrabe.pt1empresa.logica;

import com.IvanArrabe.pt1empresa.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearEmpleado(Empleado empleado) {
        controlPersis.crearEmpleado(empleado);
    }

    public List<Empleado> listarEmpleado() {
        return controlPersis.listarEmpleado();
    }

    public Empleado buscarEmpleado(int editarEmpleado) {
        return controlPersis.buscarEmpleado(editarEmpleado);
    }

    public void editarEmpleado(Empleado empleadoEditar) {
        controlPersis.editarEmpleado(empleadoEditar);
    }

    public void eliminarEmpleado(int idEmpleado) {
        controlPersis.eliminarEmpleado(idEmpleado);
    }

    public List<Empleado> listaEmpleadoNoBorrado() {
        return controlPersis.listaEmpleadoNoBorrado();
    }

    public List<Empleado> buscarCargoEmpleado(String cargo) {
        return controlPersis.buscarCargoEmpleado(cargo);
    }

}
