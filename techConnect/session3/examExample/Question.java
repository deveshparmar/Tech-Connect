package techConnect.session3.examExample;

import java.util.concurrent.Semaphore;


public class Question {
    private String questionStem;
    /* Defining seamphore to control access to question */
    private Semaphore mutex;

    public Question(String questionStem) {
        this.questionStem = questionStem;
        // initial permit of 1 so only 1 thresd can access the question at a time
        this.mutex = new Semaphore(1);
    }

    /* getter for questions stem */
    public String getQuestionStem(){
        return questionStem;
    }

    public void answerQuestion(String studentName, String answer){
        try{
            mutex.acquire(); // acquire the semaphore for answering question
            System.out.println(studentName + " is answering question - " + questionStem);
            System.out.println("Answer - "+answer);
            Thread.sleep(2000);
            System.out.println(studentName + "'s answer submitted successfully");
        }catch(InterruptedException error){
            error.printStackTrace();
        }finally{
            mutex.release();// release the semaphore after answering question
        }
    }
    
}
