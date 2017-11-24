import ApiHandler.ApiHandler;
import ApiHandler.XmlHandler;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.net.UnknownHostException;
import java.io.IOException;
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
            if (command != null && command[0].equals("/ip")) {
                //message.setText("/echo" + '\n' + "/reverse" + '\n' + "/add" + '\n'
                //        + "/spam" + '\n' + "/weather");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
                List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
                rowInline.add(new InlineKeyboardButton().setText("Ping").setCallbackData("ping_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Whois").setCallbackData("whois_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("IPlocation").setCallbackData("iplocation_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Portscanner").setCallbackData("portscanner_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Nslookup").setCallbackData("nslookup_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Domain").setCallbackData("domain_msg_text"));

                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setText("Funktionen:");
                setMsg(command[1]);
                message.setReplyMarkup(markupInline);

            } else if (command != null && command[0].equals("/ip")) {
                message.setText("Tippen Sie '/ip + Domain/IP' ein! \nDann Wählen Sie die Funktion aus, durch einen Klick auf einen Button");
            } else {
                message.setText("Tippen Sie '/ip + Domain/IP' ein! \nDann Wählen Sie die Funktion aus, durch einen Klick auf einen Button");
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

                String ip = getMsg();
                String ausgabe = null;
                try {
                    ausgabe = Ping.getPing(ip);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(ausgabe);
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (call_data.equals("whois_msg_text")) {

                String amk = getMsg();
                String whois_ausgabe = null;
                try {
                    whois_ausgabe = XmlHandler.getWhoIs(amk);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(whois_ausgabe);
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (call_data.equals("iplocation_msg_text")) {

                String ip = getMsg();
                ApiHandler api = new ApiHandler();
                String antwort = "";


                for (String str : api.getLocation(ip)) {
                    System.out.println(str);
                    antwort = str;
                }


                String[] splitloc = antwort.split(",", 2);
                float Breitengrad = Float.parseFloat(splitloc[0]);
                float Längengrad = Float.parseFloat(splitloc[1]);
                System.out.println(Breitengrad);
                System.out.println(Längengrad);


                SendLocation loc = new SendLocation().setLatitude(Breitengrad).setLongitude(Längengrad).setChatId(chat_idn);
                Weather wea = new Weather();


                try {
                    sendLocation(loc);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("Keine Nachricht");
                }
                EditMessageText new_message = null;
                try {
                    new_message = new EditMessageText()
                            .setChatId(chat_idn)
                            .setMessageId((int) message_id)
                            .setText("Hier befindet sich der Standort ihrer IP\n"
                                    + "Das Wetter ihrer IP: " + wea.getCurrentWeather(Breitengrad, Längengrad)
                                    + "\n Die Maximale Temperatuer beträgt: " + wea.getMaxTemp(Breitengrad, Längengrad)
                                    + "\n Die Minimale Temperatur beträgt: " + wea.getMinTemp(Breitengrad, Längengrad));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
            if (call_data.equals("portscanner_msg_text")) {

                String ip = getMsg();

                PortScanner pc = new PortScanner();

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id);


                new_message.setText("Ports werden gescannt. Bitte warten!");
                try {
                    editMessageText(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                pc.scanPort(ip);

                new_message.setText(String.valueOf("Offene Ports bei " + getMsg() + "-> " + pc.getOpenPorts()));
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
            if (call_data.equals("domain_msg_text")) {

                String nslookup = getMsg();

                Domain domain = new Domain();
                domain.performNSLookup(nslookup);

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_idn)
                        .setMessageId((int) message_id)
                        .setText(String.valueOf(domain.getInet()));
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
        return "htlnetbot";
    }

    public String getBotToken() {
        return "480278208:AAGOsa-2-JAW49mxve7ytERqOQNycnpR-wQ";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
