package com.example.pm1examen2756.tablas;


import java.io.Serializable;

public class Contactos implements Serializable {
    private Integer id;
    private String paises;
    private String nombres;
    private Integer telefono;
    private String nota;

    public Contactos() {

    }

    public Contactos(Integer id, String paises, String nombres, Integer telefono, String nota) {
        this.id = id;
        this.paises = paises;
        this.nombres = nombres;
        this.telefono = telefono;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaises() {
        return paises;
    }

    public void setPaises(String paises) {
        this.paises = paises;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
