/*
* The StockPriceHandler sends stock price updates to the connected client over the socket connection using a PrintWriter.
* */

package techConnect.session4.src.StockPriceApp_IPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StockPriceHandler implements Runnable{
    private Socket clientSocket;
    private boolean flag = true;
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
            double[] prices = {2345.67, 178.45, 321.98, 3456.21};

            while (flag){
                // Send stock price updates to the client
                for(int i=0;i<stocks.length;i++){
                    String stockUpdate = stocks[i] + ":" + prices[i];
                    out.println(stockUpdate);
                    System.out.println("Sent stock price update to client: "+stockUpdate);
                }

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
