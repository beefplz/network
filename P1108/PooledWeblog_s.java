package P1108;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWeblog_s {
	
	private final static int NUM_THREADS = 4;
	
	public static void main(String[] args) throws IOException{
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("access_log_23.txt"),"UTF-8"))){
            for(String entry = in.readLine(); entry != null; entry = in.readLine()){
                LookupTask_s task = new LookupTask_s(entry);
                Future<String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry, future);
                results.add(result);
            }
		}
        for(LogEntry result : results){
            try{
                System.out.println(result.future.get());
            }catch(InterruptedException|ExecutionException ex){
                System.out.println(result.original);
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
