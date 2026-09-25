package co.edu.uniquindio.poo.Model;

public class ValoracionFisicaFactory implements ServicioAdicionalFactory {
    @Override
    public ValoracionFisica crearValoracionFisica() {
        return new ValoracionFisica("VF-001", "Valoración Inicial", "Evaluación antropométrica", 50000.0, true, 70.0, 1.75, "Sin novedades médicas");
    }

    @Override
    public AsesoriaNutricional crearAsesoriaNutricional() { return null; }

    @Override
    public EntrenamientoPersonalizado crearEntrenamientoPerson() { return null; }

    @Override
    public ClasesEspeciales crearClasesEspeciales() { return null; }
}