package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionViewController {

    @FXML private TextField txtCodigo;
    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cbPlan;
    @FXML private ComboBox<Entrenador> cbEntrenador;
    @FXML private ListView<ServicioAdicional> lvServicios;
    @FXML private DatePicker dpFecha;
    @FXML private Label lblValorTotal;

    @FXML private TableView<Inscripcion> tableInscripciones;
    @FXML private TableColumn<Inscripcion, String> colCodigo;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colFecha;
    @FXML private TableColumn<Inscripcion, Double> colTotal;

    private ObservableList<Inscripcion> listaInscripciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dpFecha.setValue(LocalDate.now());

        lvServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SmartGym gym = SmartGym.getInstance();
        cbCliente.setItems(FXCollections.observableArrayList(gym.getClientes()));
        cbPlan.setItems(FXCollections.observableArrayList(gym.getPlanes()));
        cbEntrenador.setItems(FXCollections.observableArrayList(gym.getEntrenadores()));
        lvServicios.setItems(FXCollections.observableArrayList(gym.getServiciosAdicionales()));

        configurarFormatosComboBoxes();

        cbPlan.setOnAction(e -> calcularTotalEstimado());
        lvServicios.setOnMouseClicked(e -> calcularTotalEstimado());

        // Configurar columnas de la tabla
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        colCliente.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getCliente() != null ? data.getValue().getCliente().getNombreCompleto() : "N/A"));
        colPlan.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getPlan() != null ? data.getValue().getPlan().getNombre() : "N/A"));
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getFecha() != null ? data.getValue().getFecha().toString() : "N/A"));
        colTotal.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().cacularPagoTotal()).asObject());

        listaInscripciones.addAll(gym.getInscripciones());
        tableInscripciones.setItems(listaInscripciones);
    }

    private void calcularTotalEstimado() {
        double total = 0.0;
        PlanEntrenamiento plan = cbPlan.getValue();
        if (plan != null) {
            total += plan.calcularValorFinalPlan();
        }
        for (ServicioAdicional servicio : lvServicios.getSelectionModel().getSelectedItems()) {
            if (servicio != null) {
                total += servicio.getPrecio();
            }
        }
        lblValorTotal.setText("$" + String.format("%.2f", total));
    }

    @FXML
    void onRegistrarInscripcion(ActionEvent event) {
        try {
            String codigo = txtCodigo.getText();
            Cliente cliente = cbCliente.getValue();
            PlanEntrenamiento plan = cbPlan.getValue();
            Entrenador entrenador = cbEntrenador.getValue();
            LocalDate fecha = dpFecha.getValue();
            List<ServicioAdicional> serviciosSeleccionados = new ArrayList<>(lvServicios.getSelectionModel().getSelectedItems());

            if (codigo.isEmpty() || cliente == null || plan == null || fecha == null) {
                mostrarAlerta("Campos Requeridos", "Debe diligenciar el código, cliente, plan y fecha.");
                return;
            }

            Inscripcion nuevaInscripcion = new Inscripcion.Builder()
                    .codigo(codigo)
                    .cliente(cliente)
                    .plan(plan)
                    .entrenador(entrenador)
                    .listaServiciosAdicionales(serviciosSeleccionados)
                    .fecha(fecha)
                    .estado(EstadoPlanEntrenamiento.ACTIVO)
                    .ownedBySmartGym(SmartGym.getInstance())
                    .build();

            SmartGym.getInstance().getInscripciones().add(nuevaInscripcion);
            listaInscripciones.add(nuevaInscripcion);
            cliente.setInscripcion(nuevaInscripcion);

            onLimpiarCampos(null);
            mostrarAlerta("Éxito", "Inscripción registrada correctamente.");

        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar la inscripción: " + e.getMessage());
        }
    }

    @FXML
    void onLimpiarCampos(ActionEvent event) {
        txtCodigo.clear();
        cbCliente.setValue(null);
        cbPlan.setValue(null);
        cbEntrenador.setValue(null);
        lvServicios.getSelectionModel().clearSelection();
        dpFecha.setValue(LocalDate.now());
        lblValorTotal.setText("$0.0");
    }

    private void configurarFormatosComboBoxes() {
        cbCliente.setConverter(new StringConverter<>() {
            @Override public String toString(Cliente object) { return object != null ? object.getNombreCompleto() + " (" + object.getDocumentoIdentidad() + ")" : ""; }
            @Override public Cliente fromString(String string) { return null; }
        });

        cbPlan.setConverter(new StringConverter<>() {
            @Override public String toString(PlanEntrenamiento object) { return object != null ? object.getNombre() + " (" + object.getDuracionMeses() + " meses)" : ""; }
            @Override public PlanEntrenamiento fromString(String string) { return null; }
        });

        cbEntrenador.setConverter(new StringConverter<>() {
            @Override public String toString(Entrenador object) { return object != null ? object.getNombreCompleto() : ""; }
            @Override public Entrenador fromString(String string) { return null; }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}