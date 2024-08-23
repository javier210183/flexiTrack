package flexitrack.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public abstract class Vehiculo implements Serializable {
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected int año;
    protected String tipoDeRuedas;
    protected LocalDate ultimoMantenimiento;
    protected LocalDate ultimaITV;

    // Constructor
    // Constructor completo
    public Vehiculo(String matricula, String marca, String modelo, int año, String tipoDeRuedas, LocalDate ultimaITV, LocalDate ultimoMantenimiento) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.tipoDeRuedas = tipoDeRuedas;
        this.ultimaITV = ultimaITV;
        this.ultimoMantenimiento = ultimoMantenimiento;
    }

    public abstract void calcularITV();

  


    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }





    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                '}';
    }
    public String toCSV() {
        return getClass().getSimpleName() + "," + matricula + "," + marca + "," + modelo + "," + año;
    }
    public static Vehiculo fromCSV(String csv) {
        String[] parts = csv.split(",");
        // Asegúrate de que tienes suficientes partes para evitar ArrayIndexOutOfBoundsException
        if (parts.length < 7) {  // Cambia este número según la cantidad de datos que esperes en cada línea
            System.err.println("CSV mal formado: " + csv);
            return null;
        }
        String type = parts[0];
        String matricula = parts[1];
        String marca = parts[2];
        String modelo = parts[3];
        int año = Integer.parseInt(parts[4]);
        String tipoDeRuedas = parts[5];
        LocalDate ultimaITV = !parts[6].equals("null") ? LocalDate.parse(parts[6]) : null;

        if (type.equals("Coche")) {
            return new Coche(matricula, marca, modelo, año, tipoDeRuedas, ultimaITV);
        } else if (type.equals("Moto")) {
            return new Moto(matricula, marca, modelo, año, tipoDeRuedas, ultimaITV);
        }
        return null;
    }

    public boolean necesitaMantenimiento() {
        if (ultimoMantenimiento == null) {
            System.out.println("La fecha del último mantenimiento no está establecida.");
            return false;
        }
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(ultimoMantenimiento, hoy);
        int mesesDesdeUltimoMantenimiento = periodo.getYears() * 12 + periodo.getMonths();

        // Suponemos que el mantenimiento es necesario cada 12 meses
        return mesesDesdeUltimoMantenimiento >= 12;
    }

}
