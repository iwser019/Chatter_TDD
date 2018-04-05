package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import main.Session;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Session session = new Session();
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
