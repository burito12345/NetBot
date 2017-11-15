import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Nslookup {
   // private ArrayList<String> list;
    public String erg;
    public void performNSLookup(String nslookup) {
       // list = new ArrayList<String>();

        System.out.println(nslookup);

        try {

            InetAddress inetHost = InetAddress.getByName(nslookup);
            System.out.println("DNS: " + inetHost.getCanonicalHostName());

         //   list.add(inetHost.getHostAddress());
            erg = "Domain Name System: " + inetHost.getCanonicalHostName();


        } catch (UnknownHostException ex) {

            System.out.println("Unrecognized host");
        }
    }

    public String getInet(){ return erg;}

}
