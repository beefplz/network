package P1201;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class ChannelBufferEx {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer1 = ByteBuffer.allocate( 100 );
        System.out.println("buffer capacity="+buffer1.capacity());
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position());
        System.out.println();
        buffer1.put((byte)0x41); // A
        buffer1.put((byte)0x42); // B
        System.out.println("buffer capacity="+buffer1.capacity());
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        buffer1.flip();
        System.out.println("buffer capacity="+buffer1.capacity());
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        WritableByteChannel output = Channels.newChannel(System.out);
        output.write(buffer1);
        System.out.println();
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        buffer1.limit(buffer1.capacity());
        buffer1.put((byte)0x43); // C
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        buffer1.rewind();
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        buffer1.clear();
        System.out.println("buffer limit="+buffer1.limit());
        System.out.println("buffer position="+buffer1.position()); System.out.println();
        buffer1.position(1);
        buffer1.limit(3);
        System.out.println(buffer1);
        byte[] array = buffer1.array();
        System.out.println(Arrays.toString(array));
        buffer1.compact();
        System.out.println(buffer1);
        array = buffer1.array();
        System.out.println(Arrays.toString(array));
        try (FileInputStream fin = new FileInputStream("data.txt" )) {
            FileChannel fc = fin.getChannel();
            ByteBuffer buffer2 = ByteBuffer.allocate( 100 );
            fc.read( buffer2 );
        }
        byte[] message = new String("test").getBytes();
        try (FileOutputStream fout = new FileOutputStream( "wrtiedata.txt" )) {
            FileChannel fcout = fout.getChannel();
            ByteBuffer bufferout = ByteBuffer.allocate( 1024 );
            for (int i=0; i<message.length; ++i) {
                bufferout.put( message[i] );
            }
            bufferout.flip();
            fcout.write( bufferout );
        }
    }
}