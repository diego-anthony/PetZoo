package com.petzoo.petzoo.models;

public class Comportamiento {
    private int IdComportamiento;
    private String Descripcion;


    public Comportamiento(int idComportamiento, String descripcion)
    {
        setIdComportamiento(idComportamiento);
        setDescripcion(descripcion);
    }

    public int getIdComportamiento() {
        return IdComportamiento;
    }

    public void setIdComportamiento(int idComportamiento) {
        IdComportamiento = idComportamiento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
