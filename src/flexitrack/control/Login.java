package flexitrack.control;

import java.io.*;
import java.util.*;

public class Login {
    private static final String DB_FILE = "usuarios.txt";
    private String usuarioLogueado = null; // Mantener el estado de sesión

    public boolean iniciarSesion(String email) {
        try (Scanner scanner = new Scanner(new File(DB_FILE))) {
            while (scanner.hasNextLine()) {
                String[] credentials = scanner.nextLine().split(",");
                if (credentials.length > 1 && credentials[1].trim().equals(email)) {
                    usuarioLogueado = email; // Establecer usuario como logueado
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.err.println("El archivo de base de datos no fue encontrado.");
            e.printStackTrace();
            return false;
        }
    }

    public void cerrarSesion() {
        if (usuarioLogueado != null) {
            System.out.println("Sesión cerrada con éxito.");
            usuarioLogueado = null; // Limpiar el estado de sesión
        } else {
            System.out.println("No hay sesión activa para cerrar.");
        }
    }

    // Método para verificar si hay una sesión activa
    public boolean haySesionActiva() {
        return usuarioLogueado != null;
    }
}
