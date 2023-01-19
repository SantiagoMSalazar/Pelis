package main.Vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.code.Conexion;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    TextField CUser;
    @FXML PasswordField CPassword;
    @FXML private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML public void iniciarDashboard(ActionEvent actionEvent) throws IOException {
        if(validarIngreso()) {
            cerrarVentana(actionEvent);
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = Loader.load();
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(escena);
            stage.show();
        }else {
            JOptionPane.showMessageDialog(null,"Credenciales incorrectas");
        }

    }
    @FXML boolean validarIngreso(){
        Conexion cn=new Conexion();
        System.out.println(cn.Ingreso()[0]+","+cn.Ingreso()[1]);
        return (Objects.equals(cn.Ingreso()[0], CUser.getText()) && Objects.equals(cn.Ingreso()[1], CPassword.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
