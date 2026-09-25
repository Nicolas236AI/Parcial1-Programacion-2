package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class ClienteViewController {

    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;

    @FXML private TableView<Cliente> tableClientes;
    @FXML private TableColumn<Cliente, String> colDocumento;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        listaClientes.addAll(SmartGym.getInstance().getClientes());
        tableClientes.setItems(listaClientes);
    }

    @FXML
    void onRegistrarCliente(ActionEvent event) {
        try {
            String doc = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String tel = txtTelefono.getText();
            String correo = txtCorreo.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Cliente cliente = new Cliente(nombre, doc, tel, correo, edad, new Date(), SmartGym.getInstance());
            if (SmartGym.getInstance().registrarCliente(cliente)) {
                listaClientes.add(cliente);
                limpiarCampos();
                mostrarAlerta("Éxito", "Cliente registrado correctamente.");
            } else {
                mostrarAlerta("Error", "Ya existe un cliente registrado con ese número de teléfono.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Ingrese una edad numérica válida.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Verifique que todos los campos estén diligenciados.");
        }
    }

    @FXML
    void onModificarCliente(ActionEvent event) {
        Cliente seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombreCompleto(txtNombre.getText());
            seleccionado.setCorreoElectronico(txtCorreo.getText());
            seleccionado.setTelefono(txtTelefono.getText());
            seleccionado.setEdad(Integer.parseInt(txtEdad.getText()));
            tableClientes.refresh();
            limpiarCampos();
            mostrarAlerta("Éxito", "Cliente modificado correctamente.");
        } else {
            mostrarAlerta("Atención", "Seleccione un cliente de la tabla.");
        }
    }

    @FXML
    void onEliminarCliente(ActionEvent event) {
        Cliente seleccionado = tableClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            SmartGym.getInstance().getClientes().remove(seleccionado);
            listaClientes.remove(seleccionado);
            limpiarCampos();
            mostrarAlerta("Éxito", "Cliente eliminado.");
        } else {
            mostrarAlerta("Atención", "Seleccione un cliente de la tabla.");
        }
    }

    private void limpiarCampos() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}