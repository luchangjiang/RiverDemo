import base.BaseDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 8:59 AM
 * @description：
 * @modified By：
 * @version: $
 */
public class ReentrantLockDemo extends BaseDemo {

    private final Lock lock = new ReentrantLock();
    private final Condition con = lock.newCondition();

    @Override
    public void callback(long response) {

        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");
        lock.lock();
        try {
            con.signal();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        ReentrantLockDemo demo2 = new ReentrantLockDemo();

        demo2.call();

        demo2.lock.lock();

        try {
            demo2.con.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            demo2.lock.unlock();
        }
        System.out.println("主线程内容");
    }
}
