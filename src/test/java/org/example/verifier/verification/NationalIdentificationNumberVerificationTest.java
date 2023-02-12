package org.example.verifier.verification;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.customer.verification.NationalIdentificationNumberVerification;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class NationalIdentificationNumberVerificationTest {
    private final NationalIdentificationNumberVerification nationalIdentificationNumberVerification = new NationalIdentificationNumberVerification();

    @Test
    void shouldReturnTrueWhenAllNationalIdentificationNumberIsCorrect() {
        // given
        Person person = buildPerson(Person.GENDER.MALE,LocalDate.of(1999,4,25), "99042503899");
        // when
        boolean result = nationalIdentificationNumberVerification.passes(person);
        // then
        Assertions.assertTrue(result);

    }
    @Test
    void shouldReturnFalseWhenDateOfBirthInObjectIsDifferentThanDateFromNationalIdentificationNumber() {
        // given
        Person person = buildPerson(Person.GENDER.MALE,LocalDate.of(1999,4,26),
                "99042503899");
        // when
        boolean result = nationalIdentificationNumberVerification.passes(person);
        // then
        Assertions.assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenGenderIsDifferentThanGenderFromNationalIdentificationNumber() {
        // given
        Person person = buildPerson(Person.GENDER.FEMALE,LocalDate.of(1999,4,25),
                "99042503899");
        // when
        boolean result = nationalIdentificationNumberVerification.passes(person);
        // then
        Assertions.assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenNationalIdentificationNumberContainsLetters() {
        // given
        Person person = buildPerson(Person.GENDER.FEMALE,LocalDate.of(1999,4,25),
                "9904XD03899");
        // when
        boolean result = nationalIdentificationNumberVerification.passes(person);
        // then
        Assertions.assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenCheckSumIsNotCorrect() {
        // given
        Person person = buildPerson(Person.GENDER.MALE,LocalDate.of(1999,4,25),
                "99042503898");
        // when
        boolean result = nationalIdentificationNumberVerification.passes(person);
        // then
        Assertions.assertFalse(result);
    }
    Person buildPerson(Person.GENDER gender, LocalDate dateOfBirth, String nationalIdentificationNumber) {
        return new Person("Jan","Testowy",dateOfBirth,gender,nationalIdentificationNumber);
    }
}
