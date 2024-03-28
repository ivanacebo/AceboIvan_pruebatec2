# 	SISTEMA PARA GESTIÓN DE TURNOS

##	Descripción del proyecto

Este proyecto consiste en el sistema de una gestión de turnos desarrollado en java con JPA para persistir los datos en una base de datos 
y JSP para la parte visual (interfaz gráfica). Permite realizar peticiones de turno en base un registro de Ciudadanos, Funcionarios y Trámites.

## Modelo de capas

**Interfaz gráfica(igu):** Se usa el modelo JSP para la parte visual de interacción con el usuario. Este podra introducir datos, modificarlos o eliminarlos a su disposición. **Persistencia de datos:**
Se emplea una base de datos llamada "Ayuntamiento" a la cual conectamos mediante JPA. **Lógica:**

## Explicación funcionamiento del proyecto

**Igu:** Se usa una plantilla para la parte de fronted.

**Login:** funciando correctamente. No esta programado para acceder al proyecto mediante login pero si esta programado y funcionando. Seria usarlo en un futuro para no permitir acceder a todo el mundo.
Desde Usuario se puede acceder a la creación de usuarios o ver una tabla con la información de cada usuario puediendo modificarla o eliminarla.

**Turnos:** Se accede a la creación de nuevos turnos mediante la selección de fecha, hora y la selección de la lista de Funcionarios, Ciudadanos y tramites. Se puede acceder a ver una tabla con todos los turnos
pudiendo eliminar de forma lógica cada turno y modificar cada turno según la conveniencia. Se accede al filtrado por estado del turno y fecha del turno.

**Tramite:** Se accede a la creación de nuevos trámites y a la visualización en una tabla de todos los trámites. Se puede modificar los trámites creados desde la tabla o proceder a su eliminación lógica.

**Ciudadano:** Se accede a la creación de nuevos Ciudadanos y a la visualización en una tabla de todos los ciudadanos. Se puede modificar los ciudadanos creados desde la tabla o proceder a su eliminación lógica.

**Funcionarios:** Se accede a la creación de nuevos Funcionarios y a la visualización en una tabla de todos los funcionarios. Se puede modificar los funcionarios creados desde la tabla o proceder a su eliminación lógica.
Aquí podemos acceder a dar acceso al siguiente turno para atenderlo. Pasa de "En espera" a "Ya atendido"

## Explicación de la lógica por modelo de capas

**Persistencia ->**
	* Se crea la persistencia de las distintas clases creadas en la lógica. Se crea una clase controladora de la persistencia desde donde se une la persistencia a la base de datos con lógica empleada.
	* Permite mediante distintos métodos extraer, introducir, eliminar o modificar datos de nuestra base de datos. Añadimos consutaltas personalizadas para una extracción de datos.

**Lógica -> **
	* Se utilizan distintas clases como (Persona que sera la Clase madre de) Funcionario, Ciudadano, Tramite, Turno, Usuario. 
	* Creamos tambien la controladora de la lógica que será la conexión entre la parte de la persistencia de datos a la base de datos y la parte de la vista del usuario final.
	
**Interfaz gráfica ->**
	* En esta utilizamos una plantilla precargada para hacer la parte del fronted más visual de cara al usuario final. (para no repetir código separamos la parte repetitiva en otro archivo y la invocamos 
		en nuestros distintos JSP).
	* Utilizamos distintos JSP donde vamos realizando todo lo que explicamos en el funcionamiento del proyecto.
	* Utilizamos también servlets para llevar a cabo la extracción de los datos introducidos por el usuario para poder unirlo a nuestra lógica y persistir dichos datos en nuestra base de datos.

## Supuestos técnicos

1. Se incorporan varias consultas para traer datos de funcionarios no eliminados, ciudadanos no eliminados, trámites no borrados, usuarios no borrados. Se incorpora en Turno consultas para traer los turnos
	no borrados y traer el siguiente turno a atender ( poder pasar de "En espera" a "Ya atendido").
2. Se implementa el borrado lógico con un campo booleano en cada Clase utilizada.
3. Se asegura que los campos a rellenar por el usuario nunca debe dejarlos vacíos.
4. Se realiza alguna comprobación para garantizar que los valores introducidos por el usuario sean los adecuados ("telefono" - 9 dígitos | "DNI" - 8 dígitos y una letra).
6. Se manejan excepciones.
7. Se implementa lógica para cuando no existan datos y este quiera verlos le reenvie a la creación de dichos datos. 
8. Filtros personalizados para visualizar turnos en "Ya atendido" o "En espera" junto con su día (se utiliza stream una vez).

## Distintos escenarios considerados

**1 -> Eliminación lógica por ID;**
		Se adquiere el id visualizando todos los datos de la parte a eliminar que estan incluidos en la tabla.
		
**2 -> Inmutabilidad del ID en la base de datos;**
		Se garantiza que el id nunca puede ser modificado de ningua de las clases que utilizamos ya que es su identificador.
		En cuanto al Turno el id seria el número de turno que tenemos y con el que hemos sido atendidos.
		
**3 -> Prevención de duplicación de datos:**
		Se considera que el usuario debe evitar la duplicación de datos. En el caso de una confusión se ofrece la opción de eliminar dicho duplicado. Se proporciona una capacidad de listar para verificar la existencia.
		
**4 -> Gestión de atender turno:**
		El que en este caso va a atender el siguiente turno sería el Funcionario por lo que desde Funcionarios se accede a atender turno y con el boton de atender siguiente turno se buscaria el nº de turno mas bajo
		que no ha sido ya atendido ("En espera") y se modifica su estado a "Ya atendido". Cumplimos así con turnos númericos (ID), asignados por orden de llegada y modificando su estado.
		
**5 -> Unión Bidireccional en base de datos**

**6 -> Duplicidad de turno:**
		Se puede saber la duplicidad de turno con la visualización de las tablas por lo que se puede proceder a su eliminación.

## Relación entre clases
	
Se adjunta un UML.
Clase Persona de la que hereda distintos atributos tanto la clase Funcionario con sus atributos y la clase Ciudadano con sus atributos.

Un Funcionario puede atender muchos turnos pero un turno solo puede ser atendido por un Funcionario. Un Ciudadano puede pertenecer a muchos turnos pero un turno solo a un Ciudadano. Un Trámite puede estar en muchos
turnos pero en un turno solo se puede atender un Trámite.

La relación entre usuario y funcionario deberia ser 1 a 1.

## Otros escenarios considerados

* Al tener funcionarios estos deberian poder iniciar una sesión propia en los que tener que atender sus turnos.
* El ciudadano podria tener un perfil dado de alta para pedir determinados turnos.
* Según el trámite podria ser atendido por un funcionario en concreto dependiendo del departamento de este.
* Cada funcionario deberia poder dar acceso a atender a su siguiente turno.
* Administrador del sistema donde veria todos los datos de los funcionarios, ciudadanos, turnos atendidos y demás consideraciones.
* Introdicudir un horario en el funcionario de atención al ciudadano.
* Restringir los horarios de los turnos solicitados a los días laborales y horas de atención al ciudadano.
* Filtros personalizados de cara a la visualización del ciudadano con cada departamento cada hora.
* No poder seleccionar un turno por fecha y hora si se dirige al mismo departamento y funcionario.
* Visualizar trabajadores, turnos ciudadanos, tramites eliminados.

##Requisitos del sistema

- Kit de desarrollo Java (JDK) instalado en el sistema.
- Acceso a una base de datos relacional compatible (por ejemplo, MySQL, PostgreSQL, phpMyAdmin). Se agrega archivo.sql con los datos de prueba del sistema de gestión de empleados.
- Servidor de aplicaciones compatible: Se requiere un servidor de aplicaciones compatible con Java EE, como Apache Tomcat. Asegúrate de tener Tomcat instalado en tu sistema.
- Instalación y configuración
- Clonar el repositorio desde GitHub.
- Configure la conexión a la base de datos en el archivo de configuración correspondiente.
- Configuración del proyecto y ejecución desde un IDE de desarrollo. (sugerencia -> netbeans)

##Contribuciones

Las contribuciones al proyecto son bienvenidas. Se agradece cualquier corrección o sugerencia para mejorar el sistema.
