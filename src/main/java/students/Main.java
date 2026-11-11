package students;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static final StudentManager manager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Принудительно устанавливаем кодировку UTF-8 для вывода, чтобы Windows не выдавал кракозябры
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        // Добавляем нескольких студентов для демонстрации
        manager.addStudent("Иван", "Иванов", "Москва", 1);
        manager.addStudent("Петр", "Петров", "Краснодар", 2);
        manager.addStudent("Анна", "Сидорова", "Москва", 1);

        while (true) {
            printMenu();
            int choice = InputHelper.readInt(scanner, "Ваш выбор: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    manager.printStatsByCourse();
                    break;
                case 5:
                    manager.printStatsByCity();
                    break;
                case 6:
                    searchStudent();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ошибка: выберите пункт от 0 до 6.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Меню управления студентами ===");
        System.out.println("1 - Добавить студента");
        System.out.println("2 - Обновить данные студента");
        System.out.println("3 - Удалить студента");
        System.out.println("4 - Статистика по курсам");
        System.out.println("5 - Статистика по городам");
        System.out.println("6 - Поиск по фамилии");
        System.out.println("0 - Выход");
    }

    private static void addStudent() {
        String firstName = InputHelper.readNonEmptyString(scanner, "Введите имя: ");
        String lastName = InputHelper.readNonEmptyString(scanner, "Введите фамилию: ");
        String city = InputHelper.readNonEmptyString(scanner, "Введите город: ");
        int course = InputHelper.readPositiveInt(scanner, "Введите курс: ");
        manager.addStudent(firstName, lastName, city, course);
    }

    private static void updateStudent() {
        int id = InputHelper.readPositiveInt(scanner, "Введите ID студента для обновления: ");
        String firstName = InputHelper.readNonEmptyString(scanner, "Введите новое имя: ");
        String lastName = InputHelper.readNonEmptyString(scanner, "Введите новую фамилию: ");
        String city = InputHelper.readNonEmptyString(scanner, "Введите новый город: ");
        int course = InputHelper.readPositiveInt(scanner, "Введите новый курс: ");
        manager.updateStudent(id, firstName, lastName, city, course);
    }

    private static void deleteStudent() {
        int id = InputHelper.readPositiveInt(scanner, "Введите ID студента для удаления: ");
        manager.deleteStudent(id);
    }

    private static void searchStudent() {
        System.out.println("Введите запрос для поиска (Enter — все, 'Фамилия' — точный, 'Фамилия1,Фамилия2' — диапазон):");
        String query = scanner.nextLine();
        manager.searchByLastName(query);
    }
}