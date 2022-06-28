import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket server;
    int serverPort = 123;
    public Server() {
        try {
            server = new ServerSocket(serverPort);
            System.out.println(server.getLocalPort());
            System.out.println("Server Created!");
            System.out.println("waiting for client...");
            while (true) {
                Socket client = server.accept();
                System.out.println("Connected to New Client!");
                System.out.println("waiting for new client ...");
            }
        } catch (IOException e) {
            System.out.println("problem in create server!");
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}