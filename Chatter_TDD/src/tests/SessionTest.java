package tests;

import static org.junit.jupiter.api.Assertions.*;
import main.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import main.Pair;


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
		Map<String, String[]> typicalBase = new HashMap<String, String[]>();
		typicalBase.put("Привет", new String[] {
				"Привет",
				"Здовово",
				"Здравствуй"
		});
		session.setTypicalMatchBase(typicalBase);
		Boolean ok = false;
		String[] answers = new String[] {
				"Привет",
				"Здовово",
				"Здравствуй"
		};
		String answer = session.getAnswer("Привет");
		for (String srcAnswer : answers) {
			if (answer.equals(srcAnswer)) {
				ok = ok || true;
			}
		}
		Assertions.assertEquals(true, ok);
	}
	
	@Test
	void SessionGetAnswerToQuestionTest() {
		Session session = new Session();
		Map<String, String> exactMatch = new HashMap<String, String>();
		exactMatch.put("Как дела?", "Неплохо.");
		exactMatch.put("Как поживаешь?", "Средне.");
		session.setExactMatchBase(exactMatch);
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
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(true, session.hasExactMatch("Ты спишь?"));
	}
	
	@Test
	void SessionHasNoExactMatchTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(false, session.hasExactMatch("фывапролджэ"));
	}
	
	@Test
	void SessionHasNoExactMatchForNullTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
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
		Map<String, String[]> matchBase = new HashMap<String, String[]>();
		matchBase.put("Не знаю.", new String[] {
				"Я тоже не знаю.",
				"А почему?",
				"Жаль."
		});
		session.setTypicalMatchBase(matchBase);
		Assertions.assertEquals(true, session.hasTypicalMatch("Не знаю."));
	}
	
	@Test
	void SessionHasNoTypicalMatchTest() {
		Session session = new Session();
		Map<String, String[]> matchBase = new HashMap<String, String[]>();
		matchBase.put("Не знаю.", new String[] {
				"Я тоже не знаю.",
				"А почему?",
				"Жаль."
		});
		session.setTypicalMatchBase(matchBase);
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
			String[] values = (String[])matchBase.get(str);
			String[] valuesAlt = (String[])matchBaseAlt.get(str);
			if (!Arrays.equals(values, valuesAlt))
				hasSameValues = hasSameValues && false;
		}
		Assertions.assertEquals(true, hasSameValues);
	}
	
	/**
	 * Тесты для вспомогательной функции для разбивки текста на предложения
	 */
	@Test
	void SessionSentenceSplitTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {
			"Не знаю.", "Как-то не думал."
		}, session.splitSentence("Не знаю. Как-то не думал.")));
	}
	
	@Test
	void SessionSentenceSplitSingleTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {
				"фывапролджэ?"
		}, session.splitSentence("фывапролджэ?")));
	}
	
	@Test
	void SessionSentenceSplitNullTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {}, session.splitSentence(null)));
	}
	
	/**
	 * Тесты для поиска точных соответствий для подстрок исходного текста
	 */
	@Test
	void SessionHasExactMatchSubTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(true, session.hasExactMatchSub("Ты спишь? Только честно."));
	}
	
	@Test
	void SessionHasInsufficentExactMatchSubTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(false, session.hasExactMatchSub("Я думал, ты спал..."));
	}
	
	@Test
	void SessionHasNoExactMatchSubTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(false, session.hasExactMatchSub("фывапролджэ"));
	}
	
	@Test
	void SessionNullExactMatchSubTest() {
		Session session = new Session();
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(matchBase);
		Assertions.assertEquals(false, session.hasExactMatchSub(null));
	}
	
	/**
	 * Тесты для вспомогательной функции разделения на слова
	 */
	@Test
	void SessionWordSplitTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {
				"Сколько",
				"будет",
				"два",
				"плюс",
				"два?"
		}, session.splitWords("Сколько будет два плюс два?")));
	}
	
	@Test
	void SessionWordSplitSingleTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {
				"фывапролджэ"
		}, session.splitWords("фывапролджэ")));
	}
	
	@Test
	void SessionWordSplitNullTest() {
		Session session = new Session();
		Assertions.assertEquals(true, Arrays.equals(new String[] {}, session.splitWords(null)));
	}
	
	/**
	 * Тесты для работы с базой ключевых слов
	 */
	
	@Test
	void SessionKeywordBaseTest() {
		Session session = new Session();
		ArrayList<Pair<String[], String>> keywordBase = new ArrayList<Pair<String[], String>>();
		keywordBase.add(
				new Pair<>(
						new String[] {
								"блин,"
								}, 
						"Я тоже люблю блины."
						)
				);
		session.setKeywordBase(keywordBase);
		ArrayList<Pair<String[], String>> keywordAlt = session.getKeywordBase();
		// структуры должны быть в точности равны
		boolean isOK = true;
		if (keywordAlt.size() != keywordBase.size()) {
			isOK = false;
		}
		else {
			int size = keywordBase.size();
			for (int i = 0; i < size; i++) {
				Pair<String[], String> pairSrc = keywordBase.get(i), 
						pairDest = keywordAlt.get(i);
				if (!pairSrc.getY().equals(pairSrc.getY())) {
					isOK = false;
					break;
				}
				String[] kwSrc = pairSrc.getX(), kwDest = pairDest.getX();
				isOK = isOK && Arrays.equals(kwSrc, kwDest);
				if (!isOK)
					break;
			}
		}
		Assertions.assertEquals(true, isOK);
	}
	
	/**
	 * Тесты для функции поиска соответствий по ключевым словам
	 */
	@Test
	void SessionHasKeywordMatchTest() {
		Session session = new Session();
		ArrayList<Pair<String[], String>> keywordBase = new ArrayList<Pair<String[], String>>();
		keywordBase.add(
				new Pair<>(
						new String[] {
								"не", "знаю."
						}, "А что ты вообще знаешь?")
				);
		session.setKeywordBase(keywordBase);
		Assertions.assertEquals(true, session.hasKeywordMatch("Я этого не знаю."));
	}
	
	@Test
	void SessionHasNoKeywordMatchTest() {
		Session session = new Session();
		ArrayList<Pair<String[], String>> keywordBase = new ArrayList<Pair<String[], String>>();
		keywordBase.add(
				new Pair<>(
						new String[] {
								"не", "знаю."
						}, "А что ты вообще знаешь?")
				);
		session.setKeywordBase(keywordBase);
		Assertions.assertEquals(false, session.hasKeywordMatch("А вот я знаю."));
	}
	
	@Test
	void SessionNullKeywordMatchTest() {
		Session session = new Session();
		ArrayList<Pair<String[], String>> keywordBase = new ArrayList<Pair<String[], String>>();
		keywordBase.add(
				new Pair<>(
						new String[] {
								"не", "знаю."
						}, "А что ты вообще знаешь?")
				);
		session.setKeywordBase(keywordBase);
		Assertions.assertEquals(false, session.hasKeywordMatch(null));
	}
	
	/**
	 * Работа с базой общих реплик
	 */
	@Test
	void SessionGenericBaseTest() {
		Session session = new Session();
		String[] genBase = new String[] {
				"Ты вообще о чем?",
				"И такое бывает...", 
				"Ясно, понятно."
		};
		session.setGenericBase(genBase);
		Assertions.assertEquals(true, Arrays.equals(genBase, session.getGenericBase()));
	}
	
}
