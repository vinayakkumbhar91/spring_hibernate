package com.app.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.dto.EmployeeDto;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenerator extends AbstractPdfView {

	@Override
	public void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.addHeader("Content-Disposition", "attachment;filename=EMPLOYEEDATA.pdf");
		response.setContentType("text/pdf");
		Paragraph paragraph = new Paragraph("Employee Data");
		paragraph.setAlignment("center");
		Paragraph paragraph1 = new Paragraph();
		PdfPTable table = new PdfPTable(3);

		table.addCell("ID");
		table.addCell("NAME");
		table.addCell("EMAIL");

		List<EmployeeDto> emps = (List<EmployeeDto>) model.get("emps");
		for (EmployeeDto employeeDto : emps) {
			table.addCell(employeeDto.getId() + "");
			table.addCell(employeeDto.getName() + "");
			table.addCell(employeeDto.getEmail() + "");
		}

		document.add(paragraph);
		document.add(paragraph1);
		document.add(table);
		document.top(10.0f);
		

	}

}
