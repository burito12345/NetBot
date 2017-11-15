import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Domain {
    //private ArrayList<String> list;
    public String erg;
    public void performNSLookup(String nslookup) {
        //list = new ArrayList<String>();

        System.out.println(nslookup);

        try {

            InetAddress inetHost = InetAddress.getByName(nslookup);
            System.out.println("Domain: " + inetHost.getHostAddress());

           // list.add(inetHost.getHostAddress());
            erg = "Domain: " + inetHost.getHostAddress();


        } catch (UnknownHostException ex) {

            System.out.println("Unrecognized host");
        }
    }

    public String getInet(){ return erg;}



}
