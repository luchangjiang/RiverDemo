import base.BaseDemo;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 8:54 AM
 * @description：
 * @modified By：
 * @version: $
 */
public class Demo1 extends BaseDemo {

    private final Object lock = new Object();

    @Override
    public void callback(long response) {
        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");

        synchronized (lock) {
            lock.notifyAll();
        }

    }

    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();

        demo1.call();

        synchronized (demo1.lock){
            try {
                demo1.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("主线程内容");

    }
}
