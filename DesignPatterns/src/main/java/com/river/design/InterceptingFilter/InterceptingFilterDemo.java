package com.river.design.InterceptingFilter;

import com.river.design.InterceptingFilter.filter.AuthenticationFilter;
import com.river.design.InterceptingFilter.filter.DebugFilter;
import com.river.design.InterceptingFilter.manager.FilterManager;
import com.river.design.InterceptingFilter.manager.Target;

/**
 * @program: RiverDemo
 * @description: 拦截过滤器模式
 * @author: River
 * @create: 2021-01-30 12:30
 **/
public class InterceptingFilterDemo {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }
}
