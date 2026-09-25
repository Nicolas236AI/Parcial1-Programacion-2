package co.edu.uniquindio.poo.Model;

public class ClasesEspecialesFactory implements ServicioAdicionalFactory {
    @Override
    public ValoracionFisica crearValoracionFisica() { return null; }

    @Override
    public AsesoriaNutricional crearAsesoriaNutricional() { return null; }

    @Override
    public EntrenamientoPersonalizado crearEntrenamientoPerson() { return null; }

    @Override
    public ClasesEspeciales crearClasesEspeciales() {
        return new ClasesEspeciales("CE-001", "Clase de Spinning", "Clase grupal de alta intensidad en bicicleta", 30000.0, true, "Lunes y Miércoles 6:00 PM");
    }
}