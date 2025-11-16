package mx.fca.aviones;

import java.util.ArrayList;
import java.util.HashMap;

public class Analizador {

    static HashMap<Integer, Plano> memoria = new HashMap<>();

    public static Plano inicializa(Plano plano) {
        memoria.put(0, plano);
        return plano;
    }

    public static Plano next(int noPaso, Plano plano){
        if (memoria.containsKey(noPaso)){
            return memoria.get(noPaso);
        } else {
            Plano planoNuevo;
            ArrayList<Avion> nuevosAviones = new ArrayList<>();
            ArrayList<Colision> colisiones = new ArrayList<>();

            // Mover cada avión según su dirección
            for (Avion avion: plano.aviones) {
                switch (avion.getDireccion()){
                    case NORTH:
                        avion.setY(avion.getY() - 1);
                        break;
                    case SOUTH:
                        avion.setY(avion.getY() + 1);
                        break;
                    case EAST:
                        avion.setX(avion.getX() + 1);
                        break;
                    case WEST:
                        avion.setX(avion.getX() - 1);
                        break;
                }
                nuevosAviones.add(avion);
            }

            // Detectar colisiones
            for (int i = 0; i < nuevosAviones.size(); i++) {
                for (int j = i + 1; j < nuevosAviones.size(); j++) {
                    Avion a1 = nuevosAviones.get(i);
                    Avion a2 = nuevosAviones.get(j);
                    if (a1.getX() == a2.getX() && a1.getY() == a2.getY()) {
                        colisiones.add(new Colision(a1.getX(), a1.getY()));
                    }
                }
            }

            planoNuevo = new Plano(noPaso, nuevosAviones, colisiones);
            memoria.put(noPaso, planoNuevo);
            return planoNuevo;
        }
    }

    public static Plano prev(int noPaso) {
        return memoria.get(noPaso);
    }
}

