package main.Vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController{

    @FXML

    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void iniciarLogin(ActionEvent actionEvent) throws IOException {
            cerrarVentana(actionEvent);
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = Loader.load();
            Scene escena=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(escena);
            stage.show();

    }
}
