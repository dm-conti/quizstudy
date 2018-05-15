package it.mexican.quizstudy;

import it.mexican.quizstudy.model.Answer;
import it.mexican.quizstudy.model.Question;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by dconti on 08/05/17.
 */
public class DocGenerator
{
    //SELECT ques.text, answ.text FROM public.questions AS ques JOIN public.answers AS answ ON ques.pk_questions = answ.pk_questions WHERE answ.flg_correct='t' ORDER BY ques.text ASC;

    private static final String PERSISTENCE_UNIT_NAME = "quizstudyDS";
    private static EntityManagerFactory factory;

    public static void main(String[] argv) throws Exception
    {
        String fileName = "risposte-ordinate-sicurezza-impianti-industriali";

        System.out.println("START");

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Query queryQuestions = entityManager.createQuery("FROM Question WHERE lesson.exam.description = 'Sicurezza degli Impianti Industriali' ORDER BY testo ASC");
        List<Question> questions = queryQuestions.getResultList();

        Query query2 = entityManager.createQuery("FROM Answer WHERE domanda IN :domande AND corretta = TRUE ORDER BY domanda.testo ASC");
        query2.setParameter("domande", questions);
        List<Answer> answers = query2.getResultList();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph tmpParagraph = document.createParagraph();

        boolean setBoldOnQuestion = false;
        boolean setBoldOnAnswer = true;

        for (Answer answer : answers){
            XWPFRun tmpRun = tmpParagraph.createRun();
            tmpRun.setBold(setBoldOnQuestion);
            tmpRun.setFontSize(10);
            tmpRun.setText(answer.getDomanda().getTesto());
            tmpRun.addBreak();

            tmpRun = tmpParagraph.createRun();
            tmpRun.setBold(setBoldOnAnswer);
            tmpRun.setFontSize(10);
            tmpRun.setText(answer.getTest());
            tmpRun.addBreak();
        }

        FileOutputStream fos = new FileOutputStream(new File("src/main/resources/" + fileName + ".doc"));
        document.write(fos);
        fos.close();

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("STOP");
    }
}