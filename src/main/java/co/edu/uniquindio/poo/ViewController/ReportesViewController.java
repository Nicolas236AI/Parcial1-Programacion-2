package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReportesViewController {

    @FXML private TextField txtTelefono;
    @FXML private Label lblResultadoCliente;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private Label lblTotalIngresos;

    /**
     * Busca al cliente por teléfono y evalúa si el valor ingresado es un número perfecto
     */
    @FXML
    void onBuscarYValidar(ActionEvent event) {
        String telefono = txtTelefono.getText().trim();

        if (telefono.isEmpty()) {
            lblResultadoCliente.setText("Resultado del sistema: Por favor ingrese un número de teléfono o valor.");
            return;
        }


        Cliente cliente = SmartGym.getInstance().buscarClientePorTelefono(telefono);

        String mensajePerfecto = "";
        try {
            int numero = Integer.parseInt(telefono);
            boolean esPerfecto = SmartGym.getInstance().esNumeroPerfecto(numero);
            mensajePerfecto = esPerfecto
                    ? " ¡El número " + numero + " ES un número perfecto!"
                    : " El número " + numero + " NO es un número perfecto.";
        } catch (NumberFormatException e) {
            mensajePerfecto = " (El valor ingresado no es un número entero válido para la prueba de número perfecto).";
        }


        if (cliente != null) {
            lblResultadoCliente.setText("Cliente encontrado: " + cliente.getNombreCompleto() +
                    " (Doc: " + cliente.getDocumentoIdentidad() + ")." + mensajePerfecto);
        } else {
            lblResultadoCliente.setText("No se encontró ningún cliente con el teléfono '" + telefono + "'." + mensajePerfecto);
        }
    }

    /**
     * Calcula los ingresos generados por las inscripciones dentro del rango de fechas
     */
    @FXML
    void onCalcularIngresos(ActionEvent event) {
        LocalDate fInicio = dpFechaInicio.getValue();
        LocalDate fFin = dpFechaFin.getValue();

        if (fInicio == null || fFin == null) {
            mostrarAlerta("Fechas requeridas", "Por favor seleccione ambas fechas (Inicio y Fin).");
            return;
        }

        if (fInicio.isAfter(fFin)) {
            mostrarAlerta("Rango inválido", "La fecha de inicio no puede ser posterior a la fecha final.");
            return;
        }

        // Convertir LocalDate a java.util.Date para ser compatible con SmartGym
        Date inicio = Date.from(fInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(fFin.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        // Invocar el cálculo desde la clase modelo SmartGym
        double total = SmartGym.getInstance().calcularIngresosPeriodo(inicio, fin);
        lblTotalIngresos.setText(String.format("$%,.2f", total));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}