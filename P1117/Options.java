package Project1;

import java.io.IOException;
import java.net.*;

public class Options {
	public static void main(String[] args) {

		// for (int i = 0; i < args.length; i++) {
		try {
			URL u = new URL("http://bhis.co");
			//URL u = new URL("http://www.hanbat.ac.kr");
			//URL u = new URL("https://www.oreilly.com");
			//URL u = new URL("http://cyber.hanbat.ac.kr");
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			//http.setRequestMethod("HEAD");
			http.setRequestMethod("TRACE");
			System.out.println(http.getResponseMessage());
			for (int j = 0; ; j++) {
				String header = http.getHeaderField(j);
				if (header == null) break;
				System.out.println(http.getHeaderFieldKey(j) + ": " + header);
			}
		//System.out.println(u + " was last modified at " + new Date(http.getLastModified()));
		} catch (MalformedURLException ex) {
			System.err.println("This is not a URL I understand");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		System.out.println();
	}
		// }
}