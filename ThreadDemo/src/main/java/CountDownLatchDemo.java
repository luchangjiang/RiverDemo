import base.BaseDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 9:09 AM
 * @description：
 * @modified By：
 * @version: $
 */
public class CountDownLatchDemo extends BaseDemo {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void callback(long response) {

        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");

        countDownLatch.countDown();

    }

    public static void main(String[] args) {

        CountDownLatchDemo demo4 = new CountDownLatchDemo();

        demo4.call();

        try {
            demo4.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程内容");

    }
}
