package it.mexican.quizstudy;

import it.mexican.quizstudy.model.Answer;
import it.mexican.quizstudy.model.Exam;
import it.mexican.quizstudy.model.Lesson;
import it.mexican.quizstudy.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.Scanner;
        /*
        //Compile mvn compile
		//Run: mvn exec:java -Dexec.mainClass="it.mexican.quizstudy.App"
		
		*/

/**
 * @author Domenico Conti
 */

public class ImportQuizApp
{

    private static final String PERSISTENCE_UNIT_NAME = "quizstudyDS";
    private static EntityManagerFactory factory;

    public static void main(String[] argv) throws Exception
    {
        System.out.println("START");

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        File quizFile = new File("src/main/resources/quiz.txt");
        if(!quizFile.exists()){
            return;
        }

        Exam exam = new Exam();
        exam.setDescription("Impianti Elettrici");
        entityManager.persist(exam);

        Scanner scanner = new Scanner(quizFile);
        scanner.useDelimiter(System.getProperty("line.separator"));

        int i = 0;
        Lesson lesson = null;
        Question question = null;
        while (scanner.hasNext())
        {

            if(i == 4){
                i = 0;
            }

            String currentLine = scanner.nextLine();

            if (currentLine.trim().isEmpty() || currentLine == null)
            {
                continue;
            }

            //Si tratta della Lezione
            if (currentLine.startsWith("<L"))
            {
                lesson = buildAndSaveLezione(currentLine.substring(1,currentLine.length()), entityManager, exam);
                continue;
            }

            else if (Character.isDigit(currentLine.charAt(0))){
                //Si tratta della domanda
                question = buildAndSaveDomanda(currentLine, entityManager, lesson);
                continue;
            }

            //Si tratta di una risposta
            else if (i < 4 &&
                    ((currentLine.startsWith("A") || currentLine.startsWith("@A"))) ||
                    ((currentLine.startsWith("B") || currentLine.startsWith("@B"))) ||
                    ((currentLine.startsWith("C") || currentLine.startsWith("@C"))) ||
                    ((currentLine.startsWith("D") || currentLine.startsWith("@D"))) )
            {
                boolean isTrue = false;
                if (currentLine.startsWith("@"))
                {
                    isTrue = true;
                }

                buildAndSaveRisposta(currentLine, question, entityManager, isTrue);
                i++;
                continue;
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("STOP");
    }

    private static Lesson buildAndSaveLezione(String textLesson, EntityManager entityManager, Exam exam)
    {
        String[] parts = textLesson.split(" ");
        if (parts.length == 0)
        {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setNumero(Integer.parseInt(parts[1]));
        lesson.setDescription(parts[0]);
        lesson.setExam(exam);
        entityManager.persist(lesson);
        return lesson;
    }

    private static Question buildAndSaveDomanda(String textQuestion, EntityManager entityManager, Lesson lesson)
    {
        if (textQuestion.length() == 0)
        {
            return null;
        }

        Question question = new Question();
        question.setTesto(textQuestion.substring(2, textQuestion.length()).trim());
        question.setLesson(lesson);
        entityManager.persist(question);
        return question;

    }

    private static void buildAndSaveRisposta(String textAnswer, Question question, EntityManager entityManager, boolean isTrue)
    {
        if (textAnswer.length() == 0)
        {
            return;
        }
        Answer answer = new Answer();
        answer.setCodice(textAnswer.trim().substring(0, 1));
        answer.setTest(textAnswer.substring(2, textAnswer.length()).trim());
        answer.setDomanda(question);
        answer.setCorretta(isTrue);
        entityManager.persist(answer);
    }
}
