import java.util.Arrays;
import java.util.Comparator;

class StudentComparatorByName implements Comparator<Student> {

    public int compare(Student first, Student second) {
        return first.getName().compareTo(second.getName());
    }

}

class StudentComparatorByAge implements Comparator<Student> {

    public int compare(Student first, Student second) {
        return Integer.compare(
            first.getAge(),
            second.getAge()
        );
    }

}

class Student implements Comparable<Student> {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

    public int compareTo(Student otherStudent) {
        return name.compareTo(otherStudent.getName());
    }

}

public class Sort {

    public static void main(String[] args) {
        sortNumbers();
        sortStrings();
        sortStudents();
    }

    private static void sortNumbers() {
        final int N = 10;

        Integer[] numbers = generateRandomNumbers(N);
        System.out.println("До:");
        printObjects(numbers);

        Arrays.sort(numbers);

        System.out.println("После:");
        printObjects(numbers);
    }


    private static void sortStrings() {
        String[] words = {
            "hello", "apple", "zero", "adminstrator", "pineapple"
        };

        System.out.println("До:");
        printObjects(words);

        Arrays.sort(words);

        System.out.println("После:");
        printObjects(words);
    }

    private static void sortStudents() {
        Student[] students = {
            new Student("Bob",   45),
            new Student("Alice", 23),
            new Student("Eve",   30),
        };

        System.out.println("До:");
        printObjects(students);

        Arrays.sort(students); // Comparable
        System.out.println("После:");
        printObjects(students);

        Arrays.sort(students, new StudentComparatorByName()); // Comparator
        System.out.println("После:");
        printObjects(students);

        Arrays.sort(students, new StudentComparatorByAge());  // Comparator
        System.out.println("После:");
        printObjects(students);

        Arrays.sort(students, (first, second) -> first.getName().compareTo(second.getName())); // Lambda
        System.out.println("После:");
        printObjects(students);
    }

    private static Integer[] generateRandomNumbers(int n) {
        Integer[] numbers = new Integer[n];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = (int) (Math.random() * n);
        }

        return numbers;
    }

    private static void printObjects(Object[] objects) {
        for (int i = 0; i < objects.length; ++i) {
            System.out.print(objects[i].toString() + " ");
        }
        System.out.println();
    }

}
