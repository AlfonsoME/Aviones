package mx.fca.aviones;

public class Avion {
    private Direccion direccion;
    private int x;
    private int y;

    // Constructor normal
    public Avion(Direccion direccion, int x, int y) {
        this.direccion = direccion;
        this.x = x;
        this.y = y;
    }

    // 🔹 Constructor de copia
    public Avion(Avion otro) {
        this.direccion = otro.direccion;
        this.x = otro.x;
        this.y = otro.y;
    }

    // 🔹 Método clonar
    public Avion clonar() {
        return new Avion(this);
    }

    // Devuelve la imagen según la dirección del avión
    public int getImage() {
        switch (direccion) {
            case NORTH: return R.mipmap.north;  // ✅ usa drawable
            case SOUTH: return R.mipmap.south;
            case EAST:  return R.mipmap.east;
            case WEST:  return R.mipmap.west;
        }
        return R.mipmap.north;
    }

    // Mover el avión según su dirección
    public void mover() {
        switch (direccion) {
            case NORTH: y--; break;
            case SOUTH: y++; break;
            case EAST:  x++; break;
            case WEST:  x--; break;
        }
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public Direccion getDireccion() { return direccion; }

    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
}

