package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.ServicioAdicional;
import co.edu.uniquindio.poo.Model.ValoracionFisica;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicioAdicionalViewController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;

    @FXML private TableView<ServicioAdicional> tableServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecio;

    private ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        listaServicios.addAll(SmartGym.getInstance().getServiciosAdicionales());
        tableServicios.setItems(listaServicios);
    }


    @FXML
    void onAgregarServicio(ActionEvent event) {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());

            ServicioAdicional nuevoServicio = new ValoracionFisica(
                    codigo, nombre, "Servicio adicional registrado", precio, true, 0.0, 0.0, "Sin observaciones"
            );

            SmartGym.getInstance().getServiciosAdicionales().add(nuevoServicio);
            listaServicios.add(nuevoServicio);
            limpiarCampos();
            mostrarAlerta("Éxito", "Servicio adicional agregado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Ingrese un precio numérico válido.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Verifique que todos los campos estén diligenciados.");
        }
    }

    @FXML
    void onModificarServicio(ActionEvent event) {
        ServicioAdicional seleccionado = tableServicios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setPrecio(Double.parseDouble(txtPrecio.getText()));
            tableServicios.refresh();
            limpiarCampos();
        } else {
            mostrarAlerta("Atención", "Seleccione un servicio de la tabla.");
        }
    }

    @FXML
    void onEliminarServicio(ActionEvent event) {
        ServicioAdicional seleccionado = tableServicios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            SmartGym.getInstance().getServiciosAdicionales().remove(seleccionado);
            listaServicios.remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Seleccione un servicio de la tabla.");
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}