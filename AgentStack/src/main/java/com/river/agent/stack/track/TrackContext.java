package com.river.agent.stack.track;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-01-11 21:31
 **/
public class TrackContext {
    private static final ThreadLocal<String> trackLocal = new ThreadLocal<String>();

    public static void clear(){
        trackLocal.remove();
    }

    public static String getLinkId(){
        return trackLocal.get();
    }

    public static void setLinkId(String linkId){
        trackLocal.set(linkId);
    }
}
