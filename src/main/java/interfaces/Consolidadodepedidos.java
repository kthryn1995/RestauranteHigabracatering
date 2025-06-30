package interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Kthryn
 */
public class Consolidadodepedidos extends javax.swing.JFrame {
 conexion co= new conexion();
 Connection conet;
    /**
     * Creates new form detallespedido
     */
    public Consolidadodepedidos() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false); 
        detallesdelpedido();
    }



  
    
public void detallesdelpedido() {
    String sql = "SELECT u.idUsuario, u.nombreCompleto, r.Dia_de_reservacion, r.Opcion, " +
                 "r.Lugar_entrega, r.TipoServicio, u.Ceco, u.Area, u.Contratista " +
                 "FROM reservacion r " +
                 "JOIN usuario u ON r.cedula = u.idUsuario";

    try (Connection conet = co.getConnection();
         PreparedStatement pst = conet.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        DefaultTableModel modelo = (DefaultTableModel) jTablepedido.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        while (rs.next()) {
            Object[] fila = new Object[9];
            fila[0] = rs.getString("idUsuario");
            fila[1] = rs.getString("nombreCompleto");
            fila[2] = rs.getString("Dia_de_reservacion");
            fila[3] = rs.getString("Opcion");
            fila[4] = rs.getString("Lugar_entrega");
            fila[5] = rs.getString("TipoServicio");
            fila[6] = rs.getString("Ceco");
            fila[7] = rs.getString("Area");
            fila[8] = rs.getString("Contratista");

            modelo.addRow(fila);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
    }
}


private void eliminarPedidosSemana() {
    int confirmacion = JOptionPane.showConfirmDialog(null,
        "¿Estás segura de eliminar todos los pedidos de la semana?\nSe hará un respaldo antes de eliminarlos.",
        "Confirmar eliminación masiva",
        JOptionPane.YES_NO_OPTION);

    if (confirmacion != JOptionPane.YES_OPTION) {
        return; // Usuario canceló
    }

    String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    try {
        conexion co = new conexion();
        Connection con = co.getConnection(); 

        // 🔁 1. RESPALDAR EN papeleriadereciclaje
        String respaldoSQL = "INSERT INTO papeleriadereciclaje " +
            "(identificacion, nombreyapellidos, fechadeEntrega, opcion, lugarEntrega, servicio, ceco, area, contratista) " +
            "SELECT u.idUsuario, u.nombreCompleto, r.Dia_de_reservacion, r.Opcion, " +
            "r.Lugar_entrega, r.TipoServicio, u.Ceco, u.Area, u.Contratista " +
            "FROM reservacion r " +
            "JOIN usuario u ON r.cedula = u.idUsuario " +
            "WHERE r.Dia_de_reservacion IN (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement respaldo = con.prepareStatement(respaldoSQL);
        for (int i = 0; i < diasSemana.length; i++) {
            respaldo.setString(i + 1, diasSemana[i]);
        }
        respaldo.executeUpdate();
        respaldo.close();

        // 🗑️ 2. ELIMINAR DE reservacion
        String eliminarSQL = "DELETE FROM reservacion WHERE Dia_de_reservacion IN (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement eliminar = con.prepareStatement(eliminarSQL);
        for (int i = 0; i < diasSemana.length; i++) {
            eliminar.setString(i + 1, diasSemana[i]);
        }

        int filasEliminadas = eliminar.executeUpdate();
        eliminar.close();
        con.close();

        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(null, filasEliminadas + " pedidos eliminados correctamente. Se guardó una copia en la papelería.");
            DefaultTableModel model = (DefaultTableModel) jTablepedido.getModel();
            model.setRowCount(0); // Limpia la tabla visual
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron pedidos de la semana para eliminar.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar pedidos: " + e.getMessage());
    }
}


    
    public void filtrarPedidosPorCriterios() {
    DefaultTableModel modelo = (DefaultTableModel) jTablepedido.getModel();
    modelo.setRowCount(0); // Limpia la tabla

   String sql = "SELECT r.*, u.NombreCompleto, u.Ceco, u.Area, u.Contratista " +
             "FROM reservacion r " +
             "JOIN usuario u ON r.cedula = u.idUsuario " +
             "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    String dia = jComboboxDia.getSelectedItem().toString();
    if (!dia.equals("Todos")) {
        sql += " AND Dia_de_reservacion = ?";
        parametros.add(dia);
    }

    String cedula = txtcedula.getText().trim();
    if (!cedula.isEmpty()) {
        sql += " AND cedula = ?";
        parametros.add(cedula);
    }

    String servicio = jComboboxServicio.getSelectedItem().toString();
    if (!servicio.equals("Todos")) {
        sql += " AND TipoServicio = ?";
        parametros.add(servicio);
    }

    try {
        // 🚨 NUEVO: crear nueva instancia para asegurar que la conexión esté activa
        Connection con = new conexion().getConnection();

        if (con == null || con.isClosed()) {
            JOptionPane.showMessageDialog(null, "La conexión con la base de datos está cerrada.");
            return;
        }

        PreparedStatement ps = con.prepareStatement(sql);

        for (int i = 0; i < parametros.size(); i++) {
            ps.setObject(i + 1, parametros.get(i));
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[]{
                rs.getString("cedula"),
                rs.getString("NombreCompleto"),
                rs.getString("Dia_de_reservacion"),
                rs.getInt("Opcion"),
                rs.getString("Lugar_entrega"),
                rs.getString("TipoServicio"),
                rs.getString("Ceco"),
                rs.getString("Area"),
                rs.getString("Contratista")
            };
            modelo.addRow(fila);
        }

        rs.close();
        ps.close();
        con.close(); // <- Cierra la conexión al final

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al filtrar: " + e.getMessage());
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jPanel1 = new javax.swing.JPanel();
        btnexportar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        jComboboxDia = new javax.swing.JComboBox<>();
        txtcedula = new javax.swing.JTextField();
        jComboboxServicio = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jlabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablepedido = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        btnexportar.setBackground(new java.awt.Color(255, 153, 51));
        btnexportar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnexportar.setText("EXPORTAR");
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atras.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR SEMANA");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnactualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/procesamiento-de-datos.png"))); // NOI18N
        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        jComboboxDia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboboxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", " " }));

        jComboboxServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboboxServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Desayuno", "Almuerzo", "Cena" }));

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jlabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlabel.setText("Identificación");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Servicio");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Dia de la semana");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        jButton2.setText("PAPELERIA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTablepedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Identificacion", "Nombre", "Fecha_entrega", "Opcion", "Lugar_entrega", "Servicio", "Ceco", "Area", "Contratista"
            }
        ));
        jTablepedido.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTablepedido);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(btnactualizar)
                        .addGap(38, 38, 38)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnexportar)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtcedula, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboboxServicio, 0, 93, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar)
                                .addGap(22, 22, 22))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboboxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscar)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btneliminar)
                        .addComponent(btnactualizar)
                        .addComponent(jButton2))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Inicio");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Volver a inicio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gestiondepedidos mn = new gestiondepedidos();
        mn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
eliminarPedidosSemana();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
       ExportarExcel obj;

        try {
            obj = new ExportarExcel();
            obj.exportarExcel(jTablepedido);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
       
    }//GEN-LAST:event_btnexportarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        consultarpedidos consultpedidos = new consultarpedidos();
        consultpedidos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         
          filtrarPedidosPorCriterios();
  
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        papeleriadereciclaje papel = new papeleriadereciclaje();
        papel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       inicio in = new inicio();
        in.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

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
            java.util.logging.Logger.getLogger(Consolidadodepedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consolidadodepedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consolidadodepedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consolidadodepedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consolidadodepedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboboxDia;
    private javax.swing.JComboBox<String> jComboboxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablepedido;
    private javax.swing.JLabel jlabel;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JTextField txtcedula;
    // End of variables declaration//GEN-END:variables
}
