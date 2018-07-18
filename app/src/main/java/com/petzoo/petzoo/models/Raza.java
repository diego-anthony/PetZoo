package com.petzoo.petzoo.models;

public class Raza {

    private int IdRaza;
    private String Descripcion;


    public Raza(int idRaza, String descripcion)
    {
        setIdRaza(idRaza);
        setDescripcion(descripcion);
    }


    public int getIdRaza() {
        return IdRaza;
    }

    public void setIdRaza(int idRaza) {
        IdRaza = idRaza;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
