package main.code;
import main.Vistas.loginController;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion{
    public static String user="santi";

    public static String password="1234";
    public String[] Ingreso(){
        String datos[]={user,password};
        return datos;
    }
    static java.sql.Connection con = null;
    static Statement st;
    //SIRVE PARA REALIZAR LA CONEXION HACIA LA BASE DE DATOS.
    public static java.sql.Connection getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:13306/peliculas";
         /*   String user = "santi";
            String password = "1234";*/
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "NO FUE POSIBLE CONECTARSE \n " + e);
            return null;
        }
    }

    public static Statement getStatement() throws SQLException{
        Statement st = null;
        st = getConexion().createStatement();
        return st;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException{
        PreparedStatement ps = getConexion().prepareStatement(sql);
        return ps;
    }

    public static ResultSet getResultSet(String sql) throws SQLException{
        ResultSet rs = null;
        rs = getStatement().executeQuery(sql);
        return rs;
    }

    public static void Cerrar(java.sql.Connection cn) throws SQLException{
        cn.close();
    }

    public static void Cerrar(PreparedStatement ps) throws SQLException{
        ps.close();
    }

    public static void Cerrar(ResultSet rs) throws SQLException{
        rs.close();
    }
}
