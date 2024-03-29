package com.IvanArrabe.pt1empresa.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Atributos de la clase
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private String fechaInicio;
    private Boolean borrado;

    //Constructor vacío
    public Empleado() {
    }

    //Constructor con parámetros
    //No incluimos el id en el constructor ya que al ser autoincremental no va a tener en cuenta el dato que aportemos, si no el orden que ocupa en la BBDD
    public Empleado(String nombre, String apellido, String cargo, double salario, String fechaInicio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
        this.borrado = false;
    }

    //Métodos Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Empleado: " + "id: " + id + ", nombre: " + nombre + ", apellido: " + apellido + ", cargo: " + cargo + ", salario: " + salario + "€" + ", fechaInicio: " + fechaInicio;
    }
}
