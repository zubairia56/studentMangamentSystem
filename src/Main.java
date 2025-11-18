import java.util.ArrayList;
import java.util.Scanner;

// ---------------------- PERSON CLASS (INHERITANCE) ----------------------
class Person {
    protected String name;
    protected int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// ---------------------- STUDENT CLASS (ENCAPSULATION + POLYMORPHISM) ----------------------
class Student extends Person {
    private int studentId;
    private String course;
    private double marks;

    public Student(int studentId, String name, int age, String course, double marks) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
        this.marks = marks;
    }

    // Encapsulation: getters + setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    // Method Overriding
    @Override
    public void display() {
        System.out.println("ID: " + studentId + ", Name: " + name +
                ", Age: " + age + ", Course: " + course + ", Marks: " + marks);
    }

    // Method Overloading
    public void display(boolean detailed) {
        if (detailed) {
            System.out.println("----- Detailed Student Information -----");
            display();
        } else {
            System.out.println(name + " (" + studentId + ")");
        }
    }
}

// ---------------------- TEACHER CLASS (INHERITANCE) ----------------------
class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void display() {
        System.out.println("Teacher: " + name + ", Age: " + age + ", Subject: " + subject);
    }
}

// ---------------------- ABSTRACT DATABASE OPERATIONS (ABSTRACTION) ----------------------
interface DatabaseOperations {
    void addStudent(Student student);
    void updateStudent(int studentId);
    void deleteStudent(int studentId);
    void viewStudents();
}

// ---------------------- STUDENT DATABASE IMPLEMENTATION ----------------------
class StudentDatabase implements DatabaseOperations {
    private ArrayList<Student> students = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    @Override
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    @Override
    public void updateStudent(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                System.out.print("Enter new course: ");
                s.setCourse(sc.next());
                System.out.print("Enter new marks: ");
                s.setMarks(sc.nextDouble());
                System.out.println("Record updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    @Override
    public void deleteStudent(int studentId) {
        boolean removed = students.removeIf(s -> s.getStudentId() == studentId);
        if (removed)
            System.out.println("Record deleted successfully!");
        else
            System.out.println("Student not found!");
    }

    @Override
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in database.");
            return;
        }
        System.out.println("\n------ Student Records ------");
        for (Student s : students) {
            s.display();
        }
    }
}

// ---------------------- MAIN PROGRAM (MENU SYSTEM) ----------------------
public class Main {
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Database Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Course: ");
                    String course = sc.next();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    Student s = new Student(id, name, age, course, marks);
                    db.addStudent(s);
                    break;

                case 2:
                    db.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    db.updateStudent(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    db.deleteStudent(sc.nextInt());
                    break;

                case 5:
                    System.out.println("Exiting program... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
