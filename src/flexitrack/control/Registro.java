package flexitrack.control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Registro {
    private static final String DB_FILE = "usuarios.txt";

    public boolean registrarUsuario(String nombre, String email) {

        try (FileWriter fw = new FileWriter(DB_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nombre + "," + email);
            return true; // El usuario fue registrado con éxito
        } catch (IOException e) {
            System.err.println("Hubo un error al escribir en el archivo de base de datos.");
            e.printStackTrace();
            return false; // Hubo un error al registrar el usuario
        }
    }
}
