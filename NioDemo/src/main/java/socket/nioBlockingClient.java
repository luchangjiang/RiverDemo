package socket;

import com.sun.org.apache.regexp.internal.RE;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class nioBlockingClient {
    private static final String STR= "Hello World!";
    private static final String REMOTE_IP="127.0.0.1";

    public static void main(String[] args) throws Exception{
        int port=1212;
        if(args!=null && args.length>0){
            port = Integer.parseInt(args[0]);
        }

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(REMOTE_IP, port));

        while(!sc.finishConnect()){
            System.out.println(String.format("正在建立同{0}的连接", REMOTE_IP));
            Thread.sleep(10);
        }
        System.out.println("已经同服务器建立连接，待将内容写入指定的端口，开始时间为：" + System.currentTimeMillis());
        ByteBuffer buf = ByteBuffer.allocate(100);
        buf.put(STR.getBytes());
        buf.flip();
        sc.write(buf);
        buf.clear();
        sc.close();
        System.out.println("客户端数据已写入完成");
    }
}
