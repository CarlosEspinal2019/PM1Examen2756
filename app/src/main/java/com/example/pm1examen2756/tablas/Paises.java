package com.example.pm1examen2756.tablas;

public class Paises {
    private Integer idpais;
    private String nombrepais;
    private String codigopais;

    public Paises() {

    }

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombrepais() {
        return nombrepais;
    }

    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }

    public String getCodigopais() {
        return codigopais;
    }

    public void setCodigopais(String codigopais) {
        this.codigopais = codigopais;
    }

    public Paises(Integer id, String paises, String codigo) {
        this.idpais = idpais;
        this.nombrepais = nombrepais;
        this.codigopais = codigopais;
    }


}
