package org.videojuego.Controladores;

public class VideoJuego {
    private int idVideoJuego;
    private String nombre;
    private String genero;
    private String plataforma;
    private int pegi;
    private double precio;

    public VideoJuego(int idVideoJuego, String nombre, String genero, String plataforma, int pegi, double precio) {
        this.idVideoJuego = idVideoJuego;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.pegi = pegi;
        this.precio = precio;
    }

    public int getIdVideoJuego() {
        return idVideoJuego;
    }

    public void setIdVideoJuego(int idVideoJuego) {
        this.idVideoJuego = idVideoJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "VideoJuego{" +
                "idVideoJuego='" + idVideoJuego + '\'' +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", pegi=" + pegi +
                ", precio=" + precio +
                '}';
    }
}
