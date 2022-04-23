package com.tecnm.campusuruapan.pi.tes.models;

import java.io.Serializable;

public class Talachero implements Serializable {
    private String nombre;
    private String direccion;
    private int calificacion;
    private String image;
    private String especialidad;

    public Talachero(String nombre, String direccion, int calificacion, String image, String especialidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.image = image;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
