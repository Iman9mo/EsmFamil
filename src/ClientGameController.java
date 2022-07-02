import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.io.IOException;
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
    @FXML
    Button startGame;
    @FXML
    Text start = new Text(" ");
    @FXML
    TextField letter;
    @FXML
    Text turn;
    @FXML
    Button finish;
    @FXML
    Text totalScore;
    @FXML
    Button nextRound;
    @FXML
    Text result;
    private static int countRound = 1;
    private static int turns = 1;


    public static void setSubjects(TextField... textField) {
        for (int i = 0; i < textField.length; i++) {
            if (!JoinController.subjects.contains(textField[i].getPromptText()))
                textField[i].setDisable(true);
        }
    }

    private int counter = 0;

    @FXML
    public void handleButtonAction() {
        if (JoinController.minute == 0) {
            timer.setVisible(false);
            return;
        }
        Timer tm = new Timer(true);
        tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter += 1;
                int sec = counter % 60;
                int min = counter / 60;
                min %= 60;
                timer.setText(String.format("%02d:%02d", min, sec));
                if (min == JoinController.minute) {
                    timer.setText("FINISH");
                    try {
                        tm.cancel();
                        tm.purge();
                        finishReceived(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFinish();
        setSubjects(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
        if (turns == 1) {
            turn.setText("نوبت بازیکن1");
            start.setText("در انتظار تصمیم بازیکن دیگر...");
            startGame.setDisable(true);
            letter.setDisable(true);
            Runnable runnable = () -> {
                try {
                    String letter = JoinController.getDis().readUTF();
                    start.setText(" حرف " + letter + " است ");
                    start.setFill(Color.BLUE);
                    handleButtonAction();
                    waitForFinish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } else {
            turn.setText("نوبت بازیکن2");
        }
    }

    public void goBack() throws Exception {
        JoinController.socket.close();
        Main main = new Main();
        main.changeScene("join.fxml");
    }

    public void exit() {
        System.exit(0);
    }

    public void setStart() throws IOException {
        if (turns == 2) {
            if (letter.getText().equals("")) {
                start.setText("حرف را وارد نکرده اید");
                start.setFill(Color.RED);
            } else if (letter.getText().length() > 1) {
                start.setText("ورودی شما باید یک حرف باشد");
                start.setFill(Color.RED);
            } else {
                JoinController.getDos().writeUTF(String.valueOf(letter.getText().charAt(0)));
                handleButtonAction();
                waitForFinish();
            }
        }
    }

    public void setFinish() {
        if (JoinController.isByTime())
            finish.setDisable(true);
        else
            timer.setVisible(false);
    }

    public void finishBtn() throws IOException {
        finisher(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
    }

    public void finisher(TextField... textFields) throws IOException {
        JoinController.getDos().writeUTF("finish");
        for (int i = 0; i < textFields.length; i++) {
            if (JoinController.subjects.contains(textFields[i].getPromptText()))
                JoinController.getDos().writeUTF(textFields[i].getText());
        }
        totalScore.setText(JoinController.getDis().readInt() + "");
    }

    public void finishReceived(TextField... textFields) throws IOException {
        for (int i = 0; i < textFields.length; i++) {
            if (JoinController.subjects.contains(textFields[i].getPromptText())) {
                JoinController.getDos().writeUTF(textFields[i].getText());
                //System.out.println(textFields[i].getText() + "  " + textFields[i].getPromptText());
            }
        }
        totalScore.setText(JoinController.getDis().readInt() + "");
    }

    public void waitForFinish() {
        if (JoinController.isByTime())
            return;
        Runnable runnable = () -> {
            String message = "null";
            while (!message.equals("finish")) {
                try {
                    if (JoinController.getDis().available() != 0) {
                        message = JoinController.getDis().readUTF();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //System.out.println("before");
                finishReceived(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
                //System.out.println("after");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setNextRound() throws Exception {
        if (countRound < JoinController.getRounds()) {
            countRound++;
            setTurn();
            Main main = new Main();
            main.changeScene("ClientGame.fxml");
        }
        else {
            int score1 = JoinController.getDis().readInt();
            int score2 = JoinController.getDis().readInt();
            result.setText("Game finished!\nTotal scores\nplayer1: " + score1 + "\nplayer2: " + score2);
            result.setFill(Color.PURPLE);
            result.setVisible(true);
        }
    }

    public void setTurn() {
        if (turns == 1)
            turns = 2;
        else
            turns = 1;
    }
}

