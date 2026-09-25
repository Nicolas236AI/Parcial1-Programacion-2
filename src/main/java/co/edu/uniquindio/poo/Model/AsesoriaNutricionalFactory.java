package co.edu.uniquindio.poo.Model;

public class AsesoriaNutricionalFactory implements ServicioAdicionalFactory {
    @Override
    public ValoracionFisica crearValoracionFisica() { return null; }

    @Override
    public AsesoriaNutricional crearAsesoriaNutricional() {
        return new AsesoriaNutricional("AN-001", "Nutrición Deportiva", "Plan de alimentación hipercalórica", 60000.0, true, 2500, "Hipercalórica", 75);
    }

    @Override
    public EntrenamientoPersonalizado crearEntrenamientoPerson() { return null; }

    @Override
    public ClasesEspeciales crearClasesEspeciales() { return null; }
}