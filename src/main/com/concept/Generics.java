package src.main.com.concept;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        List<?> list = integers;
//        Both lines will give error, when using unbounded wildcards (?) the list becomes immutable as compiler won't know the type of elements
//        list.add("Name");
//        list.add(1);
        System.out.println(list);


//        Below line gives error because list of string is not subtype of list of objects
//        List<Object> objects = new ArrayList<String>();

        List<? extends Object> list2 =
                new ArrayList<String>(); // OK!
        //but below still gives error
//        list2.add("String");

        //With generics we can use methods of object class only until specified the type of value

//        bounded wildcards:
//        ? extends T (Upper-bounded wildcard)
//        ? super T (Lower-bounded wildcard)

        List<Integer> listInteger = new ArrayList<>();
        List<Float> listFloat = new ArrayList<>();
        List<Number> listNumber = new ArrayList<>();
        listNumber.add(Integer.valueOf(10)); // OK
        listNumber.add(Float.valueOf(20.4f)); // OK
//        listNumber = listInteger; // Error
//        listNumber = listFloat; // Error
        //below line will throw classcastexception
        Integer val = (Integer) listNumber.get(1);
        System.out.println(val);

        List<? extends Number> listExtendsNum = new ArrayList<>();
// This would cause an error
// listExtendsNum.add(Integer.valueOf(10));
        listExtendsNum = listInteger; // OK
        listExtendsNum = listFloat; // OK



        List<? super Integer> list3 = new ArrayList<>();

//        It means that list can be assigned to an Integer list (List<Integer>)
//        or some supertype of Integer (like List<Number> or List<Object>).
//        This time, since you know that the list would be typed to at least an Integer,
//        it's safe for the compiler to allow modifications to the list:

        List<? super Integer> list4 = new ArrayList<>();
        list4.add(1); // OK!
        list4.add(2); // OK!



    }
}

//<T extends String> means that any class that extends
//(or implements when working with an interface) String (or String itself)
//can be used as the type parameter. As T is replaced by String, it's safe to use its methods:
class Printer<T extends String> {
    public void print(T t) {
        System.out.println(t.toUpperCase());// Error if we don't use extends keyword
        // What if T doesn't represent a String?
    }
}

class Question_6_2 <T extends Number> {
    T t;
    public static void main(String[] args) {
        Question_6_2 q =
                new Question_6_2<Integer>(); // 1
        // if it was Question_6_2<Integer> q = new Question_6_2<>();, then we will get error for assigning float
        q.t = Float.valueOf(12.7f); // 2
        System.out.println(q.t);


        List<? super Number> list = new ArrayList<>(); // 1
        list.add(Integer.valueOf(2)); // 2
//        list.add(new Object()); // 3
    }
}

interface AnInterface {
    default int aMethod() { return 0; }
    int anotherMethod();
}
class Question_9_5 implements AnInterface {
    public static void main(String[] args) {
//        AnInterface a = () -> aMethod(); //Compile time error, accessing non static method from static method
//        System.out.println(a.anotherMethod());
    }
    @Override
    public int anotherMethod() {
        return 1;
    }
}

interface X {
    int test(int i);
}
class Question_9_8 {
     int i = 0;
    public static void main(String[] args) {
        X x = i -> i * 2; //lambda can refer to sta
        System.out.println(x.test(3));
    }
}