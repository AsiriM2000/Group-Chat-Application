package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Client> clients;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    Socket socket;
    ServerSocket serverSocket;
    static int port;
    String name;

    Server(){
        System.out.println("Server Start...");

        port = 20001;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);

            while (true){
                socket = serverSocket.accept();
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                name = inputStream.readUTF();
                Client users = new Client();
                System.out.println("["+name + " is connected."+"]");
                clients.add(users);

                String message = "["+name+ " has join the group."+"]";
                System.out.println(message);
                List<Client> clientList = Server.clients;

                for (Client c : clientList){
                    DataOutputStream stream = null;
                    stream.writeUTF(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
