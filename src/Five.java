import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Five implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text sub1;
    @FXML
    private Text sub2;
    @FXML
    private Text sub3;
    @FXML
    private Text sub4;
    @FXML
    private Text sub5;
    @FXML
    Text timer;

    public void setSubjects() {
        sub1.setText(MakeGameController.selected.get(0));
        sub2.setText(MakeGameController.selected.get(1));
        sub3.setText(MakeGameController.selected.get(2));
        sub4.setText(MakeGameController.selected.get(3));
        sub5.setText(MakeGameController.selected.get(4));
    }

    private int counter = 0;
    MakeGameController mgc = new MakeGameController();

    @FXML
    public void handleButtonAction(MouseEvent event) {
        Timer tm = new Timer();
        tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter += 10;
                int seg = counter % 60;
                int min = counter / 60;
                min %= 60;
                timer.setText(String.format("%02d:%02d", min, seg));
                if (min == MakeGameController.minute)
                    timer.setText("FINISH");
            }
        }, 1000, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void goBack() throws Exception {
        Main main = new Main();
        main.changeScene("makeGame.fxml");
        MakeGameController.selected.clear();
        MakeGameController.counter = 0;
    }

    public void exit(){
        System.exit(0);
    }
}
