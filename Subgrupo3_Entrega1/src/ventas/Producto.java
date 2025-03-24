package ventas; // Asegúrate de que esté en el paquete correcto

public class Producto {
    private String id;
    private String nombre;

    // Constructor que recibe dos Strings
    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return id + ": " + nombre;
    }
}