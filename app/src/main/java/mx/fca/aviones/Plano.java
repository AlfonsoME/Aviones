package mx.fca.aviones;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class Plano {

    public ArrayList<Avion> aviones;
    public ArrayList<Colision> colisiones;
    public int col;
    public int row;
    public int noPaso;

    // Historial de planos para retroceder
    private static List<Plano> historial = new ArrayList<>();

    // Constructor
    public Plano(int noPaso, ArrayList<Avion> aviones, ArrayList<Colision> colisiones) {
        this.noPaso = noPaso;
        this.aviones = aviones;
        this.colisiones = colisiones;

        int tmpX = 0;
        int tmpY = 0;
        for (Avion avion : aviones) {
            if (avion.getX() > tmpX) {
                tmpX = avion.getX();
            }
            if (avion.getY() > tmpY) {
                tmpY = avion.getY();
            }
        }
        col = tmpX;
        row = tmpY;

        Log.i("Aviones max de columna", String.valueOf(col));
        Log.i("Aviones max de renglon", String.valueOf(row));
    }

    // Crear una copia profunda del plano
    private Plano copiar() {
        ArrayList<Avion> copiaAviones = new ArrayList<>();
        for (Avion avion : aviones) {
            copiaAviones.add(avion.clonar()); // ✅ ahora sí existe
        }

        ArrayList<Colision> copiaColisiones = new ArrayList<>();
        for (Colision colision : colisiones) {
            copiaColisiones.add(colision.clonar()); // ✅ ahora sí existe
        }

        return new Plano(noPaso, copiaAviones, copiaColisiones);
    }

    // Avanzar al siguiente paso
    public Plano next() {
        // Guardar copia del estado actual en historial
        historial.add(this.copiar());

        noPaso += 1;

        // Mover cada avión
        for (Avion avion : aviones) {
            avion.mover();
        }

        // Detectar colisiones
        colisiones.clear();
        for (int i = 0; i < aviones.size(); i++) {
            for (int j = i + 1; j < aviones.size(); j++) {
                Avion a1 = aviones.get(i);
                Avion a2 = aviones.get(j);
                if (a1.getX() == a2.getX() && a1.getY() == a2.getY()) {
                    colisiones.add(new Colision(a1.getX(), a1.getY()));
                }
            }
        }

        return this;
    }

    // Retroceder un paso
    public Plano prev() {
        if (noPaso > 0 && !historial.isEmpty()) {
            noPaso -= 1;
            // Recuperar el último plano guardado intacto
            return historial.remove(historial.size() - 1);
        }
        return this; // si no hay historial, regresa el mismo plano
    }

    // Número de colisiones
    public int getNumeroColisiones() {
        return colisiones.size();
    }

    // Número de aviones
    public int getNumeroAviones() {
        return aviones.size();
    }

    // Número de pasos
    public int getPasos() {
        return noPaso;
    }
}
