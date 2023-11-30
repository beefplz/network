package P1011;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {
	static final String dataFile = "data.bin";
	public static void main(String[] args) throws IOException {
		try ( DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))){
            out.writeInt(1);
            out.writeDouble(1.0);
            out.writeUTF("abc한밭");
            out.writeInt(2);
            out.writeDouble(2.0);
            out.writeUTF("abc대학교");
		}  
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
				while (true) {
					System.out.println(in.readDouble());
					System.out.println(in.readInt());
                    
                    System.out.println(in.readUTF());
				}
			} catch (EOFException e) {
		}
	}
}
