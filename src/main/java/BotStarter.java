/**
 * Beispiel 02: Telegram Bot
 *
 * @author <PRE>
 * ID        date        description
 * ERMIN     11.10.2017  Neuerstellung
 * </PRE>
 */

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


public class BotStarter {

    /**
     * Wird beim Aufrufen des Programms gestartet, ist die Einstiegsmethode
     *
     * @param args StringArray
     * @throws TelegramApiRequestException Fehlermeldung sollte die Verbindung mit Telegram nicht funktionieren
     */
    public static void main(String args[]) throws TelegramApiRequestException {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new NetBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("Bot successfully started!");

    }

}
