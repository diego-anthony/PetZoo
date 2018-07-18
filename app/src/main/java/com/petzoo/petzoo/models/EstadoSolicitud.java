package com.petzoo.petzoo.models;

public class EstadoSolicitud {

    private int IdEstadoSolicitud;
    private String Descripcion;


    public EstadoSolicitud(int idEstadoSolicitud, String descripcion)
    {
        setIdEstadoSolicitud(idEstadoSolicitud);
        setDescripcion(descripcion);
    }

    public int getIdEstadoSolicitud() {
        return IdEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(int idEstadoSolicitud) {
        IdEstadoSolicitud = idEstadoSolicitud;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
