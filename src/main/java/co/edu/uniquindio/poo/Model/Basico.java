package co.edu.uniquindio.poo.Model;

public class Basico extends PlanEntrenamiento {
    private boolean accesoZona;

    /**
     * metodo constructor de Basico
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @param accesoZona
     */
    public Basico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlanEntrenamiento estado, boolean accesoZona) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        this.accesoZona = accesoZona;
    }

    /**
     * sobreescritura del metodo abstracto declarado en PlanEntrenamiento que sirve para calcular el valor final de este tipo de plan
     * @return
     */
    @Override
    public double calcularValorFinalPlan() {
        return getValorMensual() * getDuracionMeses();
    }

    /**
     * Clona el plan basico actual aplicando el patron Prototype
     * @return un nuevo objeto Plan clonado con los mismos atributos
     */
    @Override
    public Plan clonar() {
        return new Basico(getCodigo(), getNombre(), getDescripcion(), getDuracionMeses(), getValorMensual(), getEstado(), this.accesoZona);
    }

    public boolean isAccesoZona() {
        return accesoZona;
    }

    public void setAccesoZona(boolean accesoZona) {
        this.accesoZona = accesoZona;
    }

    @Override
    public String toString() {
        return "Basico{" +
                super.toString() +
                ", accesoZona=" + accesoZona +
                '}';
    }
}