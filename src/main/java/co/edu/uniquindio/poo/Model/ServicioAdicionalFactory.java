package co.edu.uniquindio.poo.Model;

public interface ServicioAdicionalFactory {
    ValoracionFisica crearValoracionFisica();
    AsesoriaNutricional crearAsesoriaNutricional();
    EntrenamientoPersonalizado crearEntrenamientoPerson();
    ClasesEspeciales crearClasesEspeciales();
}