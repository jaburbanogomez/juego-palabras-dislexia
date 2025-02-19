/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContenidoVisual;

import jaco.mp3.player.MP3Player;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author JORGE BURBANO
 */
public class VentanaBienvenida extends javax.swing.JFrame {

    File rtaudiobienvenido = new File("src/MusicaJuego/audiobienvenidajuego.mp3");
    MP3Player audiobienvenido = new MP3Player(rtaudiobienvenido);
    ImageIcon imagen;
    Icon icono;

    public VentanaBienvenida() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconojuego.png")).getImage());
        setResizable(false);
        audiobienvenido.play();
        ponerBtnJugar(btn_jugar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btn_jugar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(580, 380));
        setMinimumSize(new java.awt.Dimension(580, 380));
        setPreferredSize(new java.awt.Dimension(580, 380));
        getContentPane().setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo_bienvenida.gif"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 60, 510, 150);

        btn_jugar.setBackground(new java.awt.Color(224, 49, 49));
        btn_jugar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_jugar.setForeground(new java.awt.Color(255, 255, 255));
        btn_jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jugarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_jugar);
        btn_jugar.setBounds(220, 220, 130, 90);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bienvenida_1.gif"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 570, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void ponerBtnJugar(JButton btn_iniciar) {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/playbienve.gif"));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(btn_iniciar.getWidth(),
                btn_iniciar.getHeight(), Image.SCALE_DEFAULT));
        btn_iniciar.setIcon(icono);

    }

    private void btn_jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jugarActionPerformed
        VentanaJuego vj = new VentanaJuego();
        vj.setVisible(true);
        this.setVisible(false);
        audiobienvenido.stop();
    }//GEN-LAST:event_btn_jugarActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaBienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBienvenida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_jugar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
