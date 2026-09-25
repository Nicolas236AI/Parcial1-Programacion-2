package co.edu.uniquindio.poo.Model;

public abstract class PlanEntrenamiento implements Plan {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlanEntrenamiento estado;

    public PlanEntrenamiento(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlanEntrenamiento estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
    }

    /**
     * Método abstracto que es implementado por las subclases (Basico, Personalizado, Premium)
     * para calcular el valor total según la duración en meses y los recargos/descuentos aplicables.
     */
    public abstract double calcularValorFinalPlan();

    @Override
    public abstract Plan clonar();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public EstadoPlanEntrenamiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlanEntrenamiento estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PlanEntrenamiento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracionMeses=" + duracionMeses +
                ", valorMensual=" + valorMensual +
                '}';
    }
}