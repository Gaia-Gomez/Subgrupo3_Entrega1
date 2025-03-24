package ventas;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String rutaVendedores = "data/vendedores.txt";
        String rutaProductos = "data/productos.txt";
        String carpetaVentas = "data/ventas"; // Asegúrate de que la carpeta exista

        // Leer la lista de vendedores
        List<Vendedor> vendedores = GestorArchivos.leerVendedores(rutaVendedores);
        if (vendedores.isEmpty()) {
            System.out.println("No se encontraron vendedores.");
            return;
        }

        // Leer la lista de productos
        List<Producto> productos = GestorArchivos.leerProductos(rutaProductos);
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        // Leer las ventas de los vendedores
        Map<String, Map<String, Integer>> ventas = GestorArchivos.leerVentas(carpetaVentas);

        // Mostrar reporte
        System.out.println("\n===== REPORTE DE VENTAS =====");
        for (Vendedor vendedor : vendedores) {
            String idVendedor = vendedor.getTipoDocumento() + ";" + vendedor.getNumeroDocumento();
            System.out.println("\nVendedor: " + vendedor.getNombres() + " " + vendedor.getApellidos());

            if (ventas.containsKey(idVendedor)) {
                for (Map.Entry<String, Integer> entry : ventas.get(idVendedor).entrySet()) {
                    String idProducto = entry.getKey();
                    int cantidad = entry.getValue();
                    String nombreProducto = productos.stream()
                            .filter(p -> p.getId().equals(idProducto))
                            .map(Producto::getNombre)
                            .findFirst()
                            .orElse("Producto Desconocido");
                    System.out.println("  - " + nombreProducto + ": " + cantidad + " unidades");
                }
            } else {
                System.out.println("  - No tiene ventas registradas.");
            }
        }
    }
}