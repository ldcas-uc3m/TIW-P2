# Lab: Name
By Luis Daniel Casais Mezquida  
Subject 2X/2Y  
Bachelor's Degree in Computer Science and Engineering  
Universidad Carlos III de Madrid


## Project statement




## Instalación

El proyecto está desarrollado y pensado para ejecutarse en Eclipse con Payara, recomendamos las versiones específicas.  
Para importar el proyecto:
1. Descarga e instala [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Descarga e instala [Eclipse IDE for Enterprise Java and Web Developers 2023-09](https://www.eclipse.org/downloads/packages/release/2023-09/r/eclipse-ide-enterprise-java-and-web-developers)
3. Descarga y descomprime [Payara-5.2022.3](
https://nexus.payara.fish/#browse/browse:payara-community:fish%2Fpayara%2Fdistributions%2Fpayara%2F5.2022.3%2Fpayara-5.2022.3.zip)
3. Instala [Payara Tools para Eclipse](https://marketplace.eclipse.org/content/payara-tools), preferiblemente desde el Eclipse Marketplace (en Eclipse: `Help` → `Eclipse Marketplace` → `Search` → `Find` y escribir "`Payara Tools`" → `Install`)
4. Crea un servidor de Payara:
    1. Abre la vista `Servers` en `Window` → `Show View` → `Other` → `Server` → `Server`.
    2. Ve a la vista `Servers` (abajo) y haz clic en `No servers are available. Click this link to create new server...` → `Payara` → `Payara`, y selecciona la versión 17 de Java y la dirección de la carpeta de payara (`payara5/`).
5. Download and install [MySQL Community Server 8.1.0](https://dev.mysql.com/downloads/mysql/) and, optionally, [MySQL Workbench](https://dev.mysql.com/downloads/workbench/).
6. Download the [MySQL Java driver](https://dev.mysql.com/downloads/connector/j/) and save the `mysql-connector-j-8.1.0.jar` to `<payara-path>/glassfish/lib`.
7. Start MySQL server (if it's not started already)
    - Linux
        ```bash
        $ sudo systemctl start mysqld
        ```
    - Windows (MySQL path is usually `C:\Program Files\MySQL\MySQL Server 8.X`)
        ```powershell
        C:\> & <mysql-path>\bin\mysqld
        ```
8. Create and import the database
    1. Access the MySQL console
        - Linux
            ```bash
            $ mysql -u root
            ```
        - Windows
            ```powershell
            C:\> & <mysql-path>bin\mysql -u root
            ```
        If you have set a password for root on installation (here we'll assume the password is `root`), add the flag `-proot`.
    2. Create a database `tiwp2`
        ```sql
        mysql> CREATE DATABASE tiwp2;
        ```
    3. If you haven't set a password already, set it up.
        ```sql
        mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
        mysql> FLUSH PRIVILEGES;
        ```
    4. Exit MySQL.
        ```sql
        mysql> exit;
        ```
    5. Use the [`db_create.sql`](LAB2-JPA/src/main/java/db_create.sql) script to create the `tiwp2` table inside of the `tiwp2` database.
        - Linux
            ```bash
            $ mysql -u root -proot tiwp2 < <path-to>/dbCreate.sql
            ```
        - Windows
            ```powershell
            C:\> & <mysql-path>\bin\mysql -u root -proot tiwp2
            ```
            ```sql
            mysql> source <path-to>\dbCreate.sql
            mysql> exit;
            ```
9. Configure Eclipse Database Connection `MySQL8.1.0-tiwp2` to the `tiwp2` MySQL database.
    1. Got to `Data Source Explorer` → Right-Click on `Database Connections` → `New...`
    2. Select `MySQL`, in `Name` put `MySQL8.1.0-tiwp2`
    3. Click the icon for `New driver definition` to the right of `Drivers:` 
    4. Select `MySQL JDBC Driver 5.1`
    5. Go to `JAR List` → select `mysql-connector-java-5.1.0-bin.jar` → click on `Edit JAR/Zip...` and select the MySQL Driver (`mysql-connector-j-8.1.0.jar` in `<payara-path>/glassfish/lib`) → `OK`
    6. Fill up the form:
        - `Database`: `tiwp2`
        - `URL`: `jdbc:mysql://localhost:3306/tiwp2?serverTimezone=UTC`
        - `User name`: `root`
        - `Password`: `root`
        - Check `Save password`
    7. Click on `Test Connection` to check it works.
    8. Click on `Finish`
10. Setup the Connection Pool in Payara (CLI)
    1. Enter the Payara asadmin console
        - Linux
            ```bash
            sudo <payara-path>/bin/asadmin
            ```
        - Windows
            ```powershell
            C:\> & <payara-path>\bin\asadmin
            ```
    2. Launch a server in a domain
        ```
        asadmin> start-domain -d
        ```
    3. Create the JDBC Connection Pool `MySQL8.1.0_jdbc/tiwp2` to the `tiwp2` database.
        ```
        asadmin> create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property 'user=root:password=root:databaseName=tiwp2:serverName=localhost:portNumber=3306:url=jdbc\:mysql\://localhost\:3306/tiwp2:useSSL=false:serverTimezone=UTC:allowPublicKeyRetrieval=true' MySQL8.1.0_jdbc/tiwp2
        ```
        (Note that all `:` that are part of the property value need to be escaped by `\`, and for that to work it must be enclosed in quotes, as demonstrated above. More info [here](https://github.com/payara/Payara/issues/1252#issuecomment-268782829).)
    4. Test the connection pool
        ```
        asadmin> ping-connection-pool MySQL8.1.0_jdbc/tiwp2
        ```
11. Setup the JDBC resource `jdbc/tiwp2` connected to the `MySQL8.1.0_jdbc/tiwp2` connection pool in Payara  
    (Assuming server is started and we're in the asadmin console)  
    ```
    asadmin> create-jdbc-resource --enabled=true --connectionpoolid MySQL8.1.0_jdbc/tiwp2 jdbc/tiwp2
    ```
    ```
    asadmin> exit
    ```


## Ejecución
En Eclipse:
1. Importa el proyecto deseado desde `File` → `Open Projects From File System...` → `Directory` → <projecto deseado, e.g `<path-to>tiwclase1/`> → `Finish`.
<!-- 2. [???] Conviértelo en un projecto JPA:
    1. `Project` → `Properties` → `Project Facets`
    2. Haz tick en la caja de JPA
    3. A la derecha, pestaña `Runtimes`, y selecciona `Payara` -->
3. Asegúrate de tener seleccionado el proyecto y haz clic en `Run` → `Run As` → `Run on Server` y selecciona tu servidor de Payara.
