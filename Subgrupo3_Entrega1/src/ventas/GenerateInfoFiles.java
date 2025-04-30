package ventas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    private static final Random rand = new Random();
    private static final String RUTA_VENDEDORES = "data/vendedores.txt";
    private static final String RUTA_PRODUCTOS = "data/productos.txt";
    private static final String CARPETA_VENTAS = "data/ventas";

    private static final String[] NOMBRES = {"Ana", "Carlos", "Luisa", "Mario", "Sofía"};
    private static final String[] APELLIDOS = {"Gómez", "Rodríguez", "Martínez", "López", "Ramírez"};
    private static final String[] TIPOS_DOCUMENTO = {"CC", "TI"};
    private static final String[] PRODUCTOS = {"Camiseta", "Pantalón", "Zapatos", "Chaqueta", "Bufanda"};
    private static final String[] CATEGORIAS = {"Ropa", "Calzado", "Accesorios"};

    public static void main(String[] args) {
        try {
            generarVendedores(5);
            generarProductos(5);
            generarVentas(5);
            System.out.println("¡Archivos generados con éxito!");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }

    public static void generarVendedores(int cantidad) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_VENDEDORES));
        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES[rand.nextInt(NOMBRES.length)];
            String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];
            String tipoDoc = TIPOS_DOCUMENTO[rand.nextInt(TIPOS_DOCUMENTO.length)];
            long documento = 1000000000L + rand.nextInt(900000000);

            writer.write(tipoDoc + "," + documento + "," + nombre + " " + apellido);
            writer.newLine();
        }
        writer.close();
    }

    public static void generarProductos(int cantidad) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_PRODUCTOS));
        for (int i = 0; i < cantidad; i++) {
            String nombreProducto = PRODUCTOS[rand.nextInt(PRODUCTOS.length)];
            String categoria = CATEGORIAS[rand.nextInt(CATEGORIAS.length)];
            int codigo = 1000 + rand.nextInt(9000);
            double precio = Math.round((10 + rand.nextDouble() * 90) * 100.0) / 100.0;

            writer.write(codigo + "," + nombreProducto + "," + categoria + "," + precio);
            writer.newLine();
        }
        writer.close();
    }

    public static void generarVentas(int cantidadPorVendedor) throws IOException {
        File carpeta = new File(CARPETA_VENTAS);
        if (!carpeta.exists()) carpeta.mkdirs();

        for (int i = 0; i < 5; i++) {
            long documento = 1000000000L + rand.nextInt(900000000);
            BufferedWriter writer = new BufferedWriter(new FileWriter(CARPETA_VENTAS + "/" + documento + ".txt"));

            for (int j = 0; j < cantidadPorVendedor; j++) {
                int codigoProducto = 1000 + rand.nextInt(9000);
                int cantidad = 1 + rand.nextInt(5);
                writer.write(codigoProducto + "," + cantidad);
                writer.newLine();
            }

            writer.close();
        }
    }
}