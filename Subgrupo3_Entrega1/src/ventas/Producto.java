package ventas;

public class Producto {
    private String id;
    private String nombre;
    // Constructor que inicializa un producto con un ID y un nombre

    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
// Getter para obtener el ID del producto
    public String getId() {
        return id;
    }
// Getter para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }
}
