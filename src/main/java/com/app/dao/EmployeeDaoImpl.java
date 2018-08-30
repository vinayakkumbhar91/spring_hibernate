package com.app.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.bo.EmployeeBo;
import com.app.common.CommonConstants;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	@Autowired
	private SessionFactory factory;

	public int saveEmp(EmployeeBo employeeBo) {
		logger.debug("saveEmp(-) method start");
		int id = 0;
		Session session = factory.getCurrentSession();
		logger.info("session object created using getCurrentSession()");
		try {
			id = (Integer) session.save(employeeBo);
			logger.info("employeeBo save and id collect");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error occured during employeeBo save");
		}
		logger.debug("id return after saving employeebo");
		return id;
	}

	public List<EmployeeBo> getEmployee() {
		logger.debug("getEmployee() method start");
		Session session = factory.getCurrentSession();
		logger.info("session object get by gerCurrentSession()");
		List<EmployeeBo> empList = null;
		Query query = null;
		try {
			query = session.createQuery("from EmployeeBo");
			logger.info("query object created by passing select hql");
			empList = query.list();
			logger.info("query executed and store in list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error occured during retriving all employeedata");
		}
		logger.debug("employee list return");
		return empList;
	}

	public void deleteEmp(int id) {
		Session session = factory.getCurrentSession();
		EmployeeBo bo = new EmployeeBo();
		bo.setId(id);
		session.delete(bo);
	}

	public EmployeeBo getEmpById(int id) {
		Session session = factory.getCurrentSession();
		EmployeeBo bo = (EmployeeBo) session.get(EmployeeBo.class, id);
		return bo;
	}

	public void updateEmp(EmployeeBo employeeBo) {
		Session session = factory.getCurrentSession();
		session.update(employeeBo);
	}

	public void deleteAll(Integer[] ids) {
		Session session = factory.getCurrentSession();

		for (int i = 0; i < ids.length; i++) {
			Query query = session.createQuery("delete from EmployeeBo where id =:id");
			query.setParameter("id", ids[i]);
			query.executeUpdate();
		}
	}

	public List<EmployeeBo> searchEmp(String name) {
		Session session = factory.getCurrentSession();
		Query query = session
				.createQuery("from EmployeeBo where name like '%" + name + "%' or email like '%" + name + "%'");
		List<EmployeeBo> empList = query.list();
		return empList;
	}

	public List<EmployeeBo> getEmpByPagination(int pageNo) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from EmployeeBo");
		int maxRecordsShow = CommonConstants.paginationNoOfMaxRecords;
		int startRecord = (pageNo - 1) * maxRecordsShow;
		query.setFirstResult(startRecord);
		query.setMaxResults(maxRecordsShow);
		List<EmployeeBo> l = query.list();
		return l;
	}

	public long noOfRecords() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("select count(*) from EmployeeBo");
		long count = (Long) query.uniqueResult();
		return count;
	}
}
