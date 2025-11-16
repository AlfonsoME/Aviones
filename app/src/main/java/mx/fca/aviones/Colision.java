package mx.fca.aviones;

public class Colision {
    private int x;
    private int y;

    // Constructor normal
    public Colision(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Constructor de copia
    public Colision(Colision otra) {
        this.x = otra.x;
        this.y = otra.y;
    }

    // Método clonar
    public Colision clonar() {
        return new Colision(this);
    }

    // Imagen para representar la colisión
    public int getImage() {
        return R.drawable.collision; // ✅ usa drawable
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}






