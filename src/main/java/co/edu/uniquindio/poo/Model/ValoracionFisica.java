package co.edu.uniquindio.poo.Model;

public class ValoracionFisica extends ServicioAdicional {
    private double pesoActual;
    private double estatura;
    private String observacionMedica;

    /**
     * Método constructor de ValoracionFisica (hereda de ServicioAdicional)
     * @param codigo código único del servicio
     * @param nombre nombre del servicio
     * @param descripcion descripción detallada
     * @param precio costo del servicio
     * @param disponibilidad estado de disponibilidad del servicio
     * @param pesoActual peso actual registrado del cliente
     * @param estatura estatura registrada del cliente
     * @param observacionMedica observaciones médicas o notas clínicas
     */
    public ValoracionFisica(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad, double pesoActual, double estatura, String observacionMedica) {
        super(codigo, nombre, descripcion, precio, disponibilidad);
        this.pesoActual = pesoActual;
        this.estatura = estatura;
        this.observacionMedica = observacionMedica;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String getObservacionMedica() {
        return observacionMedica;
    }

    public void setObservacionMedica(String observacionMedica) {
        this.observacionMedica = observacionMedica;
    }

    @Override
    public String toString() {
        return "ValoracionFisica{" +
                super.toString() +
                ", pesoActual=" + pesoActual +
                ", estatura=" + estatura +
                ", observacionMedica='" + observacionMedica + '\'' +
                '}';
    }
}