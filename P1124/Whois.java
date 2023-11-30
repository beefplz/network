package P1124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Whois {
    public final static int DEFAULT_PORT = 43;
    public final static String DEFAULT_HOST = "whois.kr";
    

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        InetAddress host = InetAddress.getByName(DEFAULT_HOST);
        Socket socket = new Socket();
        try {
            SocketAddress address = new InetSocketAddress(DEFAULT_HOST, port);
            socket.connect(address);
            Writer out = new OutputStreamWriter(socket.getOutputStream(), "ASCII");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ASCII"));
            out.write("hanbat.ac.kr");
            out.flush();
            StringBuilder response = new StringBuilder();
            String theLine = null;
            while ((theLine = in.readLine()) != null) {
                response.append(theLine);
                response.append("\r\n");
            }
            System.out.println(response.toString());
            } finally {
                socket.close();
            }
    }   
}
