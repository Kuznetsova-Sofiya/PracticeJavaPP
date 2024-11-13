/*
Лабораторная работа №5 Java
Кузнецовой Софии 2 курс 5 группа

    Условие:
Постановка задачи
Необходимо прочитать данные, обработать их и записать выходные данные в заданном формате.

Входные данные
Входной файл input содержит данные в формате CSV.
Каждая запись в файле расположена на новой строке.
Разделителем между полями одной записи является символ точка с запятой (';').
Если значения какого-то поля записи нет, то разделить все равно присутствует.
Обязательными для заполнения являются только те поля, по которым строятся запросы для выборки данных.

Формат входных данных
Имеется список компаний.
Каждый элемент списка содержит следующие поля:
   Наименование (name)
    Краткое наименование (shortTitle)
    Дата актуализации (dateUpdate)
    Адрес (address)
    Дата основания (dateFoundation)
    Численность сотрудников (countEmployees)
    Аудитор (auditor)
    Телефон (phone)
    Email (email)
    Отрасль (branch)
    Вид деятельности (activity)
    Адрес страницы в Интернет (internetAddress/link)

Выходные данные
1. Прочитать данные из входного файла.
2. Выбрать данные по запросу.
3. Записать полученные данные в два файла (в XML-формате и JSON).

Запросы
1. Найти компанию по краткому наименованию.
2. Выбрать компании по отрасли.
3. Выбрать компании по виду деятельности.
4. Выбрать компании по дате основания в определенном промежутке (с и по).
5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).

Примечания
1. Ваша программа должна игнорировать различие между строчными и прописными буквами.
2. Необходимо вести статистику работы приложения в файле logfile.txt. Данные должны накапливаться. Формат данных: дата и время запуска приложения; текст запроса.


Алгоритм пограммы:
1. Настройка: Инициализировать лог-файл и загрузить данные о компаниях из CSV-файла.
2. Выбор запроса: Показать пользователю меню для выбора типа поиска по различным критериям.
3. Выполнение запроса: В зависимости от выбора пользователя выполнить поиск по заданному критерию.
4. Запись в лог-файл: Записать запрос и его параметры в лог-файл с текущей датой и временем.
5. Вывод результатов: Показать найденные компании в консоли.
7. Сохранение результатов: Записать результаты в XML и JSON файлы.


Тест:
Выберите номер запроса:
1. Поиск компании по краткому названию
2. Выбор компаний по отрасли
3. Выбор компаний по виду деятельности
4. Выбор компаний по дате основания в заданном диапазоне (от и до)
5. Выбор компаний по численности сотрудников в заданном диапазоне (от и до)
5
Введите численность сотрудников (с): 20
Введите численность сотрудников (по): 100
Результаты запроса:
нояб. 12, 2024 10:39:24 PM org.example.lab5_diffFails.Solver logApplicationUsage
INFO: Выбрать компании по численности сотрудников с 20 по 100

Компания:
--------------------------------------------------
Имя: CompanyA
Краткое наименование: ComA
Адрес: �. �����
--------------------------------------------------


Компания:
--------------------------------------------------
Имя: CompanyB
Краткое наименование: ComB
Адрес:
--------------------------------------------------


Компания:
--------------------------------------------------
Имя: CompanyC
Краткое наименование: ComC
Адрес: �. ������
--------------------------------------------------



 */




package org.example.lab5_diffFails;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Класс Company представляет данные о компании
class Company {
    String name;
    String shortTitle;
    String dateUpdate;
    String address;
    String dateFoundation;
    int countEmployees;
    String auditor;
    String phone;
    String email;
    String branch;
    String activity;
    String internetAddress;

    // Конструктор для инициализации полей объекта Company
    public Company(String[] fields) {
        // Инициализация полей с проверкой на существование значений
        this.name = fields.length > 0 ? fields[0] : "";
        this.shortTitle = fields.length > 1 ? fields[1] : "";
        this.dateUpdate = fields.length > 2 ? fields[2] : "";
        this.address = fields.length > 3 ? fields[3] : "";
        this.dateFoundation = fields.length > 4 ? fields[4] : "";
        this.countEmployees = (fields.length > 5 && !fields[5].isEmpty()) ? Integer.parseInt(fields[5]) : 0;
        this.auditor = fields.length > 6 ? fields[6] : "";
        this.phone = fields.length > 7 ? fields[7] : "";
        this.email = fields.length > 8 ? fields[8] : "";
        this.branch = fields.length > 9 ? fields[9] : "";
        this.activity = fields.length > 10 ? fields[10] : "";
        this.internetAddress = fields.length > 11 ? fields[11] : "";
    }

    // Метод для вывода информации о компании в строковом виде
    public String toString(String[] headers) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nКомпания:\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Имя: ").append(name).append("\n");
        sb.append("Краткое наименование: ").append(shortTitle).append("\n");
        sb.append("Адрес: ").append(address).append("\n");
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }
}

// Класс CompanyQuery содержит методы для поиска компаний по разным критериям
class CompanyQuery {
    private List<Company> companies;

    public CompanyQuery(List<Company> companies) {
        this.companies = companies;
    }

    // Поиск компании по краткому наименованию
    public List<Company> findByShortTitle(String shortTitle) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies) {
            if (company.shortTitle.equalsIgnoreCase(shortTitle.trim())) {
                results.add(company);
            }
        }
        return results;
    }

    // Поиск компаний по отрасли
    public List<Company> findByIndustry(String industry) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies) {
            if (company.branch.equalsIgnoreCase(industry.trim())) {
                results.add(company);
            }
        }
        return results;
    }

    // Поиск компаний по виду деятельности
    public List<Company> findByActivity(String activity) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies) {
            if (company.activity.equalsIgnoreCase(activity.trim())) {
                results.add(company);
            }
        }
        return results;
    }

    // Поиск компаний по дате основания в заданном диапазоне
    public List<Company> findByFoundationDateRange(String startDate, String endDate) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies) {
            if (company.dateFoundation.compareTo(startDate) >= 0 && company.dateFoundation.compareTo(endDate) <= 0) {
                results.add(company);
            }
        }
        return results;
    }

    // Поиск компаний по численности сотрудников в заданном диапазоне
    public List<Company> findByEmployeeCountRange(int minCount, int maxCount) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies) {
            if (company.countEmployees >= minCount && company.countEmployees <= maxCount) {
                results.add(company);
            }
        }
        return results;
    }
}

// Класс CompanyFileWriter содержит методы для записи данных компаний в XML и JSON файлы
class CompanyFileWriter {
    // Запись списка компаний в XML файл
    public static void writeToXML(List<Company> companies, String outputFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println("<companies>");
            for (Company company : companies) {
                writer.println("  <company>");
                writer.printf("    <name>%s</name>%n", company.name);
                writer.printf("    <shortTitle>%s</shortTitle>%n", company.shortTitle);
                writer.printf("    <address>%s</address>%n", company.address);
                writer.println("  </company>");
            }
            writer.println("</companies>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись списка компаний в JSON файл
    public static void writeToJson(List<Company> companies, String outputFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println("[");
            for (int i = 0; i < companies.size(); i++) {
                Company company = companies.get(i);
                writer.print("  {");
                writer.printf("\"name\": \"%s\", ", company.name);
                writer.printf("\"shortTitle\": \"%s\", ", company.shortTitle);
                writer.printf("\"address\": \"%s\"", company.address);
                writer.print("}");
                if (i < companies.size() - 1) writer.println(",");
                else writer.println();
            }
            writer.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Solver {
    private static final Logger logger = Logger.getLogger("MyLog");

    public static void main(String[] args) {
        setupLogger();
        String csvFile = "input1.csv";
        List<Company> companies = loadCompaniesFromCSV(csvFile);

        Scanner scanner = new Scanner(System.in);
        CompanyQuery query = new CompanyQuery(companies);
        List<Company> results = new ArrayList<>();

        // Запрос ввода от пользователя
        System.out.println("Выберите номер запроса:");
        System.out.println("1. Поиск компании по краткому названию");
        System.out.println("2. Выбор компаний по отрасли");
        System.out.println("3. Выбор компаний по виду деятельности");
        System.out.println("4. Выбор компаний по дате основания в заданном диапазоне (от и до)");
        System.out.println("5. Выбор компаний по численности сотрудников в заданном диапазоне (от и до)");

        // Чтение выбора пользователя
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Логируем сообщение и запускаем выбранный запрос
        String logMessage = "";
        if (choice == 1) {
            System.out.print("Введите краткое наименование: ");
            String shortName = scanner.nextLine();
            results = query.findByShortTitle(shortName);
            logMessage = "Найти компанию по краткому наименованию: " + shortName;
        } else if (choice == 2) {
            System.out.print("Введите отрасль: ");
            String industry = scanner.nextLine();
            results = query.findByIndustry(industry);
            logMessage = "Выбрать компании по отрасли: " + industry;
        } else if (choice == 3) {
            System.out.print("Введите вид деятельности: ");
            String activity = scanner.nextLine();
            results = query.findByActivity(activity);
            logMessage = "Выбрать компании по виду деятельности: " + activity;
        } else if (choice == 4) {
            System.out.print("Введите дату основания (с): ");
            String startDate = scanner.nextLine();
            System.out.print("Введите дату основания (по): ");
            String endDate = scanner.nextLine();
            results = query.findByFoundationDateRange(startDate, endDate);
            logMessage = "Выбрать компании по дате основания с " + startDate + " по " + endDate;
        } else if (choice == 5) {
            System.out.print("Введите численность сотрудников (с): ");
            int minCount = scanner.nextInt();
            System.out.print("Введите численность сотрудников (по): ");
            int maxCount = scanner.nextInt();
            results = query.findByEmployeeCountRange(minCount, maxCount);
            logMessage = "Выбрать компании по численности сотрудников с " + minCount + " по " + maxCount;
        } else {
            System.out.println("Неверный выбор.");
        }

        logApplicationUsage(logMessage); // Логирование запроса
        System.out.println("Результаты запроса:");
        results.forEach(company -> System.out.println(company.toString(null))); // Печать результатов

        // Запись результатов в XML и JSON файлы
        CompanyFileWriter.writeToXML(results, "output.xml");
        CompanyFileWriter.writeToJson(results, "output.json");
    }

    // Настройка логгера для записи сообщений в файл
    private static void setupLogger() {
        try {
            FileHandler fh = new FileHandler("logfile.txt", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для записи использования приложения в лог
    private static void logApplicationUsage(String message) {
        logger.info(message);
    }

    // Загрузка данных из CSV файла в список объектов Company
    private static List<Company> loadCompaniesFromCSV(String csvFile) {
        List<Company> companies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Пропускаем заголовок
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                companies.add(new Company(fields));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
