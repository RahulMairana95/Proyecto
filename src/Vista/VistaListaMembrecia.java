/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.event.FocusEvent;

/**
 *
 * @author RAHUL
 */
public class VistaListaMembrecia extends javax.swing.JInternalFrame {
    //ExcelExpo exp;
    /**
     * Creates new form VistaMembrecia
     */
    public VistaListaMembrecia() {
        initComponents();
        
        /*javax.swing.SwingUtilities.invokeLater(() -> {
        txtbusqueda.setText("Buscar por nombres, apellidos y CI");
        txtbusqueda.setForeground(Color.GRAY);
        
        botonbuscar.requestFocusInWindow();

        txtbusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtbusqueda.getText().equals("Buscar por nombres, apellidos y CI")) {
                    txtbusqueda.setText("");
                    txtbusqueda.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtbusqueda.getText().trim().isEmpty()) {
                    txtbusqueda.setText("Buscar por nombres, apellidos y CI");
                    txtbusqueda.setForeground(Color.GRAY);
                }
            }
        });
    });*/
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablalistar = new javax.swing.JTable();
        btnexportar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        botonbuscar = new javax.swing.JButton();
        botonlistar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        datehappyini = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        datehappyfin = new com.toedter.calendar.JDateChooser();
        botonhappy = new javax.swing.JButton();
        txtbusqueda = new javax.swing.JTextField();

        setClosable(true);

        tablalistar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "APELLIDO P.", "APELLIDO M.", "C.I.", "FECHA NACIMIENTO", "ESTADO CIVIL", "FECHA CONVERSION", "FECHA BAUTIZO", "TALENTOS", "DONES", "ACTIVO", "DIRECCIÓN", "TELÉFONO", "NOMBRE DE REFERENCIA", "NÚMERO DE REFERENCIA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablalistar);

        btnexportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconexcel2.png"))); // NOI18N
        btnexportar.setText("EXPORTAR LISTA");
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        jLabel13.setText("BUSCAR:");

        botonbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconbuscarazul.png"))); // NOI18N
        botonbuscar.setText("BUSCAR");

        botonlistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistaboton.png"))); // NOI18N
        botonlistar.setText("RECARGAR");
        botonlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonlistarActionPerformed(evt);
            }
        });

        jLabel1.setText("DESDE:");

        jLabel2.setText("HASTA:");

        botonhappy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconhappy.png"))); // NOI18N
        botonhappy.setText("CUMPLEAÑOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonbuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonlistar)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datehappyini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datehappyfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonhappy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnexportar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonbuscar)
                            .addComponent(botonlistar)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnexportar)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(botonhappy)
                            .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datehappyfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datehappyini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        /*try {
            exp= new ExcelExpo();
            exp.Exportar(tablalistar);// llamando metodo exportar
            
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    }//GEN-LAST:event_btnexportarActionPerformed

    private void botonlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonlistarActionPerformed
        //DefaultTableModel md=new DefaultTableModel();
        //ResultSet rs
    }//GEN-LAST:event_botonlistarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonbuscar;
    public javax.swing.JButton botonhappy;
    public javax.swing.JButton botonlistar;
    public javax.swing.JButton btnexportar;
    public com.toedter.calendar.JDateChooser datehappyfin;
    public com.toedter.calendar.JDateChooser datehappyini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablalistar;
    public javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}
