import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    TextField text;
    @FXML
    Text error;
    @FXML
    Button joinServer;
    @FXML
    Button createServer;
    static String name;

    public void create() throws Exception {
        name = text.getText();
        System.out.println(name);
        Main main = new Main();
        if (name.equals("")) {
            error.setText("لطفا نام خود را وارد کنید");
            error.setFill(Color.RED);
        } else
            main.changeScene("makeGame.fxml");
    }

    public void join() throws Exception {
        name = text.getText();
        if (name.equals("")) {
            error.setText("لطفا نام خود را وارد کنید");
            error.setFill(Color.RED);
        } else {
            System.out.println(name);
            Main main = new Main();
            main.changeScene("join.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        joinServer.setStyle("-fx-background-color:\n" +
                "        linear-gradient(#f0ff35, #a9ff00),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
                "    -fx-background-radius: 6, 5;\n" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-text-fill: #395306;");
        createServer.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    }
}
