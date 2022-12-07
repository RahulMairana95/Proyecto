/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author RAHUL
 */
public class VistaLider extends javax.swing.JInternalFrame {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
   
    /**
     * Creates new form VistaLider
     */
    public VistaLider() {
        initComponents();
        //con=consql.conectando();
        //combo();
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
        txtpaterno = new javax.swing.JTextField();
        txtmaterno = new javax.swing.JTextField();
        txtdocumento = new javax.swing.JTextField();
        boxcargo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        fechainicio = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablalider = new javax.swing.JTable();
        botonbuscar = new javax.swing.JButton();
        botoneditar = new javax.swing.JButton();
        botoneliminar = new javax.swing.JButton();
        botonreporte = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        botonnuevo = new javax.swing.JButton();
        botonagregar = new javax.swing.JButton();
        botoncancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        fechafin = new com.toedter.calendar.JDateChooser();
        boxnombre = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jLabel2.setText("Nombre del líder:");

        jLabel3.setText("Apellido Paterno:");

        jLabel4.setText("Apellido Materno:");

        jLabel5.setText("N° de documento:");

        jLabel6.setText("Cargo:");

        txtpaterno.setEditable(false);

        txtmaterno.setEditable(false);
        txtmaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaternoActionPerformed(evt);
            }
        });

        txtdocumento.setEditable(false);

        boxcargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ANCIANO", "DIACONO", "DIACONIZA", "TESORERO DE DIEZMOS", "TESORERO DE OFRENDAS", "SECRETARIO" }));

        jLabel7.setText("Inicio de Gestion:");

        tablalider.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido P", "Apellido M", "C.I.", "Cargo", "Gestion Inicio", "Fin Gestion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablalider);

        botonbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconbuscar.png"))); // NOI18N
        botonbuscar.setText("BUSCAR");

        botoneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconeditar.png"))); // NOI18N
        botoneditar.setText("MODIFICAR");

        botoneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconeliminar.png"))); // NOI18N
        botoneliminar.setText("ELIMINAR");

        botonreporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconexportar2.png"))); // NOI18N
        botonreporte.setText("EXPORTAR");
        botonreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonreporteActionPerformed(evt);
            }
        });

        jLabel8.setText("Buscar:");

        botonnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon nuevo.png"))); // NOI18N
        botonnuevo.setText("NUEVO");

        botonagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconnew4.png"))); // NOI18N
        botonagregar.setText("REGISTRAR");

        botoncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconcance.png"))); // NOI18N
        botoncancelar.setText("CANCELAR");

        jLabel10.setText("Fin de Gestion:");

        boxnombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxnombreItemStateChanged(evt);
            }
        });
        boxnombre.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                boxnombrePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel11.setText("Nombre:");

        txtnombre.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonnuevo)
                        .addGap(12, 12, 12)
                        .addComponent(botonagregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botoncancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechainicio, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(boxcargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(fechafin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtpaterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(txtmaterno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre)
                            .addComponent(txtdocumento))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonbuscar)
                        .addGap(30, 30, 30)
                        .addComponent(botoneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonreporte))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(botonbuscar)
                    .addComponent(botoneditar)
                    .addComponent(botoneliminar)
                    .addComponent(botonreporte)
                    .addComponent(jLabel8)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtmaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel6)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechainicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonnuevo)
                            .addComponent(botonagregar)
                            .addComponent(botoncancelar))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonreporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonreporteActionPerformed

    private void txtmaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaternoActionPerformed

    private void boxnombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxnombreItemStateChanged

    }//GEN-LAST:event_boxnombreItemStateChanged

    private void boxnombrePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_boxnombrePopupMenuWillBecomeInvisible
        String tmp=(String) boxnombre.getSelectedItem();
        String [] aux=tmp.split("-");
        String idmen=aux[0];
        
        String sqll="select * from membrecia where idmembrecia=?";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(sqll);
            pres.setString(1, idmen);
            rs=pres.executeQuery();
            if(rs.next()){
                String add1=rs.getString("nombre");
                txtnombre.setText(add1);
                
                System.out.println("LIDERRRRR"+ add1);
                
                String add2=rs.getString("apellidop");
                txtpaterno.setText(add2);
                String add3=rs.getString("apellidom");
                txtmaterno.setText(add3);
                String add4=rs.getString("numdocumento");
                txtdocumento.setText(add4);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_boxnombrePopupMenuWillBecomeInvisible
    /*private void combo(){
        //List<Membrecia> listamem = new ArrayList<>();
        try {
            con=consql.conectando();
            String sqlk="select * from membrecia";
            pres=con.prepareStatement(sqlk);
            rs=pres.executeQuery();
            
            System.out.println("MOSTRAR" + rs);
            
            while(rs.next()){
                //Membrecia mem=new Membrecia();
                //mem.setNombre(rs.getString(2));
                String idd=rs.getString("idmembrecia");
                String nombre=rs.getString("nombre");
                String app=rs.getString("apellidop");
                
                //listamem.add(mem);
                //boxnombre.addItem(" " + "-"+"Seleccione nombre del Lider");
                //for(int i=0;i<nom.size();i++){
                boxnombre.addItem(idd+"-"+nombre+" "+app);
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonagregar;
    public javax.swing.JButton botonbuscar;
    public javax.swing.JButton botoncancelar;
    public javax.swing.JButton botoneditar;
    public javax.swing.JButton botoneliminar;
    public javax.swing.JButton botonnuevo;
    public javax.swing.JButton botonreporte;
    public javax.swing.JComboBox<String> boxcargo;
    public javax.swing.JComboBox<String> boxnombre;
    public com.toedter.calendar.JDateChooser fechafin;
    public com.toedter.calendar.JDateChooser fechainicio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablalider;
    public javax.swing.JTextField txtbuscar;
    public javax.swing.JTextField txtdocumento;
    public javax.swing.JTextField txtmaterno;
    public javax.swing.JTextField txtnombre;
    public javax.swing.JTextField txtpaterno;
    // End of variables declaration//GEN-END:variables

    
}
