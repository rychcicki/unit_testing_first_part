package org.example.unittesting.order;

import java.math.BigDecimal;

public class Promotion {
    private String name;
    private BigDecimal discount;

    public Promotion(String name, BigDecimal discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
