package co.edu.uniquindio.poo.Model;

public class EntrenamientoPersonalizadoFactory implements ServicioAdicionalFactory {
    @Override
    public ValoracionFisica crearValoracionFisica() { return null; }

    @Override
    public AsesoriaNutricional crearAsesoriaNutricional() { return null; }

    @Override
    public EntrenamientoPersonalizado crearEntrenamientoPerson() {
        return new EntrenamientoPersonalizado("EP-001", "Personal Trainer 1 a 1", "Entrenamiento dirigido de fuerza", 80000.0, true, 60, "Alta");
    }

    @Override
    public ClasesEspeciales crearClasesEspeciales() { return null; }
}