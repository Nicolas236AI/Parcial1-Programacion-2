package co.edu.uniquindio.poo.Model;

public class ClasesEspeciales extends ServicioAdicional {
    private String horario;

    /**
     * Método constructor de ClasesEspeciales
     */
    public ClasesEspeciales(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad, String horario) {
        super(codigo, nombre, descripcion, precio, disponibilidad);
        this.horario = horario;
    }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    @Override
    public String toString() {
        return "ClasesEspeciales{" + super.toString() + ", horario='" + horario + "'}";
    }
}