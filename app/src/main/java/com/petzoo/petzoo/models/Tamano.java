package com.petzoo.petzoo.models;

public class Tamano {

    private int IdTamano;
    private String Descripcion;


    public Tamano(int idTamano, String descripcion)
    {
        setIdTamano(idTamano);
        setDescripcion(descripcion);
    }


    public int getIdTamano() {
        return IdTamano;
    }

    public void setIdTamano(int idTamano) {
        IdTamano = idTamano;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
