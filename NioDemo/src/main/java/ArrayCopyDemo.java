/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-23 11:58
 **/
public class ArrayCopyDemo {
    public static void main(String[] args) {
        testArrayCopy();
    }

    public static void testArrayCopy(){
        int size = 100000;
        int[] array = new int[size];
        int[] arraydest = new int[size];

        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        for (int k=0;k<1000;k++){
            //进行复制
            System.arraycopy(array,0,arraydest,0,size);
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }
}
