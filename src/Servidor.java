
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends javax.swing.JFrame {

    private ChastPSP server = null;

    public Servidor() {
        initComponents();
        if (server == null) {
            ChastPSP chat = new ChastPSP("Servidor");
            chat.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtServidor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaServidor = new javax.swing.JTextArea();
        btnCyCServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServidorActionPerformed(evt);
            }
        });
        txtServidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtServidorKeyPressed(evt);
            }
        });

        txtAreaServidor.setColumns(20);
        txtAreaServidor.setRows(5);
        txtAreaServidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaServidorKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtAreaServidor);

        btnCyCServer.setText("CyC");
        btnCyCServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCyCServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtServidor)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(btnCyCServer)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnCyCServer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServidorActionPerformed

    }//GEN-LAST:event_txtServidorActionPerformed

    private void txtAreaServidorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaServidorKeyPressed

    }//GEN-LAST:event_txtAreaServidorKeyPressed

    private void txtServidorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServidorKeyPressed
        if (evt.getKeyCode() == 10) {
            try {
                ChastPSP.escribirMensajes(txtServidor.getText());
                txtServidor.setText("");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_txtServidorKeyPressed

    private void btnCyCServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCyCServerActionPerformed
        try {
            txtAreaServidor.setText("nabucodonosorcito  cyc");
            //Cliente.txtAreaCliente.setText("nabucodonosorcito  cyc"); 
            ChastPSP.infinito=false;
            ChastPSP.terminarChat();
            //this.setVisible(false);
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnCyCServerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCyCServer;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea txtAreaServidor;
    public static javax.swing.JTextField txtServidor;
    // End of variables declaration//GEN-END:variables
}
