package com.petzoo.petzoo.models;

public class EstadoMascota {
    private int IdEstadoMascota;
    private String Descripcion;


    public EstadoMascota(int idEstadoMascota, String descripcion)
    {
        setIdEstadoMascota(idEstadoMascota);
        setDescripcion(descripcion);
    }


    public int getIdEstadoMascota() {
        return IdEstadoMascota;
    }

    public void setIdEstadoMascota(int idEstadoMascota) {
        IdEstadoMascota = idEstadoMascota;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
