import java.net.InetAddress;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String>{
    private String line;

    public LookupTask(String line){
        this.line = line;
    }
    @Override
    public String call(){
        try{
            int index = line.indexOf(' ');
            String address = line.substring(0, index);
            
            String theRest = line.substring(index);
            String hostname = InetAddress.getByName(address).getHostAddress();
            return hostname + " "+theRest;
        }catch(Exception ex){
            return line;
        }
    }
    public static final String BLACKHOLE = "zen.spamhaus.org";
    
}
