# Ejercicio L DEIN

Este proyecto es una aplicación de gestión de aeropuertos y aviones desarrollada en Java utilizando JavaFX para la interfaz gráfica de usuario y MySQL como sistema de gestión de bases de datos. La aplicación permite a los usuarios gestionar aeropuertos y aviones, así como realizar operaciones de activación y desactivación.

## Estructura del Proyecto

El proyecto se organiza en diferentes paquetes, cada uno con su propia funcionalidad:

- **application**: Contiene las clases principales para las distintas funcionalidades de la aplicación.
    - `L_ActivarDesactivarAvion.java`
    - `L_AddAeropuerto.java`
    - `L_AddAvion.java`
    - `L_BorrarAvion.java`
    - `L_EditarAeropuerto.java`
    - `L_ListadoAeropuertos.java`
    - `L_Login.java`

- **bbdd**: Contiene la clase de conexión a la base de datos.
    - `ConexionBD.java`

- **controllers**: Controladores para manejar la lógica de la interfaz de usuario.
    - `L_ActivarDesactivarAvionController.java`
    - `L_AddAeropuertoController.java`
    - `L_AddAvionController.java`
    - `L_BorrarAvionController.java`
    - `L_EditAeropuertoController.java`
    - `L_ListadoAeropuertosController.java`
    - `L_LoginController.java`

- **dao**: Objetos de acceso a datos, responsables de las operaciones CRUD con la base de datos.
    - `AeropuertosDao.java`
    - `AeropuertosPrivadosDao.java`
    - `AeropuertosPublicosDao.java`
    - `AvionesDao.java`
    - `DireccionesDao.java`
    - `UsuariosDAO.java`

- **model**: Modelos que representan las entidades de la aplicación.
    - `Aeropuertos.java`
    - `AeropuertosPrivados.java`
    - `AeropuertosPublicos.java`
    - `Aviones.java`
    - `Direcciones.java`
    - `InformacionAeropuertosPrivados.java`
    - `InformacionAeropuertosPublicos.java`
    - `Usuarios.java`

- **util**: Utilidades generales para el proyecto.
    - `Propiedades.java`

- **fxml**: Archivos FXML para definir las interfaces de usuario.
    - `L_ActivarDesactivarAvion.fxml`
    - `L_AddAeropuerto.fxml`
    - `L_AddAvion.fxml`
    - `L_BorrarAvion.fxml`
    - `L_EditarAeropuerto.fxml`
    - `L_ListadoAeropuertos.fxml`
    - `L_Login.fxml`

- **images**: Imágenes utilizadas en la aplicación.
    - `avion.png`
    - `plane.png`

- **configuration.properties**: Archivo de configuración para definir propiedades de la aplicación.

- **module-info.java**: Archivo de configuración del módulo Java.

## Requisitos

- JDK 22 o superior.
- MySQL Server.
- Maven para la gestión de dependencias y construcción del proyecto.

## Uso

Una vez que la aplicación esté en funcionamiento, podrás iniciar sesión y gestionar aeropuertos y aviones utilizando las distintas funcionalidades disponibles en la interfaz gráfica.

