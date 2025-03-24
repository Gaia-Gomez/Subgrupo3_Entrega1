package ventas;

import java.io.*;
import java.util.*;

public class GestorArchivos {

    public static List<Vendedor> leerVendedores(String rutaArchivo) {
        List<Vendedor> vendedores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    vendedores.add(new Vendedor(datos[0], datos[1], datos[2], datos[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de vendedores: " + e.getMessage());
        }
        return vendedores;
    }

    public static List<Producto> leerProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    productos.add(new Producto(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de productos: " + e.getMessage());
        }
        return productos;
    }
}