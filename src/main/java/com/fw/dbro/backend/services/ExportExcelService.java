package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.ExportUserPayrollDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExportExcelService {

  private final String[] headers = {
    "Name", "Role", "Tunjangan Jabatan", "BOps", "Subsidi", "Jumlah Gaji Pokok", "Jumlah Uang Makan", "Lembur LN", "Lembur LS", "Lembur OT",
    "Bonus Harian", "Bonus Ka/Waka", "THR", "Lain-lain", "Casbon", "Punishment", "Subsidi", "BPJSK", "BPJS PT", "PPH 21", "Lain-lain",
    "Jumlah Gaji Bruto", "Jumlah Potongan", "Gaji Diterima", "BSI Account", "MK", "LS", "LN", "SKD", "CT", "DISP", "OT",
    "Gaji Pokok", "Uang Makan"
  };

  public void exportPayrollToExcel(HttpServletResponse httpServletResponse, String divisionName, String periodValue, List<ExportUserPayrollDTO> exportUserPayrollDTOs) throws IOException {
    String filename = "payroll-" + divisionName + "-" + periodValue + ".xlsx";
    httpServletResponse.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=" + filename;
    String headerKey1 = "Access-Control-Expose-Headers";
    String headerValue1 = "Content-Disposition";
    httpServletResponse.setHeader(headerKey, headerValue);
    httpServletResponse.setHeader(headerKey1, headerValue1);
    ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
    /* create excel file */
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    int rowIndex = 0;
    int columnIndex = 0;
    CellStyle cellStyle = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    /* create division name cell */
    font.setBold(true);
    cellStyle.setFont(font);
    Row row = sheet.createRow(rowIndex++);
    Cell cell = row.createCell(columnIndex);
    cell.setCellValue(divisionName);
    cell.setCellStyle(cellStyle);
    /* create header */
    row = sheet.createRow(rowIndex++);
    for(int i = 0; i < headers.length; i++) {
      cell = row.createCell(i);
      cell.setCellValue(headers[i]);
      cell.setCellStyle(cellStyle);
    }
    /* data */
    for(ExportUserPayrollDTO exportUserPayrollDTO : exportUserPayrollDTOs) {
      row = sheet.createRow(rowIndex++);
      columnIndex = 0;
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getName());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getRole());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getTunjanganJabatan().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBops().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getSubsidi().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getJumlahGajiPokok().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getJumlahUangMakan().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLemburLN().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLemburLS().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLemburOT().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBonusHarian().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBonusKawaka().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getThr().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLainLainPlus().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getCasbon().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getPunishment().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getSubsidi().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBpjsk().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBpjspt().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getPph21().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLainLainMinus().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getJumlahGajiBruto().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getJumlahPotongan().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getGajiDiterima().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getBsiAccount());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getMk().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLs().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getLn().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getSkd().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getCt().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getDisp().intValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getOt().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getGajiPokok().doubleValue());
      cell = row.createCell(columnIndex++);
      cell.setCellValue(exportUserPayrollDTO.getUangMakan().doubleValue());
    }
    /* auto-resize column to fit text */
    for(int i = 0; i < headers.length; i++) {
      sheet.autoSizeColumn(i);
    }
    workbook.write(servletOutputStream);
    workbook.close();
    servletOutputStream.close();
  }

}
