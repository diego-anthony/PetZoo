package com.petzoo.petzoo.models;

public class Alerta {
    private float IdAlerta;
    private String Descripcion;
    private float Latitud;
    private float Longitud;
    private String FechaAlerta;
    private float IdPersona;
    private float IdPersonaRescate;
    private int IdClaseMascota;
    private float IdEstadoAlerta;
    //ArrayList < Object > Foto = new ArrayList < Object > ();
    //private String ClaseMascota = null;
    //private String EstadoAlerta = null;
    //private String Persona = null;
    //private String Persona1 = null;


    // Getter Methods

    public float getIdAlerta() {
        return IdAlerta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public float getLatitud() {
        return Latitud;
    }

    public float getLongitud() {
        return Longitud;
    }

    public String getFechaAlerta() {
        return FechaAlerta;
    }

    public float getIdPersona() {
        return IdPersona;
    }

    public float getIdPersonaRescate() {
        return IdPersonaRescate;
    }

    public int getIdClaseMascota() {
        return IdClaseMascota;
    }

    public float getIdEstadoAlerta() {
        return IdEstadoAlerta;
    }
/*
    public String getClaseMascota() {
        return ClaseMascota;
    }

    public String getEstadoAlerta() {
        return EstadoAlerta;
    }

    public String getPersona() {
        return Persona;
    }

    public String getPersona1() {
        return Persona1;
    }
*/
    // Setter Methods

    public void setIdAlerta(float IdAlerta) {
        this.IdAlerta = IdAlerta;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setLatitud(float Latitud) {
        this.Latitud = Latitud;
    }

    public void setLongitud(float Longitud) {
        this.Longitud = Longitud;
    }

    public void setFechaAlerta(String FechaAlerta) {
        this.FechaAlerta = FechaAlerta;
    }

    public void setIdPersona(float IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setIdPersonaRescate(float IdPersonaRescate) {
        this.IdPersonaRescate = IdPersonaRescate;
    }

    public void setIdClaseMascota(int IdClaseMascota) {
        this.IdClaseMascota = IdClaseMascota;
    }

    public void setIdEstadoAlerta(float IdEstadoAlerta) {
        this.IdEstadoAlerta = IdEstadoAlerta;
    }
/*
    public void setClaseMascota(String ClaseMascota) {
        this.ClaseMascota = ClaseMascota;
    }

    public void setEstadoAlerta(String EstadoAlerta) {
        this.EstadoAlerta = EstadoAlerta;
    }

    public void setPersona(String Persona) {
        this.Persona = Persona;
    }

    public void setPersona1(String Persona1) {
        this.Persona1 = Persona1;
    }*/
}
