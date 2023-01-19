package main.code;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class ListMovie {
    public ArrayList<Movie> Lista;

    public ListMovie(ArrayList vlista) {
    this.Lista=vlista;
    }
    public void ordenarInsercion(){
        for (int i = 1; i < this.Lista.size(); i++) {
            Movie PeliculaActual = Lista.get(i);
            int j = i - 1;
            while (j >= 0 && this.Lista.get(j).compareToName(PeliculaActual) > 0) {
                this.Lista.set(j + 1, this.Lista.get(j));
                j = j - 1;
            }
            this.Lista.set(j + 1, PeliculaActual);
        }
    }
    /*
    @FXML
    public void DesplegarPeliculas(JTextArea txt) throws SQLException{
        lista = new  ArrayList<Movie>();
        try {
            String sql = "SELECT idPelicula, nombrepelicula, director,"
                    + "genero, año, calificacion, estado FROM pelicula";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            resultset = Conexion.getResultSet(sql);
            String filas[] = new String[7];
            while(resultset.next()){
                Movie pelicula = new Movie();
                pelicula.setIdPelicula(resultset.getString(1));
                pelicula.setNombrePelicula(resultset.getString(2));
                pelicula.setDirectorPelicula(resultset.getString(3));
                pelicula.setGeneroPelicula(resultset.getString(4));
                pelicula.setAnioPelicula(Integer.parseInt(resultset.getString(5)));
                pelicula.setCalificacionPelicula(Float.parseFloat(resultset.getString(6)));
                pelicula.setEstadoPelicula(resultset.getString(7));
                lista.add(pelicula);
                ImprimirLista(txt);
            }
        } catch (Exception e) {
            System.err.print(e+"Fallaste mijin, no conetó :c FFFFFFFF");
        }
    }
    public void agregar(Movie pelicula){
        lista.add(pelicula);
    }
    public void ImprimirLista(JTextArea txt){
        String text = "";
        for(Movie aux:this.lista){
            text+=aux+"\n";
        }
    }
    public void ordenarPorSeleccion(){
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            Movie key = lista.get(i);
            int j = i - 1;
            while(j >= 0 && lista.get(j).getNombrePelicula().compareToIgnoreCase(key.getNombrePelicula())>0) {
                lista.set(j + 1, lista.get(j));
                j = j - 1;
            }
            lista.set(j + 1, key);
        }
    }
    */
}



