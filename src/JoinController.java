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
    public void goBack() throws Exception {
        Main main = new Main();
        main.changeScene("welcome.fxml");
    }
}
