package it.mexican.quizstudy;

import it.mexican.quizstudy.model.Answer;
import it.mexican.quizstudy.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dconti on 08/05/17.
 */
public class QuestionExtractor
{
    //SELECT ques.text, answ.text FROM public.questions AS ques JOIN public.answers AS answ ON ques.pk_questions = answ.pk_questions WHERE answ.flg_correct='t' ORDER BY ques.text ASC;

    private static final String PERSISTENCE_UNIT_NAME = "quizstudyDS";
    private static EntityManagerFactory factory;

    public static void main(String[] argv) throws Exception
    {
        System.out.println("START");

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("FROM Question ORDER BY testo ASC");
        List<Question> questions = query.getResultList();

        Query query2 = entityManager.createQuery("FROM Answer WHERE domanda IN :domande AND corretta = TRUE ORDER BY domanda.testo ASC");
        query2.setParameter("domande", questions);
        List<Answer> answers = query2.getResultList();

        PrintWriter printWriter = new PrintWriter(new File("src/main/resources/risposte-ordinate.txt"));
        for (Answer answer : answers){
            printWriter.println(answer.getDomanda().getTesto());
            printWriter.println(answer.getTest());
            printWriter.flush();
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("STOP");
    }

}
