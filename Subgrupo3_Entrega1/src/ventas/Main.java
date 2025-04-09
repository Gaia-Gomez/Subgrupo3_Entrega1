package ventas;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Estas son las rutas de los archivos de vendedores, productos y la carpeta de ventas
        String rutaVendedores = "data/vendedores.txt";
        String rutaProductos = "data/productos.txt";
        String carpetaVentas = "data/ventas"; // Asegúrate de que la carpeta exista

        // Aqui lee la lista de vendedores
        List<Vendedor> vendedores = GestorArchivos.leerVendedores(rutaVendedores);
        if (vendedores.isEmpty()) {
            System.out.println("No se encontraron vendedores.");
            return;
        }

        // aqui lee la lista de productos
        List<Producto> productos = GestorArchivos.leerProductos(rutaProductos);
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }

        // aqui lee las ventas de los vendedores
        Map<String, Map<String, Integer>> ventas = GestorArchivos.leerVentas(carpetaVentas);

        // nos muestra el reporte
        System.out.println("\n===== REPORTE DE VENTAS =====");
        for (Vendedor vendedor : vendedores) {
            String idVendedor = vendedor.getTipoDocumento() + ";" + vendedor.getNumeroDocumento();
            System.out.println("\nVendedor: " + vendedor.getNombres() + " " + vendedor.getApellidos());
            // Si el vendedor tiene ventas registradas, las mostramos
            if (ventas.containsKey(idVendedor)) {
                // Iteramos sobre las ventas de ese vendedor
                for (Map.Entry<String, Integer> entry : ventas.get(idVendedor).entrySet()) {
                    String idProducto = entry.getKey();
                    int cantidad = entry.getValue();
                    // Buscamos el nombre del producto usando su ID
                    String nombreProducto = productos.stream()
                            .filter(p -> p.getId().equals(idProducto))
                            .map(Producto::getNombre)
                            .findFirst()
                            .orElse("Producto Desconocido");
                     // Imprimimos la venta
                    System.out.println("  - " + nombreProducto + ": " + cantidad + " unidades");
                }
            } else {
                // Si el vendedor no tiene ventas, lo indicamos
                System.out.println("  - No tiene ventas registradas.");
            }
        }
    }
}
