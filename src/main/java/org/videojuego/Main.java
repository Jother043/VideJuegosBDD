package org.videojuego;

import org.videojuego.Controladores.Observaciones;
import org.videojuego.Controladores.Usuario;
import org.videojuego.Controladores.VideoJuegoBBDD;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        VideoJuegoBBDD bdd = new VideoJuegoBBDD();
//        VideoJuego v = new VideoJuego(3,"Call Of Duty","FPS","All",16,55.70);
        Usuario user = new Usuario(1,"Jose","Gutierrez");
        Observaciones observable = new Observaciones(1,2,6,7.9,68,1);
        try {
//            bdd.insertarJuego(v);
//            bdd.insertarUsuarios(user);
            System.out.println(bdd.seleccionarObservacionesUsuarios("jose"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}