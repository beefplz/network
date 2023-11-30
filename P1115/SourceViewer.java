package P1115;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
    public static void main (String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("http://www.hanbat.ac.kr/images/kor/main/images1.jpg");
            in = u.openStream();
            in = new BufferedInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int c;
            byte[] b = new byte[8192];
            FileOutputStream output = new FileOutputStream("data.jpg");
            while ((c = in.read(b)) != -1) {
                out.write(b,0,c);
            }
            out.close();          
            byte[] response = out.toByteArray();
            output.write(response);
            output.close();
        } catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        } //92.04% 64 0.001
    }
}
