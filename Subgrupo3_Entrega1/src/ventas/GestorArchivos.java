package ventas;

import java.io.*;
import java.util.*;

public class GestorArchivos {
    // Método para leer los vendedores desde un archivo
    public static List<Vendedor> leerVendedores(String rutaArchivo) {
        List<Vendedor> vendedores = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        // Verificamos si el archivo existe
        if (!archivo.exists()) {
            System.out.println("Error: El archivo de vendedores no existe en la ruta: " + rutaArchivo);
            return vendedores;
        }
// Intentamos leer el archivo

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Leemos línea por línea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
               // Separamos los datos usando el punto y coma
                // Si hay 4 datos en la línea, los usamos para crear un vendedor
                if (datos.length == 4) {
                    vendedores.add(new Vendedor(datos[0], datos[1], datos[2], datos[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer vendedores: " + e.getMessage());
        }
        return vendedores;
    }
 // Método para leer los productos desde un archivo
    public static List<Producto> leerProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        // Verificamos si el archivo existe

        if (!archivo.exists()) {
            System.out.println("Error: El archivo de productos no existe en la ruta: " + rutaArchivo);
            return productos;
        }
// Intentamos leer el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Leemos línea por línea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");// Separamos los datos usando el punto y coma
                // Si hay 2 datos en la línea, los usamos para crear un producto
                if (datos.length == 2) {
                    productos.add(new Producto(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }
        return productos;
    }
 // Método para leer las ventas desde los archivos en una carpeta
    public static Map<String, Map<String, Integer>> leerVentas(String carpeta) {
        Map<String, Map<String, Integer>> ventas = new HashMap<>();
        File directorio = new File(carpeta);
// Verificamos si la carpeta existe
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("Error: La carpeta de ventas no existe en la ruta: " + carpeta);
            return ventas;
        }
// Listamos los archivos dentro de la carpeta
        File[] archivos = directorio.listFiles();
        if (archivos == null) return ventas;
// Leemos cada archivo de ventas
        for (File archivo : archivos) {
            System.out.println("Leyendo ventas de: " + archivo.getName());

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                // El primer dato en el archivo es el ID del vendedor
                String idVendedor = br.readLine();
                if (idVendedor == null || idVendedor.isEmpty()) continue;
// Inicializamos un mapa para las ventas del vendedor
                ventas.put(idVendedor, new HashMap<>());

                String linea;
                // Leemos las ventas del vendedor
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");// Separamos los datos
                    // Si hay 2 datos en la línea, los usamos para registrar la venta
                    if (datos.length == 2) {
                        String idProducto = datos[0].trim();
                        int cantidad = Integer.parseInt(datos[1].trim());
                        ventas.get(idVendedor).put(idProducto, cantidad);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error en archivo de ventas: " + archivo.getName() + " → " + e.getMessage());
            }
        }

        return ventas;
    }
}
