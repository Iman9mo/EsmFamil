import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket server;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    public Server() {
        try {
            server = new ServerSocket(MakeGameController.port1);
            System.out.println(server.getLocalPort());
            System.out.println("Server Created!");
            System.out.println("waiting for client...");
            Socket client = server.accept();
            oos = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Connected to New Client!");
            oos.writeObject(MakeGameController.selected);
        } catch (IOException e) {
            System.out.println("problem in create server!");
        }
    }
}