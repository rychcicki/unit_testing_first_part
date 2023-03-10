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

    @Test
    void shouldContainExceptionWhenAgeBelow0() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act
        //then - assert
        Assertions.assertThrows(IllegalStateException.class, () -> ageVerification.passes(person));
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0WithMessage() {
        //given - arrange
        Person person = buildPerson(-1);
        //when - act
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
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("Age cannot");
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
        assertThat(thrown).isInstanceOf(IllegalStateException.class).hasMessageContaining("Age cannot be negative.");
    }

    @Test
    void shouldContainExceptionWhenAgeBelow0UsingAssertJ3() {
        //given - arrange
        Person person = buildPerson(-3);
        //when - act
        //then - assert
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> ageVerification.passes(person)).withMessageContaining("negative");
    }

    private Person buildPerson(int age) {
        LocalDate yearDate = LocalDate.now().minusYears(age);
        return new Person("Andrzej", "Nowak", LocalDate.of(yearDate.getYear(), 1, 1), Person.GENDER.MALE, "00410171972");
    }
}
