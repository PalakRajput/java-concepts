### Generics

Helps remove code duplication if we want to perform same operation on different types. Generics doesn't work primitive
types.
Ex: Collection framework, Streams

#### Generic class

```java
class GenericPrinter<T> {
    //T -> Type of thing this GenericPrinter can hold and print

    T value;

    public GenericPrinter(T value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println(value);
    }

}

public class ClientApp {
    public static void main(String[] args) {
        GenericPrinter<Integer> integer = new GenericPrinter<>(100);
        integer.printValue();
        GenericPrinter<String> string = new GenericPrinter<>("100");
        string.printValue();
        GenericPrinter<Double> doubleP = new GenericPrinter<>(100.0);
        doubleP.printValue();
        //Raw type -> name of generic class or interface without any type argument
        //Compile time warning: Raw use of parameterized class 'GenericPrinter' 
        GenericPrinter rawPrinter = new GenericPrinter(10);
        Pair<String, Integer> p1 = new Pair<>("Hello", 5);
        Pair<String, Integer> p2 = new Pair<>("Java", 4);
        boolean result = GenericUtil.compare(p1, p2);
        //Type after the class name can be omitted as shown in above line.
        boolean result1 = Util.<Integer, String>compare(p1, p2);

    }
}

class GenericUtil {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}

class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
```

#### Bounded types

```java
import java.io.Serializable;

class Animal {
    public void eat() {
        System.out.println("eating...");
    }
}

class Cat extends Animal {

}

class Dog extends Animal {

}

class GenericPrinter<T extends Animal & Serializable & Cloneable> {
    T t; //Can be of type Animal and hence access to the methods of Animal class.

    public void print() {
        t.eat();
    }
}
```

#### Generic methods

```java
class GenericExample {
    public static void main(String[] args) {
        System.out.println(shout("Hey", 10));
        System.out.println(shout("Hey", new Animal()));
    }

    private static <T, S> T shout(T val1, S val2) {
        System.out.println(val1 + " " + val2 + " !!!!");
        return val1;
    }
}
```

#### Wildcard(?)

Represents unknown type.

```java
import java.util.List;

class GenericExample {
    public static void main(String[] args) {
        System.out.println(printList(List.of(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(printList(List.of(new Cat())));
    }

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    //Only subclass of Animal can be passed as list
    private static void printList1(List<? extends Animal> list) {
        System.out.println(list);
    }

    //Only superclass of Animals can be passed as list
    private static void printList2(List<? super Animal> list) {
        System.out.println(list);
    }
}
```