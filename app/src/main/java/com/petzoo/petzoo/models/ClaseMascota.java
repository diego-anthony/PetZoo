package com.petzoo.petzoo.models;

public class ClaseMascota {
    private int IdClaseMascota;
    private String Descripcion;


    public ClaseMascota(int idClaseMascota, String descripcion)
    {
        setIdClaseMascota(idClaseMascota);
        setDescripcion(descripcion);
    }


    public int getIdClaseMascota() {
        return IdClaseMascota;
    }

    public void setIdClaseMascota(int idClaseMascota) {
        IdClaseMascota = idClaseMascota;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
