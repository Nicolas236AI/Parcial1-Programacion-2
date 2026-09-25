package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.Model.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PruebaUnitaria {

    /**
     * Prueba que valida el cálculo del valor final de un plan personalizado sumando las sesiones adicionales.
     */
    @Test
    public void testCalcularValorFinalPlanPersonalizado() {
        PlanEntrenamiento planPersonalizado = new Personalizado(
                "PER-01", "Entrenamiento Funcional", "Personalizado 1 a 1",
                3, 150000.0, EstadoPlanEntrenamiento.ACTIVO, 4, "Fuerza", true
        );

        // Cálculo: (150000 * 3 meses) + (4 sesiones * 20000) = 530000.0
        double valorEsperado = 530000.0;
        assertEquals(valorEsperado, planPersonalizado.calcularValorFinalPlan(), 0.01);
    }

    /**
     * Prueba que valida el registro exitoso y la búsqueda de clientes por número de teléfono en SmartGym.
     */
    @Test
    public void testRegistrarYBuscarClienteSmartGym() {
        SmartGym gym = SmartGym.getInstance();
        Cliente cliente = new Cliente(
                "Ana Gómez", "123456789", "3111234567",
                "ana@email.com", 22, new Date(), gym
        );

        boolean registrado = gym.registrarCliente(cliente);
        assertTrue(registrado);

        Cliente clienteEncontrado = gym.buscarClientePorTelefono("3111234567");
        assertNotNull(clienteEncontrado);
        assertEquals("Ana Gómez", clienteEncontrado.getNombreCompleto());
    }

    /**
     * Prueba que valida si el número de teléfono de un cliente corresponde a un número perfecto.
     */
    @Test
    public void testEsNumeroPerfectoTelefonoCliente() {
        SmartGym gym = SmartGym.getInstance();

        Cliente cliente = new Cliente(
                "Carlos Pérez", "123456789", "6",
                "carlos@email.com", 25, new Date(), gym
        );

        long telefonoNumerico = Long.parseLong(cliente.getTelefono());

        assertTrue(gym.esNumeroPerfecto((int) telefonoNumerico));
    }
}