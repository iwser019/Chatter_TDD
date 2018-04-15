package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Pair;

class PairTest {

	@Test
	void PairNotNull() {
		Pair<Object, Object> pair = new Pair<>(null, null);
		Assertions.assertNotNull(pair);
	}
	
	@Test
	void PairHasXValue() {
		Pair<Integer, Integer> pair = new Pair<>(1, 2);
		Assertions.assertEquals(1, (int)pair.getX());
	}
	
	@Test
	void PairHasYValue() {
		Pair<Integer, Integer> pair = new Pair<>(1, 2);
		Assertions.assertEquals(2, (int)pair.getY());
	}

}
