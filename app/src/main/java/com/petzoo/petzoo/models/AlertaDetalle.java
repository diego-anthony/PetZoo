package com.petzoo.petzoo.models;

    public class AlertaDetalle {
        private float IdAlerta;
        private String Descripcion;
        private float Latitud;
        private float Longitud;
        private String FechaAlerta;
        private String EstadoAlerta;
        private String ClaseMascota;
        private String NombrePersona;


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

        public String getEstadoAlerta() {
            return EstadoAlerta;
        }

        public String getClaseMascota() {
            return ClaseMascota;
        }

        public String getNombrePersona() {
            return NombrePersona;
        }

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

        public void setEstadoAlerta(String EstadoAlerta) {
            this.EstadoAlerta = EstadoAlerta;
        }

        public void setClaseMascota(String ClaseMascota) {
            this.ClaseMascota = ClaseMascota;
        }

        public void setNombrePersona(String NombrePersona) {
            this.NombrePersona = NombrePersona;
        }
    }

