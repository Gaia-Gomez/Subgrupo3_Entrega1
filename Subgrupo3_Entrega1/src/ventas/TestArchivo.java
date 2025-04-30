package ventas;

import java.io.*;

/**
 * Clase de prueba que permite leer y mostrar el contenido de un archivo de ventas específico.
 */
public class TestArchivo {
    public static void main(String[] args) {
        // Ruta del archivo de ventas que se desea leer
        File archivo = new File("ventas/1001234567.txt");

        // Verifica si el archivo existe antes de intentar leerlo
        if (!archivo.exists()) {
            System.out.println("❌ El archivo no existe: " + archivo.getAbsolutePath());
            return; // Finaliza el programa si el archivo no existe
        }

        // Muestra el nombre del archivo que se va a leer
        System.out.println("📂 Leyendo archivo: " + archivo.getName());

        // Lectura del contenido del archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Mientras haya líneas por leer, se imprimen en consola
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            // Imprime el error si ocurre una excepción al leer el archivo
            e.printStackTrace();
        }
    }
}
