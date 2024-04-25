package techConnect.session3.bankExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount{
    private double balance;
    private Lock lock; // defining the mutex (lock)

    public BankAccount(double initialBankBalance){
        this.balance = initialBankBalance;
        this.lock = new ReentrantLock(); // initializing mutex with reentrant lock
    }

    public void depositMoney(double amount){
        lock.lock(); // Acquiring lock (synchronization)
        try{
            balance += amount;
            System.out.println("Deposit of Rs. "+ amount +" successfully completed. New Balance - Rs. "+balance);
        }catch(Exception e){
            System.out.println("Error occured in depositing money - " + e);
        }finally{
            lock.unlock(); // Releasing lock (performing synchronization)
        }
    }

    public void withdrawMoney(double amount){
        lock.lock(); // Acquiring lock (synchronization)

        try{
            if(balance >= amount){
                balance -= amount;
                System.out.println("Withdrawal of Rs. "+ amount +" successfully completed. New Balance - Rs. "+balance);
            }
            else{
                throw new Error("Insufficient funds in account");
            }
        }finally{
            lock.unlock(); // Releasing lock (performing synchronization)
        }
    }
}