# SisteCreditoTestApp Android
# MegaLoginApp Android

Este proyecto de Android es un ejemplo de una aplicacion que consume una API publica de video juegos, muestra un listado de estos mismos, puede visualizar la informacion detallada de cada juego y adicional puede guardar los juegos en el dispositivo de manera local para sus juegos favoritos.

## Características y tecnologías utilizadas:

- **Clean Architecture**: El proyecto sigue los principios SOLID y utiliza Clean Architecture para una estructura de código modular y mantenible, cada feature o modulo se compone principalmente de 3 capas
- Capa de datos: En esta capa se encontraria todo lo relaciona a la fuente de datos, en este caso la conexion con la base de datos local (Room) o la fuente de datos remota consumiendo la api con retrofit
- Capa de dominio: En esta capa alojamos toda la logica de negocio principalmente contenida en los casos de uso.
- Capa de presentacion : En esta capa tenemos todos los elementos visuales que se muestran en la UI, en este caso diseñada con Jetpack Compose. 
- **MVVM**: Se implementa el patrón de arquitectura Modelo-Vista-VistaModelo para una separación clara de responsabilidades y un flujo de datos eficiente.
- **Room**: Se utiliza la biblioteca Room para el almacenamiento local de datos, proporcionando una capa de abstracción para interactuar con la base de datos SQLite.
- **Compose**: La interfaz de usuario se ha desarrollado utilizando Jetpack Compose, el kit de herramientas moderno y declarativo para la creación de interfaces de usuario en Android.
- **Hilt**: Se utiliza Hilt como framework para la inyección de dependencias, facilitando la gestión y la creación de instancias de objetos en la app.
- **MockK**: La biblioteca MockK se utiliza para escribir pruebas unitarias y crear objetos simulados, permitiendo una mejor cobertura de pruebas y garantizando la calidad del código.

## Evidencias: