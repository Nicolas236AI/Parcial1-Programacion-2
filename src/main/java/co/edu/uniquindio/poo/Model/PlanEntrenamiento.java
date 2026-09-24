package co.edu.uniquindio.poo.Model;

public abstract class PlanEntrenamiento implements Plan {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlanEntrenamiento estado;

    /**
     * metodo constructor de PlanEntrenamiento
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     */
    public PlanEntrenamiento(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlanEntrenamiento estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
    }

    /**
     * Metodo que sirve para calcular el valor final del plan de entrenamiento
     * @return double con el valor final
     */
    public abstract double calcularValorFinalPlan();

    /**
     * Sobreescritura del metodo de la interfaz que sirve para clonar el plan  de entenamiento
     * @return
     */
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