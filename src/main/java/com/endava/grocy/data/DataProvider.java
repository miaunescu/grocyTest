package com.endava.grocy.data;

import com.endava.grocy.model.*;
import com.github.javafaker.Faker;

public class DataProvider {

    private Faker faker = new Faker();

    public Product getProduct(Long locationId, Long quantityPurchaseId, Long quantityStockId) {
        Product product = new Product();
        product.setName(faker.commerce().productName() + "-" + faker.number().numberBetween(1, Long.MAX_VALUE));
        product.setDescription(faker.chuckNorris().fact());
        product.setLocationId(locationId);
        product.setMinStockAmount(faker.number().numberBetween(0, 10));
        product.setQuantityPurchaseId(quantityPurchaseId);
        product.setQuantityStockId(quantityStockId);
        product.setQuantityFactorPurchaseToStock(faker.number().randomDouble(4, 0, 100));

        return product;
    }

    public Location getLocation() {
        Location location = new Location();
        location.setName(faker.relationships().any() + " " + faker.name().fullName());
        location.setDescription(faker.harryPotter().quote());
        location.setIsFreezer(faker.options().option(0, 1));

        return location;
    }

    public QuantityUnit getQuantityUnit() {
        QuantityUnit quantityUnit = new QuantityUnit();
        quantityUnit.setName(faker.food().measurement() + " " + faker.number().digits(5));
        quantityUnit.setNamePlural(quantityUnit.getName() + "s");
        quantityUnit.setDescription(faker.backToTheFuture().quote());

        return quantityUnit;
    }

    public Stock getStock() {
        Stock stock = new Stock();
        stock.setAmount(faker.number().randomDouble(2, 0, 500));
        stock.setTransactionType(TransactionType.PURCHASE);
        stock.setPrice(faker.number().randomDouble(2, 0, 1000));

        return stock;
    }
}
