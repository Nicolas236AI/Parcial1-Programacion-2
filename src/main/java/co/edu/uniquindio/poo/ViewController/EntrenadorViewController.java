package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Entrenador;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class EntrenadorViewController {

    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTarifa;
    @FXML private DatePicker dpFechaRegistro;

    @FXML private TableView<Entrenador> tableEntrenadores;
    @FXML private TableColumn<Entrenador, String> colDocumento;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, String> colCorreo;
    @FXML private TableColumn<Entrenador, Double> colTarifa;
    @FXML private TableColumn<Entrenador, LocalDate> colFecha;

    private ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaSesion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        listaEntrenadores.addAll(SmartGym.getInstance().getEntrenadores());
        tableEntrenadores.setItems(listaEntrenadores);
    }

    @FXML
    void onRegistrarEntrenador(ActionEvent event) {
        try {
            String doc = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String tel = txtTelefono.getText();
            String correo = txtCorreo.getText();
            double tarifa = Double.parseDouble(txtTarifa.getText());
            LocalDate fecha = dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue() : LocalDate.now();

            if (doc.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta("Campos vacíos", "Por favor ingrese al menos documento y nombre.");
                return;
            }

            Entrenador entrenador = new Entrenador(nombre, doc, tel, correo, fecha, tarifa, SmartGym.getInstance());

            SmartGym.getInstance().getEntrenadores().add(entrenador);

            listaEntrenadores.add(entrenador);

            limpiarCampos();
            mostrarAlerta("Éxito", "Entrenador guardado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Ingrese un valor numérico válido para la tarifa.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar el entrenador: " + e.getMessage());
        }
    }

    @FXML
    void onModificarEntrenador(ActionEvent event) {
        Entrenador seleccionado = tableEntrenadores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombreCompleto(txtNombre.getText());
            seleccionado.setTelefono(txtTelefono.getText());
            seleccionado.setCorreoElectronico(txtCorreo.getText());
            if (!txtTarifa.getText().isEmpty()) {
                seleccionado.setTarifaSesion(Double.parseDouble(txtTarifa.getText()));
            }
            if (dpFechaRegistro.getValue() != null) {
                seleccionado.setFechaRegistro(dpFechaRegistro.getValue());
            }
            tableEntrenadores.refresh();
            limpiarCampos();
            mostrarAlerta("Éxito", "Entrenador modificado correctamente.");
        } else {
            mostrarAlerta("Atención", "Seleccione un entrenador de la tabla.");
        }
    }

    @FXML
    void onEliminarEntrenador(ActionEvent event) {
        Entrenador seleccionado = tableEntrenadores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            SmartGym.getInstance().getEntrenadores().remove(seleccionado);
            listaEntrenadores.remove(seleccionado);
            limpiarCampos();
            mostrarAlerta("Éxito", "Entrenador eliminado.");
        } else {
            mostrarAlerta("Atención", "Seleccione un entrenador de la tabla.");
        }
    }

    private void limpiarCampos() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtTarifa.clear();
        dpFechaRegistro.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}