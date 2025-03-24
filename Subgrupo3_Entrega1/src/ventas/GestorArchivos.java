package ventas;

import java.io.*;
import java.util.*;

public class GestorArchivos {
    public static List<Vendedor> leerVendedores(String rutaArchivo) {
        List<Vendedor> vendedores = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Error: El archivo de vendedores no existe en la ruta: " + rutaArchivo);
            return vendedores;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    vendedores.add(new Vendedor(datos[0], datos[1], datos[2], datos[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer vendedores: " + e.getMessage());
        }
        return vendedores;
    }

    public static List<Producto> leerProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Error: El archivo de productos no existe en la ruta: " + rutaArchivo);
            return productos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    productos.add(new Producto(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }
        return productos;
    }

    public static Map<String, Map<String, Integer>> leerVentas(String carpeta) {
        Map<String, Map<String, Integer>> ventas = new HashMap<>();
        File directorio = new File(carpeta);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("Error: La carpeta de ventas no existe en la ruta: " + carpeta);
            return ventas;
        }

        File[] archivos = directorio.listFiles();
        if (archivos == null) return ventas;

        for (File archivo : archivos) {
            System.out.println("Leyendo ventas de: " + archivo.getName());

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String idVendedor = br.readLine();
                if (idVendedor == null || idVendedor.isEmpty()) continue;

                ventas.put(idVendedor, new HashMap<>());

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
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