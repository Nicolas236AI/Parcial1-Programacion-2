package co.edu.uniquindio.poo.ViewController;

import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.SmartGym;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReportesViewController {

    @FXML private TextField txtTelefonoBusqueda;
    @FXML private Label lblResultadoBusqueda;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private Label lblTotalIngresos;

    private final SmartGym gimnasio = SmartGym.getInstance();

    @FXML
    public void onBuscarYValidar() {
        String telefono = txtTelefonoBusqueda.getText();
        if (telefono == null || telefono.trim().isEmpty()) {
            lblResultadoBusqueda.setText("Resultado: Ingrese un número telefónico.");
            return;
        }

        Cliente cliente = gimnasio.buscarClientePorTelefono(telefono);
        if (cliente == null) {
            lblResultadoBusqueda.setText("Resultado: No se encontró ningún cliente con ese teléfono.");
            return;
        }

        try {
            int numTel = Integer.parseInt(telefono.replaceAll("[^0-9]", ""));
            boolean esPerfecto = gimnasio.esNumeroPerfecto(numTel);
            String textoPerfecto = esPerfecto ? "y su número ES PERFECTO." : "y su número NO es perfecto.";

            lblResultadoBusqueda.setText(String.format("Cliente: %s | Tel: %s (%s)",
                    cliente.getNombreCompleto(), cliente.getTelefono(), textoPerfecto));
        } catch (NumberFormatException e) {
            lblResultadoBusqueda.setText(String.format("Cliente: %s | Teléfono no numérico para evaluación.", cliente.getNombreCompleto()));
        }
    }

    @FXML
    public void onCalcularIngresos() {
        LocalDate inicio = dpFechaInicio.getValue();
        LocalDate fin = dpFechaFin.getValue();

        if (inicio == null || fin == null) {
            lblTotalIngresos.setText("$0.00 (Seleccione ambas fechas)");
            return;
        }

        Date dateInicio = Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFin = Date.from(fin.atStartOfDay(ZoneId.systemDefault()).toInstant());

        double total = gimnasio.calcularIngresosPeriodo(dateInicio, dateFin);
        lblTotalIngresos.setText(String.format("$%.2f", total));
    }
}