package main;

public class Session {
	public Session() {
		
	}

	public String getAnswer(String saying) {
		// TODO: создать логику
		if (saying == "Привет")
			return "Привет";
		return "Неплохо.";
	}
}
