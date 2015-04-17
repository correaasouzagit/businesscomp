package br.com.businesscomp.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.businesscomp.domain.Department;
import br.com.businesscomp.domain.Employee;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
//		EntityManager manager = factory.createEntityManager();
//		JpaTest test = new JpaTest(manager);
//
//		EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//		try {
//			test.createEmployees();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tx.commit();
//
//		test.listEmployees();

		System.out.println(".. done");
	}

 
//	public void createEmployees() {
//		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
//		if (numOfEmployees == 0) {
//			Department department = new Department("java");
//			manager.persist(department);
//
//			manager.persist(new Employee("Jakab Gipsz",department));
//			manager.persist(new Employee("Captain Nemo",department));
//
//		}
//	}


	public void listEmployees() {
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}
	}
	
	public String escreveEmployees() {
		StringBuilder str = new StringBuilder();
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			str.append(" next employee: " + next);
		}
		return str.toString();
	}


}
