package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import main.Session;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Session session = new Session();
		// Добавление минимального примера
		Map<String, String> exactMatch = new HashMap<String, String>();
		exactMatch.put("Как дела?", "Неплохо.");
		exactMatch.put("Как поживаешь?", "Средне.");
		exactMatch.put("Ты спишь?", "Нет");
		exactMatch.put("Сколько будет два плюс два?", "Четыре.");
		session.setExactMatchBase(exactMatch);
		Map<String, String[]> typicalBase = new HashMap<String, String[]>();
		typicalBase.put("Привет", new String[] {
				"Привет",
				"Здовово",
				"Здравствуй"
		});
		typicalBase.put("Не знаю.", new String[] {
				"Я тоже не знаю.",
				"А почему?",
				"Жаль."
		});
		session.setTypicalMatchBase(typicalBase);
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
		String[] genBase = new String[] {
				"Ты вообще о чем?",
				"И такое бывает...", 
				"Ясно, понятно."
		};
		session.setGenericBase(genBase);
		// Собственно, выполнение
		System.out.println("Введите stop() для выхода.\n");
		String saying = null;
		String answer = null;
		while (true) {
			saying = br.readLine();
			if (saying.equals("stop()"))
				break;
			else if (saying.equals(""))
				saying = null;
			answer = session.getAnswer(saying);
			System.out.println(answer);
		}
		
	}
}
