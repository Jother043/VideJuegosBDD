package org.videojuego;

import org.videojuego.Controladores.Observaciones;
import org.videojuego.Controladores.Usuario;
import org.videojuego.Controladores.VideoJuego;
import org.videojuego.Controladores.VideoJuegoBBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static VideoJuegoBBDD videoJuegoBBDD = new VideoJuegoBBDD();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;
        do {
            System.out.println(menu());
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    try {
                        System.out.println("Introduce el id del videojuego");
                        int idVideoJuego = sc.nextInt();
                        System.out.println("Introduce el nombre del videojuego");
                        String nombre = sc.next();
                        System.out.println("Introduce el género del videojuego");
                        String genero = sc.next();
                        System.out.println("Introduce la plataforma del videojuego");
                        String plataforma = sc.next();
                        System.out.println("Introduce el pegi del videojuego");
                        int pegi = sc.nextInt();
                        System.out.println("Introduce el precio del videojuego");
                        double precio = sc.nextDouble();
                        validarDatosVideojuego(idVideoJuego, nombre, genero, plataforma, pegi, precio);
                        VideoJuego videoJuego = new VideoJuego(idVideoJuego, nombre, genero, plataforma, pegi, precio);
                        videoJuegoBBDD.insertarJuego(videoJuego);
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al insertar el videojuego");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Introduce el id del usuario");
                        int idUsuario = sc.nextInt();
                        System.out.println("Introduce el nombre del usuario");
                        String nombre = sc.next();
                        System.out.println("Introduce el apellido del usuario");
                        String apellido = sc.next();
                        validarUsuario(idUsuario, nombre, apellido);
                        Usuario usuario = new Usuario(idUsuario, nombre, apellido);
                        videoJuegoBBDD.insertarUsuarios(usuario);
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al insertar el usuario");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Introduce el id de las observaciones");
                        int idObservaciones = sc.nextInt();
                        System.out.println("Introduce el id del videojuego");
                        int idVideoJuego = sc.nextInt();
                        System.out.println("Introduce la duración del juego");
                        double duracion = sc.nextInt();
                        System.out.println("Introduce la puntuación del juego");
                        double puntuacion = sc.nextInt();
                        System.out.println("Introduce la veces que se ha jugado");
                        int vecesJugado = sc.nextInt();
                        System.out.println("Introduce el id del usuario");
                        int idUsuario = sc.nextInt();
                        validarObservaciones(idObservaciones, idVideoJuego, duracion, puntuacion, vecesJugado, idUsuario);
                        Observaciones observaciones1 = new Observaciones(idObservaciones, idVideoJuego, duracion, puntuacion, vecesJugado, idUsuario);
                        videoJuegoBBDD.insertarObservaciones(observaciones1);
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al insertar las observaciones");
                        sc.nextLine();
                    }
                    break;
                case 4:

                    try {
                        for (Usuario usuario : VideoJuegoBBDD.listarUsuarios()) {
                            System.out.println(usuario);
                        }
                        System.out.println("Introduce el id del usuario");
                        int idUsuario = sc.nextInt();
                        System.out.println(videoJuegoBBDD.listarObservacionesUsuario(idUsuario));
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al seleccionar las observaciones");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    try {
                        System.out.println(VideoJuegoBBDD.listarJuegos());
                    } catch (SQLException e) {
                        System.err.println("Error al listar los juegos");
                    }
                    break;
                case 6:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 6);
    }


    public static String menu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Insertar videojuego\n");
        menu.append("2. Insertar usuario\n");
        menu.append("3. Insertar observaciones\n");
        menu.append("4. Seleccionar observaciones de un usuario\n");
        menu.append("5. Listar juegos \n");
        menu.append("6. Salir\n");
        menu.append("Introduce una opción: ");
        return menu.toString();
    }

    /**
     * Método que valida los datos del videojuego introducidos por el usuario, si son correctos devuelve true, si no false
     * y muestra un mensaje de error por pantalla indicando el error que se ha producido, utilizamos los setters de la clase.
     *
     * @return true si los datos son correctos, false si no lo son
     */
    public static boolean validarDatosVideojuego(int idVideoJuego, String nombre, String genero, String plataforma, int pegi, double precio) {

        boolean datosCorrectos = true;
        String mensajeError = "";
        if (idVideoJuego < 0) {
            datosCorrectos = false;
            mensajeError += "El id del videojuego no puede ser negativo\n";
        }
        if (nombre == null || nombre.trim().equals("")) {
            datosCorrectos = false;
            mensajeError += "El nombre del videojuego no puede estar vacío\n";
        }
        if (genero == null || genero.trim().equals("")) {
            datosCorrectos = false;
            mensajeError += "El género del videojuego no puede estar vacío\n";
        }
        if (plataforma == null || plataforma.trim().equals("")) {
            datosCorrectos = false;
            mensajeError += "La plataforma del videojuego no puede estar vacía\n";
        }
        if (pegi < 0) {
            datosCorrectos = false;
            mensajeError += "El pegi del videojuego no puede ser negativo\n";
        }
        if (precio < 0) {
            datosCorrectos = false;
            mensajeError += "El precio del videojuego no puede ser negativo\n";
        }
        if (!datosCorrectos) {
            System.out.println(mensajeError);
        }
        return datosCorrectos;
    }

    public static boolean validarUsuario(int idUsuario, String nombre, String apellido) {

        boolean datosCorrectos = true;
        String mensajeError = "";
        if (idUsuario < 0) {
            datosCorrectos = false;
            mensajeError += "El id del usuario no puede ser negativo\n";
        }
        if (nombre == null || nombre.trim().equals("")) {
            datosCorrectos = false;
            mensajeError += "El nombre del usuario no puede estar vacío\n";
        }
        if (apellido == null || apellido.trim().equals("")) {
            datosCorrectos = false;
            mensajeError += "El apellido del usuario no puede estar vacío\n";
        }
        if (!datosCorrectos) {
            System.out.println(mensajeError);
        }
        return datosCorrectos;
    }

    private static void validarObservaciones(int idObservaciones, int idVideoJuego, double duracion, double puntuacion, int vecesJugado, int idUsuario) throws SQLException {

        boolean datosCorrectos = true;
        String mensajeError = "";
        if (idObservaciones < 0) {
            datosCorrectos = false;
            mensajeError += "El id de las observaciones no puede ser negativo\n";
        }
        if (idVideoJuego < 0 || !videoJuegoBBDD.comprobarIdVideoJuego(idVideoJuego)) {
            if (!videoJuegoBBDD.comprobarIdVideoJuego(idVideoJuego)) {
                mensajeError += "El id del videojuego no existe\n";
            }
            if (idVideoJuego < 0) {
                mensajeError += "El id del videojuego no puede ser negativo\n";
            }
            datosCorrectos = false;
        }
        if (duracion < 0) {
            datosCorrectos = false;
            mensajeError += "La duración del juego no puede ser negativa\n";
        }
        if (puntuacion < 0) {
            datosCorrectos = false;
            mensajeError += "La puntuación del juego no puede ser negativa\n";
        }
        if (vecesJugado < 0) {
            datosCorrectos = false;
            mensajeError += "Las veces que se ha jugado no puede ser negativa\n";
        }
        if (idUsuario < 0 || !videoJuegoBBDD.comprobarIdUsuario(idUsuario)) {
            if (!videoJuegoBBDD.comprobarIdUsuario(idUsuario)) {
                mensajeError += "El id del usuario no existe\n";
            }
            if (idUsuario < 0) {
                mensajeError += "El id del usuario no puede ser negativo\n";
            }
            datosCorrectos = false;
        }
        if (!datosCorrectos) {
            System.out.println(mensajeError);
        }
    }

}