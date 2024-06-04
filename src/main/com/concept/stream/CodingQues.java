package src.main.com.concept.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodingQues {
    public static void main(String[] args) {
//        sortBySecondChar();
//        convertIntListToSting();
//        countOccurrence();
        Person person = new Person("Peter");
        person.setName("Peter1");
        updatePerson(person);
        System.out.println(person.getName());
        Person p = new PersonA("Java", 12);
        p.printName();
        //p.printAge(); -> Compile time error
    }

    private static void updatePerson(Person p) {
        p.setName("Peter2");
        p = null;

    }

    public static void convertIntListToSting() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        integers.stream().map(Object::toString).forEach(System.out::println);
    }

    public static void countOccurrence() {
        int i = 234567631;
        String s = String.valueOf(i);
        Map<String, Long> occurrenceMap = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<String, Long> entry : occurrenceMap.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }

    public static void sortBySecondChar() {
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Jane");
        list.add("Jessica");
        list.add("Jeet");
        list.add("Jacob");


        list.stream().sorted(Comparator.comparing(n -> n.charAt(1))).forEach(System.out::println);

    }
}

class Person {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println("Parent");
    }
}

class PersonA extends Person {
    private int age;

    public PersonA(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public void printName() {
        System.out.println("Child");
    }

    public void printAge() {
        System.out.println(age);
    }
}