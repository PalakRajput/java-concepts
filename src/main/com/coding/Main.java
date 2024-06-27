package src.main.com.coding;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
/*//        Write a program to categorize numbers from an array based on their tens place using java 8 streams ?

        Map<Integer, List<Integer>> map = List.of(10, 4, 56, 23, 25, 11).stream()
                .collect(Collectors.groupingBy(e -> e/10 * 10, Collectors.toList()));
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            System.out.printf(entry.getKey() + "-" + entry.getValue());

        }*/

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
