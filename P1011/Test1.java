package P1011;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test1 {
    public static void main(String[] args) throws IOException {
    	try{
            generateCharacter(System.out);
        }catch(IOException e){
            e.printStackTrace( );
        }
    	try(
            InputStream in = new FileInputStream("data.txt")){
    		int bytesRead = 0;
        	int bytesToRead = 500;
        	byte[] input = new byte[bytesToRead];
        	while (bytesRead < bytesToRead) {
        		int result = in.read(input, bytesRead, bytesToRead - bytesRead);
        		System.out.println("result:"+result+ "\n");
        		
        		if (result == -1) break; // end of stream
        		bytesRead += result;
        	}
        	for (int i = 0;i<1024;i++)
        		System.out.print((char)input[i]);
        	System.out.print("\ncaarriage return");
        	System.out.print("\r");
        	System.out.print("line feed");
        	
    	}    
    }

    public static void generateCharacter(OutputStream out)throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharactersPerLine+2];
          try{
            out = new FileOutputStream("data.txt");
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }

        for (int numLine = 0; numLine <100; numLine++){
            for(int i = start; i<start+numberOfCharactersPerLine;i++){
                line[i-start]=(byte)((i-firstPrintableCharacter)
                %numberOfPrintableCharacters+firstPrintableCharacter);
            }
            line[72] = (byte)'\r';
            line[73]= (byte) '\n';
            out.write(line);
            start = ((start+1)-firstPrintableCharacter)
            %numberOfPrintableCharacters+firstPrintableCharacter;
        }
    }
}

