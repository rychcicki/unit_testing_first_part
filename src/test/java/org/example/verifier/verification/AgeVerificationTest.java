package org.example.verifier.verification;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.customer.verification.AgeVerification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AgeVerificationTest {
    private final AgeVerification ageVerification = new AgeVerification();

    @Test
    void shouldReturnFalseWhenAgeOver99() {
        //given - arrange
        Person person = buildPerson(LocalDate.of(1899, 4, 25), Person.GENDER.MALE, "99042503899");
        // when - act
        boolean result = ageVerification.passes(person);
        // then - assert
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenAge23() {
        //given - arrange
        Person person = buildPerson(LocalDate.of(2000, 1, 25), Person.GENDER.FEMALE, "99042503899");
        //when - act
        boolean result = person.getAge() == 23;
        //then - assert
        Assertions.assertTrue(result);
    }

    /**
     * W Jaki sposób określić warunki (when)? Co powinno być w then? Czy należy skorzystać np. z assertThatThrownBy()
     * W sumie większość warunków jest sprawdzana w metodzie passes();
     */
    @Test
    void shouldContainExceptionWhenAgeBelow0() {
        //given - arrange
        Person person = buildPerson(LocalDate.of(2000, 1, 1), Person.GENDER.MALE, "00410171972");
        //when - act
        boolean result = ageVerification.passes(person);
        //then - assert
        Assertions.assertTrue(result);
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0WithMessage() {
        //given - arrange
        Person person = buildPerson(LocalDate.of(2000, 1, 1), Person.GENDER.MALE, "00410171972");
        //when - act
        try {
            if (person.getAge() < 0) {
                throw new IllegalArgumentException(" You set a negative age. It must be non-negative.");
            }
        } catch (IllegalStateException age) {
            System.out.println(" Set a proper age.");
        }
        //then - assert

    }

    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ() {
        //given - arrange
        Person person = buildPerson(LocalDate.of(2100, 1, 1), Person.GENDER.MALE, "00410171972");
        //when - act

        //then - assert
        assertThatThrownBy(() -> ageVerification.passes(person)).isInstanceOf(IllegalStateException.class);
    }

    Person buildPerson(LocalDate localdate, Person.GENDER gender, String nationalIdentificationNumber) {
        return new Person("Andrzej", "Nowak", localdate, gender, nationalIdentificationNumber);
    }
}
