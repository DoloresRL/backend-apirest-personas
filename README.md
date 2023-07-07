# backend-apirest-personas
Backend CRUD API para gestionar la entidad de 'Persona'

##DEscripción

Este proyecto backend está diseñado para proporcionar funcionalidad CRUD para la entidad de 'Persona'. Permite crear, leer, actualizar y eliminar registros de personas en una base de datos.

##Características principales:

Implementa las operaciones básicas CRUD para la entidad de 'Persona'.
Utiliza tecnologías java (Openjdk v 11.0.18), SpringBoot y maven (para la gestión de dependencias).

Para la conexión a la base de datos se hace la conexión a un host que contiene la misma y el propio programa ejecuta un script para la inserción de datos iniciales de prueba:


Host:db4free.net
BD:bd_personas
username:dolores_reyes
password:S!dWxczdL4$fK6z
Ubicación Script:/backend-apirest-personas/src/main/resources/import.sql

##Instalación
Sigue estos pasos para instalar y configurar el proyecto en tu entorno de desarrollo:

Clona el repositorio del proyecto desde GitHub.
Abre tu IDE preferido, y asegúrate de contar con la extensión Spring Boot Tools.
Importa el proyecto.
Espera a que se descarguen todas las dependencias del proyecto.
Ejecuta el proyecto (Run As Spring Boot App)
El proyecto se ejecutará en el servidor de aplicaciones embebido de Spring Boot.
Si todo se ha configurado correctamente, podrás acceder al proyecto a través de la URL proporcionada por el servidor de aplicaciones embebido (por ejemplo, http://localhost:8080).

Nota: Asegúrate de tener instalado Java Development Kit (JDK) en tu sistema antes de importar y ejecutar el proyecto.