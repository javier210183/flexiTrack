package flexitrack.model;

import java.time.LocalDate;

public class Coche extends Vehiculo {
    private String tipoDeRuedas; // Atributo específico de Coche
    private LocalDate ultimaITV;
    private LocalDate ultimoMantenimiento;

    // Constructor que incluye la última ITV y el último mantenimiento
    public Coche(String matricula, String marca, String modelo, int año, String tipoDeRuedas, LocalDate ultimaITV) {
        super(matricula, marca, modelo, año, tipoDeRuedas, ultimaITV, LocalDate.now());  // Asumiendo que el mantenimiento se inicializa al momento
    }


    @Override
    public void calcularITV() {
        if (ultimaITV == null) {
            System.out.println("Fecha de la última ITV no está definida.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        int edadCoche = hoy.getYear() - getAño();

        if (edadCoche < 4) {
            System.out.println("El coche aún no requiere ITV.");
        } else {
            LocalDate proximaITV = edadCoche <= 10 ? ultimaITV.plusYears(2) : ultimaITV.plusYears(1);
            System.out.println("La próxima ITV del coche es el " + proximaITV);
        }
    }


    public String getTipoDeRuedas() {
        return tipoDeRuedas;
    }

    public void setTipoDeRuedas(String tipoDeRuedas) {
        this.tipoDeRuedas = tipoDeRuedas;
    }

    public LocalDate getUltimaITV() {
        return ultimaITV;
    }

    public void setUltimaITV(LocalDate ultimaITV) {
        this.ultimaITV = ultimaITV;
    }

    public LocalDate getUltimoMantenimiento() {
        return ultimoMantenimiento;
    }

    public void setUltimoMantenimiento(LocalDate ultimoMantenimiento) {
        this.ultimoMantenimiento = ultimoMantenimiento;
    }



    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", tipoDeRuedas='" + tipoDeRuedas + '\'' +
                '}';
    }
    @Override
    public String toCSV() {
        return super.toCSV() + "," + tipoDeRuedas;
    }
}
