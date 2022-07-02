import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JoinController implements Initializable {
    @FXML
    TextField port;
    @FXML
    Button back;
    @FXML
    Button join;
    private int port1 = 0;
    private static int rounds = 0;
    static int minute = 0;
    private static boolean byTime;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;
    public static Socket socket;
    public static ArrayList<String> subjects = new ArrayList<>();

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

    public static boolean isByTime() {
        return byTime;
    }

    public static void setByTime(boolean byTime) {
        JoinController.byTime = byTime;
    }

    public void setNumeric() {
        port.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                port.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void goBack() throws Exception {
        Main main = new Main();
        main.changeScene("welcome.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setStyle("-fx-base: mistyrose;");
    }

    public void setJoin() throws Exception {
        port1 = Integer.parseInt(port.getText());
        socket = new Socket("localhost", port1);
        Main main = new Main();
        System.out.println("client joined");
        ois = new ObjectInputStream(socket.getInputStream());
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        subjects = (ArrayList<String>) ois.readObject();
        System.out.println(subjects);
        rounds =  dis.read();
        System.out.println(rounds);
        minute = dis.read();
        System.out.println(minute);
        byTime = dis.readBoolean();
        System.out.println(byTime);
        dos.writeUTF(WelcomeController.getName());
        main.changeScene("ClientGame.fxml");
    }

    public static int getRounds() {
        return rounds;
    }
}
