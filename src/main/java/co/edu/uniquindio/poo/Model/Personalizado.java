package co.edu.uniquindio.poo.Model;

public class Personalizado extends PlanEntrenamiento {
    private int cantSesiones;
    private String especialidadReq;
    private boolean accesoZona;

    /**
     * metodo constructor de Personalizado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @param cantSesiones
     * @param especialidadReq
     * @param accesoZona
     */
    public Personalizado(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlanEntrenamiento estado, int cantSesiones, String especialidadReq, boolean accesoZona) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        this.cantSesiones = cantSesiones;
        this.especialidadReq = especialidadReq;
        this.accesoZona = accesoZona;
    }
    /**
     * sobreescritura del metodo abstracto declarado en PlanEntrenamiento que sirve para calcular el valor final de este tipo de plan
     * @return
     */

    @Override
    public double calcularValorFinalPlan() {
        double subtotal = getValorMensual() * getDuracionMeses();
        return subtotal + (cantSesiones * 20000);
    }

    /**
     * Clona el plan personalizado actual aplicando el patron Prototype
     * @return un nuevo objeto Plan clonado con los mismos atributos
     */
    @Override
    public Plan clonar() {
        return new Personalizado(getCodigo(), getNombre(), getDescripcion(), getDuracionMeses(), getValorMensual(), getEstado(), this.cantSesiones, this.especialidadReq, this.accesoZona);
    }

    public int getCantSesiones() {
        return cantSesiones;
    }

    public void setCantSesiones(int cantSesiones) {
        this.cantSesiones = cantSesiones;
    }

    public String getEspecialidadReq() {
        return especialidadReq;
    }

    public void setEspecialidadReq(String especialidadReq) {
        this.especialidadReq = especialidadReq;
    }

    public boolean isAccesoZona() {
        return accesoZona;
    }

    public void setAccesoZona(boolean accesoZona) {
        this.accesoZona = accesoZona;
    }

    @Override
    public String toString() {
        return "Personalizado{" +
                super.toString() +
                ", cantSesiones=" + cantSesiones +
                ", especialidadReq='" + especialidadReq + '\'' +
                ", accesoZona=" + accesoZona +
                '}';
    }
}