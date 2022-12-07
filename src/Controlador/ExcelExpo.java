/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author RAHUL
 */
public class ExcelExpo {
    public void Exportar(JTable tab) throws IOException{
        JFileChooser chooser=new JFileChooser();
        //File archivo;
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Archivos de excel","xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            String ruta=chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS=new File(ruta);
                if(archivoXLS.exists()){
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo=new FileOutputStream(archivoXLS);
                Sheet hoja=libro.createSheet("Datos");
                hoja.setDisplayGridlines(false);
                for(int f=0;f<tab.getRowCount();f++){
                    Row fila=hoja.createRow(f);
                    for(int c=0;c<tab.getColumnCount();c++){
                        Cell celda=fila.createCell(c);
                        if(f==0){
                            celda.setCellValue(tab.getColumnName(c));
                        }
                    }
                }
                int filainicio=1;
                for(int f=0;f<tab.getRowCount();f++){
                    Row fila=hoja.createRow(filainicio);
                    filainicio++;
                    for(int c=0;c<tab.getColumnCount();c++){
                        Cell celda=fila.createCell(c);
                        if(tab.getValueAt(f, c) instanceof Double){
                            celda.setCellValue(Double.parseDouble(tab.getValueAt(f, c).toString()));
                        }else if(tab.getValueAt(f, c) instanceof Float){
                            celda.setCellValue(Float.parseFloat((String) tab.getValueAt(f, c)));
                            
                        }else{
                            celda.setCellValue(String.valueOf(tab.getValueAt(f, c)));
                        }
                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
                
                    
        } catch (IOException | NumberFormatException e) {
            throw e;
        }
        }
    }
}
