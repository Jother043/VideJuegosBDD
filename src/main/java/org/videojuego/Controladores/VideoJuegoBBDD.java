package org.videojuego.Controladores;

import org.videojuego.Connect.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoJuegoBBDD {

    public Connection getConnection() throws SQLException {
        DataBaseConnection connection;
        connection = DataBaseConnection.getInstance();
        return connection.getConexion();
    }

    public void insertarJuego(VideoJuego juego) throws SQLException {

        Connection con = getConnection();

        try (PreparedStatement insertarJuego = con.prepareStatement("INSERT INTO videojuego VALUES (?,?,?,?,?,?)")) {
            insertarJuego.setInt(1, juego.getIdVideoJuego());
            insertarJuego.setString(2, juego.getNombre());
            insertarJuego.setString(3, juego.getGenero());
            insertarJuego.setString(4, juego.getPlataforma());
            insertarJuego.setInt(5, juego.getPegi());
            insertarJuego.setDouble(6, juego.getPrecio());
            insertarJuego.executeUpdate();
        }
        System.out.println("VideoJuego insertado correctamente...");
    }

    public void insertarObservaciones(Observaciones observaciones) throws SQLException {
        Connection con = getConnection();
        try (PreparedStatement insertarObservaciones = con.prepareStatement("INSERT INTO observaciones VALUE (?,?,?,?,?,?)")) {
            insertarObservaciones.setInt(1, observaciones.getIdObservacion());
            insertarObservaciones.setInt(2, observaciones.getIdVideoJuego());
            insertarObservaciones.setDouble(3, observaciones.getDuracion());
            insertarObservaciones.setDouble(4, observaciones.getPuntuacion());
            insertarObservaciones.setInt(5, observaciones.getVecesJugado());
            insertarObservaciones.setInt(6, observaciones.getIdUsuario());
            insertarObservaciones.executeUpdate();
        }
        System.out.println("Observaciones introducida correctamente...");
    }

    public void insertarUsuarios(Usuario usuario) throws SQLException {
        Connection con = getConnection();
        try (PreparedStatement insertarObservaciones = con.prepareStatement("INSERT INTO usuarios VALUE (?,?,?)")) {
            insertarObservaciones.setInt(1, usuario.getId());
            insertarObservaciones.setString(2, usuario.getNombre());
            insertarObservaciones.setString(3, usuario.getApellido());
            insertarObservaciones.executeUpdate();
        }
        System.out.println("Usuario introducida correctamente...");
    }

    public String seleccionarObservacionesUsuarios(String userName) throws SQLException {

        Connection con = getConnection();
        int idUsuario;
        StringBuilder sb = new StringBuilder();
        String id = null;
        String idVideoJuego = null;
        String duracion = null;
        String puntuacion = null;
        String vecesJugado = null;
        String idUsuario1 = null;
        String nombreJuego = null;

        try (PreparedStatement userSelect = con.prepareStatement("SELECT idUsuarios FROM usuarios WHERE nombre = ?")) {
            PreparedStatement observacionSelect = con.prepareStatement("SELECT * FROM observaciones WHERE idUsuario = ?");
            PreparedStatement juegoSelect = con.prepareStatement("SELECT nombre FROM videojuego WHERE idVideoJuego = ?");
            userSelect.setString(1, userName);
            ResultSet rs = userSelect.executeQuery();
            if(!rs.next()) {
                System.err.println("Error al obtener el usuario, no existe.");
            }else {
                idUsuario = rs.getInt(1);
                sb.append("Usuario: " + userName + ", ");
                observacionSelect.setInt(1, idUsuario);
                ResultSet rs2 = observacionSelect.executeQuery();
                if (rs2.next()) {
                    id = rs2.getString(1);
                    sb.append("ID: " + id + ", ");
                    idVideoJuego = rs2.getString(2);
                    sb.append("ID videoJuego: " + idVideoJuego + ", ");
                    duracion = rs2.getString(3);
                    sb.append("Duración: " + duracion + ", ");
                    puntuacion = rs2.getString(4);
                    sb.append("Puntuación: " + puntuacion + ", ");
                    vecesJugado = rs2.getString(5);
                    sb.append("Veces Jugado: " + vecesJugado + ", ");
                    idUsuario1 = rs2.getString(6);
                    sb.append("ID usuario: " + idUsuario1 + ", ");
                    juegoSelect.setString(1, idVideoJuego);
                    ResultSet rs3 = juegoSelect.executeQuery();
                    nombreJuego = rs3.getString(1);
                    sb.append("Nombre juego: " + nombreJuego + " ");
                }
            }
        }
        return sb.toString();
    }
}
