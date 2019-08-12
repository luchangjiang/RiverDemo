import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo3 {
    public static void main(String[] args) throws Exception{
        fileChannelRead();
        fileChannelWrite();
    }

    private static void fileChannelRead() throws Exception{
        File file=new File("D:/MyJava/Studying/NioDemo/readme.txt");
        FileInputStream fis=new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb=ByteBuffer.allocate(35);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining()){
            System.out.print((char)bb.get());
        }
        bb.clear();
        fc.close();
        System.out.print("\r\n");
    }

    private static void fileChannelWrite() throws Exception{
        File file=new File("D:/MyJava/Studying/NioDemo/readme.txt");
        RandomAccessFile raf=new RandomAccessFile(file, "rw");
        FileChannel fc = raf.getChannel();
        ByteBuffer bb=ByteBuffer.allocate(35);
        String str = "Hello Hello Kity!";
        bb.put(str.getBytes());
        bb.flip();
        fc.write(bb);
        bb.clear();
        fc.close();

        fileChannelRead();
    }
}
