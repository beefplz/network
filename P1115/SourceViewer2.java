package T1110;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer2 {
    public static void main (String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("http://localhost:9999");
            in = u.openStream();
            in = new BufferedInputStream(in);
            Reader r = new InputStreamReader(in,"utf-8");
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
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
        }
    }
}
