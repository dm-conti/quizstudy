package it.mexican.quizstudy;

import it.mexican.quizstudy.model.Answer;
import it.mexican.quizstudy.model.Lesson;
import it.mexican.quizstudy.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
		/*
		//Compile mvn compile
		//Run: mvn exec:java -Dexec.mainClass="it.mexican.quizstudy.App"
		
		*/
/**
* @author Domenico Conti
*/
		
public class App
{

	private static final String PERSISTENCE_UNIT_NAME = "quizstudyDS";
	private static EntityManagerFactory factory;

	public static void main(String[] argv) throws Exception {
		System.out.println("START");

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Lesson lesson = new Lesson();
		lesson.setNumero(1);
		lesson.setDescription("Lezione 0");
		em.persist(lesson);

		Question question = new Question();
		question.setLesson(lesson);
		question.setTesto("Dummy Question");
		em.persist(question);

		Answer answer = new Answer();
		answer.setDomanda(question);
		answer.setTest("Dummy Answer");
		answer.setCorretta(true);
		em.persist(answer);

		em.getTransaction().commit();
		em.close();

		System.out.println("STOP");
	}
}
