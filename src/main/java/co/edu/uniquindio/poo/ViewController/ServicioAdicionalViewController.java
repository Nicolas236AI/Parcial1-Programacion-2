package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.AsesoriaNutricionalFactory;
import co.edu.uniquindio.poo.Model.ClasesEspecialesFactory;
import co.edu.uniquindio.poo.Model.EntrenamientoPersonalizadoFactory;
import co.edu.uniquindio.poo.Model.ServicioAdicional;
import co.edu.uniquindio.poo.Model.ServicioAdicionalFactory;
import co.edu.uniquindio.poo.Model.SmartGym;
import co.edu.uniquindio.poo.Model.ValoracionFisicaFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicioAdicionalViewController {

    @FXML private ComboBox<String> cbFabricaServicio;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private CheckBox chkDisponible;

    @FXML private TableView<ServicioAdicional> tableServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecio;
    @FXML private TableColumn<ServicioAdicional, Boolean> colDisponibilidad;

    private final SmartGym gimnasio = SmartGym.getInstance();
    private final ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

        listaServicios.addAll(gimnasio.getServiciosAdicionales());
        tableServicios.setItems(listaServicios);
    }

    @FXML
    public void onCrearServicio() {
        String seleccion = cbFabricaServicio.getValue();
        if (seleccion == null) {
            mostrarAlerta("Selección vacía", "Por favor seleccione una fábrica de servicios.");
            return;
        }

        ServicioAdicionalFactory factory = null;

        switch (seleccion) {
            case "Valoración Física":
                factory = new ValoracionFisicaFactory();
                break;
            case "Asesoría Nutricional":
                factory = new AsesoriaNutricionalFactory();
                break;
            case "Entrenamiento Personalizado":
                factory = new EntrenamientoPersonalizadoFactory();
                break;
            case "Clases Especiales":
                factory = new ClasesEspecialesFactory();
                break;
        }

        if (factory != null) {
            ServicioAdicional nuevoServicio = null;
            if (factory instanceof ValoracionFisicaFactory) nuevoServicio = factory.crearValoracionFisica();
            else if (factory instanceof AsesoriaNutricionalFactory) nuevoServicio = factory.crearAsesoriaNutricional();
            else if (factory instanceof EntrenamientoPersonalizadoFactory) nuevoServicio = factory.crearEntrenamientoPerson();
            else if (factory instanceof ClasesEspecialesFactory) nuevoServicio = factory.crearClasesEspeciales();

            if (nuevoServicio != null) {
                if (!txtCodigo.getText().isEmpty()) nuevoServicio.setCodigo(txtCodigo.getText());
                if (!txtNombre.getText().isEmpty()) nuevoServicio.setNombre(txtNombre.getText());
                if (!txtPrecio.getText().isEmpty()) nuevoServicio.setPrecio(Double.parseDouble(txtPrecio.getText()));
                nuevoServicio.setDisponibilidad(chkDisponible.isSelected());

                gimnasio.getServiciosAdicionales().add(nuevoServicio);
                listaServicios.add(nuevoServicio);
                limpiarCampos();
            }
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        chkDisponible.setSelected(false);
        cbFabricaServicio.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}