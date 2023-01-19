package main.Vistas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.code.Data;
import main.code.ListMovie;
import main.code.Movie;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashController implements Initializable {

    @FXML TableView<Movie> tablaPelis=new TableView<>();
    @FXML TableColumn<Movie,String> Tid=new TableColumn<>("Nombre");
    @FXML TableColumn<Movie,String> Ttitulo=new TableColumn<>("Titulo");
    @FXML TableColumn<Movie,String> Tdirector=new TableColumn<>("Director");
    @FXML TableColumn<Movie,String> Tgenero=new TableColumn<>("Genero");
    @FXML TableColumn<Movie,Integer> Tanio=new TableColumn<>("Año");
    @FXML TableColumn<Movie,Float> Tcalificacion=new TableColumn<>("Calificacion");
    @FXML TableColumn<Movie,String> Testado=new TableColumn<>("Estado");
    @FXML ObservableList<Movie> listaPeliculas;
    @FXML CheckBox byear;
    @FXML CheckBox bgenero;
    @FXML TextField barrabuscar;
    @FXML Button BOTONNUEVA;
    @FXML TextField ID;
    @FXML TextField NOMBRE;
    @FXML TextField DIRECTOR;
    @FXML TextField YEAR;
    @FXML TextField GENERO;
    @FXML TextField ESTADO;
    @FXML TextField CALIFICACION;
    Data base=new Data();
    //Movie PeliculaSeleccionada=new Movie();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaPeliculas = FXCollections.observableArrayList();
        //Seteamos columnas
        this.Tid.setCellValueFactory(new PropertyValueFactory<>("idPelicula"));
        this.Ttitulo.setCellValueFactory(new PropertyValueFactory<>("nombrePelicula"));
        this.Tdirector.setCellValueFactory(new PropertyValueFactory<>("directorPelicula"));
        this.Tgenero.setCellValueFactory(new PropertyValueFactory<>("generoPelicula"));
        this.Tanio.setCellValueFactory(new PropertyValueFactory<>("anioPelicula"));
        this.Tcalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacionPelicula"));
        this.Testado.setCellValueFactory(new PropertyValueFactory<>("estadoPelicula"));
        try {
            listaPeliculas=base.DesplegarPeliculas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tablaPelis.setItems(listaPeliculas);
        tablaPelis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Movie peliculaSelect=new Movie();
                if (event.getClickCount() == 2) {
                    Movie peliculaSelect = tablaPelis.getSelectionModel().getSelectedItem();

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("PeliculaSeleccionada.fxml"));
                        Scene escena = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(escena);
                        TextField namepid = (TextField) escena.lookup("#pid");
                        TextField namenombre = (TextField) escena.lookup("#pnombre");
                        TextField namedirector = (TextField) escena.lookup("#pdirector");
                        TextField namegenero = (TextField) escena.lookup("#pgenero");
                        TextField nameyear = (TextField) escena.lookup("#pyear");
                        TextField namecalificacion = (TextField) escena.lookup("#pcalificacion");
                        TextField nameestado = (TextField) escena.lookup("#pestado");
                        //set de las bases
                        namepid.setText(peliculaSelect.getIdPelicula());
                        namenombre.setText(peliculaSelect.getNombrePelicula());
                        namedirector.setText(peliculaSelect.getDirectorPelicula());
                        namegenero.setText(peliculaSelect.getGeneroPelicula());
                        nameyear.setText(String.valueOf(peliculaSelect.getAnioPelicula()));
                        namecalificacion.setText(String.valueOf(peliculaSelect.getCalificacionPelicula()));
                        nameestado.setText(peliculaSelect.getEstadoPelicula());
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
    }
    @FXML
    public void ingresarPelicula(ActionEvent actionEvent) throws SQLException {
        Movie pelicula=new Movie(this.ID.getText(),
                this.NOMBRE.getText(),
                Float.parseFloat(this.CALIFICACION.getText()),
                this.DIRECTOR.getText(),
                this.GENERO.getText(),
                this.ESTADO.getText(),
                Integer.parseInt(this.YEAR.getText()));
        base.GuardarPelicula(pelicula);
        try {
            listaPeliculas=base.DesplegarPeliculas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tablaPelis.setItems(FXCollections.observableArrayList());
        tablaPelis.setItems(listaPeliculas);
    }
    @FXML
    public void buscar(ActionEvent event) throws SQLException {
    if(byear.isSelected()){
        listaPeliculas=base.BuscarPoryear(barrabuscar.getText());
        tablaPelis.setItems(FXCollections.observableArrayList());
        tablaPelis.setItems(listaPeliculas);
    } else if (bgenero.isSelected()) {
        listaPeliculas=base.BuscarPorGenero((barrabuscar.getText()));
        tablaPelis.setItems(FXCollections.observableArrayList());
        tablaPelis.setItems(listaPeliculas);
    }else{
        listaPeliculas=base.BuscarPorNombre((barrabuscar.getText()));
        tablaPelis.setItems(FXCollections.observableArrayList());
        tablaPelis.setItems(listaPeliculas);
    }

    }

    @FXML
    public void ordenarAlfabeticamente() throws SQLException {
    listaPeliculas=this.base.DesplegarPeliculas();
    tablaPelis.setItems(listaPeliculas);
    }
    @FXML
    public void ordenarPorID() throws SQLException {
        listaPeliculas=this.base.DesplegarPeliculas();
        tablaPelis.setItems(listaPeliculas);
    }
    @FXML
    public void mostrarTop(ActionEvent event) throws SQLException {
        listaPeliculas=base.ordenarPorCalificacion();
        tablaPelis.setItems(FXCollections.observableArrayList());
        tablaPelis.setItems(listaPeliculas);
    }
}
