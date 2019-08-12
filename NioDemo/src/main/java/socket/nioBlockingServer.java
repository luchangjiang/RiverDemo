package socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class nioBlockingServer {
    public static void main(String[] args) throws Exception{
        int port=1212;
        if(args!=null && args.length>0)
        {
            port = Integer.parseInt(args[0]);

        }

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss =ssc.socket();
        ss.bind(new InetSocketAddress(port));
        System.out.println("开始等待客户端的数据，开始时间为：" + System.currentTimeMillis());
        while (true){
            SocketChannel sc = ssc.accept();
            if(sc == null){
                Thread.sleep(1000);
            }
            else{
                System.out.println("客户端的数据已经到来，客户端的IP为：" + sc.socket().getRemoteSocketAddress());
                ByteBuffer bb = ByteBuffer.allocate(100);
                sc.read(bb);
                bb.flip();
                while (bb.hasRemaining()){
                    System.out.print((char)bb.get());
                }
                sc.close();
                //bb.clear();
                System.exit(0);
            }
        }
    }
}
