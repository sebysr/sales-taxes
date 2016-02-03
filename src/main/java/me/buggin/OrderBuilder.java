package me.buggin;

import java.util.LinkedList;
import java.util.List;

/**
 * Builder pattern for <code>Order</code> object.
 * <p>
 * Motivation for use of <code>LinkedList</code>
 * <p>
 * <code>for (Obj o : Iterable</code> compiles to
 * <code>for (I #i = Expression.iterator(); #i.hasNext(); ) </code>
 * <p>
 * Then in this context, <code>ListIterator.add(E element)</code> is <code>O(1)</code> which is a main benefit of <code>LinkedList</code>
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.14.2">ref</a>
 */
public class OrderBuilder {
    private List<Good> listOfGoods = new LinkedList<>();

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
