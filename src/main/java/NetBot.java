import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class NetBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {


        }
    }

    public String getBotUsername() {
        return "ip_scann_bot";
    }

    public String getBotToken() {
        return "409778950:AAGaPG3OnomG8_skF1TU1ke1G5VBlDCGrlc";
    }
}
