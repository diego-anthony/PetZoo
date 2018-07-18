package com.petzoo.petzoo.models;

public class Foto {
    private long IdFoto;

    private String Path;

    private String DataImage;

    private long IdMascota;

    private long IdUsuario;

    private long IdAlerta;

    public long getIdFoto() {
        return IdFoto;
    }

    public void setIdFoto(long idFoto) {
        IdFoto = idFoto;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public long getIdMascota() {
        return IdMascota;
    }

    public void setIdMascota(long idMascota) {
        IdMascota = idMascota;
    }

    public long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        IdUsuario = idUsuario;
    }

    public long getIdAlerta() {
        return IdAlerta;
    }

    public void setIdAlerta(long idAlerta) {
        IdAlerta = idAlerta;
    }

    public String getDataImage() {
        return DataImage;
    }

    public void setDataImage(String dataImage) {
        DataImage = dataImage;
    }
}
