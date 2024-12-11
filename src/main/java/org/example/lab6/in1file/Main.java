package org.example.lab6.in1file;

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

public class Main {
    public static void main(String[] args) {
        // Объявить экземпляр класса Adapter
        Adapter adapter = new Adapter();

        // Объявить экземпляр класса Client и передать ему экземпляр адаптера
        Client client = new Client(adapter);

        // Вызвать метод Show()
        client.Show();

        // Дополнительное тестирование
        ITarget testClient = new Adapter();
        testClient.ClientDouble(3.77);
        testClient.ClientInt(255);
        testClient.ClientChar('z');
    }
}

// Интерфейс ITarget
interface ITarget {
    void ClientDouble(double value);
    void ClientInt(int value);
    void ClientChar(char value);
}

// Класс Original
class Original {
    public void OriginalDouble(double value) {
        System.out.println("Method Original.OriginalDouble(), value = " + value);
    }

    public void OriginalInt(int value) {
        System.out.println("Method Original.OriginalInt(), value = " + value);
    }

    public void OriginalChar(char value) {
        System.out.println("Method Original.OriginalChar(), value = " + value);
    }
}

// Адаптер для класса Original
class Adapter extends Original implements ITarget {
    @Override
    public void ClientDouble(double value) {
        // Перенаправляем вызов в OriginalDouble
        this.OriginalDouble(value);
    }

    @Override
    public void ClientInt(int value) {
        // Заменяем логику метода: выводим удвоенное значение
        this.OriginalInt(value * 2);
    }

    @Override
    public void ClientChar(char value) {
        // Заменяем логику метода: выводим символ 5 раз
        for (int i = 0; i < 5; i++) {
            this.OriginalChar(value);
        }
    }
}

// Класс Client
class Client {
    private ITarget client;

    public Client(ITarget client) {
        this.client = client;
    }

    public void Show() {
        client.ClientDouble(2.88);
        client.ClientInt(39);
        client.ClientChar('f');
    }
}
