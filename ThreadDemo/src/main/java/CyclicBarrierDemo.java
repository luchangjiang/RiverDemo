import base.BaseDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 9:11 AM
 * @description：
 * @modified By：
 * @version: $
 */
public class CyclicBarrierDemo extends BaseDemo {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


    @Override
    public void callback(long response) {

        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        CyclicBarrierDemo demo5 = new CyclicBarrierDemo();

        demo5.call();

        try {
            Thread.sleep(1000);
            System.out.println("主线程先执行");
            demo5.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("主线程内容");

    }
}
