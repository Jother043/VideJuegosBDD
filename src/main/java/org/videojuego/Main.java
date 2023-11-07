package org.videojuego;

import org.videojuego.Controladores.Observaciones;
import org.videojuego.Controladores.Usuario;
import org.videojuego.Controladores.VideoJuego;
import org.videojuego.Controladores.VideoJuegoBBDD;
import java.sql.SQLException;
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
            switch (opcion){
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
                        Observaciones observaciones1 = new Observaciones(idObservaciones, idVideoJuego, duracion, puntuacion, vecesJugado, idUsuario);
                        videoJuegoBBDD.insertarObservaciones(observaciones1);
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al insertar las observaciones");
                        sc.nextLine();
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Introduce el nombre del usuario");
                        String nombre = sc.next();
                        System.out.println(videoJuegoBBDD.seleccionarObservacionesUsuarios(nombre));
                    } catch (SQLException | InputMismatchException o) {
                        System.out.println("Error al seleccionar las observaciones");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }while (opcion != 5);
    }

    public static String menu(){
        StringBuilder menu = new StringBuilder();
        menu.append("1. Insertar videojuego\n");
        menu.append("2. Insertar usuario\n");
        menu.append("3. Insertar observaciones\n");
        menu.append("4. Seleccionar observaciones de un usuario\n");
        menu.append("5. Salir.\n");
        menu.append("Introduce una opción: ");
        return menu.toString();
    }
}