/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author RAHUL
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
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

        jScrollBar1 = new javax.swing.JScrollBar();
        panelPrincipal = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        idusuarioaminnis = new javax.swing.JTextField();
        lblusuario = new javax.swing.JTextField();
        botonUce = new javax.swing.JButton();
        botonJuve = new javax.swing.JButton();
        botonfemenil = new javax.swing.JButton();
        botonshiret = new javax.swing.JButton();
        botonmisiones = new javax.swing.JButton();
        botondominical = new javax.swing.JButton();
        botoncdi = new javax.swing.JButton();
        botonOansa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemInfo = new javax.swing.JMenuItem();
        registros = new javax.swing.JMenu();
        itemmiembros = new javax.swing.JMenuItem();
        itemlideriglesia = new javax.swing.JMenuItem();
        itemministerio = new javax.swing.JMenuItem();
        itemingresos = new javax.swing.JMenuItem();
        itemegresos = new javax.swing.JMenuItem();
        itemMembrecia = new javax.swing.JMenu();
        itemLista = new javax.swing.JMenuItem();
        liderazgo = new javax.swing.JMenu();
        itemIglesia = new javax.swing.JMenuItem();
        iteMinis = new javax.swing.JMenuItem();
        finanzas = new javax.swing.JMenu();
        itemreporteingresos = new javax.swing.JMenuItem();
        itemreporteegresos = new javax.swing.JMenuItem();
        usuarios = new javax.swing.JMenu();
        itemRegistro = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemrespaldo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuev.png"))); // NOI18N

        lblusuario.setEditable(false);

        botonUce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UCE1.png"))); // NOI18N

        botonJuve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MAR.png"))); // NOI18N
        botonJuve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJuveActionPerformed(evt);
            }
        });

        botonfemenil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MEN32P.png"))); // NOI18N

        botonshiret.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SHIER.png"))); // NOI18N

        botonmisiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mis.png"))); // NOI18N

        botondominical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DOMIN.png"))); // NOI18N

        botoncdi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CDI.png"))); // NOI18N

        botonOansa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/OANSA.png"))); // NOI18N

        panelPrincipal.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(idusuarioaminnis, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(lblusuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonUce, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonJuve, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonfemenil, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonshiret, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonmisiones, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botondominical, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botoncdi, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelPrincipal.setLayer(botonOansa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonJuve)
                            .addComponent(botonfemenil)
                            .addComponent(botonshiret)
                            .addComponent(botonmisiones)
                            .addComponent(botondominical)
                            .addComponent(botonOansa)
                            .addComponent(botoncdi))
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(idusuarioaminnis, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(botonUce)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idusuarioaminnis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(botonUce)
                        .addGap(18, 18, 18)
                        .addComponent(botonJuve)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonfemenil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonshiret)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonmisiones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botondominical)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonOansa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botoncdi)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );

        jButton1.setText("jButton1");

        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N

        jMenu1.setForeground(new java.awt.Color(0, 51, 51));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu-button_icon-icons.com_72989 (2).png"))); // NOI18N
        jMenu1.setText("MENÚ");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/log32.png"))); // NOI18N
        itemInfo.setText("Acerca de la Iglesia");
        jMenu1.add(itemInfo);

        jMenuBar1.add(jMenu1);

        registros.setForeground(new java.awt.Color(0, 51, 51));
        registros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lightbrown_books_folders_12307 (1).png"))); // NOI18N
        registros.setText("REGISTROS");
        registros.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemmiembros.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemmiembros.setForeground(new java.awt.Color(0, 0, 51));
        itemmiembros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon registro.png"))); // NOI18N
        itemmiembros.setText("REGISTRAR MIEMBROS");
        registros.add(itemmiembros);

        itemlideriglesia.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemlideriglesia.setForeground(new java.awt.Color(0, 0, 51));
        itemlideriglesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconnew1.png"))); // NOI18N
        itemlideriglesia.setText("REGISTRAR LÍDER DE LA IGLESIA");
        itemlideriglesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemlideriglesiaActionPerformed(evt);
            }
        });
        registros.add(itemlideriglesia);

        itemministerio.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemministerio.setForeground(new java.awt.Color(0, 0, 51));
        itemministerio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconnew2.png"))); // NOI18N
        itemministerio.setText("REGISTRAR LÍDER DEL MINISTERIO");
        registros.add(itemministerio);

        itemingresos.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemingresos.setForeground(new java.awt.Color(0, 0, 51));
        itemingresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlibro.png"))); // NOI18N
        itemingresos.setText("REGISTRO DE INGRESOS");
        registros.add(itemingresos);

        itemegresos.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemegresos.setForeground(new java.awt.Color(0, 0, 51));
        itemegresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4288584andbusinessfinancepersonalportfolioprofileresume-115772_115741 (1).png"))); // NOI18N
        itemegresos.setText("REGISTRO DE EGRESOS");
        itemegresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemegresosActionPerformed(evt);
            }
        });
        registros.add(itemegresos);

        jMenuBar1.add(registros);

        itemMembrecia.setForeground(new java.awt.Color(0, 51, 51));
        itemMembrecia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/libraryfolder_books_binders_2688 (1).png"))); // NOI18N
        itemMembrecia.setText("MEMBRECÍA");
        itemMembrecia.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemLista.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemLista.setForeground(new java.awt.Color(0, 0, 51));
        itemLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistama.png"))); // NOI18N
        itemLista.setText("LISTA DE MIEMBROS BAUTIZADOS");
        itemMembrecia.add(itemLista);

        jMenuBar1.add(itemMembrecia);

        liderazgo.setForeground(new java.awt.Color(0, 51, 51));
        liderazgo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconliders.png"))); // NOI18N
        liderazgo.setText("LIDERAZGO");
        liderazgo.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemIglesia.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemIglesia.setForeground(new java.awt.Color(0, 0, 51));
        itemIglesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconLiderMarron.png"))); // NOI18N
        itemIglesia.setText("LISTA DE LÍDERES DE LA IGLESIA");
        itemIglesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIglesiaActionPerformed(evt);
            }
        });
        liderazgo.add(itemIglesia);

        iteMinis.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        iteMinis.setForeground(new java.awt.Color(0, 0, 51));
        iteMinis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconLiderBlue.png"))); // NOI18N
        iteMinis.setText("LISTA DE LÍDERES DE MINISTERIOS");
        iteMinis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iteMinisActionPerformed(evt);
            }
        });
        liderazgo.add(iteMinis);

        jMenuBar1.add(liderazgo);

        finanzas.setForeground(new java.awt.Color(0, 51, 51));
        finanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4288584andbusinessfinancepersonalportfolioprofileresume-115772_115741 (1).png"))); // NOI18N
        finanzas.setText("FINANZAS");
        finanzas.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemreporteingresos.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemreporteingresos.setForeground(new java.awt.Color(0, 0, 51));
        itemreporteingresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistblue.png"))); // NOI18N
        itemreporteingresos.setText("REPORTES DE INGRESOS");
        finanzas.add(itemreporteingresos);

        itemreporteegresos.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemreporteegresos.setForeground(new java.awt.Color(0, 0, 51));
        itemreporteegresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistmarron.png"))); // NOI18N
        itemreporteegresos.setText("REPORTES DE EGRESOS");
        finanzas.add(itemreporteegresos);

        jMenuBar1.add(finanzas);

        usuarios.setForeground(new java.awt.Color(0, 51, 51));
        usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/users_clients_group_16774.png"))); // NOI18N
        usuarios.setText("USUARIO");
        usuarios.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemRegistro.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemRegistro.setForeground(new java.awt.Color(0, 0, 51));
        itemRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ivonLocked.png"))); // NOI18N
        itemRegistro.setText("REGISTRO DE USUARIOS");
        usuarios.add(itemRegistro);

        jMenuBar1.add(usuarios);

        jMenu2.setForeground(new java.awt.Color(0, 51, 51));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/database.png"))); // NOI18N
        jMenu2.setText("RESPALDO");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N

        itemrespaldo.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 12)); // NOI18N
        itemrespaldo.setForeground(new java.awt.Color(0, 0, 51));
        itemrespaldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icondata.png"))); // NOI18N
        itemrespaldo.setText("RESPALDO DE BASE DE DATOS");
        jMenu2.add(itemrespaldo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemlideriglesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemlideriglesiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemlideriglesiaActionPerformed

    private void itemegresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemegresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemegresosActionPerformed

    private void itemIglesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIglesiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIglesiaActionPerformed

    private void iteMinisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iteMinisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iteMinisActionPerformed

    private void botonJuveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJuveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonJuveActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonJuve;
    public javax.swing.JButton botonOansa;
    public javax.swing.JButton botonUce;
    public javax.swing.JButton botoncdi;
    public javax.swing.JButton botondominical;
    public javax.swing.JButton botonfemenil;
    public javax.swing.JButton botonmisiones;
    public javax.swing.JButton botonshiret;
    private javax.swing.JMenu finanzas;
    public javax.swing.JTextField idusuarioaminnis;
    public javax.swing.JMenuItem iteMinis;
    public javax.swing.JMenuItem itemIglesia;
    public javax.swing.JMenuItem itemInfo;
    public javax.swing.JMenuItem itemLista;
    public javax.swing.JMenu itemMembrecia;
    public javax.swing.JMenuItem itemRegistro;
    public javax.swing.JMenuItem itemegresos;
    public javax.swing.JMenuItem itemingresos;
    public javax.swing.JMenuItem itemlideriglesia;
    public javax.swing.JMenuItem itemmiembros;
    public javax.swing.JMenuItem itemministerio;
    public javax.swing.JMenuItem itemreporteegresos;
    public javax.swing.JMenuItem itemreporteingresos;
    public javax.swing.JMenuItem itemrespaldo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollBar jScrollBar1;
    public javax.swing.JTextField lblusuario;
    private javax.swing.JMenu liderazgo;
    public javax.swing.JDesktopPane panelPrincipal;
    private javax.swing.JMenu registros;
    private javax.swing.JMenu usuarios;
    // End of variables declaration//GEN-END:variables
}
