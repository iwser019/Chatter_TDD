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
	
	@Test
	void SessionGetAnswerToQuestionTest() {
		Session session = new Session();
		Assertions.assertEquals("Неплохо.", session.getAnswer("Как дела?"));
	}
	
	@Test
	void SessionNullSayingTest() {
		Session session = new Session();
		Assertions.assertEquals("Не понял.", session.getAnswer(null));
	}
	
	@Test
	void SessionHasExactMatch() {
		Session session = new Session();
		Assertions.assertEquals(true, session.hasExactMatch("Ты спишь?"));
	}
}
