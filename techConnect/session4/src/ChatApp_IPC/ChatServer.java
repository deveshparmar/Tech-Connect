/*
* Chat Server listens for client connections, receives messages from clients
* and broadcasts to all connected clients
* */

package techConnect.session4.src.ChatApp_IPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    // Set to keep track of all connected clients
    private static final Set<ClientHandler> clientHandlers = new HashSet<>();
    private static boolean flag = true;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            System.out.println("Chat Server started");

            // Start a thread to handle server-to-client messages
            new Thread(new ServerMessageHandler()).start();

            // Continuously accept new client connections
            while (flag){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                // Start a new thread to handle client communication
                new Thread(clientHandler).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void stopRunning(){
        flag = false;
    }

    // Broadcast a message to all connected clients except the sender
    static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clientHandlers) {
            if (client != sender) {
                client.sendMessage(message);  // performs IPC
            }
        }
    }

    static void broadcastMessage(String message) {
        for (ClientHandler client : clientHandlers) {
            client.sendMessage(message);
        }
    }


    // Inner class to handle client communication
    static class ClientHandler implements Runnable{
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                // Create output and input streams for communication
                out = new PrintWriter(socket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;

                // Continuously read messages from the client
                while ((message=in.readLine())!=null){
                    System.out.println("Received: "+message);

                    // Broadcast the received message to other clients
                    broadcastMessage(message,this);  //performs IPC
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                // Remove the client handler from the set
                clientHandlers.remove(this);
            }
        }

        void sendMessage(String message){
            out.println(message);
        }
    }

    static class ServerMessageHandler implements Runnable {
        public void run() {
            try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                String serverInput;
                while ((serverInput = stdIn.readLine()) != null) {
                    broadcastMessage(serverInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


