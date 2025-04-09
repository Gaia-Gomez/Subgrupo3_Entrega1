package ventas;

/**
 * Clase que representa un vendedor con su información personal.
 */
public class Vendedor {
    // Atributos privados del vendedor
    private String tipoDocumento;      // Tipo de documento del vendedor (ej. CC, TI, etc.)
    private String numeroDocumento;    // Número de documento del vendedor
    private String nombres;            // Nombres del vendedor
    private String apellidos;          // Apellidos del vendedor

    /**
     * Constructor que inicializa un objeto Vendedor con sus datos básicos.
     *
     * @param tipoDocumento Tipo de documento del vendedor
     * @param numeroDocumento Número de documento del vendedor
     * @param nombres Nombres del vendedor
     * @param apellidos Apellidos del vendedor
     */
    public Vendedor(String tipoDocumento, String numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el tipo de documento del vendedor.
     *
     * @return Tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Obtiene el número de documento del vendedor.
     *
     * @return Número de documento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Obtiene los nombres del vendedor.
     *
     * @return Nombres del vendedor
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene los apellidos del vendedor.
     *
     * @return Apellidos del vendedor
     */
    public String getApellidos() {
        return apellidos;
    }
}
