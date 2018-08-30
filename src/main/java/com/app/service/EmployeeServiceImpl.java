package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.bo.EmployeeBo;
import com.app.common.CommonConstants;
import com.app.dao.EmployeeDao;
import com.app.dto.EmployeeDto;

@EnableTransactionManagement
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int saveEmp(EmployeeDto employeeDto) {
		EmployeeBo employeeBo = new EmployeeBo();
		BeanUtils.copyProperties(employeeDto, employeeBo);
		return empDao.saveEmp(employeeBo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDto> getAllEmp() {
		List<EmployeeBo> empBo = empDao.getEmployee();

		List<EmployeeDto> empDto = new ArrayList();

		for (EmployeeBo bo : empBo) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(bo, dto);
			empDto.add(dto);
		}
		return empDto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteEmp(int id) {
		empDao.deleteEmp(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDto getEmplById(int id) {
		EmployeeBo bo = empDao.getEmpById(id);
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEmp(EmployeeDto employeeDto) {
		EmployeeBo employeeBo = new EmployeeBo();
		BeanUtils.copyProperties(employeeDto, employeeBo);
		empDao.updateEmp(employeeBo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll(Integer[] ids) {
		empDao.deleteAll(ids);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDto> searchEmp(String name) {
		List<EmployeeBo> listBo = empDao.searchEmp(name);
		List<EmployeeDto> listDto = new ArrayList();
		for (EmployeeBo bo : listBo) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(bo, dto);
			listDto.add(dto);
		}
		return listDto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDto> getEmpByPagination(int pageNo) {
		List<EmployeeBo> empBo = empDao.getEmpByPagination(pageNo);
		List<EmployeeDto> listDto = new ArrayList();
		for (EmployeeBo bo : empBo) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(bo, dto);
			listDto.add(dto);
		}
		return listDto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long noOfRecords() {
		return empDao.noOfRecords();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int getPageCount() {
		int noOfMaxRecords = CommonConstants.paginationNoOfMaxRecords;
		long count = empDao.noOfRecords();
		int pages = (int) Math.ceil(count / (noOfMaxRecords + 0.0));
		return pages;
	}

}
