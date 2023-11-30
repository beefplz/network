package Project1;

import java.io.*;
import java.net.*;

public class BinarySaver {
	
	public static void main (String[] args) {
		try {
			URL root = new URL("http://www.hanbat.ac.kr/images/kor/main/images1.jpg");
			saveBinaryFile(root);
		} catch (MalformedURLException ex) {
			System.err.println("http://www.hanbat.ac.kr/images/kor/main/images1.jpg" + " is not URL I understand.");
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	public static void saveBinaryFile(URL u) throws IOException {
		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		System.out.println("contentType = "+contentType +", length = "+contentLength);
		if (contentType.startsWith("text/") || contentLength == -1 ) {
			throw new IOException("This is not a binary file.");
		}
		try (InputStream raw = uc.getInputStream()) {
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int offset = 0;
			while (offset < contentLength) {
				int bytesRead = in.read(data, offset, data.length - offset);
				System.out.println("bytesRead"+bytesRead);
				if (bytesRead == -1) break;
				offset += bytesRead;
			}
			if (offset != contentLength) {
				throw new IOException("Only read " + offset+ " bytes; Expected " + contentLength + " bytes");
			}
			String filename = u.getFile();
			System.out.println("u.getFile()="+filename);
			filename = filename.substring(filename.lastIndexOf('/') + 1);
			try (FileOutputStream fout = new FileOutputStream(filename)) {
				fout.write(data);
				fout.flush();
			}
		}
	}
}