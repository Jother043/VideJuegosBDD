package org.videojuego.Controladores;

import org.videojuego.Connect.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class VideoJuegoBBDD {

    public static Connection getConnection() throws SQLException {
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

    /**
     * Método que devuelve un string con todas las observaciones de un usuario en concreto, pasandole el idUsuario.
     * tambien necesitamos el nombre del videojuego, por lo que necesitamos hacer un join con la tabla videojuego.
     * y el nombre del usuario, por lo que necesitamos hacer un join con la tabla usuarios.
     * @param idUsuario
     * @return
     * @throws SQLException
     */
    public String listarObservacionesUsuario(int idUsuario ) throws SQLException {

        Connection con = getConnection();
        StringBuilder sb = new StringBuilder();
        try (PreparedStatement listarObservaciones = con.prepareStatement("SELECT * FROM observaciones INNER JOIN videojuego ON observaciones.idVideoJuego = videojuego.idVideoJuego INNER JOIN usuarios ON observaciones.idUsuario = usuarios.idUsuarios WHERE idUsuario = ?")) {
            listarObservaciones.setInt(1, idUsuario);
            ResultSet rs = listarObservaciones.executeQuery();
            while (rs.next()) {
                sb.append("ID Observacion: ").append(rs.getInt(1)).append("\n");
                sb.append("ID VideoJuego: ").append(rs.getInt("idVideojuego")).append("\n");
                sb.append("Nombre VideoJuego: ").append(rs.getString("nombre")).append("\n");
                sb.append("Duracion: ").append(rs.getDouble(3)).append("\n");
                sb.append("Puntuacion: ").append(rs.getDouble(4)).append("\n");
                sb.append("Veces Jugado: ").append(rs.getInt(5)).append("\n");
                sb.append("ID Usuario: ").append(rs.getInt("idusuarios")).append("\n");
                sb.append("Nombre Usuario: ").append(rs.getString("nombreUsuario")).append("\n");
                sb.append("Apellido Usuario: ").append(rs.getString("apellidos")).append("\n");
                sb.append("--------------------------------------------------").append("\n");
            }
        }
        return sb.toString();
    }

    public static String listarJuegos() throws SQLException {

        Connection con = getConnection();
        StringBuilder sb = new StringBuilder();
        try (PreparedStatement listarJuegos = con.prepareStatement("SELECT * FROM videojuego")) {
            ResultSet rs = listarJuegos.executeQuery();
            while (rs.next()) {
                sb.append("ID VideoJuego: ").append(rs.getInt(1)).append("\n");
                sb.append("Nombre VideoJuego: ").append(rs.getString(2)).append("\n");
                sb.append("Genero: ").append(rs.getString(3)).append("\n");
                sb.append("Plataforma: ").append(rs.getString(4)).append("\n");
                sb.append("Pegi: ").append(rs.getInt(5)).append("\n");
                sb.append("Precio: ").append(rs.getDouble(6)).append("\n");
                sb.append("--------------------------------------------------").append("\n");
            }
        }
        return sb.toString();
    }

    public boolean comprobarIdUsuario(int idUsuario) throws SQLException {
        Connection con = getConnection();
        boolean existe = false;
        try (PreparedStatement userSelect = con.prepareStatement("SELECT idUsuarios FROM usuarios WHERE idUsuarios = ?")) {
            userSelect.setInt(1, idUsuario);
            ResultSet rs = userSelect.executeQuery();
            if(rs.next()) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean comprobarIdVideoJuego(int idVideoJuego) throws SQLException {
        Connection con = getConnection();
        boolean existe = false;
        try (PreparedStatement userSelect = con.prepareStatement("SELECT idVideoJuego FROM videojuego WHERE idVideoJuego = ?")) {
            userSelect.setInt(1, idVideoJuego);
            ResultSet rs = userSelect.executeQuery();
            if(rs.next()) {
                existe = true;
            }
        }
        return existe;
    }
    public static ArrayList<Usuario> listarUsuarios() throws SQLException {
        Connection con = DataBaseConnection.getInstance().getConexion();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement userSelect = con.prepareStatement("SELECT * FROM usuarios")) {
            ResultSet rs = userSelect.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
