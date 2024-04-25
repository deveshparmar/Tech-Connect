package techConnect.session3.examExample;

import java.util.concurrent.Semaphore;


public class Question {
    private String questionStem;
    private Semaphore mutex;

    public Question(String questionStem) {
        this.questionStem = questionStem;
        this.mutex = new Semaphore(1);
    }

    public String getQuestionStem(){
        return questionStem;
    }

    public void answerQuestion(String studentName, String answer){
        try{
            mutex.acquire();
            System.out.println(studentName + " is answering question - " + questionStem);
            System.out.println("Answer - "+answer);
            Thread.sleep(2000);
            System.out.println(studentName + "'s answer submitted successfully");
        }catch(InterruptedException error){
            error.printStackTrace();
        }finally{
            mutex.release();
        }
    }
    
}
