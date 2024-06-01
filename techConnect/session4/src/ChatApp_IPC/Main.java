/*
* Chat application where multiple clients can send messages to each other through server.
* IPC is used to manage communication between server and clients*/
package techConnect.session4.src.ChatApp_IPC;

public class Main {
    public static void main(String[] args) {
        final String hostName= "localhost";
        final int port = 8080;
        // driver code
        Client client = new Client(hostName,port);
    }
}