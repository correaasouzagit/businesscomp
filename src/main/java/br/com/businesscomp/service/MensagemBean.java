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
    properties.put("javax.persistence.jdbc.url", databaseUrl );
    properties.put("javax.persistence.jdbc.user", userName );
    properties.put("javax.persistence.jdbc.password", password );
    properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("businesscomp", properties);
    
	//EntityManagerFactory factory = Persistence.createEntityManagerFactory("businesscomp");
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

	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	System.out.println(".. done");
	return "Atualizado em " + sdf.format(new Date());
  }
}
