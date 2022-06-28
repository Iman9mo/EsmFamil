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
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    public static Socket socket;
    public static ArrayList<String> subjects = new ArrayList<>();

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
        subjects = (ArrayList<String>) ois.readObject();
        System.out.println(subjects);
        main.changeScene("ClientGame.fxml");
    }
}
