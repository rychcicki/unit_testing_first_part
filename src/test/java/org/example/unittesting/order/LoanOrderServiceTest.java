package org.example.unittesting.order;

import org.example.unittesting.customer.Customer;
import org.example.unittesting.customer.Person;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class LoanOrderServiceTest {
    private LoanOrderService loanOrderService = new LoanOrderService();
    //jak test nie powinien wygladac
    @Test
    void shouldCreateProperLoanOrderObjectForStudent() {
        //given
        Customer student = aStudent();
        //when
        LoanOrder order = loanOrderService.studentLoanOrder(student);
        //then
        assertThat(order.getOrderDate()).isEqualTo(LocalDate.now());
        assertThat(order.getPromotionList())
                .filteredOn(promotion -> promotion.getName().equals("Studencka promocja"))
                .size().isEqualTo(1);
        assertThat(order.getPromotionList()).hasSize(1);
        assertThat(order.getCommission().equals(new BigDecimal(200)));

    }


    //troche lepszy test z uzyciem AssertObject v1
    @Test
    void studentLoanOrderBetter(){
        //given
        Customer customer = aStudent();
        //when
        LoanOrder order = loanOrderService.studentLoanOrder(customer);
        //then
        LoanOrderAssert orderAssert = new LoanOrderAssert(order);
        orderAssert.hasPromotion("Studencka promocja");
    }



    @Test
    void shouldThrowIllegalStateExceptionForCustomerWhichIsNotStudent() {
        //given
        Customer customer = buildCustomer();

    }

    private Customer aStudent() {
      Customer customer = buildCustomer();
      customer.student();
      return customer;
    }

    private Customer buildCustomer(){
        Person person = buildPerson(18);
        return new Customer(UUID.randomUUID(), person);
    }

    private Person buildPerson(int age) {
        LocalDate yearDate = LocalDate.now().minusYears(age);
        return new Person("Jan", "Testowy", LocalDate.of(yearDate.getYear(), 4,
                25), Person.GENDER.MALE, "CRE-234");
    }
}
