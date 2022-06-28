import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {
    private static Stage stage;
    static Socket socket;
    static InputStream inputStream;
    static OutputStream outputStream;
    static DataInputStream in;
    static DataOutputStream out;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("اسم و فامیل");
        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        socket = new Socket("localhost", 123);
        System.out.println(socket.getLocalSocketAddress());
        outputStream = socket.getOutputStream();
        in = new DataInputStream(inputStream);
        out = new DataOutputStream(outputStream);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
    }
}
