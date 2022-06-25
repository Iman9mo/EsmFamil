import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateController {
    @FXML
    TextField rounds;
    @FXML
    TextField port;
    public void setNumeric(){
        port.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                port.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void setNumeric1(){
        rounds.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rounds.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
