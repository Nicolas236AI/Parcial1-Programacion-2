package co.edu.uniquindio.poo.Model;

public class Premium extends PlanEntrenamiento {
    private boolean accesoZona;
    private boolean accesoVip;

    /**
     * metodo constructor de Premium
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @param accesoZona
     * @param accesoVip
     */
    public Premium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlanEntrenamiento estado, boolean accesoZona, boolean accesoVip) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        this.accesoZona = accesoZona;
        this.accesoVip = accesoVip;
    }
    /**
     * sobreescritura del metodo abstracto declarado en PlanEntrenamiento que sirve para calcular el valor final de este tipo de plan
     * @return
     */

    @Override
    public double calcularValorFinalPlan() {
        double subtotal = getValorMensual() * getDuracionMeses();
        return subtotal * 0.95;
    }

    /**
     * Clona el plan premium actual aplicando el patron Prototype
     * @return un nuevo objeto Plan clonado con los mismos atributos
     */
    @Override
    public Plan clonar() {
        return new Premium(getCodigo(), getNombre(), getDescripcion(), getDuracionMeses(), getValorMensual(), getEstado(), this.accesoZona, this.accesoVip);
    }

    public boolean isAccesoZona() {
        return accesoZona;
    }

    public void setAccesoZona(boolean accesoZona) {
        this.accesoZona = accesoZona;
    }

    public boolean isAccesoVip() {
        return accesoVip;
    }

    public void setAccesoVip(boolean accesoVip) {
        this.accesoVip = accesoVip;
    }

    @Override
    public String toString() {
        return "Premium{" +
                super.toString() +
                ", accesoZona=" + accesoZona +
                ", accesoVip=" + accesoVip +
                '}';
    }
}