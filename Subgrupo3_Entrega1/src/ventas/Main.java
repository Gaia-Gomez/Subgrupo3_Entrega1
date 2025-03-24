package ventas;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ruta de los archivos (ajustar según la ubicación)
        String rutaVendedores = "vendedores.txt";
        String rutaProductos = "productos.txt";

        // Leer archivos
        List<Vendedor> vendedores = GestorArchivos.leerVendedores(rutaVendedores);
        List<Producto> productos = GestorArchivos.leerProductos(rutaProductos);

        // Mostrar datos cargados
        System.out.println("Lista de Vendedores:");
        for (Vendedor v : vendedores) {
            System.out.println(v);
        }

        System.out.println("\nLista de Productos:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}