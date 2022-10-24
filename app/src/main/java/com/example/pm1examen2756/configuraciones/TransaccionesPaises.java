package com.example.pm1examen2756.configuraciones;

public class TransaccionesPaises {

    public static final String NameDatabase = "PM1EXAMEN1";


    public static final String tablapaises = "paises";


    public static final String idpais = "idpais";
    public static final String nombrepais = "nombrepais";
    public static final String codigopais = "codigopais";


    public static final String CreateTablePaises = "CREATE TABLE " + tablapaises +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombrepais TEXT, codigopais TEXT)";

    public static final String DropTablePaises = "DROP TABLE IF EXISTS " + tablapaises;
}
