package src.main.com.coding;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamQues {
    public static void main(String[] args) {
        get15AuthorsByUniqueUppercaseSurname();
        getFirstNonRepeatingChar();
        sumOfSquareOfEvenNumbers();
        reverseAStringWithSpecialChars();
        sortEmployeesByNameAgeSal();
        mapEmployeeBySal();
        rotateArray();
//        Map<String, Integer> result = Stream.of("a:332", "b:42", "c:", "d", "f:2345", "i:-34")
//                .collect(Collectors.toMap());

        Map<Integer, Employee> map = new HashMap<>();
        map.put(1, new Employee("Jane", "Doe", 21, 100));
        map.put(2, new Employee("James", "Doe", 31, 100));
        map.put(3, new Employee("Jess", "Doe", 41, 100));
        map.put(4, new Employee("Jack", "Doe", 51, 100));
        map.put(5, new Employee("Jake", "Doe", 52, 100));
        map.put(6, new Employee("Jones", "Doe", 53, 100));
        map.put(7, new Employee("Jenn", "Doe", 50, 100));

//        map.entrySet().stream().filter(entry -> entry.getValue().getAge() > 50).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).forEach((k, v) -> System.out.println(k + " - " + v));
        Map<Employee, String> treeMap = new TreeMap<>();
        treeMap.put(new Employee("Jane", "Doe", 21, 100), "Jane");
        treeMap.put(new Employee("James", "Doe", 23, 1000), "James");
        treeMap.put(new Employee("Jenna", "Doe", 22, 10000), "Jenna");
        for (Map.Entry<Employee, String> entry : treeMap.entrySet()) { //ClassCastException because Employee is not Comparable
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    private static void rotateArray() {
        //One way to rotate is to reverse first idx elements, then reverse idx to length elements and then reverse whole array.
        int[] arr = {5, 3, 6, 1, 7};
        int idx = 2;
        int[] res = new int[arr.length];
        int j = 0;
        for (int i = idx; i < arr.length; i++) {
            res[j] = arr[i];
            j++;
        }
        for (int i = 0; i < idx; i++) {
            res[j] = arr[i];
            j++;
        }
        System.out.println(Arrays.toString(res));
    }

    private static void mapEmployeeBySal() {
        List<Employee> employees = List.of(new Employee("James", "Doe", 20, 2100.0),
                new Employee("Jane", "Loe", 22, 2323.23), new Employee("Amar", "Doe", 22, 9876.23),
                new Employee("Kyle", "Koe", 24, 9323.23),
                new Employee("Mina", "Joe", 29, 2823.23),
                new Employee("Rina", "Joe", 19, 2823.23),
                new Employee("Amit", "Patel", 45, 7783.23));

        employees.stream().filter(e -> e.getSal() > 2000).collect(Collectors.groupingBy(e -> e.getSal())).forEach((k, v) -> System.out.println(k + " " + v));
    }


    private static void sumOfSquareOfEvenNumbers() {
        //Sum of square of even numbers from the given list.
        Integer sum = List.of(1, 2, 3, 4, 5, 6, 8, 10).stream().filter(e -> e % 2 == 0).map(e -> e * e).reduce(0, (num1, num2) -> num1 + num2);
        System.out.println("Sum: " + sum);
    }

    private static void getFirstNonRepeatingChar() {
        Optional<Map.Entry<String, Long>> entry = Arrays.stream("AAnish".split("")).collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(e -> e.getValue() == 1).findFirst();
        if (entry.isPresent()) {
            System.out.println(entry.get().getKey());
        }
    }

    private static void get15AuthorsByUniqueUppercaseSurname() {
        List<Author> list = List.of(new Author("James", "Doe"), new Author("Jane", "Doe"),
                new Author("James", "Does"), new Author("James", "Doe1"),
                new Author("James", "Doe5"), new Author("James", "Doe2"),
                new Author("James", "Doe5"), new Author("James", "Doe3"),
                new Author("James", "Doe5"), new Author("James", "Doe4"),
                new Author("James", "Doe7"), new Author("James", "Doe6"),
                new Author("James", "Doe8"), new Author("James", "Doe6"),
                new Author("James", "Doe9"));
        list.stream().collect(Collectors.toMap(
                        author -> author.getSurname().toUpperCase(),
                        author -> author,
                        (author1, author2) -> author1))
                .values()
                .stream()
                .limit(15)
                .collect(Collectors.toList()).forEach(a -> System.out.println(a.getName() + " " + a.getSurname()));
    }

    private static void sortEmployeesByNameAgeSal() {
        List<Employee> employees = List.of(new Employee("James", "Doe", 20, 2000.0),
                new Employee("Jane", "Loe", 22, 2323.23), new Employee("Amar", "Doe", 22, 9876.23),
                new Employee("Kyle", "Koe", 24, 9323.23),
                new Employee("Mina", "Joe", 29, 823.23),
                new Employee("Amit", "Patel", 45, 7783.23));
        employees.stream().sorted(Comparator.comparing(Employee::getFirstname)
                .thenComparing(Employee::getLastname).thenComparing(Employee::getAge)
                .thenComparing(Employee::getSal)).forEach(e -> System.out.println(e));
    }

    //Reverse a string with special chars
    //abc.xyz-pqr -> rqp.zyx-cba
    //Input: str = "Bc,d,ef!$"
    //Output: str = "fe,d,cB!$"
    private static void reverseAStringWithSpecialChars() {
        String s = "Bc,d,ef!$";
        String[] str = s.split("");
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            if (str[i].matches("[A-Za-z0-9]") && str[j].matches("[A-Za-z0-9]")) {
                String temp = str[j];
                str[j] = str[i];
                str[i] = temp;
                i++;
                j--;
            } else if (!str[i].matches("[A-Za-z0-9]")) {
                i++;
            } else {
                j--;
            }

        }
        System.out.println(String.join("", str));
    }
}

class Employee {
    String firstname;
    String lastname;
    int age;
    double sal;

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", sal=" + sal +
                '}';
    }

    public Employee(String firstname, String lastname, int age, double sal) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.sal = sal;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
}

class Author {
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}