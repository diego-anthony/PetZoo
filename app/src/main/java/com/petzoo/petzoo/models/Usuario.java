package com.petzoo.petzoo.models;

public class Usuario {
    private int IdUsuario;
    private String Username;
    private String Password;
    private int IdPersona;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Celular;
    private String Telefono = null;
    private String Direccion = null;
    private String FechaNacimiento = null;
    private String CorreoElectronico;
    private String Web = null;


    // Getter Methods

    public int getIdUsuario() {
        return IdUsuario;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public String getCelular() {
        return Celular;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public String getWeb() {
        return Web;
    }

    // Setter Methods

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public void setWeb(String Web) {
        this.Web = Web;
    }
}