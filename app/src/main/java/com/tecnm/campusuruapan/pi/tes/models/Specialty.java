package com.tecnm.campusuruapan.pi.tes.models;

public class Specialty {
    private String nombre;
    private int cantTalacheros;
    private int image;

    public Specialty(String nombre, int cantTalacheros, int image) {
        this.nombre = nombre;
        this.cantTalacheros = cantTalacheros;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantTalacheros() {
        return cantTalacheros;
    }

    public void setCantTalacheros(int cantTalacheros) {
        this.cantTalacheros = cantTalacheros;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
