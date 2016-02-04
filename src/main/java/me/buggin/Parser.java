package me.buggin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Concrete implementer of TypeRequester (consumer)
 */
public class Parser implements TypeRequester {

    private TypeProvider service;

    public Parser(TypeProvider svc) {
        this.service = svc;
    }

    /**
     *
     * @param input
     * @return
     */
    @Override
    public Cart parse(String input) {
        Scanner scanner = new Scanner(input);
        CartBuilder cart = CartBuilder.newOrderBuilder();
        while (scanner.hasNextLine()) {
            cart.addGood(buildGoodFrom(scanner.nextLine()));
        }
        return cart.build();
    }

    //TODO refactor
    private Good buildGoodFrom(String line) {
        Scanner scanner = new Scanner(line);
        int quantity = scanner.nextInt();
        List<String> desc = new ArrayList<>();
        boolean imported = false;
        scan:while (scanner.hasNext()) {
            String next = scanner.next();

            switch (next) {
                case "of":
                    break;
                case "at":
                    break scan;
                case "imported":
                    imported = true;
                    break;
                default:
                    desc.add(next);
            }

        }


        BigDecimal price = new BigDecimal(scanner.next());
        GoodType type = parseType(desc);
        Good goodParsed = GoodBuilder
                .newGoodBuilder()
                .ofType(type)
                .howMany(quantity)
                .withDescription(desc.toString())
                .isImported(imported)
                .withPrice(price)
                .build();

        return goodParsed;
    }

    private GoodType parseType(List<String> desc) {
        for (String x : desc) {
            GoodType potential = service.getType(x);
            if (potential != null) {
                return potential;
            }
        }
        return GoodType.OTHER_TYPE;
    }
}
