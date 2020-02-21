import java.nio.CharBuffer;

public class NioDemo2 {
    private static String[] strs ={
            "A random string value",
            "The product of an inifinited number",
            "Hey hey we are monkees",
            "Opening act for the monkees: Jimy",
            "Scuse me while I kiss this fly",
            "Help me!Help me!"
    };

    private static int index=0;

    private static boolean fillBuffer(CharBuffer buffer){
        if(index >= strs.length)
            return false;

        String str = strs[index++];
        for(int i=0; i<str.length();i++)
            buffer.put(str.charAt(i));

        return true;
    }

    private static void drainBuffer(CharBuffer buffer){
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.print("\r\n");
    }

    public static void main(String[] args) {
        CharBuffer cb = CharBuffer.allocate(100);

        while(fillBuffer(cb)) {
            cb.flip();
            drainBuffer(cb);
            cb.clear();
        }
    }
}
