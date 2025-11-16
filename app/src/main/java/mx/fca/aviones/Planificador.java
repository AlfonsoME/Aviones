package mx.fca.aviones;

import java.util.ArrayList;

public class Planificador {

    public static Plano crearRutaInicial() {
        ArrayList<Avion> aviones = new ArrayList<>();
        ArrayList<Colision> colisiones = new ArrayList<>();

        // Avión que va hacia el Este (izquierda → derecha)
        aviones.add(new Avion(Direccion.EAST, 0, 2));

        // Avión que va hacia el Oeste (derecha → izquierda)
        aviones.add(new Avion(Direccion.WEST, 6, 2));

        // Avión que va hacia el Norte (abajo → arriba)
        aviones.add(new Avion(Direccion.NORTH, 1, 5));

        // Avión que va hacia el Sur (arriba → abajo)
        aviones.add(new Avion(Direccion.SOUTH, 5, 0));

        // Avión diagonal (si tu clase Avion soporta más direcciones)
        // aviones.add(new Avion(Direccion.NORTHEAST, 1, 5));

        return new Plano(0, aviones, colisiones);
    }
}


