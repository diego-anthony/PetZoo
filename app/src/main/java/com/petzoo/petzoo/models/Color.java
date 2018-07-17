package com.petzoo.petzoo.models;

public class Color {
    private String IdColor;


    public Color(String idColor)
    {
        setIdColor(idColor);
    }


    public String getIdColor() {
        return IdColor;
    }

    public void setIdColor(String idColor) {
        IdColor = idColor;
    }
}
