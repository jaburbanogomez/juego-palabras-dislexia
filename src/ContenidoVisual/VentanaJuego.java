package ContenidoVisual;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author JORGE BURBANO
 */
public class VentanaJuego extends javax.swing.JFrame {

    //Declarando Arreglos
    String correctas[] = {"RATON", "TELEVISOR", "FLORERO", "MUÑECO", "ARBOLES", "SANDIA", "FUTBOLISTA", "GUZANO", "BALLENA", "NARANJA", "CAFETERA", "PATINES", "CALABAZA"};
    String incorrectas[] = {"GATON", "TETEVISOR", "FLOMERO", "PEÑECO", "ARMOLES", "PANDIA", "TRUTBOLISTA", "UMSANO", "TRALLENA", "NARANLA", "FAFETERA", "JATINES", "MALABAZA"};
    String ruta[] = {"raton_1.gif", "tv_1.gif", "florero.gif", "muñeco.gif", "arbol.gif", "sandia.gif", "fubolista.gif", "gusano.gif", "ballena.gif", "naranja.gif", "cafetera.gif", "patines.gif", "calabaza.gif"};
    Random rd = new Random();
    ImageIcon imagen;
    Icon icono;
    int opcion;
    int intento = 0;
    int aciertos = 0;
    int vidas = 3;
    int fallas = 0;
    int puntaje = 0;
    int puntos = 0;

    File rtaudioprincipal = new File("src/MusicaJuego/audioprincipaljuego.mp3");
    File rtacierto = new File("src/MusicaJuego/acierto.mp3");
    File rtaerror = new File("src/MusicaJuego/error.mp3");
    File rtaganador = new File("src/MusicaJuego/ganador.mp3");
    File rtaperdedor = new File("src/MusicaJuego/perdedor.mp3");
    MP3Player audioprincipal = new MP3Player(rtaudioprincipal);
    MP3Player audioacierto = new MP3Player(rtacierto);
    MP3Player audioerror = new MP3Player(rtaerror);
    MP3Player audioganador = new MP3Player(rtaganador);
    MP3Player audioperdedor = new MP3Player(rtaperdedor);

    public VentanaJuego() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconojuego.png")).getImage());
        setResizable(false);
        ponerBtnJugar(btn_jugar);
        btn_imagenes.setVisible(false);
        btn_jugar.setVisible(true);
        lbl_palabra.setVisible(false);
        txt_aciertos.setEditable(false);
        txt_fallas.setEditable(false);
        txt_puntaje.setEditable(false);
        btn_correcta.setEnabled(false);
        btn_incorrecta.setEnabled(false);
        btn_vida.setEnabled(false);
        btn_vida2.setEnabled(false);
        btn_vida3.setEnabled(false);
        String correctas[] = new String[13];
        String incorrectas[] = new String[13];
    }
    private Timer t;
    private int s = 30, cs;
    private ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            ++cs;
            if (cs == 100) {
                cs = 0;
                --s;
            }
            if (s == 0) {
                audioperdedor.play();
                audioprincipal.stop();
                btn_imagenes.setVisible(false);
                lbl_palabra.setVisible(false);
                txt_aciertos.setEditable(false);
                txt_fallas.setEditable(false);
                txt_puntaje.setEditable(false);
                btn_correcta.setEnabled(false);
                btn_incorrecta.setEnabled(false);
                btn_vida.setEnabled(false);
                btn_vida2.setEnabled(false);
                btn_vida3.setEnabled(false);
                String correctas[] = new String[13];
                String incorrectas[] = new String[13];
                perdedor();
                t.stop();
            }
            actualizarLabel();
        }

    };

    private void actualizarLabel() {
        String tiempo = (s <= 9 ? "0" : "") + s + ":" + (cs <= 9 ? "0" : "") + cs;
        lbl_tiempo.setText(tiempo);
    }

    //Pone icono de vidas al iniciar juego o al tener cinco aciertos correctos
    public void ponerIconoVidas(JButton btn_poner_vidas) {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/vida.gif"));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(btn_poner_vidas.getWidth(),
                btn_poner_vidas.getHeight(), Image.SCALE_DEFAULT));
        btn_poner_vidas.setIcon(icono);

    }

    public void ponerBtnJugar(JButton btn_iniciar) {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/iniciarjuego.gif"));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(btn_iniciar.getWidth(),
                btn_iniciar.getHeight(), Image.SCALE_DEFAULT));
        btn_iniciar.setIcon(icono);
    }

    //Pone imagenes para cada intento con su respectiva palabra
    public void ponerImagenesIntentos(JButton btn_poner_imagenes_intentos) {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/" + ruta[intento]));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(btn_poner_imagenes_intentos.getWidth(),
                btn_poner_imagenes_intentos.getHeight(), Image.SCALE_DEFAULT));
        btn_poner_imagenes_intentos.setIcon(icono);
    }

    //Quita el icono de vidas cuanto pierde una de estas
    public void quitarIconoVidas(JButton btn_quitar_vidas) {
        imagen = new ImageIcon(getClass().getResource("/Imagenes/corazonnegro.png"));
        icono = new ImageIcon(imagen.getImage().getScaledInstance(btn_quitar_vidas.getWidth(), btn_quitar_vidas.getHeight(), Image.SCALE_SMOOTH));
        btn_quitar_vidas.setIcon(icono);
    }

    //Genera una nueva palabra
    public void generarNuevaPalabra() {
        opcion = rd.nextInt(2);
        if (opcion == 0) {
            lbl_palabra.setText(correctas[intento]);
        } else if (opcion == 1) {
            lbl_palabra.setText(incorrectas[intento]);
        }
        ponerImagenesIntentos(btn_imagenes);
    }

    //Metodo de validacion de palabras correctas
    public void palabrasCorrectas() {
        if (opcion == 0) {
            audioacierto.play();
            aciertos++;
            puntaje = puntaje + 10;
            txt_puntaje.setText(" " + puntaje);
            txt_aciertos.setText(" " + aciertos);
        }
        if (opcion == 1) {
            audioerror.play();
            fallas++;
            txt_fallas.setText(" " + fallas);
        }
        if (opcion == 1) {
            vidas--;
            if (vidas < 3) {
                correctas = Arrays.copyOf(correctas, correctas.length + 1);
                incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
            }
            if (vidas == 2) {
                quitarIconoVidas(btn_vida3);
            } else if (vidas == 1) {
                quitarIconoVidas(btn_vida2);
            } else if (vidas == 0) {
                quitarIconoVidas(btn_vida);
            }
        }
        //Si acierta 5 veces le aumenta una vida
        if (opcion == 0) {
            if (aciertos == 5 && vidas < 3) {
                vidas++;
                if (vidas < 3) {
                    correctas = Arrays.copyOf(correctas, correctas.length + 1);
                    incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
                }
                if (vidas == 3) {
                    ponerIconoVidas(btn_vida3);
                } else if (vidas == 2) {
                    ponerIconoVidas(btn_vida2);
                }
            }
            if (aciertos == 8 && vidas < 3) {
                vidas++;
                intento -= 2;
                if (vidas < 3) {
                    correctas = Arrays.copyOf(correctas, correctas.length + 1);
                    incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
                }
                if (vidas == 3) {
                    ponerIconoVidas(btn_vida3);
                } else if (vidas == 2) {
                    ponerIconoVidas(btn_vida2);
                }
            }

        }
        if (opcion == 0) {
            if (vidas > 0 && puntaje == 110 && intento <= 12) {
                audioprincipal.stop();
                audioganador.play();
                t.stop();
                intento = intento - 1;
                btn_imagenes.setVisible(false);
                lbl_palabra.setVisible(false);
                txt_aciertos.setEditable(false);
                txt_fallas.setEditable(false);
                txt_puntaje.setEditable(false);
                btn_correcta.setEnabled(false);
                btn_incorrecta.setEnabled(false);
                btn_vida.setEnabled(false);
                btn_vida2.setEnabled(false);
                btn_vida3.setEnabled(false);
                ganador();
            }
        }
        if (opcion == 1) {
            if (vidas == 0) {
                audioprincipal.stop();
                audioperdedor.play();
                t.stop();
                intento = intento - 1;
                btn_imagenes.setVisible(false);
                lbl_palabra.setVisible(false);
                txt_aciertos.setEditable(false);
                txt_fallas.setEditable(false);
                txt_puntaje.setEditable(false);
                btn_correcta.setEnabled(false);
                btn_incorrecta.setEnabled(false);
                btn_vida.setEnabled(false);
                btn_vida2.setEnabled(false);
                btn_vida3.setEnabled(false);
                perdedor();
            }
        }
        intento++;
        generarNuevaPalabra();
    }

    public void palabrasIncorrectas() {
        if (opcion == 1) {
            audioacierto.play();
            aciertos++;
            puntaje = puntaje + 10;
            txt_puntaje.setText(" " + puntaje);
            txt_aciertos.setText(" " + aciertos);
        }
        if (opcion == 0) {
            audioerror.play();
            fallas++;
            txt_fallas.setText(" " + fallas);
        }
        if (opcion == 0) {
            vidas--;
            if (vidas < 3) {
                correctas = Arrays.copyOf(correctas, correctas.length + 1);
                incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
            }
            if (vidas == 2) {
                quitarIconoVidas(btn_vida3);
            } else if (vidas == 1) {
                quitarIconoVidas(btn_vida2);
            } else if (vidas == 0) {
                quitarIconoVidas(btn_vida);
            }
        }
        if (opcion == 1) {
            if (aciertos == 5 && vidas < 3) {
                vidas++;
                if (vidas < 3) {
                    correctas = Arrays.copyOf(correctas, correctas.length + 1);
                    incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
                }
                if (vidas == 3) {
                    ponerIconoVidas(btn_vida3);
                } else if (vidas == 2) {
                    ponerIconoVidas(btn_vida2);
                }
            }
            if (aciertos == 8 && vidas < 3) {
                vidas++;
                intento -= 2;
                if (vidas < 3) {
                    correctas = Arrays.copyOf(correctas, correctas.length + 1);
                    incorrectas = Arrays.copyOf(incorrectas, incorrectas.length + 1);
                }
                if (vidas == 3) {
                    ponerIconoVidas(btn_vida3);
                } else if (vidas == 2) {
                    ponerIconoVidas(btn_vida2);
                }
            }
        }
        if (opcion == 1) {
            if (vidas > 0 && puntaje == 110 && intento <= 12) {
                audioprincipal.stop();
                audioganador.play();
                t.stop();
                intento = intento - 1;
                btn_imagenes.setVisible(false);
                lbl_palabra.setVisible(false);
                txt_aciertos.setEditable(false);
                txt_fallas.setEditable(false);
                txt_puntaje.setEditable(false);
                btn_correcta.setEnabled(false);
                btn_incorrecta.setEnabled(false);
                btn_vida.setEnabled(false);
                btn_vida2.setEnabled(false);
                btn_vida3.setEnabled(false);
                ganador();
            }
        }
        if (opcion == 0) {
            if (vidas == 0) {
                audioprincipal.stop();
                audioperdedor.play();
                t.stop();
                intento = intento - 1;
                btn_imagenes.setVisible(false);
                lbl_palabra.setVisible(false);
                txt_aciertos.setEditable(false);
                txt_fallas.setEditable(false);
                txt_puntaje.setEditable(false);
                btn_correcta.setEnabled(false);
                btn_incorrecta.setEnabled(false);
                btn_vida.setEnabled(false);
                btn_vida2.setEnabled(false);
                btn_vida3.setEnabled(false);
                perdedor();

            }
        }
        intento++;
        generarNuevaPalabra();
    }

    public void ganador() {
        Ganaste gn = new Ganaste();
        gn.setVisible(true);
        btn_jugar.setVisible(true);

    }

    public void perdedor() {
        Perdiste gn = new Perdiste();
        gn.setVisible(true);
        btn_jugar.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_puntaje = new javax.swing.JTextField();
        txt_aciertos = new javax.swing.JTextField();
        txt_fallas = new javax.swing.JTextField();
        lbl_palabra = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_jugar = new javax.swing.JButton();
        btn_imagenes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_vida = new javax.swing.JButton();
        btn_vida2 = new javax.swing.JButton();
        btn_vida3 = new javax.swing.JButton();
        btn_correcta = new javax.swing.JButton();
        btn_incorrecta = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_tiempo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(480, 500));
        setMinimumSize(new java.awt.Dimension(725, 560));
        setPreferredSize(new java.awt.Dimension(500, 300));
        getContentPane().setLayout(null);

        txt_puntaje.setBackground(new java.awt.Color(24, 40, 24));
        txt_puntaje.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_puntaje.setForeground(new java.awt.Color(255, 255, 255));
        txt_puntaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_puntajeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_puntajeMouseExited(evt);
            }
        });
        getContentPane().add(txt_puntaje);
        txt_puntaje.setBounds(50, 470, 80, 30);

        txt_aciertos.setBackground(new java.awt.Color(24, 40, 24));
        txt_aciertos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_aciertos.setForeground(new java.awt.Color(255, 255, 255));
        txt_aciertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_aciertosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_aciertosMouseExited(evt);
            }
        });
        getContentPane().add(txt_aciertos);
        txt_aciertos.setBounds(180, 470, 80, 30);

        txt_fallas.setBackground(new java.awt.Color(121, 17, 17));
        txt_fallas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_fallas.setForeground(new java.awt.Color(255, 255, 255));
        txt_fallas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_fallasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_fallasMouseExited(evt);
            }
        });
        getContentPane().add(txt_fallas);
        txt_fallas.setBounds(310, 470, 80, 30);

        lbl_palabra.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_palabra.setForeground(new java.awt.Color(5, 21, 54));
        getContentPane().add(lbl_palabra);
        lbl_palabra.setBounds(530, 160, 150, 30);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nube.gif"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 130, 230, 120);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logotipo.gif"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(320, 50, 280, 60);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gamesdados.gif"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(190, 10, 170, 60);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/detalles.gif"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 0, 140, 140);

        btn_jugar.setBackground(new java.awt.Color(255, 102, 0));
        btn_jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jugarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_jugar);
        btn_jugar.setBounds(150, 210, 130, 90);

        btn_imagenes.setBackground(new java.awt.Color(102, 204, 255));
        getContentPane().add(btn_imagenes);
        btn_imagenes.setBounds(70, 160, 290, 190);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tv.gif"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 140, 460, 260);

        jPanel1.setBackground(new java.awt.Color(167, 55, 55));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btn_vida.setBackground(new java.awt.Color(240, 83, 83));
        jPanel1.add(btn_vida);

        btn_vida2.setBackground(new java.awt.Color(204, 204, 36));
        jPanel1.add(btn_vida2);

        btn_vida3.setBackground(new java.awt.Color(69, 193, 69));
        jPanel1.add(btn_vida3);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(460, 450, 240, 50);

        btn_correcta.setBackground(new java.awt.Color(24, 40, 24));
        btn_correcta.setForeground(new java.awt.Color(255, 255, 255));
        btn_correcta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/correcto.gif"))); // NOI18N
        btn_correcta.setText("CORRECTAS");
        btn_correcta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_correctaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_correctaMouseExited(evt);
            }
        });
        btn_correcta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_correctaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_correcta);
        btn_correcta.setBounds(500, 240, 160, 40);

        btn_incorrecta.setBackground(new java.awt.Color(121, 17, 17));
        btn_incorrecta.setForeground(new java.awt.Color(255, 255, 255));
        btn_incorrecta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/incorrecto.gif"))); // NOI18N
        btn_incorrecta.setText("INCORRECTAS");
        btn_incorrecta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_incorrectaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_incorrectaMouseExited(evt);
            }
        });
        btn_incorrecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_incorrectaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_incorrecta);
        btn_incorrecta.setBounds(500, 290, 160, 40);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/score.gif"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 420, 90, 50);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aciertos.gif"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(190, 420, 60, 52);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/falla.gif"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(320, 410, 60, 70);

        lbl_tiempo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbl_tiempo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tiempo.setText("00:00");
        getContentPane().add(lbl_tiempo);
        lbl_tiempo.setBounds(550, 370, 70, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconotiempo.gif"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(530, 340, 110, 90);

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoventanajuego.jpg"))); // NOI18N
        lbl_fondo.setText("jLabel3");
        getContentPane().add(lbl_fondo);
        lbl_fondo.setBounds(0, 0, 730, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btn_correctaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_correctaActionPerformed
        palabrasCorrectas();
    }//GEN-LAST:event_btn_correctaActionPerformed
    private void btn_incorrectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_incorrectaActionPerformed
        palabrasIncorrectas();
    }//GEN-LAST:event_btn_incorrectaActionPerformed

    private void btn_jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jugarActionPerformed
        t = new Timer(10, acciones);
        t.start();
        audioprincipal.play();
        vidas = 3;
        puntaje = 0;
        aciertos = 0;
        fallas = 0;
        intento = 0;
        s = 30;
        txt_puntaje.setText(" " + puntaje);
        txt_aciertos.setText(" " + aciertos);
        txt_fallas.setText(" " + fallas);
        generarNuevaPalabra();
        ponerIconoVidas(btn_vida);
        ponerIconoVidas(btn_vida2);
        ponerIconoVidas(btn_vida3);
        btn_jugar.setVisible(false);
        btn_imagenes.setVisible(true);
        lbl_palabra.setVisible(true);
        String correctas[] = new String[13];
        String incorrectas[] = new String[13];
        actualizarLabel();
        txt_fallas.setEditable(false);
        txt_puntaje.setEditable(false);
        btn_correcta.setEnabled(true);
        btn_incorrecta.setEnabled(true);
        btn_vida.setEnabled(true);
        btn_vida2.setEnabled(true);
        btn_vida3.setEnabled(true);

    }//GEN-LAST:event_btn_jugarActionPerformed

    private void btn_correctaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_correctaMouseEntered
        btn_correcta.setBackground(new Color(44, 123, 44));
    }//GEN-LAST:event_btn_correctaMouseEntered

    private void btn_correctaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_correctaMouseExited
        btn_correcta.setBackground(new Color(24, 40, 24));
    }//GEN-LAST:event_btn_correctaMouseExited

    private void btn_incorrectaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_incorrectaMouseEntered
        btn_incorrecta.setBackground(new Color(176, 40, 40));
    }//GEN-LAST:event_btn_incorrectaMouseEntered

    private void btn_incorrectaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_incorrectaMouseExited
        btn_incorrecta.setBackground(new Color(121, 17, 17));
    }//GEN-LAST:event_btn_incorrectaMouseExited

    private void txt_puntajeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_puntajeMouseEntered
        txt_puntaje.setBackground(new Color(44, 123, 44));
    }//GEN-LAST:event_txt_puntajeMouseEntered

    private void txt_puntajeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_puntajeMouseExited
        txt_puntaje.setBackground(new Color(24, 40, 24));
    }//GEN-LAST:event_txt_puntajeMouseExited

    private void txt_aciertosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_aciertosMouseEntered
        txt_aciertos.setBackground(new Color(44, 123, 44));
    }//GEN-LAST:event_txt_aciertosMouseEntered

    private void txt_aciertosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_aciertosMouseExited
        txt_aciertos.setBackground(new Color(24, 40, 24));
    }//GEN-LAST:event_txt_aciertosMouseExited

    private void txt_fallasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fallasMouseEntered
        txt_fallas.setBackground(new Color(176, 40, 40));
    }//GEN-LAST:event_txt_fallasMouseEntered

    private void txt_fallasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fallasMouseExited
        txt_fallas.setBackground(new Color(121, 17, 17));
    }//GEN-LAST:event_txt_fallasMouseExited
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_correcta;
    private javax.swing.JButton btn_imagenes;
    private javax.swing.JButton btn_incorrecta;
    private javax.swing.JButton btn_jugar;
    private javax.swing.JButton btn_vida;
    private javax.swing.JButton btn_vida2;
    private javax.swing.JButton btn_vida3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_palabra;
    private javax.swing.JLabel lbl_tiempo;
    private javax.swing.JTextField txt_aciertos;
    private javax.swing.JTextField txt_fallas;
    private javax.swing.JTextField txt_puntaje;
    // End of variables declaration//GEN-END:variables
}
