package main.code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import main.Vistas.DashController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;

public class Data {
    static Connection connect;
    static Statement statement;
    static ResultSet resultset;
    static PreparedStatement preparedS;
    ArrayList<Movie> listaPelicula;
public Data(){

}
    public ObservableList DesplegarPeliculas() throws SQLException{
        ObservableList tabla;
        listaPelicula=new ArrayList<Movie>();
        try {
            String sql = "SELECT idPelicula, nombrepelicula, director,"
                    + "genero, año, calificacion, estado FROM pelicula";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            resultset = Conexion.getResultSet(sql);
            while(resultset.next()) {
                Movie film= new Movie();
                film.setIdPelicula(resultset.getString(1));
                film.setNombrePelicula(resultset.getString(2));
                film.setDirectorPelicula( resultset.getString(3));
                film.setGeneroPelicula( resultset.getString(4));
                film.setAnioPelicula(resultset.getInt(5));
                film.setCalificacionPelicula(resultset.getInt(6));
                film.setEstadoPelicula(resultset.getString(7));
                this.listaPelicula.add(film);
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        ordenarInsercion();
        Conexion.Cerrar(connect);
        tabla= FXCollections.observableArrayList(listaPelicula);

        return tabla;
    }

    public void GuardarPelicula (Movie pelicula) throws SQLException{
        String sql = "INSERT INTO pelicula (idPelicula, nombrepelicula, director,"
                + " genero, año, calificacion, estado) VALUES (?,?,?,?,?,?,?)";
        try {
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            preparedS = Conexion.getPreparedStatement(sql);
            preparedS.setString(1, pelicula.getIdPelicula());
            preparedS.setString(2, pelicula.getNombrePelicula());
            preparedS.setString(3, pelicula.getDirectorPelicula());
            preparedS.setString(4, pelicula.getGeneroPelicula());
            preparedS.setInt(5, pelicula.getAnioPelicula());
            preparedS.setFloat(6, pelicula.getCalificacionPelicula());
            preparedS.setString(7, pelicula.getEstadoPelicula());
            preparedS.executeUpdate();
            JOptionPane.showMessageDialog(null,"Los datos se han guardado con éxito","Películas",JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar película",
                    "Películas", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }finally {
            if (preparedS != null) {
                Conexion.Cerrar(preparedS);
            }
            if (connect != null) {
                Conexion.Cerrar(connect);
            }
        }
    }

    public Integer ActualizarPelicula(Movie pelicula) throws SQLException{
        Integer aux = null;
        try {
            String sql = "UPDATE pelicula SET nombrepelicula=?, director=?,"
                    + " genero=?, año=?, calificacion=?, estado=? WHERE idPelicula=?";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            preparedS = Conexion.getPreparedStatement(sql);
            preparedS.setString(1, pelicula.getNombrePelicula());
            preparedS.setString(2, pelicula.getDirectorPelicula());
            preparedS.setString(3, pelicula.getGeneroPelicula());
            preparedS.setInt(4, pelicula.getAnioPelicula());
            preparedS.setFloat(5, pelicula.getCalificacionPelicula());
            preparedS.setString(6, pelicula.getEstadoPelicula());
            preparedS.setString(7, pelicula.getIdPelicula());
            aux = preparedS.executeUpdate();
            if(aux >= 1){
                JOptionPane.showMessageDialog(null, "Los datos de la película han sido actualizados", "Películas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos de la película", "Películas", JOptionPane.ERROR_MESSAGE);
            }
        }finally{
            if(preparedS != null){
                Conexion.Cerrar(preparedS);
            }if(connect != null){
                Conexion.Cerrar(connect);
            }
        }
        return aux;
    }

    public void ordenarInsercion(){
        for (int i = 1; i < this.listaPelicula.size(); i++) {
            Movie PeliculaActual = this.listaPelicula.get(i);
            int j = i - 1;
            while (j >= 0 && this.listaPelicula.get(j).getNombrePelicula().compareToIgnoreCase(PeliculaActual.getNombrePelicula()) > 0) {
                this.listaPelicula.set(j + 1, this.listaPelicula.get(j));
                j = j - 1;
            }
            this.listaPelicula.set(j + 1, PeliculaActual);
        }
    }
    public ObservableList BuscarPoryear(String pelicula) throws SQLException{
        ObservableList tabla;
        ArrayList<Movie> listaPeliculanio =new ArrayList<Movie>();
        try {
            String sql = "SELECT idPelicula, nombrepelicula, director,"
                    + "genero, año, calificacion, estado FROM pelicula WHERE año = '"+pelicula+"'";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            resultset = Conexion.getResultSet(sql);
            while(resultset.next()) {
                Movie film= new Movie();
                film.setIdPelicula(resultset.getString(1));
                film.setNombrePelicula(resultset.getString(2));
                film.setDirectorPelicula( resultset.getString(3));
                film.setGeneroPelicula( resultset.getString(4));
                film.setAnioPelicula(resultset.getInt(5));
                film.setCalificacionPelicula(resultset.getInt(6));
                film.setEstadoPelicula(resultset.getString(7));
                listaPeliculanio.add(film);
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        Conexion.Cerrar(connect);
        tabla= FXCollections.observableArrayList(listaPeliculanio);

        return tabla;
    }
    public ObservableList BuscarPorGenero(String pelicula) throws SQLException{
        ObservableList tabla;
        ArrayList<Movie> listaPeliculanio =new ArrayList<Movie>();
        try {
            String sql = "SELECT idPelicula, nombrepelicula, director,"
                    + "genero, año, calificacion, estado FROM pelicula WHERE genero = '"+pelicula+"'";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            resultset = Conexion.getResultSet(sql);
            while(resultset.next()) {
                Movie film= new Movie();
                film.setIdPelicula(resultset.getString(1));
                film.setNombrePelicula(resultset.getString(2));
                film.setDirectorPelicula( resultset.getString(3));
                film.setGeneroPelicula( resultset.getString(4));
                film.setAnioPelicula(resultset.getInt(5));
                film.setCalificacionPelicula(resultset.getInt(6));
                film.setEstadoPelicula(resultset.getString(7));
                listaPeliculanio.add(film);
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        Conexion.Cerrar(connect);
        tabla= FXCollections.observableArrayList(listaPeliculanio);

        return tabla;
    }
    public ObservableList BuscarPorNombre(String pelicula) throws SQLException{
        ObservableList tabla;
        ArrayList<Movie> listaPeliculanio =new ArrayList<Movie>();
        try {
            String sql = "SELECT idPelicula, nombrepelicula, director,"
                    + "genero, año, calificacion, estado FROM pelicula WHERE nombrepelicula = '"+pelicula+"'";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            resultset = Conexion.getResultSet(sql);
            while(resultset.next()) {
                Movie film= new Movie();
                film.setIdPelicula(resultset.getString(1));
                film.setNombrePelicula(resultset.getString(2));
                film.setDirectorPelicula( resultset.getString(3));
                film.setGeneroPelicula( resultset.getString(4));
                film.setAnioPelicula(resultset.getInt(5));
                film.setCalificacionPelicula(resultset.getInt(6));
                film.setEstadoPelicula(resultset.getString(7));
                listaPeliculanio.add(film);
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        Conexion.Cerrar(connect);
        tabla= FXCollections.observableArrayList(listaPeliculanio);

        return tabla;
    }

    public ObservableList ordenarPorCalificacion() throws SQLException{
        ObservableList tabla;
        ArrayList<Movie> listaOrdenada =new ArrayList<Movie>();
        listaOrdenada=this.listaPelicula;
        for (int i = 1; i < listaOrdenada.size(); i++) {
            Movie PeliculaActual = listaOrdenada.get(i);
            int j = i - 1;
            while (j >= 0 && Float.compare(listaOrdenada.get(j).getCalificacionPelicula(),PeliculaActual.getCalificacionPelicula()) < 0) {
                listaOrdenada.set(j + 1, listaOrdenada.get(j));
                j = j - 1;
            }
            listaOrdenada.set(j + 1, PeliculaActual);
        }
        ArrayList<Movie> listaTop10 =new ArrayList<Movie>();
        for(int i=1;i<=10;i++) {
            Movie film= new Movie();
            film=listaOrdenada.get(i);
            listaTop10.add(film);
        }
        tabla= FXCollections.observableArrayList(listaTop10);

        return tabla;
    }
    public int eliminar(Movie pelicula) throws SQLException{
            int aux;
            String sql = "DELETE FROM pelicula WHERE idPelicula=?";
            try{
                connect = Conexion.getConexion();
                preparedS = Conexion.getPreparedStatement(sql);
                preparedS.setString(1, pelicula.getIdPelicula());
                aux = preparedS.executeUpdate();
                if(aux > 0){
                    JOptionPane.showMessageDialog(null, "Registro eliminado", "Películas", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar registro", "Películas", JOptionPane.ERROR_MESSAGE);
                }
            }finally{
                if(preparedS != null){
                    Conexion.Cerrar(preparedS);

                }if(connect != null){
                    Conexion.Cerrar(connect);
                }
            }
            return aux;
}
    public Integer ActualizarEstado(Movie pelicula) throws SQLException{
        Integer aux = null;
        try {
            String sql = "UPDATE pelicula SET estado='Alquilado' WHERE idPelicula=?";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            preparedS = Conexion.getPreparedStatement(sql);
            preparedS.setString(1, pelicula.getIdPelicula());
            aux = preparedS.executeUpdate();
            if(aux >= 1){
                pelicula.setEstadoPelicula("Alquilado");
                JOptionPane.showMessageDialog(null, "El estado de la película han sido actualizado", "Películas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la pelicula", "Películas", JOptionPane.ERROR_MESSAGE);
            }

        }finally{
            if(preparedS != null){
                Conexion.Cerrar(preparedS);
            }if(connect != null){
                Conexion.Cerrar(connect);
            }
        }
        return aux;
}
    public Integer actualizarADisponible(Movie pelicula) throws SQLException{
        Integer aux = null;
        try {
            String sql = "UPDATE pelicula SET estado='Disponible' WHERE idPelicula=?";
            connect = Conexion.getConexion();
            statement = Conexion.getStatement();
            preparedS = Conexion.getPreparedStatement(sql);
            preparedS.setString(1, pelicula.getIdPelicula());
            aux = preparedS.executeUpdate();
            if(aux >= 1){
                pelicula.setEstadoPelicula("Disponible");
                JOptionPane.showMessageDialog(null, "El estado de la película han sido actualizado", "Películas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la pelicula", "Películas", JOptionPane.ERROR_MESSAGE);
            }

        }finally{
            if(preparedS != null){
                Conexion.Cerrar(preparedS);
            }if(connect != null){
                Conexion.Cerrar(connect);
            }
        }
        return aux;
    }
}

