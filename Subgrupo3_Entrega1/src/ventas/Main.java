package ventas;

import java.util.List;
import java.util.Map;

/**
 * Clase principal del programa.
 * Se encarga de orquestar la lectura de archivos y mostrar un reporte de ventas por vendedor.
 */
public class Main {
    public static void main(String[] args) {
        // Definir las rutas de los archivos de entrada
        String rutaVendedores = "data/vendedores.txt";
        String rutaProductos = "data/productos.txt";
        String carpetaVentas = "data/ventas"; // Asegúrate de que la carpeta exista

        // Leer la lista de vendedores desde el archivo
        List<Vendedor> vendedores = GestorArchivos.leerVendedores(rutaVendedores);
        if (vendedores.isEmpty()) {
            System.out.println("No se encontraron vendedores.");
            return; // Finaliza el programa si no hay vendedores
        }

        // Leer la lista de productos desde el archivo
        List<Producto> productos = GestorArchivos.leerProductos(rutaProductos);
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return; // Finaliza el programa si no hay productos
        }

        // Leer las ventas de todos los vendedores desde los archivos en la carpeta
        Map<String, Map<String, Integer>> ventas = GestorArchivos.leerVentas(carpetaVentas);

        // Mostrar el reporte de ventas
        System.out.println("\n===== REPORTE DE VENTAS =====");
        for (Vendedor vendedor : vendedores) {
            // Construye el identificador único del vendedor (tipoDocumento;numeroDocumento)
            String idVendedor = vendedor.getTipoDocumento() + ";" + vendedor.getNumeroDocumento();
            
            // Imprime el nombre completo del vendedor
            System.out.println("\nVendedor: " + vendedor.getNombres() + " " + vendedor.getApellidos());

            // Verifica si hay ventas registradas para ese vendedor
            if (ventas.containsKey(idVendedor)) {
                // Itera sobre cada venta (producto y cantidad)
                for (Map.Entry<String, Integer> entry : ventas.get(idVendedor).entrySet()) {
                    String idProducto = entry.getKey();
                    int cantidad = entry.getValue();

                    // Busca el nombre del producto correspondiente al ID
                    String nombreProducto = productos.stream()
                            .filter(p -> p.getId().equals(idProducto))
                            .map(Producto::getNombre)
                            .findFirst()
                            .orElse("Producto Desconocido"); // Si no se encuentra el producto

                    // Imprime el nombre del producto y la cantidad vendida
                    System.out.println("  - " + nombreProducto + ": " + cantidad + " unidades");
                }
            } else {
                // Si no hay ventas registradas para ese vendedor
                System.out.println("  - No tiene ventas registradas.");
            }
        }
    }
}
