package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Entrenador;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class EntrenadorViewController {

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombreCompleto;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreoElectronico;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private TextField txtTarifaSesion;
    @FXML private TextField txtBuscarEntrenador;

    @FXML private TableView<Entrenador> tableEntrenadores;
    @FXML private TableColumn<Entrenador, String> colIdentificacion;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    private final SmartGym gimnasio = SmartGym.getInstance();
    private final ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();
    private FilteredList<Entrenador> entrenadoresFiltrados;

    @FXML
    public void initialize() {
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaSesion"));

        listaEntrenadores.addAll(gimnasio.getEntrenadores());
        entrenadoresFiltrados = new FilteredList<>(listaEntrenadores, p -> true);
        tableEntrenadores.setItems(entrenadoresFiltrados);

        txtBuscarEntrenador.textProperty().addListener((observable, oldValue, newValue) -> {
            entrenadoresFiltrados.setPredicate(e -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lower = newValue.toLowerCase();
                return e.getNombreCompleto().toLowerCase().contains(lower) ||
                        e.getDocumentoIdentidad().contains(lower);
            });
        });

        tableEntrenadores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) cargarFormulario(newSel);
        });
    }

    @FXML
    public void onGuardar() {
        try {
            String doc = txtIdentificacion.getText();
            String nombre = txtNombreCompleto.getText();
            String tel = txtTelefono.getText();
            String correo = txtCorreoElectronico.getText();
            LocalDate fecha = dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue() : LocalDate.now();
            double tarifa = Double.parseDouble(txtTarifaSesion.getText());

            Entrenador nuevo = new Entrenador(nombre, doc, tel, correo, fecha, tarifa, gimnasio);
            gimnasio.getEntrenadores().add(nuevo);
            listaEntrenadores.add(nuevo);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error de Ingreso", "Compruebe que los campos numéricos sean correctos.");
        }
    }

    @FXML
    public void onActualizar() {
        Entrenador seleccionado = tableEntrenadores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                seleccionado.setNombreCompleto(txtNombreCompleto.getText());
                seleccionado.setDocumentoIdentidad(txtIdentificacion.getText());
                seleccionado.setTelefono(txtTelefono.getText());
                seleccionado.setCorreoElectronico(txtCorreoElectronico.getText());
                seleccionado.setTarifaSesion(Double.parseDouble(txtTarifaSesion.getText()));
                tableEntrenadores.refresh();
                limpiarCampos();
            } catch (Exception e) {
                mostrarAlerta("Error", "Ocurrió un fallo al actualizar los datos.");
            }
        }
    }

    private void cargarFormulario(Entrenador e) {
        txtIdentificacion.setText(e.getDocumentoIdentidad());
        txtNombreCompleto.setText(e.getNombreCompleto());
        txtTelefono.setText(e.getTelefono());
        txtCorreoElectronico.setText(e.getCorreoElectronico());
        txtTarifaSesion.setText(String.valueOf(e.getTarifaSesion()));
    }

    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtNombreCompleto.clear();
        txtEspecialidad.clear();
        txtTelefono.clear();
        txtCorreoElectronico.clear();
        txtTarifaSesion.clear();
        dpFechaRegistro.setValue(null);
        tableEntrenadores.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}