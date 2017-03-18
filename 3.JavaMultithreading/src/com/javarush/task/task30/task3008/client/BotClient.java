package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by GETMAN on 02.03.2017.
 */
public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int number = (int) (Math.random() * 100);
        return "date_bot_" + String.valueOf(number);
    }

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] array = null;
            SimpleDateFormat simpleDateFormat = null;
            try{
                array = message.split(": ");
            }
            catch (Exception e){

            }
            if (array.length == 2){
                switch (array[1]){
                    case "дата" : simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день" : simpleDateFormat = new SimpleDateFormat("d");
                        break;
                    case "месяц" : simpleDateFormat = new SimpleDateFormat("MMMM");
                        break;
                    case "год" : simpleDateFormat = new SimpleDateFormat("YYYY");
                        break;
                    case "время" : simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час" : simpleDateFormat = new SimpleDateFormat("H");
                        break;
                    case "минуты" : simpleDateFormat = new SimpleDateFormat("m");
                        break;
                    case "секунды" : simpleDateFormat = new SimpleDateFormat("s");
                        break;
                }

                if(simpleDateFormat != null){
                    sendTextMessage(String.format("Информация для %s: %s", array[0], simpleDateFormat.format(Calendar.getInstance().getTime())));
                }
            }
        }
    }
}
