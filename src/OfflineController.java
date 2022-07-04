import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class OfflineController implements Initializable {
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
    Text letter;
    @FXML
    Text totalScore;
    @FXML
    Button nextRound;
    @FXML
    Text result;
    @FXML
    Button start;
    @FXML
    ProgressBar progressBar;
    private int counter = MakeOfflineController.getTime();
    private double time = MakeOfflineController.getTime();
    private static char letterChar;
    private char[] chars = {'ا', 'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ', 'د', 'ذ', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ک', 'گ', 'ل', 'م', 'ن', 'و', 'ه', 'ی', 'ص', 'ض', 'س', 'ش', 'ر', 'ز'};
    private Random random = new Random();
    private Player player = new Player("", 0);

    public static void setSubjects(TextField... textField) {
        for (int i = 0; i < textField.length; i++) {
            if (!MakeOfflineController.getSelected().contains(textField[i].getPromptText()))
                textField[i].setDisable(true);
        }
    }

    @FXML
    public void handleButtonAction() {
        timer.setFill(Color.BROWN);
        Timer tm = new Timer(true);
        tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter--;
                int sec = counter % 60;
                int min = counter / 60;
                min %= 60;
                timer.setText(String.format("%02d:%02d", min, sec));
                progressBar.setProgress(counter / time);
                if (counter == 0) {
                    timer.setText("FINISH");
                    try {
                        judgment(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tm.cancel();
                    tm.purge();
                    return;
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSubjects(name, family, clothes, car, city, country, flower, food, object, animal, fruit);
        progressBar.setProgress(1.0);
    }

    public void goBack() throws Exception {
        Main main = new Main();
        main.changeScene("makeOfflineGame.fxml");
        MakeOfflineController.getSelected().clear();
        MakeOfflineController.setCounter(0);
    }

    public void setStart() {
        int index = random.nextInt(31);
        System.out.println(chars.length);
        letterChar = chars[index];
        letter.setText(letterChar + "");
        letter.setFill(Color.BLUE);
        start.setDisable(true);
        handleButtonAction();
    }

    public void setNextRound() throws Exception {
        MakeOfflineController.getSelected().clear();
        MakeOfflineController.setCounter(0);
        Main main = new Main();
        main.changeScene("makeOffline.fxml");
    }

    public void judgment(TextField... textFields) throws IOException {
        for (int i = 0; i < textFields.length; i++) {
            if (isContain(letterChar, textFields[i].getText(), textFields[i].getPromptText()))
                player.setScore(player.getScore() + 10);
            textFields[i].setDisable(true);
        }
        totalScore.setText(player.getScore() + "");
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
