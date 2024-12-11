package org.example.lab6;
/* Лаб6 Кузнецовой Софии 2 курс 5 группа

Условие:
Используя средства языка программирование Java решить следующую задачу.
Пусть задан класс с именем Original, в котором есть 3 метода с именами OriginalDouble(), OriginalInt(), OriginalChar(). Каждый из методов получает по одному параметру:
•	метод OriginalDouble() получает значение типа double и выводит его на экран;
•	метод OriginalInt() получает значение типа int и выводит его на экран;
•	метод OriginalChar() получает значение типа char и выводит его на экран.
Нужно адаптировать класс Original к потребностям класса Client, который имеет следующие требования:
•	метод OriginalDouble() нужно переименовать в ClientDouble(). Работа метода остается неизменной;
•	метод OriginalInt() нужно заменить методом ClientInt(). Метод ClientInt() должен выводить двойное значение (умноженное на 2) параметру типа int;
•	метод OriginalChar() нужно заменить методом ClientChar(). Метод ClientChar() выводит параметр типа char 5 раз.
Класс Client должен получать ссылку на интерфейс ITarget в конструкторе.


Тест:
Method Original.OriginalDouble(), value = 2.88
Method Original.OriginalInt(), value = 78
Method Original.OriginalChar(), value = f
Method Original.OriginalChar(), value = f
Method Original.OriginalChar(), value = f
Method Original.OriginalChar(), value = f
Method Original.OriginalChar(), value = f
Method Original.OriginalDouble(), value = 3.77
Method Original.OriginalInt(), value = 510
Method Original.OriginalChar(), value = z
Method Original.OriginalChar(), value = z
Method Original.OriginalChar(), value = z
Method Original.OriginalChar(), value = z
Method Original.OriginalChar(), value = z

 */
public class Solver {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        Client client = new Client(adapter);

        client.Show();

        ITarget testClient = new Adapter();
        testClient.ClientDouble(3.77);
        testClient.ClientInt(255);
        testClient.ClientChar('z');
    }
}