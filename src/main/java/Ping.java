import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Leon Fucking Bahl on 10.11.2017.
 */
public class Ping {

    public static String getPing(String ipAddress) throws IOException {
        int ping_success = 0;
        int ping_fail = 0;
        long currentTime;

        InetAddress inet = InetAddress.getByName(ipAddress);
        ArrayList<Long> time = new ArrayList<Long>();

        for (int i = 0; i < 5; i++) {
            currentTime = System.currentTimeMillis();
            if (inet.isReachable(1000) == true) {
                ping_success++;
                time.add(System.currentTimeMillis() - currentTime);
            } else {
                ping_fail++;

            }
        }

        ArrayList<String> ping = new ArrayList<String>();
        ping.add("Host war " + ping_success + " Mal erreichbar, " + ping_fail + " Mal war er NICHT erreichbar.");
        if (ping_success > 0) {
            long time_total = 0;
            for (int i = 0; i < time.size(); ++i) {
                time_total = time_total + time.get(i);
            }
            long time_average = time_total / time.size();
            ping.add("Durchschnittszeit eines Pings: " + time_average + " Milisekunden");
        }
        StringBuilder sb = new StringBuilder();
        for (String s : ping) {
            sb.append(s);
            sb.append("\t");
        }
        return sb.toString();
    }
}