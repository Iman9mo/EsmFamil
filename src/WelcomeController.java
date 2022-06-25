import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WelcomeController {

    @FXML
    TextField text;
    @FXML
    Text error;
    static String name;

    public void create() throws Exception {
        name = text.getText();
        System.out.println(name);
        Main main = new Main();
        if (name.equals("")) {
            error.setText("لطفا نام خود را وارد کنید");
            error.setFill(Color.RED);
        } else
            main.changeScene("makeGame.fxml");
    }

    public void join() throws Exception {
        name = text.getText();
        System.out.println(name);
        Main main = new Main();
        main.changeScene("join.fxml");
    }
}
