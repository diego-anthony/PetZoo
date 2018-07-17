package com.petzoo.petzoo.localdata;

import com.petzoo.petzoo.models.ClaseMascota;
import com.petzoo.petzoo.models.Color;
import com.petzoo.petzoo.models.Comportamiento;
import com.petzoo.petzoo.models.EstadoAlerta;
import com.petzoo.petzoo.models.EstadoMascota;
import com.petzoo.petzoo.models.EstadoSolicitud;
import com.petzoo.petzoo.models.Raza;
import com.petzoo.petzoo.models.Tamano;

public class DataLocal {


    public ClaseMascota[] getArrayClaseMascota()
    {
        return new ClaseMascota[]
                {
                        new ClaseMascota(1,"Perro"),
                        new ClaseMascota(2,"Gato"),
                        new ClaseMascota(4,"Cerdo"),
                        new ClaseMascota(5,"Caballo"),
                        new ClaseMascota(6,"Vaca"),
                        new ClaseMascota(7,"Mono"),
                        new ClaseMascota(8,"Loro"),
                        new ClaseMascota(9,"Pato"),
                        new ClaseMascota(10,"Gallo"),
                        new ClaseMascota(11,"Serpiente"),
                        new ClaseMascota(12,"Cuy"),
                        new ClaseMascota(13,"Otro"),
                };
    }

    public Color[] getArrayColor()
    {
        return new Color[]
                {
                        new Color("FFFFFF"),
                        new Color("000000"),
                        new Color("B45F04")
                };
    }


    public Comportamiento[] getArrayComportamiento()
    {
        return new Comportamiento[]
                {
                        new Comportamiento(1,"Bueno con otros animales"),
                        new Comportamiento(2,"Amable con los niños"),
                        new Comportamiento(3,"Cariñoso"),
                        new Comportamiento(4,"No ladra"),
                        new Comportamiento(5,"Le gusta salir a pasear")
                };
    }

    public EstadoAlerta[] getArrayEstadoAlerta()
    {
        return new EstadoAlerta[]
                {
                        new EstadoAlerta(1,"Pendiente"),
                        new EstadoAlerta(2,"Rescatado"),
                        new EstadoAlerta(3,"No contestado"),
                };
    }

    public EstadoMascota[] getArrayEstadoMascota()
    {
        return new EstadoMascota[]
                {
                        new EstadoMascota(1,"En adopación"),
                        new EstadoMascota(2,"Urgente"),
                        new EstadoMascota(3,"Adoptado"),
                        new EstadoMascota(4,"Refugiado parcial")
                };
    }

    public EstadoSolicitud[] getArrayEstadoSolicitud()
    {
        return new EstadoSolicitud[]
                {
                        new EstadoSolicitud(1,"Pendiente"),
                        new EstadoSolicitud(2,"Denegado"),
                        new EstadoSolicitud(3,"Aceptado")
                };
    }


    public Raza[] getArrayRaza()
    {
        return new Raza[]
                {
                        new Raza(1,"Pastor alemán"),
                        new Raza(2,"Labrador"),
                        new Raza(3,"Bulldog"),
                        new Raza(4,"Caniche"),
                        new Raza(5,"Golden retriever"),
                        new Raza(6,"Chihuahua"),
                        new Raza(7,"Pug"),
                        new Raza(8,"Husky"),
                        new Raza(9,"Raza única"),
                        new Raza(10,"Otro"),

                };
    }

    public Tamano[] getArrayTamano()
    {
        return new Tamano[]
                {
                        new Tamano(1,"Muy pequeño"),
                        new Tamano(2,"Pequeño"),
                        new Tamano(3,"Mediano"),
                        new Tamano(4,"Grande"),
                        new Tamano(5,"Muy grande"),
                };
    }

}
