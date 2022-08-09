package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    String name;
    String message;

    private DataOutputStream outputStream;

    public DataOutputStream getOutputStream(){
        return outputStream;
    }

    Client(String name,DataOutputStream outputStream,DataInputStream inputStream){
        this.name  = name;
        this.outputStream = outputStream;

        new Thread(() -> {
            try {
                while (true){
                    message = inputStream.readUTF();
                    List<Client> clients = Server.clients;

                    for (Client c : clients){
                        DataOutputStream stream = c.getOutputStream();
                        stream.writeUTF(message);
                    }
                }
            }catch (IOException E){

                try {
                    inputStream.close();
                    outputStream.close();
                    Server.clients = Server.clients.stream().filter(e->{
                        if (!(e == this)){
                            String message_exit = name+ " exit group.";

                            try {
                                e.getOutputStream().writeUTF(message_exit);
                            }catch (IOException eee){}
                        }
                        return !(e == this);
                    }).collect(Collectors.toList());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }).start();
    }
}
