/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Brian
 */
public class ConexionUsuarios {
    Connection conectar=null;
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
           JOptionPane.showMessageDialog(null,"Conexion Exitosa");
        }catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error Conexion"+e.getMessage());  
        }
        return conectar;
    }
    
}
