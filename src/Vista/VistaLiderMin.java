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
public class VistaLiderMin extends javax.swing.JInternalFrame {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
   
    /**
     * Creates new form VistaLider
     */
    public VistaLiderMin() {
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpaterno = new javax.swing.JTextField();
        txtdocumento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fechainicio = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablamin = new javax.swing.JTable();
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
        botonlistar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        boxministerio = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmaterno = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        boxcargo = new javax.swing.JComboBox<>();

        setClosable(true);

        jLabel2.setText("Miembros:");

        jLabel3.setText("Apellido Paterno:");

        jLabel5.setText("N° de documento:");

        jLabel6.setText("Cargo:");

        txtpaterno.setEditable(false);

        txtdocumento.setEditable(false);

        jLabel7.setText("Inicio de Gestion:");

        tablamin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "C.I.", "TELÉFONO", "MINISTERIO", "CARGO", "INICIO GESTIÓN", "FIN GESTIÓN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablamin);
        if (tablamin.getColumnModel().getColumnCount() > 0) {
            tablamin.getColumnModel().getColumn(8).setResizable(false);
        }

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

        botonlistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconlistaazul.png"))); // NOI18N
        botonlistar.setText("RECARGAR");

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("DATOS DEL LIDER DE MINISTERIO");

        jLabel4.setText("Ministerio:");

        jLabel9.setText("Apellido Materno:");

        txtmaterno.setEditable(false);
        txtmaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaternoActionPerformed(evt);
            }
        });

        txttelefono.setEditable(false);

        jLabel12.setText("Teléfono:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boxnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fechainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtdocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(txtmaterno, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(boxcargo, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                                        .addComponent(boxministerio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 143, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonbuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonlistar)
                                .addGap(179, 179, 179))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonagregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoncancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botoneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonreporte)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonlistar)
                    .addComponent(botonbuscar)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boxministerio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(boxcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechainicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechafin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonnuevo)
                    .addComponent(botonagregar)
                    .addComponent(botoncancelar)
                    .addComponent(botonreporte)
                    .addComponent(botoneliminar)
                    .addComponent(botoneditar))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void boxnombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxnombreItemStateChanged

    }//GEN-LAST:event_boxnombreItemStateChanged

    private void boxnombrePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_boxnombrePopupMenuWillBecomeInvisible
        String tmp=(String) boxnombre.getSelectedItem();
        String [] aux=tmp.split("-");
        String idmen=aux[0];
        
        String sqll="SELECT * FROM membrecia WHERE idmembrecia=?";
        
        try {
            con=consql.getConnection();
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

    private void txtmaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaternoActionPerformed
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
    public javax.swing.JButton botonlistar;
    public javax.swing.JButton botonnuevo;
    public javax.swing.JButton botonreporte;
    public javax.swing.JComboBox<String> boxcargo;
    public javax.swing.JComboBox<String> boxministerio;
    public javax.swing.JComboBox<String> boxnombre;
    public com.toedter.calendar.JDateChooser fechafin;
    public com.toedter.calendar.JDateChooser fechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    public javax.swing.JTable tablamin;
    public javax.swing.JTextField txtbuscar;
    public javax.swing.JTextField txtdocumento;
    public javax.swing.JTextField txtmaterno;
    public javax.swing.JTextField txtnombre;
    public javax.swing.JTextField txtpaterno;
    public javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

    
}
