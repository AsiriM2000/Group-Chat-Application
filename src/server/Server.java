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
    public  static DataOutputStream outputStream;
    DataInputStream inputStream;

    Socket socket;
    ServerSocket serverSocket;
    final int port = 20001;
    String name;

    Server(){
        System.out.println("Server Start...");
        System.out.println();

        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);

            while (true){
                socket = serverSocket.accept();
                System.out.println("Client Accept...");
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                name = inputStream.readUTF();
                Client users = new Client(name,outputStream,inputStream);
                System.out.println("["+name + " is connected."+"]");
                System.out.println();
                clients.add(users);

                String message = name+ " has join the group.";
                List<Client> clientList = Server.clients;

                for (Client c : clientList){
                    DataOutputStream stream = c.getOutputStream();
                    stream.writeUTF(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
