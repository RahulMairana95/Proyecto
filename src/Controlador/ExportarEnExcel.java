/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;
import java.io.*;

/**
 *
 * @author LENOVO
 */
public class ExportarEnExcel {
    public void ExportarE(JTable tab) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();

                // Crear libro y hoja
                Workbook libro = new HSSFWorkbook();  // Usar XSSFWorkbook si es .xlsx
                // Crear estilo para encabezado con fuente en negrita
                CellStyle estiloEncabezado = libro.createCellStyle();
                Font fuenteNegrita = libro.createFont();
                fuenteNegrita.setBoldweight(Font.BOLDWEIGHT_BOLD);
                estiloEncabezado.setFont(fuenteNegrita);

                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Datos");
                hoja.setDisplayGridlines(false);

                // Agregar los nombres de las columnas a la primera fila
                Row filaEncabezado = hoja.createRow(0);
                for (int c = 0; c < tab.getColumnCount(); c++) {
                    Cell celda = filaEncabezado.createCell(c);
                    celda.setCellValue(tab.getColumnName(c));
                    celda.setCellStyle(estiloEncabezado);
                }

                // Llenar las filas con los datos
                for (int f = 0; f < tab.getRowCount(); f++) {
                    Row fila = hoja.createRow(f + 1);  // Empezar desde la segunda fila
                    for (int c = 0; c < tab.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        Object valor = tab.getValueAt(f, c);

                        if (valor instanceof Double) {
                            celda.setCellValue((Double) valor);
                        } else if (valor instanceof Float) {
                            celda.setCellValue((Float) valor);
                        } else if (valor instanceof Integer) {
                            celda.setCellValue((Integer) valor);
                        } else if (valor instanceof String) {
                            celda.setCellValue((String) valor);
                        } else if (valor instanceof Boolean) {
                            celda.setCellValue((Boolean) valor);
                        } else if (valor instanceof java.util.Date) {
                            celda.setCellValue((java.util.Date) valor);
                            // Asegúrate de formatear las fechas si es necesario
                            CellStyle dateStyle = libro.createCellStyle();
                            short dateFormat = libro.createDataFormat().getFormat("yyyy-mm-dd");
                            dateStyle.setDataFormat(dateFormat);
                            celda.setCellStyle(dateStyle);
                        } else {
                            celda.setCellValue(String.valueOf(valor));
                        }
                    }
                }
                // Ajustar ancho de columnas para que se adapte al contenido
                for (int c = 0; c < tab.getColumnCount(); c++) {
                    hoja.autoSizeColumn(c);
                }

                // Escribir el archivo y cerrar
                libro.write(archivo);
                archivo.close();

                // Abrir el archivo después de guardarlo
                Desktop.getDesktop().open(archivoXLS);

            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}

