package main;

public class Session {
	public Session() {
		
	}

	/**
	 * Метод для получения ответа
	 * @param saying Исходный запрос
	 * @return Ответ
	 */
	public String getAnswer(String saying) {
		// TODO: создать логику
		if (saying == "Привет")
			return "Привет";
		return "Неплохо.";
	}
}
