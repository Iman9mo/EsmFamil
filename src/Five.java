import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Five implements Initializable {
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


    public void setSubjects(TextField... textField) {
        for (int i = 0; i < textField.length; i++) {
            if (!MakeGameController.selected.contains(textField[i].getPromptText()))
                textField[i].setDisable(true);
        }
    }

    private int counter = 0;
    MakeGameController mgc = new MakeGameController();

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
                counter += 10;
                int seg = counter % 60;
                int min = counter / 60;
                min %= 60;
                timer.setText(String.format("%02d:%02d", min, seg));
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
        Main main = new Main();
        main.changeScene("makeGame.fxml");
        MakeGameController.selected.clear();
        MakeGameController.counter = 0;
    }

    public void exit() {
        System.exit(0);
    }
}
