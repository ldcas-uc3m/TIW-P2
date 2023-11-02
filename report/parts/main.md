# Introducción

La consiguiente práctica consistirá en una evolución de la aplicación desarrollada enla anterior entrega en la cual la información requerida se guardaba en una base de datos volátil consistente unicamente en una variable de cadena a la que accedían los servlets. Como pequeño recordatotio se volverá a definir los ebjetivos funcionales de la misma así como su estructura, que ha variado levemente respecto a la version anterior. 

La aplicación web realizada está basada en la posibilidad de agregar, editar y eliminar jugadores que podrían formar parte de la plantillas de los clubes más prestigiosos de España (eg: Atlético de Madrid, _er_ Beti, Rayo Vallecano, F.C. Barcelona... etc). Esta vez la gestión de la plantilla de jugadores queda almacenada en bases de datos basadas en el uso tanto de JPA como de JDBC.

La estructura principal que sigue el proyecto (carpeta `src/main/`) se divide en dos, una sección relacionada a la programación en **Java** (`java/`) y otra a cargo de las **WebApp** (`webapp/`). Respecto a la parte de **Java** es principalmente donde se incluyen los servlets, beans y utils además del sql encargado de crear la base de datos. Por otro lado el **WebApp** sigue siendo el más relacionado a la propia página web en sí y es el que más a cambiado respecto de la versión anterior, teniendo archivos jsp para distintas finalidades, como por ejemplo un "form" para editar el jugador y otro para insertarlo, un header y un footer o el home y la página de error. 

Lo más detacable de esta segunda versión es la migración de nuestros datos a una base de datos real, montada en mySQL lo que nos permite una mejor gestion de los datos, menos manual y más intuitiva y escalable. 

# Java
Dividido en tres carpetas principales (`entities/`, `servlets/` y `utils/`) con sus respectivos archivos y divisiones. Es la carpeta madre de todos los archivos Java del proyecto. La anterior carpeta beans desaparece

## Entities (`entities/`)
Incluye los archivos java que representan a los jugadores y a las posiciones disponibles.

### Jugador (`Jugador.java`)
Se trata del archivo donde se define el objeto `Jugador` y se define el tipo de relación que va a tener dentro de la base de datos, en este caso es "many to one" (o n-1) lo que significa que vario jugadores pueden tener una posición (por ejemplo en un equipo hay varios mediocentros).  
Los atributos, todos strings, son `nombre`, `apellidos`, `dni`, `alias` y `posición`. Además se definen los "getters" y los "setters" de todos sus atrivutos. 

### Posicion (`Posicion.java`)
Se trata del archivo donde se define el objeto `Posiciones` y se definen estas mismas.  
El archivo comienza definiendo el numero máximo de cada posición junto a un "getter" del nombre de la posición como de el número máximo por posición o del número de jugagores actuales.  
Se definen los métodos que añaden y borran jugadores con sus correspondiente gestión de errores (más judadores de la cuenta, posiciones no reconocidas o número negativos).  
Se utiliza una función propia llamada "isMAx()" que checkea si una posición ya se encuentra en su máxima capacidad.

## Servlets (`servlets/`)
Carpeta conteniente de todos los archivos Java en relación a los servlets que nuestra aplicación utiliza.  
Destacar la importancia del _ServletContext_, que el es encargado de mantener la consistencia entre todos los servlets y es el encargado de almacenar datos cuando por ejemplo un jugador se edita entre otras. Este servlet es la interfaz entre todos ellos y el que permite el correcto funcionamiento de los servicios que proveen.

### FrontController.java (`FrontController.java`)
Se trata del controlador de la parte del frontend de nuestra aplicación, pose un servlet llaamdo "WebServlet" en el que se desarrollará toda la funcionalidad del archivo. En este archivo aparece el metodo doGet() que es el encargado de encaminar al usuario a la página o funcionalidad que desea (editar, insertar, home ...etc) y es el que utiliza el requestDispatcher() para ir realizando estas tareas usando JSP. 

Tiene al igual que todos los archivos gestión de errores(con sus correspondientes redirecciones) y la definición y implementación de editar, eliminar e insertar jugadores, teniendo acceso a la base de datos con la capacidad de modificarla de estas mismas maneras. Recordar que nuestro proyecto SQL está proncipalmente definido por dos tablas que son jugador y posición, con una realcion n-1.

## Utils (`utils/`)
Contiene funcionalidades genéricas que pueden ser utilizadas una o varias veces en un proyecto y que es mejor encapsular para la simplicidad y escalado del proyecto.

### ValidadorDNI (`ValidadorDNI.java`)
Archivo encargado de validar un valor que se introduce como DNI comprobando que la letra es correcta y coincide con el número correspondiente o sea un dni que no exista.  
Para esta funcionalidad se utiliza una expresion regular (regex).

## db_create.sql (` db_create.sql`)
Como se mencionó en la introducción se trata de un archivo relacionado a la creación de la base de datos, y como se indica en su cabecera se trata de un archivo generado con MySQL Workbench que es la herramienta con la que estamos gestionando la base de datos 

# WebApp (`webapp/`)
Dividido en los diferentes **JavaServer Pages** y la carpeta de hojas de estilo posible.

## `EditarJugadorForm.jsp`
Archivo JSP que despliega en la página web el formulario necesario para editar a aun jugador, dándo al ususario capacidad de acceso y modificación sobre la base de datos. Esta funcionalidad es tambien útil de cara a gestión de errores, no tanto de código si no puramente humanos, como inputs erroneos o inexactos que con esta funcionalidad pueden ser modificados.

## `InsertarJugadorForm.jsp`
Archivo JSP que despliega en la página web el formulario necesario para añadir a aun jugador, dándo al ususario capacidad de acceso y modificación sobre la base de datos al igual que la funciónalidad editar.

## `Error.jsp`
Se trata del JSP que implementa la página de error con el tipo de error y sus características indicando al usuario lo que ha pasado por medio de un mensaje.

## `Header.jsp`
Es un JSP que representa la cabecera de página de en este caso el Atlético de Madrid pero cuyo objetivo es ser "generalizado" para poder ser reutilizado en distintas páginas (ya sea de otros equipos o de distintas funcionalidades dentro de un mismo equipo).

## `Footer.jsp`
Al igual que el archivo anterior un JSP que representa el footer de página y cuyo objetivo es ser "generalizado" para poder ser reutilizado en distintas páginas.

## `Home.jsp`
Se trata del JSP que representa y dibuja la página principal de bienvenida en la cual son accesibles todas las opciones que ofrece nustra aplicación para que el usuario las localice facilmente. Hace uso de tanto header como footer y llama a editar e insertar jugador.

# Conclusiones
La realización de esta práctica ha supuesto un reto para el equipo y en ciertas ocasiones una ocasión perfecta para aprender a enfrentarnos a problemas reales que pueden aparecer en el desarrollo de una aplicación real de misma naturaleza. Creemos que el hecho de incluir una base de datos real y no un simple string nos ha ayudado a entender correctamente la forma en la que se trabaja en el mundo laboral con herramientas como MySQL workbench o los servidores Payara entre otras muchas cosas.

Cabe destacar que el uso de archivos JDBC y JSP aun aumentando la dificultad de la práctica hace de nuestra aplicación un proyecto más consistente escalable y organizado y nos a proporcianado conocimientos muy útiles para el desarrollo de este tipo de herramientas.

Destacar la dificultad añadida de las coincidencias con otros exámenes y proyectos que ha podido entorpecer el hecho de coincidir el equipo entero de desarrollo que en cambio ha sido capaz de sacar adelante el proyecto dividiendo el trabajo de forma eficiente y estando en constante contacto.
