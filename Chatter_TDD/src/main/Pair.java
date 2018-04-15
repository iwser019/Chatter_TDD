package main;

/**
 * Класс объекта пары.
 * Представляет собой пару из двух элементов разных типов
 * @param <X> Тип первого элемента
 * @param <Y> Тип второго элемента
 */
public class Pair<X, Y> {
	private X x;
	private Y y;
	/**
	 * Конструктор объекта пары
	 * @param item1 Первый элемент
	 * @param item2 Второй элемент
	 */
	public Pair(X item1, Y item2) {
		this.x = item1;
		this.y = item2;
	}
	/**
	 * Получение первого элемента
	 * @return Первый элемент
	 */
	public X getX() {
		// TODO добавить возврат значения
		return this.x;
	}
	/**
	 * Получение второго элемента
	 * @return Второй элемент
	 */
	public Y getY() {
		//TODO добавить возврат значения
		return this.y;
	}
}
