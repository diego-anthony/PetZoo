package com.petzoo.petzoo.models;

public class Mascota {
    private float IdMascota;
    private String Nombre;
    private String FechaNacimiento;
    private boolean EsMacho;
    private boolean TieneMicroChip;
    private boolean EstaVacunado;
    private boolean EstaEsterilizado;
    private float IdClaseMascota;
    private String IdColor;
    private float IdRaza;
    private float IdEstadoMascota;
    private float IdTamano;
    private float IdPersona;
    private String ClaseMascota = null;
    private String Color = null;
    private String EstadoMascota = null;
    private String Persona = null;
    private String Raza = null;
    private String Tamano = null;

    // Getter Methods

    public float getIdMascota() {
        return IdMascota;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public boolean getEsMacho() {
        return EsMacho;
    }

    public boolean getTieneMicroChip() {
        return TieneMicroChip;
    }

    public boolean getEstaVacunado() {
        return EstaVacunado;
    }

    public boolean getEstaEsterilizado() {
        return EstaEsterilizado;
    }

    public float getIdClaseMascota() {
        return IdClaseMascota;
    }

    public String getIdColor() {
        return IdColor;
    }

    public float getIdRaza() {
        return IdRaza;
    }

    public float getIdEstadoMascota() {
        return IdEstadoMascota;
    }

    public float getIdTamano() {
        return IdTamano;
    }

    public float getIdPersona() {
        return IdPersona;
    }

    public String getClaseMascota() {
        return ClaseMascota;
    }

    public String getColor() {
        return Color;
    }

    public String getEstadoMascota() {
        return EstadoMascota;
    }

    public String getPersona() {
        return Persona;
    }

    public String getRaza() {
        return Raza;
    }

    public String getTamano() {
        return Tamano;
    }

    // Setter Methods

    public void setIdMascota(float IdMascota) {
        this.IdMascota = IdMascota;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setEsMacho(boolean EsMacho) {
        this.EsMacho = EsMacho;
    }

    public void setTieneMicroChip(boolean TieneMicroChip) {
        this.TieneMicroChip = TieneMicroChip;
    }

    public void setEstaVacunado(boolean EstaVacunado) {
        this.EstaVacunado = EstaVacunado;
    }

    public void setEstaEsterilizado(boolean EstaEsterilizado) {
        this.EstaEsterilizado = EstaEsterilizado;
    }

    public void setIdClaseMascota(float IdClaseMascota) {
        this.IdClaseMascota = IdClaseMascota;
    }

    public void setIdColor(String IdColor) {
        this.IdColor = IdColor;
    }

    public void setIdRaza(float IdRaza) {
        this.IdRaza = IdRaza;
    }

    public void setIdEstadoMascota(float IdEstadoMascota) {
        this.IdEstadoMascota = IdEstadoMascota;
    }

    public void setIdTamano(float IdTamano) {
        this.IdTamano = IdTamano;
    }

    public void setIdPersona(float IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setClaseMascota(String ClaseMascota) {
        this.ClaseMascota = ClaseMascota;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public void setEstadoMascota(String EstadoMascota) {
        this.EstadoMascota = EstadoMascota;
    }

    public void setPersona(String Persona) {
        this.Persona = Persona;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public void setTamano(String Tamano) {
        this.Tamano = Tamano;
    }
}
