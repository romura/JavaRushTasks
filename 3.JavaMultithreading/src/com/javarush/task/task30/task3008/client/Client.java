package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by GETMAN on 01.03.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this){
                wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Vozniklo isklu4enie");
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
        }
        else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        while (clientConnected){
            String message = ConsoleHelper.readString();
            if (message.equals("exit")){
                break;
            }
            else{
                if (shouldSendTextFromConsole()) sendTextMessage(message);
            }
        }
    }

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Enter server adress");
        String adress = ConsoleHelper.readString();
        return adress;
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Enter server port");
        int port = ConsoleHelper.readInt();
        return port;
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Enter your name");
        String name = ConsoleHelper.readString();
        return name;
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("error in send text");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread{
        public void run(){
            String adress = getServerAddress();
            int port = getServerPort();
            Socket socket = null;
            try{
                socket = new Socket(adress, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e){
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }


        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("User added: " + userName);
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String name = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, name));
                }
                else  if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT || message.getType() == MessageType.USER_REMOVED || message.getType() == MessageType.USER_ADDED){
                    if (message.getType().equals(MessageType.TEXT)){
                        processIncomingMessage(message.getData());
                    }
                    else if (message.getType().equals(MessageType.USER_ADDED)){
                        informAboutAddingNewUser(message.getData());
                    }
                    else if (message.getType().equals(MessageType.USER_REMOVED)){
                        informAboutDeletingNewUser(message.getData());
                    }
                }
                else {
                    throw new IOException("Unexpected MessageType");
                }

            }

        }
    }
}
