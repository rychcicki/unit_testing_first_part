package org.example.unittesting.order;

import org.example.unittesting.customer.Customer;
import org.example.unittesting.loan.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;

class LoanOrderService {

    public LoanOrder studentLoanOrder(Customer customer){
        if(!customer.isStudent()){
            throw new IllegalStateException("Nie jest studentem");
        }
        LocalDate now = LocalDate.now();
        LoanOrder loanOrder = new LoanOrder(now,customer);
        loanOrder.setLoanType(LoanType.STUDENT);
        loanOrder.getPromotionList().add(new Promotion("Studencka promocja", new BigDecimal(10)));
        loanOrder.setCommission(new BigDecimal(200));
        return loanOrder;
    }
}
