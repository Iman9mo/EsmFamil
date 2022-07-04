import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {
    private static Stage stage;
    private static Media media = new Media(new File("music.mp3").toURI().toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("اسم و فامیل");
        stage = primaryStage;
        stage.getIcons().add(new Image("w.jpg"));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(1));
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
