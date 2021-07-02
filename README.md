# ProyectoFinalGlobant_App
ProyectoFinalGlobant_App

#Api Rest Library -SpringBoot

## Introduction

Es una api rest diseñada para gestionar libros, usuarios y reservas.

## Code Samples

Hemos diseñado el proyecto en cuatro capas, Model, Service, Controller y Respository.
Las capas Service y Controller fueron testeadas con Unit Test.

## Installation

En primer lugar, crear una base de datos MySQL llamada library.
User: root
Password: 123

Antes de hacer  correr la api, en el archivo application.properties 
cambiar spring.jpa.hibernate.ddl-auto=update a spring.jpa.hibernate.ddl-auto=create

Cuando las tablas se hayan generado automáticamente en la base de datos, ingresar al archivo mencionado previamente, y poner nuevamente esta sentencia spring.jpa.hibernate.ddl-auto=update

Ahora correr la aplicación nuevamente y funcionará sin problemas.

