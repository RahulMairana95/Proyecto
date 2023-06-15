/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author RAHUL
 */
public class VistaMembrecia extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaMembrecia
     */
    public VistaMembrecia() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtpaterno = new javax.swing.JTextField();
        txtdocumento = new javax.swing.JTextField();
        boxestado = new javax.swing.JComboBox<>();
        datevonversion = new com.toedter.calendar.JDateChooser();
        datebautizo = new com.toedter.calendar.JDateChooser();
        datenacimiento = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablademiembros = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtmaterno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        botonnuevo = new javax.swing.JButton();
        botonagregar = new javax.swing.JButton();
        botoncancelar = new javax.swing.JButton();
        botonmodificar = new javax.swing.JButton();
        botoneliminar = new javax.swing.JButton();
        botonreporte = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        botonlistar = new javax.swing.JButton();
        boxactivo = new javax.swing.JComboBox<>();
        boxtalentos = new javax.swing.JComboBox<>();
        boxdones = new javax.swing.JComboBox<>();
        btnbuscar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO DE MEMBRECÍA");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellidos Paterno:");

        jLabel4.setText("N° Documento:");

        jLabel5.setText("Fecha de Nacimiento:");

        jLabel6.setText("Estado civil:");

        jLabel7.setText("Fecha de bautismo:");

        jLabel8.setText("Fecha de conversion:");

        jLabel9.setText("Talentos:");

        jLabel10.setText("Activo:");

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txtpaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaternoActionPerformed(evt);
            }
        });
        txtpaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpaternoKeyTyped(evt);
            }
        });

        txtdocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocumentoActionPerformed(evt);
            }
        });
        txtdocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdocumentoKeyTyped(evt);
            }
        });

        boxestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASADO", "SOLTERO", "CONCUBINO", "VIUDO", "VIUDA" }));
        boxestado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxestadoKeyPressed(evt);
            }
        });

        datevonversion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datevonversionKeyPressed(evt);
            }
        });

        datebautizo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datebautizoKeyPressed(evt);
            }
        });

        datenacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datenacimientoKeyPressed(evt);
            }
        });

        tablademiembros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "APELLIDO P.", "APELLIDO M.", "C.I.", "FECHA NACIMIENTO", "ESTADO CIVIL", "FECHA CONVERSION", "FECHA BAUTIZO", "TALENTOS", "DONES", "ACTIVO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablademiembros);

        jLabel12.setText("Apellido Materno:");

        txtmaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaternoActionPerformed(evt);
            }
        });
        txtmaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Mayus(evt);
            }
        });

        jLabel11.setText("Dones:");

        botonnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon nuevo.png"))); // NOI18N
        botonnuevo.setText("NUEVO");

        botonagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon registro.png"))); // NOI18N
        botonagregar.setText("REGISTRAR");
        botonagregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonagregarKeyPressed(evt);
            }
        });

        botoncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconcance.png"))); // NOI18N
        botoncancelar.setText("CANCELAR");
        botoncancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botoncancelarKeyPressed(evt);
            }
        });

        botonmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconmodificar.png"))); // NOI18N
        botonmodificar.setText("MODIFICAR");

        botoneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconeliminar.png"))); // NOI18N
        botoneliminar.setText("ELIMINAR");

        botonreporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconexcel.png"))); // NOI18N
        botonreporte.setText("EXPORTAR");

        txtbuscar.setToolTipText("");
        txtbuscar.setName(""); // NOI18N

        jLabel13.setText("BUSCAR:");

        botonlistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistaboton.png"))); // NOI18N
        botonlistar.setText("LISTAR");
        botonlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonlistarActionPerformed(evt);
            }
        });

        boxactivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));
        boxactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxactivoActionPerformed(evt);
            }
        });
        boxactivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxactivoKeyPressed(evt);
            }
        });

        boxtalentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TALENTO MUSICAL", "TALENTO DEPORTIVO", "TALENTO CREATIVO", "TALENTO EN LA GESTION DE PERSONAL", "TALENTO ORGANIZACIONAL", "TALENTO EN LA GASTRONOMIA", "TALENTO EN AREA DE VENTAS", "TALENTO EN MANEJO DE DINERO", "TALENTO EN MANEJO DE INVERSIONES", "TALENTO EN IDIOMAS", "TALENTO EN LA COMUNICACION", "TALENTO CIENTIFICO", "TALENTO EN LA ACTUACION" }));
        boxtalentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxtalentosActionPerformed(evt);
            }
        });
        boxtalentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxtalentosKeyPressed(evt);
            }
        });

        boxdones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DON DE LENGUAS", "DON DE INTERPRETACION DE LENGUAS", "DON DE TRADUCCION", "DON DE SABIDURIA", "DON DE CONOCIMIENTO" }));
        boxdones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxdonesActionPerformed(evt);
            }
        });
        boxdones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxdonesKeyPressed(evt);
            }
        });

        btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconbuscar.png"))); // NOI18N
        btnbuscar1.setText("BUSCAR");
        btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("DATOS DE MIEMBROS DE LA IGLESIA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonagregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoncancelar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxestado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxtalentos, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxdones, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxactivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtmaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtpaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(datenacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(datebautizo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                .addComponent(datevonversion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonlistar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                                .addComponent(botonmodificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botoneliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonreporte))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(btnbuscar1)
                            .addComponent(botonlistar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonmodificar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(botonreporte)
                                .addComponent(botoneliminar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datenacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datevonversion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datebautizo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxtalentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxdones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxactivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonnuevo)
                    .addComponent(botonagregar)
                    .addComponent(botoncancelar))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtpaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaternoActionPerformed

    private void txtmaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaternoActionPerformed

    private void boxactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxactivoActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        String nom=txtnombre.getText();
        char validar=evt.getKeyChar();
        if(nom.length()>0){
            char primletra=nom.charAt(0);
            nom=Character.toUpperCase(primletra)+nom.substring(1, nom.length() );
            txtnombre.setText(nom);
        }else if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "INGRESE SOLO LETRAS");
        }
        
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtpaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaternoKeyTyped
        String nom=txtpaterno.getText();
        char validar=evt.getKeyChar();
        if(nom.length()>0){
            char primletra=nom.charAt(0);
            nom=Character.toUpperCase(primletra)+nom.substring(1, nom.length() );
            txtpaterno.setText(nom);
        }else if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "INGRESE SOLO LETRAS");
        }
    }//GEN-LAST:event_txtpaternoKeyTyped

    private void Mayus(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Mayus
        String nom=txtmaterno.getText();
        char validar=evt.getKeyChar();
        if(nom.length()>0){
            char primletra=nom.charAt(0);
            nom=Character.toUpperCase(primletra)+nom.substring(1, nom.length() );
            txtmaterno.setText(nom);
        }else if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "INGRESE SOLO LETRAS");
        }
    }//GEN-LAST:event_Mayus

    private void boxtalentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxtalentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxtalentosActionPerformed

    private void boxdonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxdonesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxdonesActionPerformed

    private void txtdocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocumentoActionPerformed
        
    }//GEN-LAST:event_txtdocumentoActionPerformed

    private void txtdocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocumentoKeyTyped
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "INGRESE SOLO NUMEROS");
        }
    }//GEN-LAST:event_txtdocumentoKeyTyped
///////////EVENTOS KEY PRESSED
    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            txtpaterno.requestFocus();
        }
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txtpaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaternoKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            txtmaterno.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            txtnombre.requestFocus();
        }
    }//GEN-LAST:event_txtpaternoKeyPressed

    private void txtdocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocumentoKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            boxestado.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            txtmaterno.requestFocus();
        }
    }//GEN-LAST:event_txtdocumentoKeyPressed

    private void datenacimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datenacimientoKeyPressed
        
    }//GEN-LAST:event_datenacimientoKeyPressed

    private void boxestadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxestadoKeyPressed
       if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            boxtalentos.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            datenacimiento.requestFocus();
        }
    }//GEN-LAST:event_boxestadoKeyPressed

    private void txtmaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmaternoKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            txtdocumento.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            txtpaterno.requestFocus();
        }
    }//GEN-LAST:event_txtmaternoKeyPressed

    private void datevonversionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datevonversionKeyPressed
        
    }//GEN-LAST:event_datevonversionKeyPressed

    private void datebautizoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datebautizoKeyPressed
        
    }//GEN-LAST:event_datebautizoKeyPressed

    private void boxtalentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxtalentosKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            boxdones.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            datebautizo.requestFocus();
        }
    }//GEN-LAST:event_boxtalentosKeyPressed

    private void boxdonesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxdonesKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            boxactivo.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            boxtalentos.requestFocus();
        }
    }//GEN-LAST:event_boxdonesKeyPressed

    private void boxactivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxactivoKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            botonagregar.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            boxdones.requestFocus();
        }
    }//GEN-LAST:event_boxactivoKeyPressed

    private void botonagregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonagregarKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
            botoncancelar.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            boxactivo.requestFocus();
        }
    }//GEN-LAST:event_botonagregarKeyPressed

    private void botoncancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botoncancelarKeyPressed
        if(evt.getExtendedKeyCode() == KeyEvent.VK_LEFT){
            botonagregar.requestFocus();
        }else if(evt.getExtendedKeyCode() == KeyEvent.VK_UP){
            boxactivo.requestFocus();
        }
    }//GEN-LAST:event_botoncancelarKeyPressed

    private void botonlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonlistarActionPerformed
        
    }//GEN-LAST:event_botonlistarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar1ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonagregar;
    public javax.swing.JButton botoncancelar;
    public javax.swing.JButton botoneliminar;
    public javax.swing.JButton botonlistar;
    public javax.swing.JButton botonmodificar;
    public javax.swing.JButton botonnuevo;
    public javax.swing.JButton botonreporte;
    public javax.swing.JComboBox<String> boxactivo;
    public javax.swing.JComboBox<String> boxdones;
    public javax.swing.JComboBox<String> boxestado;
    public javax.swing.JComboBox<String> boxtalentos;
    public javax.swing.JButton btnbuscar1;
    public com.toedter.calendar.JDateChooser datebautizo;
    public com.toedter.calendar.JDateChooser datenacimiento;
    public com.toedter.calendar.JDateChooser datevonversion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablademiembros;
    public javax.swing.JTextField txtbuscar;
    public javax.swing.JTextField txtdocumento;
    public javax.swing.JTextField txtmaterno;
    public javax.swing.JTextField txtnombre;
    public javax.swing.JTextField txtpaterno;
    // End of variables declaration//GEN-END:variables

    public String datenacimiento(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JDateChooser datevonversion(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
