package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Vistas/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PoliFans");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
        try {
            FileWriter fw = new FileWriter("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\cont.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Integer.toString(0));
            bw.close();
            FileWriter fuw = new FileWriter("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\ListTemp.txt");
            BufferedWriter buw = new BufferedWriter(fuw);
            bw.write("");
            bw.close();
        }catch (IOException e){

        }
    }
}