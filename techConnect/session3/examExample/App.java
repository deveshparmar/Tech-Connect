package techConnect.session3.examExample;

import java.util.ArrayList;
import java.util.List;

/* Scenerio of ASSET exam where different students answers the same question using multithreading (used Semaphore)*/

public class App {
    public static void main(String[] args) {

        /* Initializing ASSET examination object */
        Asset assetExam = new Asset();

        /* Creating list of questions for asset examination */
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?"));
        questions.add(new Question("What is the largest planet in our solar system?"));
        questions.add(new Question("What is the square root of 25?"));

        /* Creating exam by adding list of questions */
        assetExam.createExam(questions);

        /* Defining the runnable interface for starting exam */
        Runnable examStarter = assetExam::startExam; 

        /* Defining runnable interface for students to submit exam */
        Runnable student1 = () -> assetExam.submitExam("Ram", "Fractions");
        Runnable student2 = () -> assetExam.submitExam("Steve", "Fractions");
        Runnable student3 = () -> assetExam.submitExam("Sam", "Fractions");
        Runnable student4 = () -> assetExam.submitExam("Adam", "Fractions");

        /* Initializing the threads for starting exam and students */
        Thread examStarterThread = new Thread(examStarter);
        Thread student1Thread = new Thread(student1);
        Thread student2Thread = new Thread(student2);
        Thread student3Thread = new Thread(student3);
        Thread student4Thread = new Thread(student4);

        /* Starting the threads of students for examination */
        examStarterThread.start();
        student1Thread.start();
        student2Thread.start();
        student3Thread.start();
        student4Thread.start();
    }
}


/* Expected Output

    ASSET exam started
Sam is submitting the exam paper: Fractions
Sam is answering question - What is the capital of France?
Answer - Answer for What is the capital of France?
Sam's answer submitted successfully
Sam is answering question - What is the largest planet in our solar system?
Answer - Answer for What is the largest planet in our solar system?        
Sam's answer submitted successfully
Sam is answering question - What is the square root of 25?
Answer - Answer for What is the square root of 25?
Sam's answer submitted successfully
Sam's exam paper submitted successfully.
Adam is submitting the exam paper: Fractions
Adam is answering question - What is the capital of France?
Answer - Answer for What is the capital of France?
Adam's answer submitted successfully
Adam is answering question - What is the largest planet in our solar system?
Answer - Answer for What is the largest planet in our solar system?        
Adam's answer submitted successfully
Adam is answering question - What is the square root of 25?
Adam's answer submitted successfully
Adam's exam paper submitted successfully.
Ram is submitting the exam paper: Fractions
Ram is answering question - What is the capital of France?
Answer - Answer for What is the capital of France?
Ram's answer submitted successfully
Ram is answering question - What is the largest planet in our solar system?Answer - Answer for What is the largest planet in our solar system?
Ram's answer submitted successfully
Ram is answering question - What is the square root of 25?
Answer - Answer for What is the square root of 25?
Ram's answer submitted successfully
Ram's exam paper submitted successfully.
Steve is submitting the exam paper: Fractions
Steve is answering question - What is the capital of France?
Answer - Answer for What is the capital of France?
Steve's answer submitted successfully
Steve is answering question - What is the largest planet in our solar system?
Answer - Answer for What is the largest planet in our solar system?
Steve's answer submitted successfully
Steve is answering question - What is the square root of 25?
Answer - Answer for What is the square root of 25?
Steve's answer submitted successfully
Steve's exam paper submitted successfully.

*/