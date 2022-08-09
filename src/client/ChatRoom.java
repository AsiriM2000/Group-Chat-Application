package client;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.io.*;
import java.net.Socket;

public class ChatRoom {

    public TextArea message_area;
    public TextField message_type;

    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String name = Data.name;

    public void initialize() {
        message_area.setEditable(false);

        try {
            socket = new Socket("localhost", 20001);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());

            outputStream.writeUTF(Data.name);

            new Thread(() -> {
                try {

                    while (true) {
                        String message = inputStream.readUTF();
                        if (message.contains(name)) {
                            continue;
                        }
                        message_area.appendText(message+"\n");
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }

            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void message_send_OnAction() {
        try {
            String msg = name+" : "+message_type.getText().trim();

            message_area.appendText(msg+"\n");
            outputStream.writeUTF(msg);
            outputStream.flush();

            message_type.clear();
            message_type.requestFocus();

        } catch (IOException e) {
        }
    }

    public void key_entered(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            message_send_OnAction();
        }
    }
}
