package com.river.design.TemplatePattern;

import com.river.design.TemplatePattern.game.Cricket;
import com.river.design.TemplatePattern.game.Football;
import com.river.design.TemplatePattern.game.Game;

/**
 * @program: RiverDemo
 * @description: 模板模式
 * @author: River
 * @create: 2021-01-30 11:46
 **/
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
