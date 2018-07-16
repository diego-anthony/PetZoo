package com.petzoo.petzoo.models;

public class Alerta {
    private int IdAlerta;
    private String Descripcion;
    private float Latitud;
    private float Longitud;
    private String FechaAlerta;
    private int IdPersona;
    private int IdPersonaRescate;
    private int IdClaseMascota;
    private int IdEstadoAlerta;
    //ArrayList < Object > Foto = new ArrayList < Object > ();
    //private String ClaseMascota = null;
    //private String EstadoAlerta = null;
    //private String Persona = null;
    //private String Persona1 = null;


    // Getter Methods

    public int getIdAlerta() {
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

    public int getIdPersona() {
        return IdPersona;
    }

    public int getIdPersonaRescate() {
        return IdPersonaRescate;
    }

    public int getIdClaseMascota() {
        return IdClaseMascota;
    }

    public int getIdEstadoAlerta() {
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

    public void setIdAlerta(int IdAlerta) {
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

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setIdPersonaRescate(int IdPersonaRescate) {
        this.IdPersonaRescate = IdPersonaRescate;
    }

    public void setIdClaseMascota(int IdClaseMascota) {
        this.IdClaseMascota = IdClaseMascota;
    }

    public void setIdEstadoAlerta(int IdEstadoAlerta) {
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
