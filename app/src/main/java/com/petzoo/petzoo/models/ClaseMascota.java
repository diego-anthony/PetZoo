package com.petzoo.petzoo.models;

public class ClaseMascota {
    private int IdClaseMascota;
    private String Descripcion;


    // Getter Methods

    public int getIdClaseMascota() {
        return IdClaseMascota;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    // Setter Methods

    public void setIdClaseMascota(int IdClaseMascota) {
        this.IdClaseMascota = IdClaseMascota;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
