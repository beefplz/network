package P1201;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class EchoServer {
     public static void main(String[] args) {
        int port=1246;    
        System.out.println("Listening for connections on port " + port);
        ServerSocketChannel serverChannel;
        ExecutorService pool = Executors.newFixedThreadPool(50);
        Selector selector;
        try {
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            Callable<Void> task = new EchoTask(selector);
            pool.submit(task);
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }
    private static class EchoTask implements Callable<Void> {
        private Selector selector;
        EchoTask(Selector selector){
            this.selector = selector;
        }
        @Override
        public Void call(){
            while (true) {
                try {
                    selector.select();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = readyKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();
                            System.out.println("Accepted connection from " + client);
                            client.configureBlocking(false);
                            SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                            ByteBuffer buffer = ByteBuffer.allocate(100);
                            clientKey.attach(buffer);
                        }
                        if (key.isReadable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer output = (ByteBuffer) key.attachment();
                            client.read(output);
                        }
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer output = (ByteBuffer) key.attachment();
                            output.flip();
                            client.write(output);
                            output.compact();
                        }
                    } catch (IOException ex) {
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException cex) {}
                    }
                }
            }
            return null;
        }   
    } 
}
