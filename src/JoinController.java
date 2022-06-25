import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class JoinController {
    @FXML
    TextField port;
    public void setNumeric(){
        port.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                port.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
