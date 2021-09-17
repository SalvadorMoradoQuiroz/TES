package com.tecnm.campusuruapan.pi.tes.models;

public class Talachero {
    private String nombre;
    private String direccion;
    private int calificacion;
    private int image;

    public Talachero(String nombre, String direccion, int calificacion, int image) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
