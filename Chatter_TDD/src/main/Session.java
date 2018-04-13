package main;

import java.util.HashMap;
import java.util.Map;

public class Session {
	// TODO: перейти к недетерминированному выводу
	
	public Session() {
		
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
		// TODO добавить проверки
		if (string == null)
			return false;
		if (string.equals("Ты спишь?"))
			return true;
		return false;
	}
	
	/**
	 * Установка базы точных соответствий реплик
	 * @param matchBase Словарь соответствий реплик (запрос - ответ)
	 */
	public void setExactMatchBase(Map<String, String> matchBase) {
		// TODO Добавить внесение заглушки
		
	}
	
	/**
	 * Получение установленной базы точных соответствий реплик
	 * @return Словарь соответствий реплик (запрос - ответ)
	 */
	public Map<String, String> getExactMatchBase() {
		// TODO Добавить действительное получение
		Map<String, String> matchBase = new HashMap<String, String>();
		matchBase.put("Ты спишь?", "Нет");
		matchBase.put("Сколько будет два плюс два?", "Четыре.");
		return matchBase;
	}
	
	/**
	 * Проверка на то, имеется ли в базе соответствие для типичной реплики
	 * @param string Исходная строка
	 * @return Результат проверки
	 */
	public Boolean hasTypicalMatch(String string) {
		// TODO Добавить логику
		if (string == null)
			return false;
		if (string == "Не знаю.")
			return true;
		return false;
	}

	/**
	 * Установка базы типичных реплик
	 * @param matchBase Словарь типичных реплик (запрос - набор ответов)
	 */
	public void setTypicalMatchBase(Map<String, String[]> matchBase) {
		// TODO Добавить логику
		
	}

	/**
	 * Получение установленной базы типичных реплик
	 * @return Словарь типичных реплик (запрос - набор ответов)
	 */
	public Map<String, String[]> getTypicalMatchBase() {
		// TODO Auto-generated method stub
		Map<String, String[]> matchBase = new HashMap<String, String[]>();
		matchBase.put("Не знаю.", new String[] {
				"Я тоже не знаю.",
				"А почему?",
				"Жаль."
		});
		return matchBase;
	}
}
