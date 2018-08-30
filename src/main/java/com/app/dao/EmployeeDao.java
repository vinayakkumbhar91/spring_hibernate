package com.app.dao;

import java.util.List;

import com.app.bo.EmployeeBo;

public interface EmployeeDao {

	public int saveEmp(EmployeeBo employeeBo);

	public List<EmployeeBo> getEmployee();

	public EmployeeBo getEmpById(int id);

	public void deleteEmp(int id);

	public void updateEmp(EmployeeBo employeeBo);
	
	public void deleteAll(Integer[] ids);
	
	public List<EmployeeBo> searchEmp(String name);
	
	public List<EmployeeBo> getEmpByPagination(int pageNo);
	
	public long noOfRecords();
}
