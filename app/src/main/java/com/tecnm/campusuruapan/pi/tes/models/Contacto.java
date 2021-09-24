package com.tecnm.campusuruapan.pi.tes.models;

public class Contacto {
    private String nombre;
    String image_uri;

    public Contacto(String nombre, String image_uri) {
        this.nombre = nombre;
        this.image_uri = image_uri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
