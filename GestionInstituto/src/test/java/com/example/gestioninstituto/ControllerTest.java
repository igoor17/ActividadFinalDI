package com.example.gestioninstituto;

import com.example.gestioninstituto.models.Dispositivo;
import com.example.gestioninstituto.models.TipoDispositivo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TableViewMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest extends ApplicationTest {
    private Controller controller;
    private AltaController altaController;

    @Override
    public void start(Stage stage) throws Exception { // Load the main view
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
        controller= fxmlLoader.getController();

        // Load the alta dispositivo view
        FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("altaDispositivo.fxml"));
        Parent root2 = (Parent) fxmlLoader2.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.show();
        altaController = fxmlLoader2.getController();
    }

    @BeforeEach
    public void setUp() {
        // Simular la interacción del usuario para crear un dispositivo
        clickOn("#idField").write("1");
        clickOn("#marcaField").write("marca1");
        clickOn("#modeloField").write("modelo1");
        clickOn("#tipoField").write("PORTATIL");
        clickOn("#fechaField").write("01/01/2022");
        clickOn("#altaButton");

        // Simular la interacción del usuario para crear otro dispositivo
        clickOn("#idButton").write("2");
        clickOn("#marcaField").write("marca2");
        clickOn("#modeloField").write("modelo2");
        clickOn("#tipoField").write("ORDENADOR");
        clickOn("#fechaField").write("02/02/2022");
        clickOn("#altaButton");

    }

    @Test
    public void testMostrarDispositivos() {
        // Asegúrate de que la tabla tiene al menos dos dispositivos
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.hasNumRows(2));

        // Selecciona el primer dispositivo en la tabla
        TableView<Dispositivo> table = lookup("#dispositivosTable").queryAs(TableView.class);
        Dispositivo expectedDispositivo1 = table.getItems().get(0);
        Dispositivo expectedDispositivo2 = table.getItems().get(1);

        // Verifica que los dispositivos se muestran correctamente en la tabla
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.containsRow(expectedDispositivo1));
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.containsRow(expectedDispositivo2));
    }

    @Test
    public void testBajaDispositivo() {
        // Asegúrate de que la tabla tiene al menos un dispositivo
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.hasNumRows(2));

        // Selecciona el primer dispositivo en la tabla
        TableView<Dispositivo> table = lookup("#dispositivosTable").queryAs(TableView.class);
        interact(() -> table.getSelectionModel().selectFirst());

        // Simula un clic en el botón de baja
        clickOn("#bajaButton");

        // Verifica que el dispositivo se ha eliminado de la tabla
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.hasNumRows(1));
    }


    @Test
    public void testModificarDispositivo() {
        // Asegúrate de que la tabla tiene al menos un dispositivo
        FxAssert.verifyThat("#dispositivosTable", TableViewMatchers.hasNumRows(1));

        // Selecciona el primer dispositivo en la tabla
        TableView<Dispositivo> table = lookup("#dispositivosTable").queryAs(TableView.class);
        interact(() -> table.getSelectionModel().selectFirst());

        // Simula un clic en el botón de modificar
        clickOn("#modificarButton");

        // Simula la interacción del usuario para modificar el dispositivo
        clickOn("#idField").write("2");
        clickOn("#marcaField").write("marca3");
        clickOn("#modeloField").write("modelo3");
        clickOn("#tipoField").write("PROYECTOR");
        clickOn("#fechaField").write("03/03/2023");
        clickOn("#modificarButton");

        // Verifica que el dispositivo se ha modificado correctamente
        Dispositivo expectedDispositivo = table.getItems().get(0);
        assertEquals("2", expectedDispositivo.getId());
        assertEquals("marca3", expectedDispositivo.getMarca());
        assertEquals("modelo3", expectedDispositivo.getModelo());
        assertEquals(TipoDispositivo.PROYECTOR, expectedDispositivo.getTipo());
        assertEquals(LocalDate.of(2023, 3, 3), expectedDispositivo.getFechaCompra());
    }

}