import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ClientInitialize extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/login.fxml"))));
        primaryStage.setTitle("Live Chat");
        Image image = new Image("assests/chat.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}
