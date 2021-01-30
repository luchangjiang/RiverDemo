package com.river.design.CommandPattern;

import com.river.design.CommandPattern.stock.BuyStock;
import com.river.design.CommandPattern.stock.SellStock;
import com.river.design.CommandPattern.stock.Stock;

/**
 * @program: RiverDemo
 * @description: 命令模式
 * @author: River
 * @create: 2021-01-30 10:17
 **/
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
