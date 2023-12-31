
import java.io.*;
import java.net.*;

public class FormPoster {
	private URL url;
	private QueryString query = new QueryString();
	
	public FormPoster (URL url) {
		if (!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException(	"Posting only works for http URLs");
		}
		this.url = url;
	}
	
	public void add(String name, String value) {
		query.add(name, value);
	}
	
	public URL getURL() {
		return this.url;
	}
	
	public InputStream post() throws IOException {
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Cookie", "name=seongwon; id=20217138; num = 01051686850" );
		uc.setRequestProperty("user-agent", "chrome");
		uc.setDoOutput(true);
		try (OutputStreamWriter out= new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		}
		return uc.getInputStream();
	}
	public static void main(String[] args) {
		URL url;
		if (args.length > 0) {
			try {
				url = new URL(args[0]);
			} catch (MalformedURLException ex) {
				System.err.println("Usage: java FormPoster url");
				return;
			}
		} else {
			try {
				url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
			} catch (MalformedURLException ex) { 
				System.err.println(ex);
				return;
			}
		}
		FormPoster poster = new FormPoster(url);
		poster.add("name", "Elliotte Rusty Harold");
		poster.add("email", "elharo@ibiblio.org");
		poster.add("number", "01051686850");
		try (InputStream in = poster.post()) {
			// Read the response
			Reader r = new InputStreamReader(in);
			int c;
			FileOutputStream outputStream = new FileOutputStream("data.html");
			while((c = r.read()) != -1) {
				System.out.print((char) c);
				outputStream.write(c);
			}
			outputStream.close();
			System.out.println();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
