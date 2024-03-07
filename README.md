# Índice

1. [Documentación de proyecto de Gestión de Empleados con JPA](#Documentación-de-proyecto-de-Gestión-de-Empleados-con-JPA)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Instalación](#instalación)
5. [Uso](#uso)
6. [Configuración](#configuración)
7. [Estructura de Carpetas y Archivos](#estructura-de-carpetas-y-archivos)
8. [Contribuciones](#contribuciones)
9. [Documentación de Métodos y Clases](#documentación-de-métodos-y-clases)
10. [Supuestos](#supuestos)
11. [Problemas Conocidos](#problemas-conocidos)
12. [Licencia](#licencia)
13. [Contacto](#contacto)


# Documentación de proyecto de Gestión de Empleados con JPA
Mediante esta aplicación podremos gestionar empleados almacenados en una base de datos, implementada con JPA (Java Persistence API). La aplicación proporciona un menú con diversas opciones para interactuar con los datos de los empleados.
Por el momento, el manejo de la aplicación se reduce a la consola del IDE que estemos utilizando, pero en versiones posteriores se implementará una interfaz gráfica para su manejo más simple e intuitivo.

## Estado del proyecto
Esta aplicación está aún en desarrollo, pero ya se han implementado las funciones necesarias para realizar las pruebas pertinentes y comprobar que todo lo que nos solicita el cliente funciona correctamente.

## Tecnologías utilizadas
* JAVA 17 (JDK 17).
* Conexión a Bases de Datos con JPA (Java Persistence API).
* Proovedor de JPA EclipseLink.
* MySQL phpMyAdmin.

## Instalación
Para poder instalar esta aplicación basta con poseer un IDE con versión de JAVA 17 y JDK 17. Se recomienda utilizar NetBeans (pero no instale la última versión, ya que está dando problemas al crear la unidad de persistencia)para la creación del proyecto, ya que ayuda mucho pero una vez creado se puede utilizar el IDE que más cómodo nos resulte.
Hay que tener instalado también XAMP para la conexión con la base de datos y una aplicación web como por ejemplo phpMyAdmin para la administración de la base de datos.
Una vez que comprobamos que tenemos todo iniciamos XAMP y entramos en phpMyAdmin y lanzamos el proyecto y pulsamos en Run, y en este momento se puede comenzar a utilizar la aplicación.

## Uso
Una vez que inicia la aplicación nos aparecerá un menú con 6 opciones:
### 1. Agregar un nuevo empleado.
* Permite al usuario ingresar detalles del empleado a través de la consola.
* Requiere que todos los campos estén completos, de lo contrario, arroja una excepción.
* Si todos los detalles son correctos, el empleado se registra en la base de datos.
### 2. Listar empleados.
* Lista todos los empleados activos (los que no han sido eliminados lógicamente) recuperados de la base de datos lanzando una consulta.
* Implementa la eliminación lógica, marcando a los empleados como eliminados sin quitarlos de la base de datos.
### 3. Actualizar información de un empleado.
* Solicita el ID del empleado a modificar, verificando su validez, ya que si no encuentra el ID proporcionado mandará un mensaje de que ese trabajador no existe y lo seguirá pidiendo hasta obtener un ID válido.
* Muestra los datos del empleado que se va a modificar.
* Muestra un menú para modificar cada campo de datos del empleado. En función de la opción escogida se procederá a modificar el campo correspondiente.
* Se pueden modificar desde 1 a todos los campos ya que el menú se repetirá hasta que el usuario decida pulsar la opción de no modificar datos.
### 4. Eliminar un empleado.
* Solicita el ID del empleado a modificar, verificando su validez, ya que si no encuentra el ID proporcionado mandará un mensaje de que ese trabajador no existe y lo seguirá pidiendo hasta obtener un ID válido.
* Muestra los datos del empleado que se ha eliminado. En caso de eliminación por error podemos acceder a la base de datos y cambiarle el estado de "borrado" a false.
* Para llevar a cabo un borrado lógico y no uno físco realizaremos una edición del empleado en lugar de una eliminación. Utilizaremos un campo booleano que se activará cuando se "elimine" el trabajador, de tal manera que al listarlo no aparecerá pero lo mantendremos en la base de datos.
### 5. Buscar empleados por cargo.
* Solicita al usuario ingresar un cargo por teclado.
* Realiza una consulta a la base de datos para recuperar y mostrar empleados con el cargo especificado.
* Proporciona comentarios apropiados para roles incorrectos o inexistentes.
### 0. Salir.
* Da fin a la ejecución del programa y se despide mediante un mensaje.

## Configuración
Para la configuración basta con lo solicitado en el apartado de instalación ya que se descargará el proyecto completo y si se tiene todo lo indicado en ese punto el programa funcionará correctamente.

## Estructura de carpetas y Archivos
La estructura de carpetas y archivos se ha basado en el modelo de JPA:
* **Igu**
* **Lógica**
  - Empleado.java
  - Controladora.java
* **Persistencia**
  * **exceptions**
    - EmpleadoException.java
    - IllegalOrphanException.java
    - NonexistentEntityException.java
    - PreexistingEntityException.java
  - ControladoraPersistencia.java
  - EmpleadoJpaController.java

## Contribuciones
Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad o corrección de errores.
3. Realiza tus cambios y haz commits.
4. Sube tus cambios a tu fork.
5. Crea una solicitud de extracción.
   ¡Gracias por contribuir!

## Documentación de Métodos y clases
### Clase Pt1Empresa:
Método Main:
```java
public static void main(String[] args) throws EmpleadoException {
```
En este método se realiza toda la lógica de la aplicación, tanto la recogida de datos como su envío por medio de métodos a las diferentes capas del modelo siguiendo el orden establecido, así como la vuelta de la información para su posterior visualización.
Es el método más importante ya que se encarga del tratamiento de toda la información.

### Clase Empleado
```java
@Entity
public class Empleado implements Serializable {
```
Clase que representa la entidad Empleado mapeada a la base de datos mediante JPA.

### Clase Controladora
```java
public class Controladora {
```
Contiene métodos para realizar operaciones CRUD en la base de datos utilizando JPA (crear, leer, actualizar, eliminar). Recibe la información de Pr1Empresa.java y lo envía a ControladoraPersistencia.java

### Clase ControladoraPersistencia
```java
public class ControladoraPersistencia {
```
Contiene métodos para realizar operaciones CRUD en la base de datos utilizando JPA (crear, leer, actualizar, eliminar). Recibe la información de Controladora.java y lo envía a EmpleadoJpaController.java

### Clase EmpleadoJpaController
```java
public class EmpleadoJpaController implements Serializable {
```
Realiza las operaciones CRUD en la base de datos utilizando JPA (crear, leer, actualizar, eliminar). Recibe la información de ControladoraPersistencia.java, procesa los datos y, finalmente los devuelve en sentido contrario pasando por las clases hasta llegar a Pt1Empresa.java y mostrar los resultados.

### Los métodos más importantes a tener en cuenta se encuentran en la clase EmpleadoJpaController.java y todos ellos tienen una función para el tratamiento de la base de datos
Los métodos que se utilizan para las operaciones CRUD son: 
* **create()**, para insertar un registro en la base de datos.
* **edit()**, para editar un registro ya existente en la base de datos.
* **findEmpleadoEntities()**, se utiliza sólo cuando visualizamos el empleado a editar y el empleado a eliminar pero nos puede servir si quisieramos ver los empleados eliminados, ya que no los filtra.
* **findEmpleado()**, para conocer el id del empleado a editar.
* **softDeleteEmpleado()**, cambia el estado del campo boolean "empleadoBorrar" a true para que cuando se realice la consulta a la base de datos no aparezca.
* **listEmpleadoNoBorrado()**, realiza la consulta a la base de datos para devolver únicamente los empleados que tengan el campo "empleadoBorrar" en false.
* **findRoleEmpleado()**, realiza la consulta a la base de datos para devolver los empleados cuyo cargo se ha solicitado por teclado.

## Supuestos
* Se asume que los datos ingresados por el usuario (nombres, apellidos, cargos, etc.) son válidos y están formateados correctamente.
* No se implementa validación exhaustiva de datos de entrada para simplificar el ejemplo.
* Se asume que el usuario conoce el ID de los empleados y sus roles para que a la hora de hacer las consultas no entre en un bucle infinito en los menús de selección.
* Se asume que el usuario sabe utilizar el IDE y que sabe como se inicia el programa mediante el método main.

## Problemas conocidos
Por el momento no se tiene constancia de ningún problema salvo que en la entrada de datos introduzcamos un tipo de dato erroneo, por ejemplo, cuando pedimos el salario si en vez de un double o un int le pasamos un Strig la aplicación lanzará una excepción que no hemos controlado y se terminará con su ejecución, teniendo que volver a iniciarla para continuar con las pruebas. A medida que vayamos implementando cambios y actualizando versiones pueden surgir problemas si no se testea todo correctamente.

## Licencia
Este prouecto pertenece a **Iván Arrabé Jiménez**.

## Contacto
Para contactar con el equipo del proyecto enviar un email a **[ivanarrabe@gmail.com](mailto:ivanarrabe@gmail.com)**
