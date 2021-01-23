package com.river.thread.base;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 8:47 AM
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class BaseDemo {

    /*protected AsyncCall asyncCall = new AsyncCall();
     */
    public abstract void callback(long response);

    public void call(){
        System.out.println("发起调用");
//        asyncCall.call(this);
        System.out.println("调用返回");
    }

}
