package org.example.unittesting.verifier.customer.verification;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.Verification;

import java.util.Arrays;
import java.util.List;

/**
 * Klasa ktora dokonuje weryfikacji po peselu. Pesel nalezy sprawdzic pod katem nastepujacych aspektow :
 * 1) czy to w ogole jest pesel
 * 2) Czy plec osoby sprawdzanej zgodna jest z tym co podane w peselu
 * 3) Czy suma kontrolna peselu jest poprawna  * Zob. https://pl.wikipedia.org/wiki/PESEL#Cyfra_kontrolna_i_sprawdzanie_poprawno.C5.9Bci_numeru
 * 4) Czy pesel zaczyna sie od daty urodzenia oraz czy jest to ta sama data ktora jest ustawiona w obiekcie osoby sprawdzanej
 */
public class NationalIdentificationNumberVerification implements Verification {
    @Override
    public boolean passes(Person person) {
        return checkCorrectnessOfNationalIdentificationNumber(person.getNationalIdentificationNumber())
                && genderCoincidesWithPerson(person)
                && isCheckSumValid(person.getNationalIdentificationNumber())
                && dateOfBirthCoincidesWithDateInPerson(person);
    }

    private boolean dateOfBirthCoincidesWithDateInPerson(Person person) {
        String dateOfBirthFromPerson = person.getDateOfBirth().getYear() % 100
                + createStringWithTwoDigits(person.getDateOfBirth().getMonthValue())
                + createStringWithTwoDigits(person.getDateOfBirth().getDayOfMonth());
        return dateOfBirthFromPerson.equals(person.getNationalIdentificationNumber().substring(0, 6));
    }

    private String createStringWithTwoDigits(int value) {
        if (value >= 10) return String.valueOf(value);
        return "0" + value;
    }

    private boolean isCheckSumValid(String nationalIdentificationNumber) {
        List<Integer> listWeight = List.of(1, 3, 7, 9, 1, 3, 7, 9, 1, 3);
        int sum = 0;
        int digit;

        for (int i = 0; i < listWeight.size(); i++) {
            digit = listWeight.get(i) * Integer.parseInt(String.valueOf(nationalIdentificationNumber.charAt(i)));
            if (digit > 9) {
                digit = digit % 10;
            }
            sum += digit;
        }
        if (sum > 9) {
            sum = sum % 10;
        }
        return 10 - sum == Integer.parseInt(String.valueOf(nationalIdentificationNumber.charAt(10)));
    }

    private boolean genderCoincidesWithPerson(Person person) {
        int digitGender = Integer.parseInt(String.valueOf(person.getNationalIdentificationNumber().charAt(9)));
        if (person.getGender().equals(Person.GENDER.FEMALE)) {
            return digitGender % 2 == 0;
        }
        return digitGender % 2 == 1;
    }

    private boolean checkCorrectnessOfNationalIdentificationNumber(String nationalIdentificationNumber) {
        final int NATIONAL_IDENTIFICATION_NUMBER_LENGTH = 11;
        if (nationalIdentificationNumber.length() != NATIONAL_IDENTIFICATION_NUMBER_LENGTH) return false;
        return nationalIdentificationNumber.matches("^\\d{11}$");
    }
}
