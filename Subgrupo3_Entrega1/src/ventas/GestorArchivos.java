package ventas;

import java.io.*;
import java.util.*;

/**
 * Clase encargada de gestionar la lectura de archivos relacionados con vendedores, productos y ventas.
 */
public class GestorArchivos {

    /**
     * Lee un archivo de texto con información de vendedores.
     * Cada línea del archivo debe tener 4 campos separados por punto y coma.
     *
     * @param rutaArchivo Ruta del archivo a leer.
     * @return Lista de objetos Vendedor.
     */
    public static List<Vendedor> leerVendedores(String rutaArchivo) {
        List<Vendedor> vendedores = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        // Verifica si el archivo existe
        if (!archivo.exists()) {
            System.out.println("Error: El archivo de vendedores no existe en la ruta: " + rutaArchivo);
            return vendedores;
        }

        // Lectura del archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea en campos usando el separador ";"
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    // Crea un nuevo objeto Vendedor y lo agrega a la lista
                    vendedores.add(new Vendedor(datos[0], datos[1], datos[2], datos[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer vendedores: " + e.getMessage());
        }

        return vendedores;
    }

    /**
     * Lee un archivo de texto con información de productos.
     * Cada línea debe tener 2 campos: ID del producto y nombre.
     *
     * @param rutaArchivo Ruta del archivo a leer.
     * @return Lista de objetos Producto.
     */
    public static List<Producto> leerProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        // Verifica si el archivo existe
        if (!archivo.exists()) {
            System.out.println("Error: El archivo de productos no existe en la ruta: " + rutaArchivo);
            return productos;
        }

        // Lectura del archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea en campos usando ";"
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    // Crea un nuevo objeto Producto y lo agrega a la lista
                    productos.add(new Producto(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }

        return productos;
    }

    /**
     * Lee archivos de ventas ubicados dentro de una carpeta.
     * Cada archivo representa las ventas de un vendedor específico.
     *
     * @param carpeta Ruta de la carpeta que contiene los archivos de ventas.
     * @return Mapa que relaciona cada ID de vendedor con un mapa de productos y cantidades vendidas.
     */
    public static Map<String, Map<String, Integer>> leerVentas(String carpeta) {
        Map<String, Map<String, Integer>> ventas = new HashMap<>();
        File directorio = new File(carpeta);

        // Verifica si la carpeta existe y es un directorio
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("Error: La carpeta de ventas no existe en la ruta: " + carpeta);
            return ventas;
        }

        // Lista todos los archivos dentro de la carpeta
        File[] archivos = directorio.listFiles();
        if (archivos == null) return ventas;

        // Itera sobre cada archivo de ventas
        for (File archivo : archivos) {
            System.out.println("Leyendo ventas de: " + archivo.getName());

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                // El primer dato del archivo es el ID del vendedor
                String idVendedor = br.readLine();
                if (idVendedor == null || idVendedor.isEmpty()) continue;

                // Crea un mapa vacío para las ventas de ese vendedor
                ventas.put(idVendedor, new HashMap<>());

                String linea;
                // Lee cada línea del archivo como una venta
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    if (datos.length == 2) {
                        // Obtiene el ID del producto y la cantidad vendida
                        String idProducto = datos[0].trim();
                        int cantidad = Integer.parseInt(datos[1].trim());

                        // Agrega la venta al mapa del vendedor
                        ventas.get(idVendedor).put(idProducto, cantidad);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                // Manejo de errores durante la lectura
                System.out.println("Error en archivo de ventas: " + archivo.getName() + " → " + e.getMessage());
            }
        }

        return ventas;
    }
}
