package main.Vistas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.code.Data;
import main.code.Movie;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class PeliculaSeleccionadaController implements Initializable {
    @FXML  TextField pid;
    @FXML  TextField pnombre;
    @FXML  TextField pyear;
    @FXML  TextField pdirector;
    @FXML  TextField pgenero;
    @FXML  TextField pcalificacion;
    @FXML  TextField pestado;
    private Movie pelicula;
    private int Contador;
    static final int MAXSIZE=5;

    public PeliculaSeleccionadaController() throws IOException {
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void actualizarMovie() throws SQLException {
        Data lista=new Data();
        this.pelicula=new Movie(
                pid.getText(),
                pnombre.getText(),
                Float.parseFloat(pcalificacion.getText()),
                pdirector.getText(),
                pgenero.getText(),
                pestado.getText(),
                Integer.parseInt(pyear.getText())
        );
        lista.ActualizarPelicula(pelicula);
    }
    @FXML public void alquilarPeli() throws SQLException {
        Data list=new Data();
        this.pelicula=new Movie(
                pid.getText(),
                pnombre.getText(),
                Float.parseFloat(pcalificacion.getText()),
                pdirector.getText(),
                pgenero.getText(),
                pestado.getText(),
                Integer.parseInt(pyear.getText())
        );
        if(Objects.equals(this.pelicula.getEstadoPelicula(), "Disponible")){
            try {
                FileReader fr=new FileReader("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\cont.txt");
                BufferedReader br= new BufferedReader(fr);
                String dato=br.readLine();
                this.Contador= Integer.parseInt(dato);
                br.close();
                FileWriter fw=new FileWriter("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\cont.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                this.Contador++;
                bw.write(Integer.toString(Contador));
                bw.close();
                //PARA GESTIONAR LA LISTA
                FileWriter listaFile=new FileWriter("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\ListTemp.txt",true);
                BufferedWriter buw=new BufferedWriter(listaFile);
                buw.newLine();
                buw.write(pelicula.toString());
                buw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if(this.Contador<=MAXSIZE) {
                list.ActualizarEstado(pelicula);
            }else {
                try {
                    FileReader fr = new FileReader("C:\\Users\\santi\\IdeaProjects\\Pelis\\src\\main\\java\\main\\ListTemp.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line= br.readLine();
                    String memoria="LISTA DE PELÍCULAS ALQUILADAS";
                    while (line!=null){
                        memoria+=line+"\n";
                        line = br.readLine();
                    }
                    fr.close();
                    JOptionPane.showMessageDialog(null, "Alquiló demasiados productos");
                    JOptionPane.showMessageDialog(null, memoria);
                }catch(IOException e){

                }
            }

        }else {
            JOptionPane.showMessageDialog(null,"La película no está disponible");
        }
    }
    @FXML public void devolverPeli() throws SQLException {
        Data list=new Data();
        this.pelicula=new Movie(
                pid.getText(),
                pnombre.getText(),
                Float.parseFloat(pcalificacion.getText()),
                pdirector.getText(),
                pgenero.getText(),
                pestado.getText(),
                Integer.parseInt(pyear.getText())
        );
        if(Objects.equals(this.pelicula.getEstadoPelicula(), "Alquilado")){
            list.actualizarADisponible(pelicula);
        }else {
            JOptionPane.showMessageDialog(null,"Este producto Esta Disponible");
        }
    }
    @FXML public void borrarPelicula() throws SQLException {
        Data lista=new Data();
        this.pelicula=new Movie(
                pid.getText(),
                pnombre.getText(),
                Float.parseFloat(pcalificacion.getText()),
                pdirector.getText(),
                pgenero.getText(),
                pestado.getText(),
                Integer.parseInt(pyear.getText())
        );
        lista.eliminar(pelicula);
    }
}
