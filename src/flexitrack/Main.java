package flexitrack;

import flexitrack.control.Login;
import flexitrack.control.Registro;
import flexitrack.model.Coche;
import flexitrack.model.Moto;
import flexitrack.model.Vehiculo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String VEHICULOS_FILE = "vehiculos.txt";
    private static List<Vehiculo> vehiculosRegistrados = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registro registro = new Registro();
        Login login = new Login();
        cargarVehiculos();

        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido a FlexiTrack");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese su email: ");
                    String emailRegistro = scanner.nextLine();
                    boolean registrado = registro.registrarUsuario(nombre, emailRegistro);
                    if (registrado) {
                        System.out.println("Usuario registrado con éxito.");
                    } else {
                        System.out.println("No se pudo registrar al usuario.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese su email para iniciar sesión: ");
                    String emailLogin = scanner.nextLine();
                    boolean autenticado = login.iniciarSesion(emailLogin);
                    if (autenticado) {
                        System.out.println("Inicio de sesión exitoso.");
                        boolean sesionActiva = true;
                        while (sesionActiva) {
                            System.out.println("Submenú de usuario:");
                            System.out.println("1. Registrar nuevo vehículo");
                            System.out.println("2. Ver vehículos registrados");
                            System.out.println("3. Establecer mantenimiento para vehículo");
                            System.out.println("4. Cerrar sesión");
                            System.out.print("Seleccione una opción: ");

                            int opcionUsuario = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea

                            switch (opcionUsuario) {
                                case 1:
                                    registrarVehiculo(scanner);
                                    break;
                                case 2:
                                    verVehiculos();
                                    break;
                                case 3:
                                    establecerMantenimientoParaVehiculo(scanner);
                                    break;
                                case 4:
                                    System.out.println("Cerrando sesión...");
                                    sesionActiva = false;
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                    break;
                case 3:
                    establecerMantenimientoParaVehiculo(scanner);
                    break;

            }
        }

        scanner.close();
    }
    private static void registrarVehiculo(Scanner scanner) {
        System.out.println("Registro de nuevo vehículo.");
        System.out.println("Seleccione el tipo de vehículo:");
        System.out.println("1. Coche");
        System.out.println("2. Moto");
        System.out.print("Tipo de vehículo: ");

        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese año: ");
        int año = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        // Nuevo código para pedir la fecha de la ITV
        System.out.print("Ingrese la fecha de la última ITV (formato AAAA-MM-DD): ");
        String fechaITVStr = scanner.nextLine();
        LocalDate fechaITV;
        try {
            fechaITV = LocalDate.parse(fechaITVStr);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Registro cancelado.");
            return;
        }

        System.out.print("Ingrese el tipo de ruedas: ");
        String tipoDeRuedas = scanner.nextLine();

        Vehiculo vehiculo = null;
        if (tipo == 1) {
            vehiculo = new Coche(matricula, marca, modelo, año, tipoDeRuedas, fechaITV);
        } else if (tipo == 2) {
            vehiculo = new Moto(matricula, marca, modelo, año, tipoDeRuedas, fechaITV);  // Ahora pasando fechaITV
        }

        if (vehiculo != null) {
            vehiculosRegistrados.add(vehiculo);
            guardarVehiculos(); // Llamar a esta función después de registrar un vehículo
            System.out.println("Vehículo registrado con éxito.");
        } else {
            System.out.println("Tipo de vehículo no válido.");
        }
    }



    private static void verVehiculos() {
        System.out.println("Vehículos registrados:");
        for (Vehiculo vehiculo : vehiculosRegistrados) {
            System.out.println(vehiculo);
        }
    }


    private static void establecerMantenimientoParaVehiculo(Scanner scanner) {
        System.out.println("Elija un vehículo para el mantenimiento:");
        int indice = 1;
        for (Vehiculo v : vehiculosRegistrados) {
            System.out.println(indice++ + ". " + v);
        }
        System.out.print("Seleccione el número del vehículo: ");
        int eleccionVehiculo = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer del scanner

        if (eleccionVehiculo < 1 || eleccionVehiculo > vehiculosRegistrados.size()) {
            System.out.println("Elección no válida");
            return;
        }

        Vehiculo vehiculo = vehiculosRegistrados.get(eleccionVehiculo - 1);
        if (vehiculo instanceof Coche) {
            Coche coche = (Coche) vehiculo;
            coche.calcularITV();
            if (coche.necesitaMantenimiento()) {
                System.out.println("El coche necesita mantenimiento.");
            } else {
                System.out.println("El coche no necesita mantenimiento actualmente.");
            }
        } else {
            System.out.println("Solo los coches tienen implementada la lógica de mantenimiento actualmente.");
        }
    }

    private static void guardarVehiculos() {
        try (PrintWriter out = new PrintWriter(new FileWriter(VEHICULOS_FILE))) {
            for (Vehiculo v : vehiculosRegistrados) {
                out.println(v.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los vehículos: " + e.getMessage());
        }
    }


    private static void cargarVehiculos() {
        File file = new File(VEHICULOS_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Vehiculo v = Vehiculo.fromCSV(line);
                    if (v != null) {
                        vehiculosRegistrados.add(v);
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Archivo no encontrado: " + e.getMessage());
            }
        }
    }



}

