package P1201;
import java.net.*;
import java.text.*;
import java.io.*;

public class EchoClient {
    private static final String HOSTNAME = "localhost";
    public static void main(String[] args) throws IOException, ParseException {
        Socket socket = null;
        try {
            socket = new Socket(HOSTNAME, 1246);
            socket.setSoTimeout(30000);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("aaa");
            writer.println("bbb");
            writer.println("ccc");
            writer.flush();
    
            String line = null;
            while ((line = br.readLine())!=null) {
                System.out.println("Return : "+line);

            }
            writer.close();
        } finally {
            socket.close();
        }
    }
}
