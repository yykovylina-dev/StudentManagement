package students;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public void addStudent(String firstName, String lastName, String city, int course) {
        Student student = new Student(nextId++, firstName, lastName, city, course);
        students.add(student);
        System.out.println("Студент успешно добавлен:\n" + student);
    }

    public void updateStudent(int id, String firstName, String lastName, String city, int course) {
        Student student = findById(id);
        if (student != null) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setCity(city);
            student.setCourse(course);
            System.out.println("Данные студента обновлены:\n" + student);
        } else {
            System.out.println("Ошибка: студент с ID " + id + " не найден.");
        }
    }

    public void deleteStudent(int id) {
        if (students.removeIf(s -> s.getId() == id)) {
            System.out.println("Студент с ID " + id + " удален.");
        } else {
            System.out.println("Ошибка: студент с ID " + id + " не найден.");
        }
    }

    private Student findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void printStatsByCourse() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }
        Map<Integer, Long> stats = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, TreeMap::new, Collectors.counting()));

        System.out.println("Статистика по курсам:");
        stats.forEach((course, count) -> System.out.println("  " + course + " курс: " + count + " чел."));
    }

    public void printStatsByCity() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }
        Map<String, Long> stats = students.stream()
                .collect(Collectors.groupingBy(Student::getCity, TreeMap::new, Collectors.counting()));

        System.out.println("Статистика по городам:");
        stats.forEach((city, count) -> System.out.println("  " + city + " - " + count));
    }

    public void searchByLastName(String query) {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        if (query.trim().isEmpty()) {
            System.out.println("Все студенты:");
            students.forEach(System.out::println);
            return;
        }

        if (!query.contains(",")) {
            List<Student> found = students.stream()
                    .filter(s -> s.getLastName().equalsIgnoreCase(query.trim()))
                    .collect(Collectors.toList());

            if (found.isEmpty()) {
                System.out.println("Студенты с фамилией '" + query.trim() + "' не найдены.");
            } else {
                System.out.println("Найденные студенты:");
                found.forEach(System.out::println);
            }
            return;
        }

        String[] parts = query.split(",");
        if (parts.length == 2) {
            String start = parts[0].trim();
            String end = parts[1].trim();

            List<Student> found = students.stream()
                    .filter(s -> {
                        String lastName = s.getLastName();
                        return lastName.compareToIgnoreCase(start) >= 0
                                && lastName.compareToIgnoreCase(end) <= 0;
                    })
                    .sorted(Comparator.comparing(Student::getLastName))
                    .collect(Collectors.toList());

            if (found.isEmpty()) {
                System.out.println("Студенты в диапазоне от '" + start + "' до '" + end + "' не найдены.");
            } else {
                System.out.println("Студенты в диапазоне от '" + start + "' до '" + end + "':");
                found.forEach(System.out::println);
            }
            return;
        }

        System.out.println("Ошибка: неверный формат поиска. Введите одну фамилию или две через запятую.");
    }
}