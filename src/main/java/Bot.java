
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.CityService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class Bot extends TelegramLongPollingBot {

    private CityService city = new CityService();

    private static long chat_id;
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();


    private void calculator( Update update ) {
        chat_id = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        String text = update.getMessage().getText();

        try {
            sendMessage.setText("Вы ввели " + text);
            execute(sendMessage);

            sendMessage.setText("Результат " + getMsg(text));
            execute(sendMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private double getMsg( String msg ) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            return Double.parseDouble(engine.eval(msg).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private void sendMessage( Message msg, String text ) {
        SendMessage sendmessage = new SendMessage();
        sendmessage.enableMarkdown(true);
        sendmessage.setChatId(msg.getChatId().toString());
        sendmessage.setText(text);
        try {
            execute(sendmessage);

        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }

    public void onUpdateReceived( Update update ) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

            if (update.getMessage().getText().equals("Random")) {
                try {
                    sendMessage.setText(city.startGame());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            city.answer(update.getMessage().getText());
        }

    public String getBotUsername() {
        return "DemasBot";
    }

    public String getBotToken() {
        return "950067716:AAF6qjNFtkPXolxTGtwPWjz3sYt9CGOpT6o";
    }
}





