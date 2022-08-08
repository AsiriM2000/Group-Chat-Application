package client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatRoom {

    public TextArea message_area;
    public TextField message_type;

    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public void message_send_OnAction(ActionEvent actionEvent) {
    }
}
