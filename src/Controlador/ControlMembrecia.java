/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RAHUL
 */
public class ControlMembrecia extends MouseAdapter implements ActionListener{
    VistaMembrecia vistaMembrecia=new VistaMembrecia();
    MembreciaDAO membreciaDAO;
    Membrecia membrecia=new Membrecia();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Membrecia> lista;
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    ConverMayus con;
    
    ExportarEnExcel excel;
    
    public ControlMembrecia(VistaMembrecia vistaMembrecia, MembreciaDAO membreciaDAO){
        System.out.println("listando");
        this.vistaMembrecia=vistaMembrecia;
        this.membreciaDAO=membreciaDAO;
        
        listar();
        /////CARGAR A COMBOS
        cargarcomboTalentos();
        combodones();
        comboestado();
        comboactivo();
        //mayus();
        inhabilitar();
        ajustarAnchoColumnas(vistaMembrecia.tablademiembros);
        
        
        //EVENTO DE BOTONES
        this.vistaMembrecia.botonagregar.addActionListener(this);
        this.vistaMembrecia.botonmodificar.addActionListener(this);
        this.vistaMembrecia.botoneliminar.addActionListener(this);
        this.vistaMembrecia.botoncancelar.addActionListener(this);
        this.vistaMembrecia.botonnuevo.addActionListener(this);
        this.vistaMembrecia.botonreporte.addActionListener(this);
        this.vistaMembrecia.btnbuscar1.addActionListener(this);
        this.vistaMembrecia.botonlistar.addActionListener(this);
        
        this.vistaMembrecia.txtdocumento.addActionListener(this);
        
        //MODIFICAR Y ELIMINAR
        this.vistaMembrecia.tablademiembros.addMouseListener(this);
        
        
        
        // 👇 Placeholder en el campo de texto de búsqueda
        vistaMembrecia.txtbuscar.setText("Buscar por nombres, apellidos o CI");
        vistaMembrecia.txtbuscar.setForeground(Color.GRAY);

        vistaMembrecia.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaMembrecia.txtbuscar.getText().equals("Buscar por nombres, apellidos o CI")) {
                    vistaMembrecia.txtbuscar.setText("");
                    vistaMembrecia.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaMembrecia.txtbuscar.getText().trim().isEmpty()) {
                    vistaMembrecia.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                    vistaMembrecia.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
        
        vistaMembrecia.txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

                // Solo permitir dígitos y tecla borrar (backspace)
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume(); // No se escribe el carácter
                    JOptionPane.showMessageDialog(null, "Solo escribe números.");
                }

                // Limitar a 8 caracteres
                if (vistaMembrecia.txttelefono.getText().length() >= 8) {
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "Solo debes ingresas 8 dígitos.");
                }
            }
        });
        
        


    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaMembrecia.botonagregar==ae.getSource()){
            try {
                agreagarNuevo();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR");
            }
        }else if(vistaMembrecia.botonmodificar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL MODIFICAR");
            }
        }else if(vistaMembrecia.botoneliminar==ae.getSource()){
            try {
                eliminarMiembros();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
            }
        }else if(vistaMembrecia.botoncancelar==ae.getSource()){
            try {
                limpiarventanas();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                inhabilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL CANCELAR");
            }
        }else if(vistaMembrecia.botonnuevo==ae.getSource()){
            try {
                limpiarventanas();
                habilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL HABILITAR");
            }
        }
        else if(vistaMembrecia.btnbuscar1==ae.getSource()){
            String texto = vistaMembrecia.txtbuscar.getText().trim();
            if(texto.equals("Buscar por nombres, apellidos o CI") || texto.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres, Apellidos o Número de Documento para que la Búsqueda sea precisa.");
                return;
            }
            try{
                List<Membrecia> resultado = membreciaDAO.buscarmiembros(texto);
                llenarTablaMembrecia(resultado);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron miembros con esos datos.");
                }
                
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR");
            }
        }else if(vistaMembrecia.botonlistar==ae.getSource()){
            try{
                vistaMembrecia.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                vistaMembrecia.txtbuscar.setForeground(Color.GRAY);
                
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();  
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL MOSTRAR LISTA");
            }
        }
        else if(vistaMembrecia.botonreporte==ae.getSource()){
            try{
                exportars();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            /// Habilitar botones
            vistaMembrecia.botonmodificar.setEnabled(true);
            vistaMembrecia.botoneliminar.setEnabled(true);
            vistaMembrecia.botonreporte.setEnabled(true);
            vistaMembrecia.botoncancelar.setEnabled(true);
            habilitarClic();
            
            id=lista.get(fila).getIdmembrecia();
            
            String nom=vistaMembrecia.tablademiembros.getValueAt(fila, 0).toString();
            String pat=vistaMembrecia.tablademiembros.getValueAt(fila, 1).toString();
            String mat=vistaMembrecia.tablademiembros.getValueAt(fila, 2).toString();
            String num=vistaMembrecia.tablademiembros.getValueAt(fila, 3).toString();
            String fnac=vistaMembrecia.tablademiembros.getValueAt(fila, 4).toString();
            String civ=vistaMembrecia.tablademiembros.getValueAt(fila, 5).toString();
            String fcon=vistaMembrecia.tablademiembros.getValueAt(fila, 6).toString();
            String fbau=vistaMembrecia.tablademiembros.getValueAt(fila, 7).toString();
            String tal=vistaMembrecia.tablademiembros.getValueAt(fila, 8).toString();
            String don=vistaMembrecia.tablademiembros.getValueAt(fila, 9).toString();
            String act=vistaMembrecia.tablademiembros.getValueAt(fila, 10).toString();
            String dir=vistaMembrecia.tablademiembros.getValueAt(fila, 11).toString();
            String tel=vistaMembrecia.tablademiembros.getValueAt(fila, 12).toString();
            String nref=vistaMembrecia.tablademiembros.getValueAt(fila, 13).toString();
            String ref=vistaMembrecia.tablademiembros.getValueAt(fila, 14).toString();
            
            
            try {
                vistaMembrecia.txtnombre.setText(nom);
                vistaMembrecia.txtpaterno.setText(pat);
                vistaMembrecia.txtmaterno.setText(mat);
                vistaMembrecia.txtdocumento.setText(num);
                //System.out.println("parseado aqui");

                vistaMembrecia.datenacimiento.setDate(sdf.parse(fnac));
                
                vistaMembrecia.boxestado.setSelectedItem(civ);
                //System.out.println("parseado");
                vistaMembrecia.datevonversion.setDate(sdf.parse(fcon));
                
                vistaMembrecia.datebautizo.setDate(sdf.parse(fbau));
                
                vistaMembrecia.boxtalentos.setSelectedItem(tal);
                vistaMembrecia.boxdones.setSelectedItem(don);
                vistaMembrecia.boxactivo.setSelectedItem(act);
                vistaMembrecia.txtdireccion.setText(dir);
                vistaMembrecia.txttelefono.setText(tel);
                vistaMembrecia.txtnomfererencia.setText(nref);
                vistaMembrecia.txtnumreferencia.setText(ref);
            } catch (ParseException err) {
                //System.out.println("errooooo"+ err);
            }
        }
    }

    public void cargarcomboTalentos() {
        vistaMembrecia.boxtalentos.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        //vistaEgreso.boxtipo.addItem("Seleccione un tipo"); // index 0
        vistaMembrecia.boxtalentos.addItem("Sin especificar");
        vistaMembrecia.boxtalentos.addItem("Música");
        vistaMembrecia.boxtalentos.addItem("Deportivo");
        vistaMembrecia.boxtalentos.addItem("Creativo");
        vistaMembrecia.boxtalentos.addItem("Gestión de Personal");
        vistaMembrecia.boxtalentos.addItem("Organizacional");
        vistaMembrecia.boxtalentos.addItem("Gastronomía");
        vistaMembrecia.boxtalentos.addItem("Área de Ventas");
        vistaMembrecia.boxtalentos.addItem("Manejo de Inversiones");
        vistaMembrecia.boxtalentos.addItem("Idiomas");
        vistaMembrecia.boxtalentos.addItem("Comunicación");
        vistaMembrecia.boxtalentos.addItem("Cientifico");
        vistaMembrecia.boxtalentos.addItem("Actuación");
    }
    
    public void combodones(){
        vistaMembrecia.boxdones.removeAllItems();
        
        vistaMembrecia.boxdones.addItem("Sin especificar");
        vistaMembrecia.boxdones.addItem("Palabra de Sabiduría");
        vistaMembrecia.boxdones.addItem("Palabra de Conocimiento");
        vistaMembrecia.boxdones.addItem("Fe");
        vistaMembrecia.boxdones.addItem("Dones de Sanidad");
        vistaMembrecia.boxdones.addItem("Poder de Milagros");
        vistaMembrecia.boxdones.addItem("Profecía");
        vistaMembrecia.boxdones.addItem("Discernimiento de Espiritus");
        vistaMembrecia.boxdones.addItem("Diversas clases de Lenguas");
        vistaMembrecia.boxdones.addItem("Interpretación de Lenguas");
        vistaMembrecia.boxdones.addItem("Servicio");
        vistaMembrecia.boxdones.addItem("Enseñanza");
        vistaMembrecia.boxdones.addItem("Exhortación");
        vistaMembrecia.boxdones.addItem("Dar");
        vistaMembrecia.boxdones.addItem("Liderazgo");
        vistaMembrecia.boxdones.addItem("Misericordia");
    }
    
    public void comboactivo(){
        vistaMembrecia.boxactivo.removeAllItems();
        
        vistaMembrecia.boxactivo.addItem("Activo");
        vistaMembrecia.boxactivo.addItem("Inactivo");
    }
    
    public void comboestado(){
        vistaMembrecia.boxestado.removeAllItems();
        
        vistaMembrecia.boxestado.addItem("Casado");
        vistaMembrecia.boxestado.addItem("Soltero");
        vistaMembrecia.boxestado.addItem("Viudo");
        vistaMembrecia.boxestado.addItem("Viuda");
        vistaMembrecia.boxestado.addItem("Concubino");
    }
    
    public void listar(){
        lista=membreciaDAO.listarMembrecia();
        tablaModel=(DefaultTableModel) vistaMembrecia.tablademiembros.getModel();
        Object obj[]=new Object[15];
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=sdf.format(lista.get(i).getFechanacimiento());
            obj[5]=lista.get(i).getEstadocivil();
            obj[6]=sdf.format(lista.get(i).getFechaconversion());
            obj[7]=sdf.format(lista.get(i).getFechabautizo());
            obj[8]=lista.get(i).getTalentos();
            obj[9]=lista.get(i).getDones();
            obj[10]=lista.get(i).getActivo();
            obj[11]=lista.get(i).getDireccion();
            obj[12]=(lista.get(i).getTelefono() == 0) ? "--" : lista.get(i).getTelefono();
            obj[13]=lista.get(i).getNomreferencia();
            obj[14]=lista.get(i).getNumreferencia();
            
            tablaModel.addRow(obj);
        }
        vistaMembrecia.tablademiembros.setModel(tablaModel);
    }
    public void agreagarNuevo(){
        if(vistaMembrecia.txtnombre.getText().trim().isEmpty()||
                vistaMembrecia.txtpaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtmaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtdocumento.getText().trim().isEmpty()||
                vistaMembrecia.datenacimiento.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxestado.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.datevonversion.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.datebautizo.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxtalentos.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxdones.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxactivo.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.txtdireccion.getText().trim().isEmpty()||
                //vistaMembrecia.txttelefono.getText().trim().isEmpty()||
                vistaMembrecia.txtnomfererencia.getText().trim().isEmpty()||
                vistaMembrecia.txtnumreferencia.getText().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            Membrecia membrecia = new Membrecia();
            // Validar duplicado por número de documento
            String documento = vistaMembrecia.txtdocumento.getText().trim();
            if (membreciaDAO.existeDocumento(documento)) {
                JOptionPane.showMessageDialog(null, "Este miembro ya está registrado con el mismo número de documento.");
                return;
            }
            System.out.println("probando insert");
            
            //membrecia.setIdmembrecia(Integer.parseInt(vistaMembrecia.txtnombre.getText()));
            String nombre = vistaMembrecia.txtnombre.getText().trim();
            membrecia.setNombre(capitalizarNombre(nombre));
            
            membrecia.setApellidop(vistaMembrecia.txtpaterno.getText());
            membrecia.setApellidom(vistaMembrecia.txtmaterno.getText());
            membrecia.setNumdocumento(vistaMembrecia.txtdocumento.getText());
            
            Calendar calenn, calenc,calenb;
            int dian,mesn,yearn,diac,mesc,yearc,diab,mesb,yearb;
            calenn=vistaMembrecia.datenacimiento.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            membrecia.setFechanacimiento(new Date(yearn, mesn, dian));
            
            membrecia.setEstadocivil((String) vistaMembrecia.boxestado.getSelectedItem());
            
            calenc=vistaMembrecia.datevonversion.getCalendar();
            diac=calenc.get(Calendar.DAY_OF_MONTH);
            mesc=calenc.get(Calendar.MONTH);
            yearc=calenc.get(Calendar.YEAR)-1900;
            membrecia.setFechaconversion(new Date(yearc, mesc, diac));
            
            calenb=vistaMembrecia.datebautizo.getCalendar();
            diab=calenb.get(Calendar.DAY_OF_MONTH);
            mesb=calenb.get(Calendar.MONTH);
            yearb=calenb.get(Calendar.YEAR)-1900;
            membrecia.setFechabautizo(new Date(yearb, mesb, diab));
            
            membrecia.setTalentos((String)vistaMembrecia.boxtalentos.getSelectedItem());
            membrecia.setDones((String)vistaMembrecia.boxdones.getSelectedItem());
            membrecia.setActivo((String) vistaMembrecia.boxactivo.getSelectedItem());
            membrecia.setDireccion(vistaMembrecia.txtdireccion.getText());
            //membrecia.setTelefono(Integer.parseInt(vistaMembrecia.txttelefono.getText()));
            // Validar teléfono: si está vacío, colocar 0
            String telTexto = vistaMembrecia.txttelefono.getText().trim();
            if (telTexto.isEmpty()) {
                membrecia.setTelefono(0); // Valor por defecto si no se ingresa teléfono
            } else if(!telTexto.matches("\\d{8}")){
                JOptionPane.showMessageDialog(null, "El número de teléfono debe tener exactamente 8 dígitos numéricos.");
                return;
            } else {
                
                    membrecia.setTelefono(Integer.parseInt(telTexto));
                
            }
            
            String nomref = vistaMembrecia.txtnomfererencia.getText().trim();
            membrecia.setNomreferencia(capitalizarNombre(nomref));
            
            membrecia.setNumreferencia(Integer.parseInt(vistaMembrecia.txtnumreferencia.getText()));
            
            membreciaDAO.agregar(membrecia);
        }
    }
    public void modificar(){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        System.out.println("modificar miembros");
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            if(vistaMembrecia.txtnombre.getText().trim().isEmpty()||
                vistaMembrecia.txtpaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtmaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtdocumento.getText().trim().isEmpty()||
                vistaMembrecia.datenacimiento.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxestado.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia. datevonversion.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.datebautizo.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxtalentos.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxdones.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxactivo.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.txtdireccion.getText().trim().isEmpty()||
                //vistaMembrecia.txttelefono.getText().trim().isEmpty()||
                vistaMembrecia.txtnomfererencia.getText().trim().isEmpty()||
                vistaMembrecia.txtnumreferencia.getText().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            id=lista.get(fila).getIdmembrecia();
            
            String nombre=vistaMembrecia.txtnombre.getText();
            String paterno=vistaMembrecia.txtpaterno.getText();
            String materno=vistaMembrecia.txtmaterno.getText();
            String num=vistaMembrecia.txtdocumento.getText();
            
            Calendar calenn, calenc,calenb;
            int dian,mesn,yearn,diac,mesc,yearc,diab,mesb,yearb;
            calenn=vistaMembrecia.datenacimiento.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            Date fnacim=(new Date(yearn, mesn, dian));
            
            String civil=(String) vistaMembrecia.boxestado.getSelectedItem();
            
            calenc=vistaMembrecia.datevonversion.getCalendar();
            diac=calenc.get(Calendar.DAY_OF_MONTH);
            mesc=calenc.get(Calendar.MONTH);
            yearc=calenc.get(Calendar.YEAR)-1900;
            Date fconver=(new Date(yearc, mesc, diac));
            
            calenb=vistaMembrecia.datebautizo.getCalendar();
            diab=calenb.get(Calendar.DAY_OF_MONTH);
            mesb=calenb.get(Calendar.MONTH);
            yearb=calenb.get(Calendar.YEAR)-1900;
            Date fbaut=(new Date(yearb, mesb, diab));
            
            String tale=(String) vistaMembrecia.boxtalentos.getSelectedItem();
            String dones=(String) vistaMembrecia.boxdones.getSelectedItem();
            String acti=(String) vistaMembrecia.boxactivo.getSelectedItem();
            String dire=vistaMembrecia.txtdireccion.getText();
            String tel=vistaMembrecia.txttelefono.getText().trim();
            
            String nomref=vistaMembrecia.txtnomfererencia.getText();
            String numref=vistaMembrecia.txtnumreferencia.getText();
            
                System.out.println("id " +id );
            
            membrecia.setIdmembrecia(id);
            membrecia.setNombre(capitalizarNombre(nombre));
            membrecia.setApellidop(paterno);
            membrecia.setApellidom(materno);
            membrecia.setNumdocumento(num);
            
            membrecia.setFechanacimiento(fnacim);
            membrecia.setEstadocivil(civil);
            membrecia.setFechaconversion(fconver);
            membrecia.setFechabautizo(fbaut);
            
            membrecia.setTalentos(tale);
            membrecia.setDones(dones);
            membrecia.setActivo(acti);
            membrecia.setDireccion(dire);
            
             int telefono = 0;
             if (!tel.isEmpty()){
                 try {
                    telefono = Integer.parseInt(tel);  
                 } catch (NumberFormatException e) {
                     JOptionPane.showMessageDialog(null, "El número de teléfono no es válido.");
                        return;
                 }
                
             }
             
            membrecia.setTelefono(telefono);
            
            membrecia.setNomreferencia(capitalizarNombre(nomref));
            
            
            membrecia.setNumreferencia(Integer.parseInt(numref));
            
                System.out.println("probando modificar");
            
            membreciaDAO.modificar(membrecia);
            }
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void eliminar(){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            membreciaDAO.eliminar(id);
            System.out.println("eliminando");
        }
    }
    ///////ELIMINAR REGISTROS
    public void eliminarMiembros() {
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este ingreso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        MembreciaDAO dao = new MembreciaDAO();
        boolean eliminado = dao.eliminar(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Ingreso eliminado correctamente");
            id = 0;             // resetea ID seleccionado
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el ingreso");
        }
    }
    public void exportars(){
        try {
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaMembrecia.tablademiembros);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void limpiarventanas(){
        
        vistaMembrecia.txtnombre.setText("");
        vistaMembrecia.txtpaterno.setText("");
        vistaMembrecia.txtmaterno.setText("");
        vistaMembrecia.txtdocumento.setText("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaMembrecia.datenacimiento.setDate(fechaactual);
        
        vistaMembrecia.boxestado.setSelectedItem("");
       
        vistaMembrecia.datevonversion.setDate(fechaactual);
        
        vistaMembrecia.datebautizo.setDate(fechaactual);
        
        vistaMembrecia.boxtalentos.setSelectedItem("");
        vistaMembrecia.boxdones.setSelectedItem("");
        vistaMembrecia.boxactivo.setSelectedItem("");
        vistaMembrecia.txtdireccion.setText("");
        vistaMembrecia.txttelefono.setText("");
        vistaMembrecia.txtnomfererencia.setText("");
        vistaMembrecia.txtnumreferencia.setText("");
    }
    public void inhabilitar(){
        vistaMembrecia.botonagregar.setEnabled(false);
        vistaMembrecia.botoncancelar.setEnabled(false);
        vistaMembrecia.botoneliminar.setEnabled(false);
        vistaMembrecia.botonmodificar.setEnabled(false);
        vistaMembrecia.botonreporte.setEnabled(false);
        //vistaMembrecia.btnbuscar1.setEnabled(false);
        //vistaMembrecia.botonlistar.setEnabled(false);
        
        vistaMembrecia.txtnombre.setEnabled(false);
        vistaMembrecia.txtpaterno.setEnabled(false);
        vistaMembrecia.txtmaterno.setEnabled(false);
        vistaMembrecia.txtdocumento.setEnabled(false);
        vistaMembrecia.txtdireccion.setEnabled(false);
        vistaMembrecia.txttelefono.setEnabled(false);
        vistaMembrecia.txtnomfererencia.setEnabled(false);
        vistaMembrecia.txtnumreferencia.setEnabled(false);
        //vistaMembrecia.txtbuscar.setEnabled(false);
        
        vistaMembrecia.boxtalentos.setEnabled(false);
        vistaMembrecia.boxdones.setEnabled(false);
        vistaMembrecia.boxactivo.setEnabled(false);
        vistaMembrecia.boxestado.setEnabled(false);
        vistaMembrecia.datebautizo.setEnabled(false);
        vistaMembrecia.datenacimiento.setEnabled(false);
        vistaMembrecia.datevonversion.setEnabled(false);
    }
    public void habilitar(){
        vistaMembrecia.botonagregar.setEnabled(true);
        vistaMembrecia.botoncancelar.setEnabled(true);
        //vistaMembrecia.botoneliminar.setEnabled(true);
        //vistaMembrecia.botonmodificar.setEnabled(true);
        //vistaMembrecia.botonreporte.setEnabled(true);
        //vistaMembrecia.btnbuscar1.setEnabled(true);
        //vistaMembrecia.botonlistar.setEnabled(true);
        
        
        vistaMembrecia.txtnombre.setEnabled(true);
        vistaMembrecia.txtpaterno.setEnabled(true);
        vistaMembrecia.txtmaterno.setEnabled(true);
        vistaMembrecia.txtdocumento.setEnabled(true);
        vistaMembrecia.txtdireccion.setEnabled(true);
        vistaMembrecia.txttelefono.setEnabled(true);
        vistaMembrecia.txtnomfererencia.setEnabled(true);
        vistaMembrecia.txtnumreferencia.setEnabled(true);
        //vistaMembrecia.txtbuscar.setEnabled(true);
        
        vistaMembrecia.boxtalentos.setEnabled(true);
        vistaMembrecia.boxdones.setEnabled(true);
        vistaMembrecia.boxactivo.setEnabled(true);
        vistaMembrecia.boxestado.setEnabled(true);
        vistaMembrecia.datebautizo.setEnabled(true);
        vistaMembrecia.datenacimiento.setEnabled(true);
        vistaMembrecia.datevonversion.setEnabled(true);
    }
    
    public void habilitarClic(){
        vistaMembrecia.txtnombre.setEnabled(true);
        vistaMembrecia.txtpaterno.setEnabled(true);
        vistaMembrecia.txtmaterno.setEnabled(true);
        vistaMembrecia.txtdocumento.setEnabled(true);
        vistaMembrecia.txtdireccion.setEnabled(true);
        vistaMembrecia.txttelefono.setEnabled(true);
        vistaMembrecia.txtnomfererencia.setEnabled(true);
        vistaMembrecia.txtnumreferencia.setEnabled(true);
        //vistaMembrecia.txtbuscar.setEnabled(true);
        
        vistaMembrecia.boxtalentos.setEnabled(true);
        vistaMembrecia.boxdones.setEnabled(true);
        vistaMembrecia.boxactivo.setEnabled(true);
        vistaMembrecia.boxestado.setEnabled(true);
        vistaMembrecia.datebautizo.setEnabled(true);
        vistaMembrecia.datenacimiento.setEnabled(true);
        vistaMembrecia.datevonversion.setEnabled(true);
    }
    
    /*public void buscar(String buscando){
         // Validación para evitar buscar con el hint
            if (vistaMembrecia.txtbuscar.getText().equals("Buscar por nombres, apellidos y CI") || vistaMembrecia.txtbuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
        if(vistaMembrecia.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablaModel=membreciaDAO.buscarMiembros(buscando);
            vistaMembrecia.tablademiembros.setModel(tablaModel);
        }
    }*/
    
    public void ajustarAnchoColumnas(JTable tabla) {
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            int ancho = 50; // Ancho mínimo
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                TableCellRenderer render = tabla.getCellRenderer(fila, columna);
                Component comp = tabla.prepareRenderer(render, fila, columna);
                ancho = Math.max(comp.getPreferredSize().width + 10, ancho);
            }
            tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        }
        
    }
    public void llenarTablaMembrecia(List<Membrecia> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaMembrecia.tablademiembros.getModel(); // Asegúrate que este nombre sea correcto
        modelo.setRowCount(0); // Limpiar la tabla

        for (Membrecia m : lista) {
            String telefonoMostrado = (m.getTelefono() == 0) ? "--" : String.valueOf(m.getTelefono());
            modelo.addRow(new Object[]{
                //m.getIdmembrecia(),
                m.getNombre(),
                m.getApellidop(),
                m.getApellidom(),
                m.getNumdocumento(),
                m.getFechanacimiento(),
                m.getEstadocivil(),
                m.getFechaconversion(),
                m.getFechabautizo(),
                m.getTalentos(),
                m.getDones(),
                m.getActivo(),
                m.getDireccion(),
                telefonoMostrado,
                m.getNomreferencia(),
                m.getNumreferencia()
            });
        }
    }
    
    public String capitalizarNombre(String texto) {
        if (texto == null || texto.isEmpty()) return "";

        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.length() > 0) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                         .append(palabra.substring(1))
                         .append(" ");
            }
        }

        return resultado.toString().trim();
    }


    /*public void mayus(){
        
            con=new ConverMayus();
            String cad=(String)vistaMembrecia.txtnombre.getText();
            
            con.convertirMayus(cad);
            
    }*/
}
