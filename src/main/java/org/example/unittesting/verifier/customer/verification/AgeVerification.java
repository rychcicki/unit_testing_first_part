package org.example.unittesting.verifier.customer.verification;


import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.Verification;

/**
 * Weryfikacja wieku osoby wnioskującej o udzielenie pożyczki.
 */
public class AgeVerification implements Verification {

	@Override
	public boolean passes(Person person) {
		if (person.getAge() <= 0) {
			throw new IllegalStateException("Age cannot be negative.");
		}
		return person.getAge() >= 18 && person.getAge() <= 99;
	}
}
