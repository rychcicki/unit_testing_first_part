package org.example.verifier.verification;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.customer.verification.AgeVerification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class AgeVerificationTest {
    private final AgeVerification ageVerification = new AgeVerification();

    @Test
    void shouldReturnFalseWhenAgeOver99() {
        //given - arrange
        Person person = buildPerson(100);
        // when - act
        boolean check = ageVerification.passes(person);
        // then - assert
        Assertions.assertFalse(check); //Assertions.assertEquals(false,check)
    }

    @Test
    void shouldReturnTrueWhenAge23() {
        //given - arrange
        Person person = buildPerson(23);
        //when - act
        boolean check = ageVerification.passes(person);
        //then - assert
        Assertions.assertEquals(true, check);
    }

    /**
     * Jeżeli rzucany jest wyjątek można pominąć when (??????)
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act
        /** Czy w przypadku wyjątku asercja ma być w when (przesłany kod) czy then?? */
        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person));
    }

    /**
     * Jeżeli rzucany jest wyjątek można pominąć when
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0WithMessage() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act
        /** Czy w przypadku wyjątku asercja ma być w when (przesłany kod) czy then?? */
        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person), "Age cannot be negative.");
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act

        //then - assert
        assertThatThrownBy(() -> {
            ageVerification.passes(person);
        })
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Age cannot");
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ2() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act
        Throwable thrown = catchThrowable(() -> {
            throw new IllegalStateException("Age cannot be negative.");
        });
        //then - assert
        assertThat(thrown)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Age cannot be negative.");
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ3() {
        //given - arrange
        Person person = buildPerson(-3);
        //when - act
        /** Czy w przypadku wyjątku asercja ma być w when (przesłany kod) czy then?? */
        //then - assert
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> ageVerification.passes(person))
                .withMessageContaining("negative")
                /**  havingCause(), withNoCause() etc. - Nie rozumiem, kiedy, co i jak to działa, a kiedy nie.
                 * Po polsku nic nie znalazłem. Z dokumentacji nie rozumiem. */
//                .havingCause();
//                .getCause();
                .withNoCause();
//                .hasRootCause();
    }
    /**
     * Czym różnią się te trzy ostatnie metody (assertThatThrownBy, assertThat(catchThrowable()), assertThatExceptionOfType ???
     * Jak dla mnie to praktycznie to samo, tylko w inny sposób.
     */
    private Person buildPerson(int age) {
        LocalDate yearDate = LocalDate.now().minusYears(age);
        return new Person("Andrzej", "Nowak", LocalDate.of(yearDate.getYear(), 1, 1),
                Person.GENDER.MALE, "00410171972");
    }
}
