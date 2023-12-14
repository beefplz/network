package P1108;

import java.net.*;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String>{
    private String line;

    public LookupTask(String line){
        this.line = line;
    }
    @Override
    public String call()throws UnknownHostException{
        try{
            int index = line.indexOf(' ');
            String address = line.substring(0, index);
            String theRest = line.substring(index);
            String hostname = InetAddress.getByName(address).getHostName();

            if(isSpammer(address))
            	return hostname + " SPAM" + theRest;
            else {
                return hostname + " "+theRest;
            }
        }catch(Exception ex){
            return line;
        }
    }
    public static final String BLACKHOLE = "zen.spamhaus.org";
    
    private static boolean isSpammer(String arg) {
		try {
			InetAddress address = InetAddress.getByName(arg);
			byte[] quad = address.getAddress();
			String query = BLACKHOLE;
			for(byte octet : quad) {
				int unsignedByte = octet < 0 ? octet + 256 : octet;
				query = unsignedByte  + "." + query;
			}
			InetAddress ad = InetAddress.getByName(query);
			return true;
		}catch(UnknownHostException e) {
			return false;
		}
	}
}
