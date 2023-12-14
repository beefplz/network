package P1108;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test1 {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.google.com");
			System.out.println(address);
			System.out.println("Host name: "+address.getHostAddress());
			System.out.println("Canonical Host name: "+address.getCanonicalHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
