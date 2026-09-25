package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            inicializarDatosDemo();

            // Ruta corregida según tu carpeta de recursos (resources/View/)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VentanaPrincipal.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("SmartGym - Sistema de Gestión de Gimnasio");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(900);
            primaryStage.setMinHeight(600);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga información básica en la instancia Singleton de SmartGym
     * para verificar el funcionamiento de las vistas y tablas.
     */
    private void inicializarDatosDemo() {
        SmartGym gimnasio = SmartGym.getInstance();

        // 1. Registro de Entrenadores
        Entrenador e1 = new Entrenador("Carlos Pérez", "10948573", "3104567890", "carlos@smartgym.com", LocalDate.now(), 50000.0, gimnasio);
        Entrenador e2 = new Entrenador("Ana Gómez", "10948574", "3119876543", "ana@smartgym.com", LocalDate.now(), 60000.0, gimnasio);
        gimnasio.getEntrenadores().add(e1);
        gimnasio.getEntrenadores().add(e2);

        // 2. Registro de Clientes
        Cliente c1 = new Cliente("Laura Restrepo", "1094123456", "3001234567", "laura@gmail.com", 25, new Date(), gimnasio);
        Cliente c2 = new Cliente("Mateo Hoyos", "1094654321", "3017654321", "mateo@gmail.com", 30, new Date(), gimnasio);
        gimnasio.registrarCliente(c1);
        gimnasio.registrarCliente(c2);

        // 3. Registro de Planes
        PlanEntrenamiento p1 = new Basico("PLN-01", "Plan Básico Mensual", "Acceso a zona de pesas", 1, 80000.0, EstadoPlanEntrenamiento.ACTIVO, true);
        PlanEntrenamiento p2 = new Premium("PLN-02", "Plan Premium Anual", "Acceso total a instalaciones y zonas VIP", 12, 120000.0, EstadoPlanEntrenamiento.ACTIVO, true, true);
        gimnasio.getPlanes().add(p1);
        gimnasio.getPlanes().add(p2);

        // 4. Registro de Servicios Adicionales mediante Factory Pattern
        ValoracionFisicaFactory vfFactory = new ValoracionFisicaFactory();
        AsesoriaNutricionalFactory anFactory = new AsesoriaNutricionalFactory();

        ServicioAdicional s1 = vfFactory.crearValoracionFisica();
        s1.setCodigo("SRV-01");
        s1.setNombre("Valoración Antropométrica");
        s1.setPrecio(35000.0);
        s1.setDisponibilidad(true);

        ServicioAdicional s2 = anFactory.crearAsesoriaNutricional();
        s2.setCodigo("SRV-02");
        s2.setNombre("Plan Alimenticio Personalizado");
        s2.setPrecio(50000.0);
        s2.setDisponibilidad(true);

        gimnasio.getServiciosAdicionales().add(s1);
        gimnasio.getServiciosAdicionales().add(s2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}