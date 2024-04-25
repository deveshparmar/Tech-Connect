package techConnect.session3.bankExample;
/* Scenerio of Bank where different accountholder withdraws , deposits the money using multithreading (Used lock)*/

public class App {
    public static void main(String[] args) {

        /* Creating the Bank account with initial bank balance */
        BankAccount bankAccount = new BankAccount(10000);

        /* Runnable interface for depositing money */
        Runnable depositer = () ->{
            for(int i = 0;i<5;i++){
                bankAccount.depositMoney(1000); // deposting the money
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
         /* Runnable interface for withdrawing money */
        Runnable withdrawer = () ->{
            for(int i = 0;i<5;i++){
                bankAccount.withdrawMoney(2000); // withdrawing the money
                try{
                    Thread.sleep(700);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
         /* Creating threads for depositing and withdrawing the money */
        Thread depositerThread = new Thread(depositer);
        Thread withdrawerThread = new Thread(withdrawer);

        /* Stating the threads for withdrawal and deposition of money */
        depositerThread.start();
        withdrawerThread.start();
    }
}

/* Expected Output
    Deposit of Rs. 1000.0 successfully completed. New Balance - Rs. 11000.0
    Withdrawal of Rs. 2000.0 successfully completed. New Balance - Rs. 9000.0
    Deposit of Rs. 1000.0 successfully completed. New Balance - Rs. 10000.0
    Withdrawal of Rs. 2000.0 successfully completed. New Balance - Rs. 8000.0
    Deposit of Rs. 1000.0 successfully completed. New Balance - Rs. 9000.0
    Withdrawal of Rs. 2000.0 successfully completed. New Balance - Rs. 7000.0
    Deposit of Rs. 1000.0 successfully completed. New Balance - Rs. 8000.0
    Deposit of Rs. 1000.0 successfully completed. New Balance - Rs. 9000.0
    Withdrawal of Rs. 2000.0 successfully completed. New Balance - Rs. 7000.0
    Withdrawal of Rs. 2000.0 successfully completed. New Balance - Rs. 5000.0
 */
