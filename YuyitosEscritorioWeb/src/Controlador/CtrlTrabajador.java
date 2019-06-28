/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionDB.Conexion;
import ConexionDB.DatosConexion;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import javax.swing.JComboBox;
import Controlador.CtrlTrabajador;
import Controlador.CtrlUsuario;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import javax.print.DocFlavor;
import javax.swing.JDialog;
//import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class CtrlTrabajador {
        
    public static ResultSet AgregarTrabajador(String rut, String dvRut, String nombres, String apePaterno,
                                              String apeMaterno, String email, String fono, Date fechaContrato,
                                              String calle, String dirNum, String deptoNum, String villa, String comuna){
        ResultSet res = null;
        
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_InsertarTrabajador(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, rut);
            cst.setString(2, dvRut);
            cst.setString(3, nombres);
            cst.setString(4, apePaterno);
            cst.setString(5, apeMaterno);
            cst.setString(6, email);
            cst.setString(7, fono);
            cst.setDate(8, fechaContrato);
            cst.setString(9, calle);
            cst.setString(10, dirNum);
            cst.setString(11, deptoNum);
            cst.setString(12, villa);
            cst.setString(13, comuna);
            cst.execute();
            
                        
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se agregó Nuevo Trabajador Exitosamente.");
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return res;
    }
    
    public static ResultSet EliminarTrabajador(String rut){
        ResultSet res = null;
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_eliminarTrabajador(?,?)}");
            
            cst.setString(1, rut);
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.execute();
            
                        
            final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Se elimino Trabajador Exitosamente.");            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    
    }
    
    public static ResultSet ListarTrab(){
        ResultSet res = null;
        try {
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            CallableStatement cst = conn.prepareCall("{call SP_tablaTrabajador(?)}");
            
            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();
            res = (ResultSet)cst.getObject(1);
            
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        return res;
        
    }
    
}
    
    