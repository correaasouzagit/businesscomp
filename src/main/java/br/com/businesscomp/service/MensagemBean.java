package br.com.businesscomp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.businesscomp.jpa.JpaTest;


@ManagedBean
@RequestScoped
public class MensagemBean {

	public String getHorario() {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return "Atualizado em " + sdf.format(new Date());
	}

	public String getEmployees() {

		String databaseUrl = System.getenv("DATABASE_URL");    
		StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
		String dbVendor = st.nextToken(); //if DATABASE_URL is set
		String userName = st.nextToken();
		String password = st.nextToken();
		String host = st.nextToken();
		String port = st.nextToken();
		String databaseName = st.nextToken();
		String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url",jdbcUrl);
		System.out.println("databaseUrl: " + jdbcUrl );
		properties.put("javax.persistence.jdbc.user", userName);
		System.out.println("userName: " + userName);
		properties.put("javax.persistence.jdbc.password", password);
		System.out.println("password: " + password);
		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");   
		
//		properties.put("javax.persistence.jdbc.url","jdbc:postgresql://localhost:5432/businesscomp");
//		properties.put("javax.persistence.jdbc.user", "postgres");
//		properties.put("javax.persistence.jdbc.password", "postgres");
//		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

//		properties.put("javax.persistence.jdbc.url","jdbc:postgresql://ec2-54-163-235-165.compute-1.amazonaws.com:5432/d7u3nt4ag4434e");
//		properties.put("javax.persistence.jdbc.user", "zezmhrpxgmbcnr");
//		properties.put("javax.persistence.jdbc.password", "EMtOPnLrOJg-bSwwcgpnH1JsDj");
//		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("businesscomp", properties);

		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listEmployees();

		return test.escreveEmployees();
	}

	public String getUrl() {
		String databaseUrl = System.getenv("DATABASE_URL");  
		return "URL = " + databaseUrl;
	}
}