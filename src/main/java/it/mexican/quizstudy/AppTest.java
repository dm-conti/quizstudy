package it.mexican.quizstudy;

import it.mexican.quizstudy.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
		/*
		//Compile mvn compile
		//Run: mvn exec:java -Dexec.mainClass="net.roseindia.AppTest"
		
		*/
/**
* @author Domenico Conti
*/
		
public class AppTest {

	private static final String PERSISTENCE_UNIT_NAME = "quizstudyDS";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		
		Product product = new Product();
		product.setProductName("JPA 2.1 Book");
		product.setProductDescription("This is the latest book on JPA 2.1");
		product.setStockQty(100.00);
		product.setPrice(95.99);
		em.persist(product);
		em.getTransaction().commit();
		em.close();
		
	}
}
