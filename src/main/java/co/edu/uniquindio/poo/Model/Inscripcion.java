package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;
import java.util.List;

public class Inscripcion {
    private String codigo;
    private LocalDate fecha;
    private EstadoPlanEntrenamiento estado;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private List<ServicioAdicional> listaServiciosAdicionales;
    private Entrenador entrenador;
    private SmartGym ownedBySmartGym;

    /**
     * Método constructor privado para el patrón Builder
     */
    private Inscripcion(Builder builder) {
        this.codigo = builder.codigo;
        this.fecha = builder.fecha;
        this.estado = builder.estado;
        this.cliente = builder.cliente;
        this.plan = builder.plan;
        this.listaServiciosAdicionales = builder.listaServiciosAdicionales;
        this.entrenador = builder.entrenador;
        this.ownedBySmartGym = builder.ownedBySmartGym;
    }

    /**
     * Calcula el pago total sumando el valor final del plan (el cual ya incluye su duración en meses)
     * y los precios de los servicios adicionales seleccionados.
     * @return double con el valor total a pagar
     */
    public double calcularPagoTotal() {
        double valorPlan = (plan != null) ? plan.calcularValorFinalPlan() : 0.0;
        double valorServicios = 0.0;

        if (listaServiciosAdicionales != null) {
            for (ServicioAdicional servicio : listaServiciosAdicionales) {
                if (servicio != null) {
                    valorServicios += servicio.getPrecio();
                }
            }
        }

        return valorPlan + valorServicios;
    }

    /**
     * Método para mantener compatibilidad con llamados existentes en SmartGym
     */
    public double cacularPagoTotal() {
        return calcularPagoTotal();
    }

    /**
     * Obtiene la fecha de finalización calculada automáticamente sumando los meses del plan a la fecha de inicio
     * @return LocalDate de finalización
     */
    public LocalDate getFechaFin() {
        if (fecha != null && plan != null && plan.getDuracionMeses() > 0) {
            return fecha.plusMonths(plan.getDuracionMeses());
        }
        return fecha;
    }

    /**
     * Clase Builder interna para la construcción flexible de Inscripcion
     */
    public static class Builder {
        private String codigo;
        private LocalDate fecha;
        private EstadoPlanEntrenamiento estado;
        private Cliente cliente;
        private PlanEntrenamiento plan;
        private List<ServicioAdicional> listaServiciosAdicionales;
        private Entrenador entrenador;
        private SmartGym ownedBySmartGym;

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder estado(EstadoPlanEntrenamiento estado) {
            this.estado = estado;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder plan(PlanEntrenamiento plan) {
            this.plan = plan;
            return this;
        }

        public Builder listaServiciosAdicionales(List<ServicioAdicional> listaServiciosAdicionales) {
            this.listaServiciosAdicionales = listaServiciosAdicionales;
            return this;
        }

        public Builder entrenador(Entrenador entrenador) {
            this.entrenador = entrenador;
            return this;
        }

        public Builder ownedBySmartGym(SmartGym ownedBySmartGym) {
            this.ownedBySmartGym = ownedBySmartGym;
            return this;
        }

        public Inscripcion build() {
            return new Inscripcion(this);
        }
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoPlanEntrenamiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlanEntrenamiento estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanEntrenamiento getPlan() {
        return plan;
    }

    public void setPlan(PlanEntrenamiento plan) {
        this.plan = plan;
    }

    public List<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
    }

    public void setListaServiciosAdicionales(List<ServicioAdicional> listaServiciosAdicionales) {
        this.listaServiciosAdicionales = listaServiciosAdicionales;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public SmartGym getOwnedBySmartGym() {
        return ownedBySmartGym;
    }

    public void setOwnedBySmartGym(SmartGym ownedBySmartGym) {
        this.ownedBySmartGym = ownedBySmartGym;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", pagoTotal=" + calcularPagoTotal() +
                '}';
    }
}