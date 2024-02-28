package com.example.gestioninstituto;

import com.example.gestioninstituto.models.Dispositivo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar la escena de "inventario"
        FXMLLoader Loader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        Parent root = Loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Obtener el controlador de la escena de "inventario"
        Controller controller = Loader.getController();

        // Abrir la ventana de "add_dispositivo"
        FXMLLoader Loader2 = new FXMLLoader(App.class.getResource("altaDispositivo.fxml"));
        Parent root2 = (Parent) Loader2.load(); // Aquí estaba el error
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.show();

        // Obtener el controlador de la nueva ventana
        AltaController controller2 = Loader2.getController();

        // Abrir la ventana de "modificar_dispositivo"
        FXMLLoader Loader3 = new FXMLLoader(App.class.getResource("modificarDispositivo.fxml"));
        Parent root3 = (Parent) Loader3.load(); // Aquí estaba el error
        Stage stage3 = new Stage();
        stage3.setScene(new Scene(root3));
        stage3.show();

        // Obtener el controlador de la nueva ventana
        ModificarController controller3 = Loader3.getController();
    }

    @BeforeEach
    public void setUp() {
        clickOn("#idText").write("123");
        clickOn("#marcaText").write("Marca");
        clickOn("#modeloText").write("Modelo");
        clickOn("#tipoText").write("Tipo");
        clickOn("#fechaCompraText").write("2021-01-01");
        clickOn("#precioText").write("1000");

        clickOn("#altaButton");

        TableView<Dispositivo> table = lookup("#dispositivoTable").query();
        ObservableList<Dispositivo> dispositivos = table.getItems();
        assertEquals(1, dispositivos.size());
        assertEquals("123", dispositivos.get(0).getId());
        assertEquals("Marca", dispositivos.get(0).getMarca());

    }

}