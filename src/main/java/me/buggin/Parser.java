package me.buggin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.inject.Inject;

/**
 * Concrete implementer of TypeRequester (consumer)
 */
public class Parser implements TypeRequester {

    private TypeProvider service;

    @Inject
    public Parser(TypeProvider svc) {
        this.service = svc;
    }

    /**
     * @param input
     * @return
     */
    @Override
    public Cart parse(String input) {
        Scanner scanner = new Scanner(input);
        Cart cart = new Cart();
        while (scanner.hasNextLine()) {
            cart.addGood(buildGoodFrom(scanner.nextLine()));
        }
        return cart;
    }

    //TODO refactor
    private Product buildGoodFrom(String line) {
        Scanner scanner = new Scanner(line);
        int quantity = scanner.nextInt();
        List<String> listDescription = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean imported = false;
        scan:
        while (scanner.hasNext()) {
            String next = scanner.next();

            switch (next) {
                case "of":
                    stringBuilder.append(next + " ");
                    break;
                case "at":
                    break scan;
                case "imported":
                    imported = true;
                    break;
                default:
                    stringBuilder.append(next + " ");
                    listDescription.add(next);
            }

        }


        BigDecimal price = new BigDecimal(scanner.next());
        ProductType type = parseType(listDescription);
        Product productParsed = ProductBuilder
                .newProductBuilder()
                .ofType(type)
                .howMany(quantity)
                .withDescription(stringBuilder.toString())
                .isImported(imported)
                .withPrice(price)
                .build();

        return productParsed;
    }

    private ProductType parseType(List<String> desc) {
        for (String x : desc) {
            ProductType potential = service.getType(x);
            if (potential != null) {
                return potential;
            }
        }
        return ProductType.OTHER;
    }
}
