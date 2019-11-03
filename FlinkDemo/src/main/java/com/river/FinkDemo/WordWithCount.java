package com.river.FinkDemo;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-10-27 10:40
 **/
public class WordWithCount {
    public String word;
    public long count;

    public WordWithCount() {}

    public WordWithCount(String word, long count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return word + " : " + count;
    }
}

