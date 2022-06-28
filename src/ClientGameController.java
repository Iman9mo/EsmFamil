import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ClientGameController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    Text timer;
    @FXML
    TextField name;
    @FXML
    TextField family;
    @FXML
    TextField city;
    @FXML
    TextField country;
    @FXML
    TextField object;
    @FXML
    TextField flower;
    @FXML
    TextField clothes;
    @FXML
    TextField food;
    @FXML
    TextField animal;
    @FXML
    TextField fruit;
    @FXML
    TextField car;


    public static void setSubjects(TextField... textField) {
        for (int i = 0; i < textField.length; i++) {
            if (!JoinController.subjects.contains(textField[i].getPromptText()))
                textField[i].setDisable(true);
        }
    }

    private int counter = 0;

    @FXML
    public void handleButtonAction() {
        if (MakeGameController.minute == 0) {
            timer.setVisible(false);
            return;
        }
        Timer tm = new Timer();
        tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter += 1;
                int sec = counter % 60;
                int min = counter / 60;
                min %= 60;
                timer.setText(String.format("%02d:%02d", min, sec));
                if (min == MakeGameController.minute) {
                    timer.setText("FINISH");
                    return;
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleButtonAction();
        setSubjects(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
    }

    public void goBack() throws Exception {
        JoinController.socket.close();
        Main main = new Main();
        main.changeScene("join.fxml");
    }

    public void exit() {
        System.exit(0);
    }
}
