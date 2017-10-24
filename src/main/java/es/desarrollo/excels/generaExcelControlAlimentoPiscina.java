package es.desarrollo.excels;

import es.desarrollo.hibernate.entities.controlAlimentoPiscina;
import es.desarrollo.servicio.Utils;
import org.apache.poi.hssf.usermodel.*;
import org.omnifaces.util.Faces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class generaExcelControlAlimentoPiscina {
    private static String fileName = "C:/Users/HP/Documents/Programming/Java/Projects/SisAquiculturaTesis/Versionamiento/SisAquicultura_0730/" +
            "web/excel/Aceite_Quemado_" + String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)) + ".xls";

    private static String contentType = "application/vnd.ms-excel";

    private static void download() throws IOException {
        File file = new File(fileName);
        Faces.sendFile(file, true);
    }

    public static void generaExcel(controlAlimentoPiscina cap){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFCellStyle style = workbook.createCellStyle();

            HSSFFont font = workbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBold(true);
            style.setFont(font);

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Reporte de Acuicultura");   //  Titulo

            HSSFRow row = sheet.createRow((short)2);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue(new HSSFRichTextString("Empresa"));
            row.createCell(1).setCellValue(cap.getEmpresa().getRuc());
            row.createCell(2).setCellValue(cap.getEmpresa().getNombre());
            row.createCell(3).setCellValue("Estado");
            row.createCell(4).setCellValue(Utils.estadoString(cap.getEstado()));

            row = sheet.createRow((short)3);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Mes");
            row.createCell(1).setCellValue(Utils.mesString(cap.getAcp_mes()));
            row.createCell(3).setCellStyle(style);
            row.createCell(3).setCellValue("Fec. Ingreso");
            row.createCell(4).setCellValue(cap.getFechaIngreso());

            row = sheet.createRow((short)4);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Piscina");
            row.createCell(1).setCellValue(cap.getFichaPiscina());
            row.createCell(3).setCellStyle(style);
            row.createCell(3).setCellValue("Peso Camarón");
            row.createCell(4).setCellValue(cap.getFichaPesoCamaron());

            row = sheet.createRow((short)5);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Tipo siembra");
            row.createCell(1).setCellValue(cap.getFichaTipoSiembra());
            row.createCell(3).setCellStyle(style);
            row.createCell(3).setCellValue("Tipo Balanceado");
            row.createCell(4).setCellValue(cap.getFichaTiposBalanceado());

            row = sheet.createRow((short)6);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Corridas");
            row.createCell(1).setCellValue(cap.getFichaCorridas());

            row = sheet.createRow((short)7);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Densidad");
            row.createCell(1).setCellValue(cap.getFichaDensidad());

            row = sheet.createRow((short)8);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Dias cultivo");
            row.createCell(1).setCellValue(cap.getFichaDiasCultivo());

            row = sheet.createRow((short)9);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Dosis Libra");
            row.createCell(1).setCellValue(cap.getFichaDosisLibras());

            row = sheet.createRow((short)10);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Hectáreas");
            row.createCell(1).setCellValue(cap.getFichaHectareas());

            row = sheet.createRow((short)11);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Origen Nauplio");
            row.createCell(1).setCellValue(cap.getFichaOrigenNauplio());

            row = sheet.createRow((short)12);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Proveedor Larva");
            row.createCell(1).setCellValue(cap.getFichaProveedorLarba());

            row = sheet.createRow((short)13);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Otros Insumos Bacterias");
            row.createCell(1).setCellValue(cap.getFichaOtrosInsBacteria());

            row = sheet.createRow((short)14);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Otros Insumos Desparacitantes");
            row.createCell(1).setCellValue(cap.getFichaOtrosInsDesparacitantes());

            row = sheet.createRow((short)15);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Otros Insumos Vitamina");
            row.createCell(1).setCellValue(cap.getFichaOtrosInsVitamina());

            row = sheet.createRow((short)16);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Otros Insumos Carbonato");
            row.createCell(1).setCellValue(cap.getFichaOtrosInsumosCalCarbonato());

            row = sheet.createRow((short)17);
            row.createCell(0).setCellStyle(style);
            row.createCell(0).setCellValue("Observación");
            row.createCell(1).setCellValue(cap.getFichaObservacion());

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
