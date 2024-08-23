package flexitrack.model;

import java.time.LocalDate;
import java.time.Period;

public class Moto extends Vehiculo {
    private String tipoDeRuedas; // Atributo específico de Moto


    public Moto(String matricula, String marca, String modelo, int año, String tipoDeRuedas, LocalDate ultimaITV) {
        super(matricula, marca, modelo, año, tipoDeRuedas, ultimaITV, LocalDate.now());  // Asumiendo que el mantenimiento se inicializa al momento
    }


    @Override
    public void calcularITV() {
        if (ultimaITV == null) {
            System.out.println("Fecha de la última ITV no está definida.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(ultimaITV, hoy);
        int añosDesdeUltimaITV = periodo.getYears();

        if (añosDesdeUltimaITV >= 2) {
            LocalDate proximaITV = ultimaITV.plusYears(2);
            System.out.println("La próxima ITV de la moto es el " + proximaITV);
        } else {
            System.out.println("La moto aún no requiere ITV.");
        }
    }



    // Getters y setters para el atributo específico de Moto
    public String getTipoDeRuedas() {
        return tipoDeRuedas;
    }

    public void setTipoDeRuedas(String tipoDeRuedas) {
        this.tipoDeRuedas = tipoDeRuedas;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", tipoDeRuedas='" + tipoDeRuedas + '\'' +
                '}';
    }
    @Override
    public String toCSV() {
        return super.toCSV() + "," + tipoDeRuedas + "," + (ultimaITV != null ? ultimaITV.toString() : "n/a");
    }
}
