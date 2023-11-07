package org.videojuego.Controladores;

public class Observaciones {
    private int idObservacion;
    private int idVideoJuego;
    private double duracion;
    private double puntuacion;
    private int vecesJugado;
    private int idUsuario;

    public Observaciones(int idObservacion, int idVideoJuego, double duracion, double puntuacion, int vecesJugado, int idUsuario) {
        this.idObservacion = idObservacion;
        this.idVideoJuego = idVideoJuego;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
        this.vecesJugado = vecesJugado;
        this.idUsuario = idUsuario;
    }

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public int getIdVideoJuego() {
        return idVideoJuego;
    }

    public void setIdVideoJuego(int idVideoJuego) {
        this.idVideoJuego = idVideoJuego;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getVecesJugado() {
        return vecesJugado;
    }

    public void setVecesJugado(int vecesJugado) {
        this.vecesJugado = vecesJugado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Observaciones{" +
                "idObservacion=" + idObservacion +
                ", idVideoJuego=" + idVideoJuego +
                ", duracion=" + duracion +
                ", puntuacion=" + puntuacion +
                ", vecesJugado=" + vecesJugado +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
