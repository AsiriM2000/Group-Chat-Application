package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    public TextField clientName;
    public AnchorPane main;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Data.name = clientName.getText();

        Stage stage = (Stage) main.getScene().getWindow();
        Parent root = FXMLLoader.load(ChatRoom.class.getResource("../view/chat-room.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle(Data.name);
        stage.setResizable(false);
        stage.show();
    }
}
