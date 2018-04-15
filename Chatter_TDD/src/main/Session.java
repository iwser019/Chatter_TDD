package main;

import java.util.HashMap;
import java.util.Map;

public class Session {
	// TODO: перейти к недетерминированному выводу
	private Map<String, String> exactMatchBase;
	private Map<String, String[]> typicalMatchBase;
	
	public Session() {
		exactMatchBase = new HashMap<String, String>();
		typicalMatchBase = new HashMap<String, String[]>();
	}

	/**
	 * Метод для получения ответа
	 * @param saying Исходный запрос
	 * @return Ответ
	 */
	public String getAnswer(String saying) {
		// TODO: создать логику
		if (saying == null)
			return "Не понял.";
		else if (saying.equals("Привет"))
			return "Привет";
		else if (saying.equals("Как дела?"))
			return "Неплохо.";
		return "Не понял.";
	}
	
	/**
	 * Проверка на то, имеется ли в базе точное соответствие строке
	 * @param Исходная строка
	 * @return Результат проверки
	 */
	public Boolean hasExactMatch(String string) {
		if (string == null)
			return false;
		return exactMatchBase.containsKey(string);
	}
	
	/**
	 * Установка базы точных соответствий реплик
	 * @param matchBase Словарь соответствий реплик (запрос - ответ)
	 */
	public void setExactMatchBase(Map<String, String> matchBase) {
		exactMatchBase = matchBase;
	}
	
	/**
	 * Получение установленной базы точных соответствий реплик
	 * @return Словарь соответствий реплик (запрос - ответ)
	 */
	public Map<String, String> getExactMatchBase() {
		return exactMatchBase;
	}
	
	/**
	 * Проверка на то, имеется ли в базе соответствие для типичной реплики
	 * @param string Исходная строка
	 * @return Результат проверки
	 */
	public Boolean hasTypicalMatch(String string) {
		if (string == null)
			return false;
		return typicalMatchBase.containsKey(string);
	}

	/**
	 * Установка базы типичных реплик
	 * @param matchBase Словарь типичных реплик (запрос - набор ответов)
	 */
	public void setTypicalMatchBase(Map<String, String[]> matchBase) {
		typicalMatchBase = matchBase;
	}

	/**
	 * Получение установленной базы типичных реплик
	 * @return Словарь типичных реплик (запрос - набор ответов)
	 */
	public Map<String, String[]> getTypicalMatchBase() {
		return typicalMatchBase;
	}

	public String[] splitSentence(String string) {
		// TODO Добавить логику разбивки на предложения
		return null;
	}
}
