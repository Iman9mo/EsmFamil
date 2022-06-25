import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Five {
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
    static Text timer;

    public void setSubjects(){
        sub1.setText(MakeGameController.selected.get(0));
        sub2.setText(MakeGameController.selected.get(1));
        sub3.setText(MakeGameController.selected.get(2));
        sub4.setText(MakeGameController.selected.get(3));
        sub5.setText(MakeGameController.selected.get(4));
    }

    public void setTimer(){
//        TimeController timeController = new TimeController();
//        timer.setText(timeController.time.getCurrentTime());
    }
}
