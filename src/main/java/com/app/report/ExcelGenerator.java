package com.app.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.app.dto.EmployeeDto;

public class ExcelGenerator extends AbstractExcelView {

	@Override
	public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.addHeader("Content-Disposition", "attachment;filename=EMPLOYEEDATA.xls");
		response.setContentType("text/xls");
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("EMAIL");

		List<EmployeeDto> emps = (List<EmployeeDto>) model.get("emps");
		int rowCount = 1;
		for (EmployeeDto employeeDto : emps) {
			HSSFRow row1 = sheet.createRow(rowCount);
			row1.createCell(0).setCellValue(employeeDto.getId());
			row1.createCell(1).setCellValue(employeeDto.getName());
			row1.createCell(2).setCellValue(employeeDto.getEmail());
			rowCount++;
		}
	}

}
