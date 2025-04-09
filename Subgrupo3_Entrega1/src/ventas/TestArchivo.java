package ventas;

import java.io.*;

public class TestArchivo {
    public static void main(String[] args) {
        // Ruta del archivo de ventas
        File archivo = new File("ventas/1001234567.txt");

        // Verifica si el archivo existe antes de leerlo
        if (!archivo.exists()) {
            System.out.println("❌ El archivo no existe: " + archivo.getAbsolutePath());
            return;
        }

        System.out.println("📂 Leyendo archivo: " + archivo.getName());

        // Lectura del archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);// Imprimimos cada línea del archivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
