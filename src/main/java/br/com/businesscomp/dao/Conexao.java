package br.com.businesscomp.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	//nome da unidade de persistencia definia no persistence.xml
	private static final String UNIT_NAME = "businesscomp";

	private EntityManagerFactory emf = null;

	private EntityManager em = null;

	public EntityManager getEntityManager() {

		if (emf == null) {
			String databaseUrl = System.getenv("DATABASE_URL");    
			StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
			String dbVendor = st.nextToken(); //if DATABASE_URL is set
			String userName = st.nextToken();
			String password = st.nextToken();
			String host = st.nextToken();
			String port = st.nextToken();
			String databaseName = st.nextToken();

			//Use esta linha para heroku
			//String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);

			//Use esta linha para local
			//Variavel de ambiente DATABASE_URL = postgres://postgres:postgres@localhost:5432/businesscomp
			String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s", host, port, databaseName);

			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.url",jdbcUrl);
			properties.put("javax.persistence.jdbc.user", userName);
			properties.put("javax.persistence.jdbc.password", password);
			properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
			properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); 		 
			emf = Persistence.createEntityManagerFactory(UNIT_NAME, properties);
		}

		if (em == null) {
			em = emf.createEntityManager();
		}

		return em;
	}
}
