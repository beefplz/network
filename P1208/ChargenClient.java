package P1208;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class ChargenClient {

public static int DEFAULT_PORT = 1111;

public static void main(String[] args) {
    int port = DEFAULT_PORT;
    try {
        SocketAddress address = new InetSocketAddress("localhost", port);
        SocketChannel client = SocketChannel.open(address);
        ByteBuffer buffer = ByteBuffer.allocate(5);
        WritableByteChannel out = Channels.newChannel(System.out);
        while (client.read(buffer) != -1) { //ABC
            // System.out.println("reading");
            buffer.flip();
            out.write(buffer);
            // System.out.print( new String(buffer.array(), "ASCII")) ;
            buffer.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}