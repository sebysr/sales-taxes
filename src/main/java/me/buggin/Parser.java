package me.buggin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abuggin on 2/3/16.
 */
public class Parser {

    public Order parse(String input){
        Scanner scanner = new Scanner(input);
        OrderBuilder order = OrderBuilder.newOrderBuilder();
        while (scanner.hasNextLine()){
            order.addGood(parseLine(scanner.nextLine()));
        }
        return order.build();
    }

    private Good parseLine(String line) {
        Scanner scanner = new Scanner(line);
        int quantity = scanner.nextInt();
        List<String> desc = new ArrayList<>();
        boolean imported = false;
        scan: while(scanner.hasNext()){
            String next = scanner.next();

            switch (next){
                case "of":
                    break ;
                case "at":
                    break scan;
                case "imported":
                    imported = true;
                    break ;
                default:
                    desc.add(next);
            }

        }

        BigDecimal price = new BigDecimal(scanner.next());
        GoodType type = GoodType.parseType(desc);
        Good goodParsed = GoodBuilder
                .newGoodBuilder()
                .setType(type)
                .setQuantity(quantity)
                .setDescription(desc.toString())
                .setImported(imported)
                .setPrice(price)
                .build();

        return goodParsed;
    }
}
