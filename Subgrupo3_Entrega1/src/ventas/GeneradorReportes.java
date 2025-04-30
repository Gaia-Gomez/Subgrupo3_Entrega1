package ventas;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

public class GeneradorReportes {

    static final String RUTA_VENDEDORES = "data/vendedores.txt";
    static final String RUTA_PRODUCTOS = "data/productos.txt";
    static final String CARPETA_VENTAS = "data/ventas";
    static final String REPORTE_VENDEDORES = "data/reporte_vendedores.csv";
    static final String REPORTE_PRODUCTOS = "data/reporte_productos.csv";

    public static void generarReportes() throws IOException {
        Map<String, Double> totalPorVendedor = new HashMap<>();
        Map<Integer, ProductoInfo> productosVendidos = new HashMap<>();

        // 1. Cargar productos (para saber su precio y nombre)
        Map<Integer, ProductoInfo> productosCatalogo = new HashMap<>();
        BufferedReader prodReader = new BufferedReader(new FileReader(RUTA_PRODUCTOS));
        String linea;
        while ((linea = prodReader.readLine()) != null) {
            String[] partes = linea.split(",");
            int codigo = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            double precio = Double.parseDouble(partes[3]);
            productosCatalogo.put(codigo, new ProductoInfo(nombre, precio, 0));
        }
        prodReader.close();

        // 2. Leer cada archivo de venta
        Files.list(Paths.get(CARPETA_VENTAS)).forEach(path -> {
            String nombreArchivo = path.getFileName().toString();
            String cedula = nombreArchivo.replace(".txt", "");

            double total = 0;
            try (BufferedReader ventaReader = new BufferedReader(new FileReader(path.toFile()))) {
                String venta;
                while ((venta = ventaReader.readLine()) != null) {
                    String[] partes = venta.split(",");
                    int codigoProd = Integer.parseInt(partes[0]);
                    int cantidad = Integer.parseInt(partes[1]);

                    ProductoInfo info = productosCatalogo.get(codigoProd);
                    if (info != null) {
                        double subtotal = info.precio * cantidad;
                        total += subtotal;

                        // Acumular producto vendido
                        productosVendidos.putIfAbsent(codigoProd, new ProductoInfo(info.nombre, info.precio, 0));
                        productosVendidos.get(codigoProd).cantidadVendida += cantidad;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            totalPorVendedor.put(cedula, total);
        });

        // 3. Generar reporte de vendedores
        List<Map.Entry<String, Double>> listaVendedores = new ArrayList<>(totalPorVendedor.entrySet());
        listaVendedores.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        BufferedWriter writerVendedores = new BufferedWriter(new FileWriter(REPORTE_VENDEDORES));
        writerVendedores.write("Cedula,Total Recaudado");
        writerVendedores.newLine();
        for (Entry<String, Double> entry : listaVendedores) {
            writerVendedores.write(entry.getKey() + "," + String.format("%.2f", entry.getValue()));
            writerVendedores.newLine();
        }
        writerVendedores.close();

        // 4. Generar reporte de productos
        List<Map.Entry<Integer, ProductoInfo>> listaProductos = new ArrayList<>(productosVendidos.entrySet());
        listaProductos.sort((a, b) -> Integer.compare(b.getValue().cantidadVendida, a.getValue().cantidadVendida));

        BufferedWriter writerProductos = new BufferedWriter(new FileWriter(REPORTE_PRODUCTOS));
        writerProductos.write("Codigo,Nombre,Precio,Total Vendido");
        writerProductos.newLine();
        for (Entry<Integer, ProductoInfo> entry : listaProductos) {
            ProductoInfo p = entry.getValue();
            writerProductos.write(entry.getKey() + "," + p.nombre + "," + p.precio + "," + p.cantidadVendida);
            writerProductos.newLine();
        }
        writerProductos.close();
    }

    // Clase auxiliar para guardar datos de producto
    static class ProductoInfo {
        String nombre;
        double precio;
        int cantidadVendida;

        public ProductoInfo(String nombre, double precio, int cantidadVendida) {
            this.nombre = nombre;
            this.precio = precio;
            this.cantidadVendida = cantidadVendida;
        }
    }
}