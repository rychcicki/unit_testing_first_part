package org.example.unittesting.verifier.customer;

import org.example.unittesting.customer.Person;
import org.example.unittesting.verifier.Verification;

/**
 * Implementacja weryfikacji obrazującej problemy z testami, które nie weryfikują poprawnie implementacji.
 */
public class SimpleVerification implements Verification {

	private boolean verificationPassed = true;

	/**
	 * Przykład problemu w testach: metoda z niezaimplementowaną logiką, dla której przechodzą źle napisane testy
	 */
	@Override
	public boolean passes(Person person) {
		// TODO: use someLogicResolvingToBoolean(person);
		return false;
	}

	private boolean someLogicResolvingToBoolean(Person person) {
		// TODO: calculate based on verificationPassed value
		throw new UnsupportedOperationException("Not yet implemented!");
	}

	boolean verificationPassed() {
		return verificationPassed;
	}
}
