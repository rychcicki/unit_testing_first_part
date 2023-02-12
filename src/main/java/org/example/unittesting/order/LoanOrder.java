package org.example.unittesting.order;

import org.example.unittesting.customer.Customer;
import org.example.unittesting.loan.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class LoanOrder {
    private final LocalDate orderDate;
    private Customer customer;
    private LoanType loanType;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private BigDecimal commission;
    private final List<Promotion> promotionList = new ArrayList<>();

    public LoanOrder(LocalDate orderDate, Customer customer) {
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }
}
