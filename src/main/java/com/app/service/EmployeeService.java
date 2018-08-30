package com.app.service;

import java.util.List;

import com.app.dto.EmployeeDto;

public interface EmployeeService {

	public int saveEmp(EmployeeDto employeeDto);

	public List<EmployeeDto> getAllEmp();

	public void deleteEmp(int id);

	public EmployeeDto getEmplById(int id);
	
	public void updateEmp(EmployeeDto employeeDto);
	
	public void deleteAll(Integer[] ids);
	
	public List<EmployeeDto> searchEmp(String name);
	
	public List<EmployeeDto> getEmpByPagination(int pageNo);
	
	public long noOfRecords();
	
	public int getPageCount();
}
