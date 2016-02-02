package me.buggin;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by abuggin on 2/2/16.
 */
public class OrderBuilder {
    private List<Good> listOfGoods = new LinkedList<Good>();

    public static OrderBuilder newOrderBuilder() {
        return new OrderBuilder();
    }

    public OrderBuilder addGood(Good good) {
        this.listOfGoods.add(good);
        return this;
    }

    public Order build() {
        return Order.newOrder(listOfGoods);
    }
}
