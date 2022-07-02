import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ServerSocket server;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;
    private static Player player1 = new Player(WelcomeController.getName(), 0);
    private static Player player2;
    private static int turn = 1;
    private static boolean byTime = false;

    public Server() {
        Runnable runnable = () -> {
            try {
                server = new ServerSocket(MakeGameController.port1);
                System.out.println(server.getLocalPort());
                System.out.println("Server Created!");
                System.out.println("waiting for client...");
                Socket client = server.accept();
                oos = new ObjectOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
                System.out.println("Connected to New Client!");
                oos.writeObject(MakeGameController.selected);
                dos = new DataOutputStream(client.getOutputStream());
                dos.write(MakeGameController.round);
                dos.write(MakeGameController.minute);
                dos.writeBoolean(byTime);
                player2 = new Player(dis.readUTF(), 0);
                System.out.println(player1.getName() + "  " + player2.getName());
            } catch (IOException e) {
                System.out.println("problem in create server!");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static boolean isByTime() {
        return byTime;
    }

    public static void setByTime(boolean byTime) {
        Server.byTime = byTime;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn() {
        if (turn == 1)
            turn = 2;
        else
            turn = 1;
    }

    public static DataInputStream getDis() {
        return dis;
    }

    public static DataOutputStream getDos() {
        return dos;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }

    public static ObjectOutputStream getOos() {
        return oos;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void judgment(String serverWord, String subject) throws IOException {
        String clientWord = "null";
        while (clientWord.equals("null")) {
            if (dis.available() != 0) {
                clientWord = dis.readUTF();
                System.out.println(clientWord + " " + serverWord + " " + subject);
            }
        }
        boolean b1 = isContain(GameController.getLetterChar(), serverWord, subject);
        boolean b2 = isContain(GameController.getLetterChar(), clientWord, subject);
        if (b1 && b2) {
            if (serverWord.equals(clientWord)) {
                player1.setScore(player1.getScore() + 5);
                player2.setScore(player2.getScore() + 5);
            } else {
                player1.setScore(player1.getScore() + 10);
                player2.setScore(player2.getScore() + 10);
            }
        } else if (b1)
            player1.setScore(player1.getScore() + 10);
        else if (b2)
            player2.setScore(player2.getScore() + 10);
        System.out.println(player1.getScore() + " " + player2.getScore());
    }

    public static boolean isContain(char letter, String word, String subject) throws IOException {
        if (word.length() == 0 || word.charAt(0) != letter)
            return false;
        File file = new File("E:\\Advanced programming\\words\\" + subject + ".txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String s;
        while ((s = br.readLine()) != null) {
            if (word.equals(s)) {
                return true;
            }
        }
        fr.close();
        return false;
    }
}