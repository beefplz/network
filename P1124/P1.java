package P1124;
import java.io.*;
import java.net.*;

public class P1 {
    public static final String SERVER = "www.cafeaulait.org"; 
    public static final int PORT = 80;
    public static final int TIMEOUT = 15000;
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            define( writer, reader);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally { 
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                }
            }
        }
    }
    static void define(Writer writer, BufferedReader reader) throws IOException, UnsupportedEncodingException {
        writer.write( "cookie=seongwon; id=20217138; num = 01051686850");
        writer.flush();
        try (BufferedWriter w = new BufferedWriter(new FileWriter("return.html"))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
                w.write(line);
                w.write("\n");
            }
            w.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
    }
}