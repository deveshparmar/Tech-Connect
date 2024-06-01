/*
* The StockPriceHandler sends stock price updates to the connected client over the socket connection using a PrintWriter.
* */

package techConnect.session4.src.StockPriceApp_IPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Random;

public class StockPriceHandler implements Runnable{
    private Socket clientSocket;
    private boolean flag = true;
    private static final Random random = new Random();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public StockPriceHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Simulated stock price updates (replace with actual data fetching logic)
            String[] stocks = {"GOOGLE", "TCS", "WIPRO", "AMAZON"};

            while (flag){
                Runnable task = ()->{
                    String stock = stocks[random.nextInt(stocks.length)];
                    double price = 100 + (500-100)*random.nextDouble();
                    String formattedPrice = decimalFormat.format(price);
                    String stockUpdate = stock + ":" + formattedPrice;
                    out.println(stockUpdate);
                    System.out.println("Sent stock price update to client: "+stockUpdate);
                };
                task.run();
                // Wait for a few seconds before sending the next update
                Thread.sleep(5000);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            try{
                clientSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void stopRunning(){
        flag  = false;
    }
}
