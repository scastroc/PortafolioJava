
package Vista;

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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

/**
 *
 * @author Sebastian
 */
public class HomeAdmin extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public HomeAdmin() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        OcultarPaneles();
        LlenarComboComunas();
        LlenarComboTrabajadores();
        LlenarComboPerfil();
        pnl_principal.setVisible(true);
        
        
    }
    
    public static java.sql.Date ConvertirFecha(java.util.Date javaDate){
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
    
    public void OcultarPaneles(){
        
        pnl_principal.setVisible(false);
        pnl_trabajador.setVisible(false);
        pnl_agregarTrabajador.setVisible(false);
        pnl_eliminarTrabajador.setVisible(false);
        pnl_listarTrabajador.setVisible(false);
        pnl_modifTrabajador.setVisible(false);
        pnl_usuario.setVisible(false);
        pnl_agregarUsuario.setVisible(false);
        pnl_modificarUsuario.setVisible(false);
        pnl_listarUsuario.setVisible(false);
        pnl_aprobarUsuario.setVisible(false);
        pnl_eliminarUsuario.setVisible(false);
        pnl_pedido.setVisible(false);
        pnl_pedidoAgregar.setVisible(false);
        pnl_pedidoModificar.setVisible(false);
        pnl_pedidoEstado.setVisible(false);
        pnl_pedidoListar.setVisible(false);
        pnl_pedidoEliminar.setVisible(false);      
                   
                
    }
    
    public void LlenarComboComunas(){
        
        this.cmb_comuna.removeAllItems();
        cmb_comuna.addItem("Seleccione");   
     
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboComunas(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            while (rs.next()) {
                this.cmb_comuna.addItem(rs.getString(1));
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void LlenarComboTrabajadores(){
        
        this.cmb_elegirTrab.removeAllItems();
        cmb_elegirTrab.addItem("Seleccione Trabajador");
        this.cmb_elegirTrab2.removeAllItems();
        cmb_elegirTrab2.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboTrabajador(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            while (rs.next()) {
                this.cmb_elegirTrab.addItem(rs.getString(1));
                this.cmb_elegirTrab2.addItem(rs.getString(1));
                
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
    }
    
    public void LlenarComboPerfil(){
        
        this.cmb_perfilUsuario.removeAllItems();
        cmb_perfilUsuario.addItem("Seleccione");
        
        try {
            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);

            CallableStatement cst = conn.prepareCall("{call SP_comboPerfil(?)}");

            cst.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cst.execute();

            ResultSet rs = (ResultSet) cst.getObject(1);
            
            
            while (rs.next()) {
                //this.cmb_perfilUsuario.addItem(rs.getString(1));
                this.cmb_perfilUsuario.addItem(rs.getString(2));   
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    private static DefaultTableModel buildTableModel(ResultSet res)
            throws SQLException {

        ResultSetMetaData metaData = res.getMetaData();

        // Nombres de columnas
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Datos de la tabla
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (res.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(res.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
    
    public void CerrarConexion(){
        
        try {            
            Conexion conexion = new Conexion();
            DatosConexion cadena = new DatosConexion();
            Connection conn = (Connection) conexion.getConnection(cadena);
            
            conn.close();
            System.out.println("Conexion cerrada");
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNavegacion = new javax.swing.JPanel();
        LogoYuyitos = new org.edisoncor.gui.panel.PanelImage();
        btn_irPnlTrabajadores = new javax.swing.JButton();
        btn_IrPnlUsuarios = new javax.swing.JButton();
        btn_irPnlPedidos = new javax.swing.JButton();
        btn_irPnlProductos = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        pnl_principal = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        pnl_trabajador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_agregarTrabajador = new javax.swing.JButton();
        btn_modificarTrabajador = new javax.swing.JButton();
        btn_eliminarTrabajador = new javax.swing.JButton();
        btn_listarTrabajador = new javax.swing.JButton();
        pnl_agregarTrabajador = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_rut = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_dv = new javax.swing.JTextField();
        txt_nombres = new javax.swing.JTextField();
        txt_apePaterno = new javax.swing.JTextField();
        txt_apeMaterno = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_fono = new javax.swing.JTextField();
        txt_calle = new javax.swing.JTextField();
        txt_dirNumero = new javax.swing.JTextField();
        txt_deptoNum = new javax.swing.JTextField();
        txt_dirVilla = new javax.swing.JTextField();
        cmb_comuna = new javax.swing.JComboBox<>();
        dateFechaContratacion = new datechooser.beans.DateChooserCombo();
        btn_nuevoTrabajador = new javax.swing.JButton();
        pnl_listarTrabajador = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_listarTrab = new javax.swing.JTable();
        btn_listarTrab = new javax.swing.JButton();
        pnl_eliminarTrabajador = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmb_elegirTrab = new javax.swing.JComboBox<>();
        btn_eliminarTrab = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_eliminarTrab = new javax.swing.JTable();
        pnl_modifTrabajador = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnl_usuario = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_agregarUsuario = new javax.swing.JButton();
        btn_modificarUsuario = new javax.swing.JButton();
        btn_eliminarUsuario = new javax.swing.JButton();
        btn_listarUsuario = new javax.swing.JButton();
        btn_aprobarUsuario = new javax.swing.JButton();
        pnl_agregarUsuario = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_nomUsuario = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_contraUsuario = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cmb_perfilUsuario = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        btn_crearUsuario = new javax.swing.JButton();
        cmb_elegirTrab2 = new javax.swing.JComboBox<>();
        pnl_modificarUsuario = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        pnl_listarUsuario = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_listarUsuarios = new javax.swing.JTable();
        btn_usuListar = new javax.swing.JButton();
        pnl_aprobarUsuario = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        pnl_eliminarUsuario = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        pnl_pedido = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btn_agregarPedido = new javax.swing.JButton();
        btn_modificarPedido = new javax.swing.JButton();
        btn_eliminarPedido = new javax.swing.JButton();
        btn_listarPedido = new javax.swing.JButton();
        btn_estadoPedido = new javax.swing.JButton();
        pnl_pedidoAgregar = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        pnl_pedidoModificar = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        pnl_pedidoEstado = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        pnl_pedidoListar = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        pnl_pedidoEliminar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1100, 800));

        jPanelNavegacion.setBackground(new java.awt.Color(204, 204, 204));
        jPanelNavegacion.setPreferredSize(new java.awt.Dimension(270, 790));

        LogoYuyitos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/YUYITOS3.png"))); // NOI18N

        javax.swing.GroupLayout LogoYuyitosLayout = new javax.swing.GroupLayout(LogoYuyitos);
        LogoYuyitos.setLayout(LogoYuyitosLayout);
        LogoYuyitosLayout.setHorizontalGroup(
            LogoYuyitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        LogoYuyitosLayout.setVerticalGroup(
            LogoYuyitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        btn_irPnlTrabajadores.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-icono.png"))); // NOI18N
        btn_irPnlTrabajadores.setText("Trabajadores");
        btn_irPnlTrabajadores.setAutoscrolls(true);
        btn_irPnlTrabajadores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_irPnlTrabajadores.setDefaultCapable(false);
        btn_irPnlTrabajadores.setFocusPainted(false);
        btn_irPnlTrabajadores.setIconTextGap(9);
        btn_irPnlTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlTrabajadoresActionPerformed(evt);
            }
        });

        btn_IrPnlUsuarios.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_IrPnlUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/usuario-icono.png"))); // NOI18N
        btn_IrPnlUsuarios.setText("Usuarios");
        btn_IrPnlUsuarios.setIconTextGap(8);
        btn_IrPnlUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IrPnlUsuariosActionPerformed(evt);
            }
        });

        btn_irPnlPedidos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-icono.png"))); // NOI18N
        btn_irPnlPedidos.setText("Pedidos");
        btn_irPnlPedidos.setIconTextGap(8);
        btn_irPnlPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlPedidosActionPerformed(evt);
            }
        });

        btn_irPnlProductos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_irPnlProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/productos-icono.png"))); // NOI18N
        btn_irPnlProductos.setText("Productos");
        btn_irPnlProductos.setIconTextGap(9);
        btn_irPnlProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irPnlProductosActionPerformed(evt);
            }
        });

        btn_cerrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/salir-icono1.png"))); // NOI18N
        btn_cerrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_cerrar.setBorderPainted(false);
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setDefaultCapable(false);
        btn_cerrar.setFocusPainted(false);
        btn_cerrar.setPreferredSize(new java.awt.Dimension(96, 96));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNavegacionLayout = new javax.swing.GroupLayout(jPanelNavegacion);
        jPanelNavegacion.setLayout(jPanelNavegacionLayout);
        jPanelNavegacionLayout.setHorizontalGroup(
            jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                .addGroup(jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LogoYuyitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlTrabajadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_IrPnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_irPnlProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanelNavegacionLayout.setVerticalGroup(
            jPanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavegacionLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(LogoYuyitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btn_irPnlTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_IrPnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_irPnlPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_irPnlProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_principal.setPreferredSize(new java.awt.Dimension(827, 600));

        lbl_titulo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("Panel Principal");

        javax.swing.GroupLayout pnl_principalLayout = new javax.swing.GroupLayout(pnl_principal);
        pnl_principal.setLayout(pnl_principalLayout);
        pnl_principalLayout.setHorizontalGroup(
            pnl_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_principalLayout.createSequentialGroup()
                .addComponent(lbl_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_principalLayout.setVerticalGroup(
            pnl_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_principalLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lbl_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(677, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajadores-chico.png"))); // NOI18N
        jLabel1.setText("Trabajadores");

        btn_agregarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_agregarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        btn_agregarTrabajador.setText("Agregar");
        btn_agregarTrabajador.setIconTextGap(8);
        btn_agregarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_agregarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarTrabajadorActionPerformed(evt);
            }
        });

        btn_modificarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_modificarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        btn_modificarTrabajador.setText("Modificar");
        btn_modificarTrabajador.setIconTextGap(8);
        btn_modificarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_modificarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarTrabajadorActionPerformed(evt);
            }
        });

        btn_eliminarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_eliminarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        btn_eliminarTrabajador.setText("Eliminar");
        btn_eliminarTrabajador.setIconTextGap(8);
        btn_eliminarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_eliminarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarTrabajadorActionPerformed(evt);
            }
        });

        btn_listarTrabajador.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_listarTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        btn_listarTrabajador.setText("Listar");
        btn_listarTrabajador.setIconTextGap(8);
        btn_listarTrabajador.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_listarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarTrabajadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_trabajadorLayout = new javax.swing.GroupLayout(pnl_trabajador);
        pnl_trabajador.setLayout(pnl_trabajadorLayout);
        pnl_trabajadorLayout.setHorizontalGroup(
            pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                        .addComponent(btn_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                        .addComponent(btn_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(btn_modificarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_trabajadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        pnl_trabajadorLayout.setVerticalGroup(
            pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_trabajadorLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(pnl_trabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        jLabel2.setText("Agregar Trabajador");
        jLabel2.setIconTextGap(6);

        jLabel3.setText("RUT :  ");

        jLabel4.setText("Nombres :  ");

        jLabel5.setText("Apellido Paterno :  ");

        jLabel6.setText("Apellido Materno :  ");

        jLabel7.setText("Email :  ");

        jLabel8.setText("Telefono :  ");

        jLabel9.setText("Calle :  ");

        jLabel10.setText("Numero :  ");

        jLabel11.setText("Departamento:  ");

        jLabel12.setText("Villa/Poblacion:  ");

        jLabel13.setText("Comuna :  ");

        jLabel14.setText("Fecha de Contratacion: ");

        jLabel15.setText("-");

        txt_dv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dvActionPerformed(evt);
            }
        });

        txt_nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombresActionPerformed(evt);
            }
        });

        txt_apePaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apePaternoActionPerformed(evt);
            }
        });

        cmb_comuna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cmb_comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_comunaActionPerformed(evt);
            }
        });

        btn_nuevoTrabajador.setText("Agregar");
        btn_nuevoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoTrabajadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_agregarTrabajadorLayout = new javax.swing.GroupLayout(pnl_agregarTrabajador);
        pnl_agregarTrabajador.setLayout(pnl_agregarTrabajadorLayout);
        pnl_agregarTrabajadorLayout.setHorizontalGroup(
            pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel10)
                        .addGap(91, 91, 91)
                        .addComponent(txt_dirNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel11)
                        .addGap(58, 58, 58)
                        .addComponent(txt_deptoNum, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel12)
                        .addGap(56, 56, 56)
                        .addComponent(txt_dirVilla, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(152, 152, 152)
                                .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(41, 41, 41)))
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_apeMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                        .addComponent(txt_calle, javax.swing.GroupLayout.Alignment.TRAILING))))))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(txt_apePaterno))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(12, 12, 12)
                                .addComponent(dateFechaContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(cmb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addGap(27, 27, 27)))
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addComponent(btn_nuevoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226))))
        );
        pnl_agregarTrabajadorLayout.setVerticalGroup(
            pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txt_dv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5))
                    .addComponent(txt_apePaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))
                    .addComponent(txt_apeMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7))
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8))
                    .addComponent(txt_fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10))
                    .addComponent(txt_dirNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11))
                    .addComponent(txt_deptoNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel12))
                    .addComponent(txt_dirVilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnl_agregarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarTrabajadorLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addComponent(dateFechaContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btn_nuevoTrabajador)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-buscar.png"))); // NOI18N
        jLabel18.setText("Listar Trabajadores");
        jLabel18.setIconTextGap(8);

        tbl_listarTrab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUT", "DV", "Nombres", "Apellido Paterno", "Apellido Materno", "Email", "Telefono", "Fecha de Contrato"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_listarTrab);

        btn_listarTrab.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_listarTrab.setText("Listar");
        btn_listarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarTrabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_listarTrabajadorLayout = new javax.swing.GroupLayout(pnl_listarTrabajador);
        pnl_listarTrabajador.setLayout(pnl_listarTrabajadorLayout);
        pnl_listarTrabajadorLayout.setHorizontalGroup(
            pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarTrabajadorLayout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addGroup(pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarTrabajadorLayout.createSequentialGroup()
                        .addComponent(btn_listarTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(353, 353, 353))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        pnl_listarTrabajadorLayout.setVerticalGroup(
            pnl_listarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarTrabajadorLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel18)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_listarTrab)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        jLabel16.setText("Eliminar Trabajador");
        jLabel16.setIconTextGap(8);

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setText("Rut : ");

        cmb_elegirTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_elegirTrabActionPerformed(evt);
            }
        });

        btn_eliminarTrab.setText("Eliminar");
        btn_eliminarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarTrabActionPerformed(evt);
            }
        });

        tbl_eliminarTrab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbl_eliminarTrab);

        javax.swing.GroupLayout pnl_eliminarTrabajadorLayout = new javax.swing.GroupLayout(pnl_eliminarTrabajador);
        pnl_eliminarTrabajador.setLayout(pnl_eliminarTrabajadorLayout);
        pnl_eliminarTrabajadorLayout.setHorizontalGroup(
            pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarTrabajadorLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(btn_eliminarTrab)
                        .addGap(283, 283, 283))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eliminarTrabajadorLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_elegirTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))))
        );
        pnl_eliminarTrabajadorLayout.setVerticalGroup(
            pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarTrabajadorLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel16)
                .addGap(40, 40, 40)
                .addGroup(pnl_eliminarTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_elegirTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_eliminarTrab)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        jLabel19.setText("Modificar Trabajador");

        javax.swing.GroupLayout pnl_modifTrabajadorLayout = new javax.swing.GroupLayout(pnl_modifTrabajador);
        pnl_modifTrabajador.setLayout(pnl_modifTrabajadorLayout);
        pnl_modifTrabajadorLayout.setHorizontalGroup(
            pnl_modifTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modifTrabajadorLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel19)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        pnl_modifTrabajadorLayout.setVerticalGroup(
            pnl_modifTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modifTrabajadorLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addContainerGap(494, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/usuarios-iconos.png"))); // NOI18N
        jLabel20.setText("  Usuarios");

        btn_agregarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_agregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        btn_agregarUsuario.setText("Agregar");
        btn_agregarUsuario.setIconTextGap(8);
        btn_agregarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_agregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarUsuarioActionPerformed(evt);
            }
        });

        btn_modificarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_modificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        btn_modificarUsuario.setText("Modificar");
        btn_modificarUsuario.setIconTextGap(8);
        btn_modificarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_modificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarUsuarioActionPerformed(evt);
            }
        });

        btn_eliminarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_eliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        btn_eliminarUsuario.setText("Eliminar");
        btn_eliminarUsuario.setIconTextGap(8);
        btn_eliminarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_eliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarUsuarioActionPerformed(evt);
            }
        });

        btn_listarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_listarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        btn_listarUsuario.setText("Listar");
        btn_listarUsuario.setIconTextGap(8);
        btn_listarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_listarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarUsuarioActionPerformed(evt);
            }
        });

        btn_aprobarUsuario.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_aprobarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-aceptar.png"))); // NOI18N
        btn_aprobarUsuario.setText("Aprobar");
        btn_aprobarUsuario.setIconTextGap(8);
        btn_aprobarUsuario.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_aprobarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aprobarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_usuarioLayout = new javax.swing.GroupLayout(pnl_usuario);
        pnl_usuario.setLayout(pnl_usuarioLayout);
        pnl_usuarioLayout.setHorizontalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btn_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_usuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_usuarioLayout.setVerticalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_usuarioLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-agregar.png"))); // NOI18N
        jLabel21.setText("Agregar Usuario");

        jLabel32.setText("Nombre Usuario:  ");

        jLabel33.setText("Contraseña :  ");

        jLabel34.setText("Tipo de Usuario: ");

        jLabel35.setText("Rut Trabajador : ");

        btn_crearUsuario.setText("Aceptar");
        btn_crearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearUsuarioActionPerformed(evt);
            }
        });

        cmb_elegirTrab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_elegirTrab2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_agregarUsuarioLayout = new javax.swing.GroupLayout(pnl_agregarUsuario);
        pnl_agregarUsuario.setLayout(pnl_agregarUsuarioLayout);
        pnl_agregarUsuarioLayout.setHorizontalGroup(
            pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(63, 63, 63)
                        .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nomUsuario)
                            .addComponent(txt_contraUsuario)
                            .addComponent(cmb_perfilUsuario, 0, 160, Short.MAX_VALUE)
                            .addComponent(cmb_elegirTrab2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_crearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );
        pnl_agregarUsuarioLayout.setVerticalGroup(
            pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_agregarUsuarioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(txt_nomUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(txt_contraUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(cmb_perfilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnl_agregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(cmb_elegirTrab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btn_crearUsuario)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-modificar.png"))); // NOI18N
        jLabel22.setText("Modificar Usuario");

        javax.swing.GroupLayout pnl_modificarUsuarioLayout = new javax.swing.GroupLayout(pnl_modificarUsuario);
        pnl_modificarUsuario.setLayout(pnl_modificarUsuarioLayout);
        pnl_modificarUsuarioLayout.setHorizontalGroup(
            pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarUsuarioLayout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        pnl_modificarUsuarioLayout.setVerticalGroup(
            pnl_modificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modificarUsuarioLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel22)
                .addContainerGap(417, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-listar.png"))); // NOI18N
        jLabel23.setText("Listar Usuarios");

        tbl_listarUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_listarUsuarios);

        btn_usuListar.setText("Listar");
        btn_usuListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_listarUsuarioLayout = new javax.swing.GroupLayout(pnl_listarUsuario);
        pnl_listarUsuario.setLayout(pnl_listarUsuarioLayout);
        pnl_listarUsuarioLayout.setHorizontalGroup(
            pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                .addGroup(pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(btn_usuListar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pnl_listarUsuarioLayout.setVerticalGroup(
            pnl_listarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listarUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btn_usuListar)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-aceptar.png"))); // NOI18N
        jLabel24.setText("Aprobar Usuario");

        javax.swing.GroupLayout pnl_aprobarUsuarioLayout = new javax.swing.GroupLayout(pnl_aprobarUsuario);
        pnl_aprobarUsuario.setLayout(pnl_aprobarUsuarioLayout);
        pnl_aprobarUsuarioLayout.setHorizontalGroup(
            pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_aprobarUsuarioLayout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
        );
        pnl_aprobarUsuarioLayout.setVerticalGroup(
            pnl_aprobarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_aprobarUsuarioLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(478, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/trabajador-eliminar.png"))); // NOI18N
        jLabel25.setText("Eliminar Usuario");

        javax.swing.GroupLayout pnl_eliminarUsuarioLayout = new javax.swing.GroupLayout(pnl_eliminarUsuario);
        pnl_eliminarUsuario.setLayout(pnl_eliminarUsuarioLayout);
        pnl_eliminarUsuarioLayout.setHorizontalGroup(
            pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarUsuarioLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel25)
                .addContainerGap(308, Short.MAX_VALUE))
        );
        pnl_eliminarUsuarioLayout.setVerticalGroup(
            pnl_eliminarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eliminarUsuarioLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel25)
                .addContainerGap(497, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/note_edit_12872.png"))); // NOI18N
        jLabel26.setText("  Pedidos");

        btn_agregarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_agregarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-agregar.png"))); // NOI18N
        btn_agregarPedido.setText("Agregar");
        btn_agregarPedido.setIconTextGap(8);
        btn_agregarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_agregarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarPedidoActionPerformed(evt);
            }
        });

        btn_modificarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_modificarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-modificar.png"))); // NOI18N
        btn_modificarPedido.setText("Modificar");
        btn_modificarPedido.setIconTextGap(8);
        btn_modificarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_modificarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarPedidoActionPerformed(evt);
            }
        });

        btn_eliminarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_eliminarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-eliminar.png"))); // NOI18N
        btn_eliminarPedido.setText("Eliminar");
        btn_eliminarPedido.setIconTextGap(8);
        btn_eliminarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_eliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarPedidoActionPerformed(evt);
            }
        });

        btn_listarPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_listarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        btn_listarPedido.setText("Listar");
        btn_listarPedido.setIconTextGap(8);
        btn_listarPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_listarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarPedidoActionPerformed(evt);
            }
        });

        btn_estadoPedido.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        btn_estadoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        btn_estadoPedido.setText("Estado");
        btn_estadoPedido.setIconTextGap(8);
        btn_estadoPedido.setPreferredSize(new java.awt.Dimension(190, 90));
        btn_estadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_estadoPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_pedidoLayout = new javax.swing.GroupLayout(pnl_pedido);
        pnl_pedido.setLayout(pnl_pedidoLayout);
        pnl_pedidoLayout.setHorizontalGroup(
            pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btn_listarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btn_agregarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_modificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_estadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_pedidoLayout.setVerticalGroup(
            pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_estadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnl_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_listarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-agregar.png"))); // NOI18N
        jLabel27.setText("Solicitar Pedidos");

        javax.swing.GroupLayout pnl_pedidoAgregarLayout = new javax.swing.GroupLayout(pnl_pedidoAgregar);
        pnl_pedidoAgregar.setLayout(pnl_pedidoAgregarLayout);
        pnl_pedidoAgregarLayout.setHorizontalGroup(
            pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        pnl_pedidoAgregarLayout.setVerticalGroup(
            pnl_pedidoAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoAgregarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-modificar.png"))); // NOI18N
        jLabel28.setText("Modificar Pedido");

        javax.swing.GroupLayout pnl_pedidoModificarLayout = new javax.swing.GroupLayout(pnl_pedidoModificar);
        pnl_pedidoModificar.setLayout(pnl_pedidoModificarLayout);
        pnl_pedidoModificarLayout.setHorizontalGroup(
            pnl_pedidoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoModificarLayout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        pnl_pedidoModificarLayout.setVerticalGroup(
            pnl_pedidoModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoModificarLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel28)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-estados.png"))); // NOI18N
        jLabel29.setText("Estado Pedidos");

        javax.swing.GroupLayout pnl_pedidoEstadoLayout = new javax.swing.GroupLayout(pnl_pedidoEstado);
        pnl_pedidoEstado.setLayout(pnl_pedidoEstadoLayout);
        pnl_pedidoEstadoLayout.setHorizontalGroup(
            pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        pnl_pedidoEstadoLayout.setVerticalGroup(
            pnl_pedidoEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEstadoLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-listar.png"))); // NOI18N
        jLabel30.setText("Listar Pedidos");

        javax.swing.GroupLayout pnl_pedidoListarLayout = new javax.swing.GroupLayout(pnl_pedidoListar);
        pnl_pedidoListar.setLayout(pnl_pedidoListarLayout);
        pnl_pedidoListarLayout.setHorizontalGroup(
            pnl_pedidoListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoListarLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
        );
        pnl_pedidoListarLayout.setVerticalGroup(
            pnl_pedidoListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoListarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Iconos/pedidos-eliminar.png"))); // NOI18N
        jLabel31.setText("Eliminar Pedido");

        javax.swing.GroupLayout pnl_pedidoEliminarLayout = new javax.swing.GroupLayout(pnl_pedidoEliminar);
        pnl_pedidoEliminar.setLayout(pnl_pedidoEliminarLayout);
        pnl_pedidoEliminarLayout.setHorizontalGroup(
            pnl_pedidoEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pedidoEliminarLayout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        pnl_pedidoEliminarLayout.setVerticalGroup(
            pnl_pedidoEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pedidoEliminarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(332, 332, 332)
                    .addComponent(pnl_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(341, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(336, 336, 336)
                    .addComponent(pnl_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(377, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(359, Short.MAX_VALUE)
                    .addComponent(pnl_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(158, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(377, Short.MAX_VALUE)
                    .addComponent(pnl_modifTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(220, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(423, 423, 423)
                    .addComponent(pnl_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(270, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(410, Short.MAX_VALUE)
                    .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(242, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(430, Short.MAX_VALUE)
                    .addComponent(pnl_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(282, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(401, Short.MAX_VALUE)
                    .addComponent(pnl_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(237, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(332, Short.MAX_VALUE)
                    .addComponent(pnl_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(234, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(407, Short.MAX_VALUE)
                    .addComponent(pnl_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(336, Short.MAX_VALUE)
                    .addComponent(pnl_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(395, Short.MAX_VALUE)
                    .addComponent(pnl_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(234, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(359, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(163, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(423, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(265, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(393, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(215, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(413, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(263, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(373, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(276, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 80, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(pnl_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(281, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(pnl_agregarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(83, Short.MAX_VALUE)
                    .addComponent(pnl_listarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(154, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(79, Short.MAX_VALUE)
                    .addComponent(pnl_modifTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(166, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(pnl_eliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(225, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(78, Short.MAX_VALUE)
                    .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(284, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(72, Short.MAX_VALUE)
                    .addComponent(pnl_agregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(37, Short.MAX_VALUE)
                    .addComponent(pnl_modificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(274, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(26, Short.MAX_VALUE)
                    .addComponent(pnl_listarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(289, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(93, Short.MAX_VALUE)
                    .addComponent(pnl_aprobarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(93, Short.MAX_VALUE)
                    .addComponent(pnl_eliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(128, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(84, Short.MAX_VALUE)
                    .addComponent(pnl_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(282, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(66, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(300, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(103, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(116, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(94, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(200, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(119, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(110, Short.MAX_VALUE)
                    .addComponent(pnl_pedidoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_irPnlPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlPedidosActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedido.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlPedidosActionPerformed

    private void btn_irPnlProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_irPnlProductosActionPerformed

    private void btn_irPnlTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irPnlTrabajadoresActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_trabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_irPnlTrabajadoresActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:        
        this.dispose(); 
        Login login = new Login();
        login.setVisible(true);
        CerrarConexion();       
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_agregarTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_agregarTrabajadorActionPerformed

    private void btn_modificarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_modifTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_modificarTrabajadorActionPerformed

    private void btn_eliminarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_eliminarTrabajador.setVisible(true);
        
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_eliminarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_eliminarTrabajadorActionPerformed

    private void btn_listarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarTrabajadorActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_listarTrabajador.setVisible(true);
        
    }//GEN-LAST:event_btn_listarTrabajadorActionPerformed

    private void txt_nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombresActionPerformed

    private void txt_dvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dvActionPerformed

    private void cmb_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_comunaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmb_comunaActionPerformed

    private void btn_nuevoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoTrabajadorActionPerformed
        // TODO add your handling code here:
        java.sql.Date sqlDate = null;
        sqlDate = ConvertirFecha(dateFechaContratacion.getSelectedDate().getTime());
        
        CtrlTrabajador.AgregarTrabajador(txt_rut.getText(), txt_dv.getText(), txt_nombres.getText(), txt_apePaterno.getText(), 
                                        txt_apeMaterno.getText(), txt_email.getText(), txt_fono.getText(), sqlDate, txt_calle.getText(), 
                                        txt_dirNumero.getText(), txt_deptoNum.getText(), txt_dirVilla.getText(), cmb_comuna.getSelectedItem().toString());
        
        CerrarConexion();        
    }//GEN-LAST:event_btn_nuevoTrabajadorActionPerformed

    private void txt_apePaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apePaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apePaternoActionPerformed

    private void cmb_elegirTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_elegirTrabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_elegirTrabActionPerformed

    private void btn_eliminarTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarTrabActionPerformed
        // TODO add your handling code here:
        CtrlTrabajador.EliminarTrabajador(cmb_elegirTrab.getSelectedItem().toString());
        
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_eliminarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        LlenarComboTrabajadores();
    }//GEN-LAST:event_btn_eliminarTrabActionPerformed

    private void btn_listarTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarTrabActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlTrabajador.ListarTrab();
            
            tbl_listarTrab.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_listarTrabActionPerformed

    private void btn_agregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_agregarUsuario.setVisible(true);        
        
    }//GEN-LAST:event_btn_agregarUsuarioActionPerformed

    private void btn_modificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_modificarUsuario.setVisible(true);
        
    }//GEN-LAST:event_btn_modificarUsuarioActionPerformed

    private void btn_eliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_eliminarUsuario.setVisible(true);
        
    }//GEN-LAST:event_btn_eliminarUsuarioActionPerformed

    private void btn_listarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_listarUsuario.setVisible(true);
        
    }//GEN-LAST:event_btn_listarUsuarioActionPerformed

    private void btn_aprobarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aprobarUsuarioActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_aprobarUsuario.setVisible(true);
        
    }//GEN-LAST:event_btn_aprobarUsuarioActionPerformed

    private void btn_IrPnlUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IrPnlUsuariosActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_usuario.setVisible(true);
        
    }//GEN-LAST:event_btn_IrPnlUsuariosActionPerformed

    private void btn_agregarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoAgregar.setVisible(true);
        
    }//GEN-LAST:event_btn_agregarPedidoActionPerformed

    private void btn_modificarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoModificar.setVisible(true);
        
    }//GEN-LAST:event_btn_modificarPedidoActionPerformed

    private void btn_eliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoEliminar.setVisible(true);
    }//GEN-LAST:event_btn_eliminarPedidoActionPerformed

    private void btn_listarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoListar.setVisible(true);
        
    }//GEN-LAST:event_btn_listarPedidoActionPerformed

    private void btn_estadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_estadoPedidoActionPerformed
        // TODO add your handling code here:
        OcultarPaneles();
        pnl_pedidoEstado.setVisible(true);
        
    }//GEN-LAST:event_btn_estadoPedidoActionPerformed

    private void btn_crearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearUsuarioActionPerformed
        // TODO add your handling code here:
        int perfilID = 0;
        
        if ("Administrador".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 1;            
        }else{
            if ("Vendedor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 2;
        }else{
            if ("Proveedor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())) {
            perfilID = 3;
        }else{
            if ("Supervisor".equals(this.cmb_perfilUsuario.getSelectedItem().toString())){
            perfilID = 4;
        }
            }
           }
        }  
        
        CtrlUsuario.AgregarUsuario(txt_nomUsuario.getText(), txt_contraUsuario.getText(), 
                                   cmb_elegirTrab2.getSelectedItem().toString(), perfilID);
        txt_nomUsuario.setText("");
        txt_contraUsuario.setText("");
        
        LlenarComboPerfil();
        LlenarComboTrabajadores();
        CerrarConexion();      
    }//GEN-LAST:event_btn_crearUsuarioActionPerformed

    private void cmb_elegirTrab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_elegirTrab2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_elegirTrab2ActionPerformed

    private void btn_usuListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuListarActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet res = CtrlUsuario.ListarUsuario();
            
            tbl_listarUsuarios.setModel(buildTableModel(res));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_usuListarActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage LogoYuyitos;
    private javax.swing.JButton btn_IrPnlUsuarios;
    private javax.swing.JButton btn_agregarPedido;
    private javax.swing.JButton btn_agregarTrabajador;
    private javax.swing.JButton btn_agregarUsuario;
    private javax.swing.JButton btn_aprobarUsuario;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_crearUsuario;
    private javax.swing.JButton btn_eliminarPedido;
    private javax.swing.JButton btn_eliminarTrab;
    private javax.swing.JButton btn_eliminarTrabajador;
    private javax.swing.JButton btn_eliminarUsuario;
    private javax.swing.JButton btn_estadoPedido;
    private javax.swing.JButton btn_irPnlPedidos;
    private javax.swing.JButton btn_irPnlProductos;
    private javax.swing.JButton btn_irPnlTrabajadores;
    private javax.swing.JButton btn_listarPedido;
    private javax.swing.JButton btn_listarTrab;
    private javax.swing.JButton btn_listarTrabajador;
    private javax.swing.JButton btn_listarUsuario;
    private javax.swing.JButton btn_modificarPedido;
    private javax.swing.JButton btn_modificarTrabajador;
    private javax.swing.JButton btn_modificarUsuario;
    private javax.swing.JButton btn_nuevoTrabajador;
    private javax.swing.JButton btn_usuListar;
    private javax.swing.JComboBox<String> cmb_comuna;
    private javax.swing.JComboBox<String> cmb_elegirTrab;
    private javax.swing.JComboBox<String> cmb_elegirTrab2;
    private javax.swing.JComboBox<String> cmb_perfilUsuario;
    private datechooser.beans.DateChooserCombo dateFechaContratacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelNavegacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPanel pnl_agregarTrabajador;
    private javax.swing.JPanel pnl_agregarUsuario;
    private javax.swing.JPanel pnl_aprobarUsuario;
    private javax.swing.JPanel pnl_eliminarTrabajador;
    private javax.swing.JPanel pnl_eliminarUsuario;
    private javax.swing.JPanel pnl_listarTrabajador;
    private javax.swing.JPanel pnl_listarUsuario;
    private javax.swing.JPanel pnl_modifTrabajador;
    private javax.swing.JPanel pnl_modificarUsuario;
    private javax.swing.JPanel pnl_pedido;
    private javax.swing.JPanel pnl_pedidoAgregar;
    private javax.swing.JPanel pnl_pedidoEliminar;
    private javax.swing.JPanel pnl_pedidoEstado;
    private javax.swing.JPanel pnl_pedidoListar;
    private javax.swing.JPanel pnl_pedidoModificar;
    private javax.swing.JPanel pnl_principal;
    private javax.swing.JPanel pnl_trabajador;
    private javax.swing.JPanel pnl_usuario;
    private javax.swing.JTable tbl_eliminarTrab;
    private javax.swing.JTable tbl_listarTrab;
    private javax.swing.JTable tbl_listarUsuarios;
    private javax.swing.JTextField txt_apeMaterno;
    private javax.swing.JTextField txt_apePaterno;
    private javax.swing.JTextField txt_calle;
    private javax.swing.JTextField txt_contraUsuario;
    private javax.swing.JTextField txt_deptoNum;
    private javax.swing.JTextField txt_dirNumero;
    private javax.swing.JTextField txt_dirVilla;
    private javax.swing.JTextField txt_dv;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fono;
    private javax.swing.JTextField txt_nomUsuario;
    private javax.swing.JTextField txt_nombres;
    private javax.swing.JTextField txt_rut;
    // End of variables declaration//GEN-END:variables
}
