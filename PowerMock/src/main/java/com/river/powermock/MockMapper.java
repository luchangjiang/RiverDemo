package com.river.powermock;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-12-13 21:46
 **/
public interface MockMapper {
    int count(MockModel model);

    MockModel getModel(String name);
}
