package flexitrack.model;

import java.time.LocalDate;

public class Alerta {
    private LocalDate fechaAlerta;
    private String mensaje;
    private Vehiculo vehiculoAsociado;
    private TipoMantenimiento tipoMantenimiento;

    // Constructor
    public Alerta(LocalDate fechaAlerta, String mensaje, Vehiculo vehiculoAsociado, TipoMantenimiento tipoMantenimiento) {
        this.fechaAlerta = fechaAlerta;
        this.mensaje = mensaje;
        this.vehiculoAsociado = vehiculoAsociado;
        this.tipoMantenimiento = tipoMantenimiento;
    }

    // Getters y setters
    public LocalDate getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(LocalDate fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Vehiculo getVehiculoAsociado() {
        return vehiculoAsociado;
    }

    public void setVehiculoAsociado(Vehiculo vehiculoAsociado) {
        this.vehiculoAsociado = vehiculoAsociado;
    }

    public TipoMantenimiento getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(TipoMantenimiento tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }
    public void notificarUsuario() {
        System.out.println("ALERTA: " + mensaje + " para el vehículo con matrícula " + vehiculoAsociado.getMatricula() + " el día " + fechaAlerta);
    }
    public void calcularProximaAlerta() {
        switch (tipoMantenimiento) {
            case ITV:
                fechaAlerta = fechaAlerta.plusYears(1);  // ITV anual
                break;
            case SEGURO:
                fechaAlerta = fechaAlerta.plusYears(1);  // Renovación anual del seguro
                break;
            case CAMBIO_ACEITE:
                fechaAlerta = fechaAlerta.plusMonths(6); // Cambio de aceite cada 6 meses
                break;
            case CAMBIO_RUEDAS:
                fechaAlerta = fechaAlerta.plusMonths(12); // Cambio de ruedas cada año
                break;
        }
        mensaje = "Próxima " + tipoMantenimiento + " necesaria en la fecha: " + fechaAlerta;
    }

}
