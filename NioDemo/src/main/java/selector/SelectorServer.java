package selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorServer {
    public static void main(String[] args) throws Exception {
        int port=1235;
        if(args!=null && args.length>0)
        {
            port = Integer.parseInt(args[0]);
        }
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss =ssc.socket();
        ss.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int n=selector.select();
            if(n==0){
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                if(sk.isAcceptable()){
                    ServerSocketChannel sscl= (ServerSocketChannel)sk.channel();
                    SocketChannel sc = sscl.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                else if(sk.isReadable()){
                    readDataFromSocket(sk);
                }
                iterator.remove();
            }
        }
    }

    private static ByteBuffer buf=ByteBuffer.allocate(1024);

    protected static void readDataFromSocket(SelectionKey sk) throws Exception{
        SocketChannel sc=(SocketChannel)sk.channel();
        buf.clear();
        while(sc.read(buf)>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
        }
    }
}
