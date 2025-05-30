package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 *
 * @author Kthryn
 */
public class menu extends javax.swing.JFrame {
    conexion co= new conexion();
    Connection conet;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    
    
    
    
    
    public menu() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false); 
        
    }

   
 
    public void ConsultarMenu() {
    String sql = "SELECT * FROM plan_comidas WHERE dia_semana = ? AND tipo_comida = ?";
    
    try {
        Connection conet = co.getConnection();
        PreparedStatement pst = conet.prepareStatement(sql);
        
        
        String diaSeleccionado = jComboBoxDias.getSelectedItem().toString();
        String servicioSeleccionado = jComboServicio.getSelectedItem().toString();

      
        pst.setString(1, diaSeleccionado); 
        pst.setString(2, servicioSeleccionado); 

        ResultSet rs = pst.executeQuery(); 

       
        DefaultTableModel modelo = (DefaultTableModel) jtpedido.getModel();
        modelo.setRowCount(0);

       
        while (rs.next()) {
            Object[] fila = new Object[3];
            fila[0] = rs.getString("tipo_comida");
            fila[1] = rs.getInt("opcion");
            fila[2] = rs.getString("descripcion");

            modelo.addRow(fila);
        }
        jtpedido.setModel(modelo);

    } catch (Exception e) {
        
       
    }
}
 
  public void Mostrarusuario() {
    String cedula = txtcedula1.getText().trim(); // Elimina espacios en blanco
    if (cedula.isEmpty()) {
        jLabelnombreusuario.setText("Ingrese una cédula");
        return;
    }

    String sql = "SELECT nombreCompleto FROM usuario WHERE idUsuario = ?";

    try {
        conet = co.getConnection();
        PreparedStatement pst = conet.prepareStatement(sql);
        pst.setString(1, cedula);
        rs = pst.executeQuery();

        if (rs.next()) {
            String nombreCompleto = rs.getString("nombreCompleto");
            jLabelnombreusuario.setText(nombreCompleto);
            System.out.println("Usuario encontrado: " + nombreCompleto);
         
            
        } else {
            jLabelnombreusuario.setText("Usuario no encontrado");
            System.out.println("No se encontró la cédula: " + cedula);
        }
        jLabelnombreusuario.repaint(); // Forzar actualización

    } catch (Exception e) {
        jLabelnombreusuario.setText("Error al buscar usuario");
        System.out.println("Error: " + e.getMessage());
    }
}



        
   public void añadirpedidos() {
    int filaSeleccionada = jtpedido.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtener datos del menú seleccionado en la tabla
            String tipoComida = jtpedido.getValueAt(filaSeleccionada, 0).toString(); // No se guarda en BD, solo validación
            String opcion = jtpedido.getValueAt(filaSeleccionada, 1).toString();
            String descripcion = jtpedido.getValueAt(filaSeleccionada, 2).toString();

            // Obtener datos de los combo box
            String dia = jComboBoxDias.getSelectedItem().toString();
            String tipoServicio = jComboServicio.getSelectedItem().toString();
            String lugarEntrega = jComboBoxlugar.getSelectedItem().toString();
            String identificacion = txtcedula1.getText();

          

            // Conexión y guardado en la base de datos
            try  {
                String sql = "INSERT INTO reservacion (cedula,Dia_de_reservacion, Lugar_entrega, TipoServicio, Opcion, descripcion) VALUES (?,?,?,?,?,?)";
                 Connection conet = co.getConnection();
               PreparedStatement pst = conet.prepareStatement(sql);
                pst.setString(1, identificacion);
                pst.setString(2, dia);
                pst.setString(3, lugarEntrega);
                pst.setString(4, tipoServicio);
                pst.setString(5, opcion);
                pst.setString(6, descripcion);
                

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Reservación guardada con éxito.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar la reservación: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una opción del menú.");
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jComboServicio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelcalendario = new javax.swing.JLabel();
        jLabellugarentrega = new javax.swing.JLabel();
        jComboBoxlugar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btndetallesdelpedido = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpedido = new javax.swing.JTable();
        jComboBoxDias = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnañadir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtcedula1 = new javax.swing.JTextField();
        jLabelnombreusuario = new javax.swing.JLabel();
        btndetalles = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Desayuno", "Almuerzo", "Cena" }));
        jComboServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboServicioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tipo de servicio");

        jLabelcalendario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelcalendario.setText("Fecha");

        jLabellugarentrega.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabellugarentrega.setText("Lugar de entrega");

        jComboBoxlugar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Comedor platanal", "Aph Higabra", "Mantenimiento mecanico" }));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/higabra.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        btndetallesdelpedido.setText("Detalles del pedido");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtpedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tipo de comida", "Opcion", "Descripcion"
            }
        ));
        jtpedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpedidoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtpedido);
        if (jtpedido.getColumnModel().getColumnCount() > 0) {
            jtpedido.getColumnModel().getColumn(0).setMinWidth(100);
            jtpedido.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtpedido.getColumnModel().getColumn(0).setMaxWidth(100);
            jtpedido.getColumnModel().getColumn(1).setMinWidth(50);
            jtpedido.getColumnModel().getColumn(1).setPreferredWidth(50);
            jtpedido.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jComboBoxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("CONSULTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("MENÚ");

        btnañadir.setText("Añadir");
        btnañadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnañadirActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese numero de cedula");

        txtcedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcedula1ActionPerformed(evt);
            }
        });

        jLabelnombreusuario.setForeground(new java.awt.Color(204, 204, 204));
        jLabelnombreusuario.setText("usuario");

        btndetalles.setText("DETALLES DEL PEDIDO");
        btndetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelcalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(329, 329, 329)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btndetalles))
                                .addGap(331, 331, 331)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btndetallesdelpedido))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabellugarentrega, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(481, 481, 481)
                        .addComponent(btnañadir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(281, 281, 281)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelnombreusuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcedula1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btndetallesdelpedido)
                .addGap(552, 552, 552)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btndetalles)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelcalendario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnañadir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabellugarentrega, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(145, 145, 145))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcedula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelnombreusuario)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboServicioActionPerformed
        
        
        
    }//GEN-LAST:event_jComboServicioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    inicio in= new inicio();
    in.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
      ConsultarMenu();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnañadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnañadirActionPerformed
       añadirpedidos();
    }//GEN-LAST:event_btnañadirActionPerformed

    private void jtpedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpedidoMouseClicked
 
    }//GEN-LAST:event_jtpedidoMouseClicked

    private void txtcedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedula1ActionPerformed
        Mostrarusuario();
    }//GEN-LAST:event_txtcedula1ActionPerformed

    private void btndetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndetallesActionPerformed
     detallespedido dp = new detallespedido();
             dp.setVisible(true);
             this.dispose();
    }//GEN-LAST:event_btndetallesActionPerformed


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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnañadir;
    private javax.swing.JButton btndetalles;
    private javax.swing.JButton btndetallesdelpedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxDias;
    private javax.swing.JComboBox<String> jComboBoxlugar;
    private javax.swing.JComboBox<String> jComboServicio;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelcalendario;
    private javax.swing.JLabel jLabellugarentrega;
    private javax.swing.JLabel jLabelnombreusuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtpedido;
    private javax.swing.JTextField txtcedula1;
    // End of variables declaration//GEN-END:variables
}
