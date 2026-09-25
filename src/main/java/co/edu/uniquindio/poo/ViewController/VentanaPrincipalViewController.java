package co.edu.uniquindio.poo.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class VentanaPrincipalViewController {

    @FXML private BorderPane mainBorderPane;

    @FXML
    public void onMostrarDashboard() {

    }

    @FXML
    public void onMostrarClientes() {
        cargarVista("/View/VenatanaCliente.fxml");
    }

    @FXML
    public void onMostrarEntrenadores() {
        cargarVista("/View/VentanaEntrenador.fxml");
    }

    @FXML
    public void onMostrarPlanes() {
        cargarVista("/View/VentanaPlan.fxml");
    }

    @FXML
    public void onMostrarServicios() {
        cargarVista("/View/ServicioAdicional.fxml");
    }

    @FXML
    public void onMostrarInscripciones() {
        cargarVista("/View/Inscripcion.fxml");
    }

    @FXML
    public void onMostrarReportes() {
        cargarVista("/View/ConsultaReportes.fxml");
    }

    private void cargarVista(String rutaFxml) {
        if (mainBorderPane != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
                Parent vista = loader.load();
                mainBorderPane.setCenter(vista);
            } catch (IOException e) {
                System.err.println("Error al cargar la vista: " + rutaFxml);
                e.printStackTrace();
            }
        }
    }
}