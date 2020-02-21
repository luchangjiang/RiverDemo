package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SelectorClient {

    private static final String STR= "Hello World!";
    private static final String REMOTE_IP="127.0.0.1";
    private static final int THREAD_COUNT =5;

    public static class NioBlockingSocketThread extends Thread{
        public void run(){
            try {
                int port = 1235;

                SocketChannel sc = SocketChannel.open();
                sc.configureBlocking(false);
                sc.connect(new InetSocketAddress(REMOTE_IP, port));

                while (!sc.finishConnect()) {
                    System.out.println(String.format("正在建立同{0}的连接", REMOTE_IP));
                    Thread.sleep(10);
                }
                System.out.println("已经同服务器建立连接，待将内容写入指定的端口，开始时间为：" + System.currentTimeMillis());
                String writeStr = STR + this.getName();
                ByteBuffer buf = ByteBuffer.allocate(writeStr.length());
                buf.put(writeStr.getBytes());
                buf.flip();
                sc.write(buf);
                buf.clear();
                sc.close();
                System.out.println("客户端数据已写入完成");
            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws Exception{
            NioBlockingSocketThread[] nbst=new NioBlockingSocketThread[THREAD_COUNT];
            for (int i = 0; i < THREAD_COUNT; i++) {
                nbst[i]=new NioBlockingSocketThread();
            }

            for (int i = 0; i < nbst.length; i++) {
                nbst[i].start();
            }

            /*for (int i = 0; i < nbst.length; i++) {
                nbst[i].join();
            }*/
        }
    }
}
