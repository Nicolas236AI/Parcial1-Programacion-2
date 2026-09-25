package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Basico;
import co.edu.uniquindio.poo.Model.EstadoPlanEntrenamiento;
import co.edu.uniquindio.poo.Model.Personalizado;
import co.edu.uniquindio.poo.Model.PlanEntrenamiento;
import co.edu.uniquindio.poo.Model.Premium;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlanViewController {

    @FXML private ComboBox<String> cbTipoPlan;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDuracionMeses;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<EstadoPlanEntrenamiento> cbEstado;

    @FXML private TableView<PlanEntrenamiento> tablePlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Double> colValorMensual;
    @FXML private TableColumn<PlanEntrenamiento, EstadoPlanEntrenamiento> colEstado;

    private final SmartGym gimnasio = SmartGym.getInstance();
    private final ObservableList<PlanEntrenamiento> listaPlanes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbEstado.setItems(FXCollections.observableArrayList(EstadoPlanEntrenamiento.values()));

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        colValorMensual.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        listaPlanes.addAll(gimnasio.getPlanes());
        tablePlanes.setItems(listaPlanes);
    }

    @FXML
    public void onCrearPlan() {
        try {
            String tipo = cbTipoPlan.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            int duracion = Integer.parseInt(txtDuracionMeses.getText());
            double valor = Double.parseDouble(txtValorMensual.getText());
            EstadoPlanEntrenamiento estado = cbEstado.getValue();

            PlanEntrenamiento nuevoPlan = null;
            if ("Básico".equals(tipo)) {
                nuevoPlan = new Basico(codigo, nombre, "Plan Básico", duracion, valor, estado, true);
            } else if ("Premium".equals(tipo)) {
                nuevoPlan = new Premium(codigo, nombre, "Plan Premium", duracion, valor, estado, true, true);
            } else if ("Personalizado".equals(tipo)) {
                nuevoPlan = new Personalizado(codigo, nombre, "Plan Personalizado", duracion, valor, estado, 10, "General", true);
            }

            if (nuevoPlan != null) {
                gimnasio.getPlanes().add(nuevoPlan);
                listaPlanes.add(nuevoPlan);
                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarAlerta("Error de Creación", "Asegúrese de ingresar valores válidos.");
        }
    }

    @FXML
    public void onClonarPlan() {
        PlanEntrenamiento seleccionado = tablePlanes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            PlanEntrenamiento clon = (PlanEntrenamiento) seleccionado.clonar();
            clon.setCodigo(seleccionado.getCodigo() + "-CLON");
            clon.setNombre(seleccionado.getNombre() + " (Copia)");

            gimnasio.getPlanes().add(clon);
            listaPlanes.add(clon);
        } else {
            mostrarAlerta("Selección requerida", "Seleccione un plan de la tabla para clonarlo.");
        }
    }

    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDuracionMeses.clear();
        txtValorMensual.clear();
        cbTipoPlan.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}