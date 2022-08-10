package client;


import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatRoom extends Window {

    public TextArea message_area;
    public TextField message_type;
    public Label lblName;
    public ImageView camera;
    public Pane send_pane;
    public Pane emoji_pane;
    public Pane emoji_view_pane;

    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String name = Data.name;

    public void initialize() {
        camera.setOnMouseClicked(event -> {

        });

        message_area.setEditable(false);
        lblName.setText(name);

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

                        message_area.appendText(message + "\n");
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }

                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
                    message_area.appendText(bufferedImage.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void message_send_OnAction() {
        try {
            String msg = name + " : " + message_type.getText().trim();

            message_area.appendText(msg + "\n");
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

    public void emoji_clicked(MouseEvent mouseEvent) {
        emoji_pane.setVisible(true);
    }

    public void back_clicked(MouseEvent mouseEvent) {
        emoji_pane.setVisible(false);
    }
}
