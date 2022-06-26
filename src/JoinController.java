import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Initializable {
    @FXML
    TextField port;
    @FXML
    Button back;
    @FXML
    Button join;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setStyle("-fx-base: mistyrose;");
    }
}
