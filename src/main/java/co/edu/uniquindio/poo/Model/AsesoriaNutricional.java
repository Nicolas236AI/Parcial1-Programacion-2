package co.edu.uniquindio.poo.Model;

public class AsesoriaNutricional extends ServicioAdicional {
    private int caloriasObjetivo;
    private String tipoDieta;
    private int pesoMeta;

    /**
     * Método constructor de AsesoriaNutricional
     */
    public AsesoriaNutricional(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad, int caloriasObjetivo, String tipoDieta, int pesoMeta) {
        super(codigo, nombre, descripcion, precio, disponibilidad);
        this.caloriasObjetivo = caloriasObjetivo;
        this.tipoDieta = tipoDieta;
        this.pesoMeta = pesoMeta;
    }

    public int getCaloriasObjetivo() { return caloriasObjetivo; }
    public void setCaloriasObjetivo(int caloriasObjetivo) { this.caloriasObjetivo = caloriasObjetivo; }

    public String getTipoDieta() { return tipoDieta; }
    public void setTipoDieta(String tipoDieta) { this.tipoDieta = tipoDieta; }

    public int getPesoMeta() { return pesoMeta; }
    public void setPesoMeta(int pesoMeta) { this.pesoMeta = pesoMeta; }

    @Override
    public String toString() {
        return "AsesoriaNutricional{" + super.toString() + ", tipoDieta='" + tipoDieta + "'}";
    }
}