import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class NetBot extends TelegramLongPollingBot {



        private String msg = "";

        /**
         * Wird Ausgeführt wenn der Telegram Bot etwas erhält
         *
         * @param update Telegram Update Objetk
         */
    public void onUpdateReceived(Update update) {

        // Kontrolle ob eine Nachricht & Nachrichtentext gekommen ist
        if (update.hasMessage() && update.getMessage().hasText()) {

            // Variablen definieren & Instanzen erstellen
            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            long user_id = update.getMessage().getChat().getId();

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);


            String[] command = message_text.split(" ", 2);      //String abtrennen


            //Abfragen nach Kommandos
            if (command != null && command[0].equals("/start")) {
                //message.setText("/echo" + '\n' + "/reverse" + '\n' + "/add" + '\n'
                //        + "/spam" + '\n' + "/weather");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
                List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
                rowInline.add(new InlineKeyboardButton().setText("Ping").setCallbackData("ping_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Whois").setCallbackData("whois_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("IPlocation").setCallbackData("iplocation_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Portscanner").setCallbackData("portscanner_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("nslookup").setCallbackData("nslookup_msg_text"));


                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setText("Funktionen:");
                setMsg(command[1]);
                message.setReplyMarkup(markupInline);

            }

            else {
                message.setText("Diese Funktion wird nicht unterstüzt.\n " +
                        "Tippen Sie '/start + ip etc.' ");
            }

            //Bevor Nachricht gesendet wird Antwort in Variable speichern


            try {
                sendMessage(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_idn = update.getCallbackQuery().getMessage().getChatId();


            if (call_data.equals("ping_msg_text")) {
                String ping = "ping folgt";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(ping);
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (call_data.equals("whois_msg_text")) {
                String whois = "whois folgt";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(whois);
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (call_data.equals("iplocation_msg_text")) {
                String iplocation = "iplocation folgt!";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(iplocation);
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (call_data.equals("portscanner_msg_text")) {

                String ip = getMsg();

                PortScanner pc = new PortScanner();
                pc.scanPort(ip);




                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(String.valueOf("Offene Ports bei " + getMsg() + "-> " + pc.getOpenPorts()));
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (call_data.equals("nslookup_msg_text")) {

                String nslookup = getMsg();

                Nslookup ns = new Nslookup();
                ns.performNSLookup(nslookup);
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(String.valueOf(ns.getInet()));
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("Tippen Sie /help ein!");
        }
    }



    public String getBotUsername() {
        return "ip_scann_bot";
    }

    public String getBotToken() {
        return "409778950:AAGaPG3OnomG8_skF1TU1ke1G5VBlDCGrlc";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
