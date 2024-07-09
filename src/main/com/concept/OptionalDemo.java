package src.main.com.concept;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> o = Optional.empty();
        System.out.println(o.isPresent()); //false

        String name = "Palak";
        Optional<String> n = Optional.of(name); //if name is null then NPE is thrown
        Optional<String> nullable = Optional.ofNullable(null);

        n.ifPresent(na -> System.out.println(na));
        System.out.println(nullable.orElse("Default"));

        System.out.println(nullable.orElseGet(() -> "Default from supplier function"));

        Optional<Person> optionalPerson = Optional.of(new Person("A", "B", 33, Gender.FEMALE));
//         map is used to transform to another value, it doesn't modify the original value.
//         optionalPerson.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().build());
        String middleName = optionalPerson
                .filter(person -> person.getGender() == Gender.FEMALE)
                .map(Person::getMiddleName)
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .orElseGet(() -> "Middle name not found");

        System.out.println("Middle name: " + middleName);
    }
}

class Person {
    String name;
    String middleName;
    int age;
    Gender gender;

    public Person(String name, String middleName, int age, Gender gender) {
        this.name = name;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}

enum Gender {MALE, FEMALE, OTHER}

class PersonService {
    public static void main(String[] args) {
        List<Person> people = List.of(new Person("Palak", null, 26, Gender.FEMALE),
                new Person("A", "b", 27, Gender.MALE),
                new Person("B", "c", 28, Gender.MALE),
                new Person("Palak", "d", 26, Gender.FEMALE),
                new Person("Palak", "e", 26, Gender.FEMALE),
                new Person("Palak", "f", 26, Gender.FEMALE),
                new Person("Palak", null, 26, Gender.MALE),
                new Person("Palak", "g", 26, Gender.FEMALE),
                new Person("Palak", "h", 26, Gender.FEMALE)
        );


        List<String> names = people.stream().filter(p -> p.getGender() == Gender.FEMALE)
                .map(p -> p.getMiddleName())
                .filter(n -> n != null)
                .map(n -> n.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(names);

        List<Person> personList = null;

        List<String> names1 = Optional.ofNullable(personList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .map(p -> p.getMiddleName())
                .filter(n -> n != null)
                .map(n -> n.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(names1);
    }
}
