package co.edu.uniquindio.poo.Model;

public class EntrenamientoPersonalizado extends ServicioAdicional {
    private int duracion;
    private String exigencia;

    /**
     * Método constructor de EntrenamientoPersonalizado
     */
    public EntrenamientoPersonalizado(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad, int duracion, String exigencia) {
        super(codigo, nombre, descripcion, precio, disponibilidad);
        this.duracion = duracion;
        this.exigencia = exigencia;
    }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public String getExigencia() { return exigencia; }
    public void setExigencia(String exigencia) { this.exigencia = exigencia; }

    @Override
    public String toString() {
        return "EntrenamientoPersonalizado{" + super.toString() + ", duracion=" + duracion + "min}";
    }
}