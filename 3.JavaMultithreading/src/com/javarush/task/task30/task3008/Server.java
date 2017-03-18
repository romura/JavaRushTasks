package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GETMAN on 28.02.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    static void sendBroadcastMessage(Message message){
        for (Connection connection : connectionMap.values()){
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ne smogli otpravit soobshenie");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Vvedite port servera");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        ConsoleHelper.writeMessage("Server zapushen");

        while(true){
            try{
                Socket socket = serverSocket.accept();
                (new Handler(socket)).start();
            }
            catch (Exception e){
                serverSocket.close();
                ConsoleHelper.writeMessage(e.toString());
                break;
            }

        }

    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run(){
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage("Connection established with: " + socketAddress);
            String name = "";
            try(Connection connection = new Connection(socket)) {
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection, name);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }

            if (!name.isEmpty()){
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            }

            ConsoleHelper.writeMessage("соединение с удаленным адресом закрыто: " + socketAddress);

        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            String name = "";
            while(true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME){
                    name = message.getData();
                    if(name.isEmpty() || connectionMap.containsKey(name)){
                        continue;
                    }
                    else{
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
                else continue;
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (String entry : connectionMap.keySet()){
                if (!entry.equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED, entry));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                }
                else{
                    ConsoleHelper.writeMessage("ne yavlaetsa tekstom");
                }
            }
        }
    }
}
