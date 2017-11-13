import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Nslookup {
    private ArrayList<String> list;

    public void performNSLookup(String nslookup) {
        list = new ArrayList<String>();

        System.out.println(nslookup);

        try {

            InetAddress inetHost = InetAddress.getByName(nslookup);
            String hostName = inetHost.getHostName();
            System.out.println("Die eingegebene Webseite war: " + hostName +
                    "\nDie IP der Internetseite ist: " + inetHost.getHostAddress()
                    + "\nDer Hostname: " + inetHost.getCanonicalHostName());
            list.add(hostName);
            list.add(inetHost.getHostAddress());
            list.add(inetHost.getCanonicalHostName());


        } catch (UnknownHostException ex) {

            System.out.println("Unrecognized host");
        }
    }

    public ArrayList<String> getInet(){ return list;}


}
