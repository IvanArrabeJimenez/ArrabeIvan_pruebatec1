package com.IvanArrabe.pt1empresa;

import com.IvanArrabe.pt1empresa.logica.Controladora;
import com.IvanArrabe.pt1empresa.logica.Empleado;
import com.IvanArrabe.pt1empresa.persistencia.exceptions.EmpleadoException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Pt1Empresa {

    //He declarado todas estas variables de manera estatica para sólo tener que declararlas una vez, ya que las voy a utilizar en métodos distintos del main
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    static Controladora control = new Controladora();
    static String nombre;
    static String apellido;
    static String cargo;
    static double salario;
    static String fechaInicio;
    static int opcion;

    public static void main(String[] args) throws EmpleadoException {

        //Para mayor comodidad y mejor manejo de la aplicación he diseñado un menú para que el usuario pueda elegir la opción que prefiera
        System.out.println("BIENVENIDO A NUESTRO SISTEMA DE GESTIÓN DE EMPLEADOS!!");
        do {
            System.out.println("""
                               
                               Que desea hacer:
                               1. Agregar un nuevo empleado.
                               2. Listar empleados.
                               3. Actualizar informaci\u00f3n de un empleado.
                               4. Eliminar un empleado.
                               5. Buscar empleados por cargo.
                               0. Salir.
                               Por favor, seleccione una opción entre 1 y 5 para gestionar los empleados o si desea salir pulse 0:
                               """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            //En el switch vamos a llamar a los métodos que correspondan para cada acción y la lógica la desarrollamos en los propios métodos 
            switch (opcion) {
                case 1 ->
                    agregarEmpleado();
                case 2 ->
                    listarEmpleado();
                case 3 ->
                    actualizarEmpleado();
                case 4 ->
                    eliminarEmpleado();
                case 5 ->
                    buscarEmpleado();
                case 0 ->
                    salir();
                default ->
                    opcionPorDefecto();
            }
        } while (opcion != 0);
    }

    private static void agregarEmpleado() {
        //Introducimos los datos del empleado y lo registramos en la base de datos

        System.out.println("Por favor, dígame el nombre del empleado: ");
        nombre = scanner.nextLine();

        System.out.println("Por favor, dígame el apellido del empleado: ");
        apellido = scanner.nextLine();

        System.out.println("Por favor, dígame el cargo que ocupa: ");
        cargo = scanner.nextLine();

        System.out.println("Por favor, dígame el salario del empleado: ");
        salario = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Por favor, dígame la fecha de inicio del empleado (dd/mm/aaaa): ");
        fechaInicio = scanner.nextLine();

        //Creamos el objeto empleado y por medio de la controladora se lo enviamos por medio de una función a la controladora 
        Empleado empleado = new Empleado(nombre, apellido, cargo, salario, fechaInicio);
        boolean agregarEmpleado = true;
        //Mediante el uso de excepciones comprobamos que al introducir todos los datos del empleado sean correctos o no haya ningún campo vacío
        try {
            validarDatos(empleado);
        } catch (EmpleadoException ex) {
            System.out.println("Se ha detectado una excepción: " + ex.getMessage());
            agregarEmpleado = false;
        }
        if (agregarEmpleado == true) {
            control.crearEmpleado(empleado);
            System.out.println("Empleado agregado correctamente!!");
        } else {
            System.out.println("No se ha agregado el empleado por falta de datos");
        }
    }

    private static void listarEmpleado() {
        System.out.println("LISTA DE EMPLEADOS:");
        /*Traemos todos los registros de la BBDD y los almacenamos en una lista para luego mostrarlos recorriendo la lista con un for each.
        En esta lista empleamos la lista de empleado no borrado que implementamos tras eliminar empleados(editamos con el boolean para que al hacer la consulta 
        no nos aparezcan)*/
        List<Empleado> listaEmpleados = control.listaEmpleadoNoBorrado();
        if (listaEmpleados.isEmpty()) {
            System.out.println("LA LISTA ESTA VACÍA!!");
        }
        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado.toString());
        }
    }

    private static void actualizarEmpleado() {
        boolean existeEmpleado = false;
        //Utilizamos un ciclo while para comprobar que el ID seleccionado existe y de no ser así lo segirá preguntando hasta que se introduzca un ID válido
        //Cuando encuentre un empleado con ID válido nos lo mostrará en consola
        while (!existeEmpleado) {
            System.out.println("Por favor, diga el ID del empleado que quiere editar:");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();
            Empleado empleadoEditar = control.buscarEmpleado(idEmpleado);
            List<Empleado> listaEmpleados = control.listarEmpleado();
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getId() == idEmpleado && empleado.getBorrado() == false) {
                    System.out.println("El empleado seleccionado es:");
                    System.out.println(empleado.toString());
                    existeEmpleado = true;

                    //Si existe el empleado procedemos a modificar los datos
                    //De nuevo ponemos un menú para que el usuario elija el dato a modificar.
                    //Lo metemos dentro de un ciclo do while por si quiere modificar varios datos del empleado y si no quiere modificar nada tiene la opción de no modificar
                    do {
                        System.out.println("""
                               Por favor, indique el dato que quiera modificar:
                               1.Nombre.
                               2.Apellido.
                               3.Cargo.
                               4.Salario.
                               5.Fecha de inicio.
                               6.No modificar datos.
                               Por favor, seleccione una opción entre 1 y 5 para modificar los datos y 0 si no quiere modificar ningún dato.
                               """);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1 -> {
                                System.out.println("Por favor, dígame el nombre del empleado: ");
                                nombre = scanner.nextLine();
                                empleadoEditar.setNombre(nombre);
                                control.editarEmpleado(empleadoEditar);
                                System.out.println("empleado actualizado!");
                            }
                            case 2 -> {
                                System.out.println("Por favor, dígame el apellido del empleado: ");
                                apellido = scanner.nextLine();
                                empleadoEditar.setApellido(apellido);
                                control.editarEmpleado(empleadoEditar);
                                System.out.println("empleado actualizado!");
                            }
                            case 3 -> {
                                System.out.println("Por favor, dígame el cargo que ocupa: ");
                                cargo = scanner.nextLine();
                                empleadoEditar.setCargo(cargo);
                                control.editarEmpleado(empleadoEditar);
                                System.out.println("empleado actualizado!");
                            }
                            case 4 -> {
                                System.out.println("Por favor, dígame el salario del empleado: ");
                                salario = scanner.nextDouble();
                                scanner.nextLine();
                                empleadoEditar.setSalario(salario);
                                control.editarEmpleado(empleadoEditar);
                                System.out.println("empleado actualizado!");
                            }
                            case 5 -> {
                                System.out.println("Por favor, dígame la fecha de inicio del empleado (dd/mm/aaaa: ");
                                fechaInicio = scanner.nextLine();
                                empleadoEditar.setFechaInicio(fechaInicio);
                                control.editarEmpleado(empleadoEditar);
                                System.out.println("empleado actualizado!");
                            }
                            case 6 ->
                                System.out.println("No se ha modificado ningún dato!!");
                            default ->
                                opcionPorDefecto();
                        }
                    } while (opcion != 6);
                }
            }
            if (!existeEmpleado) {
                System.out.println("No exixte ningún empleado con el ID seleccionado!!");
            }
        }
    }

    private static void eliminarEmpleado() {
        boolean existeEmpleado = false;
        //Volvemos a buscar el empleado por su ID para confirmar que el empleado existe y proceder posteriormente a su eliminación
        while (!existeEmpleado) {
            System.out.println("Por favor, diga el ID del empleado que quiere eliminar:");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();
            List<Empleado> listaEmpleados = control.listarEmpleado();
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getId() == idEmpleado && empleado.getBorrado() == false) {
                    System.out.println("El empleado seleccionado es:");
                    System.out.println(empleado.toString());
                    existeEmpleado = true;
                    /*Si existe el empleado procedemos a eliminarlo (en realidad lo editamos para que no sea visible al listarlo en la opción 2 del menú
                    lo que hacemos es un borrado lógico o soft delete en el que los datos permanecen en la base de datos pero en la aplicación se supone que están 
                    eliminados*/
                    control.eliminarEmpleado(idEmpleado);
                }
            }
            if (!existeEmpleado) {
                System.out.println("No exixte ningún empleado con el ID seleccionado!!");
            }

        }
        System.out.println("empleado eliminado!!");
    }

    private static void buscarEmpleado() {
        //Lo primero que tenemos que hacer es filtrar nuestra base de datos de empleados y buscar por el cargo que nosotros queramos
        System.out.println("Por favor, dígame el cargo que quiere buscar:");
        cargo = scanner.nextLine();
        List<Empleado> cargoEmpleados = control.buscarCargoEmpleado(cargo);

        //Después de realizar el filtro por cargo procedemos a visualizar los empleados que tienen ese cargo
        if (cargoEmpleados.isEmpty()) {
            System.out.println("No hay ningún empleado con el cargo seleccionado!!");
        }
        for (Empleado empleado : cargoEmpleados) {
            if (empleado.getBorrado() == false) {
                System.out.println(empleado.toString());
            }
        }
    }

    private static void salir() {
        //En esta opción salimos diréctamente de la aplicación sin realizar ninguna operación
        System.out.println("""
                           GRACIAS POR UTILIZAR NUESTROS SERVICIOS
                           HASTA PRONTO
                           SALIENDO DEL SISTEMA....""");
    }

    private static void opcionPorDefecto() {
        System.out.println("OPCIÓN INCORRECTA! Por favor elija una opción entre 0 y 5:");
    }

    private static void validarDatos(Empleado empleado) throws EmpleadoException {
        if (empleado.getNombre().equals("")) {
            throw new EmpleadoException("No se ha introducido ningún nombre!!");
        }
        if (empleado.getApellido().equals("")) {
            throw new EmpleadoException("No se ha introducido ningún apellido!!");
        }
        if (empleado.getCargo().equals("")) {
            throw new EmpleadoException("No se ha introducido ningún cargo!!");
        }
        if (empleado.getSalario() == 0) {
            throw new EmpleadoException("No se ha introducido ningún salario!!");
        }
        if (empleado.getFechaInicio().equals("")) {
            throw new EmpleadoException("No se ha introducido ninguna fecha!!");
        }
    }
}
