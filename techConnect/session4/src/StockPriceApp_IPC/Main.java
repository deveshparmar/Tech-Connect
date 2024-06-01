/*
* Stock price app provides real time stock price updates which uses IPC using sockets
* to fetch stock data for clients.
* */

package techConnect.session4.src.StockPriceApp_IPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try(Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT)){
            System.out.println("Connected to Stock price server");

            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String stockUpdate;

            while ((stockUpdate=in.readLine())!=null){
                // Read stock price updates from the server
                System.out.println("Stock-Update: "+stockUpdate);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
