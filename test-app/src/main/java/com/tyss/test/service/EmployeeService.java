package com.tyss.test.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyss.test.dto.EmployeeDTO;
import com.tyss.test.entity.Employee;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private SessionFactory sessionFactory;

	public String saveEmployee(EmployeeDTO employeeDTO) {
		Session currentSession = sessionFactory.getCurrentSession();
		String query = "from Employee where employeeId =:employeeId";
		List resultList = currentSession.createQuery(query).setParameter("employeeId", employeeDTO.getEmployeeId())
				.getResultList();
		System.err.println(resultList.isEmpty());
		System.err.println(resultList);
		if (!resultList.isEmpty()) {
			return "Employee Already Exists with the Employee Id";
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		System.err.println(employee);
		Serializable save = currentSession.save(employee);
		System.err.println(save);
		return "Saved Successfully";
	}

	public String updateEmployee(EmployeeDTO employeeDTO) {
		Session currentSession = sessionFactory.getCurrentSession();
		Employee employee = currentSession.find(Employee.class, employeeDTO.getEmployeeInfoId());
		if (employee == null) {
			return "Employee Not Found";
		}
		employee.setName(employeeDTO.getName());
		currentSession.save(employee);
		return "Updated Successfully";
	}

	public String deleteEmployee(Long employeeInfoId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Employee employee = currentSession.find(Employee.class, employeeInfoId);
		if (employee == null) {
			return "Employee Not Found";
		}
		currentSession.delete(employee);
		return "Deleted Successfully";
	}

	public Employee getById(Long employeeInfoId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.find(Employee.class, employeeInfoId);
	}

	public List<EmployeeDTO> getAllEmployees() {
		Session currentSession = sessionFactory.getCurrentSession();
		String query = "from Employee";
		List resultList = currentSession.createQuery(query).getResultList();
		System.err.println(resultList);
		return (List<EmployeeDTO>) resultList.stream().map(object -> {
			Employee employee = (Employee) object;
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			return employeeDTO;
		}).collect(Collectors.toList());

	}

}
