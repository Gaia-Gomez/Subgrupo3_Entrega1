package ventas;

/**
 * Clase que representa un producto con un identificador y un nombre.
 */
public class Producto {
    // Atributos privados del producto
    private String id;      // Identificador único del producto
    private String nombre;  // Nombre del producto

    /**
     * Constructor que inicializa un producto con su ID y nombre.
     *
     * @param id Identificador del producto
     * @param nombre Nombre del producto
     */
    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return ID del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
}
