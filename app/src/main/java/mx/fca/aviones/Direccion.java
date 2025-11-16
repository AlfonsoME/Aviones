package mx.fca.aviones;

public enum Direccion {
    NORTH, SOUTH, EAST, WEST;

    // Devuelve un texto descriptivo
    public String getNombre() {
        switch (this) {
            case NORTH: return "Norte";
            case SOUTH: return "Sur";
            case EAST:  return "Este";
            case WEST:  return "Oeste";
            default:    return "";
        }
    }
}

