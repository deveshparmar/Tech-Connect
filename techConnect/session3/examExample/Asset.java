package techConnect.session3.examExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Asset {
    private boolean examInProgress;
    private Semaphore mutex; // defining the mutex (uses locking mechanism 1 or 0)
    private List<Question> examQuestions;

    public Asset() {
        this.examInProgress = false;
        this.mutex = new Semaphore(1); // initializing the semaphore 
        this.examQuestions = new ArrayList<>();
    }

    public void createExam(List<Question> questions) {
        try {
            mutex.acquire(); // Acquire the mutex for creating question
            if (!examInProgress) {
                examQuestions.addAll(questions);
            } else {
                throw new Error("Exam already in progress");
            }
        } catch (InterruptedException error) {
            error.printStackTrace();
        } finally {
            mutex.release(); // Release the mutex so other thread can access
        }
    }

    public void startExam() {
        try {
            mutex.acquire(); // acquiring the mutex at the starting of examination
            if (!examInProgress) {
                examInProgress = true; // changing status of examination
                System.out.println("ASSET exam started");
            } else {
                throw new Error("Exam already started");
            }
        } catch (InterruptedException error) {
            error.printStackTrace();
        } finally {
            mutex.release(); // releasing the mutex so that other threads can access
        }
    }

    public void submitExam(String studentName, String examName){
        try{
            mutex.acquire(); // acquiring the mutex at the time of submitting the examination
            if(examInProgress){
                System.out.println(studentName + " is submitting the exam paper: " + examName);
                 
                for (Question question : examQuestions) {
                    question.answerQuestion(studentName, "Answer for " + question.getQuestionStem());
                }

                System.out.println(studentName + "'s exam paper submitted successfully.");
            }else{
                throw new Error("Sorry, "  +studentName + "Exam Not yet started!");
            }
        }catch(InterruptedException error){
            error.printStackTrace();
        } finally {
           mutex.release(); // releasing the mutex so others thread can access
        }
    }
}
