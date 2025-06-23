/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Kthryn
 */
public class consultarpedidos extends javax.swing.JFrame {
// realizamos la conexion con la BD
conexion co=new conexion();
Connection conet;
Statement st;
ResultSet rs;
DefaultTableModel modelopedidos;
    /**
     * Creates new form consultarpedidos
     */
    public consultarpedidos() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        
    }

    public void verpedidos() {
    String cedulaTexto = txtcedula.getText().trim(); // Campo de texto donde el usuario escribe su cédula

    if (cedulaTexto.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de cédula.");
        return;
    }

    try {
        int cedula = Integer.parseInt(cedulaTexto); // Convertimos el texto a número

        String sql = "SELECT idpedido, Dia_de_reservacion, Lugar_entrega, TipoServicio FROM reservacion WHERE cedula = ?";


        conet = co.getConnection();
        PreparedStatement pst = conet.prepareStatement(sql);
        pst.setInt(1, cedula); // Enviamos como número

        rs = pst.executeQuery();

        modelopedidos = (DefaultTableModel) jTableconsultarpedidos.getModel();
        modelopedidos.setRowCount(0); // Limpiar la tabla antes de llenarla

        boolean hayResultados = false;

        while (rs.next()) {
            Object[] fila = new Object[4];
            fila[0] = rs.getString("idpedido");
            fila[1] = rs.getString("Dia_de_reservacion");
            fila[2] = rs.getString("Lugar_entrega");
            fila[3] = rs.getString("TipoServicio");
            
            modelopedidos.addRow(fila);
            hayResultados = true;
        }

        if (!hayResultados) {
            JOptionPane.showMessageDialog(null, "No se encontraron pedidos para esa cédula.");
        }

        jTableconsultarpedidos.setModel(modelopedidos);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar pedidos: " + e.getMessage());
    }
}

    
  
    private void eliminarpedido() {
    int filaSeleccionada = jTableconsultarpedidos.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esta fila?", "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        
        int id = Integer.parseInt(jTableconsultarpedidos.getValueAt(filaSeleccionada, 0).toString());

        try {
            Connection con = co.getConnection();
            String sql = "DELETE FROM reservacion WHERE idpedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                
                DefaultTableModel model = (DefaultTableModel) jTableconsultarpedidos.getModel();
                model.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar (ID no encontrado).");
            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }
}
    
       
    
    
    
    
    
    public void actualizar() {
    try {
        int fila = jTableconsultarpedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // ✅ Obtenemos el ID del pedido (columna 0)
        int idpedido = Integer.parseInt(jTableconsultarpedidos.getValueAt(fila, 0).toString());

        // ✅ Obtenemos los nuevos valores desde los JComboBox
        String diareservacion = jComboBoxdiadereservacion.getSelectedItem().toString();
        String lugar = jComboBoxlugar.getSelectedItem().toString();
        String servicio = jComboBoxservicio.getSelectedItem().toString().trim();


        // ✅ Consulta SQL correcta
        String sql = "UPDATE reservacion SET Dia_de_reservacion = ?, Lugar_entrega = ?, TipoServicio = ? WHERE idpedido = ?";

        Connection conet = co.getConnection();
        PreparedStatement pst = conet.prepareStatement(sql);

        // ✅ Establecemos los parámetros
        pst.setString(1, diareservacion);
        pst.setString(2, lugar);
        pst.setString(3, servicio);
        pst.setInt(4, idpedido); // ID de la fila seleccionada

        // ✅ Ejecutamos
        int filasactualizadas = pst.executeUpdate();

        if (filasactualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
            verpedidos(); // Refrescar tabla
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el registro para actualizar.");
        }

        pst.close();
        conet.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
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
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlabelcedula = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jlabellugardeentrega = new javax.swing.JLabel();
        jLabelservicio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableconsultarpedidos = new javax.swing.JTable();
        jComboBoxdiadereservacion = new javax.swing.JComboBox<>();
        jComboBoxlugar = new javax.swing.JComboBox<>();
        jComboBoxservicio = new javax.swing.JComboBox<>();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnconsultar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jlabelcedula.setText("Cédula:");

        txtcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcedulaActionPerformed(evt);
            }
        });

        jLabel1.setText("Día de reservación: ");

        jlabellugardeentrega.setText("Lugar de entrega:");

        jLabelservicio.setText("Tipo de servicio:");

        jTableconsultarpedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id pedido", "Dia de reservación", "Lugar de entrega", "Tipo de servicio"
            }
        ));
        jScrollPane1.setViewportView(jTableconsultarpedidos);

        jComboBoxdiadereservacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo" }));

        jComboBoxlugar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Comedor Platanal", "Aph HIgabra", "Mantenimiento mecanico" }));

        jComboBoxservicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Desayuno", "Almuerzo", "Cena" }));

        btnactualizar.setBackground(new java.awt.Color(102, 153, 0));
        btnactualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(102, 153, 0));
        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnconsultar.setBackground(new java.awt.Color(102, 153, 0));
        btnconsultar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnconsultar.setText("CONSULTAR");
        btnconsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restaurante (1).png"))); // NOI18N
        jButton1.setText("inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        jButton2.setText("Consolidado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1)
                        .addComponent(jlabellugardeentrega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelservicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jlabelcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxdiadereservacion, 0, 112, Short.MAX_VALUE)
                        .addComponent(jComboBoxlugar, 0, 1, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jComboBoxservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconsultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnactualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addGap(12, 12, 12)
                        .addComponent(btnconsultar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabelcedula)
                            .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBoxdiadereservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabellugardeentrega)
                            .addComponent(jComboBoxlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelservicio)
                            .addComponent(jComboBoxservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        inicio in = new inicio();
        in.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarActionPerformed
        verpedidos();
    }//GEN-LAST:event_btnconsultarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        eliminarpedido();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     Consolidadodepedidos consopedidos = new Consolidadodepedidos();
     consopedidos.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnactualizarActionPerformed

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
            java.util.logging.Logger.getLogger(consultarpedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultarpedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultarpedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultarpedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultarpedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnconsultar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxdiadereservacion;
    private javax.swing.JComboBox<String> jComboBoxlugar;
    private javax.swing.JComboBox<String> jComboBoxservicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelservicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableconsultarpedidos;
    private javax.swing.JLabel jlabelcedula;
    private javax.swing.JLabel jlabellugardeentrega;
    private javax.swing.JTextField txtcedula;
    // End of variables declaration//GEN-END:variables
    }
