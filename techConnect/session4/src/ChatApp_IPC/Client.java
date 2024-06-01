/*
* Chat client connects to server , sends messages to server,
* and receives messages from the server
* */

package techConnect.session4.src.ChatApp_IPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String serverAddress, int port) {
        try {
            // Connect to the server at the given address and port
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to server");

            // Create output and input streams for communication
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start a new thread to handle incoming messages from the server
            new Thread(new IncomingMessageHandler()).start();

            // Read user input from the console and send it to the server
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput=stdIn.readLine())!=null){
                out.println(userInput); // performs IPC
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Inner class to handle incoming messages from the server
    private class IncomingMessageHandler implements Runnable {

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message);  //performs IPC
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}


