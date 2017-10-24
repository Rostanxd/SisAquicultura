package es.desarrollo.excels;

import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.servicio.Utils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.omnifaces.util.Faces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class generaExcelAceiteQuemado {

    private static String fileName = "C:/Users/HP/Documents/Programming/Java/Projects/SisAquiculturaTesis/Versionamiento/SisAquicultura_0730/" +
            "web/excel/Aceite_Quemado_" + String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)) + ".xls";

    private static String contentType = "application/vnd.ms-excel";

    private static void download() throws IOException {
        File file = new File(fileName);
        Faces.sendFile(file, true);
    }

    public static void generaExcel(aceiteQuemado aceiteQuemado){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFCellStyle style = workbook.createCellStyle();
//            style.setBorderTop(BorderStyle.DOUBLE);
//            style.setBorderBottom(BorderStyle.THIN);
//            style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);

            HSSFFont font = workbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
//            font.setFontHeightInPoints((short) 20);
            font.setBold(true);
//            font.setColor(HSSFColor.BLUE.index);
            style.setFont(font);

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Reporte de Acuicultura");   //  Titulo

            HSSFRow row = sheet.createRow((short)2);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue(new HSSFRichTextString("Empresa"));
            row.createCell(1).setCellValue(aceiteQuemado.getEmpresa().getRuc());
            row.createCell(2).setCellValue(aceiteQuemado.getEmpresa().getNombre());
            row.createCell(3).setCellValue("Estado");
            row.createCell(4).setCellValue(Utils.estadoString(aceiteQuemado.getEstado()));

            row = sheet.createRow((short)3);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Mes");
            row.createCell(1).setCellValue(Utils.mesString(aceiteQuemado.getAcq_mes()));
            row.createCell(3).setCellStyle(style);
            row.createCell(3).setCellValue("Fec. Ingreso");
            row.createCell(4).setCellValue(aceiteQuemado.getFechaIngreso());

            row = sheet.createRow((short)4);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Descripción");
            row.createCell(1).setCellValue(aceiteQuemado.getFichaDescripcion());

            row = sheet.createRow((short)5);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Observación");
            row.createCell(1).setCellValue(aceiteQuemado.getFichaObservacion());

            sheet.autoSizeColumn((short) 1);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            download();
        } catch ( Exception ex ) {
            System.out.println(ex);
            ex.getStackTrace();
        }
    }
}
