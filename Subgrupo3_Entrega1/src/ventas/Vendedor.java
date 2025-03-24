package ventas; // Asegúrate de que esté en el paquete correcto

public class Vendedor {
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;

    public Vendedor(String tipoDocumento, String numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return tipoDocumento + " " + numeroDocumento + ": " + nombres + " " + apellidos;
    }
}