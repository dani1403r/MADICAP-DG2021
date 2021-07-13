/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgift;

import java.util.Date;
import ConexionSQL.ConexionUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class tablas extends javax.swing.JFrame {

    ConexionUsuarios cc=new ConexionUsuarios();
    Connection con=cc.conexion();
    
    
    
    public tablas() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        mostrarDatos();
        mostrarDatosClientes();
        mostrarDatosBanco();
        mostrarDatosComunas();
        mostrarDatosRrss();
        mostrarDatosCategoriaArt();
        
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
 public static ArrayList<String> llenar_combo(){
 ArrayList<String> lista = new ArrayList<String>();
 String q ="SELECT * FROM categoria_articulo";
     try {
        // resultado= cboxcategoria_maestros_articulos.executeQuery(q);
         
         
     } catch (Exception e) {
     }
 return lista;
 }
 public void insertarDatosArticulos(){
 
 try{
    
    String SQL ="insert into articulo (CAT_ID_CATEGORIA,ART_DESCRIPCION,ART_Unidades,ART_FECHA_VENCIMIENTO,ART_MARCAS) values(?,?,?,?,?)";
    
    PreparedStatement pst= con.prepareStatement(SQL);
   
    
   
    //pst.setString(1,cboxcategoria_maestros_articulos.getItemAt(CAT_DESCRIPCION));
    
    pst.setString(2,txtnarticulo_maestros_articulo.getText());
    pst.setString(3,txtunidades_maestros_articulos.getText());
   
    
     pst.setString(4,((JTextField)txtfnac_maestros_clientes.getDateEditor().getUiComponent()).getText());
    
    pst.setString(5,txtmarcas_maestros_articulos.getText());
    
   pst.execute();
   
    JOptionPane.showMessageDialog(null,"Registro Exitoso");
   
}catch(Exception e) {
      
    JOptionPane.showMessageDialog(null,"Error al mostrar datos"+e.getMessage());
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
        btnguardar_clte_ventas_venta = new javax.swing.JButton();
        btncancelar_clte_ventas_venta = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txtemail_ventas_venta = new javax.swing.JTextField();
        txtnpedido_ventas_venta = new javax.swing.JTextField();
        txtncliente_ventas_venta = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        txtfentrega_ventas_venta = new javax.swing.JTextField();
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
        txtcomunas_ventas_venta = new javax.swing.JTextField();
        txtsaludo_ventas_venta = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        txtpacks_ventas_venta = new javax.swing.JTextField();
        txthorai_ventas_venta = new javax.swing.JTextField();
        txthoraf_ventas_venta = new javax.swing.JTextField();
        btncancelardestinatario_ventas_venta = new javax.swing.JButton();
        btnguardardestinatario_ventas_venta = new javax.swing.JButton();
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
        txtfpago_ventas_confirmacion = new javax.swing.JTextField();
        txtnclte_ventas_confirmacion = new javax.swing.JTextField();
        txttransaccion_ventas_confirmacion = new javax.swing.JTextField();
        cboxbanco_ventas_confirmacion = new javax.swing.JComboBox<>();
        btncancelar_ventas_confirmacion = new javax.swing.JButton();
        btnconfirmar_ventas_confirmacion = new javax.swing.JButton();
        btnseleccionar_ventas_confirmacion = new javax.swing.JButton();
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
        btnbuscar_ventas_listadestino = new javax.swing.JButton();
        btndescarga_ventas_listadestino = new javax.swing.JButton();
        btnimprimir_ventas_listadestino = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        confirmacion_despacho = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        cboxestado_ventas_adespacho = new javax.swing.JComboBox<>();
        btnguardar_ventas_adespacho = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        detalle_dev_cambios = new javax.swing.JTable();
        btnseleccionar_ventas_adespacho = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        compras = new javax.swing.JPanel();
        panel_compras = new javax.swing.JTabbedPane();
        solicitudes_pedido = new javax.swing.JPanel();
        solicitudes_pedido1 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtnpedido_compras_solicitudes = new javax.swing.JTextField();
        txtfpedido_compras_solicitudes = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        listaarticulos_compras_solicitudes = new javax.swing.JList<>();
        jScrollPane23 = new javax.swing.JScrollPane();
        listaarticuloselegidos_compras_solicitudes = new javax.swing.JList<>();
        jLabel102 = new javax.swing.JLabel();
        txtunidades_compras_solicitudes = new javax.swing.JTextField();
        btncancelar_compras_solicitudes = new javax.swing.JButton();
        btnguardar_compras_solicitudes = new javax.swing.JButton();
        btnmas_compras_solicitudes = new javax.swing.JButton();
        btnmenos_compras_solicitudes = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        detalle_pedidos_realizados = new javax.swing.JTable();
        btneditar_compras_solicitudes = new javax.swing.JButton();
        btnver_compras_solicitudes = new javax.swing.JButton();
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
        jLabel15 = new javax.swing.JLabel();
        cboxrazonsocial_compras_revision = new javax.swing.JComboBox<>();
        btncancelar_compras_revision = new javax.swing.JButton();
        btnbuscar_compras_revision = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btneditar_compras_revision = new javax.swing.JButton();
        btnver_compras_revision = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        informes = new javax.swing.JPanel();
        panel_informes = new javax.swing.JTabbedPane();
        informe_ventas = new javax.swing.JPanel();
        txtbusqueda_informes_infoventas = new javax.swing.JFormattedTextField();
        btnbuscar_informes_infoventas = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        detalleventarealizada_infoventas = new javax.swing.JTable();
        jLabel93 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnbuscarrango_informes_infoventas = new javax.swing.JButton();
        txtdesde_informes_infoventas = new javax.swing.JFormattedTextField();
        jLabel91 = new javax.swing.JLabel();
        txtbuscarut_informes_infoventas = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txthasta_informes_infoventas = new javax.swing.JFormattedTextField();
        btndescargar_informes_infoventas = new javax.swing.JButton();
        jLabel134 = new javax.swing.JLabel();
        informe_inventarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalle_inventario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdesde_informes_infoinventarios = new javax.swing.JTextField();
        txthasta_informes_infoinventarios = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboxcategoria_informes_infoinventarios = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtvencimiento_informes_infoinventarios = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboxrut_informes_infoinventarios = new javax.swing.JComboBox<>();
        btnbuscar_informes_infoinventarios = new javax.swing.JButton();
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
        txtdesde_informes_infoclientes = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txthasta_informes_infoclientes = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txtrut_informes_infoclientes = new javax.swing.JTextField();
        btnbuscarinfo_informes_infoclientes = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        btnbuscar_informes_clientes = new javax.swing.JButton();
        txtbusqueda_informes_infoclientes = new javax.swing.JTextField();
        informe_dev_y_cambios = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        devoluciones_y_cambios = new javax.swing.JTable();
        btndescargar_informes_infodevycambios = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        txtdesde_informes_infodevycambios = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txthasta_informes_infodevycambios = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtrut_informes_infodevycambios = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        btnbuscarinfo_informes_infodevycambios = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        btnbuscar_informes_infodevycambios = new javax.swing.JButton();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        txtbusqueda_informes_infodevycambios = new javax.swing.JTextField();
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
        jScrollPane17 = new javax.swing.JScrollPane();
        proveedores_maestros = new javax.swing.JTable();
        btncompra_maestros_proveedores = new javax.swing.JButton();
        btneditar_maestros_proveedores = new javax.swing.JButton();
        btndesactivar_maestros_proveedores = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        btnbuscar_maestros_proveedores = new javax.swing.JButton();
        jLabel126 = new javax.swing.JLabel();
        txtbusqueda_maestros_proveedores = new javax.swing.JTextField();
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
        cboxcategoria_maestros_articulos = new javax.swing.JComboBox<>();
        cboxproveedor_maestros_articulos = new javax.swing.JComboBox<>();
        btncancelar_maestros_articulos = new javax.swing.JButton();
        btnguardar_maestros_articulos = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane18 = new javax.swing.JScrollPane();
        listaarticulos = new javax.swing.JTable();
        btneditar_maestros_articulos = new javax.swing.JButton();
        btndesactivar_maestros_articulos = new javax.swing.JButton();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        txtbusqueda_maestros_articulos = new javax.swing.JTextField();
        btnbuscar_maestros_articulos = new javax.swing.JButton();
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
        jLabel47 = new javax.swing.JLabel();
        btnguardar_maestros_catventas = new javax.swing.JButton();
        btncancelar_maestros_catventas = new javax.swing.JButton();
        txtcodigo_maestros_catventas = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        categorias_ventas_registradas = new javax.swing.JTable();
        btnbuscar_maestros_catventas = new javax.swing.JButton();
        btneditar_maestros_catventas = new javax.swing.JButton();
        btndesactivar_maestros_catventas = new javax.swing.JButton();
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

        btnguardar_clte_ventas_venta.setText("Guardar");

        btncancelar_clte_ventas_venta.setText("Cancelar");

        jLabel116.setText("Telefono");

        jLabel115.setText("Rut");

        jLabel112.setText("Numero Pedido");

        jLabel113.setText("Nombre Cliente");

        jLabel114.setText("Email");

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
                    .addComponent(txtemail_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtncliente_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel115)
                            .addComponent(jLabel116))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfono_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrut_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_clte_ventas_venta)
                        .addGap(64, 64, 64)
                        .addComponent(btnguardar_clte_ventas_venta)
                        .addGap(94, 94, 94))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel116)
                            .addComponent(txtfono_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(btncancelar_clte_ventas_venta)
                            .addComponent(btnguardar_clte_ventas_venta))))
                .addGap(42, 42, 42))
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
        jPanel22.add(txtenvio_ventas_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 70, -1));
        jPanel22.add(txttotal_ventas_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 70, -1));

        jLabel121.setText("Comuna");

        jLabel122.setText("Saludo");

        jLabel124.setText("Hora Inicio");

        jLabel125.setText("Hora Fin");

        btncancelardestinatario_ventas_venta.setText("Cancelar");

        btnguardardestinatario_ventas_venta.setText("Guardar");

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
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtfentrega_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdireccion_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtsaludo_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel121)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcomunas_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(213, 213, 213)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel123)
                            .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpacks_ventas_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txthorai_ventas_venta)
                            .addComponent(txthoraf_ventas_venta)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(btnguardardestinatario_ventas_venta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(btncancelardestinatario_ventas_venta)))
                .addGap(81, 81, 81)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
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
                            .addComponent(txtpacks_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfentrega_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel119)
                            .addComponent(jLabel124)
                            .addComponent(txthorai_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtdireccion_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel120)
                                    .addComponent(jLabel125))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcomunas_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel121))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsaludo_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel122)
                                    .addComponent(btnguardardestinatario_ventas_venta)
                                    .addComponent(btncancelardestinatario_ventas_venta)))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txthoraf_ventas_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_ventas.addTab("venta", venta);

        confirmacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        ventas_pendientes_pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                "Numero de Pedido", "Fecha de Pedido", "Nombre Cliente", "Numero de Telefono", "Monto", "Pack", "Seleccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        txttransaccion_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttransaccion_ventas_confirmacionActionPerformed(evt);
            }
        });

        cboxbanco_ventas_confirmacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btncancelar_ventas_confirmacion.setText("Cancelar");

        btnconfirmar_ventas_confirmacion.setText("Confirmar");
        btnconfirmar_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmar_ventas_confirmacionActionPerformed(evt);
            }
        });

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
                .addGap(64, 64, 64)
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
                    .addComponent(txtfpago_ventas_confirmacion)
                    .addComponent(txtnclte_ventas_confirmacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncancelar_ventas_confirmacion)
                .addGap(96, 96, 96)
                .addComponent(btnconfirmar_ventas_confirmacion)
                .addGap(60, 60, 60))
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
                            .addComponent(cboxbanco_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtfpago_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtnclte_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txttransaccion_ventas_confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar_ventas_confirmacion)
                            .addComponent(btnconfirmar_ventas_confirmacion))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnseleccionar_ventas_confirmacion.setText("Seleccionar");
        btnseleccionar_ventas_confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseleccionar_ventas_confirmacionActionPerformed(evt);
            }
        });

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
                .addContainerGap(662, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confirmacionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnseleccionar_ventas_confirmacion)
                .addGap(79, 79, 79))
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
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnseleccionar_ventas_confirmacion)
                .addGap(44, 44, 44))
        );

        panel_ventas.addTab("confirmacion", confirmacion);

        lista_destino.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listadestino_despacho.setModel(new javax.swing.table.DefaultTableModel(
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
                "Registro venta", "Pack", "Destinatario", "Fecha de entregal", "Comuna", "Direccion", "Hora de entrega"
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

        btnbuscar_ventas_listadestino.setText("Buscar");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(360, 360, 360)
                .addComponent(jLabel64)
                .addGap(94, 94, 94)
                .addComponent(btnbuscar_ventas_listadestino)
                .addGap(18, 18, 18)
                .addComponent(txtbusqueda_ventas_llistadestino, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(btnbuscar_ventas_listadestino)
                    .addComponent(txtbusqueda_ventas_llistadestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        btndescarga_ventas_listadestino.setText("Descargar");

        btnimprimir_ventas_listadestino.setText("Imprimir");
        btnimprimir_ventas_listadestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_ventas_listadestinoActionPerformed(evt);
            }
        });

        jLabel63.setText("Despacho");

        javax.swing.GroupLayout lista_destinoLayout = new javax.swing.GroupLayout(lista_destino);
        lista_destino.setLayout(lista_destinoLayout);
        lista_destinoLayout.setHorizontalGroup(
            lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lista_destinoLayout.createSequentialGroup()
                .addGroup(lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lista_destinoLayout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(btndescarga_ventas_listadestino)
                        .addGap(150, 150, 150)
                        .addComponent(btnimprimir_ventas_listadestino))
                    .addGroup(lista_destinoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel63)))
                .addContainerGap(390, Short.MAX_VALUE))
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lista_destinoLayout.setVerticalGroup(
            lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lista_destinoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(lista_destinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndescarga_ventas_listadestino)
                    .addComponent(btnimprimir_ventas_listadestino))
                .addGap(48, 48, 48))
        );

        panel_ventas.addTab("lista destino", lista_destino);

        confirmacion_despacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        jLabel59.setText("Actualización estado despacho");

        jLabel60.setText("Detalle de devoluciones y cambios");

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setText("Estado de entrega:");

        cboxestado_ventas_adespacho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entregado", "Anulado", "Rechazado", "No entregado" }));

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
                .addComponent(cboxestado_ventas_adespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
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
                    .addComponent(btnguardar_ventas_adespacho))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        detalle_dev_cambios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Número de pedido", "Pack", "Destinatario", "Fecha entrega", "Comuna", "Hora entrega", "Estado de entrega ", "Editar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        detalle_dev_cambios.setMaximumSize(new java.awt.Dimension(200, 64));
        jScrollPane13.setViewportView(detalle_dev_cambios);

        btnseleccionar_ventas_adespacho.setText("Seleccionar");

        javax.swing.GroupLayout confirmacion_despachoLayout = new javax.swing.GroupLayout(confirmacion_despacho);
        confirmacion_despacho.setLayout(confirmacion_despachoLayout);
        confirmacion_despachoLayout.setHorizontalGroup(
            confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                .addGroup(confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel59))
                    .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(491, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confirmacion_despachoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnseleccionar_ventas_adespacho)
                .addGap(283, 283, 283))
            .addComponent(jScrollPane13)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmacion_despachoLayout.setVerticalGroup(
            confirmacion_despachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacion_despachoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnseleccionar_ventas_adespacho)
                .addGap(22, 22, 22))
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

        jScrollPane22.setViewportView(listaarticulos_compras_solicitudes);

        jScrollPane23.setViewportView(listaarticuloselegidos_compras_solicitudes);

        jLabel102.setText("Unidades");

        btncancelar_compras_solicitudes.setText("Cancelar");

        btnguardar_compras_solicitudes.setText("Guardar");

        btnmas_compras_solicitudes.setText(">");

        btnmenos_compras_solicitudes.setText("<");

        javax.swing.GroupLayout solicitudes_pedido1Layout = new javax.swing.GroupLayout(solicitudes_pedido1);
        solicitudes_pedido1.setLayout(solicitudes_pedido1Layout);
        solicitudes_pedido1Layout.setHorizontalGroup(
            solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel100)
                        .addGap(50, 50, 50)
                        .addComponent(txtnpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solicitudes_pedido1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addGap(35, 35, 35)
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnmenos_compras_solicitudes, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(btnmas_compras_solicitudes, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(txtunidades_compras_solicitudes))
                        .addGap(99, 99, 99)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(btnguardar_compras_solicitudes)
                        .addGap(66, 66, 66)
                        .addComponent(btncancelar_compras_solicitudes)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(txtfpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(470, 470, 470))))
        );
        solicitudes_pedido1Layout.setVerticalGroup(
            solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfpedido_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel101)))
                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnmas_compras_solicitudes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel102)
                                    .addComponent(txtunidades_compras_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmenos_compras_solicitudes))
                            .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(solicitudes_pedido1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(solicitudes_pedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnguardar_compras_solicitudes)
                                .addComponent(btncancelar_compras_solicitudes)))
                        .addGap(26, 26, 26))))
        );

        detalle_pedidos_realizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero Pedido", "Fecha Pedido", "Cantidades Articulo", "Seleccion"
            }
        ));
        jScrollPane26.setViewportView(detalle_pedidos_realizados);

        btneditar_compras_solicitudes.setText("Editar");

        btnver_compras_solicitudes.setText("Ver");

        btngeneraroc_compras_solicitudes.setText("Generar O/C");

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
                .addGap(18, 18, 18)
                .addComponent(btnver_compras_solicitudes)
                .addGap(18, 18, 18)
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
                    .addComponent(btnver_compras_solicitudes)
                    .addComponent(btngeneraroc_compras_solicitudes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_compras.addTab("solicitudes pedido", solicitudes_pedido);

        registro_compras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 204), 3, true));

        registro_compras1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel103.setText("Numero Factura");

        jLabel104.setText("Rut");

        jLabel105.setText("Fecha Recepcion");

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
                    .addComponent(txtnfactura_compras_registro, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
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

        jLabel14.setText("Fecha de Recepcion");

        txtnfactura_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnfactura_compras_revisionActionPerformed(evt);
            }
        });

        jLabel15.setText("Proveedor Razon Social");

        cboxrazonsocial_compras_revision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btncancelar_compras_revision.setText("Cancelar");

        btnbuscar_compras_revision.setText("Buscar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnfactura_compras_revision)
                    .addComponent(txtrut_compras_revision)
                    .addComponent(txtfrecepcion_compras_revision, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(cboxrazonsocial_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btncancelar_compras_revision)
                        .addGap(148, 148, 148)
                        .addComponent(btnbuscar_compras_revision)))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnfactura_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(cboxrazonsocial_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtrut_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtfrecepcion_compras_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar_compras_revision)
                    .addComponent(btnbuscar_compras_revision))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel9.setText("Detalle de Factura");

        btneditar_compras_revision.setText("Editar");
        btneditar_compras_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_compras_revisionActionPerformed(evt);
            }
        });

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
                        .addComponent(btneditar_compras_revision)
                        .addGap(47, 47, 47)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(revision_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_compras_revision)
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

        btnbuscar_informes_infoventas.setText("Buscar");

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

        jLabel91.setText("Busqueda por Rut");

        jLabel89.setText("Desde");

        jLabel90.setText("Hasta");

        jLabel88.setText("Busqueda Rango Fecha Ventas");

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
                    .addComponent(txtdesde_informes_infoventas, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(txtbuscarut_informes_infoventas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(btnbuscarrango_informes_infoventas)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel90)
                        .addGap(162, 162, 162)
                        .addComponent(txthasta_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(353, 353, 353))))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel88)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdesde_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(jLabel90)
                    .addComponent(txthasta_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(txtbuscarut_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarrango_informes_infoventas))
                .addGap(19, 19, 19))
        );

        btndescargar_informes_infoventas.setText("Descargar");

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
                        .addGap(341, 341, 341)
                        .addComponent(btnbuscar_informes_infoventas)
                        .addGap(18, 18, 18)
                        .addComponent(txtbusqueda_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
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
                .addGroup(informe_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(txtbusqueda_informes_infoventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar_informes_infoventas))
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

        cboxcategoria_informes_infoinventarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Fecha vencimiento");

        txtvencimiento_informes_infoinventarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvencimiento_informes_infoinventariosActionPerformed(evt);
            }
        });

        jLabel6.setText("Rut proveedor");

        cboxrut_informes_infoinventarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnbuscar_informes_infoinventarios.setText("Buscar");

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
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtdesde_informes_infoinventarios, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(txthasta_informes_infoinventarios))
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboxcategoria_informes_infoinventarios, 0, 118, Short.MAX_VALUE)
                    .addComponent(txtvencimiento_informes_infoinventarios)
                    .addComponent(cboxrut_informes_infoinventarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                .addComponent(btnbuscar_informes_infoinventarios)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboxcategoria_informes_infoinventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbuscar_informes_infoinventarios))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtdesde_informes_infoinventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txthasta_informes_infoinventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtvencimiento_informes_infoinventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(cboxrut_informes_infoinventarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel7.setText("Detalle de Inventario");

        jLabel8.setText("Informe Inventario");

        btndescargar_informes_infoinventarios.setText("Descargar");

        javax.swing.GroupLayout informe_inventariosLayout = new javax.swing.GroupLayout(informe_inventarios);
        informe_inventarios.setLayout(informe_inventariosLayout);
        informe_inventariosLayout.setHorizontalGroup(
            informe_inventariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        ventas_realizada_infoclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo Pack", "Pack", "Fecha Registro", "Cliente", "Estado", "Comuna"
            }
        ));
        jScrollPane21.setViewportView(ventas_realizada_infoclientes);

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel94.setText("Busqueda Fecha Rango Ventas");

        jLabel95.setText("Desde");

        jLabel96.setText("Hasta");

        jLabel97.setText("Busqueda por Rut ");

        btnbuscarinfo_informes_infoclientes.setText("Buscar");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel94)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(txtdesde_informes_infoclientes, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtrut_informes_infoclientes)))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel96)
                        .addGap(70, 70, 70)
                        .addComponent(txthasta_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbuscarinfo_informes_infoclientes)
                        .addGap(155, 155, 155))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel94)
                .addGap(1, 1, 1)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdesde_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96)
                    .addComponent(txthasta_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(txtrut_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarinfo_informes_infoclientes))
                .addGap(14, 14, 14))
        );

        jLabel92.setText("Busqueda deVentas");

        btnbuscar_informes_clientes.setText("Buscar");

        javax.swing.GroupLayout informe_clientesLayout = new javax.swing.GroupLayout(informe_clientes);
        informe_clientes.setLayout(informe_clientesLayout);
        informe_clientesLayout.setHorizontalGroup(
            informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informe_clientesLayout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addComponent(btnbuscar_informes_clientes)
                .addGap(18, 18, 18)
                .addComponent(txtbusqueda_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
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
                .addGroup(informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(informe_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnbuscar_informes_clientes)
                        .addComponent(txtbusqueda_informes_infoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
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

        txtdesde_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdesde_informes_infodevycambiosActionPerformed(evt);
            }
        });

        jLabel54.setText("Hasta:");

        txthasta_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthasta_informes_infodevycambiosActionPerformed(evt);
            }
        });

        jLabel55.setText("Busqueda por rango de fecha:");

        txtrut_informes_infodevycambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrut_informes_infodevycambiosActionPerformed(evt);
            }
        });

        jLabel56.setText("Busqueda por rut:");

        btnbuscarinfo_informes_infodevycambios.setText("Buscar");

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
                    .addComponent(txtdesde_informes_infodevycambios, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtrut_informes_infodevycambios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(39, 39, 39)
                        .addComponent(txthasta_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(333, 333, 333))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(btnbuscarinfo_informes_infodevycambios)
                        .addGap(150, 150, 150))))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(jLabel55)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel55))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txtdesde_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addComponent(txthasta_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(txtrut_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbuscarinfo_informes_infodevycambios))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel98.setText("Busqueda de Ventas");

        btnbuscar_informes_infodevycambios.setText("Buscar");

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
                        .addGap(124, 124, 124)
                        .addComponent(btnbuscar_informes_infodevycambios)
                        .addGroup(informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel136)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel135))
                            .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(txtbusqueda_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(159, Short.MAX_VALUE))
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
                        .addGap(119, 119, 119)
                        .addGroup(informe_dev_y_cambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(btnbuscar_informes_infodevycambios)
                            .addComponent(txtbusqueda_informes_infodevycambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(informe_dev_y_cambiosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addContainerGap(390, Short.MAX_VALUE))
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
                .addContainerGap(312, Short.MAX_VALUE))
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

        btnguardar_maestros_proveedores.setText("Guardar");
        btnguardar_maestros_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_proveedoresActionPerformed(evt);
            }
        });

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
                        .addComponent(jLabel79)
                        .addGap(35, 35, 35)
                        .addComponent(txtemail_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(proveedores1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnguardar_maestros_proveedores)))
                .addContainerGap(95, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(proveedores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(txtncontacto_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78)
                    .addComponent(txtfono_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jScrollPane17.setViewportView(proveedores_maestros);

        btncompra_maestros_proveedores.setText("Compra");

        btneditar_maestros_proveedores.setText("Editar");

        btndesactivar_maestros_proveedores.setText("Desactivar");

        jLabel80.setText("Proveedores");

        btnbuscar_maestros_proveedores.setText("Buscar");

        jLabel126.setText("Proveedor");

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
                        .addGap(179, 179, 179)
                        .addComponent(btnbuscar_maestros_proveedores)
                        .addGap(18, 18, 18)
                        .addComponent(txtbusqueda_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(311, 311, 311))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proveedoresLayout.createSequentialGroup()
                        .addComponent(btncompra_maestros_proveedores)
                        .addGap(74, 74, 74)
                        .addComponent(btneditar_maestros_proveedores)
                        .addGap(58, 58, 58)
                        .addComponent(btndesactivar_maestros_proveedores)
                        .addGap(148, 148, 148))))
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
                    .addComponent(btnbuscar_maestros_proveedores)
                    .addComponent(jLabel80)
                    .addComponent(txtbusqueda_maestros_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndesactivar_maestros_proveedores)
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

        jLabel87.setText("Proveedor");

        cboxcategoria_maestros_articulos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboxproveedor_maestros_articulos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btncancelar_maestros_articulos.setText("Cancelar");

        btnguardar_maestros_articulos.setText("Guardar");

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
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxcategoria_maestros_articulos, 0, 134, Short.MAX_VALUE)
                            .addComponent(cboxproveedor_maestros_articulos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulos1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar_maestros_articulos)
                        .addGap(72, 72, 72)
                        .addComponent(btnguardar_maestros_articulos)
                        .addGap(137, 137, 137))))
        );
        articulos1Layout.setVerticalGroup(
            articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(articulos1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81)
                        .addComponent(txtnarticulo_maestros_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxcategoria_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(articulos1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel86)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(articulos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(cboxproveedor_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(btnguardar_maestros_articulos))))
                .addGap(43, 43, 43))
        );

        listaarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Articulo", "Tipo Articulo", "Unidades", "F. Vencimiento", "Marca", "Proveedor", "Accion"
            }
        ));
        jScrollPane18.setViewportView(listaarticulos);

        btneditar_maestros_articulos.setText("Editar");

        btndesactivar_maestros_articulos.setText("Desactivar");

        jLabel138.setText("Articulos");

        jLabel139.setText("Articulos");

        btnbuscar_maestros_articulos.setText("Buscar");

        javax.swing.GroupLayout articulosLayout = new javax.swing.GroupLayout(articulos);
        articulos.setLayout(articulosLayout);
        articulosLayout.setHorizontalGroup(
            articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, articulosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btneditar_maestros_articulos)
                .addGap(85, 85, 85)
                .addComponent(btndesactivar_maestros_articulos)
                .addGap(128, 128, 128))
            .addComponent(articulos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane18)
            .addGroup(articulosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel138)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(articulosLayout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(jLabel139)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                .addComponent(btnbuscar_maestros_articulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(12, 12, 12)
                .addGroup(articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(txtbusqueda_maestros_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar_maestros_articulos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_maestros_articulos)
                    .addComponent(btndesactivar_maestros_articulos))
                .addGap(29, 29, 29))
        );

        panel_maestros.addTab("articulos", articulos);

        tabla_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Pack", "Nombre Pack", "Cantidad", "Seleccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabla_packs);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        articuloselegidos_maestros_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nuevo pack"
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

        btnmenos_maestros_packs.setText("<");
        btnmenos_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenos_maestros_packsActionPerformed(evt);
            }
        });

        btncancelar_maestros_packs.setText("Cancelar");

        btncrearpack_maestros_packs.setText("Crear Pack");

        listaarticulos_maestros_packs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Articulos"
            }
        ));
        jScrollPane20.setViewportView(listaarticulos_maestros_packs);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(57, 57, 57)
                        .addComponent(txtnombre_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtprecio_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnmenos_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtunidades_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnmas_maestros_packs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncrearpack_maestros_packs)
                        .addGap(78, 78, 78)
                        .addComponent(btncancelar_maestros_packs)
                        .addGap(63, 63, 63))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txtnombre_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncancelar_maestros_packs)
                            .addComponent(btncrearpack_maestros_packs)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnmas_maestros_packs)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtunidades_maestros_packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmenos_maestros_packs))
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(88, 88, 88))
        );

        jLabel22.setText("Packs");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Tabla Packs");

        txtbusqueda_maestros_packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusqueda_maestros_packsActionPerformed(evt);
            }
        });

        btneditar_maestros_packs.setText("Editar");

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
                .addContainerGap(1103, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(packsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, packsLayout.createSequentialGroup()
                        .addComponent(btneditar_maestros_packs)
                        .addGap(60, 60, 60)
                        .addComponent(btndesactivar_maestros_packs)
                        .addGap(88, 88, 88))
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
                    .addComponent(btneditar_maestros_packs)
                    .addComponent(btndesactivar_maestros_packs))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE))
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
            .addGap(0, 21, Short.MAX_VALUE)
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
                        .addGap(431, 583, Short.MAX_VALUE))
                    .addGroup(categoria_articulosLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1167, Short.MAX_VALUE)
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
                .addContainerGap(387, Short.MAX_VALUE))
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
                .addContainerGap(326, Short.MAX_VALUE))
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
                        .addContainerGap(738, Short.MAX_VALUE)
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

        jLabel47.setText("Codigo Categoria Venta:");

        btnguardar_maestros_catventas.setText("Guardar");
        btnguardar_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_maestros_catventasActionPerformed(evt);
            }
        });

        btncancelar_maestros_catventas.setText("Cancelar");

        txtcodigo_maestros_catventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigo_maestros_catventasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel46)
                .addGap(86, 86, 86)
                .addComponent(txtcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185)
                .addComponent(jLabel47)
                .addGap(104, 104, 104)
                .addComponent(txtcodigo_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jLabel47)
                    .addComponent(txtcodigo_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcategoria_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(39, 39, 39)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar_maestros_catventas)
                    .addComponent(btncancelar_maestros_catventas))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        categorias_ventas_registradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo Categoria Venta", "Nombre Banco", "Accion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        categorias_ventas_registradas.setMaximumSize(new java.awt.Dimension(200, 64));
        jScrollPane10.setViewportView(categorias_ventas_registradas);

        btnbuscar_maestros_catventas.setText("Buscar");

        btneditar_maestros_catventas.setText("Editar");

        btndesactivar_maestros_catventas.setText("Desactivar");

        jLabel42.setText("Categorias Registradas");

        javax.swing.GroupLayout categoria_ventasLayout = new javax.swing.GroupLayout(categoria_ventas);
        categoria_ventas.setLayout(categoria_ventasLayout);
        categoria_ventasLayout.setHorizontalGroup(
            categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane10)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoria_ventasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(146, 146, 146)
                        .addComponent(btndesactivar_maestros_catventas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))))
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
                .addGroup(categoria_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditar_maestros_catventas)
                    .addComponent(btndesactivar_maestros_catventas))
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
                .addGap(0, 605, Short.MAX_VALUE))
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
            .addComponent(tablas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtvencimiento_informes_infoinventariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvencimiento_informes_infoinventariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvencimiento_informes_infoinventariosActionPerformed

    private void btneditar_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_compras_revisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_compras_revisionActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_ventas_adespachoActionPerformed

    private void txtnbanco_maestros_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnbanco_maestros_bancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnbanco_maestros_bancosActionPerformed

    private void txtcategoria_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcategoria_maestros_catventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcategoria_maestros_catventasActionPerformed

    private void btnguardar_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_catventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_catventasActionPerformed

    private void txtcodigo_maestros_catventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigo_maestros_catventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigo_maestros_catventasActionPerformed

    private void txtusuario_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuario_maestros_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuario_maestros_usuariosActionPerformed

    private void txtcontraseña_maestros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseña_maestros_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseña_maestros_usuariosActionPerformed

    private void btnseleccionar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseleccionar_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnseleccionar_ventas_confirmacionActionPerformed

    private void txtbusqueda_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusqueda_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_ventas_confirmacionActionPerformed

    private void btnbuscar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar_ventas_confirmacionActionPerformed

    private void btnconfirmar_ventas_confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmar_ventas_confirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconfirmar_ventas_confirmacionActionPerformed

    private void btnver_compras_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnver_compras_revisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnver_compras_revisionActionPerformed

    private void txtunidades_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunidades_maestros_packsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtunidades_maestros_packsActionPerformed

    private void btnmenos_maestros_packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenos_maestros_packsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmenos_maestros_packsActionPerformed

    private void btneditar_maestros_comunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_maestros_comunasActionPerformed
actualizarDatosComunas();
mostrarDatosComunas();
limpiarCajasComunas();

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_comunasActionPerformed

    private void btnimprimir_ventas_listadestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_ventas_listadestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnimprimir_ventas_listadestinoActionPerformed

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

    private void txthasta_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthasta_informes_infodevycambiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthasta_informes_infodevycambiosActionPerformed

    private void txtdesde_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesde_informes_infodevycambiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdesde_informes_infodevycambiosActionPerformed

    private void btndescargar_informes_infodevycambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndescargar_informes_infodevycambiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btndescargar_informes_infodevycambiosActionPerformed

    private void btnguardar_maestros_comunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_comunasActionPerformed
insertarDatosComunas();
mostrarDatosComunas();
limpiarCajasComunas();

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

        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar_maestros_catarticulosActionPerformed

    private void txtbusqueda_maestros_catarticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqueda_maestros_catarticulosKeyReleased
        filtrarDatosCategoriaArt(txtbusqueda_maestros_catarticulos.getText());
        

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqueda_maestros_catarticulosKeyReleased

    private void btnguardar_maestros_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_maestros_proveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar_maestros_proveedoresActionPerformed

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
    private javax.swing.JPanel articulos;
    private javax.swing.JPanel articulos1;
    private javax.swing.JTable articuloselegidos_maestros_packs;
    private javax.swing.JPanel bancos;
    private javax.swing.JTable bancosregistrados;
    private javax.swing.JButton btnCancelar_maestros_usuarios;
    private javax.swing.JButton btnGuardar_maestros_usuarios;
    private javax.swing.JButton btnactualizar_maestros_usuarios;
    private javax.swing.JButton btnagregar_compras_registro;
    private javax.swing.JButton btnbuscar_compras_revision;
    private javax.swing.JButton btnbuscar_informes_clientes;
    private javax.swing.JButton btnbuscar_informes_infodevycambios;
    private javax.swing.JButton btnbuscar_informes_infoinventarios;
    private javax.swing.JButton btnbuscar_informes_infoventas;
    private javax.swing.JButton btnbuscar_maestros_articulos;
    private javax.swing.JButton btnbuscar_maestros_catventas;
    private javax.swing.JButton btnbuscar_maestros_packs;
    private javax.swing.JButton btnbuscar_maestros_proveedores;
    private javax.swing.JButton btnbuscar_ventas_confirmacion;
    private javax.swing.JButton btnbuscar_ventas_listadestino;
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
    private javax.swing.JButton btndesactivar_maestros_articulos;
    private javax.swing.JButton btndesactivar_maestros_catventas;
    private javax.swing.JButton btndesactivar_maestros_packs;
    private javax.swing.JButton btndesactivar_maestros_proveedores;
    private javax.swing.JButton btndescarga_ventas_listadestino;
    private javax.swing.JButton btndescargar_informes_infodevycambios;
    private javax.swing.JButton btndescargar_informes_infoinventarios;
    private javax.swing.JButton btndescargar_informes_infoventas;
    private javax.swing.JButton btndescargas_informes_infoclientes;
    private javax.swing.JButton btneditar_compras_registro;
    private javax.swing.JButton btneditar_compras_revision;
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
    private javax.swing.JButton btnguardar_clte_ventas_venta;
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
    private javax.swing.JButton btnimprimir_ventas_listadestino;
    private javax.swing.JButton btnmas_compras_solicitudes;
    private javax.swing.JButton btnmas_maestros_packs;
    private javax.swing.JButton btnmenos_compras_solicitudes;
    private javax.swing.JButton btnmenos_maestros_packs;
    private javax.swing.JButton btnseleccionar_ventas_adespacho;
    private javax.swing.JButton btnseleccionar_ventas_confirmacion;
    private javax.swing.JButton btnventas_maestros_clientes;
    private javax.swing.JButton btnver_compras_revision;
    private javax.swing.JButton btnver_compras_solicitudes;
    private javax.swing.JPanel categoria_articulos;
    private javax.swing.JPanel categoria_ventas;
    private javax.swing.JTable categoriaarticulos;
    private javax.swing.JTable categorias_ventas_registradas;
    private javax.swing.JComboBox<String> cbbanco_maestros_bancos;
    private javax.swing.JComboBox<String> cbcategoria_maestros_catarticulos;
    private javax.swing.JComboBox<String> cbcomuna_maestros_comunas;
    private javax.swing.JComboBox<String> cboxarticulo_compras_registro;
    private javax.swing.JComboBox<String> cboxatcdes_maestros_clientes;
    private javax.swing.JComboBox<String> cboxbanco_ventas_confirmacion;
    private javax.swing.JComboBox<String> cboxcategoria_informes_infoinventarios;
    private javax.swing.JComboBox<String> cboxcategoria_maestros_articulos;
    private javax.swing.JComboBox<String> cboxestado_ventas_adespacho;
    private javax.swing.JComboBox<String> cboxproveedor_maestros_articulos;
    private javax.swing.JComboBox<String> cboxrazonsocial_compras_registro;
    private javax.swing.JComboBox<String> cboxrazonsocial_compras_revision;
    private javax.swing.JComboBox<String> cboxrut_informes_infoinventarios;
    private javax.swing.JComboBox<String> cbrrss_maestros_rrss;
    private javax.swing.JPanel clientes;
    private javax.swing.JPanel compras;
    private javax.swing.JPanel comunas;
    private javax.swing.JTable comunasregistradas;
    private javax.swing.JPanel confirmacion;
    private javax.swing.JPanel confirmacion_despacho;
    private javax.swing.JTable detalle_dev_cambios;
    private javax.swing.JTable detalle_factura_compras_registro;
    private javax.swing.JTable detalle_factura_compras_revision;
    private javax.swing.JTable detalle_inventario;
    private javax.swing.JTable detalle_pedidos_realizados;
    private javax.swing.JTable detalleventarealizada_infoventas;
    private javax.swing.JTable devoluciones_y_cambios;
    private javax.swing.JTable facturas_compras_inventariadas;
    private javax.swing.JPanel informe_clientes;
    private javax.swing.JPanel informe_dev_y_cambios;
    private javax.swing.JPanel informe_inventarios;
    private javax.swing.JPanel informe_ventas;
    private javax.swing.JPanel informes;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel112;
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
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel lista_destino;
    private javax.swing.JTable listaarticulos;
    private javax.swing.JList<String> listaarticulos_compras_solicitudes;
    private javax.swing.JTable listaarticulos_maestros_packs;
    private javax.swing.JList<String> listaarticuloselegidos_compras_solicitudes;
    private javax.swing.JTable listadeclientes_maestros;
    private javax.swing.JTable listadestino_despacho;
    private javax.swing.JPanel maestros;
    private javax.swing.JPanel packs;
    private javax.swing.JTabbedPane panel_compras;
    private javax.swing.JTabbedPane panel_informes;
    private javax.swing.JTabbedPane panel_maestros;
    private javax.swing.JTabbedPane panel_ventas;
    private javax.swing.JPanel proveedores;
    private javax.swing.JPanel proveedores1;
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
    private javax.swing.JTextField txt_ncliente_maestros_clientes;
    private javax.swing.JTextField txtbuscarut_informes_infoventas;
    private javax.swing.JTextField txtbusqueda_informes_infoclientes;
    private javax.swing.JTextField txtbusqueda_informes_infodevycambios;
    private javax.swing.JFormattedTextField txtbusqueda_informes_infoventas;
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
    private javax.swing.JTextField txtcategoria_maestros_catarticulos;
    private javax.swing.JTextField txtcategoria_maestros_catventas;
    private javax.swing.JTextField txtcel_maestros_clientes;
    private javax.swing.JTextField txtcodigo_compras_registro;
    private javax.swing.JTextField txtcodigo_maestros_catventas;
    private javax.swing.JTextField txtcomunas_ventas_venta;
    private javax.swing.JTextField txtcontraseña_maestros_usuarios;
    private javax.swing.JTextField txtdesde_informes_infoclientes;
    private javax.swing.JTextField txtdesde_informes_infodevycambios;
    private javax.swing.JTextField txtdesde_informes_infoinventarios;
    private javax.swing.JFormattedTextField txtdesde_informes_infoventas;
    private javax.swing.JTextField txtdestinatario_ventas_venta;
    private javax.swing.JTextField txtdireccion_maestros_proveedores;
    private javax.swing.JTextField txtdireccion_ventas_venta;
    private javax.swing.JTextField txtemail_maestros_proveedores;
    private javax.swing.JTextField txtemail_ventas_venta;
    private javax.swing.JTextField txtemaill_maestros_clientes;
    private javax.swing.JTextField txtenvio_ventas_venta;
    private javax.swing.JTextField txtfentrega_ventas_venta;
    private com.toedter.calendar.JDateChooser txtfnac_maestros_clientes;
    private javax.swing.JTextField txtfono_maestros_clientes;
    private javax.swing.JTextField txtfono_maestros_proveedores;
    private javax.swing.JTextField txtfono_ventas_venta;
    private javax.swing.JTextField txtfpago_ventas_confirmacion;
    private javax.swing.JTextField txtfpedido_compras_solicitudes;
    private javax.swing.JFormattedTextField txtfrecepcion_compras_registro;
    private javax.swing.JTextField txtfrecepcion_compras_revision;
    private javax.swing.JTextField txthasta_informes_infoclientes;
    private javax.swing.JTextField txthasta_informes_infodevycambios;
    private javax.swing.JTextField txthasta_informes_infoinventarios;
    private javax.swing.JFormattedTextField txthasta_informes_infoventas;
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
    private javax.swing.JTextField txtpacks_ventas_venta;
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
    private javax.swing.JTextField txtvencimiento_informes_infoinventarios;
    private javax.swing.JPanel usuarios;
    private javax.swing.JPanel venta;
    private javax.swing.JPanel ventas;
    private javax.swing.JTable ventas_pendientes_pago;
    private javax.swing.JTable ventas_realizada_infoclientes;
    // End of variables declaration//GEN-END:variables
}
