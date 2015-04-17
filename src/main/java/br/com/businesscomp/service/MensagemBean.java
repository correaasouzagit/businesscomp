package br.com.businesscomp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.businesscomp.dao.DepartmentDAO;
import br.com.businesscomp.dao.DepartmentDAOImpl;
import br.com.businesscomp.dao.EmployeeDAO;
import br.com.businesscomp.dao.EmployeeDAOImpl;
import br.com.businesscomp.domain.Department;
import br.com.businesscomp.domain.Employee;


@ManagedBean
@RequestScoped
public class MensagemBean {

	public String getHorario() {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return "Atualizado em " + sdf.format(new Date());
	}

	public String getEmployees() {
		String erro = null;
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		DepartmentDAO departmentDAO = new DepartmentDAOImpl();
		try {
			Department department = new Department("teste1");
			departmentDAO.save(department);			
			employeeDAO.save(new Employee("Alexandre", department));
			Department department2 = new Department("teste2");			
			employeeDAO.save(new Employee("Fernanda", department2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			erro = e.getMessage();
		}	

		return "Erro: " + erro + " - - " +  employeeDAO.escreveTodos(Employee.class);


	}
}