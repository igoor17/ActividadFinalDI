package com.example.gestioninstituto;

import com.example.gestioninstituto.models.Dispositivo;
import com.example.gestioninstituto.models.TipoDispositivo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AltaController {

    @FXML
    private Button altaButton;

    @FXML
    private TextField fechaField;

    @FXML
    private TextField idField;

    @FXML
    private TextField marcaField;

    @FXML
    private TextField modeloField;

    @FXML
    private TextField tipoField;

    private Dispositivo dispositivo;

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAltaButton(ActionEvent event) {
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
        LocalDate fecha;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            fecha = LocalDate.parse(fechaString, formatter);
        } catch (Exception e) {
            alertError("Error", "Fecha no válida");
            return; // Salir del método handleOkButton
        }


        // crear dispositivo
        this.dispositivo = new Dispositivo(id, fecha, tipo, marca, modelo);

        // cerrar la ventana
        ((Button) (event.getSource())).getScene().getWindow().hide();

    }

    public void alertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
