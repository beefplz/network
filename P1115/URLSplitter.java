package P1115;

import java.net.MalformedURLException;
import java.net.URL;

public class URLSplitter {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://admin:admin@www.test.org:900/folder/index.html?category=Piano@aaa");
            System.out.println("The URL is " + u);
            System.out.println("The scheme is " + u.getProtocol());
            System.out.println("The user info is " + u.getUserInfo());
            String host = u.getHost();
            System.out.println("The host is " + host);
            if (host != null) {
                int atSign = host.indexOf('@');
                if (atSign != -1) host = host.substring(atSign+1);
                System.out.println("The host is " + host);
            } else {
                System.out.println("The host is null.");
            }
            System.out.println("The port is " + u.getPort());
            System.out.println("The path is " + u.getPath());
            System.out.println("The ref is " + u.getRef());
            System.out.println("The query string is " + u.getQuery());
        } catch (MalformedURLException ex) {
            System.err.println(" is not a URL I understand.");
        }
        System.out.println();
    }
}

