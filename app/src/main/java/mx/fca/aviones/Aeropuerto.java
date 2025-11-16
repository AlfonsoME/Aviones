package mx.fca.aviones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aeropuerto {
    private List<Avion> aviones = new ArrayList<>();
    private int pasos = 0;
    private int colisiones = 0;

    // Agregar un avión al aeropuerto
    public void agregarAvion(Avion a) {
        aviones.add(a);
    }

    // Avanzar un paso en la simulación
    public void siguientePaso() {
        pasos++;

        // Mapa para registrar posiciones y detectar colisiones
        Map<String, List<Avion>> posiciones = new HashMap<>();

        for (Avion a : aviones) {
            a.mover(); // mover cada avión según su dirección
            String pos = a.getX() + "," + a.getY();

            posiciones.putIfAbsent(pos, new ArrayList<>());
            posiciones.get(pos).add(a);
        }

        // Detectar colisiones
        for (List<Avion> lista : posiciones.values()) {
            if (lista.size() > 1) {
                colisiones++;
                System.out.println("💥 Colisión en (" +
                        lista.get(0).getX() + "," + lista.get(0).getY() + ")");
            }
        }
    }

    // Retroceder un paso (requiere historial si quieres hacerlo completo)
    public void pasoAnterior() {
        if (pasos > 0) {
            pasos--;
            // Aquí podrías implementar un historial de posiciones
            // usando una pila (Stack) para guardar estados previos.
            System.out.println("⏪ Retroceder un paso (pendiente implementar historial)");
        }
    }

    // Getters para mostrar información en pantalla
    public int getPasos() {
        return pasos;
    }

    public int getColisiones() {
        return colisiones;
    }

    public List<Avion> getAviones() {
        return aviones;
    }
}

