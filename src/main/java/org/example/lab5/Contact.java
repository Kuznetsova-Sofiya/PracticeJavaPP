/*
Кузнецова София 2 курс лр4 java #1

Условие:
Использовать контейнеры:
 Vector, ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap.)

1) Задача "контакты"
а) разработать класс Контакт, определяющий запись в электронной книге мобильного
телефона и содержащий по меньшей мере следующие поля:
- *Наименование (имя человека или организации)
- *Номер телефона мобильного
- Номер телефона рабочего
- Номер телефона (домашнего)
- Адрес электронной почты
- Адрес веб-страницы
- Адрес

Обязательными является поля помеченные *, остальные поля могут быть пустыми

б) класс Контакт должен реализовать:
-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
-интерфейс Iterator - индексатор по всем полям объекта Контакт
-метод для сохранения значений всех полей в строке текста (переопределить toString())
-конструктор или метод для инициализации объекта из строки текста

в) Для тестирования класса Контакт, создать консольное приложение позволяющее
создать небольшой массив контактов и напечатать отсортированными по
выбранному полю.


 Тест(вывод на консоль):
Контакты, загруженные из файла:
Alice|1234567890|1112223333||alice@example.com|www.alice.com|123 Wonderland Ave
Bob|9876543210||4445556666|bob@example.com|www.bob.com|456 Ocean Blvd
Charlie|5555555555|||charlie@example.com||789 Hill St
Diana|3334445555|2223334444|7778889999|diana@example.com|www.diana.com|101 Maple Dr
Eve|6667778888|||eve@example.com||456 Elm St

Фильтровать по номеру мобильного телефона '1234567890':
Alice|1234567890|1112223333||alice@example.com|www.alice.com|123 Wonderland Ave
Отфильтрованные контакты, записанные в 'filtered_contacts.txt'


данные из файла contacts.txt:
Alice|1234567890|1112223333||alice@example.com|www.alice.com|123 Wonderland Ave
Bob|9876543210||4445556666|bob@example.com|www.bob.com|456 Ocean Blvd
Charlie|5555555555|||charlie@example.com||789 Hill St
Diana|3334445555|2223334444|7778889999|diana@example.com|www.diana.com|101 Maple Dr
Eve|6667778888|||eve@example.com||456 Elm St

вывод в файл filtered_contacts.txt:
Alice|1234567890|1112223333||alice@example.com|www.alice.com|123 Wonderland Ave


 */

package org.example.lab5;

import java.io.*;
import java.util.*;

public class Contact implements Comparable<Contact>, Iterable<String>, Iterator<String> {

    // исключение для проверки недопустимых аргументов при создании контакта
    public static class ArgException extends Exception {
        private static final long serialVersionUID = 1L;
        ArgException(String arg) {
            super("Недопустимый аргумент: " + arg);
        }
    }
    // Названия полей контакта для форматирования и вывода
    public static final String[] names = {
            "*Contact", "*Mobile", "Work", "Home", "E-mail", "WWW-page", "Address"
    };
    // Форматирование строк для вывода на консоль или в файл
    public static String[] formatStr = {
            "%-9s", "%-9s", "%-9s", "%-9s", "%-17s", "%-17s", "%-9s"
    };

    private String[] areas;
    private int iteratorIdx = 0;

    // Конструктор с несколькими аргументами, минимальный контакт требует хотя бы два поля
    public Contact(String... args) throws ArgException {
        setup(args);
    }
    // Конструктор, принимающий строку, разделенную '|' для создания объекта
    public Contact(String str) throws ArgException {
        String[] args = str.split("\\|");
        setup(args);
    }
    // Метод setup выполняет инициализацию и проверку переданных аргументов
    private void setup(String[] args) throws ArgException {
        if (args == null || args.length < 2 || args.length > names.length) {
            throw new ArgException(Arrays.toString(args));// Выбрасываем исключение, если не соблюден формат
        }
        areas = new String[names.length];
        // Заполняем обязательные и опциональные поля
        for (int i = 0; i < args.length; i++) {
            setArea(i, args[i]);
        }
        // Оставшиеся неиспользованные поля заполняются пустыми строками
        for (int i = args.length; i < names.length; i++) {
            areas[i] = "";
        }
    }
    // Получить значение поля по индексу, с проверкой на допустимый диапазон
    public String getArea(int idx) {
        if (idx >= areas.length || idx < 0) throw new IndexOutOfBoundsException();
        return areas[idx];
    }
    // Установить значение поля с валидацией, проверяем правильность значений полей
    public void setArea(int idx, String value) throws ArgException {
        if (idx >= areas.length || idx < 0) throw new IndexOutOfBoundsException();
        if ((idx == 0 && !validName(value)) || (idx == 1 && !validMobile(value)) ||
                (idx > 1 && idx < 4 && !validPhone(value)) || (idx == 4 && !validEMail(value)) ||
                (idx == 5 && !validWWWPage(value)) || (idx == 6 && !validAddress(value))) {
            throw new ArgException(value);
        }
        areas[idx] = value;
    }
    // Методы для проверки допустимости полей
    protected boolean validName(String str) { return str != null && !str.isEmpty(); }
    protected boolean validMobile(String str) { return str != null && !str.isEmpty(); }
    protected boolean validPhone(String str) { return str != null; }
    protected boolean validEMail(String str) { return str != null; }
    protected boolean validWWWPage(String str) { return str != null; }
    protected boolean validAddress(String str) { return str != null; }

    // Метод для сортировки контактов по умолчанию (по имени)
    @Override
    public int compareTo(Contact other) {
        return this.areas[0].compareTo(other.areas[0]);
    }

    // Метод для создания Comparator, сортировка по указанному полю (например, по телефону)
    public static Comparator<Contact> getComparator(int sortBy) {
        if (sortBy < 0 || sortBy >= names.length) throw new IndexOutOfBoundsException();
        return Comparator.comparing(c -> c.areas[sortBy], Comparator.nullsFirst(String::compareTo));
    }

    // Переопределение методов итератора для перебора полей объекта
    @Override
    public Iterator<String> iterator() {
        reset();
        return this;
    }

    public void reset() { iteratorIdx = 0; }
    @Override public boolean hasNext() { return iteratorIdx < areas.length; }
    @Override public String next() { return iteratorIdx < areas.length ? areas[iteratorIdx++] : null; }

    // Преобразование объекта в строку для вывода/сохранения
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < areas.length; i++) {
            result.append(areas[i]).append(i < areas.length - 1 ? "|" : "");
        }
        return result.toString();
    }

    // Загрузка контактов из файла
    public static List<Contact> loadContactsFromFile(String filename) throws IOException, ArgException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contacts.add(new Contact(line));
            }
        }
        return contacts;
    }

    // Запись списка контактов в файл
    public static void writeContactsToFile(List<Contact> contacts, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
        }
    }

    // Фильтр контактов по номеру мобильного телефона
    public static List<Contact> filterByMobile(List<Contact> contacts, String mobile) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getArea(1).equals(mobile)) {
                result.add(contact);
            }
        }
        return result;
    }


    // Фильтр контактов по e-mail
    public static List<Contact> filterByEmail(List<Contact> contacts, String email) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getArea(4).equals(email)) {
                result.add(contact);
            }
        }
        return result;
    }

    // Фильтр контактов по адресу
    public static List<Contact> filterByAddress(List<Contact> contacts, String address) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getArea(6).equals(address)) {
                result.add(contact);
            }
        }
        return result;
    }
}

class ContactTest {
    public static void main(String[] args) {
        try {
            // Чтение контактов из файла
            List<Contact> contacts = Contact.loadContactsFromFile("D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab5\\contacts.txt");
            System.out.println("Контакты, загруженные из файла:");

            for (Contact contact : contacts) {
                System.out.println(contact);
            }

            System.out.println("\nФильтровать по номеру мобильного телефона '1234567890':");
            List<Contact> filteredByMobile = Contact.filterByMobile(contacts, "1234567890");
            filteredByMobile.forEach(System.out::println);

            // Запись отфильтрованных контактов в файл
            Contact.writeContactsToFile(filteredByMobile, "D:\\Study\\Java\\PracticeJavaPP\\src\\main\\java\\org\\example\\lab5\\filtered_contacts.txt");
            System.out.println("Отфильтрованные контакты, записанные в 'filtered_contacts.txt'");

        } catch (IOException | Contact.ArgException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}