package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Entrenador;
import co.edu.uniquindio.poo.Model.EstadoPlanEntrenamiento;
import co.edu.uniquindio.poo.Model.Inscripcion;
import co.edu.uniquindio.poo.Model.PlanEntrenamiento;
import co.edu.uniquindio.poo.Model.ServicioAdicional;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionViewController {

    @FXML private TextField txtCodigoInscripcion;
    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cbPlan;
    @FXML private ComboBox<Entrenador> cbEntrenador;
    @FXML private ListView<ServicioAdicional> lvServicios;
    @FXML private Label lblValorTotal;

    private final SmartGym gimnasio = SmartGym.getInstance();

    @FXML
    public void initialize() {
        cbCliente.setItems(FXCollections.observableArrayList(gimnasio.getClientes()));
        cbPlan.setItems(FXCollections.observableArrayList(gimnasio.getPlanes()));
        cbEntrenador.setItems(FXCollections.observableArrayList(gimnasio.getEntrenadores()));

        lvServicios.setItems(FXCollections.observableArrayList(gimnasio.getServiciosAdicionales()));
        lvServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cbPlan.valueProperty().addListener((obs, oldV, newV) -> calcularTotalTemporal());
        lvServicios.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> calcularTotalTemporal());
    }

    private void calcularTotalTemporal() {
        double total = 0.0;
        PlanEntrenamiento plan = cbPlan.getValue();
        if (plan != null) {
            total += plan.calcularValorFinalPlan();
        }
        for (ServicioAdicional servicio : lvServicios.getSelectionModel().getSelectedItems()) {
            total += servicio.getPrecio();
        }
        lblValorTotal.setText(String.format("$%.2f", total));
    }

    @FXML
    public void onConfirmarInscripcion() {
        try {
            String codigo = txtCodigoInscripcion.getText();
            Cliente cliente = cbCliente.getValue();
            PlanEntrenamiento plan = cbPlan.getValue();
            Entrenador entrenador = cbEntrenador.getValue();
            List<ServicioAdicional> serviciosSeleccionados = new ArrayList<>(lvServicios.getSelectionModel().getSelectedItems());

            Inscripcion nuevaInscripcion = new Inscripcion.Builder()
                    .codigo(codigo)
                    .fecha(LocalDate.now())
                    .estado(EstadoPlanEntrenamiento.ACTIVO)
                    .cliente(cliente)
                    .plan(plan)
                    .entrenador(entrenador)
                    .listaServiciosAdicionales(serviciosSeleccionados)
                    .ownedBySmartGym(gimnasio)
                    .build();

            gimnasio.getInscripciones().add(nuevaInscripcion);
            if (cliente != null) {
                cliente.setInscripcion(nuevaInscripcion);
            }

            mostrarAlerta("Inscripción Exitosa", "Inscripción registrada. Total pagado: " + String.format("$%.2f", nuevaInscripcion.cacularPagoTotal()));
            limpiarFormulario();
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al procesar la inscripción.");
        }
    }

    private void limpiarFormulario() {
        txtCodigoInscripcion.clear();
        cbCliente.getSelectionModel().clearSelection();
        cbPlan.getSelectionModel().clearSelection();
        cbEntrenador.getSelectionModel().clearSelection();
        lvServicios.getSelectionModel().clearSelection();
        lblValorTotal.setText("$0.00");
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}