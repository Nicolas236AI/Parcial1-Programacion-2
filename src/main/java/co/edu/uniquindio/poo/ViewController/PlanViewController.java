package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Basico;
import co.edu.uniquindio.poo.Model.EstadoPlanEntrenamiento;
import co.edu.uniquindio.poo.Model.Personalizado;
import co.edu.uniquindio.poo.Model.PlanEntrenamiento;
import co.edu.uniquindio.poo.Model.Premium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlanViewController {

    @FXML private ComboBox<String> comboTipoPlan;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<EstadoPlanEntrenamiento> comboEstado;

    @FXML private TableView<PlanEntrenamiento> tablePlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Double> colValorMensual;
    @FXML private TableColumn<PlanEntrenamiento, EstadoPlanEntrenamiento> colEstado;

    private ObservableList<PlanEntrenamiento> listaPlanes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comboTipoPlan.setItems(FXCollections.observableArrayList("Básico", "Personalizado", "Premium"));
        comboEstado.setItems(FXCollections.observableArrayList(EstadoPlanEntrenamiento.values()));

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        colValorMensual.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablePlanes.setItems(listaPlanes);
    }

    @FXML
    void onCrearPlan(ActionEvent event) {
        try {
            String tipo = comboTipoPlan.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            double valor = Double.parseDouble(txtValorMensual.getText());
            EstadoPlanEntrenamiento estado = comboEstado.getValue();

            PlanEntrenamiento nuevoPlan = null;
            if ("Básico".equals(tipo)) {
                nuevoPlan = new Basico(codigo, nombre, "Plan básico de gimnasio", duracion, valor, estado, true);
            } else if ("Personalizado".equals(tipo)) {
                nuevoPlan = new Personalizado(codigo, nombre, "Plan personalizado", duracion, valor, estado, 12, "Musculación", true);
            } else if ("Premium".equals(tipo)) {
                nuevoPlan = new Premium(codigo, nombre, "Plan premium con acceso VIP", duracion, valor, estado, true, true);
            }

            if (nuevoPlan != null) {
                listaPlanes.add(nuevoPlan);
                limpiarCampos();
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Por favor verifique los datos ingresados.");
        }
    }

    @FXML
    void onModificarPlan(ActionEvent event) {
        PlanEntrenamiento seleccionado = tablePlanes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setDuracionMeses(Integer.parseInt(txtDuracion.getText()));
            seleccionado.setValorMensual(Double.parseDouble(txtValorMensual.getText()));
            seleccionado.setEstado(comboEstado.getValue());
            tablePlanes.refresh();
            limpiarCampos();
        } else {
            mostrarAlerta("Atención", "Seleccione un plan de la tabla para modificar.");
        }
    }

    @FXML
    void onEliminarPlan(ActionEvent event) {
        PlanEntrenamiento seleccionado = tablePlanes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaPlanes.remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Seleccione un plan de la tabla para eliminar.");
        }
    }

    @FXML
    void onClonarPlan(ActionEvent event) {
        PlanEntrenamiento seleccionado = tablePlanes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Aplicación del patrón Prototype mediante la invocación del método clonar()
            PlanEntrenamiento planClonado = (PlanEntrenamiento) seleccionado.clonar();
            planClonado.setCodigo(seleccionado.getCodigo() + "-CLON");
            planClonado.setNombre(seleccionado.getNombre() + " (Copia)");

            listaPlanes.add(planClonado);
        } else {
            mostrarAlerta("Atención", "Seleccione un plan de la tabla para clonar.");
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDuracion.clear();
        txtValorMensual.clear();
        comboTipoPlan.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}