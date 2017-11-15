package ApiHandler;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XmlHandler {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String[] getWhoIs(String link) throws IOException {
        XmlHandler example = new XmlHandler();
        String response = example.run("http://xml.utrace.de/?query=" + link);

        response = response.replaceAll("<.*?>", "");

        String lines[] = response.split("\\r?\\n");

        return lines;
    }
}
