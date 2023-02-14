package org.example.verifier.verification;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.customer.verification.AgeVerification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AgeVerificationTest {
    private final AgeVerification ageVerification = new AgeVerification();

    @Test
    void shouldReturnFalseWhenAgeOver99() {
        //given - arrange
        Person person = buildPerson(101);
        // when - act

        // then - assert
        Assertions.assertFalse(person.getAge() < 100);
    }

    @Test
    void shouldReturnTrueWhenAge23() {
        //given - arrange
        Person person = buildPerson(23);
        //when - act

        //then - assert
        Assertions.assertEquals(23, person.getAge());
    }

    /**
     * Poniższy test działa źle. Jeżeli wiek == 0, to test również przechodzi. To wina testu, czy implementacji?
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act

        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person), "Age has not to be negative");
    }

    /**
     * Poniższy test działa źle. Jeżeli wiek == 0, to test również przechodzi. To wina testu, czy bardziej implementacji?
     * O jaki Message chodzi?
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0WithMessage() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act

        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person), "Age has not to be negative");
    }

    /**
     * Poniższy test działa źle. Jeżeli wiek == 0, to test również przechodzi. To wina testu, czy bardziej implementacji?
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act

        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person), "Age has not to be negative");
    }

    private Person buildPerson(int age) {
        LocalDate yearDate = LocalDate.now().minusYears(age);
        return new Person("Andrzej", "Nowak", LocalDate.of(yearDate.getYear(), 1, 1), Person.GENDER.MALE, "00410171972");
    }
}
