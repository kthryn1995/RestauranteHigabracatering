package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class admonusuarios extends javax.swing.JFrame {
// realizamos nuestra conexion
    
conexion co=new conexion();
Connection conet;
Statement st;
ResultSet rs;
DefaultTableModel tablausuarios;
int idc;


   
    public admonusuarios() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        consultaradmonusuario();
        
    }
  
    public void limpiar(){
    
    while(tablausuarios.getRowCount()>0){
       tablausuarios.removeRow(0);
    
    }
    
    }
 
    
    //creamos nuestro metodo consultar
    
    public void consultaradmonusuario(){
        String sql = "select * from usuario";// seleccionamos la tabla 
        //de donde quiero traer la informacion
        
        
        try{
        conet = co.getConnection();
        st=conet.createStatement();
        rs= st.executeQuery(sql);
        
      
    
    tablausuarios =(DefaultTableModel)jtregistro.getModel();
    tablausuarios.setRowCount(0); //limpia todas las filas anteriores

while (rs.next()) {
    Object[] persona = new Object[5];
    persona[0] = rs.getInt("idUsuario");
    persona[1] = rs.getString("NombreCompleto");
    persona[2] = rs.getString("Ceco");
    persona[3] = rs.getString("Area");
    persona[4] = rs.getString("Contratista");

    tablausuarios.addRow(persona);
}
 jtregistro.setModel(tablausuarios);

        
        
        } catch (SQLException e){
JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());        
        }
        
    }
   public void Consultarusuario() {
    String cedulaTexto = txtcedulausuario.getText().trim(); // Campo de texto donde el usuario escribe su cédula

    if (cedulaTexto.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de cédula.");
        return;
    }

    try {
        int cedula = Integer.parseInt(cedulaTexto); // Convertimos el texto a número

        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";

        conet = co.getConnection();
        PreparedStatement pst = conet.prepareStatement(sql);
        pst.setInt(1, cedula);

        rs = pst.executeQuery();

        DefaultTableModel tablausuarios = (DefaultTableModel) jtregistro.getModel();
        tablausuarios.setRowCount(0); // Limpiar la tabla antes de llenarla

        boolean hayResultados = false;

        while (rs.next()) {
            Object[] fila = new Object[5]; // Porque mostraremos 5 columnas
            fila[0] = rs.getString("idUsuario");
            fila[1] = rs.getString("NombreCompleto");
            fila[2] = rs.getString("Ceco");
            fila[3] = rs.getString("Area");
            fila[4] = rs.getString("Contratista");

            tablausuarios.addRow(fila);
            hayResultados = true;
        }

        if (!hayResultados) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con esa cédula.");
        }

        jtregistro.setModel(tablausuarios);

        pst.close();
        rs.close();
      

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar usuario: " + e.getMessage());
    }
}


    
    
    
    
   public void actualizar() {
    Connection conet = null;
    PreparedStatement pst = null;

    try {
        int fila = jtregistro.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila.");
            return;
        }

        // Obtiene datos de las cajas de texto
        int id = Integer.parseInt(jtregistro.getValueAt(fila, 0).toString());
        String nombres = txtnombre.getText();
        String ceco = txtceco.getText();
        String area = txtarea.getText();
        String contratista = txtcontratista.getText();

        // Usa la conexión ya creada
        conet = co.getConnection();

        String sql = "UPDATE usuario SET Nombrecompleto=?, Ceco=?, Area=?, Contratista=? WHERE IdUsuario=?";
        pst = conet.prepareStatement(sql);

        pst.setString(1, nombres);
        pst.setString(2, ceco);
        pst.setString(3, area);
        pst.setString(4, contratista);
        pst.setInt(5, id);

        int filasactualizadas = pst.executeUpdate();
        if (filasactualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Registro actualizado.");
            limpiar();
            consultaradmonusuario(); // Refresca la tabla
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro para actualizar.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
    } finally {
        // Cierra solo el PreparedStatement
        try {
            if (pst != null) pst.close();
            // No cerrar conet aquí, porque usarás la misma conexión global en otros métodos
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
}


   
   
   
  private void eliminar() {
    int filaSeleccionada = jtregistro.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esta fila?", "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        
        int id = Integer.parseInt(jtregistro.getValueAt(filaSeleccionada, 0).toString());

        try {
            Connection con = co.getConnection();
            String sql = "DELETE FROM usuario WHERE idUsuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                
                DefaultTableModel model = (DefaultTableModel) jtregistro.getModel();
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

   
   
   
   
   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtregistro = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtidentificacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtceco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtarea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcontratista = new javax.swing.JTextField();
        btnconsultar = new javax.swing.JButton();
        txtcedulausuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnactualizar.setBackground(new java.awt.Color(153, 211, 38));
        btnactualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(153, 211, 38));
        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jtregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Identificacion", "Nombre", "Ceco", "Area", "Contratista"
            }
        ));
        jtregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtregistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtregistro);

        jLabel1.setText("Identifación:");

        txtidentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidentificacionActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre:");

        jLabel3.setText("Ceco:");

        jLabel4.setText("Area:");

        jLabel5.setText("Contratista:");

        btnconsultar.setBackground(new java.awt.Color(153, 211, 38));
        btnconsultar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnconsultar.setText("CONSULTAR");
        btnconsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtrar por cédula:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtarea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtceco, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidentificacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcontratista)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcedulausuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnactualizar)
                    .addComponent(btnconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnconsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnactualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcedulausuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidentificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtceco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addComponent(txtcontratista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 211, 38));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atras.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      registro rg = new registro();
      rg.setVisible(true);
      this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtidentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidentificacionActionPerformed

    private void jtregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtregistroMouseClicked
         txtidentificacion.setEditable(false);
       int fila = jtregistro.getSelectedRow();
      if (fila ==-1){
          JOptionPane.showMessageDialog(null,"Seleccione una fila");
          
      }else {
      idc = Integer.parseInt(jtregistro.getValueAt(fila, 0).toString());

    String nombres = (String) jtregistro.getValueAt(fila, 1);
    String ceco = (String) jtregistro.getValueAt(fila, 2);
    String area = (String) jtregistro.getValueAt(fila, 3);
    String contratista = (String) jtregistro.getValueAt(fila, 4);    

      txtidentificacion.setText(""+idc);
      txtnombre.setText(nombres);
      txtceco.setText(ceco);
      txtarea.setText(area);
      txtcontratista.setText(contratista);


      }
    }//GEN-LAST:event_jtregistroMouseClicked

    private void btnconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarActionPerformed
        Consultarusuario();
    }//GEN-LAST:event_btnconsultarActionPerformed

    
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
            java.util.logging.Logger.getLogger(admonusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admonusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admonusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admonusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admonusuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnconsultar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtregistro;
    private javax.swing.JTextField txtarea;
    private javax.swing.JTextField txtceco;
    private javax.swing.JTextField txtcedulausuario;
    private javax.swing.JTextField txtcontratista;
    private javax.swing.JTextField txtidentificacion;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
