package com.river.powermock;

import java.util.Random;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-12-13 22:25
 **/
public class MockUtil {
    private static final Random random = new Random();

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
