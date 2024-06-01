/*
*The server accepts incoming client connections using a ServerSocket and
* creates a new StockPriceHandler instance for each connected client, enabling communication
* between server and clients
* */
package techConnect.session4.src.StockPriceApp_IPC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockPriceServer {
    private static final int PORT = 8080;
    private static boolean isServerRunning = true;

    public static void main(String[] args) {
        // Create an executor service
        ExecutorService executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Stock price server started on port - "+PORT);

            while (isServerRunning){
                Socket clientSocket = serverSocket.accept();
                // Accept incoming client connections
                System.out.println("New Client connected: "+clientSocket.getInetAddress().getHostAddress());
                // Create a new thread to handle the client connection
                executorService.execute(new StockPriceHandler(clientSocket));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();  // Shutdown the executor service
        }
    }

    public static void stopServer(){
        isServerRunning=false;
        System.out.println("Stopping server");
    }
}
