package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Cliente;
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
import java.time.ZoneId;
import java.util.Date;

public class ClienteViewController {

    @FXML private TextField txtNombreCompleto;
    @FXML private TextField txtDocumentoIdentidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreoElectronico;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private TextField txtBuscarCliente;

    @FXML private TableView<Cliente> tableClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colDocumento;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;

    private final SmartGym gimnasio = SmartGym.getInstance();
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private FilteredList<Cliente> clientesFiltrados;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        listaClientes.addAll(gimnasio.getClientes());
        clientesFiltrados = new FilteredList<>(listaClientes, p -> true);
        tableClientes.setItems(clientesFiltrados);

        txtBuscarCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            clientesFiltrados.setPredicate(cliente -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lower = newValue.toLowerCase();
                return cliente.getNombreCompleto().toLowerCase().contains(lower) ||
                        cliente.getDocumentoIdentidad().contains(lower) ||
                        cliente.getTelefono().contains(lower);
            });
        });

        tableClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) cargarFormulario(newSel);
        });
    }

    @FXML
    public void onRegistrar() {
        try {
            String nombre = txtNombreCompleto.getText();
            String doc = txtDocumentoIdentidad.getText();
            String tel = txtTelefono.getText();
            String correo = txtCorreoElectronico.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            LocalDate localDate = dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue() : LocalDate.now();
            Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Cliente nuevoCliente = new Cliente(nombre, doc, tel, correo, edad, fecha, gimnasio);
            if (gimnasio.registrarCliente(nuevoCliente)) {
                listaClientes.add(nuevoCliente);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "Ya existe un cliente registrado con ese número de teléfono.");
            }
        } catch (Exception e) {
            mostrarAlerta("Datos inválidos", "Verifique que todos los campos estén correctamente diligenciados.");
        }
    }

    @FXML
    public void onModificar() {
        Cliente seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                seleccionado.setNombreCompleto(txtNombreCompleto.getText());
                seleccionado.setDocumentoIdentidad(txtDocumentoIdentidad.getText());
                seleccionado.setTelefono(txtTelefono.getText());
                seleccionado.setCorreoElectronico(txtCorreoElectronico.getText());
                seleccionado.setEdad(Integer.parseInt(txtEdad.getText()));
                tableClientes.refresh();
                limpiarCampos();
            } catch (Exception e) {
                mostrarAlerta("Error", "Ocurrió un problema al modificar el cliente.");
            }
        }
    }

    @FXML
    public void onEliminar() {
        Cliente seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            gimnasio.getClientes().remove(seleccionado);
            listaClientes.remove(seleccionado);
            limpiarCampos();
        }
    }

    private void cargarFormulario(Cliente c) {
        txtNombreCompleto.setText(c.getNombreCompleto());
        txtDocumentoIdentidad.setText(c.getDocumentoIdentidad());
        txtTelefono.setText(c.getTelefono());
        txtCorreoElectronico.setText(c.getCorreoElectronico());
        txtEdad.setText(String.valueOf(c.getEdad()));
    }

    private void limpiarCampos() {
        txtNombreCompleto.clear();
        txtDocumentoIdentidad.clear();
        txtTelefono.clear();
        txtCorreoElectronico.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(null);
        tableClientes.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}