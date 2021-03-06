package ApiHandler;


import ApiHandler.json.NetAPI;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ApiHandler {


    private static OkHttpClient client = new OkHttpClient();


  /*  public static void main(String args[]) {

        for(String str : getLocation("ermin.eu")){
            System.out.println(str);
        }


    } */

    public static String getJSON(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String[] getLocation(String address) {


        String json = null;

        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Geht nicht Bruda");
        } catch (NullPointerException e){
            System.out.println("Kaputt");
        }

        String newIp = ip.getHostAddress();

        try {
            json = getJSON("https://ipinfo.io/" + newIp + "/json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
        System.out.println("Kaputt");
    }

        Gson gson = new Gson();

        NetAPI netAPI = gson.fromJson(json, NetAPI.class);

        return new String[]{
                netAPI.getLoc()
        };
    }

}