package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.app.command.EmployeeCommand;
import com.app.common.CommonConstants;
import com.app.common.CommonMail;
import com.app.dto.EmployeeDto;
import com.app.service.EmployeeService;

@Controller
public class AppController {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private CommonMail mail;

	/**
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping("/getformPage")
	public String getJsp(@ModelAttribute("emp") EmployeeCommand emp) {
		return "form";
	}

	/**
	 * Save data with file upload
	 * 
	 * @param model
	 * @param empCommand
	 * @return
	 */
	@RequestMapping(value = "/savedata", method = RequestMethod.POST)
	public String saveData(Model model, @RequestParam("file") CommonsMultipartFile file,
			@ModelAttribute("emp") EmployeeCommand empCommand) {
		if (empCommand != null && !empCommand.getName().equals("") && !empCommand.getEmail().equals("")
				&& !empCommand.getPassword().equals("") && file != null) {

			String fileName = file.getOriginalFilename();
			String path = "d:/fileupload/" + fileName;
			empCommand.setPath(path);

			// file upload
			byte[] fileByte = file.getBytes();
			try {
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
				bufferedOutputStream.write(fileByte);
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				// file upload completed

				EmployeeDto employeeDto = new EmployeeDto();
				BeanUtils.copyProperties(empCommand, employeeDto);
				int id = empService.saveEmp(employeeDto);
				if (id > 0) {
					boolean status = mail.mailSender(empCommand.getEmail(), CommonConstants.subject,
							CommonConstants.mailText, null);
					if (status)
						model.addAttribute("msg", "Employee registered with id : " + id + " And mail aslo send");
					else
						model.addAttribute("msg", "Employee registered with id : " + id + " mail not send");
					model.addAttribute("emp", new EmployeeCommand());
				} else {
					model.addAttribute("msg", "Employee not saved..Please try again.");
					model.addAttribute("emp", empCommand);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("msg", "Please enter all fields");
			model.addAttribute("emp", empCommand);
		}
		return "form";
	}

	/**
	 * get employee by pagination
	 * 
	 * @author VINAYAk
	 * @param model
	 * @return
	 */
	@RequestMapping("/getEmpData")
	public String getAllEmp(@RequestParam("pageno") int pageNo, Model model) {
		List<EmployeeDto> empDto = empService.getEmpByPagination(pageNo);
		int pages = empService.getPageCount();
		model.addAttribute("pages", pages);
		model.addAttribute("empdata", empDto);
		return "AllData";
	}

	/**
	 * delete employee
	 * 
	 * @author VINAYAK
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteEmp")
	public String deleteEmp(Model model, @RequestParam("id") int id) {
		empService.deleteEmp(id);
		List<EmployeeDto> empDto = empService.getEmpByPagination(1);
		int pages = empService.getPageCount();
		model.addAttribute("pages", pages);
		model.addAttribute("empdata", empDto);
		return "AllData";
	}

	/**
	 * edit employee
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editEmp")
	public String editEmp(Model model, @RequestParam("id") int id) {
		EmployeeDto empDto = empService.getEmplById(id);
		model.addAttribute("emp", empDto);
		return "EditEmp";
	}

	/**
	 * Update data
	 * 
	 * @author VINAYAK
	 * @param emp
	 * @return
	 */
	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	public String updateEmp(Model model, @ModelAttribute("emp") EmployeeCommand emp) {
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(emp, employeeDto);
		empService.updateEmp(employeeDto);
		List<EmployeeDto> empDto = empService.getEmpByPagination(1);
		int pages = empService.getPageCount();
		model.addAttribute("pages", pages);
		model.addAttribute("empdata", empDto);
		return "AllData";
	}

	/**
	 * delete selected multiple checkbox records
	 * 
	 * @author VINAYAK
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deletemul")
	public String deleteAll(Model model, @RequestParam("ids") Integer[] ids) {
		empService.deleteAll(ids);
		List<EmployeeDto> empDto = empService.getEmpByPagination(1);
		int pages = empService.getPageCount();
		model.addAttribute("pages", pages);
		model.addAttribute("empdata", empDto);
		return "AllData";
	}

	/**
	 * Search employee by name and email
	 * 
	 * @author VINAYAK
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping("/search")
	public String searchEmp(Model model, @RequestParam("searchname") String search) {
		List<EmployeeDto> listDto;
		if (!search.equals("") && search != null) {
			listDto = empService.searchEmp(search);
			if (!listDto.isEmpty()) {
				model.addAttribute("empdata", listDto);
			} else {
				List<EmployeeDto> empDto = empService.getEmpByPagination(1);
				int pages = empService.getPageCount();
				model.addAttribute("pages", pages);
				model.addAttribute("empdata", empDto);
				model.addAttribute("msg", "Record not found");
			}
		} else {
			List<EmployeeDto> empDto = empService.getEmpByPagination(1);
			int pages = empService.getPageCount();
			model.addAttribute("pages", pages);
			model.addAttribute("empdata", empDto);
			model.addAttribute("msg", "please enter character to search");
		}
		return "AllData";
	}

	/**
	 * download file
	 * 
	 * @author VINAYAK
	 * @param id
	 * @param responce
	 * @return
	 */
	@RequestMapping("/downloadFile")
	public String downloadFile(@RequestParam("id") int id, HttpServletResponse responce) {
		try {
			File file = new File(empService.getEmplById(id).getPath());
			byte[] bytes = FileCopyUtils.copyToByteArray(file);
			responce.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
			FileCopyUtils.copy(bytes, responce.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AllData";
	}

	/**
	 * create pdf of data
	 * @author VINAYAK
	 * @param model
	 * @return
	 */
	@RequestMapping("convertPdf")
	public String covertPdf(Model model) {
		List<EmployeeDto> employeeData = empService.getAllEmp();
		model.addAttribute("emps", employeeData);
		return "pdfView";
	}

	/**
	 * create excel file
	 * @author VINAYAK
	 * @param model
	 * @return
	 */
	@RequestMapping("convertExcel")
	public String covertExcel(Model model) {
		List<EmployeeDto> employeeData = empService.getAllEmp();
		model.addAttribute("emps", employeeData);
		return "excelView";
	}
}
