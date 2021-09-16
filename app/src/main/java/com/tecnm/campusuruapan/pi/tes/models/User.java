package com.tecnm.campusuruapan.pi.tes.models;

public class User {
    private String id;
    private String tipo_user;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String ubicacion;
    private String email;
    private String password;
    private String especialidad;
    private boolean activo;

    public User(){}

    public User(String id, String tipo_user, String nombre, String apellidos, String telefono, String ubicacion, String email, String password, boolean activo, String especialidad) {
        this.id = id;
        this.tipo_user = tipo_user;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.especialidad = especialidad;
    }

    public User(String id, String tipo_user, String nombre, String apellidos, String telefono, String ubicacion, String email, String password, boolean activo) {
        this.id = id;
        this.tipo_user = tipo_user;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.email = email;
        this.password = password;
        this.activo = activo;
    }

    public String getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(String tipo_user) {
        this.tipo_user = tipo_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
