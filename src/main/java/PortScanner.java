import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Ermin Kameric on 10.11.2017.
 */
public class PortScanner {

    private ArrayList<Integer> list;

    public void scanPort(String address) {

        list = new ArrayList<Integer>();

        System.out.println(address);

        for (int port = 0; port <= 100; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(address, port), 200);
                socket.close();
                System.err.println(port + " -> PORT OFFEN");
                list.add(port);
            } catch (IOException e) {
                System.out.println(port + " -> Port geschlossen");

            }

        }
    }

    public ArrayList<Integer> getOpenPorts(){
        return list;
    }

}
