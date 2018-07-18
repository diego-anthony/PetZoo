package com.petzoo.petzoo.models;

public class EstadoAlerta {

    private int IdEstadoAlerta;
    private String Descripcion;


    public EstadoAlerta(int idEstadoAlerta, String descripcion)
    {
        setIdEstadoAlerta(idEstadoAlerta);
        setDescripcion(descripcion);
    }

    public int getIdEstadoAlerta() {
        return IdEstadoAlerta;
    }

    public void setIdEstadoAlerta(int idEstadoAlerta) {
        IdEstadoAlerta = idEstadoAlerta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
