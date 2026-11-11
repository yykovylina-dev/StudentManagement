package students;

import java.util.Objects;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private int course;

    public Student(int id, String firstName, String lastName, String city, int course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.course = course;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCity() { return city; }
    public int getCourse() { return course; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setCity(String city) { this.city = city; }
    public void setCourse(int course) { this.course = course; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s | Город: %s | Курс: %d",
                id, firstName, lastName, city, course);
    }
}