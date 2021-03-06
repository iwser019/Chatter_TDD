package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Session {
	// TODO: перейти к недетерминированному выводу
	private Map<String, String> exactMatchBase;
	private Map<String, String[]> typicalMatchBase;
	private ArrayList<Pair<String[], String>> keywordBase;
	private String[] genericBase;
	private Random randomizer;
	
	public Session() {
		exactMatchBase = new HashMap<String, String>();
		typicalMatchBase = new HashMap<String, String[]>();
		keywordBase = new ArrayList<Pair<String[], String>>();
		genericBase = new String[] {};
		randomizer = new Random();
	}

	/**
	 * Метод для получения ответа
	 * @param saying Исходный запрос
	 * @return Ответ
	 */
	public String getAnswer(String saying) {
		// TODO: создать логику
		String answer = "";
		if (saying == null)
			return "Не понял.";
		else {
			if (hasExactMatch(saying)) {
				answer = exactMatchBase.get(saying);
			}
			else {
				if (hasTypicalMatch(saying)) {
					String[] answers = typicalMatchBase.get(saying);
					answer = answers[randomizer.nextInt(answers.length)];
				}
				else {
					if (hasExactMatchSub(saying)) {
						ArrayList<String> answers = new ArrayList<>();
						String[] exactSrc = new String[exactMatchBase.keySet().size()];
						exactSrc = exactMatchBase.keySet().toArray(exactSrc);
						String[] srcWords = splitWords(saying);
						for (String exactStr : exactSrc) {
							int wordCounter = 0;
							String[] exactWords = splitWords(exactStr);
							for (String srcWord : srcWords) {
								for (String exactWord : exactWords) {
									if (srcWord.equals(exactWord))
										wordCounter++;
								}
							}
							if (wordCounter >= 2) {
								answers.add(exactStr);
							}
						}
						answer = answers.get(randomizer.nextInt(answers.size()));
					}
					else {
						if (hasKeywordMatch(saying)) {
							ArrayList<String> answers = new ArrayList<>();
							String[] srcWords = splitWords(saying.toLowerCase());
							for (Pair<String[], String> pair : keywordBase) {
								String[] keywords = pair.getX();
								int size = keywords.length;
								int counter = 0;
								for (String keyword : keywords) {
									Boolean ok = false;
									for (String word : srcWords) {
										if (word.equals(keyword))
											ok = ok || true;
									}
									if (ok)
										counter++;
								}
								if (counter == size) {
									answers.add(pair.getY());
								}
							}
							answer = answers.get(randomizer.nextInt(answers.size()));
						}
						else {
							if (genericBase.length > 0) {
								answer = genericBase[randomizer.nextInt(genericBase.length)];
							}
							else
								answer = "Не понял.";
						}
					}
				}
			}
		}
		return answer;
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

	/**
	 * Разбивка текста реплики на предложения
	 * @param string Полный текст реплики
	 * @return Массив отдельных предложений
	 */
	public String[] splitSentence(String string) {
		if (string == null)
			return new String[] {};
		// если строка - не нулевая
		ArrayList<String> result = new ArrayList<>();
		int size = string.length();
		int ptr = 0;
		int ptrStart = 0;
		char currChar = 0;
		Boolean atEnd = false;
		Boolean finalized = false;
		while (!atEnd) {
			if (ptr >= size) {
				atEnd = true;
				break;
			}
			currChar = string.charAt(ptr);
			if (currChar == '.' 
					|| currChar == '?' 
					|| currChar == '!') {
				if ((ptr + 1) < size) {
					if (string.charAt(ptr + 1) == ' ') {
						result.add(string.substring(ptrStart, ptr + 1));
						if ((ptr + 2 < size)) {
							ptrStart = ptr + 2;
							ptr = ptr + 2;
						}
						else {
							finalized = true;
							atEnd = true;
						}
					}
				}
				else {
					result.add(string.substring(ptrStart, ptr + 1));
					finalized = true;
					atEnd = true;
				}
			}
			else {
				ptr++;
			}
		}
		if (atEnd && !finalized)
			result.add(string.substring(ptrStart, ptr + 1));
		String[] resultArr = new String[result.size()];
		resultArr = result.toArray(resultArr);
		return resultArr;
	}
	
	/**
	 * Поиск соответствия из обычных реплик на уровне значимых слов.
	 * @param string Исходный текст
	 * @return Результат проверки
	 */
	public Boolean hasExactMatchSub(String string) {
		if (string == null)
			return false;
		Boolean result = false;
		String[] exactSrc = new String[exactMatchBase.keySet().size()];
		exactSrc = exactMatchBase.keySet().toArray(exactSrc);
		String[] srcWords = splitWords(string);
		for (String exactStr : exactSrc) {
			int wordCounter = 0;
			String[] exactWords = splitWords(exactStr);
			for (String srcWord : srcWords) {
				for (String exactWord : exactWords) {
					if (srcWord.equals(exactWord))
						wordCounter++;
				}
			}
			if (wordCounter >= 2) {
				result = result || true;
				break;
			}
		}
		return result;
	}

	/**
	 * Разбивка текста на слова
	 * @param string Исходный текст
	 * @return Массив слов
	 */
	public String[] splitWords(String string) {
		if (string == null)
			return new String[] {};
		return string.split(" ");
	}

	/**
	 * Установка базы ключевых слов
	 * @param keywordBase Массив пар из набора ключевых слов и соответствующего ответа
	 */
	public void setKeywordBase(ArrayList<Pair<String[], String>> keywordBase) {
		this.keywordBase = keywordBase;
	}

	/**
	 * Получение базы ключевых слов
	 * @return Массив пар из набора ключевых слов и соответствующего ответа
	 */
	public ArrayList<Pair<String[], String>> getKeywordBase() {
		return keywordBase;
	}
	
	/**
	 * Проверка на соответствие на уровне ключевых слов
	 * @param string Исходный текст
	 * @return Результат проверки
	 */
	public Boolean hasKeywordMatch(String string) {
		if (string == null)
			return false;
		Boolean result = false;
		String[] srcWords = splitWords(string.toLowerCase());
		for (Pair<String[], String> pair : keywordBase) {
			String[] keywords = pair.getX();
			int size = keywords.length;
			int counter = 0;
			for (String keyword : keywords) {
				Boolean ok = false;
				for (String word : srcWords) {
					if (word.equals(keyword))
						ok = ok || true;
				}
				if (ok)
					counter++;
			}
			if (counter == size) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Установка базы общих ответов
	 * @param genBase Набор общих ответов
	 */
	public void setGenericBase(String[] genBase) {
		genericBase = genBase;
	}

	/**
	 * Получение базы общих ответов
	 * @return Набор общих ответов
	 */
	public String[] getGenericBase() {
		return genericBase;
	}
}
