
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends javax.swing.JFrame {

    private ClienteClass cliente = null;

    //Iniciamos el hilo Cliente en el constructor
    public Cliente() throws IOException {
        initComponents();
        if (cliente == null) {
            ClienteClass cli = new ClienteClass("Cliente");
            cli.start();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCliente = new javax.swing.JTextField();
        btnCyCCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaCliente = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
        });

        btnCyCCliente.setText("CyC");
        btnCyCCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCyCClienteActionPerformed(evt);
            }
        });

        txtAreaCliente.setColumns(20);
        txtAreaCliente.setRows(5);
        jScrollPane1.setViewportView(txtAreaCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCliente)
            .addGroup(layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(btnCyCCliente)
                .addContainerGap(348, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCyCCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
    }//GEN-LAST:event_txtClienteActionPerformed

    //Texfield, cuando pulsas enter envia el mensaje y se pone en blanco para seguir escribiendo
    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed

        if (evt.getKeyCode() == 10) {
            try {
                ClienteClass.escribirMensaje(txtCliente.getText());
                txtCliente.setText("");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_txtClienteKeyPressed

    //Boton para cerrar conexion manda el mensaje nabucodonosorcito 
    private void btnCyCClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCyCClienteActionPerformed
        try {

            ClienteClass.escribirMensaje("nabucodonosorcito");
            ClienteClass.terminarChat();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }    }//GEN-LAST:event_btnCyCClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCyCCliente;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea txtAreaCliente;
    public static javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
