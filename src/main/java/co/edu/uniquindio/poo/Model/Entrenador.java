package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Entrenador {
    private String nombreCompleto;
    private String documentoIdentidad;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private double tarifaSesion;
    private SmartGym ownedBySmartGym;

    /**
     * metodo constructor de Entrenador
     * @param nombreCompleto
     * @param documentoIdentidad
     * @param telefono
     * @param correoElectronico
     * @param fechaRegistro
     * @param tarifaSesion
     * @param ownedBySmartGym
     */
    public Entrenador(String nombreCompleto, String documentoIdentidad, String telefono, String correoElectronico, LocalDate fechaRegistro, double tarifaSesion, SmartGym ownedBySmartGym) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.tarifaSesion = tarifaSesion;
        this.ownedBySmartGym = ownedBySmartGym;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    public SmartGym getOwnedBySmartGym() {
        return ownedBySmartGym;
    }

    public void setOwnedBySmartGym(SmartGym ownedBySmartGym) {
        this.ownedBySmartGym = ownedBySmartGym;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", tarifaSesion=" + tarifaSesion +
                '}';
    }
}