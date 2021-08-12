/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgift;

import java.util.Date;
import ConexionSQL.ConexionUsuarios;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.util.Collections;



/**
 *
 * @author Brian
 */
public class tablas extends javax.swing.JFrame {

    ConexionUsuarios cc=new ConexionUsuarios();
    Connection con=cc.conexion();
    static ResultSet resultado;
    static Statement sentencia;
    
    
    
    public tablas() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        mostrarDatos();
        mostrarDatosClientes();
        mostrarDatosBanco();
        mostrarDatosComunas();
        mostrarDatosRrss();
        mostrarDatosCategoriaArt();
        llenar_comboArticulos();       
        mostrarDatosArticulos();
        llenar_TablaArticulos();
        MostrarDatosPack();
        MostrarDatosProveedores();
        mostrarDatosCatVentas();
        llenar_comboPacks();
        llenar_comboRRSS();
        llenar_comboComunas();
        llenar_comboEstado();
        llenar_comboBanco();
        MostrarDatosVentas();
        numeroMayor();
        MostrarDatosListaDestino();
        MostrarDatosActualizacionDespachos();
        llenar_TablaProveedores();
        MostrarDatosSolPedidos();
        numeroMayorCompras();
        mostrarDatoFactura();
        mostrarDatosPack();
        mostrarPorFechaVentas();
        mostrarDatosPorRutDevolucion();
        mostrarDatosPorcategoria();
        
    }
//USUARIOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void filtrarDatos(String valor){
  
      String[] titulos={"ID","Nombre","Contraseña"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from usuarios where USU_NOMBRE like '%"+valor+"%'";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("USU_ID_USUARIO");
             registros[1]=rs.getString("USU_NOMBRE"); 
             registros[2]=rs.getString("USU_CLAVE");
             
             modelo.addRow(registros);
             
         }
         
         tablaUsuarios.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  }  
 public void mostrarDatos(){
  
      String[] titulos={"ID","Nombre","Contraseña"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from usuarios";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("USU_ID_USUARIO");
             registros[1]=rs.getString("USU_NOMBRE"); 
             registros[2]=rs.getString("USU_CLAVE");
             
             modelo.addRow(registros);
             
         }
         
         tablaUsuarios.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  }  
 public void eliminarRegistros(){
 int filaSeleccionada=tablaUsuarios.getSelectedRow();
 
 try{
    String SQL="delete from usuarios where USU_ID_USUARIO="+tablaUsuarios.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 }   
 public void limpiarCajas(){
    txtusuario_maestros_usuarios.setText("");
    txtcontraseña_maestros_usuarios.setText("");
    
    
}
 public void actualizarDatos(){
 
 try{
    
    String SQL ="update usuarios set USU_NOMBRE=?,USU_CLAVE=? where USU_ID_USUARIO=? ";
    
    int filaSeleccionado=tablaUsuarios.getSelectedRow();
    String dao=(String)tablaUsuarios.getValueAt(filaSeleccionado, 0);
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtusuario_maestros_usuarios.getText());
    pst.setString(2,txtcontraseña_maestros_usuarios.getText());
  
    pst.setString(3, dao);
   pst.execute();
   
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 }    
 public void insertarDatos(){
     
 try{
    
    String SQL ="insert into usuarios(USU_NOMBRE,USU_CLAVE) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtusuario_maestros_usuarios.getText());
    pst.setString(2,txtcontraseña_maestros_usuarios.getText());
  
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
}
 }
    @SuppressWarnings("unchecked")
    
//CLIENTESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosClientes(){
 
 try{
    
    String SQL ="insert into cliente(CLI_RUT_CLIENTE,CLI_NOMBRE,CLI_CELULAR,CLI_TELEFONO,CLI_CORREO,CLI_NACIMIENTO,CLI_ACTIVADO_DESACTIVADO) values(?,?,?,?,?,?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtrut_maestros_clientes.getText());
    pst.setString(2,txt_ncliente_maestros_clientes.getText());
    pst.setString(3,txtcel_maestros_clientes.getText()); 
    pst.setString(4,txtfono_maestros_clientes.getText());
    pst.setString(5,txtemaill_maestros_clientes.getText());
    pst.setString(6,((JTextField)txtfnac_maestros_clientes.getDateEditor().getUiComponent()).getText());
    int seleccionado=cboxatcdes_maestros_clientes.getSelectedIndex();
    pst.setString(7,cboxatcdes_maestros_clientes.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
}
 }   
 public void limpiarCajasClientes(){
    txtrut_maestros_clientes.setText("");
    txt_ncliente_maestros_clientes.setText("");
    txtcel_maestros_clientes.setText("");
    txtfono_maestros_clientes.setText("");
    txtemaill_maestros_clientes.setText("");
    txtfnac_maestros_clientes.setCalendar(null);
    cboxatcdes_maestros_clientes.setSelectedItem(null);
    
    
    
  
}  
 public void actualizarDatosClientes(){
     
 try{
      
    String SQL ="update cliente set CLI_RUT_CLIENTE=?,CLI_NOMBRE=?,CLI_CELULAR=?,CLI_TELEFONO=?,CLI_CORREO=?,CLI_NACIMIENTO=?,CLI_ACTIVADO_DESACTIVADO=? where CLI_RUT_CLIENTE=? ";
    
     int filaSeleccionado=listadeclientes_maestros.getSelectedRow();
    String dao=(String)listadeclientes_maestros.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtrut_maestros_clientes.getText());
    pst.setString(2,txt_ncliente_maestros_clientes.getText());
    pst.setString(3,txtcel_maestros_clientes.getText()); 
    pst.setInt(4,Integer.parseInt(txtfono_maestros_clientes.getText()));
    pst.setString(5,txtemaill_maestros_clientes.getText());
    pst.setString(6,((JTextField)txtfnac_maestros_clientes.getDateEditor().getUiComponent()).getText());
    
    int seleccionado=cboxatcdes_maestros_clientes.getSelectedIndex();
    pst.setString(7,cboxatcdes_maestros_clientes.getItemAt(seleccionado));
   
    
    pst.setString(8, dao);
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void mostrarDatosClientes(){
  
      String[] titulos={"RUT","Nombre","Celular","Telefono","Correo","Fecha de Nacimiento","Estado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from cliente";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("CLI_RUT_CLIENTE");
             registros[1]=rs.getString("CLI_NOMBRE"); 
             registros[2]=rs.getString("CLI_CELULAR");
              registros[3]=rs.getString("CLI_TELEFONO");
             registros[4]=rs.getString("CLI_CORREO"); 
             registros[5]=rs.getString("CLI_NACIMIENTO");
             registros[6]=rs.getString("CLI_ACTIVADO_DESACTIVADO");
             modelo.addRow(registros);
             
         }
         
         listadeclientes_maestros.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosClientes(){
 int filaSeleccionada=listadeclientes_maestros.getSelectedRow();
 
 try{
    String SQL="delete from cliente where CLI_RUT_CLIENTE="+listadeclientes_maestros.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 }   
 public void filtrarDatosClientes(String valor){
  
      String[] titulos={"RUT","Nombre","Celular","Telefono","Correo","Fecha de Nacimiento","Estado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from cliente where CLI_RUT_CLIENTE like '%"+valor+"%'";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
              registros[0]=rs.getString("CLI_RUT_CLIENTE");
             registros[1]=rs.getString("CLI_NOMBRE"); 
             registros[2]=rs.getString("CLI_CELULAR");
              registros[3]=rs.getString("CLI_TELEFONO");
             registros[4]=rs.getString("CLI_CORREO"); 
             registros[5]=rs.getString("CLI_NACIMIENTO");
             registros[6]=rs.getString("CLI_ACTIVADO_DESACTIVADO");
             modelo.addRow(registros);
             
         }
         
         listadeclientes_maestros.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar Clientes "+e.getMessage());
     }
     
     
     
  }  

 //BANCOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosBanco(){
 
 try{
    
    String SQL ="insert into bancos(BAN_DESCRIPCION,BAN_ACTIVADO_DESACTIVADO) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtnbanco_maestros_bancos.getText());
  
    int seleccionado=cbbanco_maestros_bancos.getSelectedIndex();
    pst.setString(2,cbbanco_maestros_bancos.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Editado");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edición"+e.getMessage());
}
 }   
 public void limpiarCajasBanco(){
    txtnbanco_maestros_bancos.setText("");
    cbbanco_maestros_bancos.setSelectedItem(null);
    
}  
 public void actualizarDatosBanco(){
     
 try{
      
    String SQL ="update bancos set BAN_DESCRIPCION=?,BAN_ACTIVADO_DESACTIVADO=? where BAN_ID_BANCO=? ";
    
     int filaSeleccionado=bancosregistrados.getSelectedRow();
    String dao=(String)bancosregistrados.getValueAt(filaSeleccionado, 0);
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtnbanco_maestros_bancos.getText());
   int seleccionado=cbbanco_maestros_bancos.getSelectedIndex();
    pst.setString(2,cbbanco_maestros_bancos.getItemAt(seleccionado));
    
    pst.setString(3, dao);
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void mostrarDatosBanco(){
  
      String[] titulos={"ID Banco","Banco","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from bancos";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("BAN_ID_BANCO");
             registros[1]=rs.getString("BAN_DESCRIPCION"); 
             registros[2]=rs.getString("BAN_ACTIVADO_DESACTIVADO"); 
            
             modelo.addRow(registros);
             
         }
         
         bancosregistrados.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosBanco(){
     
 int filaSeleccionada=bancosregistrados.getSelectedRow();
 
 try{
    String SQL="delete from bancos where BAN_ID_BANCO="+bancosregistrados.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 }   
 public void filtrarDatosBanco(String valor){
  
      String[] titulos={"ID Banco","Banco","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from bancos where BAN_DESCRIPCION like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("BAN_ID_BANCO");
             registros[1]=rs.getString("BAN_DESCRIPCION"); 
             registros[2]=rs.getString("BAN_ACTIVADO_DESACTIVADO"); 
             
             modelo.addRow(registros);
             
         }
         
          bancosregistrados.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar bancos "+e.getMessage());
     }
     
     
     
  } 
 
//COMUNASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosComunas(){
 
 try{
    
    String SQL ="insert into comunas(COM_DESCRIPCION,COM_ACTIVADO_DESACTIVADO) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtncomuna_maestros_comunas.getText());
    int seleccionado=cbcomuna_maestros_comunas.getSelectedIndex();
    pst.setString(2,cbcomuna_maestros_comunas.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Editado");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edición"+e.getMessage());
}
 }   
 public void actualizarDatosComunas(){
     
 try{
      
    String SQL ="update comunas set COM_DESCRIPCION=?,COM_ACTIVADO_DESACTIVADO=? where COM_ID_COMUNA=? ";
    
     int filaSeleccionado=comunasregistradas.getSelectedRow();
    String dao=(String)comunasregistradas.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtncomuna_maestros_comunas.getText());
    int seleccionado=cbcomuna_maestros_comunas.getSelectedIndex();
    pst.setString(2,cbcomuna_maestros_comunas.getItemAt(seleccionado));
    pst.setString(3, dao);
    
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void limpiarCajasComunas(){
    txtncomuna_maestros_comunas.setText("");
    cbcomuna_maestros_comunas.setSelectedItem(null);
     
}
 public void mostrarDatosComunas(){
  
      String[] titulos={"ID Comunas","Comunas","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from comunas";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("COM_ID_COMUNA");
             registros[1]=rs.getString("COM_DESCRIPCION"); 
             registros[2]=rs.getString("COM_ACTIVADO_DESACTIVADO");
             
             modelo.addRow(registros);
             
         }
         
         comunasregistradas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosComunas(){
     
 int filaSeleccionada=comunasregistradas.getSelectedRow();
 
 try{
    String SQL="delete from comunas where COM_ID_COMUNA="+comunasregistradas.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 }   
 public void filtrarDatosComunas(String valor){
  
      String[] titulos={"ID Comunas","Comunas","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from comunas where COM_DESCRIPCION like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
            registros[0]=rs.getString("COM_ID_COMUNA");
            registros[1]=rs.getString("COM_DESCRIPCION"); 
            registros[2]=rs.getString("COM_ACTIVADO_DESACTIVADO");
            
             modelo.addRow(registros);
             
         }
         
          comunasregistradas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar comunas "+e.getMessage());
     }
     
     
     
  } 
 
 //RRSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosRrss(){
 
 try{
    
    String SQL ="insert into rrss (RRS_NOMBRE,RRS_ACTIVADO_DESACTIVADO) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtnrrss_maestros_rrss.getText());
    int seleccionado=cbrrss_maestros_rrss.getSelectedIndex();
    pst.setString(2,cbrrss_maestros_rrss.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
}
 }   
 public void limpiarCajasRrss(){
    txtnrrss_maestros_rrss.setText("");
    cbrrss_maestros_rrss.setSelectedItem(null);
    
}
 public void actualizarDatosRrss(){
     
 try{
      
    String SQL ="update rrss set RRS_NOMBRE=?,RRS_ACTIVADO_DESACTIVADO=? where RRS_ID_RRSS=? ";
    
     int filaSeleccionado=redessociales.getSelectedRow();
    String dao=(String)redessociales.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtnrrss_maestros_rrss.getText());
   int seleccionado=cbrrss_maestros_rrss.getSelectedIndex();
    pst.setString(2,cbrrss_maestros_rrss.getItemAt(seleccionado));
    pst.setString(3, dao);
    
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void mostrarDatosRrss(){
  
      String[] titulos={"ID RRSS","RRSS","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from rrss";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("RRS_ID_RRSS");
             registros[1]=rs.getString("RRS_NOMBRE"); 
             registros[2]=rs.getString("RRS_ACTIVADO_DESACTIVADO"); 
             
             modelo.addRow(registros);
             
         }
         
         redessociales.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosRrss(){
     
 int filaSeleccionada=redessociales.getSelectedRow();
 
 try{
    String SQL="delete from rrss where RRS_ID_RRSS="+redessociales.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 }   
 public void filtrarDatosRrss(String valor){
  
      String[] titulos={"ID RRSS","RRSS","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from rrss where RRS_NOMBRE like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
            registros[0]=rs.getString("RRS_ID_RRSS");
            registros[1]=rs.getString("RRS_NOMBRE"); 
            registros[2]=rs.getString("RRS_ACTIVADO_DESACTIVADO");
            
             modelo.addRow(registros);
             
         }
         
          redessociales.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar RRSS "+e.getMessage());
     }
     
     
     
  } 
 
 //CATEGORIA_ARTICULOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosCategoriaArt(){
 
 try{
    
    String SQL ="insert into categoria_articulo (CAT_DESCRIPCION,CAT_ACTIVADO_DESACTIVADO) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtcategoria_maestros_catarticulos.getText());
    int seleccionado=cbcategoria_maestros_catarticulos.getSelectedIndex();
    pst.setString(2,cbcategoria_maestros_catarticulos.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
}
 }   
 public void limpiarCajasCategoriaArt(){
    txtcategoria_maestros_catarticulos.setText("");
    cbcategoria_maestros_catarticulos.setSelectedItem(null);
    
}
 public void actualizarDatosCategoriaArt(){
     
 try{
      
    String SQL ="update categoria_articulo set CAT_DESCRIPCION=?,CAT_ACTIVADO_DESACTIVADO=? where ID_Categoria=? ";
    
     int filaSeleccionado=categoriaarticulos.getSelectedRow();
    String dao=(String)categoriaarticulos.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtcategoria_maestros_catarticulos.getText());
   int seleccionado=cbcategoria_maestros_catarticulos.getSelectedIndex();
    pst.setString(2,cbcategoria_maestros_catarticulos.getItemAt(seleccionado));
    pst.setString(3, dao);
    
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void mostrarDatosCategoriaArt(){
  
      String[] titulos={"ID Categoria","Categoria","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from categoria_articulo";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ID_Categoria");
             registros[1]=rs.getString("CAT_DESCRIPCION"); 
             registros[2]=rs.getString("CAT_ACTIVADO_DESACTIVADO"); 
             
             modelo.addRow(registros);
             
         }
         
         categoriaarticulos.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosCategoriaArt(){
     
 int filaSeleccionada=categoriaarticulos.getSelectedRow();
 
 try{
    String SQL="delete from categoria_articulo where ID_Categoria="+categoriaarticulos.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 } 
 public void filtrarDatosCategoriaArt(String valor){
  
      String[] titulos={"ID Categoria","Categoria","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from categoria_articulo where CAT_DESCRIPCION like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ID_Categoria");
             registros[1]=rs.getString("CAT_DESCRIPCION"); 
             registros[2]=rs.getString("CAT_ACTIVADO_DESACTIVADO");
            
             modelo.addRow(registros);
             
         }
         
          categoriaarticulos.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar RRSS "+e.getMessage());
     }
     
     
     
  } 
 
 //ARTICULOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void llenar_comboArticulos(){
  cboxcategoria_maestros_articulos1.removeAllItems();
     
 try {
   String q ="SELECT * FROM categoria_articulo where CAT_ACTIVADO_DESACTIVADO='Activado'";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
             
             
          cboxcategoria_maestros_articulos1.addItem(rs.getString("CAT_DESCRIPCION"));
          
         //cboxcategoria_maestros_articulos1.addItem(rs.getString("ID_CATEGORIA"));
         }
     } catch (Exception e) {
         System.out.println("Incorrector asd "+e.getMessage());
     }
    
 
 }
 public void insertarDatosArticulos(){
 
 try{
    
    String SQL ="insert into articulo (ID_CATEGORIA,ART_DESCRIPCION,ART_UNIDADES,ART_FECHA_VENCIMIENTO,ART_MARCAS,ART_ACTIVADO_DESACTIVADO) values(?,?,?,?,?,?)";
                 
    
    PreparedStatement pst= con.prepareStatement(SQL);

    pst.setString(1,txtcategoria_maestros_articulos1.getText());
    
    pst.setString(2,txtnarticulo_maestros_articulo.getText());
    pst.setString(3,txtunidades_maestros_articulos.getText());
    pst.setString(4,((JTextField)jDvencimiento_maestros_articulos.getDateEditor().getUiComponent()).getText());
    pst.setString(5,txtmarcas_maestros_articulos.getText());
    int seleccionado=cboxproveedor_maestros_articulos.getSelectedIndex();
    pst.setString(6,cboxproveedor_maestros_articulos.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
}
 } 
 public void limpiarCajasArticulos(){
    txtnarticulo_maestros_articulo.setText("");
    txtunidades_maestros_articulos.setText("");
    txtmarcas_maestros_articulos.setText("");
    txtcategoria_maestros_articulos1.setText("");
    jDvencimiento_maestros_articulos.setCalendar(null);
    cboxproveedor_maestros_articulos.setSelectedItem(null);
    cboxcategoria_maestros_articulos1.setSelectedItem(null);
}  
 public void actualizarDatosArticulos(){
     
try{
    
    //    String SQL ="update articulo set (select CAT_DESCRIPCION from categoria_articulo where ID_CATEGORIA =? ),ART_DESCRIPCION=?,ART_UNIDADES=?,ART_FECHA_VENCIMIENTO=?,ART_MARCAS=?,ART_ACTIVADO_DESACTIVADO=? where ART_ID_ARTICULO=? ";
    //     String SQL ="UPDATE categoria_articulo SET CAT_DESCRIPCION = ? WHERE categoria_articulo.ID_Categoria =?" + "update articulo set ART_DESCRIPCION=?,ART_UNIDADES=?,ART_FECHA_VENCIMIENTO=?,ART_MARCAS=?,ART_ACTIVADO_DESACTIVADO=? where ART_ID_ARTICULO=? ";
    String SQL="update articulo set  ID_CATEGORIA=?,ART_DESCRIPCION=?,ART_UNIDADES=?,ART_FECHA_VENCIMIENTO=?,ART_MARCAS=?,ART_ACTIVADO_DESACTIVADO=? where ART_ID_ARTICULO=?";
    
    //(select CAT_DESCRIPCION from cate/goria_articulo where ID_CATEGORIA =? )
    //(select ID_CATEGORIA=? from categoria_articulo where CAT_DESCRIPCION=? )  txtcategoria_maestros_articulos1
     int filaSeleccionado=listaarticulos.getSelectedRow();
    String dao=(String)listaarticulos.getValueAt(filaSeleccionado, 0);

    PreparedStatement pst= con.prepareStatement(SQL);
//JOptionPane.showMessageDialog(null,pst );
    pst.setString(1,txtcategoria_maestros_articulos1.getText());
    //int seleccionadoo=cboxcategoria_maestros_articulos1.getSelectedIndex();
   // pst.setString(1,cboxcategoria_maestros_articulos1.getItemAt(seleccionadoo) );
    pst.setString(2,txtnarticulo_maestros_articulo.getText());
    pst.setString(3,txtunidades_maestros_articulos.getText());
    pst.setString(4,((JTextField)jDvencimiento_maestros_articulos.getDateEditor().getUiComponent()).getText());
    pst.setString(5,txtmarcas_maestros_articulos.getText());
    int seleccionado=cboxproveedor_maestros_articulos.getSelectedIndex();
    pst.setString(6,cboxproveedor_maestros_articulos.getItemAt(seleccionado));
    pst.setString(7,dao);
    pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
}
 }  
 public void mostrarDatosArticulos(){
  
      String[] titulos={"ID Articulo","ID Categoría","Articulo","Cantidad","Fecha de vencimiento","Marca","Estado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,articulo.ID_CATEGORIA from articulo JOIN categoria_articulo ON categoria_articulo.ID_Categoria= articulo.ID_CATEGORIA";
             
             
             
             
             /*
             "SELECT *,articulo.ID_CATEGORIA \n" +
                "from articulo\n" +
                "JOIN categoria_articulo ON articulo.ID_CATEGORIA = categoria_articulo.ID_Categoria";
             */
     //"CAT_DESCRIPCION"ID_CATEGORIA
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ART_ID_ARTICULO");
             registros[1]=rs.getString("CAT_DESCRIPCION"); 
             registros[2]=rs.getString("ART_DESCRIPCION");
             registros[3]=rs.getString("ART_UNIDADES");
             registros[4]=rs.getString("ART_FECHA_VENCIMIENTO"); 
             registros[5]=rs.getString("ART_MARCAS");
             registros[6]=rs.getString("ART_ACTIVADO_DESACTIVADO");
             modelo.addRow(registros);
             
         }
         
         listaarticulos.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 public void eliminarRegistrosArticulos(){
     
 int filaSeleccionada=listaarticulos.getSelectedRow();
 
 try{
    String SQL="delete from articulo where ART_ID_ARTICULO="+listaarticulos.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 } 
 public void filtrarDatosArticulos(String valor){
  
      String[] titulos={"ID Articulo","ID Categoría","Articulo","Cantidad","Fecha de vencimiento","Marca","Estado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from articulo where ART_DESCRIPCION like '%"+valor+"%' ";
     //"CAT_DESCRIPCION"
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ART_ID_ARTICULO");
             registros[1]=rs.getString("ID_CATEGORIA"); 
             registros[2]=rs.getString("ART_DESCRIPCION");
             registros[3]=rs.getString("ART_UNIDADES");
             registros[4]=rs.getString("ART_FECHA_VENCIMIENTO"); 
             registros[5]=rs.getString("ART_MARCAS");
             registros[6]=rs.getString("ART_ACTIVADO_DESACTIVADO");
             modelo.addRow(registros);
             
         }
         
         listaarticulos.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
     
     
     
  } 
 
 //PROVEEDORESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void InsertarDatosProveedores(){
 
 try {
    String SQL ="insert into proveedor (PRO_RUT_PROVEEDOR,PRO_NOMBRE,PRO_TELEFONO,PRO_CORREO,PRO_DIRECCION,PRO_RAZON,PRO_ACTIVADO_DESACTIVADO) values(?,?,?,?,?,?,?)";
    
    PreparedStatement Pst= con.prepareStatement(SQL);
    
    Pst.setString(1,txtrut_maestros_proveedores.getText());
    Pst.setString(2,txtncontacto_maestros_proveedores.getText());
    Pst.setString(3,txtfono_maestros_proveedores.getText());
    Pst.setString(4,txtemail_maestros_proveedores.getText());
    Pst.setString(5,txtdireccion_maestros_proveedores.getText());
    Pst.setString(6,txtrazon_maestros_proveedores.getText());
    int seleccionado=cbactdes_maestros_proveedores.getSelectedIndex();
    Pst.setString(7,cbactdes_maestros_proveedores.getItemAt(seleccionado));
    
    Pst.execute();
        JOptionPane.showMessageDialog(null,"Registro Exitoso");
     
 }catch (Exception e) {
     
     JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
     
 }       
  }
 public void limpiarCajasProveedores(){
     txtrut_maestros_proveedores.setText("");
     txtncontacto_maestros_proveedores.setText("");
     txtfono_maestros_proveedores.setText("");
     txtemail_maestros_proveedores.setText("");
     txtdireccion_maestros_proveedores.setText("");
     txtrazon_maestros_proveedores.setText("");
     cbactdes_maestros_proveedores.setSelectedItem(null);
     
     
 }
 public void ActualizarDatosProveedores(){
     try {
         
         String SQL ="update proveedor set  PRO_RUT_PROVEEDOR=?,PRO_NOMBRE=?,PRO_TELEFONO=?,PRO_CORREO=?,PRO_DIRECCION=?,PRO_RAZON=?,PRO_ACTIVADO_DESACTIVADO=? where PRO_RUT_PROVEEDOR=?";
         
         int filaSeleccionado=proveedores_maestros.getSelectedRow();
         String dao=(String)proveedores_maestros.getValueAt(filaSeleccionado, 0);
         PreparedStatement pst= con.prepareStatement(SQL);
         
         pst.setString(1,txtrut_maestros_proveedores.getText());
         pst.setString(2,txtncontacto_maestros_proveedores.getText());
         pst.setString(3,txtfono_maestros_proveedores.getText());
         pst.setString(4,txtemail_maestros_proveedores.getText());
         pst.setString(5,txtdireccion_maestros_proveedores.getText());
         pst.setString(6,txtrazon_maestros_proveedores.getText());
         int seleccionado=cbactdes_maestros_proveedores.getSelectedIndex();
         pst.setString(7,cbactdes_maestros_proveedores.getItemAt(seleccionado));
         
         pst.setString(8, dao);
        pst.execute();
        
        
         JOptionPane.showMessageDialog(null,"Registro Editado");
         
     }catch (Exception e) {
         
         JOptionPane.showMessageDialog(null,"Error en Edicion"+e.getMessage());
         
         
     }
 }
 public void MostrarDatosProveedores(){
     
     String[] Titulos={"Rut Proveedor","Nombre","Telefono","Correo","direccion","Razon Social","Estado"};
     String[] Registros= new String[7];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="select * from proveedor";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PRO_RUT_PROVEEDOR");
             Registros[1]=rs.getString("PRO_NOMBRE");
             Registros[2]=rs.getString("PRO_TELEFONO");
             Registros[3]=rs.getString("PRO_CORREO");
             Registros[4]=rs.getString("PRO_DIRECCION");
             Registros[5]=rs.getString("PRO_RAZON");
             Registros[6]=rs.getString("PRO_ACTIVADO_DESACTIVADO");
             
             Modelo.addRow(Registros);
             
         }
         
         proveedores_maestros.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }
 public void FiltarDatosProveedores (String valor){
    String[] titulos={"Rut Proveedor","Nombre","Telefono","Correo","direccion","Razon Social","Estado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from proveedor where PRO_RUT_PROVEEDOR like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("PRO_RUT_PROVEEDOR");
             registros[1]=rs.getString("PRO_NOMBRE"); 
             registros[2]=rs.getString("PRO_TELEFONO");
             registros[3]=rs.getString("PRO_CORREO");
             registros[4]=rs.getString("PRO_DIRECCION");
             registros[5]=rs.getString("PRO_RAZON");
             registros[6]=rs.getString("PRO_ACTIVADO_DESACTIVADO");
             
             modelo.addRow(registros);
             
         }
         
          proveedores_maestros.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar RRSS "+e.getMessage());
     }
      
 }
 
 //PACKSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void llenar_TablaArticulos(){
    
    DefaultTableModel modelo;
 modelo=(DefaultTableModel)listaarticulos_maestros_packs.getModel();
  modelo.getDataVector().removeAllElements();  
     
  String[] Titulos={"ID Articulo","Articulo","Stock Articulos"};
     String[] Registros= new String[3];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     DefaultTableModel Modeloo=new DefaultTableModel (null,Titulos);
     String q ="SELECT * FROM articulo where ART_ACTIVADO_DESACTIVADO='Activado'";
     
 try {
   
    
     
     
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
          Registros[0]=rs.getString("ART_ID_ARTICULO");   
          Registros[1]=rs.getString("ART_DESCRIPCION");     
          Registros[2]=rs.getString("ART_UNIDADES");
          Modelo.addRow(Registros);
         }
           listaarticulos_maestros_packs.setModel (Modelo);
          
     } catch (Exception e) {
         System.out.println("Problemas al mostrar datos "+e.getMessage());
     }
    
 
 }
 public void limpiar_TablaPacks() {
 
 DefaultTableModel modelo;
 modelo=(DefaultTableModel)articuloselegidos_maestros_packs.getModel();
  modelo.removeRow(articuloselegidos_maestros_packs.getSelectedRow());
  
     
 }
 public void limpiar_TablaPacksCompleta() {
 
 DefaultTableModel modelo;
 modelo=(DefaultTableModel)articuloselegidos_maestros_packs.getModel();
  modelo.getDataVector().removeAllElements();
   
 }
 public void limpiarCajasPack (){
 
     txtprecio_maestros_packs.setText("");
     txtnombre_maestros_packs.setText("");
     txtunidades_maestros_packs.setText("");
     
 } 
 public void insertarDatosPacks(){
 try{
   
    String SQL ="insert into pack (PCK_NOMBRE,PCK_COSTO,PCK_STOCK,PCK_ACTIVADO_DESACTIVADO) values(?,?,?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtnombre_maestros_packs.getText());
    
    pst.setString(2,txtprecio_maestros_packs.getText());
   
    pst.setString(3,Integer.toString(valorMenor()));
    
    int seleccionado=cboxEstados_maestros_packs.getSelectedIndex();
    pst.setString(4,cboxEstados_maestros_packs.getItemAt(seleccionado));
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
   JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
}
 }   
 public int valorMenor(){
 
   ArrayList<Integer> list =new ArrayList<Integer>();
     for (int i = 0; i < articuloselegidos_maestros_packs.getRowCount(); i++) {
        list.add(Integer.parseInt(articuloselegidos_maestros_packs.getValueAt(i, 4).toString()));
         
     }
     int min=Collections.min(list);
     
     
     return min;
 } 
 public void MostrarDatosPack(){
     
     String[] Titulos={"ID Pack","Nombre","Costo","Stock_packs","Estados"};
     String[] Registros= new String[5];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="select * from pack ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PCK_ID_PACK");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("PCK_COSTO");
             Registros[3]=rs.getString("PCK_STOCK");
             Registros[4]=rs.getString("PCK_ACTIVADO_DESACTIVADO");
             
             Modelo.addRow(Registros);
             
         }
         
         tabla_packs.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }    
 public void FiltrarDatosPack(String valor){
     
     String[] Titulos={"ID Pack","Nombre","Costo","Stock_packs"};
     String[] Registros= new String[4];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="select * from pack where PCK_NOMBRE like '%"+valor+"%' ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PCK_ID_PACK");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("PCK_COSTO");
             Registros[3]=rs.getString("PCK_STOCK");

             
             Modelo.addRow(Registros);
             
         }
         
         tabla_packs.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }       
 public void actualizarDatosPack(){
     
 try{
      
    String SQL ="update pack set PCK_NOMBRE=?,PCK_COSTO=?,PCK_STOCK=?,PCK_ACTIVADO_DESACTIVADO=? where PCK_ID_PACK=? ";
    
     int filaSeleccionado=tabla_packs.getSelectedRow();
    String dao=(String)tabla_packs.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtnombre_maestros_packs.getText());
    pst.setString(2, txtprecio_maestros_packs.getText());
   pst.setString(3,Integer.toString(valorMenor()));
   int seleccionado=cboxEstados_maestros_packs.getSelectedIndex();
    pst.setString(4,cboxEstados_maestros_packs.getItemAt(seleccionado));
    pst.setString(5, dao);
    
   pst.execute();
JOptionPane.showMessageDialog(null," Registro Editado ");

 for (int i = 0; i <= articuloselegidos_maestros_packs.getRowCount(); i++) {
         String cantidad;
         int id_articulo =  Integer.parseInt(articuloselegidos_maestros_packs.getValueAt(i,0).toString());
         String articulo =(articuloselegidos_maestros_packs.getValueAt(i,1).toString());
         String stockArticulo =(articuloselegidos_maestros_packs.getValueAt(i,3).toString());
         String sql ="update pack_has_articulo set ART_ID_ARTICULO=?,Pack_ID_PACK=?,HAS_ARTICULO=?,HAS_STOCK_ARTICULOS=?,CANTIDAD=? WHERE Pack_ID_PACK=? ";
        PreparedStatement pstt= con.prepareStatement(sql);

             cantidad= articuloselegidos_maestros_packs.getValueAt(i,2).toString();
  
            int filaSeleccionadoo=tabla_packs.getSelectedRow();
            String daoo= tabla_packs.getValueAt(filaSeleccionadoo, 0).toString();
          //  id_pack=(int) tabla_packs.getValueAt(filaSeleccionado,0);
            //System.out.println("id_pack "+dao);
            int filaSeleccionadooo=tabla_packs.getSelectedRow();
             String daooo=(String)tabla_packs.getValueAt(filaSeleccionadooo, 0);
        
            pstt.setInt(1,id_articulo);
            pstt.setString(2,daoo);
            pstt.setString(3,articulo);
            pstt.setString(4,stockArticulo);
            pstt.setString(5,cantidad);
            pstt.setString(6,daooo);
            pst.execute();
   
 } 
   
}catch(Exception e) {
      
   // JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 }   
 
 //PACK_HAS_ARTICULOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosHasArticulo (){
     try {
     for (int i = 0; i <= articuloselegidos_maestros_packs.getRowCount(); i++) {
         String cantidad;
         int id_articulo =  Integer.parseInt(articuloselegidos_maestros_packs.getValueAt(i,0).toString());
         String articulo =(articuloselegidos_maestros_packs.getValueAt(i,1).toString());
         String stockArticulo =(articuloselegidos_maestros_packs.getValueAt(i,3).toString());
         String SQL ="insert into pack_has_articulo (ART_ID_ARTICULO,Pack_ID_PACK,HAS_ARTICULO,HAS_STOCK_ARTICULOS,CANTIDAD) values(?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(SQL);
            
             
             cantidad= articuloselegidos_maestros_packs.getValueAt(i,2).toString();
            // System.out.println("id_articulo "+id_articulo);
            // System.out.println("cantidad "+cantidad);
             
            int filaSeleccionado=tabla_packs.getSelectedRow();
            String dao= tabla_packs.getValueAt(filaSeleccionado, 0).toString();
          //  id_pack=(int) tabla_packs.getValueAt(filaSeleccionado,0);
            //System.out.println("id_pack "+dao);
            
        
            pst.setInt(1,id_articulo);
            pst.setString(2,dao);
            pst.setString(3,articulo);
            pst.setString(4,stockArticulo);
            pst.setString(5,cantidad);
            pst.execute();
          
          }JOptionPane.showMessageDialog(null,"Registros de articulo exitoso");
              }catch (Exception e){
         //System.out.println("Error al ingresar has_articulo" + e.getMessage());  
          }
 
 }
 public void actualizarDatosHasArticulo (){
   String[] Titulos={"ID Articulo","Articulo","cantidad","Stock articulos","Stock packs"};
     String[] Registros= new String[5];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
             int filaSeleccionado=tabla_packs.getSelectedRow();
            String ID= tabla_packs.getValueAt(filaSeleccionado, 0).toString();
         int  cantidad,stock;
         
     String SQL ="select * from pack_has_articulo where Pack_ID_PACK='"+ID+"' ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             cantidad=rs.getInt("CANTIDAD");
             stock=rs.getInt("HAS_STOCK_ARTICULOS");
             int    valor =stock/cantidad;
             Registros[0]=rs.getString("ART_ID_ARTICULO");
             Registros[1]=rs.getString("HAS_ARTICULO");
             Registros[2]=rs.getString("CANTIDAD");
             Registros[3]=rs.getString("HAS_STOCK_ARTICULOS");
             Registros[4]=Integer.toString(valor);
             
             Modelo.addRow(Registros);
             
         }
         
         articuloselegidos_maestros_packs.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }    
 public void eliminarRegistrosPack1(){
     
 int filaSeleccionada=articuloselegidos_maestros_packs.getSelectedRow();
 int filaSeleccionadaa=tabla_packs.getSelectedRow();
  String   IDArt= (String) articuloselegidos_maestros_packs.getValueAt(filaSeleccionada,0);
  String   IDPac= (String) tabla_packs.getValueAt(filaSeleccionadaa,0);

  
     System.out.println("Arti  "+IDArt);
     System.out.println("pack "+IDPac);
 try{
    String SQL="delete from pack_has_articulo where ART_ID_ARTICULO='"+IDArt+"'AND Pack_ID_PACK='"+IDPac+"'";
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
  JOptionPane.showMessageDialog(null,"Error al eliminar Registros"+e.getMessage());   
 }
     
 } 
 
 //CATEGORIA_VENTASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void insertarDatosCatVentas(){
 
 try{
    
    String SQL ="insert into estados_venta(EST_DESCRIPCION,EST_ACTIVADO_DESACTIVADO) values(?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtcategoria_maestros_catventas.getText());
    int seleccionado=cbcategoria_maestros_catventas.getSelectedIndex();
    pst.setString(2,cbcategoria_maestros_catventas.getItemAt(seleccionado));
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
}
 }   
 public void limpiarCajasCatVentas(){
    txtcategoria_maestros_catventas.setText("");
    cbcategoria_maestros_catventas.setSelectedItem(null);
   
}  
 public void actualizarDatosCatVentas(){
     
 try{
      
    String SQL ="update estados_venta set EST_DESCRIPCION=?,EST_ACTIVADO_DESACTIVADO=? where EST_ID_ESTADO=? ";
    
     int filaSeleccionado=categorias_ventas_registradas.getSelectedRow();
    String dao=(String)categorias_ventas_registradas.getValueAt(filaSeleccionado, 0);
    
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtcategoria_maestros_catventas.getText());
    int seleccionado=cbcategoria_maestros_catventas.getSelectedIndex();
    pst.setString(2,cbcategoria_maestros_catventas.getItemAt(seleccionado));
    pst.setString(3, dao);
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void mostrarDatosCatVentas(){
  
      String[] titulos={"ID Estado","Estados de venta","Estado"};
      String[] registros =new String[3];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from estados_venta";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("EST_ID_ESTADO");
             registros[1]=rs.getString("EST_DESCRIPCION"); 
             registros[2]=rs.getString("EST_ACTIVADO_DESACTIVADO"); 
             modelo.addRow(registros);
             
         }
         
         categorias_ventas_registradas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     }
  
  } 
 public void filtrarDatosCatVentas(String valor){
  
      String[] titulos={"ID Estado","Estados de venta"};
      String[] registros =new String[2];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="select * from estados_venta where EST_DESCRIPCION like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("EST_ID_ESTADO");
             registros[1]=rs.getString("EST_DESCRIPCION"); 
             registros[2]=rs.getString("EST_ACTIVADO_DESACTIVADO");
             modelo.addRow(registros);
             
         }
         
          categorias_ventas_registradas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar bancos "+e.getMessage());
     }
      
  } 
 
  //VENTASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void limpiarDatosSolicitante(){
     txtrut_ventas_venta.setText("");
     txtfono_ventas_venta.setText("");
     txtnpedido_ventas_venta.setText("");
     txtemail_ventas_venta.setText("");
     txtncliente_ventas_venta.setText("");
     
     
     
 } 
 public void llenar_comboPacks(){
 cboxpacks_ventas_venta.removeAllItems();
 
 try {
     
     
   String q ="SELECT * FROM pack where PCK_ACTIVADO_DESACTIVADO='Activado'";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
         
          cboxpacks_ventas_venta.addItem(rs.getString("PCK_NOMBRE"));   
         
         }
     } catch (Exception e) {
         System.out.println("Incorrecto  "+e.getMessage());
     }
  }
 public void llenar_comboComunas(){
 cboxcomunas_ventas_venta.removeAllItems();
 try {
   String q ="SELECT * FROM comunas where COM_ACTIVADO_DESACTIVADO='Activado'";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
             
          cboxcomunas_ventas_venta.addItem(rs.getString("COM_DESCRIPCION"));   
          //cboxcategoria_maestros_articulos1.addItem(rs.getString("CAT_DESCRIPCION"));
         //cboxcategoria_maestros_articulos1.addItem(rs.getString("ID_CATEGORIA"));
         }
     } catch (Exception e) {
         System.out.println("Incorrecto "+e.getMessage());
     }
 
 }
 public void llenar_comboRRSS(){
 cboxRRSS_ventas_venta.removeAllItems();
 try {
   String q ="SELECT * FROM rrss where RRS_ACTIVADO_DESACTIVADO='Activado'";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
             
          cboxRRSS_ventas_venta.addItem(rs.getString("RRS_NOMBRE"));    
         }
     } catch (Exception e) {
         System.out.println("Incorrecto  "+e.getMessage());
     }
  }
 public void llenar_comboEstado(){
 cboxEstados_ventas_venta.removeAllItems();
 cbonestados_ventas_confirmacion.removeAllItems();
 cboxestado_ventas_adespacho.removeAllItems();
 
 try {
   String q ="SELECT * FROM estados_venta ";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
             
          cboxEstados_ventas_venta.addItem(rs.getString("EST_DESCRIPCION"));   
          cbonestados_ventas_confirmacion.addItem(rs.getString("EST_DESCRIPCION"));
          cboxestado_ventas_adespacho.addItem(rs.getString("EST_DESCRIPCION"));
          
         }
     } catch (Exception e) {
         System.out.println("Incorrecto  "+e.getMessage());
     }
  }
 public void InsertarDatosDestinatario(){
 
 try {
    //String SQL ="insert into venta (VTA_NOMBRE_DESTINATARIO,VTA_FECHA_ENTREGA,VTA_DIRECCION_DESTINATARIO,Comunas_COM_ID_COMUNA,VTA_SALUDO,PCK_ID_PACK,VTA_HORA_ENTREGA_INICIAL,VTA_HORA_ENTREGA_FINAL,RRSS_RRS_ID_RRSS,ESTADOS_VENTA_EST_ID_ESTADO,VTA_TOTAL,cliente_CLI_RUT_CLIENTE,VTA_TELEFONO,VTA_CORREO) values(?,?,?,(select COM_ID_COMUNA from comunas where COM_DESCRIPCION=? ),?,(select PCK_ID_PACK from pack where PCK_NOMBRE=? ),?,?,(select RRS_ID_RRSS from rrss where RRS_NOMBRE=? ),(select EST_ID_ESTADO from estados_venta where EST_DESCRIPCION=? ),?,?,?,?)";
    String SQL ="insert into venta (cliente_CLI_RUT_CLIENTE,PCK_ID_PACK,BAN_ID_BANCO,RRSS_RRS_ID_RRSS,ESTADOS_VENTA_EST_ID_ESTADO,Comunas_COM_ID_COMUNA,VTA_TOTAL,VTA_FECHA_VENTA,VTA_FECHA_TRANSFERENCIA,VTA_CODIGO_TRANSFERENCIA,VTA_NOMBRE_DESTINATARIO,VTA_DIRECCION_DESTINATARIO,VTA_TELEFONO,VTA_CORREO,VTA_FECHA_ENTREGA,VTA_HORA_ENTREGA_INICIAL,VTA_HORA_ENTREGA_FINAL,VTA_SALUDO,VTA_NOMBRE_CLIENTE) values(?,(select PCK_ID_PACK from pack where PCK_NOMBRE=? ),(select BAN_ID_BANCO from bancos where BAN_DESCRIPCION=? ),(select RRS_ID_RRSS from rrss where RRS_NOMBRE=? ),(select EST_ID_ESTADO from estados_venta where EST_DESCRIPCION=? ),(select COM_ID_COMUNA from comunas where COM_DESCRIPCION=? ),?,?,?,?,?,?,?,?,?,?,?,?,?)";	
    PreparedStatement Pst= con.prepareStatement(SQL);
    
    Pst.setString(1,txtrut_ventas_venta.getText());
    
    int seleccionadoo=cboxpacks_ventas_venta.getSelectedIndex();		
    Pst.setString(2,cboxpacks_ventas_venta.getItemAt(seleccionadoo));
    
     int seleccionadooi=cboxbanco_ventas_confirmacion.getSelectedIndex();
     Pst.setString(3,cboxbanco_ventas_confirmacion.getItemAt(seleccionadooi));
     
     int seleccionadooo=cboxRRSS_ventas_venta.getSelectedIndex();		
    Pst.setString(4,cboxRRSS_ventas_venta.getItemAt(seleccionadooo));
    
    
    int seleccionadoooo=cboxEstados_ventas_venta.getSelectedIndex();		
    Pst.setString(5,cboxEstados_ventas_venta.getItemAt(seleccionadoooo));
    
     int seleccionado=cboxcomunas_ventas_venta.getSelectedIndex();
    Pst.setString(6,cboxcomunas_ventas_venta.getItemAt(seleccionado));
    
    Pst.setString(7,txttotal_ventas_venta.getText());
    Pst.setString(8,((JTextField)jDfventa_ventas_venta.getDateEditor().getUiComponent()).getText());
    Pst.setString(9,null);
    Pst.setInt(10,0);
    Pst.setString(11,txtdestinatario_ventas_venta.getText());
    Pst.setString(12,txtdireccion_ventas_venta.getText());
    Pst.setString(13,txtfono_ventas_venta.getText());
    Pst.setString(14,txtemail_ventas_venta.getText());
    Pst.setString(15,((JTextField)jDfentrega_ventas_venta.getDateEditor().getUiComponent()).getText());
    Pst.setString(16,txthorai_ventas_venta.getText());
    Pst.setString(17,txthoraf_ventas_venta.getText());
    Pst.setString(18,txtsaludo_ventas_venta.getText());
    Pst.setString(19,txtncliente_ventas_venta.getText());
    Pst.execute();
        JOptionPane.showMessageDialog(null,"Datos de venta ingresados correctamente");
     
 }catch (Exception e) {
     
     JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
     
 }   
  }
 public void llenar_comboBanco(){
 cboxbanco_ventas_confirmacion.removeAllItems();
 try {
   String q ="SELECT * FROM bancos";
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
             
          cboxbanco_ventas_confirmacion.addItem(rs.getString("BAN_DESCRIPCION"));   
          
         }
     } catch (Exception e) {
         System.out.println("Incorrecto  "+e.getMessage());
     }
  }
 public void sumaTotal(){
       int a = parseInt(txtsubtotal_ventas_venta.getText());
       int b = parseInt(txtenvio_ventas_venta.getText());
      int suma = (a+b);
       String  suma1=String.valueOf(suma) ;
      txttotal_ventas_venta.setText(suma1);
      
       
 }
 public void MostrarDatosVentas(){
     
     String[] Titulos={"ID Venta","Fecha Venta","Nombre Cliente","Número de celular","Precio","Pack","Estado"};
     String[] Registros= new String[7];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("VTA_FECHA_VENTA");
             Registros[2]=rs.getString("VTA_NOMBRE_CLIENTE");
             Registros[3]=rs.getString("VTA_TELEFONO");
             Registros[4]=rs.getString("VTA_TOTAL");
             Registros[5]=rs.getString("PCK_NOMBRE");
             Registros[6]=rs.getString("EST_DESCRIPCION");
             
             
             Modelo.addRow(Registros);
             
         }
         
         ventas_pendientes_pago.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void limpiarCajasVentas(){
    txtdestinatario_ventas_venta.setText("");
    jDfentrega_ventas_venta.setCalendar(null);
    txtdireccion_ventas_venta.setText("");
    cboxcomunas_ventas_venta.setSelectedItem(null);
    txtsaludo_ventas_venta.setText("");
    cboxpacks_ventas_venta.setSelectedItem(null);
    txthorai_ventas_venta.setText("");
    txthoraf_ventas_venta.setText("");
    cboxRRSS_ventas_venta.setSelectedItem(null);
    txttotal_ventas_venta.setText("");
    cboxEstados_ventas_venta.setSelectedItem(null);
    
}  
 public void limpiarCajasVentasCl(){
    txtncliente_ventas_venta.setText("");
    txtemail_ventas_venta.setText("");
    txtrut_ventas_venta.setText("");
    txtfono_ventas_venta.setText("");
    jDfventa_ventas_venta.setCalendar(null);
      
}  
 public void llenarIDVentas(){
 
     String[] Registros= new String[4];
     
        String valor=txtnpedido_ventas_confirmacion.getText();
     
     String SQL ="select *,venta.ESTADOS_VENTA_EST_ID_ESTADO from venta JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO where VTA_ID_VENTA like '%"+valor+"%' ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("VTA_NOMBRE_CLIENTE");
             Registros[2]=rs.getString("cliente_CLI_RUT_CLIENTE");
             Registros[3]=rs.getString("EST_DESCRIPCION");
           
             
             if (valor.equals(Registros[0])) {
                 
                 txtrut_ventas_confirmacion.setText(Registros[2]);
                 txtnclte_ventas_confirmacion.setText(Registros[1]);
                 cbonestados_ventas_confirmacion.setSelectedItem(Registros[3]);
                 
             }
             
             
         }
         
         
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
  
     }
 }
 public void actualizarDatosVentas(){
     
 try{
      
    String SQL ="update venta set BAN_ID_BANCO=?,VTA_FECHA_TRANSFERENCIA=?,VTA_CODIGO_TRANSFERENCIA=?,ESTADOS_VENTA_EST_ID_ESTADO=? where VTA_ID_VENTA=? ";
    
     int filaSeleccionado=ventas_pendientes_pago.getSelectedRow();
    String dao=(String)ventas_pendientes_pago.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1,txtbanco_ventas_confirmacion.getText());
    pst.setString(2,((JTextField)txtfpago_ventas_confirmacion.getDateEditor().getUiComponent()).getText());
    pst.setString(3,txttransaccion_ventas_confirmacion.getText());
    pst.setString(4,txtestados_ventas_confirmacion.getText());
    pst.setString(5, dao);
    
   pst.execute();

 
    JOptionPane.showMessageDialog(null," Registro Editado ");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 } 
 public void limpiarCajasConfirmacion(){
    txtnpedido_ventas_confirmacion.setText("");
    txtrut_ventas_confirmacion.setText("");
    cboxbanco_ventas_confirmacion.setSelectedItem(null);
    txtbanco_ventas_confirmacion.setText("");
    txtfpago_ventas_confirmacion.setCalendar(null);
    txtnclte_ventas_confirmacion.setText("");
    txttransaccion_ventas_confirmacion.setText("");
    cbonestados_ventas_confirmacion.setSelectedItem(null);
    txtestados_ventas_confirmacion.setText("");
    
}  
  public void numeroMayor(){
 
  ArrayList<Integer> list =new ArrayList<Integer>();
     for (int i = 0; i < ventas_pendientes_pago.getRowCount(); i++) {
        list.add(Integer.parseInt(ventas_pendientes_pago.getValueAt(i, 0).toString()));
         
     }
     int max=Collections.max(list)+1;
     
     String suma= max+"";
   txtnpedido_ventas_venta.setText(suma);
     
 }  
  
 //SOLICITUDES_DE_PEDIDOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS 
 public void limpiar_Tabla_proveedores_compras() {
 
 DefaultTableModel modelo;
 modelo=(DefaultTableModel)proveedores_compra.getModel();
  modelo.removeRow(proveedores_compra.getSelectedRow());
 
 }
     
//LISTA DESTINOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void PDFListaDestino() {
try {
         FileOutputStream archivo;
         File file= new File("src/pdf/venta.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Lista de Destino"+"\n\n");
         doc.add(cli);
 
         PdfPTable tablapro = new PdfPTable(8);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f,10f,10f,10f,10f};
         tablapro .setWidths(Columnapro);
         tablapro .setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("Codigo Venta"));
         PdfPCell pro2 = new PdfPCell(new Phrase("Pack"));
         PdfPCell pro3 = new PdfPCell(new Phrase("Destinatario"));
         PdfPCell pro4 = new PdfPCell(new Phrase("Fecha Entrega"));
         PdfPCell pro5 = new PdfPCell(new Phrase("Comuna"));
         PdfPCell pro6 = new PdfPCell(new Phrase("Dirección"));
         PdfPCell pro7 = new PdfPCell(new Phrase("H. entrega I"));
         PdfPCell pro8 = new PdfPCell(new Phrase("H. entrega F"));

         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro4);
         tablapro.addCell(pro5);
         tablapro.addCell(pro6);
         tablapro.addCell(pro7);
         tablapro.addCell(pro8);
         for (int i = 0; i < listadestino_despacho.getRowCount(); i++) {
        String IdVenta =listadestino_despacho.getValueAt(i,0).toString();
        String Pack =listadestino_despacho.getValueAt(i,1).toString();
        String Destinatario =listadestino_despacho.getValueAt(i,2).toString();
        String FechaEntrega =listadestino_despacho.getValueAt(i,3).toString();
        String Comuna =listadestino_despacho.getValueAt(i,4).toString();
        String Direccion =listadestino_despacho.getValueAt(i,5).toString();
        String horaI =listadestino_despacho.getValueAt(i,6).toString();
        String horaF=listadestino_despacho.getValueAt(i,7).toString();
        
        tablapro.addCell(IdVenta);
        tablapro.addCell(Pack);
        tablapro.addCell(Destinatario);
        tablapro.addCell(FechaEntrega);
        tablapro.addCell(Comuna);
        tablapro.addCell(Direccion);
        tablapro.addCell(horaI);
        tablapro.addCell(horaF);

         }
         doc.add(tablapro);
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     }
 }
 public void MostrarDatosListaDestino(){
     
     String[] Titulos={"ID Venta","Pack","Destinatario","Fecha entrega","Comuna","Dirección","Hora entrega inicial","Hora entrega Final"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.Comunas_COM_ID_COMUNA\n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             Registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             Registros[4]=rs.getString("COM_DESCRIPCION");
             Registros[5]=rs.getString("VTA_DIRECCION_DESTINATARIO");
             Registros[6]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             Registros[7]=rs.getString("VTA_HORA_ENTREGA_FINAL");
             
             Modelo.addRow(Registros);
             
         }
         
         listadestino_despacho.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void filtrarDatosListaDestino(String valor){
  
      String[] Titulos={"ID Venta","Pack","Destinatario","Fecha entrega","Comuna","Dirección","Hora entrega inicial","Hora entrega Final"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.Comunas_COM_ID_COMUNA\n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA"+
                 " where VTA_FECHA_ENTREGA like '%"+valor+"%' ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             Registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             Registros[4]=rs.getString("COM_DESCRIPCION");
             Registros[5]=rs.getString("VTA_DIRECCION_DESTINATARIO");
             Registros[6]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             Registros[7]=rs.getString("VTA_HORA_ENTREGA_FINAL");
             
             Modelo.addRow(Registros);
         }
         
          listadestino_despacho.setModel(Modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar bancos "+e.getMessage());
     }
      
  } 
 
 //ACTUALIZACION DESPACHOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void MostrarDatosActualizacionDespachos(){
     
     String[] Titulos={"ID Venta","Pack","Destinatario","Fecha entrega","Comuna","Hora entrega inicial","Hora entrega Final","Estado"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA \n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK \n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             Registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             Registros[4]=rs.getString("COM_DESCRIPCION");
             Registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             Registros[6]=rs.getString("VTA_HORA_ENTREGA_FINAL");
             Registros[7]=rs.getString("EST_DESCRIPCION");
             
             Modelo.addRow(Registros);
             
         }
         
         detalle_dev_cambios.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void ActualizarDatosActualizacionDespacho(){
     try {
         
         String SQL ="update venta set  ESTADOS_VENTA_EST_ID_ESTADO=? where VTA_ID_VENTA=?";
         
         int filaSeleccionado=detalle_dev_cambios.getSelectedRow();
         String dao=(String)detalle_dev_cambios.getValueAt(filaSeleccionado, 0);
         PreparedStatement pst= con.prepareStatement(SQL);
         
         pst.setString(1,txtestado_ventas_adespacho.getText());
         
         pst.setString(2, dao);
        pst.execute();
        
        
         JOptionPane.showMessageDialog(null,"Estado editado correctamente");
         
     }catch (Exception e) {
         
         JOptionPane.showMessageDialog(null,"Error en Edicion"+e.getMessage());
         
         
     }
 }
 public void filtrarDatosActualizacionDespachoFecha(String valor){
     
     String[] Titulos={"ID Venta","Pack","Destinatario","Fecha entrega","Comuna","Hora entrega inicial","Hora entrega Final","Estado"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA \n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK \n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                 " where VTA_FECHA_ENTREGA like '%"+valor+"%' ";
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             Registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             Registros[4]=rs.getString("COM_DESCRIPCION");
             Registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             Registros[6]=rs.getString("VTA_HORA_ENTREGA_FINAL");
             Registros[7]=rs.getString("EST_DESCRIPCION");
             
             Modelo.addRow(Registros);
             
         }
         
         detalle_dev_cambios.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void filtrarDatosActualizacionDespachoID (String valor){
     
     String[] Titulos={"ID Venta","Pack","Destinatario","Fecha entrega","Comuna","Hora entrega inicial","Hora entrega Final","Estado"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA \n" +
                 "FROM venta\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK \n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                 " where VTA_ID_VENTA like '%"+valor+"%' ";
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("VTA_ID_VENTA");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             Registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             Registros[4]=rs.getString("COM_DESCRIPCION");
             Registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             Registros[6]=rs.getString("VTA_HORA_ENTREGA_FINAL");
             Registros[7]=rs.getString("EST_DESCRIPCION");
             
             Modelo.addRow(Registros);
             
         }
         
         detalle_dev_cambios.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 
 //SOLICITUD DE PEDIDOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void llenar_TablaProveedores(){
    
    DefaultTableModel modelo;
 modelo=(DefaultTableModel)listapedido_compras_solicitudes.getModel();
  modelo.getDataVector().removeAllElements();  
     
  String[] Titulos={"ID Articulo","Articulo","Stock Articulos"};
     String[] Registros= new String[3];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     DefaultTableModel Modeloo=new DefaultTableModel (null,Titulos);
     String q ="SELECT * FROM articulo where ART_ACTIVADO_DESACTIVADO='Activado'";
     
 try {
   
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(q);
    
         while(rs.next()){
          Registros[0]=rs.getString("ART_ID_ARTICULO");   
          Registros[1]=rs.getString("ART_DESCRIPCION");     
          Registros[2]=rs.getString("ART_UNIDADES");
          Modelo.addRow(Registros);
         }
           listapedido_compras_solicitudes.setModel (Modelo);
          
     } catch (Exception e) {
         System.out.println("Problemas al mostrar datos "+e.getMessage());
     }
    
 
 }
 public void sumaCantidades (){
    int suma=0;
       int contar=listaCompras_solicitudes.getRowCount();
       for (int i = 0; i < contar; i++) {
                 suma = suma+Integer.parseInt( listaCompras_solicitudes.getValueAt(i,2).toString());
               }
       
     String sumaa=suma+"";
 txtcantidad_compras_solicitudes.setText(sumaa);
 }
 public void InsertarDatosCompras(){
   DefaultTableModel modelo;
   String id_proveedor;
   
              
 try {
   
    String SQL ="insert into orden_compra (ORC_ID_PROVEEDOR,ORC_NUMERO,ORC_FECHA_ORDEN) values(?,?,?)";	
    PreparedStatement Pst= con.prepareStatement(SQL);
    
    int fila=proveedores_compra.getSelectedRow();
    id_proveedor=proveedores_compra.getValueAt(fila,0).toString();
    Pst.setString(1,id_proveedor);
    Pst.setString(2,txtcantidad_compras_solicitudes.getText());
    Pst.setString(3,((JTextField)datepedido_compras_solicitudes.getDateEditor().getUiComponent()).getText());
   
   
    
    Pst.execute();
    
    
    
        JOptionPane.showMessageDialog(null,"Solicitud de pedido ingresado correctamente");
     
 }catch (Exception e) {
     
     JOptionPane.showMessageDialog(null,"Registro Fallido"+e.getMessage());
     
 }   
  }
 public void insertarDatosHasArticuloSolPedidos (){
     int i;
     try {
     for ( i = 0; i <= listaCompras_solicitudes.getRowCount(); i++) {
         
          String SQL ="insert into orden_compra_detalle (OCD_ID_ARTICULO,OCD_ID_ORDEN,OCD_NOMBRE,OCD_CANTIDAD,DET_VALOR) values(?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(SQL);
         
         String id_articulo =listaCompras_solicitudes.getValueAt(i,0).toString();
         String nombre =listaCompras_solicitudes.getValueAt(i,1).toString();
         String id_orden=txtnpedido_compras_solicitudes.getText();
         String cantidad =listaCompras_solicitudes.getValueAt(i,2).toString();
         String precio=listaCompras_solicitudes.getValueAt(i,3).toString();
          
          //  id_pack=(int) tabla_packs.getValueAt(filaSeleccionado,0);
            //System.out.println("id_pack "+dao);
           // listaCompras_solicitudes.getRowCount()
        
            pst.setString(1,id_articulo);
            pst.setString(2,id_orden);
            pst.setString(3,nombre);
            pst.setString(4,cantidad);
            pst.setString(5,precio);
            
            pst.execute();
          
          }
              }catch (Exception e){
         //System.out.println("Error al ingresar has_articulo" + e.getMessage());  
          }
 
 }
 public void numeroMayorCompras(){
 
  ArrayList<Integer> list =new ArrayList<Integer>();
     for (int i = 0; i < detalle_pedidos_realizados.getRowCount(); i++) {
        list.add(Integer.parseInt(detalle_pedidos_realizados.getValueAt(i, 0).toString()));
         
     }
     int max=Collections.max(list)+1;
     
     String suma= max+"";
   txtnpedido_compras_solicitudes.setText(suma);
     
 }  
 public void MostrarDatosSolPedidos(){
     
     String[] Titulos={"ID pedido","Nombre proovedor","Cantidad de Articulos","Fecha pedido"};
     String[] Registros= new String[4];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,orden_compra.ORC_ID_PROVEEDOR\n" +
                 "FROM orden_compra\n" +
                 "JOIN proveedor ON proveedor.PRO_RUT_PROVEEDOR=orden_compra.ORC_ID_PROVEEDOR";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("ORC_ID_ORDEN");
             Registros[1]=rs.getString("PRO_NOMBRE");
             Registros[2]=rs.getString("ORC_NUMERO");
             Registros[3]=rs.getString("ORC_FECHA_ORDEN");

             
             Modelo.addRow(Registros);
             
         }
         
         detalle_pedidos_realizados.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void actualizarDatosClikerSolPedidos (){
   String[] Titulos={"ID Articulo","Articulo","Cantidad","Precio"};
     String[] Registros= new String[4];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
             int filaSeleccionado=detalle_pedidos_realizados.getSelectedRow();
            String ID= detalle_pedidos_realizados.getValueAt(filaSeleccionado, 0).toString();
         int  cantidad,stock=0;
         
     String SQL ="select * from orden_compra_detalle where OCD_ID_ORDEN='"+ID+"' ";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             Registros[0]=rs.getString("OCD_ID_ARTICULO");
             Registros[1]=rs.getString("OCD_NOMBRE");
             Registros[2]=rs.getString("OCD_CANTIDAD");
             Registros[3]=rs.getString("DET_VALOR");
             
             
             Modelo.addRow(Registros);
             
         }
         
         listaCompras_solicitudes.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }    
 public void actualizarDatosSolPedidos(){
     
 try{
      
    String SQL ="update orden_compra set ORC_NUMERO=?,ORC_FECHA_ORDEN=? where ORC_ID_ORDEN=? ";
    
     int filaSeleccionado=detalle_pedidos_realizados.getSelectedRow();
    String dao=(String)detalle_pedidos_realizados.getValueAt(filaSeleccionado, 0);
    PreparedStatement pst= con.prepareStatement(SQL);
    
    pst.setString(1, txtcantidad_compras_solicitudes.getText());
    pst.setString(2,((JTextField)datepedido_compras_solicitudes.getDateEditor().getUiComponent()).getText());

    pst.setString(3, dao);
    
   pst.execute();
JOptionPane.showMessageDialog(null," Registro Editado ");
 for (int i = 0; i <= listaCompras_solicitudes.getRowCount(); i++) {
         String cantidad;
         int id_articulo =  Integer.parseInt(listaCompras_solicitudes.getValueAt(i,0).toString());
         String articulo =(listaCompras_solicitudes.getValueAt(i,1).toString());
         String precio=(listaCompras_solicitudes.getValueAt(i,3).toString());
         
         String sql ="update orden_compra_detalle set OCD_ID_ARTICULO=?,OCD_NOMBRE=?,OCD_CANTIDAD=?,DET_VALOR=? WHERE OCD_ID_ORDEN=? and OCD_ID_ARTICULO=? ";
        PreparedStatement pstt= con.prepareStatement(sql);
     

             cantidad= listaCompras_solicitudes.getValueAt(i,2).toString();
  
            int filaSeleccionadoo=detalle_pedidos_realizados.getSelectedRow();
            String daoo= detalle_pedidos_realizados.getValueAt(filaSeleccionadoo, 0).toString();
            //int filaSeleccionadooo=tabla_packs.getSelectedRow();
           // String daooo=(String)tabla_packs.getValueAt(filaSeleccionadooo, 0);
        
            pstt.setInt(1,id_articulo);
            pstt.setString(2,articulo);
            pstt.setString(3,cantidad);
            pstt.setString(4,precio);
            pstt.setString(5,daoo);
            pstt.setInt(6,id_articulo);
            pstt.execute();
   
 } 
   

}catch(Exception e) {
      
    //JOptionPane.showMessageDialog(null,"Error de Edicion "+e.getMessage());
}
 }  
 public void eliminarRegistrosSolHasPedidos(){
 int filaSeleccionada=detalle_pedidos_realizados.getSelectedRow();
 
 try{
    String SQL="delete from orden_compra_detalle where OCD_ID_ORDEN="+detalle_pedidos_realizados.getValueAt(filaSeleccionada,0);
    
    Statement st=con.createStatement();
    
    int n = st.executeUpdate(SQL);
  if(n>=0){
  JOptionPane.showMessageDialog(null,"Registro Eliminado");
  }
    
 }catch(Exception e){
    
 }
     
 } 
 public void limpiar_TablaCompras() {
 
 DefaultTableModel modelo;
 modelo=(DefaultTableModel)listaCompras_solicitudes.getModel();
  modelo.removeRow(listaCompras_solicitudes.getSelectedRow());
  
     
 }
 public void insertarDatosHasArticuloSolPedidosArticulos(){
     int i;
     
     try {
         
        String sql ="update orden_compra set ORC_NUMERO=?,ORC_FECHA_ORDEN=? where ORC_ID_ORDEN=? ";
    
     int filaSeleccionado=detalle_pedidos_realizados.getSelectedRow();
    String dao=(String)detalle_pedidos_realizados.getValueAt(filaSeleccionado, 0);
    PreparedStatement psttt= con.prepareStatement(sql);
    
    psttt.setString(1, txtcantidad_compras_solicitudes.getText());
    psttt.setString(2,((JTextField)datepedido_compras_solicitudes.getDateEditor().getUiComponent()).getText());

    psttt.setString(3, dao);
    
   psttt.execute();  
   
     for ( i = 0; i <= listaCompras_solicitudes.getRowCount(); i++) {
         
          String SQL ="insert into orden_compra_detalle (OCD_ID_ARTICULO,OCD_ID_ORDEN,OCD_NOMBRE,OCD_CANTIDAD,DET_VALOR) values(?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(SQL);
         
         String id_articulo =listaCompras_solicitudes.getValueAt(i,0).toString();
         String nombre =listaCompras_solicitudes.getValueAt(i,1).toString();
         int fila=detalle_pedidos_realizados.getSelectedRow();
         String id_orden=detalle_pedidos_realizados.getValueAt(fila,0).toString();  
         String cantidad =listaCompras_solicitudes.getValueAt(i,2).toString();
         String precio=listaCompras_solicitudes.getValueAt(i,3).toString();
          
          //  id_pack=(int) tabla_packs.getValueAt(filaSeleccionado,0);
            //System.out.println("id_pack "+dao);
           // listaCompras_solicitudes.getRowCount()
        
            pst.setString(1,id_articulo);
            pst.setString(2,id_orden);
            pst.setString(3,nombre);
            pst.setString(4,cantidad);
            pst.setString(5,precio);
            
            pst.execute();
          
          }
              }catch (Exception e){
         //System.out.println("Error al ingresar has_articulo" + e.getMessage());  
          }
 
 }
 public void PDFSolicitudPedidos() {
try {
         FileOutputStream archivo;
         File file= new File("src/pdf/Solicitud de pedidos.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Orden de compra"+"\n\n");
         doc.add(cli);
 
         
         PdfPTable tablapro = new PdfPTable(4);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f};
         tablapro.setWidths(Columnapro);
         tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("Proveedor"));
         PdfPCell pro2 = new PdfPCell(new Phrase("Articulo"));
         PdfPCell pro3 = new PdfPCell(new Phrase("Cantidad"));
         PdfPCell pro5 = new PdfPCell(new Phrase("Fecha pedido"));


         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro5);

         for (int i = 0; i < listaCompras_solicitudes.getRowCount(); i++) {
          int fila= detalle_pedidos_realizados.getSelectedRow();
        String IdVenta =detalle_pedidos_realizados.getValueAt(fila,1).toString();
        String Pack =listaCompras_solicitudes.getValueAt(i,1).toString();
        String Destinatario =listaCompras_solicitudes.getValueAt(i,2).toString();
        String Comuna =detalle_pedidos_realizados.getValueAt(fila,3).toString();

        
        tablapro.addCell(IdVenta);
        tablapro.addCell(Pack);
        tablapro.addCell(Destinatario);
        tablapro.addCell(Comuna);


         }
          doc.add(tablapro);
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     }
 }
 
//INFORME CLIENTESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSsss
public void mostrarDatosPack(){
     
     String[] Titulos={"Codigo pack","Nombre pack","Fecha del pedido","Nombre cliente","Estados","Comuna"};
     String[] Registros= new String[6];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.cliente_CLI_RUT_CLIENTE,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                 "FROM `venta`\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN cliente ON cliente.CLI_RUT_CLIENTE=venta.cliente_CLI_RUT_CLIENTE\n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas  ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA"; 
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PCK_ID_PACK");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_FECHA_VENTA");
             Registros[3]=rs.getString("CLI_NOMBRE");
             Registros[4]=rs.getString("EST_DESCRIPCION");
             Registros[5]=rs.getString("COM_DESCRIPCION");
             
             Modelo.addRow(Registros);  
         }
         
         ventas_realizada_infoclientes.setModel (Modelo); 
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());  
     }
 }      
public void BuscarDatosPack(String buscarRut){
     
     String[] Titulos={"Codigo pack","Nombre pack","Fecha del pedido","Nombre cliente","Estados","Comuna"};
     String[] Registros= new String[6];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.cliente_CLI_RUT_CLIENTE,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                 "FROM `venta`\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN cliente ON cliente.CLI_RUT_CLIENTE=venta.cliente_CLI_RUT_CLIENTE\n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas  ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                 "where CLI_RUT_CLIENTE LIKE  '%"+buscarRut+"%' "; 
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PCK_ID_PACK");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_FECHA_VENTA");
             Registros[3]=rs.getString("CLI_NOMBRE");
             Registros[4]=rs.getString("EST_DESCRIPCION");
             Registros[5]=rs.getString("COM_DESCRIPCION");
             
             Modelo.addRow(Registros);  
         }
         
         ventas_realizada_infoclientes.setModel (Modelo); 
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());  
     }
 }  
public void filtrarDatosPorFechaInforCliente(String fecha1, String fecha2){
     String[] Titulos={"Codigo pack","Nombre pack","Fecha del pedido","Nombre cliente","Estados","Comuna"};
     String[] Registros= new String[6];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,venta.PCK_ID_PACK,venta.cliente_CLI_RUT_CLIENTE,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                 "FROM `venta`\n" +
                 "JOIN pack ON pack.PCK_ID_PACK=venta.PCK_ID_PACK\n" +
                 "JOIN cliente ON cliente.CLI_RUT_CLIENTE=venta.cliente_CLI_RUT_CLIENTE\n" +
                 "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                 "JOIN comunas  ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                 "where VTA_FECHA_VENTA BETWEEN '"+fecha1+"' AND '"+fecha2+"' ";
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("PCK_ID_PACK");
             Registros[1]=rs.getString("PCK_NOMBRE");
             Registros[2]=rs.getString("VTA_FECHA_VENTA");
             Registros[3]=rs.getString("CLI_NOMBRE");
             Registros[4]=rs.getString("EST_DESCRIPCION");
             Registros[5]=rs.getString("COM_DESCRIPCION");
             
             Modelo.addRow(Registros);  
         }
         
         ventas_realizada_infoclientes.setModel (Modelo); 
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());  
     }
   
 }
 public void limpiarCajaInforCliente(){
     desde_informe_cliente.setCalendar(null);
     hasta_informe_cliente.setCalendar(null);
     txtrut_informes_infoclientes.setText("");
     
 }
 public void PDFimformesClientes(){
 try {
         FileOutputStream archivo;
         File file= new File("src/pdf/Informe Clientes.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Informe Clientes"+"\n\n");
         doc.add(cli);
 
         PdfPTable tablapro = new PdfPTable(6);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f,10f,10f};
         tablapro .setWidths(Columnapro);
         tablapro .setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("Codigo Pack"));
         PdfPCell pro2 = new PdfPCell(new Phrase("Pack"));
         PdfPCell pro3 = new PdfPCell(new Phrase("Cliente"));
         PdfPCell pro4 = new PdfPCell(new Phrase("Fecha pedido"));
         PdfPCell pro5 = new PdfPCell(new Phrase("Comuna"));
         PdfPCell pro6 = new PdfPCell(new Phrase("Estado"));
        
         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro4);
         tablapro.addCell(pro5);
         tablapro.addCell(pro6);

         
         for (int i = 0; i < ventas_realizada_infoclientes.getRowCount(); i++) {
        String Codigo_Pack =ventas_realizada_infoclientes.getValueAt(i,0).toString();
        String Pack =ventas_realizada_infoclientes.getValueAt(i,1).toString();
        String Cliente =ventas_realizada_infoclientes.getValueAt(i,2).toString();
        String Fecha_pedido =ventas_realizada_infoclientes.getValueAt(i,3).toString();
        String Comuna =ventas_realizada_infoclientes.getValueAt(i,4).toString();
        String Estado =ventas_realizada_infoclientes.getValueAt(i,5).toString();
        
        
        tablapro.addCell(Codigo_Pack);
        tablapro.addCell(Pack);
        tablapro.addCell(Cliente);
        tablapro.addCell(Fecha_pedido);
        tablapro.addCell(Comuna);
        tablapro.addCell(Estado);

         }
         doc.add(tablapro);
         
         
         
         
         
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     
     }      
 }
 
//REVISION DE FACTURASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 
 public void FiltrarDatosPorNumeroFactura(String valor){
    
     String[] titulos={"ID Factura","Rut Proveedor","Razon Social","Numero Factura","Fecha","subtotal","iva","total"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *, factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "from factura\n" +
                 "JOIN proveedor ON proveedor.PRO_RUT_PROVEEDOR=factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "WHERE FAC_NUMERO like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("FAC_ID_FACTURA");
             registros[1]=rs.getString("proveedor_PRO_RUT_PROVEEDOR"); 
             registros[2]=rs.getString("PRO_RAZON");
             registros[3]=rs.getString("FAC_NUMERO");
             registros[4]=rs.getString("FAC_FECHA_FACTURA");
             registros[5]=rs.getString("FAC_SUBTOTAL");
             registros[6]=rs.getString("FAC_IVA");
             registros[7]=rs.getString("FAC_TOTAL");
             modelo.addRow(registros);
             
         }
         
          facturas_compras_inventariadas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }
       
 }
 public void FiltrarDatosPorRut(String valor){
    
     
     String[] titulos={"ID Factura","Rut Proveedor","Razon Social","Numero Factura","Fecha","subtotal","iva","total"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *, factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "from factura\n" +
                 "JOIN proveedor ON proveedor.PRO_RUT_PROVEEDOR=factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "WHERE proveedor_PRO_RUT_PROVEEDOR like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("FAC_ID_FACTURA");
             registros[1]=rs.getString("proveedor_PRO_RUT_PROVEEDOR"); 
             registros[2]=rs.getString("PRO_RAZON");
             registros[3]=rs.getString("FAC_NUMERO");
             registros[4]=rs.getString("FAC_FECHA_FACTURA");
             registros[5]=rs.getString("FAC_SUBTOTAL");
             registros[6]=rs.getString("FAC_IVA");
             registros[7]=rs.getString("FAC_TOTAL");
             modelo.addRow(registros);
             
         }
         
          facturas_compras_inventariadas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }
 }
 public void FiltrarDatosPorFecha(String valor){
     
     String[] titulos={"ID Factura","Rut Proveedor","Razon Social","Numero Factura","Fecha","subtotal","iva","total"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *, factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "from factura\n" +
                 "JOIN proveedor ON proveedor.PRO_RUT_PROVEEDOR=factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "WHERE FAC_FECHA_FACTURA like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("FAC_ID_FACTURA");
             registros[1]=rs.getString("proveedor_PRO_RUT_PROVEEDOR"); 
             registros[2]=rs.getString("PRO_RAZON");
             registros[3]=rs.getString("FAC_NUMERO");
             registros[4]=rs.getString("FAC_FECHA_FACTURA");
              registros[5]=rs.getString("FAC_SUBTOTAL");
             registros[6]=rs.getString("FAC_IVA");
             registros[7]=rs.getString("FAC_TOTAL");
             modelo.addRow(registros);
             
         }
         
          facturas_compras_inventariadas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
 }
 public void limpiarCajasRevision(){
     txtnfactura_compras_revision.setText("");
     txtfrecepcion_compras_revision.setText("");
     txtrut_compras_revision.setText("");
     
 }
 public void mostrarDatoDetalle(String valor){
      String[] Titulos={"idFactura","factura","codigo","articulo","unidades","subtotal","iva","total"};
     String[] Registros= new String[8];
     DefaultTableModel Modelo=new DefaultTableModel (null,Titulos);
     
     String SQL ="SELECT *,factura.FAC_ID_FACTURA, facturadetalle.ARTICULO_ART_ID_ARTICULO\n" +
                  "FROM `factura`\n" +
                  "JOIN facturadetalle ON factura.FAC_ID_FACTURA=facturadetalle.FAC_ID_FACTURA\n" +
             "JOIN articulo ON articulo.ART_ID_ARTICULO=facturadetalle.ARTICULO_ART_ID_ARTICULO\n" +
              "WHERE FAC_NUMERO like '%"+valor+"%'";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()){
             
             Registros[0]=rs.getString("FAC_ID_FACTURA");
             Registros[1]=rs.getString("FAC_NUMERO");
             Registros[2]=rs.getString("ARTICULO_ART_ID_ARTICULO");
             Registros[3]=rs.getString("ART_DESCRIPCION");
             Registros[4]=rs.getString("DET_CANTIDAD");
             Registros[5]=rs.getString("DET_SUBTOTAL");
             Registros[6]=rs.getString("DET_IVA");
             Registros[7]=rs.getString("DET_TOTAL");
             
             Modelo.addRow(Registros);
             
         }
         
       detalle_factura_compras_revision.setModel (Modelo);
         
     }catch (Exception e){
         JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
     
     
         
     }
 }   
 public void mostrarDatoFactura(){
    
     String[] titulos={"ID Factura","Rut Proveedor","Razon Social","Numero Factura","Fecha","subtotal","iva","total"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *, factura.proveedor_PRO_RUT_PROVEEDOR\n" +
                 "from factura\n" +
                 "JOIN proveedor ON proveedor.PRO_RUT_PROVEEDOR=factura.proveedor_PRO_RUT_PROVEEDOR";

     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("FAC_ID_FACTURA");
             registros[1]=rs.getString("proveedor_PRO_RUT_PROVEEDOR"); 
             registros[2]=rs.getString("PRO_RAZON");
             registros[3]=rs.getString("FAC_NUMERO");
             registros[4]=rs.getString("FAC_FECHA_FACTURA");
             registros[5]=rs.getString("FAC_SUBTOTAL");
             registros[6]=rs.getString("FAC_IVA");
             registros[7]=rs.getString("FAC_TOTAL");
             modelo.addRow(registros);
             
         }
         
          facturas_compras_inventariadas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }
       
 }
 
//INFORMES DEVSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 public void filtarDatosPorRutDevolucion(String valor){
     String[] titulos={"numero pedido","pack","destinatario","fecha de entrega","comuna","hora de entrega","estado","rut cliente"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                "FROM venta \n" +
                "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                "JOIN comunas  ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                "WHERE cliente_CLI_RUT_CLIENTE like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("PCK_ID_PACK"); 
             registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             registros[4]=rs.getString("Comunas_COM_ID_COMUNA");
             registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             registros[6]=rs.getString("ESTADOS_VENTA_EST_ID_ESTADO");
             registros[7]=rs.getString("cliente_CLI_RUT_CLIENTE");
             modelo.addRow(registros);
             
         }
         
         devoluciones_y_cambios.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
 }
 public void mostrarDatosPorRutDevolucion(){
     String[] titulos={"numero pedido","pack","destinatario","fecha de entrega","comuna","hora de entrega","estado","rut cliente"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                "FROM venta\n" +
                "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                "JOIN comunas ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("PCK_ID_PACK"); 
             registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             registros[4]=rs.getString("COM_DESCRIPCION");
             registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             registros[6]=rs.getString("EST_DESCRIPCION");
             registros[7]=rs.getString("cliente_CLI_RUT_CLIENTE");
             modelo.addRow(registros);
             
         }
         
         devoluciones_y_cambios.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
 }
 public void filtrarDatosPorFechaDevolucion(String fecha1, String fecha2){
     
      String[] titulos={"numero pedido","pack","destinatario","fecha de entrega","comuna","hora de entrega","estado","rut cliente"};
      String[] registros =new String[8];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,venta.ESTADOS_VENTA_EST_ID_ESTADO,venta.Comunas_COM_ID_COMUNA\n" +
                "FROM venta \n" +
                "JOIN estados_venta ON estados_venta.EST_ID_ESTADO=venta.ESTADOS_VENTA_EST_ID_ESTADO\n" +
                "JOIN comunas  ON comunas.COM_ID_COMUNA=venta.Comunas_COM_ID_COMUNA\n"+
                "where VTA_FECHA_ENTREGA BETWEEN '"+fecha1+"' AND '"+fecha2+"' ";
     
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("PCK_ID_PACK"); 
             registros[2]=rs.getString("VTA_NOMBRE_DESTINATARIO");
             registros[3]=rs.getString("VTA_FECHA_ENTREGA");
             registros[4]=rs.getString("Comunas_COM_ID_COMUNA");
              registros[5]=rs.getString("VTA_HORA_ENTREGA_INICIAL");
             registros[6]=rs.getString("ESTADOS_VENTA_EST_ID_ESTADO");
             registros[7]=rs.getString("cliente_CLI_RUT_CLIENTE");
             modelo.addRow(registros);
             
         }
         
         devoluciones_y_cambios.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }  
     
   
 }
 public void limpiarCajasDevolucion(){
     desde_dev_y_cambios.setCalendar(null);
     hasta_dev_y_cambios.setCalendar(null);
     txtrut_informes_infodevycambios.setText("");
     
 }
 public void PDFimformesDevolucion(){
 try {
         FileOutputStream archivo;
         File file= new File("src/pdf/Informe Devoluciones.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Informe Devolucion y Cambios"+"\n\n");
         doc.add(cli);
 
         PdfPTable tablapro = new PdfPTable(8);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f,10f,10f,10f,10f};
         tablapro .setWidths(Columnapro);
         tablapro .setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("Registro Ventas"));
         PdfPCell pro2 = new PdfPCell(new Phrase("Pack"));
         PdfPCell pro3 = new PdfPCell(new Phrase("Destinatario"));
         PdfPCell pro4 = new PdfPCell(new Phrase("Fecha Entrega"));
         PdfPCell pro5 = new PdfPCell(new Phrase("Comuna"));
         PdfPCell pro6 = new PdfPCell(new Phrase("Hora de Entrega"));
         PdfPCell pro7 = new PdfPCell(new Phrase("Estado"));
         PdfPCell pro8 = new PdfPCell(new Phrase("Rut Cliente"));

         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro4);
         tablapro.addCell(pro5);
         tablapro.addCell(pro6);
         tablapro.addCell(pro7);
         tablapro.addCell(pro8);
         
         for (int i = 0; i < devoluciones_y_cambios.getRowCount(); i++) {
        String IdVenta =devoluciones_y_cambios.getValueAt(i,0).toString();
        String Pack =devoluciones_y_cambios.getValueAt(i,1).toString();
        String Destinatario =devoluciones_y_cambios.getValueAt(i,2).toString();
        String FechaEntrega =devoluciones_y_cambios.getValueAt(i,3).toString();
        String Comuna =devoluciones_y_cambios.getValueAt(i,4).toString();
        String hora  =devoluciones_y_cambios.getValueAt(i,5).toString();
        String Estado =devoluciones_y_cambios.getValueAt(i,6).toString();
        String Rut =devoluciones_y_cambios.getValueAt(i,7).toString();
        
        tablapro.addCell(IdVenta);
        tablapro.addCell(Pack);
        tablapro.addCell(Destinatario);
        tablapro.addCell(FechaEntrega);
        tablapro.addCell(Comuna);
        tablapro.addCell(hora);
        tablapro.addCell(Estado);
        tablapro.addCell(Rut);

         }
         doc.add(tablapro);
         
         
         
         
         
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     
     }      
 }
 
//INFORME VENTASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 
 public void filtrarDatosPorRutVentas(String valor){
     String[] titulos={"numero de pedido","rut cliente","nombre cliente","fecha de compra","fecha de entrega","pack comprado","monto pagado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *FROM venta WHERE cliente_CLI_RUT_CLIENTE like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("cliente_CLI_RUT_CLIENTE"); 
             registros[2]=rs.getString("VTA_NOMBRE_CLIENTE");
             registros[3]=rs.getString("VTA_FECHA_VENTA");
             registros[4]=rs.getString("VTA_FECHA_ENTREGA");
             registros[5]=rs.getString("PCK_ID_PACK");
             registros[6]=rs.getString("VTA_TOTAL");
             
             modelo.addRow(registros);
             
         }
         
         detalleventarealizada_infoventas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
 }
 public void mostrarDatosPorRutVentas(){
     String[] titulos={"numero de pedido","rut cliente","nombre cliente","fecha de compra","fecha de entrega","pack comprado","monto pagado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *FROM venta";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("cliente_CLI_RUT_CLIENTE"); 
             registros[2]=rs.getString("VTA_NOMBRE_CLIENTE");
             registros[3]=rs.getString("VTA_FECHA_VENTA");
             registros[4]=rs.getString("VTA_FECHA_ENTREGA");
             registros[5]=rs.getString("PCK_ID_PACK");
             registros[6]=rs.getString("VTA_TOTAL");
             
             modelo.addRow(registros);
             
         }
         
         detalleventarealizada_infoventas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
 }
 public void FiltrarPorFechaVentas(String fecha1, String fecha2){
       String[] titulos={"numero de pedido","rut cliente","nombre cliente","fecha de compra","fecha de entrega","pack comprado","monto pagado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT * \n" +
                 "FROM `venta`\n" +
                 "where VTA_FECHA_VENTA BETWEEN '"+fecha1+"' AND '"+fecha2+"' ";
     
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("cliente_CLI_RUT_CLIENTE"); 
             registros[2]=rs.getString("VTA_NOMBRE_CLIENTE");
             registros[3]=rs.getString("VTA_FECHA_VENTA");
             registros[4]=rs.getString("VTA_FECHA_ENTREGA");
             registros[5]=rs.getString("PCK_ID_PACK");
             registros[6]=rs.getString("VTA_TOTAL");
             
             modelo.addRow(registros);
             
         }
         
         detalleventarealizada_infoventas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }  
 }
 public void mostrarPorFechaVentas(){
       String[] titulos={"numero de pedido","rut cliente","nombre cliente","fecha de compra","fecha de entrega","pack comprado","monto pagado"};
      String[] registros =new String[7];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT * \n" +
                 "FROM `venta`";
     
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("VTA_ID_VENTA");
             registros[1]=rs.getString("cliente_CLI_RUT_CLIENTE"); 
             registros[2]=rs.getString("VTA_NOMBRE_CLIENTE");
             registros[3]=rs.getString("VTA_FECHA_VENTA");
             registros[4]=rs.getString("VTA_FECHA_ENTREGA");
             registros[5]=rs.getString("PCK_ID_PACK");
             registros[6]=rs.getString("VTA_TOTAL");
             
             modelo.addRow(registros);
             
         }
         
         detalleventarealizada_infoventas.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }  
 }
 public void limpiarCajasInformaVentas(){
     jDateChooser2.setCalendar(null);
     jDateChooser1.setCalendar(null);
     txtbuscarut_informes_infoventas.setText("");
      
 }
 public void PdfinformesVentas(){
    try {
         FileOutputStream archivo;
         File file= new File("src/pdf/Informe de venta.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Informe De Ventas"+"\n\n");
         doc.add(cli);
 
         PdfPTable tablapro = new PdfPTable(7);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f,10f,10f,10f};
         tablapro .setWidths(Columnapro);
         tablapro .setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("Numero de pedido"));
         PdfPCell pro2 = new PdfPCell(new Phrase("Rut cliente"));
         PdfPCell pro3 = new PdfPCell(new Phrase("Nombre cliente"));
         PdfPCell pro4 = new PdfPCell(new Phrase("fecha de compra"));
         PdfPCell pro5 = new PdfPCell(new Phrase("fecha de entrega"));
         PdfPCell pro6 = new PdfPCell(new Phrase("pack comprado"));
         PdfPCell pro7 = new PdfPCell(new Phrase("monto pagado"));
        
         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro4);
         tablapro.addCell(pro5);
         tablapro.addCell(pro6);
         tablapro.addCell(pro7);
         
         
         for (int i = 0; i < detalleventarealizada_infoventas.getRowCount(); i++) {
        String IdVenta =detalleventarealizada_infoventas.getValueAt(i,0).toString();
        String Rut =detalleventarealizada_infoventas.getValueAt(i,1).toString();
        String Nombre =detalleventarealizada_infoventas.getValueAt(i,2).toString();
        String fechaCompra =detalleventarealizada_infoventas.getValueAt(i,3).toString();
        String FechaEntrega =detalleventarealizada_infoventas.getValueAt(i,4).toString();
        String Pack  =detalleventarealizada_infoventas.getValueAt(i,5).toString();
        String Monto =detalleventarealizada_infoventas.getValueAt(i,6).toString();
      
        
        tablapro.addCell(IdVenta);
        tablapro.addCell(Rut);
        tablapro.addCell(Nombre);
        tablapro.addCell(fechaCompra);
        tablapro.addCell(FechaEntrega);
        tablapro.addCell(Pack);
        tablapro.addCell(Monto);
      

         }
         doc.add(tablapro);
         
         
         
         
         
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     
     }       
 }
 
//INFORME INVENTARIOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 
 public void filtrarDatosPorcategoria(String valor){
     
      String[] titulos={"codigo articulo","nombre articulo","stock","fecha de vencimiento","categoria","marca"};
      String[] registros =new String[6];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,articulo.ID_CATEGORIA\n" +
                 "FROM articulo\n" +
                 "JOIN categoria_articulo ON categoria_articulo.ID_Categoria=articulo.ID_CATEGORIA\n"+
                 " WHERE CAT_DESCRIPCION like '%"+valor+"%' ";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ART_ID_ARTICULO");
             registros[1]=rs.getString("ART_DESCRIPCION"); 
             registros[2]=rs.getString("ART_UNIDADES");
             registros[3]=rs.getString("ART_FECHA_VENCIMIENTO");
             registros[4]=rs.getString("CAT_DESCRIPCION");
             registros[5]=rs.getString("ART_MARCAS");
            
             modelo.addRow(registros);
             
         }
         
         detalle_inventario.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
     
     
 }
 public void mostrarDatosPorcategoria(){
     
      String[] titulos={"codigo articulo","nombre articulo","stock","fecha de vencimiento","categoria","marca"};
      String[] registros =new String[6];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,articulo.ID_CATEGORIA\n" +
                 "FROM articulo\n" +
                 "JOIN categoria_articulo ON categoria_articulo.ID_Categoria=articulo.ID_CATEGORIA";
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ART_ID_ARTICULO");
             registros[1]=rs.getString("ART_DESCRIPCION"); 
             registros[2]=rs.getString("ART_UNIDADES");
             registros[3]=rs.getString("ART_FECHA_VENCIMIENTO");
             registros[4]=rs.getString("CAT_DESCRIPCION");
             registros[5]=rs.getString("ART_MARCAS");
            
             modelo.addRow(registros);
             
         }
         
         detalle_inventario.setModel(modelo);
         
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     } 
     
     
 }
 public void filtrarPorFechaInventario(String fecha1, String fecha2){
   String[] titulos={"codigo articulo","nombre articulo","stock","fecha de vencimiento","categoria","marca"};
      String[] registros =new String[6];
      DefaultTableModel modelo=new DefaultTableModel(null,titulos);
      
     String SQL="SELECT *,articulo.ID_CATEGORIA\n" +
                 "FROM articulo\n" +
                 "JOIN categoria_articulo ON categoria_articulo.ID_Categoria=articulo.ID_CATEGORIA\n"+
                 " WHERE ART_FECHA_VENCIMIENTO BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
     
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ART_ID_ARTICULO");
             registros[1]=rs.getString("ART_DESCRIPCION"); 
             registros[2]=rs.getString("ART_UNIDADES");
             registros[3]=rs.getString("ART_FECHA_VENCIMIENTO");
             registros[4]=rs.getString("CAT_DESCRIPCION");
             registros[5]=rs.getString("ART_MARCAS");
            
             modelo.addRow(registros);
             
         }
         
         detalle_inventario.setModel(modelo);
     }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error al mostrar datos "+e.getMessage());
     }    
     
     
     
     
 }
 public void limpiarCajasInventario(){
   txtInformeDesde.setCalendar(null);
    txtInformeHasta.setCalendar(null);
     articulo_info_inventario.setText("");
     
 }
 public void pdfInformeInventario(){
   try {
         FileOutputStream archivo;
         File file= new File("src/pdf/inventario.pdf");
         archivo = new FileOutputStream(file);
         Document doc = new Document();
         PdfWriter.getInstance(doc,archivo);
         doc.open();
         
         Paragraph cli =new Paragraph();
         cli.add(Chunk.NEWLINE);
         cli.add("                                                  Informe Inventario"+"\n\n");
         doc.add(cli);
 
         PdfPTable tablapro = new PdfPTable(6);
         tablapro.setWidthPercentage(100);
         tablapro .getDefaultCell().setBorder(1);
         float[] Columnapro =new float[]{10f,10f,10f,10f,10f,10f};
         tablapro .setWidths(Columnapro);
         tablapro .setHorizontalAlignment(Element.ALIGN_LEFT);
         PdfPCell pro1 = new PdfPCell(new Phrase("codigo articulo"));
         PdfPCell pro2 = new PdfPCell(new Phrase("nombre articulo"));
         PdfPCell pro3 = new PdfPCell(new Phrase("stock"));
         PdfPCell pro4 = new PdfPCell(new Phrase("fecha de vencimiento"));
         PdfPCell pro5 = new PdfPCell(new Phrase("categoria"));
         PdfPCell pro6 = new PdfPCell(new Phrase("marca"));
       
         tablapro.addCell(pro1);
         tablapro.addCell(pro2);
         tablapro.addCell(pro3);
         tablapro.addCell(pro4);
         tablapro.addCell(pro5);
         tablapro.addCell(pro6);
         
         for (int i = 0; i < detalle_inventario.getRowCount(); i++) {
        String idArticulo =detalle_inventario.getValueAt(i,0).toString();
        String nombreArticulo =detalle_inventario.getValueAt(i,1).toString();
        String stock =detalle_inventario.getValueAt(i,2).toString();
        String FechaVencimiento =detalle_inventario.getValueAt(i,3).toString();
        String categoria =detalle_inventario.getValueAt(i,4).toString();
        String marca  =detalle_inventario.getValueAt(i,5).toString();
      
        
        tablapro.addCell(idArticulo);
        tablapro.addCell(nombreArticulo);
        tablapro.addCell(stock);
        tablapro.addCell(FechaVencimiento);
        tablapro.addCell(categoria);
        tablapro.addCell(marca);
        
         }
         doc.add(tablapro);
         
         
         
         
         
         
         doc.close();
         archivo.close();
         
         
         
     } catch (Exception e) {
     
     }   
     
     
     
     
     
 }
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        tablas = new javax.swing.JTabbedPane();
        ventas = new javax.swing.JPanel();
        panel_ventas = new javax.swing.JTabbedPane();
        venta = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        txtrut_ventas_venta = new javax.swing.JTextField();
        txtfono_ventas_venta = new javax.swing.JTextField();
        btncancelar_clte_ventas_venta = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txtemail_ventas_venta = new javax.swing.JTextField();
        txtnpedido_ventas_venta = new javax.swing.JTextField();
        txtncliente_ventas_venta = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jDfventa_ventas_venta = new com.toedter.calendar.JDateChooser();
        jLabel149 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        txtdireccion_ventas_venta = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        txtdestinatario_ventas_venta = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        txtsubtotal_ventas_venta = new javax.swing.JTextField();
        txtenvio_ventas_venta = new javax.swing.JTextField();
        txttotal_ventas_venta = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        txtsaludo_ventas_venta = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        txthorai_ventas_venta = new javax.swing.JTextField();
        txthoraf_ventas_venta = new javax.swing.JTextField();
        btncancelardestinatario_ventas_venta = new javax.swing.JButton();
        btnguardardestinatario_ventas_venta = new javax.swing.JButton();
        jLabel147 = new javax.swing.JLabel();
        cboxRRSS_ventas_venta = new javax.swing.JComboBox<>();
        cboxcomunas_ventas_venta = new javax.swing.JComboBox<>();
        cboxpacks_ventas_venta = new javax.swing.JComboBox<>();
        jDfentrega_ventas_venta = new com.toedter.calendar.JDateChooser();
        jLabel148 = new javax.swing.JLabel();
        cboxEstados_ventas_venta = new javax.swing.JComboBox<>();
        confirmacion = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ventas_pendientes_pago = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtnpedido_ventas_confirmacion = new javax.swing.JTextField();
        txtrut_ventas_confirmacion = new javax.swing.JTextField();
        txtnclte_ventas_confirmacion = new javax.swing.JTextField();
        txttransaccion_ventas_confirmacion = new javax.swing.JTextField();
        cboxbanco_ventas_confirmacion = new javax.swing.JComboBox<>();
        btncancelar_ventas_confirmacion = new javax.swing.JButton();
        btnconfirmar_ventas_confirmacion = new javax.swing.JButton();
        txtfpago_ventas_confirmacion = new com.toedter.calendar.JDateChooser();
        jLabel150 = new javax.swing.JLabel();
        cbonestados_ventas_confirmacion = new javax.swing.JComboBox<>();
        txtbanco_ventas_confirmacion = new javax.swing.JTextField();
        txtestados_ventas_confirmacion = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtbusqueda_ventas_confirmacion = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnbuscar_ventas_confirmacion = new javax.swing.JButton();
        lista_destino = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        listadestino_despacho = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        txtbusqueda_ventas_llistadestino = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        btndescarga_ventas_listadestino = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        confirmacion_despacho = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        cboxestado_ventas_adespacho = new javax.swing.JComboBox<>();
        btnguardar_ventas_adespacho = new javax.swing.JButton();
        txtestado_ventas_adespacho = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        detalle_dev_cambios = new javax.swing.JTable();
        jLabel152 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        compras = new javax.swing.JPanel();
        panel_compras = new javax.swing.JTabbedPane();
        solicitudes_pedido = new javax.swing.JPanel();
        solicitudes_pedido1 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtnpedido_compras_solicitudes = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txtunidades_compras_solicitudes = new javax.swing.JTextField();
        btncancelar_compras_solicitudes = new javax.swing.JButton();
        btnguardar_compras_solicitudes = new javax.swing.JButton();
        btnmas_compras_solicitudes = new javax.swing.JButton();
        btnmenos_compras_solicitudes = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        proveedores_compra = new javax.swing.JTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        listaCompras_solicitudes = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        listapedido_compras_solicitudes = new javax.swing.JTable();
        datepedido_compras_solicitudes = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio_compras_solicitudes = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        txtcantidad_compras_solicitudes = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        detalle_pedidos_realizados = new javax.swing.JTable();
        btneditar_compras_solicitudes = new javax.swing.JButton();
        btngeneraroc_compras_solicitudes = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        registro_compras = new javax.swing.JPanel();
        registro_compras1 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        txtnfactura_compras_registro = new javax.swing.JTextField();
        txtrut_compras_registro = new javax.swing.JTextField();
        txtfrecepcion_compras_registro = new javax.swing.JFormattedTextField();
        cboxrazonsocial_compras_registro = new javax.swing.JComboBox<>();
        btncancelarfac_compras_registro = new javax.swing.JButton();
        btnguardar_compras_registro = new javax.swing.JButton();
        jScrollPane27 = new javax.swing.JScrollPane();
        detalle_factura_compras_registro = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        txtcantidad_compras_registro = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        cboxarticulo_compras_registro = new javax.swing.JComboBox<>();
        jLabel108 = new javax.swing.JLabel();
        txtcodigo_compras_registro = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        txtvalor_compras_registro = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        txtvencimiento_compras_registro = new javax.swing.JFormattedTextField();
        jLabel111 = new javax.swing.JLabel();
        btncancelardetal_compras_registro = new javax.swing.JButton();
        btnagregar_compras_registro = new javax.swing.JButton();
        btneditar_compras_registro = new javax.swing.JButton();
        btneliminar_compras_registro = new javax.swing.JButton();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        revision_facturas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detalle_factura_compras_revision = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        facturas_compras_inventariadas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtnfactura_compras_revision = new javax.swing.JTextField();
        txtrut_compras_revision = new javax.swing.JTextField();
        txtfrecepcion_compras_revision = new javax.swing.JTextField();
        btncancelar_compras_revision = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnver_compras_revision = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        informes = new javax.swing.JPanel();
        panel_informes = new javax.swing.JTabbedPane();
        informe_ventas = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        detalleventarealizada_infoventas = new javax.swing.JTable();
        jLabel93 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnbuscarrango_informes_infoventas = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        txtbuscarut_informes_infoventas = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        btndescargar_informes_infoventas = new javax.swing.JButton();
        jLabel134 = new javax.swing.JLabel();
        informe_inventarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalle_inventario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnbuscar_informes_infoinventarios = new javax.swing.JButton();
        txtInformeDesde = new com.toedter.calendar.JDateChooser();
        txtInformeHasta = new com.toedter.calendar.JDateChooser();
        articulo_info_inventario = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btndescargar_informes_infoinventarios = new javax.swing.JButton();
        informe_clientes = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        btndescargas_informes_infoclientes = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        ventas_realizada_infoclientes = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtrut_informes_infoclientes = new javax.swing.JTextField();
        btnbuscarinfo_informes_infoclientes = new javax.swing.JButton();
        hasta_informe_cliente = new com.toedter.calendar.JDateChooser();
        desde_informe_cliente = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        informe_dev_y_cambios = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        devoluciones_y_cambios = new javax.swing.JTable();
        btndescargar_informes_infodevycambios = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtrut_informes_infodevycambios = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        btnbuscarinfo_informes_infodevycambios = new javax.swing.JButton();
        hasta_dev_y_cambios = new com.toedter.calendar.JDateChooser();
        desde_dev_y_cambios = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        maestros = new javax.swing.JPanel();
        panel_maestros = new javax.swing.JTabbedPane();
        clientes = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        listadeclientes_maestros = new javax.swing.JTable();
        btnventas_maestros_clientes = new javax.swing.JButton();
        btneditar_maestros_clientes = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txt_ncliente_maestros_clientes = new javax.swing.JTextField();
        txtfono_maestros_clientes = new javax.swing.JTextField();
        txtrut_maestros_clientes = new javax.swing.JTextField();
        txtemaill_maestros_clientes = new javax.swing.JTextField();
        cboxatcdes_maestros_clientes = new javax.swing.JComboBox<>();
        txtcel_maestros_clientes = new javax.swing.JTextField();
        btncancelar_maestros_clientes = new javax.swing.JButton();
        btnguardar_maestros_clientes = new javax.swing.JButton();
        txtfnac_maestros_clientes = new com.toedter.calendar.JDateChooser();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtBuscarClientes = new javax.swing.JTextField();
        proveedores = new javax.swing.JPanel();
        proveedores1 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtrut_maestros_proveedores = new javax.swing.JTextField();
        txtncontacto_maestros_proveedores = new javax.swing.JTextField();
        txtdireccion_maestros_proveedores = new javax.swing.JTextField();
        txtrazon_maestros_proveedores = new javax.swing.JTextField();
        txtfono_maestros_proveedores = new javax.swing.JTextField();
        txtemail_maestros_proveedores = new javax.swing.JTextField();
        btncancelar_maestros_proveedores = new javax.swing.JButton();
        btnguardar_maestros_proveedores = new javax.swing.JButton();
        cbactdes_maestros_proveedores = new javax.swing.JComboBox<>();
        jLabel145 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        proveedores_maestros = new javax.swing.JTable();
        btncompra_maestros_proveedores = new javax.swing.JButton();
        btneditar_maestros_proveedores = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        txtbusqueda_maestros_proveedores = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        articulos = new javax.swing.JPanel();
        articulos1 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txtnarticulo_maestros_articulo = new javax.swing.JTextField();
        txtunidades_maestros_articulos = new javax.swing.JTextField();
        txtmarcas_maestros_articulos = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        cboxproveedor_maestros_articulos = new javax.swing.JComboBox<>();
        btncancelar_maestros_articulos = new javax.swing.JButton();
        btnguardar_maestros_articulos = new javax.swing.JButton();
        jDvencimiento_maestros_articulos = new com.toedter.calendar.JDateChooser();
        cboxcategoria_maestros_articulos1 = new javax.swing.JComboBox<>();
        txtcategoria_maestros_articulos1 = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        listaarticulos = new javax.swing.JTable();
        btneditar_maestros_articulos = new javax.swing.JButton();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        txtbusqueda_maestros_articulos = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        packs = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_packs = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        articuloselegidos_maestros_packs = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtnombre_maestros_packs = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtprecio_maestros_packs = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtunidades_maestros_packs = new javax.swing.JTextField();
        btnmas_maestros_packs = new javax.swing.JButton();
        btnmenos_maestros_packs = new javax.swing.JButton();
        btncancelar_maestros_packs = new javax.swing.JButton();
        btncrearpack_maestros_packs = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        listaarticulos_maestros_packs = new javax.swing.JTable();
        btningresararticulo_maestros_packs = new javax.swing.JButton();
        jLabel155 = new javax.swing.JLabel();
        cboxEstados_maestros_packs = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtbusqueda_maestros_packs = new javax.swing.JTextField();
        btneditar_maestros_packs = new javax.swing.JButton();
        btndesactivar_maestros_packs = new javax.swing.JButton();
        btnbuscar_maestros_packs = new javax.swing.JButton();
        RRSS = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtnrrss_maestros_rrss = new javax.swing.JTextField();
        btncancelar_maestros_rrss = new javax.swing.JButton();
        btnguardar_maestros_rrss = new javax.swing.JButton();
        cbrrss_maestros_rrss = new javax.swing.JComboBox<>();
        jLabel142 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        redessociales = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtbusqueda_maestros_rrss = new javax.swing.JTextField();
        btneditar_maestros_rrss = new javax.swing.JButton();
        jLabel141 = new javax.swing.JLabel();
        categoria_articulos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtcategoria_maestros_catarticulos = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        btncancelar_maestros_catarticulos = new javax.swing.JButton();
        btnguardar_maestros_catarticulos = new javax.swing.JButton();
        cbcategoria_maestros_catarticulos = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btneditar_maestros_catarticulos = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        categoriaarticulos = new javax.swing.JTable();
        txtbusqueda_maestros_catarticulos = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        comunas = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtncomuna_maestros_comunas = new javax.swing.JTextField();
        btnguardar_maestros_comunas = new javax.swing.JButton();
        btncancelar_maestros_comunas = new javax.swing.JButton();
        cbcomuna_maestros_comunas = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtbusqueda_maestros_comunas = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        btneditar_maestros_comunas = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        comunasregistradas = new javax.swing.JTable();
        jLabel52 = new javax.swing.JLabel();
        bancos = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtnbanco_maestros_bancos = new javax.swing.JTextField();
        btnguardar_maestros_bancos = new javax.swing.JButton();
        btncancelar_maestros_bancos = new javax.swing.JButton();
        cbbanco_maestros_bancos = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtbusqueda_maestros_bancos = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        bancosregistrados = new javax.swing.JTable();
        btneditar_maestros_bancos = new javax.swing.JButton();
        jLabel140 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        categoria_ventas = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtcategoria_maestros_catventas = new javax.swing.JTextField();
        btnguardar_maestros_catventas = new javax.swing.JButton();
        btncancelar_maestros_catventas = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        cbcategoria_maestros_catventas = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        categorias_ventas_registradas = new javax.swing.JTable();
        btnbuscar_maestros_catventas = new javax.swing.JButton();
        btneditar_maestros_catventas = new javax.swing.JButton();
        txtbusqueda_maestros_catventas = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        usuarios = new javax.swing.JPanel();
        txtbusqueda_maestros_usuarios = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btneliminar_maestros_usuarios = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        txtusuario_maestros_usuarios = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        btnGuardar_maestros_usuarios = new javax.swing.JButton();
        btnCancelar_maestros_usuarios = new javax.swing.JButton();
        txtcontraseña_maestros_usuarios = new javax.swing.JTextField();
        btnactualizar_maestros_usuarios = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tablas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 8, true));

        ventas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 204), 5, true));

        panel_ventas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 3, true));

        venta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btncancelar_clte_ventas_venta.setText("Cancelar");
        btncancelar_clte_ventas_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_clte_ventas_ventaActionPerformed(evt);
            }
        });

        jLabel116.setText("Celular");

        jLabel115.setText("Rut");

        txtnpedido_ventas_venta.setFocusable(false);

        jLabel112.setText("Numero Pedido");

        jLabel113.setText("Nombre Cliente");

        jLabel114.setText("Email");

        jDfventa_ventas_venta.setDateFormatString("yyyy-MM-dd");

        jLabel149.setText("Fecha venta:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel113)
                        .addComponent(jLabel112))
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnpedido_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtncliente_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(198, 198, 198)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel115)
                    .addComponent(jLabel116)
                    .addComponent(jLabel149))
                .addGap(67, 67, 67)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDfventa_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfono_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(txtrut_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncancelar_clte_ventas_venta)
                .addGap(160, 160, 160))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel115)
                            .addComponent(txtrut_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfono_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDfventa_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel149))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel112)
                            .addComponent(txtnpedido_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel113)
                            .addComponent(txtncliente_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(txtemail_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar_clte_ventas_venta))
                        .addGap(42, 42, 42))))
        );

        jLabel137.setText("Datos Cliente Solicitante");

        jLabel117.setText("Datos Destinatario");

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel123.setText("Packs");

        jLabel118.setText("Nombre Destinatario");

        jLabel119.setText("Fecha Entrega");

        jLabel120.setText("Direccion");

        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel127.setText("Subtotal");
        jPanel22.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel128.setText("Envio");
        jPanel22.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel129.setText("Total");
        jPanel22.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 30, -1));
        jPanel22.add(txtsubtotal_ventas_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 60, -1));

        txtenvio_ventas_venta.setText("0");
        jPanel22.add(txtenvio_ventas_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 70, -1));
        jPanel22.add(txttotal_ventas_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 70, -1));

        jLabel121.setText("Comuna");

        jLabel122.setText("Saludo");

        jLabel124.setText("Hora Inicio");

        jLabel125.setText("Hora Fin");

        btncancelardestinatario_ventas_venta.setText("Cancelar");
        btncancelardestinatario_ventas_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelardestinatario_ventas_ventaActionPerformed(evt);
            }
        });

        btnguardardestinatario_ventas_venta.setText("Guardar");
        btnguardardestinatario_ventas_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardardestinatario_ventas_ventaActionPerformed(evt);
            }
        });

        jLabel147.setText("RRSS:");

        cboxpacks_ventas_venta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxpacks_ventas_ventaItemStateChanged(evt);
            }
        });
        cboxpacks_ventas_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboxpacks_ventas_ventaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboxpacks_ventas_ventaMousePressed(evt);
            }
        });
        cboxpacks_ventas_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxpacks_ventas_ventaActionPerformed(evt);
            }
        });
        cboxpacks_ventas_venta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboxpacks_ventas_ventaPropertyChange(evt);
            }
        });
        cboxpacks_ventas_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboxpacks_ventas_ventaKeyPressed(evt);
            }
        });

        jDfentrega_ventas_venta.setDateFormatString("yyyy-MM-dd");

        jLabel148.setText("Estado:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel118)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(txtdestinatario_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtsaludo_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel120)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel122)
                                .addComponent(jLabel121)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdireccion_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(cboxcomunas_ventas_venta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDfentrega_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel123)
                            .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125)
                            .addComponent(jLabel147))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txthorai_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                        .addComponent(txthoraf_ventas_venta))
                                    .addComponent(cboxpacks_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(cboxRRSS_ventas_venta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(138, 138, 138))))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btnguardardestinatario_ventas_venta)
                        .addGap(92, 92, 92)
                        .addComponent(btncancelardestinatario_ventas_venta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel148)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxEstados_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdestinatario_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118)
                            .addComponent(jLabel123)
                            .addComponent(cboxpacks_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel119)
                                .addComponent(jLabel124)
                                .addComponent(txthorai_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDfentrega_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel125)
                                .addGap(17, 17, 17)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboxcomunas_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel121))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsaludo_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel122)))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txthoraf_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdireccion_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel120))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboxRRSS_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel147))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btncancelardestinatario_ventas_venta)
                                    .addComponent(btnguardardestinatario_ventas_venta)))))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxEstados_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel148))))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout ventaLayout = new javax.swing.GroupLayout(venta);
        venta.setLayout(ventaLayout);
        ventaLayout.setHorizontalGroup(
            ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117))
                .addContainerGap(1113, Short.MAX_VALUE))
        );
        ventaLayout.setVerticalGroup(
            ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel137)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_ventas.addTab("venta", venta);

        confirmacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        ventas_pendientes_pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ventas_pendientes_pago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ventas_pendientes_pagoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(ventas_pendientes_pago);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setText("Numero de Pedido");

        jLabel31.setText("Rut");

        jLabel32.setText("Banco Cliente");

        jLabel33.setText("Nombre Cliente");

        jLabel34.setText("Fecha de Pago");

        jLabel35.setText("Numero Transaccion");

        txtnpedido_ventas_confirmacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnpedido_ventas_confirmacionKeyReleased(evt);
            }
        });

        txtrut_ventas_confirmacion.setFocusable(false);

        txtnclte_ventas_confirmacion.setFocusable(false);

        txttransaccion_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttransaccion_ventas_confirmacionActionPerformed(evt);
            }
        });

        cboxbanco_ventas_confirmacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxbanco_ventas_confirmacionItemStateChanged(evt);
            }
        });

        btncancelar_ventas_confirmacion.setText("Cancelar");
        btncancelar_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_ventas_confirmacionActionPerformed(evt);
            }
        });

        btnconfirmar_ventas_confirmacion.setText("Confirmar");
        btnconfirmar_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmar_ventas_confirmacionActionPerformed(evt);
            }
        });

        txtfpago_ventas_confirmacion.setDateFormatString("yyyy-MM-dd");

        jLabel150.setText("Estado:");

        cbonestados_ventas_confirmacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbonestados_ventas_confirmacionItemStateChanged(evt);
            }
        });

        txtbanco_ventas_confirmacion.setFocusable(false);

        txtestados_ventas_confirmacion.setFocusable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnpedido_ventas_confirmacion)
                    .addComponent(txtrut_ventas_confirmacion)
                    .addComponent(cboxbanco_ventas_confirmacion, 0, 166, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbanco_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttransaccion_ventas_confirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtnclte_ventas_confirmacion)
                    .addComponent(txtfpago_ventas_confirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                        .addComponent(btncancelar_ventas_confirmacion)
                        .addGap(96, 96, 96)
                        .addComponent(btnconfirmar_ventas_confirmacion)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbonestados_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtestados_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtnpedido_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtrut_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(cboxbanco_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbanco_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(txtfpago_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel150))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(txtnclte_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbonestados_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtestados_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txttransaccion_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar_ventas_confirmacion)
                            .addComponent(btnconfirmar_ventas_confirmacion))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel27.setText("Ventas pendientes de pago");

        txtbusqueda_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_ventas_confirmacionActionPerformed(evt);
            }
        });

        jLabel29.setText("Confirma pago cliente");

        btnbuscar_ventas_confirmacion.setText("Buscar");
        btnbuscar_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_ventas_confirmacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmacionLayout = new javax.swing.GroupLayout(confirmacion);
        confirmacion.setLayout(confirmacionLayout);
        confirmacionLayout.setHorizontalGroup(
            confirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacionLayout.createSequentialGroup()
                .addGroup(confirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmacionLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel29))
                    .addGroup(confirmacionLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel27)
                        .addGap(30, 30, 30)
                        .addComponent(btnbuscar_ventas_confirmacion)
                        .addGap(18, 18, 18)
                        .addComponent(txtbusqueda_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(770, Short.MAX_VALUE))
            .addComponent(jScrollPane7)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmacionLayout.setVerticalGroup(
            confirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confirmacionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(confirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(btnbuscar_ventas_confirmacion)
                    .addComponent(txtbusqueda_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        panel_ventas.addTab("confirmacion", confirmacion);

        lista_destino.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listadestino_despacho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(listadestino_despacho);

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setText("Lista destino para despacho por día");

        txtbusqueda_ventas_llistadestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_ventas_llistadestinoActionPerformed(evt);
            }
        });
        txtbusqueda_ventas_llistadestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_ventas_llistadestinoKeyReleased(evt);
            }
        });

        jLabel151.setText("Buscar por Fecha");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel64)
                .addGap(110, 110, 110)
                .addComponent(jLabel151)
                .addGap(18, 18, 18)
                .addComponent(txtbusqueda_ventas_llistadestino, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtbusqueda_ventas_llistadestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel151))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btndescarga_ventas_listadestino.setText("Descargar PDF");
        btndescarga_ventas_listadestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndescarga_ventas_listadestinoActionPerformed(evt);
            }
        });

        jLabel63.setText("Despacho");

        javax.swing.GroupLayout lista_destinoLayout = new javax.swing.GroupLayout(lista_destino);
        lista_destino.setLayout(lista_destinoLayout);
        lista_destinoLayout.setHorizontalGroup(
            lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lista_destinoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel63)
                .addContainerGap(1208, Short.MAX_VALUE))
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lista_destinoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btndescarga_ventas_listadestino)
                .addGap(669, 669, 669))
        );
        lista_destinoLayout.setVerticalGroup(
            lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lista_destinoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndescarga_ventas_listadestino)
                .addContainerGap())
        );

        panel_ventas.addTab("lista destino", lista_destino);

        confirmacion_despacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jLabel59.setText("Actualización estado despacho");

        jLabel60.setText("Detalle de devoluciones y cambios");

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setText("Estado de entrega:");

        cboxestado_ventas_adespacho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxestado_ventas_adespachoItemStateChanged(evt);
            }
        });

        btnguardar_ventas_adespacho.setText("Guardar");
        btnguardar_ventas_adespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_ventas_adespachoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel58)
                .addGap(87, 87, 87)
                .addComponent(cboxestado_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtestado_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnguardar_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(cboxestado_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar_ventas_adespacho)
                    .addComponent(txtestado_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        detalle_dev_cambios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        detalle_dev_cambios.setMaximumSize(new java.awt.Dimension(200, 64));
        detalle_dev_cambios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detalle_dev_cambiosMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(detalle_dev_cambios);

        jLabel152.setText("Buscar por Código:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel153.setText("Buscar por Fecha:");

        javax.swing.GroupLayout confirmacion_despachoLayout = new javax.swing.GroupLayout(confirmacion_despacho);
        confirmacion_despacho.setLayout(confirmacion_despachoLayout);
        confirmacion_despachoLayout.setHorizontalGroup(
            confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane13)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        confirmacion_despachoLayout.setVerticalGroup(
            confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel152)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        panel_ventas.addTab("Actualización despacho", confirmacion_despacho);

        jLabel36.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel36.setText("Dream Gifts");

        javax.swing.GroupLayout ventasLayout = new javax.swing.GroupLayout(ventas);
        ventas.setLayout(ventasLayout);
        ventasLayout.setHorizontalGroup(
            ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_ventas)
            .addGroup(ventasLayout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ventasLayout.setVerticalGroup(
            ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventasLayout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(panel_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablas.addTab("Ventas", ventas);

        compras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 204), 5, true));

        panel_compras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 3, true));

        solicitudes_pedido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        solicitudes_pedido1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel100.setText("Numero Pedido");

        jLabel101.setText("Fecha Pedido");

        txtnpedido_compras_solicitudes.setFocusable(false);

        jLabel102.setText("Unidades");

        btncancelar_compras_solicitudes.setText("Cancelar");
        btncancelar_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_compras_solicitudesActionPerformed(evt);
            }
        });

        btnguardar_compras_solicitudes.setText("Guardar");
        btnguardar_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_compras_solicitudesActionPerformed(evt);
            }
        });

        btnmas_compras_solicitudes.setText(">");
        btnmas_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmas_compras_solicitudesActionPerformed(evt);
            }
        });

        btnmenos_compras_solicitudes.setText("<");
        btnmenos_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenos_compras_solicitudesActionPerformed(evt);
            }
        });

        proveedores_compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre", "Teléfono", "Correo", "Dirección", "Razon social"
            }
        ));
        jScrollPane28.setViewportView(proveedores_compra);

        listaCompras_solicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Articulo", "Articulo", "Cantidad", "Precio"
            }
        ));
        jScrollPane24.setViewportView(listaCompras_solicitudes);

        listapedido_compras_solicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane25.setViewportView(listapedido_compras_solicitudes);

        datepedido_compras_solicitudes.setDateFormatString("yyyy-MM-dd");

        jLabel5.setText("Precio");

        jLabel154.setText("Cantidad de articulos:");

        txtcantidad_compras_solicitudes.setFocusable(false);

        jButton2.setText("Agregar Articulos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout solicitudes_pedido1Layout = new javax.swing.GroupLayout(solicitudes_pedido1);
        solicitudes_pedido1.setLayout(solicitudes_pedido1Layout);
        solicitudes_pedido1Layout.setHorizontalGroup(
            solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnmenos_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                        .addComponent(jLabel102)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtunidades_compras_solicitudes))
                                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPrecio_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44)
                                .addComponent(btnmas_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel100)
                        .addGap(50, 50, 50)
                        .addComponent(txtnpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datepedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel154)
                        .addGap(18, 18, 18)
                        .addComponent(txtcantidad_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)
                        .addGap(29, 29, 29)
                        .addComponent(btnguardar_compras_solicitudes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_compras_solicitudes))
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        solicitudes_pedido1Layout.setVerticalGroup(
            solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtnpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel100)
                                .addComponent(jLabel101))
                            .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel154)
                                    .addComponent(txtcantidad_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(datepedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solicitudes_pedido1Layout.createSequentialGroup()
                                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPrecio_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel102)
                                    .addComponent(txtunidades_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                .addComponent(btnmas_compras_solicitudes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnmenos_compras_solicitudes))))
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar_compras_solicitudes)
                            .addComponent(btncancelar_compras_solicitudes)
                            .addComponent(jButton2))))
                .addContainerGap())
        );

        detalle_pedidos_realizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        detalle_pedidos_realizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detalle_pedidos_realizadosMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(detalle_pedidos_realizados);

        btneditar_compras_solicitudes.setText("Editar");
        btneditar_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_compras_solicitudesActionPerformed(evt);
            }
        });

        btngeneraroc_compras_solicitudes.setText("Generar O/C");
        btngeneraroc_compras_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneraroc_compras_solicitudesActionPerformed(evt);
            }
        });

        jLabel51.setText("Solicitudes de Pedido");

        jLabel130.setText("Detalle de Pedidos Ralizados");

        javax.swing.GroupLayout solicitudes_pedidoLayout = new javax.swing.GroupLayout(solicitudes_pedido);
        solicitudes_pedido.setLayout(solicitudes_pedidoLayout);
        solicitudes_pedidoLayout.setHorizontalGroup(
            solicitudes_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(solicitudes_pedido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane26)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solicitudes_pedidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneditar_compras_solicitudes)
                .addGap(89, 89, 89)
                .addComponent(btngeneraroc_compras_solicitudes)
                .addGap(84, 84, 84))
            .addGroup(solicitudes_pedidoLayout.createSequentialGroup()
                .addGroup(solicitudes_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitudes_pedidoLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(solicitudes_pedidoLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        solicitudes_pedidoLayout.setVerticalGroup(
            solicitudes_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solicitudes_pedidoLayout.createSequentialGroup()
                .addComponent(jLabel51)
                .addGap(8, 8, 8)
                .addComponent(solicitudes_pedido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel130)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(solicitudes_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_compras_solicitudes)
                    .addComponent(btngeneraroc_compras_solicitudes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_compras.addTab("solicitudes pedido", solicitudes_pedido);

        registro_compras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        registro_compras1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel103.setText("Numero Factura");

        jLabel104.setText("Rut");

        jLabel105.setText("Fecha Factura");

        jLabel106.setText("Proveedor Razon Social");

        cboxrazonsocial_compras_registro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btncancelarfac_compras_registro.setText("Cancelar");

        btnguardar_compras_registro.setText("Guardar");

        javax.swing.GroupLayout registro_compras1Layout = new javax.swing.GroupLayout(registro_compras1);
        registro_compras1.setLayout(registro_compras1Layout);
        registro_compras1Layout.setHorizontalGroup(
            registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registro_compras1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(122, 122, 122)
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnfactura_compras_registro, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(txtrut_compras_registro))
                .addGap(133, 133, 133)
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboxrazonsocial_compras_registro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfrecepcion_compras_registro, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardar_compras_registro)
                .addGap(54, 54, 54)
                .addComponent(btncancelarfac_compras_registro)
                .addGap(28, 28, 28))
        );
        registro_compras1Layout.setVerticalGroup(
            registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registro_compras1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel103)
                        .addComponent(txtnfactura_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfrecepcion_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registro_compras1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncancelarfac_compras_registro)
                            .addComponent(btnguardar_compras_registro)
                            .addComponent(jLabel106)
                            .addComponent(cboxrazonsocial_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(registro_compras1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(registro_compras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtrut_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        detalle_factura_compras_registro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane27.setViewportView(detalle_factura_compras_registro);

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel109.setText("Cantidad");

        cboxarticulo_compras_registro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel108.setText("Articulo");

        jLabel107.setText("Codigo");

        jLabel110.setText("Valor");

        jLabel111.setText("Vencimiento");

        btncancelardetal_compras_registro.setText("Cancelar");

        btnagregar_compras_registro.setText("Agregar Articulo");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel107)
                        .addGap(18, 18, 18)
                        .addComponent(txtcodigo_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(jLabel108)
                        .addGap(18, 18, 18)
                        .addComponent(cboxarticulo_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel109)
                        .addGap(18, 18, 18)
                        .addComponent(txtcantidad_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtvalor_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel111)
                        .addGap(18, 18, 18)
                        .addComponent(txtvencimiento_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnagregar_compras_registro)
                        .addGap(82, 82, 82)
                        .addComponent(btncancelardetal_compras_registro)))
                .addGap(59, 59, 59))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel109)
                        .addComponent(cboxarticulo_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel108)
                        .addComponent(txtcantidad_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcodigo_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvalor_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110)
                    .addComponent(jLabel111)
                    .addComponent(txtvencimiento_compras_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregar_compras_registro)
                    .addComponent(btncancelardetal_compras_registro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btneditar_compras_registro.setText("Editar");

        btneliminar_compras_registro.setText("Eliminar");

        jLabel131.setText("Registro Factura de Proveedores");

        jLabel132.setText("Registro del Detalle de Factura");

        jLabel133.setText("Detalle de la Factura");

        javax.swing.GroupLayout registro_comprasLayout = new javax.swing.GroupLayout(registro_compras);
        registro_compras.setLayout(registro_comprasLayout);
        registro_comprasLayout.setHorizontalGroup(
            registro_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane27)
            .addComponent(registro_compras1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(registro_comprasLayout.createSequentialGroup()
                .addGroup(registro_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registro_comprasLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_comprasLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel133))
                    .addGroup(registro_comprasLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registro_comprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneditar_compras_registro)
                .addGap(75, 75, 75)
                .addComponent(btneliminar_compras_registro)
                .addGap(192, 192, 192))
        );
        registro_comprasLayout.setVerticalGroup(
            registro_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registro_comprasLayout.createSequentialGroup()
                .addComponent(jLabel131)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registro_compras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel132)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel133)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registro_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_compras_registro)
                    .addComponent(btneliminar_compras_registro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_compras.addTab("registro compra", registro_compras);

        revision_facturas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        detalle_factura_compras_revision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Articulo", "Cantidad", "Valor", "Seleccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(detalle_factura_compras_revision);

        facturas_compras_inventariadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nuemero de Factura", "Fecha de Recepcion", "Razon Social", "Total", "Seleccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(facturas_compras_inventariadas);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Numero de Factura");

        jLabel13.setText("Rut");

        jLabel14.setText("Fecha de Factura");

        txtnfactura_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnfactura_compras_revisionActionPerformed(evt);
            }
        });
        txtnfactura_compras_revision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnfactura_compras_revisionKeyReleased(evt);
            }
        });

        txtrut_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrut_compras_revisionActionPerformed(evt);
            }
        });
        txtrut_compras_revision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrut_compras_revisionKeyReleased(evt);
            }
        });

        txtfrecepcion_compras_revision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfrecepcion_compras_revisionKeyReleased(evt);
            }
        });

        btncancelar_compras_revision.setText("Cancelar");
        btncancelar_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_compras_revisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12)
                        .addGap(41, 41, 41)
                        .addComponent(txtnfactura_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtrut_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(jLabel14)
                        .addGap(41, 41, 41)
                        .addComponent(txtfrecepcion_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(522, 522, 522)
                        .addComponent(btncancelar_compras_revision)))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnfactura_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtrut_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(txtfrecepcion_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btncancelar_compras_revision)
                .addGap(25, 25, 25))
        );

        jLabel9.setText("Detalle de Factura");

        btnver_compras_revision.setText("Ver");
        btnver_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnver_compras_revisionActionPerformed(evt);
            }
        });

        jLabel10.setText("Facturas de Compra Inventariadas");

        jLabel11.setText("Revision de Facturas Inventariadas");

        javax.swing.GroupLayout revision_facturasLayout = new javax.swing.GroupLayout(revision_facturas);
        revision_facturas.setLayout(revision_facturasLayout);
        revision_facturasLayout.setHorizontalGroup(
            revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(revision_facturasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revision_facturasLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnver_compras_revision)
                        .addGap(88, 88, 88))
                    .addGroup(revision_facturasLayout.createSequentialGroup()
                        .addGroup(revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        revision_facturasLayout.setVerticalGroup(
            revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revision_facturasLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnver_compras_revision)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        panel_compras.addTab("revision de facturas", revision_facturas);

        jLabel37.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel37.setText("Dream Gifts");

        javax.swing.GroupLayout comprasLayout = new javax.swing.GroupLayout(compras);
        compras.setLayout(comprasLayout);
        comprasLayout.setHorizontalGroup(
            comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_compras)
            .addGroup(comprasLayout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        comprasLayout.setVerticalGroup(
            comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comprasLayout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(panel_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablas.addTab("Compras", compras);

        informes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 204), 5, true));

        panel_informes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 3, true));

        informe_ventas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        detalleventarealizada_infoventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero Pedido", "Rut Cliente", "Nombre Cliente", "Fecha Compra", "Fecha Entregada", "Packs Comprado", "Monto Pagado"
            }
        ));
        jScrollPane19.setViewportView(detalleventarealizada_infoventas);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel93.setText("Detalle de Ventas Realizada");

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnbuscarrango_informes_infoventas.setText("Buscar");
        btnbuscarrango_informes_infoventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarrango_informes_infoventasActionPerformed(evt);
            }
        });

        jLabel91.setText("Busqueda por Rut");

        txtbuscarut_informes_infoventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarut_informes_infoventasKeyReleased(evt);
            }
        });

        jLabel89.setText("Desde");

        jLabel90.setText("Hasta");

        jLabel88.setText("Busqueda Rango Fecha Ventas");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel89)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtbuscarut_informes_infoventas, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(184, 316, Short.MAX_VALUE)
                        .addComponent(jLabel90)
                        .addGap(44, 44, 44)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btnbuscarrango_informes_infoventas)
                        .addGap(220, 220, 220))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jButton4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel88)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel89)
                                .addComponent(jLabel90))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(txtbuscarut_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btnbuscarrango_informes_infoventas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addContainerGap())))
        );

        btndescargar_informes_infoventas.setText("Descargar");
        btndescargar_informes_infoventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndescargar_informes_infoventasActionPerformed(evt);
            }
        });

        jLabel134.setText("Busqueda de Ventas");

        javax.swing.GroupLayout informe_ventasLayout = new javax.swing.GroupLayout(informe_ventas);
        informe_ventas.setLayout(informe_ventasLayout);
        informe_ventasLayout.setHorizontalGroup(
            informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(informe_ventasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel134)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane19)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_ventasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_ventasLayout.createSequentialGroup()
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(636, 636, 636))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_ventasLayout.createSequentialGroup()
                        .addComponent(btndescargar_informes_infoventas)
                        .addGap(122, 122, 122))))
        );
        informe_ventasLayout.setVerticalGroup(
            informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel134)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel93)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndescargar_informes_infoventas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_informes.addTab("informe ventas", informe_ventas);

        informe_inventarios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        detalle_inventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detalle_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "codigo articulo", "nombre articulo", "stock", "fecha de vencimiento", "valor_producto", "categoria", "rut_proveedor"
            }
        ));
        jScrollPane1.setViewportView(detalle_inventario);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Busqueda rango fecha venta ");

        jLabel2.setText("Desde");

        jLabel3.setText("Hasta");

        jLabel4.setText("Categoria articulo");

        btnbuscar_informes_infoinventarios.setText("Buscar");
        btnbuscar_informes_infoinventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_informes_infoinventariosActionPerformed(evt);
            }
        });

        txtInformeDesde.setDateFormatString("yyyy-MM-dd");

        txtInformeHasta.setDateFormatString("yyyy-MM-dd");

        articulo_info_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                articulo_info_inventarioKeyReleased(evt);
            }
        });

        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(64, 64, 64)
                        .addComponent(txtInformeDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(articulo_info_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txtInformeHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnbuscar_informes_infoinventarios)
                .addGap(531, 531, 531))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtInformeHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInformeDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addComponent(btnbuscar_informes_infoinventarios))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(articulo_info_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel7.setText("Detalle de Inventario");

        jLabel8.setText("Informe Inventario");

        btndescargar_informes_infoinventarios.setText("Descargar");
        btndescargar_informes_infoinventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndescargar_informes_infoinventariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout informe_inventariosLayout = new javax.swing.GroupLayout(informe_inventarios);
        informe_inventarios.setLayout(informe_inventariosLayout);
        informe_inventariosLayout.setHorizontalGroup(
            informe_inventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, Short.MAX_VALUE)
            .addGroup(informe_inventariosLayout.createSequentialGroup()
                .addGroup(informe_inventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informe_inventariosLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(informe_inventariosLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_inventariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btndescargar_informes_infoinventarios)
                .addGap(26, 26, 26))
        );
        informe_inventariosLayout.setVerticalGroup(
            informe_inventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_inventariosLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndescargar_informes_infoinventarios)
                .addGap(53, 53, 53))
        );

        panel_informes.addTab("informe inventarios", informe_inventarios);

        informe_clientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel99.setText("Detalle Ventas Realizadas");

        btndescargas_informes_infoclientes.setText("Descargar");
        btndescargas_informes_infoclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndescargas_informes_infoclientesActionPerformed(evt);
            }
        });

        ventas_realizada_infoclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane21.setViewportView(ventas_realizada_infoclientes);

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel94.setText("Busqueda Fecha Rango Ventas");

        jLabel95.setText("Desde");

        jLabel96.setText("Hasta");

        jLabel97.setText("Busqueda por Rut ");

        txtrut_informes_infoclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrut_informes_infoclientesKeyReleased(evt);
            }
        });

        btnbuscarinfo_informes_infoclientes.setText("Buscar");
        btnbuscarinfo_informes_infoclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarinfo_informes_infoclientesActionPerformed(evt);
            }
        });

        hasta_informe_cliente.setDateFormatString("yyyy-MM-dd");

        desde_informe_cliente.setDateFormatString("yyyy-MM-dd");

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel94)
                .addContainerGap(781, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtrut_informes_infoclientes, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(desde_informe_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel96)
                        .addGap(35, 35, 35)
                        .addComponent(hasta_informe_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(btnbuscarinfo_informes_infoclientes))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton5)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnbuscarinfo_informes_infoclientes)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel96))
                            .addComponent(hasta_informe_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desde_informe_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(txtrut_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(14, 14, 14))
        );

        jLabel92.setText("Busqueda deVentas");

        javax.swing.GroupLayout informe_clientesLayout = new javax.swing.GroupLayout(informe_clientes);
        informe_clientes.setLayout(informe_clientesLayout);
        informe_clientesLayout.setHorizontalGroup(
            informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informe_clientesLayout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 720, Short.MAX_VALUE))
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(informe_clientesLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane21)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_clientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btndescargas_informes_infoclientes)
                .addGap(174, 174, 174))
        );
        informe_clientesLayout.setVerticalGroup(
            informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informe_clientesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndescargas_informes_infoclientes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_informes.addTab("informe clientes", informe_clientes);

        informe_dev_y_cambios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        devoluciones_y_cambios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Registro venta", "Pack", "Destinatario", "Fecha entrega", "Comuna", "Hora entrega", "Devolución ", "Ver"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        devoluciones_y_cambios.setMaximumSize(new java.awt.Dimension(200, 64));
        jScrollPane12.setViewportView(devoluciones_y_cambios);

        btndescargar_informes_infodevycambios.setText("Descargar");
        btndescargar_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndescargar_informes_infodevycambiosActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("Detalle de devoluciones y cambios");

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setText("Desde:");

        jLabel54.setText("Hasta:");

        jLabel55.setText("Busqueda por rango de fecha:");

        txtrut_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrut_informes_infodevycambiosActionPerformed(evt);
            }
        });
        txtrut_informes_infodevycambios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrut_informes_infodevycambiosKeyReleased(evt);
            }
        });

        jLabel56.setText("Busqueda por rut:");

        btnbuscarinfo_informes_infodevycambios.setText("Buscar");
        btnbuscarinfo_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarinfo_informes_infodevycambiosActionPerformed(evt);
            }
        });

        hasta_dev_y_cambios.setDateFormatString("yyyy-MM-dd");

        desde_dev_y_cambios.setDateFormatString("yyyy-MM-dd");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel53))
                .addGap(58, 58, 58)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtrut_informes_infodevycambios, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(desde_dev_y_cambios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(hasta_dev_y_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnbuscarinfo_informes_infodevycambios)
                        .addGap(195, 195, 195))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(jLabel55)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel55)
                .addGap(1, 1, 1)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel53)
                                .addComponent(jLabel54)
                                .addComponent(btnbuscarinfo_informes_infodevycambios))
                            .addComponent(desde_dev_y_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(txtrut_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(hasta_dev_y_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel98.setText("Busqueda de Ventas");

        javax.swing.GroupLayout informe_dev_y_cambiosLayout = new javax.swing.GroupLayout(informe_dev_y_cambios);
        informe_dev_y_cambios.setLayout(informe_dev_y_cambiosLayout);
        informe_dev_y_cambiosLayout.setHorizontalGroup(
            informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane12)
            .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                .addGroup(informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel98))
                    .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235)
                        .addComponent(jLabel136)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel135)))
                .addContainerGap(352, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informe_dev_y_cambiosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btndescargar_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        informe_dev_y_cambiosLayout.setVerticalGroup(
            informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98)
                .addGroup(informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel57))
                    .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel135)
                            .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndescargar_informes_infodevycambios)
                .addContainerGap())
        );

        panel_informes.addTab("informe dev. y cambios", informe_dev_y_cambios);

        jLabel39.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel39.setText("Dream Gifts");

        javax.swing.GroupLayout informesLayout = new javax.swing.GroupLayout(informes);
        informes.setLayout(informesLayout);
        informesLayout.setHorizontalGroup(
            informesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_informes, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(informesLayout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(jLabel39)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        informesLayout.setVerticalGroup(
            informesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panel_informes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablas.addTab("Informes", informes);

        maestros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 204), 5, true));

        panel_maestros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 3, true));

        clientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        listadeclientes_maestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        listadeclientes_maestros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listadeclientes_maestrosMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(listadeclientes_maestros);

        btnventas_maestros_clientes.setText("Ventas");
        btnventas_maestros_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventas_maestros_clientesActionPerformed(evt);
            }
        });

        btneditar_maestros_clientes.setText("Editar");
        btneditar_maestros_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_clientesActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setText("Nombre Cliente");

        jLabel69.setText("Rut");

        jLabel71.setText("Celular");

        jLabel66.setText("Telefono");

        jLabel67.setText(" E-mail");

        jLabel68.setText("Estado:");

        jLabel70.setText("F.Nacimiento");

        cboxatcdes_maestros_clientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        btncancelar_maestros_clientes.setText("Cancelar");
        btncancelar_maestros_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_clientesActionPerformed(evt);
            }
        });

        btnguardar_maestros_clientes.setText("Guardar");
        btnguardar_maestros_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_clientesActionPerformed(evt);
            }
        });

        txtfnac_maestros_clientes.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboxatcdes_maestros_clientes, 0, 155, Short.MAX_VALUE)
                                    .addComponent(txtemaill_maestros_clientes, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtfono_maestros_clientes, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(142, 142, 142)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(txt_ncliente_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtrut_maestros_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(txtcel_maestros_clientes)
                            .addComponent(txtfnac_maestros_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(478, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_maestros_clientes)
                        .addGap(50, 50, 50)
                        .addComponent(btnguardar_maestros_clientes)
                        .addGap(92, 92, 92))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtrut_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ncliente_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(txtfono_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemaill_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxatcdes_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68)
                            .addComponent(btncancelar_maestros_clientes)
                            .addComponent(btnguardar_maestros_clientes)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70)
                            .addComponent(txtfnac_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(txtcel_maestros_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel72.setText("Clientes");

        jLabel73.setText("Lista de Clientes");

        jLabel48.setText("Buscar por RUT:");

        txtBuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout clientesLayout = new javax.swing.GroupLayout(clientes);
        clientes.setLayout(clientesLayout);
        clientesLayout.setHorizontalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_clientes)
                .addGap(81, 81, 81)
                .addComponent(btnventas_maestros_clientes)
                .addGap(158, 158, 158))
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane16)
            .addGroup(clientesLayout.createSequentialGroup()
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel72))
                    .addGroup(clientesLayout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(400, Short.MAX_VALUE))
        );
        clientesLayout.setVerticalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jLabel48)
                    .addComponent(txtBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_maestros_clientes)
                    .addComponent(btnventas_maestros_clientes))
                .addGap(29, 29, 29))
        );

        panel_maestros.addTab("clientes", clientes);

        proveedores1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel74.setText("Rut Proveedor");

        jLabel75.setText("Nombre Contacto");

        jLabel76.setText("Direccion");

        jLabel77.setText("Razon Social");

        jLabel78.setText("Telefono");

        jLabel79.setText("E-mail");

        btncancelar_maestros_proveedores.setText("Cancelar");
        btncancelar_maestros_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_proveedoresActionPerformed(evt);
            }
        });

        btnguardar_maestros_proveedores.setText("Guardar");
        btnguardar_maestros_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_proveedoresActionPerformed(evt);
            }
        });

        cbactdes_maestros_proveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        jLabel145.setText("Estado");

        javax.swing.GroupLayout proveedores1Layout = new javax.swing.GroupLayout(proveedores1);
        proveedores1.setLayout(proveedores1Layout);
        proveedores1Layout.setHorizontalGroup(
            proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedores1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtncontacto_maestros_proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txtdireccion_maestros_proveedores)
                    .addComponent(txtrut_maestros_proveedores))
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(proveedores1Layout.createSequentialGroup()
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(txtfono_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(proveedores1Layout.createSequentialGroup()
                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(txtrazon_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedores1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar_maestros_proveedores)
                        .addGap(71, 71, 71)))
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnguardar_maestros_proveedores))
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(proveedores1Layout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addGap(35, 35, 35))
                            .addGroup(proveedores1Layout.createSequentialGroup()
                                .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemail_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbactdes_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        proveedores1Layout.setVerticalGroup(
            proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedores1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txtrut_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77)
                    .addComponent(txtrazon_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79)
                    .addComponent(txtemail_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(txtncontacto_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78)
                            .addComponent(txtfono_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbactdes_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel145))))
                .addGap(23, 23, 23)
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txtdireccion_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar_maestros_proveedores)
                    .addComponent(btnguardar_maestros_proveedores))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        proveedores_maestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Razon Social", "Nombre Contacto", "Telefono", "E-mail", "Acciones"
            }
        ));
        proveedores_maestros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proveedores_maestrosMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(proveedores_maestros);

        btncompra_maestros_proveedores.setText("Compra");
        btncompra_maestros_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncompra_maestros_proveedoresActionPerformed(evt);
            }
        });

        btneditar_maestros_proveedores.setText("Editar");
        btneditar_maestros_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_proveedoresActionPerformed(evt);
            }
        });

        jLabel80.setText("Proveedores");

        jLabel126.setText("Proveedor");

        txtbusqueda_maestros_proveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_proveedoresKeyReleased(evt);
            }
        });

        jLabel146.setText("Busqueda por RUT:");

        javax.swing.GroupLayout proveedoresLayout = new javax.swing.GroupLayout(proveedores);
        proveedores.setLayout(proveedoresLayout);
        proveedoresLayout.setHorizontalGroup(
            proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(proveedores1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane17)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedoresLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(txtbusqueda_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(311, 311, 311))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedoresLayout.createSequentialGroup()
                        .addComponent(btncompra_maestros_proveedores)
                        .addGap(74, 74, 74)
                        .addComponent(btneditar_maestros_proveedores)
                        .addGap(289, 289, 289))))
            .addGroup(proveedoresLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel126)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        proveedoresLayout.setVerticalGroup(
            proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proveedoresLayout.createSequentialGroup()
                .addComponent(jLabel126)
                .addGap(8, 8, 8)
                .addComponent(proveedores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtbusqueda_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel146))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_maestros_proveedores)
                    .addComponent(btncompra_maestros_proveedores))
                .addGap(38, 38, 38))
        );

        panel_maestros.addTab("proveedores", proveedores);

        articulos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel81.setText("Nombre  Articulo");

        jLabel82.setText("Unidades");

        jLabel83.setText("Marcas");

        jLabel85.setText("Categoria Articulo");

        jLabel86.setText("Fecha Vencimiento");

        jLabel87.setText("Estado:");

        cboxproveedor_maestros_articulos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));
        cboxproveedor_maestros_articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxproveedor_maestros_articulosActionPerformed(evt);
            }
        });

        btncancelar_maestros_articulos.setText("Cancelar");
        btncancelar_maestros_articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_articulosActionPerformed(evt);
            }
        });

        btnguardar_maestros_articulos.setText("Guardar");
        btnguardar_maestros_articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_articulosActionPerformed(evt);
            }
        });

        jDvencimiento_maestros_articulos.setDateFormatString("yyyy-MM-dd");

        cboxcategoria_maestros_articulos1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxcategoria_maestros_articulos1ItemStateChanged(evt);
            }
        });

        txtcategoria_maestros_articulos1.setFocusable(false);
        txtcategoria_maestros_articulos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcategoria_maestros_articulos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout articulos1Layout = new javax.swing.GroupLayout(articulos1);
        articulos1.setLayout(articulos1Layout);
        articulos1Layout.setHorizontalGroup(
            articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(articulos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82)
                    .addComponent(jLabel83))
                .addGap(18, 18, 18)
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnarticulo_maestros_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txtunidades_maestros_articulos)
                    .addComponent(txtmarcas_maestros_articulos))
                .addGap(139, 139, 139)
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboxproveedor_maestros_articulos, 0, 189, Short.MAX_VALUE)
                    .addComponent(jDvencimiento_maestros_articulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboxcategoria_maestros_articulos1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(btncancelar_maestros_articulos)
                        .addGap(72, 72, 72)
                        .addComponent(btnguardar_maestros_articulos)
                        .addGap(137, 137, 137))
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtcategoria_maestros_articulos1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(437, Short.MAX_VALUE))))
        );
        articulos1Layout.setVerticalGroup(
            articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(articulos1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81)
                        .addComponent(txtnarticulo_maestros_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxcategoria_maestros_articulos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcategoria_maestros_articulos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(txtunidades_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(txtmarcas_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncancelar_maestros_articulos)
                            .addComponent(btnguardar_maestros_articulos)))
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel86)
                            .addComponent(jDvencimiento_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(cboxproveedor_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );

        listaarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        listaarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaarticulosMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(listaarticulos);

        btneditar_maestros_articulos.setText("Editar");
        btneditar_maestros_articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_articulosActionPerformed(evt);
            }
        });

        jLabel138.setText("Articulos");

        jLabel139.setText("Articulos");

        txtbusqueda_maestros_articulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_articulosKeyReleased(evt);
            }
        });

        jLabel144.setText("Buscar por Nombre:");

        javax.swing.GroupLayout articulosLayout = new javax.swing.GroupLayout(articulos);
        articulos.setLayout(articulosLayout);
        articulosLayout.setHorizontalGroup(
            articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_articulos)
                .addGap(304, 304, 304))
            .addComponent(articulos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane18)
            .addGroup(articulosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel138)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(articulosLayout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(jLabel139)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
                .addComponent(jLabel144)
                .addGap(50, 50, 50)
                .addComponent(txtbusqueda_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        articulosLayout.setVerticalGroup(
            articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel138)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(articulos1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(txtbusqueda_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneditar_maestros_articulos)
                .addGap(29, 29, 29))
        );

        panel_maestros.addTab("articulos", articulos);

        tabla_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_packs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_packsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabla_packs);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        articuloselegidos_maestros_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Articulo", "Articulo", "Cantidad", "Stock Articulos", "Stock Pack"
            }
        ));
        jScrollPane5.setViewportView(articuloselegidos_maestros_packs);

        jLabel21.setText("Nombre");

        jLabel23.setText("Precio Pack");

        jLabel26.setText("unidades");

        txtunidades_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunidades_maestros_packsActionPerformed(evt);
            }
        });

        btnmas_maestros_packs.setText(">");
        btnmas_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmas_maestros_packsActionPerformed(evt);
            }
        });

        btnmenos_maestros_packs.setText("<");
        btnmenos_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenos_maestros_packsActionPerformed(evt);
            }
        });

        btncancelar_maestros_packs.setText("Cancelar");
        btncancelar_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_packsActionPerformed(evt);
            }
        });

        btncrearpack_maestros_packs.setText("Crear Pack");
        btncrearpack_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearpack_maestros_packsActionPerformed(evt);
            }
        });

        listaarticulos_maestros_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(listaarticulos_maestros_packs);

        btningresararticulo_maestros_packs.setText("Ingresar Articulos");
        btningresararticulo_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresararticulo_maestros_packsActionPerformed(evt);
            }
        });

        jLabel155.setText("Estado:");

        cboxEstados_maestros_packs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        jButton1.setText("Borrar Articulos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel21)
                        .addGap(57, 57, 57)
                        .addComponent(txtnombre_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(31, 31, 31)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnmenos_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtunidades_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnmas_maestros_packs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtprecio_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel155)
                        .addGap(18, 18, 18)
                        .addComponent(cboxEstados_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(208, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btningresararticulo_maestros_packs))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btncrearpack_maestros_packs)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btncancelar_maestros_packs)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txtnombre_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel155)
                    .addComponent(cboxEstados_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncrearpack_maestros_packs)
                            .addComponent(btncancelar_maestros_packs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btningresararticulo_maestros_packs)
                            .addComponent(jButton1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnmas_maestros_packs)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtunidades_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmenos_maestros_packs))
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(79, 79, 79))
        );

        jLabel22.setText("Packs");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Tabla Packs");

        txtbusqueda_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_maestros_packsActionPerformed(evt);
            }
        });
        txtbusqueda_maestros_packs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_packsKeyReleased(evt);
            }
        });

        btneditar_maestros_packs.setText("Editar");
        btneditar_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_packsActionPerformed(evt);
            }
        });

        btndesactivar_maestros_packs.setText("Desactivar");

        btnbuscar_maestros_packs.setText("Buscar");

        javax.swing.GroupLayout packsLayout = new javax.swing.GroupLayout(packs);
        packs.setLayout(packsLayout);
        packsLayout.setHorizontalGroup(
            packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(packsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                        .addComponent(btneditar_maestros_packs)
                        .addGap(51, 51, 51)
                        .addComponent(btndesactivar_maestros_packs)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btnbuscar_maestros_packs)
                        .addGap(26, 26, 26)
                        .addComponent(txtbusqueda_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225))))
        );
        packsLayout.setVerticalGroup(
            packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(9, 9, 9)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscar_maestros_packs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndesactivar_maestros_packs)
                    .addComponent(btneditar_maestros_packs))
                .addContainerGap())
        );

        panel_maestros.addTab("packs", packs);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setText("Nombre RRSS");

        btncancelar_maestros_rrss.setText("Cancelar");
        btncancelar_maestros_rrss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_rrssActionPerformed(evt);
            }
        });

        btnguardar_maestros_rrss.setText("Guardar");
        btnguardar_maestros_rrss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_rrssActionPerformed(evt);
            }
        });

        cbrrss_maestros_rrss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        jLabel142.setText("Estado:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(49, 49, 49)
                        .addComponent(txtnrrss_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220)
                        .addComponent(jLabel142)
                        .addGap(58, 58, 58)
                        .addComponent(cbrrss_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_maestros_rrss)
                        .addGap(64, 64, 64)))
                .addComponent(btnguardar_maestros_rrss)
                .addGap(143, 143, 143))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtnrrss_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbrrss_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel142))
                .addGap(24, 79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar_maestros_rrss)
                    .addComponent(btncancelar_maestros_rrss))
                .addContainerGap())
        );

        redessociales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        redessociales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                redessocialesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(redessociales);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Redes Sociales");

        jLabel17.setText("Redes Sociales");

        txtbusqueda_maestros_rrss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_rrssKeyReleased(evt);
            }
        });

        btneditar_maestros_rrss.setText("Editar");
        btneditar_maestros_rrss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_rrssActionPerformed(evt);
            }
        });

        jLabel141.setText("Buscar por nombre:");

        javax.swing.GroupLayout RRSSLayout = new javax.swing.GroupLayout(RRSS);
        RRSS.setLayout(RRSSLayout);
        RRSSLayout.setHorizontalGroup(
            RRSSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
            .addGroup(RRSSLayout.createSequentialGroup()
                .addGroup(RRSSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RRSSLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel141)
                        .addGap(18, 18, 18)
                        .addComponent(txtbusqueda_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RRSSLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RRSSLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_rrss)
                .addGap(200, 200, 200))
        );
        RRSSLayout.setVerticalGroup(
            RRSSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RRSSLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(RRSSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda_maestros_rrss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel141))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_rrss)
                .addContainerGap())
        );

        panel_maestros.addTab("RRSS", RRSS);

        categoria_articulos.setName("search"); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setText("Categoria articulos");

        jLabel25.setText("Categoría articulo");

        jLabel28.setText("Estado:");

        btncancelar_maestros_catarticulos.setText("Cancelar");
        btncancelar_maestros_catarticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_catarticulosActionPerformed(evt);
            }
        });

        btnguardar_maestros_catarticulos.setText("Guardar");
        btnguardar_maestros_catarticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_catarticulosActionPerformed(evt);
            }
        });

        cbcategoria_maestros_catarticulos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel25)
                .addGap(27, 27, 27)
                .addComponent(txtcategoria_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_maestros_catarticulos)
                        .addGap(99, 99, 99)
                        .addComponent(btnguardar_maestros_catarticulos)
                        .addGap(161, 161, 161))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbcategoria_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtcategoria_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(cbcategoria_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar_maestros_catarticulos)
                    .addComponent(btnguardar_maestros_catarticulos))
                .addGap(42, 42, 42))
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Categoria Articulos");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        btneditar_maestros_catarticulos.setText("Editar");
        btneditar_maestros_catarticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_catarticulosActionPerformed(evt);
            }
        });

        categoriaarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        categoriaarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriaarticulosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(categoriaarticulos);

        txtbusqueda_maestros_catarticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_catarticulosKeyReleased(evt);
            }
        });

        jLabel143.setText("Buscar por nombre:");

        javax.swing.GroupLayout categoria_articulosLayout = new javax.swing.GroupLayout(categoria_articulos);
        categoria_articulos.setLayout(categoria_articulosLayout);
        categoria_articulosLayout.setHorizontalGroup(
            categoria_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoria_articulosLayout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jLabel43)
                .addGap(140, 140, 140)
                .addComponent(jLabel143)
                .addGap(38, 38, 38)
                .addComponent(txtbusqueda_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(categoria_articulosLayout.createSequentialGroup()
                .addGroup(categoria_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoria_articulosLayout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(btneditar_maestros_catarticulos)
                        .addGap(431, 626, Short.MAX_VALUE))
                    .addGroup(categoria_articulosLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        categoria_articulosLayout.setVerticalGroup(
            categoria_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoria_articulosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(categoria_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtbusqueda_maestros_catarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel143))
                .addGroup(categoria_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoria_articulosLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(categoria_articulosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar_maestros_catarticulos)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panel_maestros.addTab("categoria articulos", categoria_articulos);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel44.setText("Comunas");

        jLabel45.setText("Nombre Comuna");

        btnguardar_maestros_comunas.setText("Guardar");
        btnguardar_maestros_comunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_comunasActionPerformed(evt);
            }
        });

        btncancelar_maestros_comunas.setText("Cancelar");
        btncancelar_maestros_comunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_comunasActionPerformed(evt);
            }
        });

        cbcomuna_maestros_comunas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        jLabel41.setText("Estado:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel45)
                        .addGap(50, 50, 50)
                        .addComponent(txtncomuna_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188)
                        .addComponent(jLabel41)
                        .addGap(44, 44, 44)
                        .addComponent(cbcomuna_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel44))
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardar_maestros_comunas)
                .addGap(40, 40, 40)
                .addComponent(btncancelar_maestros_comunas)
                .addGap(233, 233, 233))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtncomuna_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcomuna_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar_maestros_comunas)
                    .addComponent(btncancelar_maestros_comunas))
                .addContainerGap())
        );

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("Comunas Regsitradas");

        txtbusqueda_maestros_comunas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_comunasKeyReleased(evt);
            }
        });

        btneditar_maestros_comunas.setText("Editar");
        btneditar_maestros_comunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_comunasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(747, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_comunas)
                .addGap(57, 57, 57))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(btneditar_maestros_comunas)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        comunasregistradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        comunasregistradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comunasregistradasMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(comunasregistradas);

        jLabel52.setText("Buscar por nombre:");

        javax.swing.GroupLayout comunasLayout = new javax.swing.GroupLayout(comunas);
        comunas.setLayout(comunasLayout);
        comunasLayout.setHorizontalGroup(
            comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comunasLayout.createSequentialGroup()
                .addGroup(comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(comunasLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253)
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)
                        .addComponent(txtbusqueda_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(comunasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(414, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane14)
        );
        comunasLayout.setVerticalGroup(
            comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comunasLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(comunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda_maestros_comunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_maestros.addTab("comunas", comunas);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel40.setText("Nombre Banco:");

        txtnbanco_maestros_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnbanco_maestros_bancosActionPerformed(evt);
            }
        });

        btnguardar_maestros_bancos.setText("Guardar");
        btnguardar_maestros_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_bancosActionPerformed(evt);
            }
        });

        btncancelar_maestros_bancos.setText("Cancelar");
        btncancelar_maestros_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_bancosActionPerformed(evt);
            }
        });

        cbbanco_maestros_bancos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        jLabel20.setText("Estado:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel40)
                        .addGap(85, 85, 85)
                        .addComponent(txtnbanco_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(248, 248, 248)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbanco_maestros_bancos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(826, Short.MAX_VALUE)
                        .addComponent(btnguardar_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87)
                .addComponent(btncancelar_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtnbanco_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbanco_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar_maestros_bancos)
                    .addComponent(btncancelar_maestros_bancos))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        txtbusqueda_maestros_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_maestros_bancosActionPerformed(evt);
            }
        });
        txtbusqueda_maestros_bancos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_bancosKeyReleased(evt);
            }
        });

        bancosregistrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        bancosregistrados.setMaximumSize(new java.awt.Dimension(200, 64));
        bancosregistrados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bancosregistradosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(bancosregistrados);

        btneditar_maestros_bancos.setText("Editar");
        btneditar_maestros_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_bancosActionPerformed(evt);
            }
        });

        jLabel140.setText("bancos registrados");

        jLabel61.setText("Buscar por nombre:");

        javax.swing.GroupLayout bancosLayout = new javax.swing.GroupLayout(bancos);
        bancos.setLayout(bancosLayout);
        bancosLayout.setHorizontalGroup(
            bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bancosLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel140)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtbusqueda_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(301, 301, 301))
            .addComponent(jScrollPane8)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bancosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_bancos)
                .addGap(383, 383, 383))
        );
        bancosLayout.setVerticalGroup(
            bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bancosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtbusqueda_maestros_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61))
                    .addComponent(jLabel140))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditar_maestros_bancos)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panel_maestros.addTab("bancos", bancos);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel46.setText("Categoria Venta:");

        txtcategoria_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcategoria_maestros_catventasActionPerformed(evt);
            }
        });

        btnguardar_maestros_catventas.setText("Guardar");
        btnguardar_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_catventasActionPerformed(evt);
            }
        });

        btncancelar_maestros_catventas.setText("Cancelar");
        btncancelar_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_maestros_catventasActionPerformed(evt);
            }
        });

        jLabel47.setText("Estado:");

        cbcategoria_maestros_catventas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel46)
                .addGap(86, 86, 86)
                .addComponent(txtcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(jLabel47)
                .addGap(40, 40, 40)
                .addComponent(cbcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(853, Short.MAX_VALUE)
                .addComponent(btnguardar_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btncancelar_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(cbcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar_maestros_catventas)
                    .addComponent(btncancelar_maestros_catventas))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        categorias_ventas_registradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        categorias_ventas_registradas.setMaximumSize(new java.awt.Dimension(200, 64));
        categorias_ventas_registradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorias_ventas_registradasMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(categorias_ventas_registradas);

        btnbuscar_maestros_catventas.setText("Buscar");

        btneditar_maestros_catventas.setText("Editar");
        btneditar_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_maestros_catventasActionPerformed(evt);
            }
        });

        txtbusqueda_maestros_catventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_catventasKeyReleased(evt);
            }
        });

        jLabel42.setText("Categorias Registradas");

        javax.swing.GroupLayout categoria_ventasLayout = new javax.swing.GroupLayout(categoria_ventas);
        categoria_ventas.setLayout(categoria_ventasLayout);
        categoria_ventasLayout.setHorizontalGroup(
            categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane10)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoria_ventasLayout.createSequentialGroup()
                .addContainerGap(352, Short.MAX_VALUE)
                .addGroup(categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoria_ventasLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(225, 225, 225)
                        .addComponent(btnbuscar_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(txtbusqueda_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(330, 330, 330))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoria_ventasLayout.createSequentialGroup()
                        .addComponent(btneditar_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(453, 453, 453))))
        );
        categoria_ventasLayout.setVerticalGroup(
            categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoria_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar_maestros_catventas)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btneditar_maestros_catventas)
                .addGap(22, 22, 22))
        );

        panel_maestros.addTab("categoria ventas", categoria_ventas);

        txtbusqueda_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_maestros_usuariosActionPerformed(evt);
            }
        });
        txtbusqueda_maestros_usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqueda_maestros_usuariosKeyReleased(evt);
            }
        });

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsuarios.setMaximumSize(new java.awt.Dimension(200, 64));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tablaUsuarios);

        btneliminar_maestros_usuarios.setText("Eliminar");
        btneliminar_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_maestros_usuariosActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setText("Nombre Usuario:");

        txtusuario_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuario_maestros_usuariosActionPerformed(evt);
            }
        });

        jLabel50.setText("Contraseña:");

        btnGuardar_maestros_usuarios.setText("Guardar");
        btnGuardar_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_maestros_usuariosActionPerformed(evt);
            }
        });

        btnCancelar_maestros_usuarios.setText("Cancelar");
        btnCancelar_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar_maestros_usuariosActionPerformed(evt);
            }
        });

        txtcontraseña_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseña_maestros_usuariosActionPerformed(evt);
            }
        });

        btnactualizar_maestros_usuarios.setText("Actualizar Datos");
        btnactualizar_maestros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizar_maestros_usuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnactualizar_maestros_usuarios)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel50)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtcontraseña_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(txtusuario_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtusuario_maestros_usuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcontraseña_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar_maestros_usuarios)
                    .addComponent(btnGuardar_maestros_usuarios)
                    .addComponent(btnactualizar_maestros_usuarios))
                .addGap(23, 23, 23))
        );

        jLabel84.setText("Buscar por nombre:");

        javax.swing.GroupLayout usuariosLayout = new javax.swing.GroupLayout(usuarios);
        usuarios.setLayout(usuariosLayout);
        usuariosLayout.setHorizontalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosLayout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbusqueda_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 693, Short.MAX_VALUE))
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane11)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneliminar_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(371, 371, 371))
        );
        usuariosLayout.setVerticalGroup(
            usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda_maestros_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneliminar_maestros_usuarios)
                .addGap(38, 38, 38))
        );

        panel_maestros.addTab("usuarios", usuarios);

        jLabel38.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel38.setText("Dream Gifts");

        javax.swing.GroupLayout maestrosLayout = new javax.swing.GroupLayout(maestros);
        maestros.setLayout(maestrosLayout);
        maestrosLayout.setHorizontalGroup(
            maestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_maestros, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(maestrosLayout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        maestrosLayout.setVerticalGroup(
            maestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, maestrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panel_maestros, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablas.addTab("Maestros", maestros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnfactura_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnfactura_compras_revisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnfactura_compras_revisionActionPerformed

    private void txtbusqueda_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_packsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_packsActionPerformed

    private void txttransaccion_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttransaccion_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttransaccion_ventas_confirmacionActionPerformed

    private void btnguardar_ventas_adespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_ventas_adespachoActionPerformed
   ActualizarDatosActualizacionDespacho();
   MostrarDatosActualizacionDespachos();
   MostrarDatosVentas();
   MostrarDatosListaDestino();
    }//GEN-LAST:event_btnguardar_ventas_adespachoActionPerformed

    private void txtnbanco_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnbanco_maestros_bancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnbanco_maestros_bancosActionPerformed

    private void txtcategoria_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcategoria_maestros_catventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcategoria_maestros_catventasActionPerformed

    private void btnguardar_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_catventasActionPerformed
insertarDatosCatVentas();
limpiarCajasCatVentas();
mostrarDatosCatVentas();
llenar_comboEstado();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_catventasActionPerformed

    private void txtusuario_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuario_maestros_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuario_maestros_usuariosActionPerformed

    private void txtcontraseña_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseña_maestros_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseña_maestros_usuariosActionPerformed

    private void txtbusqueda_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_ventas_confirmacionActionPerformed

    private void btnbuscar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar_ventas_confirmacionActionPerformed

    private void btnconfirmar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmar_ventas_confirmacionActionPerformed
        actualizarDatosVentas();
        MostrarDatosVentas();
        limpiarCajasConfirmacion();
        MostrarDatosActualizacionDespachos();
        MostrarDatosListaDestino();
        llenar_comboEstado();
    }//GEN-LAST:event_btnconfirmar_ventas_confirmacionActionPerformed

    private void btnver_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnver_compras_revisionActionPerformed
 String factura;
    
        int fila=facturas_compras_inventariadas.getSelectedRow(); 
      factura=facturas_compras_inventariadas.getValueAt(fila,3).toString();
      mostrarDatoDetalle(factura);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnver_compras_revisionActionPerformed

    private void txtunidades_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunidades_maestros_packsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtunidades_maestros_packsActionPerformed

    private void btnmenos_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenos_maestros_packsActionPerformed
//eliminarRegistrosPack1();
limpiar_TablaPacks();






        // TODO add your handling code here:
    }//GEN-LAST:event_btnmenos_maestros_packsActionPerformed

    private void btneditar_maestros_comunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_comunasActionPerformed
actualizarDatosComunas();
mostrarDatosComunas();
limpiarCajasComunas();
llenar_comboComunas();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_comunasActionPerformed

    private void btnGuardar_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_maestros_usuariosActionPerformed
                    insertarDatos(); 
                    limpiarCajas(); 
                    mostrarDatos();
// TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar_maestros_usuariosActionPerformed

    private void btnCancelar_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar_maestros_usuariosActionPerformed
limpiarCajas(); 
// TODO add your handling code here:
    }//GEN-LAST:event_btnCancelar_maestros_usuariosActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked

int filaSeleccionada=tablaUsuarios.rowAtPoint(evt.getPoint());

txtusuario_maestros_usuarios.setText(tablaUsuarios.getValueAt(filaSeleccionada, 1).toString());
txtcontraseña_maestros_usuarios.setText(tablaUsuarios.getValueAt(filaSeleccionada, 2).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnactualizar_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizar_maestros_usuariosActionPerformed
actualizarDatos();
limpiarCajas();
mostrarDatos();
// TODO add your handling code here:
    }//GEN-LAST:event_btnactualizar_maestros_usuariosActionPerformed

    private void btneliminar_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_maestros_usuariosActionPerformed
eliminarRegistros();
mostrarDatos();
limpiarCajas();



        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar_maestros_usuariosActionPerformed

    private void txtbusqueda_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_usuariosActionPerformed

    private void txtbusqueda_maestros_usuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_usuariosKeyReleased
filtrarDatos(txtbusqueda_maestros_usuarios.getText());

// TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_usuariosKeyReleased

    private void txtbusqueda_ventas_llistadestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_ventas_llistadestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_ventas_llistadestinoActionPerformed

    private void txtrut_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrut_informes_infodevycambiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrut_informes_infodevycambiosActionPerformed

    private void btndescargar_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescargar_informes_infodevycambiosActionPerformed
PDFimformesDevolucion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btndescargar_informes_infodevycambiosActionPerformed

    private void btnguardar_maestros_comunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_comunasActionPerformed
insertarDatosComunas();
mostrarDatosComunas();
limpiarCajasComunas();
llenar_comboComunas();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_comunasActionPerformed

    private void btnguardar_maestros_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_clientesActionPerformed
     insertarDatosClientes();
     mostrarDatosClientes();
     limpiarCajasClientes();
     
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_clientesActionPerformed

    private void btncancelar_maestros_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_clientesActionPerformed
limpiarCajasClientes();


        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_clientesActionPerformed

    private void btneditar_maestros_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_clientesActionPerformed
 actualizarDatosClientes();
 limpiarCajasClientes();
 mostrarDatosClientes();
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_clientesActionPerformed

    private void listadeclientes_maestrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadeclientes_maestrosMouseClicked
int filaSeleccionadoo= listadeclientes_maestros.rowAtPoint(evt.getPoint());


txtrut_maestros_clientes.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,0).toString());

txt_ncliente_maestros_clientes.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,1).toString());

txtcel_maestros_clientes.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,2).toString());

txtfono_maestros_clientes.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,3).toString());

txtemaill_maestros_clientes.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,4).toString());

String valor= listadeclientes_maestros.getValueAt(filaSeleccionadoo,6).toString();
String cliente="Activado";
limpiarCajasVentasCl();
        if (cliente.equals(valor)) {
          txtemail_ventas_venta.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,4).toString());
          txtfono_ventas_venta.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,2).toString());
          txtncliente_ventas_venta.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,1).toString());
          txtrut_ventas_venta.setText(listadeclientes_maestros.getValueAt(filaSeleccionadoo,0).toString());
          
          
        }


try{

Date date=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)listadeclientes_maestros.getValueAt(filaSeleccionadoo,5));
txtfnac_maestros_clientes.setDate(date);
}catch(Exception e){
    JOptionPane.showMessageDialog(null, "Error al editar la fecha "+e.getMessage());    
}

cboxatcdes_maestros_clientes.setSelectedItem(listadeclientes_maestros.getValueAt(filaSeleccionadoo,6));   

//txtfnac_maestros_clientes.setDate((java.util.Date) listadeclientes_maestros.getValueAt(filaSeleccionadoo,5));
   
 
    }//GEN-LAST:event_listadeclientes_maestrosMouseClicked

    private void txtBuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyReleased
        filtrarDatosClientes(txtBuscarClientes.getText());

        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClientesKeyReleased

    private void btnguardar_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_bancosActionPerformed
insertarDatosBanco();
limpiarCajasBanco();
mostrarDatosBanco();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_bancosActionPerformed

    private void btncancelar_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_bancosActionPerformed
limpiarCajasBanco();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_bancosActionPerformed

    private void btneditar_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_bancosActionPerformed
actualizarDatosBanco();
limpiarCajasBanco();
mostrarDatosBanco();
llenar_comboBanco();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_bancosActionPerformed

    private void txtbusqueda_maestros_bancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_bancosKeyReleased
        filtrarDatosBanco(txtbusqueda_maestros_bancos.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_bancosKeyReleased

    private void btnguardar_maestros_rrssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_rrssActionPerformed
insertarDatosRrss();
mostrarDatosRrss();
limpiarCajasRrss();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_rrssActionPerformed

    private void btncancelar_maestros_comunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_comunasActionPerformed
limpiarCajasComunas();


        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_comunasActionPerformed

    private void comunasregistradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comunasregistradasMouseClicked
int filaSeleccionadoo= comunasregistradas.rowAtPoint(evt.getPoint());

txtncomuna_maestros_comunas.setText(comunasregistradas.getValueAt(filaSeleccionadoo, 1).toString());
cbcomuna_maestros_comunas.setSelectedItem(comunasregistradas.getValueAt(filaSeleccionadoo, 2));

        // TODO add your handling code here:
    }//GEN-LAST:event_comunasregistradasMouseClicked

    private void txtbusqueda_maestros_comunasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_comunasKeyReleased
        filtrarDatosComunas(txtbusqueda_maestros_comunas.getText());
        

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_comunasKeyReleased

    private void btncancelar_maestros_rrssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_rrssActionPerformed
limpiarCajasRrss();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_rrssActionPerformed

    private void btneditar_maestros_rrssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_rrssActionPerformed
actualizarDatosRrss();
limpiarCajasRrss();
mostrarDatosRrss();
llenar_comboRRSS();
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_rrssActionPerformed

    private void txtbusqueda_maestros_rrssKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_rrssKeyReleased
        filtrarDatosRrss(txtbusqueda_maestros_rrss.getText());
        

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_rrssKeyReleased

    private void redessocialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redessocialesMouseClicked
int filaSeleccionadoo= redessociales.rowAtPoint(evt.getPoint());

txtnrrss_maestros_rrss.setText(redessociales.getValueAt(filaSeleccionadoo, 1).toString());
cbrrss_maestros_rrss.setSelectedItem(redessociales.getValueAt(filaSeleccionadoo, 2));
        // TODO add your handling code here:
    }//GEN-LAST:event_redessocialesMouseClicked

    private void txtbusqueda_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_bancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_bancosActionPerformed

    private void bancosregistradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bancosregistradosMouseClicked
int filaSeleccionadoo= bancosregistrados.rowAtPoint(evt.getPoint());

txtnbanco_maestros_bancos.setText(bancosregistrados.getValueAt(filaSeleccionadoo, 1).toString());
cbbanco_maestros_bancos.setSelectedItem(bancosregistrados.getValueAt(filaSeleccionadoo,2)); 
        // TODO add your handling code here:
    }//GEN-LAST:event_bancosregistradosMouseClicked

    private void btnguardar_maestros_catarticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_catarticulosActionPerformed
insertarDatosCategoriaArt();
mostrarDatosCategoriaArt();
limpiarCajasCategoriaArt();
llenar_comboArticulos();


        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_catarticulosActionPerformed

    private void btncancelar_maestros_catarticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_catarticulosActionPerformed
limpiarCajasCategoriaArt();


        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_catarticulosActionPerformed

    private void categoriaarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriaarticulosMouseClicked
int filaSeleccionadoo= categoriaarticulos.rowAtPoint(evt.getPoint());

txtcategoria_maestros_catarticulos.setText(categoriaarticulos.getValueAt(filaSeleccionadoo, 1).toString());
cbcategoria_maestros_catarticulos.setSelectedItem(categoriaarticulos.getValueAt(filaSeleccionadoo,2)); 

        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaarticulosMouseClicked

    private void btneditar_maestros_catarticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_catarticulosActionPerformed
actualizarDatosCategoriaArt();
mostrarDatosCategoriaArt();
limpiarCajasCategoriaArt();
llenar_comboArticulos();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_catarticulosActionPerformed

    private void txtbusqueda_maestros_catarticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_catarticulosKeyReleased
        filtrarDatosCategoriaArt(txtbusqueda_maestros_catarticulos.getText());
        

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_catarticulosKeyReleased

    private void btnguardar_maestros_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_proveedoresActionPerformed
InsertarDatosProveedores();
limpiarCajasProveedores();
MostrarDatosProveedores();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_proveedoresActionPerformed

    private void cboxproveedor_maestros_articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxproveedor_maestros_articulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxproveedor_maestros_articulosActionPerformed

    private void btnguardar_maestros_articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_articulosActionPerformed
        insertarDatosArticulos();
        limpiarCajasArticulos();
        mostrarDatosArticulos();
        llenar_TablaArticulos();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_articulosActionPerformed

    private void btncancelar_maestros_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_proveedoresActionPerformed
limpiarCajasProveedores();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_proveedoresActionPerformed

    private void btncancelar_maestros_articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_articulosActionPerformed
limpiarCajasArticulos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_articulosActionPerformed

    private void btneditar_maestros_articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_articulosActionPerformed
actualizarDatosArticulos();
limpiarCajasArticulos();
mostrarDatosArticulos();


        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_articulosActionPerformed

    private void listaarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaarticulosMouseClicked
int filaSeleccionadoo= listaarticulos.rowAtPoint(evt.getPoint());

String asd =(String) listaarticulos.getValueAt(filaSeleccionadoo,1);

cboxcategoria_maestros_articulos1.setSelectedItem(listaarticulos.getValueAt(filaSeleccionadoo,1));

txtnarticulo_maestros_articulo.setText(listaarticulos.getValueAt(filaSeleccionadoo,2).toString());
txtunidades_maestros_articulos.setText(listaarticulos.getValueAt(filaSeleccionadoo,3).toString());

try{

Date date=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)listaarticulos.getValueAt(filaSeleccionadoo,4));
jDvencimiento_maestros_articulos.setDate(date);
}catch(Exception e){
    JOptionPane.showMessageDialog(null, "Error al editar la fecha "+e.getMessage());    
}


txtmarcas_maestros_articulos.setText(listaarticulos.getValueAt(filaSeleccionadoo,5).toString());


cboxproveedor_maestros_articulos.setSelectedItem(listaarticulos.getValueAt(filaSeleccionadoo,6));   

        
    }//GEN-LAST:event_listaarticulosMouseClicked

    private void txtbusqueda_maestros_articulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_articulosKeyReleased
        filtrarDatosArticulos(txtbusqueda_maestros_articulos.getText());
        

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_articulosKeyReleased

    private void btnmas_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmas_maestros_packsActionPerformed
      DefaultTableModel modelo;
      String id_articulo,articulo,cantidad,Stock_articulo,stock_pack;
      double valor;
        int fila=listaarticulos_maestros_packs.getSelectedRow();
        
        try {
            
            modelo=(DefaultTableModel)listaarticulos_maestros_packs.getModel();
            id_articulo=listaarticulos_maestros_packs.getValueAt(fila,0).toString();
            articulo=listaarticulos_maestros_packs.getValueAt(fila,1).toString();
            cantidad= txtunidades_maestros_packs.getText();
            Stock_articulo=listaarticulos_maestros_packs.getValueAt(fila,2).toString();
            
            valor=(Integer.parseInt(Stock_articulo))/ (Integer.parseInt(cantidad));
            
            
          modelo=(DefaultTableModel)articuloselegidos_maestros_packs.getModel();
          stock_pack=Integer.toString((int) valor);
          String filamodelo[]={id_articulo,articulo,cantidad,Stock_articulo,stock_pack};
          
          modelo.addRow(filamodelo);
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al traspasar datos"+e.getMessage());
        }
  
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmas_maestros_packsActionPerformed

    private void btncancelar_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_packsActionPerformed
limpiar_TablaPacksCompleta();
limpiarCajasPack();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_packsActionPerformed

    private void btncrearpack_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearpack_maestros_packsActionPerformed

insertarDatosPacks();
MostrarDatosPack();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncrearpack_maestros_packsActionPerformed

    private void txtbusqueda_maestros_packsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_packsKeyReleased
        FiltrarDatosPack(txtbusqueda_maestros_packs.getText());


        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_packsKeyReleased

    private void btneditar_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_packsActionPerformed
actualizarDatosPack();
limpiarCajasPack();
MostrarDatosPack();
llenar_comboPacks();



        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_packsActionPerformed

    private void btningresararticulo_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresararticulo_maestros_packsActionPerformed
insertarDatosHasArticulo();
//pruebaaa();

        // TODO add your handling code here:
    }//GEN-LAST:event_btningresararticulo_maestros_packsActionPerformed

    private void txtbusqueda_maestros_proveedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_proveedoresKeyReleased
        FiltarDatosProveedores(txtbusqueda_maestros_proveedores.getText());

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_proveedoresKeyReleased

    private void btneditar_maestros_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_proveedoresActionPerformed
ActualizarDatosProveedores();
limpiarCajasProveedores();
MostrarDatosProveedores();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_proveedoresActionPerformed

    private void proveedores_maestrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedores_maestrosMouseClicked
int filaSeleccionado= proveedores_maestros.rowAtPoint(evt.getPoint());

      txtrut_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 0).toString());
      txtncontacto_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 1).toString());
      txtfono_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 2).toString());
      txtemail_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 3).toString());
      txtdireccion_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 4).toString());
      txtrazon_maestros_proveedores.setText(proveedores_maestros.getValueAt(filaSeleccionado, 5).toString());
      cbactdes_maestros_proveedores.setSelectedItem(proveedores_maestros.getValueAt(filaSeleccionado,6)); 

        // TODO add your handling code here:
    }//GEN-LAST:event_proveedores_maestrosMouseClicked

    private void btncompra_maestros_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncompra_maestros_proveedoresActionPerformed
DefaultTableModel modelo;
      String rut,nombre,telefono,correo,direccion,razonSocial;
      int fila=proveedores_maestros.getSelectedRow();  
      
      
           
        
        try {
            
            modelo=(DefaultTableModel)proveedores_maestros.getModel();
            rut=proveedores_maestros.getValueAt(fila,0).toString();
            nombre=proveedores_maestros.getValueAt(fila,1).toString();
            telefono=proveedores_maestros.getValueAt(fila,2).toString();
            correo=proveedores_maestros.getValueAt(fila,3).toString();
            direccion=proveedores_maestros.getValueAt(fila,4).toString();
            razonSocial=proveedores_maestros.getValueAt(fila,5).toString();
             modelo=(DefaultTableModel)proveedores_compra.getModel();
         
          String filamodelo[]={rut,nombre,telefono,correo,direccion,razonSocial};
          
          modelo.addRow(filamodelo);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al traspasar datos"+e.getMessage());
        } 
        compras.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btncompra_maestros_proveedoresActionPerformed

    private void btncancelar_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_maestros_catventasActionPerformed
limpiarCajasCatVentas();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_maestros_catventasActionPerformed

    private void btneditar_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_catventasActionPerformed
actualizarDatosCatVentas();
limpiarCajasCatVentas();
mostrarDatosCatVentas();
llenar_comboEstado();
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_catventasActionPerformed

    private void txtbusqueda_maestros_catventasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_catventasKeyReleased
        filtrarDatosCatVentas(txtbusqueda_maestros_catventas.getText());

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_catventasKeyReleased

    private void categorias_ventas_registradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorias_ventas_registradasMouseClicked
int filaSeleccionadoo= categorias_ventas_registradas.rowAtPoint(evt.getPoint());

txtcategoria_maestros_catventas.setText(categorias_ventas_registradas.getValueAt(filaSeleccionadoo, 1).toString());
cbcategoria_maestros_catventas.setSelectedItem(categorias_ventas_registradas.getValueAt(filaSeleccionadoo,2)); 

        // TODO add your handling code here:
    }//GEN-LAST:event_categorias_ventas_registradasMouseClicked

    private void tabla_packsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_packsMouseClicked
actualizarDatosHasArticulo();

int filaSeleccionadoo= tabla_packs.rowAtPoint(evt.getPoint());



txtnombre_maestros_packs.setText(tabla_packs.getValueAt(filaSeleccionadoo,1).toString());


txtprecio_maestros_packs.setText(tabla_packs.getValueAt(filaSeleccionadoo,2).toString());




//cboxproveedor_maestros_articulos.setSelectedItem(listaarticulos.getValueAt(filaSeleccionadoo,4));  
  
    }//GEN-LAST:event_tabla_packsMouseClicked

    private void btnventas_maestros_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventas_maestros_clientesActionPerformed
tablas.setSelectedIndex(0);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnventas_maestros_clientesActionPerformed

    private void cboxpacks_ventas_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxpacks_ventas_ventaActionPerformed

    private void btnguardardestinatario_ventas_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardardestinatario_ventas_ventaActionPerformed
InsertarDatosDestinatario();
limpiarCajasVentas();
limpiarCajasVentasCl();
MostrarDatosVentas();
numeroMayor();
MostrarDatosListaDestino();
MostrarDatosActualizacionDespachos();
llenar_comboEstado();

   
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardardestinatario_ventas_ventaActionPerformed

    private void cboxpacks_ventas_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaKeyPressed
       // System.out.println(" asd1");

        // TODO add your handling code here:
    }//GEN-LAST:event_cboxpacks_ventas_ventaKeyPressed

    private void cboxpacks_ventas_ventaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaPropertyChange
    // System.out.println(" asd11");

        
// TODO add your handling code here:
    }//GEN-LAST:event_cboxpacks_ventas_ventaPropertyChange

    private void cboxpacks_ventas_ventaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaMousePressed
// System.out.println(" asd161");

        // TODO add your handling code here:
    }//GEN-LAST:event_cboxpacks_ventas_ventaMousePressed

    private void cboxpacks_ventas_ventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaMouseEntered
  //  System.out.println(" asd161"); 
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxpacks_ventas_ventaMouseEntered

    private void cboxpacks_ventas_ventaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxpacks_ventas_ventaItemStateChanged
    
 int seleccionado= cboxpacks_ventas_venta.getSelectedIndex();
 String packs=cboxpacks_ventas_venta.getItemAt(seleccionado);
 
 String[] registros= new String[2];
 String SQL="select * from pack";
 
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("PCK_NOMBRE");
             registros[1]=rs.getString("PCK_COSTO"); 
             
            if (packs.equals(registros[0])) {
            
          String  valor= registros[1];
          txtsubtotal_ventas_venta.setText(valor);
          
          sumaTotal();
        } 
             
             
         }
     
         
        
 
     }catch(Exception e){
           
           }
 
 
    }//GEN-LAST:event_cboxpacks_ventas_ventaItemStateChanged

    private void cboxcategoria_maestros_articulos1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxcategoria_maestros_articulos1ItemStateChanged
 int seleccionado= cboxcategoria_maestros_articulos1.getSelectedIndex();
 String packs=cboxcategoria_maestros_articulos1.getItemAt(seleccionado);
 
 String[] registros= new String[2];
 String SQL="SELECT * from categoria_articulo where CAT_ACTIVADO_DESACTIVADO='Activado' ";
 
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("ID_Categoria");
            // registros[1]=rs.getString("ID_CATEGORIA"); 
             registros[1]=rs.getString("CAT_DESCRIPCION");
             
            if (packs.equals(registros[1])) {
            
          String  valor= registros[0];
          txtcategoria_maestros_articulos1.setText(valor);
          
          
        } 
             
             
         }
     
         
        
 
     }catch(Exception e){
           
           }

        // TODO add your handling code here:
    }//GEN-LAST:event_cboxcategoria_maestros_articulos1ItemStateChanged

    private void txtcategoria_maestros_articulos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcategoria_maestros_articulos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcategoria_maestros_articulos1ActionPerformed

    private void txtnpedido_ventas_confirmacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnpedido_ventas_confirmacionKeyReleased
 llenarIDVentas();
 
    }//GEN-LAST:event_txtnpedido_ventas_confirmacionKeyReleased

    private void btncancelardestinatario_ventas_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelardestinatario_ventas_ventaActionPerformed
limpiarCajasVentas();

        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelardestinatario_ventas_ventaActionPerformed

    private void btncancelar_clte_ventas_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_clte_ventas_ventaActionPerformed
limpiarCajasVentasCl();
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_clte_ventas_ventaActionPerformed

    private void ventas_pendientes_pagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventas_pendientes_pagoMouseClicked
int filaSeleccionadoo= ventas_pendientes_pago.rowAtPoint(evt.getPoint());


txtnpedido_ventas_confirmacion.setText(ventas_pendientes_pago.getValueAt(filaSeleccionadoo,0).toString());
llenarIDVentas();
        // TODO add your handling code here:
    }//GEN-LAST:event_ventas_pendientes_pagoMouseClicked

    private void cboxbanco_ventas_confirmacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxbanco_ventas_confirmacionItemStateChanged
   int seleccionado= cboxbanco_ventas_confirmacion.getSelectedIndex();
 String packs=cboxbanco_ventas_confirmacion.getItemAt(seleccionado);
 
 String[] registros= new String[2];
 String SQL="select * from bancos";
 
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("BAN_ID_BANCO");
             registros[1]=rs.getString("BAN_DESCRIPCION"); 
             
            if (packs.equals(registros[1])) {
            
          String  valor= registros[0];
          txtbanco_ventas_confirmacion.setText(valor);
          } 
         } 
       }catch(Exception e){
           
           } 
    }//GEN-LAST:event_cboxbanco_ventas_confirmacionItemStateChanged

    private void cbonestados_ventas_confirmacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbonestados_ventas_confirmacionItemStateChanged
   int seleccionado= cbonestados_ventas_confirmacion.getSelectedIndex();
 String packs=cbonestados_ventas_confirmacion.getItemAt(seleccionado);
 
 String[] registros= new String[2];
 String SQL="select * from estados_venta";
 
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("EST_ID_ESTADO");
             registros[1]=rs.getString("EST_DESCRIPCION"); 
             
            if (packs.equals(registros[1])) {
            
          String  valor= registros[0];
          txtestados_ventas_confirmacion.setText(valor);
          } 
         } 
       }catch(Exception e){
           
           } 
    }//GEN-LAST:event_cbonestados_ventas_confirmacionItemStateChanged

    private void btncancelar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_ventas_confirmacionActionPerformed
limpiarCajasConfirmacion();
        
    }//GEN-LAST:event_btncancelar_ventas_confirmacionActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btncancelar_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_compras_solicitudesActionPerformed
limpiar_Tabla_proveedores_compras();
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_compras_solicitudesActionPerformed

    private void txtbusqueda_ventas_llistadestinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_ventas_llistadestinoKeyReleased
        filtrarDatosListaDestino(txtbusqueda_ventas_llistadestino.getText());

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_ventas_llistadestinoKeyReleased

    private void detalle_dev_cambiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detalle_dev_cambiosMouseClicked
    int filaSeleccionadoo= detalle_dev_cambios.rowAtPoint(evt.getPoint());


cboxestado_ventas_adespacho.setSelectedItem(detalle_dev_cambios.getValueAt(filaSeleccionadoo,7).toString());
    }//GEN-LAST:event_detalle_dev_cambiosMouseClicked

    private void cboxestado_ventas_adespachoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxestado_ventas_adespachoItemStateChanged
     int seleccionado= cboxestado_ventas_adespacho.getSelectedIndex();
 String packs=cboxestado_ventas_adespacho.getItemAt(seleccionado);
 
 String[] registros= new String[2];
 String SQL="select * from estados_venta";
 
     try{
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while(rs.next()){
         
             registros[0]=rs.getString("EST_ID_ESTADO");
             registros[1]=rs.getString("EST_DESCRIPCION"); 
             
            if (packs.equals(registros[1])) {
            
          String  valor= registros[0];
          txtestado_ventas_adespacho.setText(valor);
          } 
         } 
       }catch(Exception e){
           
           } 
    }//GEN-LAST:event_cboxestado_ventas_adespachoItemStateChanged

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        filtrarDatosActualizacionDespachoFecha(jTextField2.getText());
     
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        filtrarDatosActualizacionDespachoID(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void btndescarga_ventas_listadestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescarga_ventas_listadestinoActionPerformed
PDFListaDestino();



        // TODO add your handling code here:
    }//GEN-LAST:event_btndescarga_ventas_listadestinoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
eliminarRegistrosPack1();
limpiar_TablaPacks();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnmas_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmas_compras_solicitudesActionPerformed
DefaultTableModel modelo;
      String id_articulo,articulo,cantidad,precio;
      
        int fila=listapedido_compras_solicitudes.getSelectedRow();
        
        try {
            
            modelo=(DefaultTableModel)listapedido_compras_solicitudes.getModel();
            id_articulo=listapedido_compras_solicitudes.getValueAt(fila,0).toString();
            articulo=listapedido_compras_solicitudes.getValueAt(fila,1).toString();
            cantidad= txtunidades_compras_solicitudes.getText();
            precio=txtPrecio_compras_solicitudes.getText();
           
            
            
          modelo=(DefaultTableModel)listaCompras_solicitudes.getModel();
          
          String filamodelo[]={id_articulo,articulo,cantidad,precio};
          
          modelo.addRow(filamodelo);
          
        sumaCantidades();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al traspasar datos"+e.getMessage());
        }
  
    }//GEN-LAST:event_btnmas_compras_solicitudesActionPerformed

    private void btnguardar_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_compras_solicitudesActionPerformed
InsertarDatosCompras();
insertarDatosHasArticuloSolPedidos();
MostrarDatosSolPedidos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_compras_solicitudesActionPerformed

    private void detalle_pedidos_realizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detalle_pedidos_realizadosMouseClicked
actualizarDatosClikerSolPedidos ();
sumaCantidades();
int filaSeleccionadoo= detalle_pedidos_realizados.rowAtPoint(evt.getPoint());
try{

Date date=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)detalle_pedidos_realizados.getValueAt(filaSeleccionadoo,3));
datepedido_compras_solicitudes.setDate(date);
}catch(Exception e){
    JOptionPane.showMessageDialog(null, "Error al editar la fecha "+e.getMessage());    
}
    }//GEN-LAST:event_detalle_pedidos_realizadosMouseClicked

    private void btneditar_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_compras_solicitudesActionPerformed
actualizarDatosSolPedidos();
MostrarDatosSolPedidos();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_compras_solicitudesActionPerformed

    private void btnmenos_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenos_compras_solicitudesActionPerformed
limpiar_TablaCompras();
sumaCantidades();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnmenos_compras_solicitudesActionPerformed

    private void txtnfactura_compras_revisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnfactura_compras_revisionKeyReleased
FiltrarDatosPorNumeroFactura(txtnfactura_compras_revision.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnfactura_compras_revisionKeyReleased

    private void txtrut_compras_revisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut_compras_revisionKeyReleased
FiltrarDatosPorRut(txtrut_compras_revision.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrut_compras_revisionKeyReleased

    private void txtfrecepcion_compras_revisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfrecepcion_compras_revisionKeyReleased
FiltrarDatosPorFecha(txtfrecepcion_compras_revision.getText());

        // TODO add your handling code here:
    }//GEN-LAST:event_txtfrecepcion_compras_revisionKeyReleased

    private void txtrut_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrut_compras_revisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrut_compras_revisionActionPerformed

    private void btncancelar_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_compras_revisionActionPerformed
limpiarCajasRevision();
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_compras_revisionActionPerformed

    private void txtrut_informes_infoclientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut_informes_infoclientesKeyReleased
        BuscarDatosPack(txtrut_informes_infoclientes.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrut_informes_infoclientesKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
insertarDatosHasArticuloSolPedidosArticulos();
MostrarDatosSolPedidos();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btngeneraroc_compras_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneraroc_compras_solicitudesActionPerformed
PDFSolicitudPedidos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btngeneraroc_compras_solicitudesActionPerformed

    private void btnbuscarinfo_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarinfo_informes_infodevycambiosActionPerformed
filtrarDatosPorFechaDevolucion(((JTextField)desde_dev_y_cambios.getDateEditor().getUiComponent()).getText(),((JTextField)hasta_dev_y_cambios.getDateEditor().getUiComponent()).getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscarinfo_informes_infodevycambiosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
limpiarCajasDevolucion();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtrut_informes_infodevycambiosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut_informes_infodevycambiosKeyReleased
filtarDatosPorRutDevolucion(txtrut_informes_infodevycambios.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrut_informes_infodevycambiosKeyReleased

    private void btnbuscarrango_informes_infoventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarrango_informes_infoventasActionPerformed
FiltrarPorFechaVentas(((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText(),((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscarrango_informes_infoventasActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
limpiarCajasInformaVentas();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btndescargar_informes_infoventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescargar_informes_infoventasActionPerformed
PdfinformesVentas();
        // TODO add your handling code here:
    }//GEN-LAST:event_btndescargar_informes_infoventasActionPerformed

    private void txtbuscarut_informes_infoventasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarut_informes_infoventasKeyReleased
filtrarDatosPorRutVentas(txtbuscarut_informes_infoventas.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarut_informes_infoventasKeyReleased

    private void btnbuscar_informes_infoinventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_informes_infoinventariosActionPerformed
filtrarPorFechaInventario(((JTextField)txtInformeDesde.getDateEditor().getUiComponent()).getText(),((JTextField)txtInformeHasta.getDateEditor().getUiComponent()).getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar_informes_infoinventariosActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 limpiarCajasInventario();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btndescargar_informes_infoinventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescargar_informes_infoinventariosActionPerformed
pdfInformeInventario();       
        // TODO add your handling code here:
    }//GEN-LAST:event_btndescargar_informes_infoinventariosActionPerformed

    private void articulo_info_inventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articulo_info_inventarioKeyReleased
filtrarDatosPorcategoria(articulo_info_inventario.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_articulo_info_inventarioKeyReleased

    private void btnbuscarinfo_informes_infoclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarinfo_informes_infoclientesActionPerformed
filtrarDatosPorFechaInforCliente(((JTextField)desde_informe_cliente.getDateEditor().getUiComponent()).getText(),((JTextField)hasta_informe_cliente.getDateEditor().getUiComponent()).getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscarinfo_informes_infoclientesActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

limpiarCajaInforCliente();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btndescargas_informes_infoclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescargas_informes_infoclientesActionPerformed
PDFimformesClientes();
        // TODO add your handling code here:
    }//GEN-LAST:event_btndescargas_informes_infoclientesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tablas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RRSS;
    private javax.swing.JTextField articulo_info_inventario;
    private javax.swing.JPanel articulos;
    private javax.swing.JPanel articulos1;
    private javax.swing.JTable articuloselegidos_maestros_packs;
    private javax.swing.JPanel bancos;
    private javax.swing.JTable bancosregistrados;
    private javax.swing.JButton btnCancelar_maestros_usuarios;
    private javax.swing.JButton btnGuardar_maestros_usuarios;
    private javax.swing.JButton btnactualizar_maestros_usuarios;
    private javax.swing.JButton btnagregar_compras_registro;
    private javax.swing.JButton btnbuscar_informes_infoinventarios;
    private javax.swing.JButton btnbuscar_maestros_catventas;
    private javax.swing.JButton btnbuscar_maestros_packs;
    private javax.swing.JButton btnbuscar_ventas_confirmacion;
    private javax.swing.JButton btnbuscarinfo_informes_infoclientes;
    private javax.swing.JButton btnbuscarinfo_informes_infodevycambios;
    private javax.swing.JButton btnbuscarrango_informes_infoventas;
    private javax.swing.JButton btncancelar_clte_ventas_venta;
    private javax.swing.JButton btncancelar_compras_revision;
    private javax.swing.JButton btncancelar_compras_solicitudes;
    private javax.swing.JButton btncancelar_maestros_articulos;
    private javax.swing.JButton btncancelar_maestros_bancos;
    private javax.swing.JButton btncancelar_maestros_catarticulos;
    private javax.swing.JButton btncancelar_maestros_catventas;
    private javax.swing.JButton btncancelar_maestros_clientes;
    private javax.swing.JButton btncancelar_maestros_comunas;
    private javax.swing.JButton btncancelar_maestros_packs;
    private javax.swing.JButton btncancelar_maestros_proveedores;
    private javax.swing.JButton btncancelar_maestros_rrss;
    private javax.swing.JButton btncancelar_ventas_confirmacion;
    private javax.swing.JButton btncancelardestinatario_ventas_venta;
    private javax.swing.JButton btncancelardetal_compras_registro;
    private javax.swing.JButton btncancelarfac_compras_registro;
    private javax.swing.JButton btncompra_maestros_proveedores;
    private javax.swing.JButton btnconfirmar_ventas_confirmacion;
    private javax.swing.JButton btncrearpack_maestros_packs;
    private javax.swing.JButton btndesactivar_maestros_packs;
    private javax.swing.JButton btndescarga_ventas_listadestino;
    private javax.swing.JButton btndescargar_informes_infodevycambios;
    private javax.swing.JButton btndescargar_informes_infoinventarios;
    private javax.swing.JButton btndescargar_informes_infoventas;
    private javax.swing.JButton btndescargas_informes_infoclientes;
    private javax.swing.JButton btneditar_compras_registro;
    private javax.swing.JButton btneditar_compras_solicitudes;
    private javax.swing.JButton btneditar_maestros_articulos;
    private javax.swing.JButton btneditar_maestros_bancos;
    private javax.swing.JButton btneditar_maestros_catarticulos;
    private javax.swing.JButton btneditar_maestros_catventas;
    private javax.swing.JButton btneditar_maestros_clientes;
    private javax.swing.JButton btneditar_maestros_comunas;
    private javax.swing.JButton btneditar_maestros_packs;
    private javax.swing.JButton btneditar_maestros_proveedores;
    private javax.swing.JButton btneditar_maestros_rrss;
    private javax.swing.JButton btneliminar_compras_registro;
    private javax.swing.JButton btneliminar_maestros_usuarios;
    private javax.swing.JButton btngeneraroc_compras_solicitudes;
    private javax.swing.JButton btnguardar_compras_registro;
    private javax.swing.JButton btnguardar_compras_solicitudes;
    private javax.swing.JButton btnguardar_maestros_articulos;
    private javax.swing.JButton btnguardar_maestros_bancos;
    private javax.swing.JButton btnguardar_maestros_catarticulos;
    private javax.swing.JButton btnguardar_maestros_catventas;
    private javax.swing.JButton btnguardar_maestros_clientes;
    private javax.swing.JButton btnguardar_maestros_comunas;
    private javax.swing.JButton btnguardar_maestros_proveedores;
    private javax.swing.JButton btnguardar_maestros_rrss;
    private javax.swing.JButton btnguardar_ventas_adespacho;
    private javax.swing.JButton btnguardardestinatario_ventas_venta;
    private javax.swing.JButton btningresararticulo_maestros_packs;
    private javax.swing.JButton btnmas_compras_solicitudes;
    private javax.swing.JButton btnmas_maestros_packs;
    private javax.swing.JButton btnmenos_compras_solicitudes;
    private javax.swing.JButton btnmenos_maestros_packs;
    private javax.swing.JButton btnventas_maestros_clientes;
    private javax.swing.JButton btnver_compras_revision;
    private javax.swing.JPanel categoria_articulos;
    private javax.swing.JPanel categoria_ventas;
    private javax.swing.JTable categoriaarticulos;
    private javax.swing.JTable categorias_ventas_registradas;
    private javax.swing.JComboBox<String> cbactdes_maestros_proveedores;
    private javax.swing.JComboBox<String> cbbanco_maestros_bancos;
    private javax.swing.JComboBox<String> cbcategoria_maestros_catarticulos;
    private javax.swing.JComboBox<String> cbcategoria_maestros_catventas;
    private javax.swing.JComboBox<String> cbcomuna_maestros_comunas;
    private javax.swing.JComboBox<String> cbonestados_ventas_confirmacion;
    private javax.swing.JComboBox<String> cboxEstados_maestros_packs;
    private javax.swing.JComboBox<String> cboxEstados_ventas_venta;
    private javax.swing.JComboBox<String> cboxRRSS_ventas_venta;
    private javax.swing.JComboBox<String> cboxarticulo_compras_registro;
    private javax.swing.JComboBox<String> cboxatcdes_maestros_clientes;
    private javax.swing.JComboBox<String> cboxbanco_ventas_confirmacion;
    private javax.swing.JComboBox<String> cboxcategoria_maestros_articulos1;
    private javax.swing.JComboBox<String> cboxcomunas_ventas_venta;
    private javax.swing.JComboBox<String> cboxestado_ventas_adespacho;
    private javax.swing.JComboBox<String> cboxpacks_ventas_venta;
    private javax.swing.JComboBox<String> cboxproveedor_maestros_articulos;
    private javax.swing.JComboBox<String> cboxrazonsocial_compras_registro;
    private javax.swing.JComboBox<String> cbrrss_maestros_rrss;
    private javax.swing.JPanel clientes;
    private javax.swing.JPanel compras;
    private javax.swing.JPanel comunas;
    private javax.swing.JTable comunasregistradas;
    private javax.swing.JPanel confirmacion;
    private javax.swing.JPanel confirmacion_despacho;
    private com.toedter.calendar.JDateChooser datepedido_compras_solicitudes;
    private com.toedter.calendar.JDateChooser desde_dev_y_cambios;
    private com.toedter.calendar.JDateChooser desde_informe_cliente;
    private javax.swing.JTable detalle_dev_cambios;
    private javax.swing.JTable detalle_factura_compras_registro;
    private javax.swing.JTable detalle_factura_compras_revision;
    private javax.swing.JTable detalle_inventario;
    private javax.swing.JTable detalle_pedidos_realizados;
    private javax.swing.JTable detalleventarealizada_infoventas;
    private javax.swing.JTable devoluciones_y_cambios;
    private javax.swing.JTable facturas_compras_inventariadas;
    private com.toedter.calendar.JDateChooser hasta_dev_y_cambios;
    private com.toedter.calendar.JDateChooser hasta_informe_cliente;
    private javax.swing.JPanel informe_clientes;
    private javax.swing.JPanel informe_dev_y_cambios;
    private javax.swing.JPanel informe_inventarios;
    private javax.swing.JPanel informe_ventas;
    private javax.swing.JPanel informes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDfentrega_ventas_venta;
    private com.toedter.calendar.JDateChooser jDfventa_ventas_venta;
    private com.toedter.calendar.JDateChooser jDvencimiento_maestros_articulos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    public static javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    public javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable listaCompras_solicitudes;
    private javax.swing.JPanel lista_destino;
    private javax.swing.JTable listaarticulos;
    private javax.swing.JTable listaarticulos_maestros_packs;
    private javax.swing.JTable listadeclientes_maestros;
    private javax.swing.JTable listadestino_despacho;
    private javax.swing.JTable listapedido_compras_solicitudes;
    private javax.swing.JPanel maestros;
    private javax.swing.JPanel packs;
    private javax.swing.JTabbedPane panel_compras;
    private javax.swing.JTabbedPane panel_informes;
    private javax.swing.JTabbedPane panel_maestros;
    private javax.swing.JTabbedPane panel_ventas;
    private javax.swing.JPanel proveedores;
    private javax.swing.JPanel proveedores1;
    private javax.swing.JTable proveedores_compra;
    private javax.swing.JTable proveedores_maestros;
    private javax.swing.JTable redessociales;
    private javax.swing.JPanel registro_compras;
    private javax.swing.JPanel registro_compras1;
    private javax.swing.JPanel revision_facturas;
    private javax.swing.JPanel solicitudes_pedido;
    private javax.swing.JPanel solicitudes_pedido1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTable tabla_packs;
    private javax.swing.JTabbedPane tablas;
    private javax.swing.JTextField txtBuscarClientes;
    private com.toedter.calendar.JDateChooser txtInformeDesde;
    private com.toedter.calendar.JDateChooser txtInformeHasta;
    private javax.swing.JTextField txtPrecio_compras_solicitudes;
    private javax.swing.JTextField txt_ncliente_maestros_clientes;
    private javax.swing.JTextField txtbanco_ventas_confirmacion;
    private javax.swing.JTextField txtbuscarut_informes_infoventas;
    private javax.swing.JTextField txtbusqueda_maestros_articulos;
    private javax.swing.JTextField txtbusqueda_maestros_bancos;
    private javax.swing.JTextField txtbusqueda_maestros_catarticulos;
    private javax.swing.JTextField txtbusqueda_maestros_catventas;
    private javax.swing.JTextField txtbusqueda_maestros_comunas;
    private javax.swing.JTextField txtbusqueda_maestros_packs;
    private javax.swing.JTextField txtbusqueda_maestros_proveedores;
    private javax.swing.JTextField txtbusqueda_maestros_rrss;
    private javax.swing.JTextField txtbusqueda_maestros_usuarios;
    private javax.swing.JTextField txtbusqueda_ventas_confirmacion;
    private javax.swing.JTextField txtbusqueda_ventas_llistadestino;
    private javax.swing.JTextField txtcantidad_compras_registro;
    private javax.swing.JTextField txtcantidad_compras_solicitudes;
    private javax.swing.JTextField txtcategoria_maestros_articulos1;
    private javax.swing.JTextField txtcategoria_maestros_catarticulos;
    private javax.swing.JTextField txtcategoria_maestros_catventas;
    private javax.swing.JTextField txtcel_maestros_clientes;
    private javax.swing.JTextField txtcodigo_compras_registro;
    private javax.swing.JTextField txtcontraseña_maestros_usuarios;
    private javax.swing.JTextField txtdestinatario_ventas_venta;
    private javax.swing.JTextField txtdireccion_maestros_proveedores;
    private javax.swing.JTextField txtdireccion_ventas_venta;
    private javax.swing.JTextField txtemail_maestros_proveedores;
    private javax.swing.JTextField txtemail_ventas_venta;
    private javax.swing.JTextField txtemaill_maestros_clientes;
    private javax.swing.JTextField txtenvio_ventas_venta;
    private javax.swing.JTextField txtestado_ventas_adespacho;
    private javax.swing.JTextField txtestados_ventas_confirmacion;
    private com.toedter.calendar.JDateChooser txtfnac_maestros_clientes;
    private javax.swing.JTextField txtfono_maestros_clientes;
    private javax.swing.JTextField txtfono_maestros_proveedores;
    private javax.swing.JTextField txtfono_ventas_venta;
    private com.toedter.calendar.JDateChooser txtfpago_ventas_confirmacion;
    private javax.swing.JFormattedTextField txtfrecepcion_compras_registro;
    private javax.swing.JTextField txtfrecepcion_compras_revision;
    private javax.swing.JTextField txthoraf_ventas_venta;
    private javax.swing.JTextField txthorai_ventas_venta;
    private javax.swing.JTextField txtmarcas_maestros_articulos;
    private javax.swing.JTextField txtnarticulo_maestros_articulo;
    private javax.swing.JTextField txtnbanco_maestros_bancos;
    private javax.swing.JTextField txtncliente_ventas_venta;
    private javax.swing.JTextField txtnclte_ventas_confirmacion;
    private javax.swing.JTextField txtncomuna_maestros_comunas;
    private javax.swing.JTextField txtncontacto_maestros_proveedores;
    private javax.swing.JTextField txtnfactura_compras_registro;
    private javax.swing.JTextField txtnfactura_compras_revision;
    private javax.swing.JTextField txtnombre_maestros_packs;
    private javax.swing.JTextField txtnpedido_compras_solicitudes;
    private javax.swing.JTextField txtnpedido_ventas_confirmacion;
    private javax.swing.JTextField txtnpedido_ventas_venta;
    private javax.swing.JTextField txtnrrss_maestros_rrss;
    private javax.swing.JTextField txtprecio_maestros_packs;
    private javax.swing.JTextField txtrazon_maestros_proveedores;
    private javax.swing.JTextField txtrut_compras_registro;
    private javax.swing.JTextField txtrut_compras_revision;
    private javax.swing.JTextField txtrut_informes_infoclientes;
    private javax.swing.JTextField txtrut_informes_infodevycambios;
    private javax.swing.JTextField txtrut_maestros_clientes;
    private javax.swing.JTextField txtrut_maestros_proveedores;
    private javax.swing.JTextField txtrut_ventas_confirmacion;
    private javax.swing.JTextField txtrut_ventas_venta;
    private javax.swing.JTextField txtsaludo_ventas_venta;
    private javax.swing.JTextField txtsubtotal_ventas_venta;
    private javax.swing.JTextField txttotal_ventas_venta;
    private javax.swing.JTextField txttransaccion_ventas_confirmacion;
    private javax.swing.JTextField txtunidades_compras_solicitudes;
    private javax.swing.JTextField txtunidades_maestros_articulos;
    private javax.swing.JTextField txtunidades_maestros_packs;
    private javax.swing.JTextField txtusuario_maestros_usuarios;
    private javax.swing.JTextField txtvalor_compras_registro;
    private javax.swing.JFormattedTextField txtvencimiento_compras_registro;
    private javax.swing.JPanel usuarios;
    private javax.swing.JPanel venta;
    private javax.swing.JPanel ventas;
    private javax.swing.JTable ventas_pendientes_pago;
    private javax.swing.JTable ventas_realizada_infoclientes;
    // End of variables declaration//GEN-END:variables
}
