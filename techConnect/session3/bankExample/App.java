package techConnect.session3.bankExample;
/* Scenerio of Bank where different accountholder withdraws , deposits the money using multithreading (Used lock)*/

public class App {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(10000);

        Runnable depositer = () ->{
            for(int i = 0;i<5;i++){
                bankAccount.depositMoney(1000);
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        Runnable withdrawer = () ->{
            for(int i = 0;i<5;i++){
                bankAccount.withdrawMoney(2000);
                try{
                    Thread.sleep(700);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        Thread depositerThread = new Thread(depositer);
        Thread withdrawerThread = new Thread(withdrawer);

        depositerThread.start();
        withdrawerThread.start();
    }
}
