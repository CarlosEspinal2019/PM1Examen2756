package com.example.pm1examen2756.configuraciones;

public class TransaccionesContactos {

    public static final String NameDatabase = "PM1EXAMEN1";


    public static final String tablacontactos = "contactos";


    public static final String id = "id";
    public static final String pais = "pais";
    public static final String nombres = "nombres";
    public static final String telefono = "telefono";
    public static final String nota = "nota";


    public static final String CreateTableContactos = "CREATE TABLE " + tablacontactos +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pais TEXT, nombres TEXT, telefono INTEGER, nota TEXT)";

    public static final String DropTableContactos = "DROP TABLE IF EXISTS " + tablacontactos;
}