package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Pair;

class PairTest {

	@Test
	void PairNotNull() {
		Pair<Object, Object> pair = new Pair<>(null, null);
	}

}
