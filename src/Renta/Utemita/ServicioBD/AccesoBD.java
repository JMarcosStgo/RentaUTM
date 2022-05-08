/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renta.Utemita.ServicioBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kAarl
 */
public class AccesoBD {
   private String driver="com.mysql.cj.jdbc.Driver";
   private String cadenaConeccion="jdbc:mysql://127.0.0.1/rentautemita";
   private String usuario="root";
   private String contraseña="";
   
   public Connection con;
   public void iniciarBD(){
       try {
            Class.forName(driver);
            con=DriverManager.getConnection(cadenaConeccion,usuario,contraseña);
           // JOptionPane.showMessageDialog(null,"Conexion exitosa a la base de datos");
            
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error al establecer la conexion con la base de datos"+e);
       }
   }
   public Boolean existeUsuario(String correo,String password){
     Statement st;
       try {
           st = con.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM usuario WHERE correo='"+correo+"' AND password='"+password+"'");
           //retorna verdadero si encuentra al usuario en la BD, sino False;
           if(rs.next()==true)
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
   }
   
   public void DesconectarBD(){
       try {con.close();
           //JOptionPane.showMessageDialog(null,"Sesion BD terminada de manera exitosa");           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"A ocurrido un error al desconectar la base de datos");
       }
   }
}
