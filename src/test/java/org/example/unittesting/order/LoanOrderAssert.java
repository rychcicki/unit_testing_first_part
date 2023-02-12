package org.example.unittesting.order;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LoanOrderAssert {

    private final LoanOrder order;

    public LoanOrderAssert(LoanOrder order) {
        this.order = order;
    }

    LoanOrderAssert hasPromotion(String promotionName) {
        assertThat(order.getPromotionList())
                .filteredOn(promotion -> promotion.getName().equals(promotionName))
                .size().isEqualTo(1);
        return this;
    }



}
