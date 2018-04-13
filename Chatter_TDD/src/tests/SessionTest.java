package tests;

import static org.junit.jupiter.api.Assertions.*;
import main.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;


class SessionTest {

	/**
	 * Создание объекта сеанса
	 */
	@Test
	void SessionNotNullTest() {
		Session session = new Session();
		Assertions.assertNotNull(session);
	}

	/**
	 * Тесты на получение ответа
	 */
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
	
	/**
	 * Тесты для работы с базой точных соответствий
	 */
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
	
	/**
	 * Тесты для работы с базой типичных реплик
	 */
	@Test
	void SessionHasTypicalMatchTest() {
		Session session = new Session();
		Assertions.assertEquals(true, session.hasTypicalMatch("Не знаю."));
	}
	
	@Test
	void SessionHasNoTypicalMatchTest() {
		Session session = new Session();
		Assertions.assertEquals(false, session.hasTypicalMatch("фывапролджэ"));
	}
	
	@Test
	void SessionHasNoTypicalMatchForNull() {
		Session session = new Session();
		Assertions.assertEquals(false, session.hasTypicalMatch(null));
	}
	
	@Test
	void SessionTypicalMatchBaseTest() {
		Session session = new Session();
		Map<String, String[]> matchBase = new HashMap<String, String[]>();
		matchBase.put("Не знаю.", new String[] {
				"Я тоже не знаю.",
				"А почему?",
				"Жаль."
		});
		session.setTypicalMatchBase(matchBase);
		Map<String, String[]> matchBaseAlt = session.getTypicalMatchBase();
		Boolean hasSameKeys = true;
		Boolean hasSameValues = true;
		for (String str : matchBaseAlt.keySet()) {
			if (!matchBase.containsKey(str))
				hasSameKeys = hasSameKeys && false;
		}
		Assertions.assertEquals(true, hasSameKeys);
		for (String str : matchBaseAlt.keySet()) {
			if (matchBase.get(str).equals(matchBaseAlt.get(str)))
				hasSameValues = hasSameValues && false;
		}
		Assertions.assertEquals(true, hasSameValues);
	}
}
