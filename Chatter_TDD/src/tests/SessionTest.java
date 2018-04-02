package tests;

import static org.junit.jupiter.api.Assertions.*;
import main.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SessionTest {

	@Test
	void SessionNotNullTest() {
		Session session = new Session();
		Assertions.assertNotNull(session);
	}

	@Test
	void SessionGetAnswerTest() {
		Session session = new Session();
		Assertions.assertEquals("Привет", session.getAnswer("Привет"));
	}
}
