package tests;

import static org.junit.jupiter.api.Assertions.*;
import main.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;


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
	void SessionHasExactMatchTest() {
		Session session = new Session();
		Assertions.assertEquals(true, session.hasExactMatch("Ты спишь?"));
	}
	
	@Test
	void SessionHasNoExactMatchTest() {
		Session session = new Session();
		Assertions.assertEquals(false, session.hasExactMatch("фывапролджэ"));
	}
	
	@Test
	void SessionHasNoExactMatchForNullTest() {
		Session session = new Session();
		Assertions.assertEquals(false, session.hasExactMatch(null));
	}
	
	@Test
	void SessionExactMatchBaseTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(matchBase, session.getExactMatchBase());
	}
}
