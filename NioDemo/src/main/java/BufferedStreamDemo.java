import java.io.*;
import java.util.Objects;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-23 12:04
 **/
public class BufferedStreamDemo {
    private static BufferedStreamDemo demo = new BufferedStreamDemo();
    public static void main(String[] args) {
        testBufferedStream();
    }

    public static void testBufferedStream(){

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream(demo.getClass().getResource("/output.txt").getPath())));
            long start = System.currentTimeMillis();
            for(int i=0;i<10000;i++){
                dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
            }
            dataOutputStream.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startInput = System.currentTimeMillis();
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(demo.getClass().getResource("/input.txt").getPath())));

            while (dataInputStream.readLine() != null){
            }
            dataInputStream.close();
            long useTimeInput = System.currentTimeMillis()-startInput;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
