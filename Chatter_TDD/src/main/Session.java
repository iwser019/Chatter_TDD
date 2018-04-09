package main;

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

	public Boolean hasExactMatch(String string) {
		// TODO добавить проверки
		return true;
	}
}
