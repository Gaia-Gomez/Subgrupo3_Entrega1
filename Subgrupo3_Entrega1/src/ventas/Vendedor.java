package ventas;

public class Vendedor {
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
 // Constructor para crear un vendedor con sus datos
    public Vendedor(String tipoDocumento, String numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
// Getters para obtener los datos del vendedor
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
}
