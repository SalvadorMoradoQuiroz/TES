package com.tecnm.campusuruapan.pi.tes.models;

public class Contrato {
    private int imageCliente;
    private String nombreCliente;
    private String descrServicio; //Descripción de servicio
    private String fecha;
    private String costo;

    public Contrato(int imageCliente, String nombreCliente, String descrServicio, String fecha, String costo) {
        this.imageCliente = imageCliente;
        this.nombreCliente = nombreCliente;
        this.descrServicio = descrServicio;
        this.fecha = fecha;
        this.costo = costo;
    }

    public int getImageCliente() {
        return imageCliente;
    }

    public void setImageCliente(int imageCliente) {
        this.imageCliente = imageCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDescrServicio() {
        return descrServicio;
    }

    public void setDescrServicio(String descrServicio) {
        this.descrServicio = descrServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
}
