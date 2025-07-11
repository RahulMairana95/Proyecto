/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Desktop;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import javax.swing.*;
import javax.swing.table.TableModel;
//import java.awt.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

public class ExportarIngresosEgresos {

    public void exportarConTotal(JTable tabla) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            File archivoXLS = new File(ruta);

            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            archivoXLS.createNewFile();

            Workbook libro = new HSSFWorkbook();
            Sheet hoja = libro.createSheet("Datos");
            hoja.setDisplayGridlines(false);

            // Estilo negrita
            CellStyle estiloNegrita = libro.createCellStyle();
            Font negrita = libro.createFont();
            negrita.setBoldweight(Font.BOLDWEIGHT_BOLD);
            estiloNegrita.setFont(negrita);
            // Crear estilo negrita con color de fondo amarillo claro
            CellStyle estiloTotal = libro.createCellStyle();
            Font fuenteNegrita = libro.createFont();
            fuenteNegrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// usa setBold en POI recientes
            //fuenteNegrita.setBold(true); 
            estiloTotal.setFont(fuenteNegrita);

            // Color de fondo amarillo claro (puedes cambiar el color)
            estiloTotal.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            estiloTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            // Escribir encabezados
            Row filaEncabezado = hoja.createRow(0);
            for (int c = 0; c < tabla.getColumnCount(); c++) {
                Cell celda = filaEncabezado.createCell(c);
                celda.setCellValue(tabla.getColumnName(c));
                celda.setCellStyle(estiloNegrita);
            }

            // Copiar datos y acumular total en columna 2 (índice 2)
            double total = 0.0;
            for (int f = 0; f < tabla.getRowCount(); f++) {
                Row fila = hoja.createRow(f + 1);
                for (int c = 0; c < tabla.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    Object valor = tabla.getValueAt(f, c);

                    if (c == 2 && valor != null) { // columna monto
                        try {
                            double monto = Double.parseDouble(valor.toString());
                            celda.setCellValue(monto);
                            total += monto;
                        } catch (NumberFormatException e) {
                            celda.setCellValue(valor.toString());
                        }
                    } else if (valor instanceof Number) {
                        celda.setCellValue(((Number) valor).doubleValue());
                    } else if (valor != null) {
                        celda.setCellValue(valor.toString());
                    }
                }
            }
            


            // Agregar fila con total al final
            int filaTotalIndex = tabla.getRowCount() + 1;
            Row filaTotal = hoja.createRow(filaTotalIndex);

            Cell celdaTextoTotal = filaTotal.createCell(1);
            celdaTextoTotal.setCellValue("TOTAL");
            celdaTextoTotal.setCellStyle(estiloTotal);

            Cell celdaTotal = filaTotal.createCell(2);
            celdaTotal.setCellValue(total);
            celdaTotal.setCellStyle(estiloTotal);

            // Ajustar ancho columnas
            for (int c = 0; c < tabla.getColumnCount(); c++) {
                hoja.autoSizeColumn(c);
            }

            // Guardar archivo
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            libro.write(archivo);
            archivo.close();

            // Abrir archivo
            Desktop.getDesktop().open(archivoXLS);
        }
    }
}

