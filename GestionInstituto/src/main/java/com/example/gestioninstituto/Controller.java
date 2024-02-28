package com.example.gestioninstituto;

import com.example.gestioninstituto.models.Dispositivo;
import com.example.gestioninstituto.utils.ScreenLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Controller {

    @FXML
    private Button altaButton;

    @FXML
    private Button bajaButton;

    @FXML
    private TableView<Dispositivo> dispositivosTable;

    @FXML
    private TableColumn<Dispositivo, String> fechaColumn;

    @FXML
    private TableColumn<Dispositivo, String> idColumn;

    @FXML
    private Button imprimirButton;

    @FXML
    private TableColumn<Dispositivo, String> marcaColumn;

    @FXML
    private TableColumn<Dispositivo, String> modeloColumn;

    @FXML
    private Button modificarButton;

    @FXML
    private TableColumn<Dispositivo, String> tipoColumn;

    private ObservableList<Dispositivo> dispositivos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        dispositivosTable.setItems(dispositivos);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        fechaColumn.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getFechaCompra();
            return new SimpleStringProperty(date.format(formatter));
        });

    }

    @FXML
    private void abrirAltaDispositivo(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Esto hará que la ventana de alta sea modal
            Object controller = ScreenLoader.loadScreen("/com/example/gestioninstituto/altaDispositivo.fxml", stage);

            stage.setOnHidden(e -> {
                if (Objects.nonNull(((AltaController)controller).getDispositivo())) {
                    Dispositivo nuevoDispositivo = ((AltaController)controller).getDispositivo();
                    dispositivos.add(nuevoDispositivo);
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void bajaDispositivo(ActionEvent event) {
        Dispositivo dispositivo = dispositivosTable.getSelectionModel().getSelectedItem();
        if (dispositivo != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de eliminación");
            alert.setHeaderText("Eliminar dispositivo");
            alert.setContentText("¿Estás seguro de que quieres eliminar este dispositivo?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    dispositivos.remove(dispositivo);
                }
            });
        }
    }

    @FXML
    private void modificarDispositivo(ActionEvent event) {
        Dispositivo dispositivo = dispositivosTable.getSelectionModel().getSelectedItem();
        if (dispositivo != null) {
            try {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Esto hará que la ventana de modificación sea modal
                Object controller = ScreenLoader.loadScreen("/com/example/gestioninstituto/modificarDispositivo.fxml", stage);

                ((ModificarController)controller).setDispositivo(dispositivo);

                stage.setOnHidden(e -> {
                    Dispositivo dispositivoModificado = ((ModificarController)controller).getDispositivo();
                    if (dispositivoModificado != null) {
                        int index = dispositivos.indexOf(dispositivo);
                        dispositivos.set(index, dispositivoModificado);
                    }
                });

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void imprimir(ActionEvent event) {
        File file = new File("src/main/java/com/example/gestioninstituto/dispositivosImpresos/dispositivos.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Dispositivo dispositivo : dispositivosTable.getItems()) {
                writer.println(dispositivo.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
