package co.edu.uniquindio.poo.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmartGym {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    private static SmartGym instance;

    private List<Cliente> clientes;
    private List<Entrenador> entrenadores;
    private List<PlanEntrenamiento> planes;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<Inscripcion> inscripciones;

    /**
     * Constructor privado para el patrón Singleton
     */
    private SmartGym(String nombreComercial, String nit, String direccion, String telefono, String correo, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
        this.clientes = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    /**
     * Método estático para obtener la instancia única de SmartGym (Singleton)
     */
    public static SmartGym getInstance() {
        if (instance == null) {
            instance = new SmartGym("SmartGym Armenia", "900123456-1", "Calle 12N", "7350000", "contacto@smartgym.com", "www.smartgym.com");
        }
        return instance;
    }

    /**
     * Registra un cliente en el sistema asignándole su pertenencia (ownedBySmartGym)
     */
    public boolean registrarCliente(Cliente cliente) {
        if (buscarClientePorTelefono(cliente.getTelefono()) == null) {
            cliente.setOwnedBySmartGym(this);
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    /**
     * Busca un cliente por su número de teléfono
     */
    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : clientes) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Valida si un número es perfecto (ejercicio de lógica solicitado en perfil)
     */
    public boolean esNumeroPerfecto(int numero) {
        if (numero <= 0) return false;
        int suma = 0;
        for (int i = 1; i < numero; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }

    /**
     * Calcula los ingresos totales en un periodo de tiempo determinado
     */
    public double calcularIngresosPeriodo(Date inicio, Date fin) {
        double total = 0.0;
        for (Inscripcion inscripcion : inscripciones) {
            if (!inscripcion.getFecha().isBefore(inicio.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()) &&
                    !inscripcion.getFecha().isAfter(fin.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate())) {
                total += inscripcion.cacularPagoTotal();
            }
        }
        return total;
    }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPaginaWeb() { return paginaWeb; }
    public void setPaginaWeb(String paginaWeb) { this.paginaWeb = paginaWeb; }

    public List<Cliente> getClientes() { return clientes; }
    public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }

    public List<Entrenador> getEntrenadores() { return entrenadores; }
    public void setEntrenadores(List<Entrenador> entrenadores) { this.entrenadores = entrenadores; }

    public List<PlanEntrenamiento> getPlanes() { return planes; }
    public void setPlanes(List<PlanEntrenamiento> planes) { this.planes = planes; }

    public List<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) { this.serviciosAdicionales = serviciosAdicionales; }

    public List<Inscripcion> getInscripciones() { return inscripciones; }
    public void setInscripciones(List<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }
}