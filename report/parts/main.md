# Introducción

La aplicación web realizada está basada en la posibilidad de agregar, editar y eliminar jugadores que podrían formar parte de la plantillas de los clubes más prestigiosos de España (eg: Atlético de Madrid, _er_ Beti, Rayo Vallecano, F.C. Barcelona... etc). Esta vez la gestión de la plantilla de jugadores queda almacenada en bases de datos basadas en el uso tanto de JPA como de JDBC.

La estructura principal que sigue el proyecto (carpeta `src/main/`) se divide en dos, una sección relacionada a la programación en **Java** (`java/`) y otra a cargo de las **WebApp** (`webapp/`). Respecto a la parte de **Java** es principalmente donde se incluyen los servlets, entities, utils y el archivo .sql que crea la base de datos con su formato de tablas. Por otro lado el **WebApp** es donde aparecen los archivos JavaServer Pages (los que permiten dar el formato HTML a nuestra web) junto a una hoja de estilos (`styles/style.css`) para poder editar fuentes y todo lo relacionado con la apariencia de la aplicación web en posibles actualizaciones.  

# Java
Dividido en tres carpetas principales (`entities/`, `servlets/` , `utils/` y `db_create.sql`) con sus respectivos archivos y divisiones. Es la carpeta madre de todos los archivos Java del proyecto.

## Entities (`entities/`)
Incluye los objetos que serán usados.

### Jugador (`Jugador.java`)

### Posiciones (`Posiciones.java`)

### PosicionesJDBC (`PosicionesJDBC.java`)

### JugadorJDBC (`JugadorJDBC.java`)

## Servlets (`servlets/`)
Carpeta conteniente de todos los archivos Java en relación a los servlets que nuestra aplicación utiliza.  
Destacar la importancia del _ServletContext_, que el es encargado de mantener la consistencia entre todos los servlets y es el encargado de almacenar datos cuando por ejemplo un jugador se edita entre otras. Este servlet es la interfaz entre todos ellos y el que permite el correcto funcionamiento de los servicios que proveen. En este caso los servlets principales son el JPAServlet y el JDBCServlet, los cuales actúan como FrontControllers propios de ellos mismos, cada uno de ellos encargado de almacenar los datos introducidos en cada base de datos que corresponda (tanto con JSP como con JDBC)

### JPAServlet (`JPAServlet.java`)


### JDBCServlet (`JDBCServlet.java`)


## Utils (`utils/`)
Contiene funcionalidades genéricas que pueden ser utilizadas una o varias veces en un proyecto y que es mejor encapsular para la simplicidad y escalado del proyecto

### ValidadorDNI (`ValidadorDNI.java`)
Archivo encargado de validar un valor que se introduce como DNI comprobando que la letra es correcta y coincide con el número correspondiente o sea un dni que no exista.  
Para esta funcionalidad se utiliza una expresion regular (regex).

## db_creator (`db_creator.sql`)
Archivo encargado de crear las tablas de la base de datos con todas sus especificaciones y requisitos 

# WebApp (`webapp/`)
Dividido en los diferentes **JavaServer Pages** y la carpeta de hojas de estilo posible.

## `Home.jsp`
La página principal donde el usuario accede a la aplicación web.  
En ella ya se presenta la posibilidad de añadir jugadores a la plantilla de fútbol en cuestión. Además, en ella misma será donde aparecerán las filas implementadas en las tablas de la base de datos de sql con cada uno de los jugadores y los datos pertinentes de cada uno de ellos según se vayan añadiendo al equipo, acompañado de una lista visible de los jugadores que quedan por agregar de esa plantilla con los totales permitidos por cada posición.  
Junto a estos datos de cada jugador, aparecerá el botón para poder editar los datos de cada uno de ellos a gusto del usuario a la vez que poder eliminarlos de la base de datos.  
Todo el resto de las páginas redirigen a esta al ser el punto de partida de la funcionalidad principal, siendo esta la de añadir nuevos jugadores.

## `InsertarJugadorForm.jsp`
La página desde la cual se pueden agregar los nuevos jugadores a la plantilla. Sigue una estructura basada en los datos que hay que incluir de cada uno de los jugadores para su correcto almacenamiento en formato fila dentro de la tabla de jugadores de sql, presentando los campos nombre, apellidos, DNI (con formato modificado), alias y posición, donde exclusivamente se pueden seleccionar delantero, defensa, medio y portero.  

## `EditarJugadorForm.jsp`
La página que permite al usuario modificar al jugador que se haya seleccionado desde cada una de las filas insertadas de la página  'home'. Presenta la misma estructura que el formulario para añadir a los jugadores con cada uno de sus subapartados y el botón para poder guardar los cambios realizados. Ésta es una de las funcionalidades que no son estrictamente necesarias o solicitadas en el enunciado de la práctica, pero para futuros trabajos y actualizaciones sobre esta aplicación web se entiende que será necesaria y de ahí su implementación extraordinaria.  
Además, existe la opción de cancelar la operación y así volver a la página principal sin haber realizado ninguna modificación en los jugadores existentes en la base de datos.


## `Error.jsp`
Esta página, como su propio nombre indica, es la destinada a mostrar errores y permitir lanzar los **mensajes de error** y **excepciones pertinentes**. Además, se incluye un botón que permite volver a la página principal para poder seguir navegando si es que ya estabas dentro de la propia aplicación cuando el error dió lugar.

## `Header.jsp y Footer.jsp`
Estas páginas, como su propio nombre indican, conforman el cabecero y el pie de página de cada una de las páginas de las que se compone la página web, incluyendo títulos e imágenes 


# Conclusiones
La realización de esta segunda parte de la primera práctica ha supuesto más problemas que la primera parte que la compone. La mayor dificultad encontrada puede haber sido el ser consciente de las conexiones entre los formularios para insertar y editar nuevos jugadores junto con los Front Controllers y tener control de las consultas sql con las que se trabajaban.

Ha sido cuanto menos interesante la utilización primeriza de Eclipse y Payara para establecer las bases en las que el proyecto se desarrolla (servidores, HTML, CSS...) ya que nos da un enfoque más profesional de la forma de trabajar del mercado laboral a pesar de todas las dificultades y herramientas algo rudimentarias o no optimizadas que presenta este entorno de desarrollo. A todo esto se suma el repaso respecto al manejo de tablas y consultas de sql visto en proyectos o trabajos anteriores y que abarca un gran campo dentro de nuestros posibles futuros laborales.

Como conclusiones finales, cabe resaltar el interés que tenemos sobre como se pueden unir este tipo de recursos y herramientas con el resto que se han comentado en las clases para, de esta manera, acabar con un trabajo de mucha mayor escala y aplicable a problemas reales con bases de datos a gran escala.

