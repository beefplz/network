package P1108;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
public class PooledWeblog {
	
	private final static int NUM_THREADS = 4;
	static final String dataFile = "access_hosts.txt";

	public static void main(String[] args) throws IOException{
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		
		try(BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream("access_log_23.txt"),"UTF-8"))){
            for(String entry = in.readLine(); entry != null; entry = in.readLine()){
                LookupTask task = new LookupTask(entry);
                Future<String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry, future);
                results.add(result);
            }
		}
		try(FileWriter out = new FileWriter(dataFile)){
			for(LogEntry result : results){
	            try{
	            	String a = result.future.get();
	                System.out.println(a); 
	                out.write(a);
	                out.write('\n');
	            }catch(InterruptedException|ExecutionException ex){
	                System.out.println(result.original);
	            }
	        }
        }  
        executor.shutdown();
	}
    private static class LogEntry{
        String original;
        Future<String> future;

        LogEntry(String original, Future<String> future){
            this.original = original;
            this.future = future;
        }
    }
}
