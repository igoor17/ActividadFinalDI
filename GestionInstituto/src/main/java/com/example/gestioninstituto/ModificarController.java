package com.example.gestioninstituto;

import com.example.gestioninstituto.models.Dispositivo;
import com.example.gestioninstituto.models.TipoDispositivo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ModificarController {

    @FXML
    private TextField fechaField;

    @FXML
    private TextField idField;

    @FXML
    private TextField marcaField;

    @FXML
    private TextField modeloField;

    @FXML
    private Button modificarButton;

    @FXML
    private TextField tipoField;

    private Dispositivo dispositivo;
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;

        // Rellenar los campos de texto con los datos del dispositivo
        idField.setText(dispositivo.getId());
        marcaField.setText(dispositivo.getMarca());
        modeloField.setText(dispositivo.getModelo());
        tipoField.setText(dispositivo.getTipo().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaField.setText(dispositivo.getFechaCompra().format(formatter));
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    @FXML
    void handleModificarButton(ActionEvent event) {
        // obtener datos de los campos
        String id = idField.getText();
        String marca = marcaField.getText();
        String modelo = modeloField.getText();
        String tipoString = tipoField.getText();
        String fechaString = fechaField.getText();

        // convertir String a TipoDispositivo
        TipoDispositivo tipo;
        try {
            tipo = TipoDispositivo.valueOf(tipoString.toUpperCase());
        } catch (IllegalArgumentException e) {
            alertError("Error", "Tipo de dispositivo no válido");
            return; // Salir del método handleOkButton
        }

        // convertir String a LocalDate
        LocalDate fechaCompra;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            fechaCompra = LocalDate.parse(fechaString, formatter);
        } catch (Exception e) {
            alertError("Error", "Fecha no válida");
            return; // Salir del método handleOkButton
        }

        // crear el dispositivo
        dispositivo = new Dispositivo(id, fechaCompra, tipo, marca, modelo);

        // cerrar ventana
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void alertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
