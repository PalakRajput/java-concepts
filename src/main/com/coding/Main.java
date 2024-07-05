package src.main.com.coding;

import java.util.*;
import java.util.stream.Collectors;

class Country {
    private List<String> cities;

    //With this only if some one tries to modify the list of cities present in country class then we will get error.
    //To make it immutable use new ArrayList<>()
    public Country(List<String> cities) {
        this.cities = new ArrayList<>(cities);
    }

    public List<String> getCities() {
        return new ArrayList<>(cities);
    }

    public void func(){
        try {
            try {
                if (true) {
                    throw new NumberFormatException();
                }
            } catch (RuntimeException e) {
                System.out.println("1");
                throw new ClassCastException();
            } catch (Exception e) {
                System.out.println("2");
                throw new ClassCastException();
            } finally {
                System.out.println("3");
            }

        } catch (RuntimeException e) {
            System.out.println("4");
            throw new ClassCastException();
        } catch (Exception e) {
            System.out.println("5");
            throw new ClassCastException();
        } finally {
            System.out.println("6");
        }
    }
//        try{
//            return 1;
//        }
//
//        catch(Exception e)
//        {
//            return 2;
//        }
//        finally
//        {
//            return 3;
//        }
//        return 4; //compile error, statement not reachable
    }


public class Main {
    public static void main(String[] args) {
/*//        Write a program to categorize numbers from an array based on their tens place using java 8 streams ?

        Map<Integer, List<Integer>> map = List.of(10, 4, 56, 23, 25, 11).stream()
                .collect(Collectors.groupingBy(e -> e/10 * 10, Collectors.toList()));
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            System.out.printf(entry.getKey() + "-" + entry.getValue());

        }*/

        List<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("Shimla");
        cities.add("Indore");
        cities.add("Bhopal");

        Country country = new Country(cities);
        System.out.println(country.getCities());
        cities.add("Jabalpur");
        System.out.println(country.getCities());
        country.func();

        System.out.println("**************************************************************");

        int[] arr = {10, 4, 56, 23, 25, 11};
        Arrays.stream(arr).mapToObj(e -> e).collect(Collectors.groupingBy(e -> e / 10 * 10, Collectors.toList())).forEach((key, value) -> System.out.println(key + "-" + value));

        System.out.println(calculate());


        Node node4 = new Node(50, null);
        Node node3 = new Node(40, node4);
        Node node2 = new Node(30, node3);
        Node node1 = new Node(20, node2);
//        Node node2 = new Node(30, node3);
        Node head = new Node(10, node1);
//        System.out.println(middleOfLinkedList(head));
        String s = "weqweqeweq142342fdfgd43rgeuopiporwe";
        s.contains(String.valueOf('d'));

    }

    private static int calculate() {
        try {
            int a = 5 / 0;
            return 10;
        } catch (Exception e) {
            return 20;
        } finally {
            System.out.println("In finally");
            return 30;
        }
    }

    public static int middleOfLinkedList(Node head) {
        if (head == null) {
            return -1;
        }
        Node pointer1 = head;
        Node pointer2 = head;
        while (pointer2.getNext() != null) {
            pointer1 = pointer1.getNext();
            pointer2 = pointer2.getNext().getNext();
        }
        return pointer1.getData();
    }

}

class Node {
     int data;
     Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
