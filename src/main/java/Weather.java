
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;


public class Weather {

    //Instanz von OpenWeatherMap erstellen, lib durch pom.xml eingefügt
    OpenWeatherMap owm = new OpenWeatherMap("e8fbcb184ad74e0e05f5bc7f2a7217dc");

    /**
     * Gibt Aktuelle Wetterlage zurück
     *
     *
     * @return current die Aktuelle Wetterlage
     * @throws IOException  Fehler abfangen für IO
     * @throws JSONException Fehler abfangen für JSON
     */
    public String getCurrentWeather(float Breitengrad, float Längengrad) throws IOException, JSONException {
        CurrentWeather cwd = owm.currentWeatherByCoordinates(Breitengrad,Längengrad);

        String current = cwd.getWeatherInstance(0).getWeatherDescription();


        return current;
    }

    /**
     * Gibt die Maximale Temperatur der Eingegebenen Stadt zurück
     *
     *
     * @return maxTemp maximale Temperatur
     * @throws IOException   Fehler abfangen für IO
     * @throws JSONException Fehler abfangen für JSON
     */
    public float getMaxTemp(float Breitengrad, float Längengrad) throws IOException, JSONException {
        CurrentWeather cwd = owm.currentWeatherByCoordinates(Breitengrad, Längengrad);
        float maxTemp = 0;
        if (cwd.hasCoordInstance() == true) {
            maxTemp = convertTemp(cwd.getMainInstance().getMaxTemperature());
        } else {
            maxTemp = 1000;
        }

        return maxTemp;
    }

    /**
     * Gibt die Minimale Temperatur der Eingegebenen Stadt zurück
     *
     *
     * @return minTemp minimale Temperatur
     * @throws IOException   Fehler abfangen für IO
     * @throws JSONException Fehler abfangen für JSON
     */
    public float getMinTemp(float Breitengrad, float Längengrad) throws IOException, JSONException {
        CurrentWeather cwd = owm.currentWeatherByCoordinates(Breitengrad, Längengrad);
        float minTemp = 0;
        if (cwd.hasCoordInstance() == true) {
            minTemp = convertTemp(cwd.getMainInstance().getMinTemperature());
        } else {
            minTemp = 1000;
        }


        return minTemp;
    }

    /**
     * Konventiert °F in °C um
     *
     * @param tempF Temperatur in Fahrenheit
     * @return Temperatur in Celsius
     */
    public static float convertTemp(float tempF) {
        return Math.round((float) ((tempF - 32) / 1.8));
    }


}
