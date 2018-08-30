package com.app.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.app.dto.EmployeeDto;
import com.app.service.EmployeeService;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations= {"ApplicationContext.xml","dispatcher-servlet.xml"})
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void saveEmployeeTest() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setName("sdsdd");
		employeeDto.setEmail("xzcgsdfh");
		employeeDto.setPassword("sssssssssss");
		int id = employeeService.saveEmp(employeeDto);
		Assert.assertEquals("check value is save ", 9, id);
	}

}
