package src.main.com.coding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutputBasedQues {
}


class Student {
    int rollNumber;

    Student(int n) {
        rollNumber = n;
    }
}


class HashSetDemo {


    public static void main(String[] args) {


        Set<Student> students = new HashSet<Student>();

        students.add(new Student(1));
        students.add(new Student(3));
        students.add(new Student(4));
        students.add(new Student(1));
        students.add(new Student(3));

        System.out.println(students.size());

        String s1 = "Sachin";
        String s2 = "Sachin";
        String s3 = new String("Sachin");
        String s4 = "Saurav";
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s3));
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s4));
    }
}

class Test {

    public static void calculate(Integer a, Integer b) {
        System.out.println("Result is: " + (a - b));
    }

    public static void calculate(int a, int b) {
        System.out.println("Result is: " + (a + b));
    }

    public static void main(String[] args) {
        calculate(10, 5);
        BiPredicate<String, Integer> biPredicateImpl = (String a, Integer b) -> a.length() == b;
//        System.out.println(biPredicateImpl.test("Palak", 5));
        List<Integer> list = List.of(1, 2, 3, 5, 89, 7, 8, 10, 12, 13);
        System.out.println("With Parallel stream: ");
        System.out.println(list.parallelStream().findFirst().get());
        System.out.println(list.parallelStream().findAny().get());
        System.out.println("With sequential stream: ");
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());


    }
}

interface BiPredicate<T, U> {
    boolean test(T t, U u);
}

interface BiFunction<T, U, R> {
    R accept(T t, U u);
}