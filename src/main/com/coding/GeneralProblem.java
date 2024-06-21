package src.main.com.coding;

import java.util.*;
import java.util.concurrent.*;


class User {
    int id;
    String name;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

class A {
    static int m1() {
        return 1;
    }
}

class B extends A {
    static int m1() {
        return 2;
    }
}

class Solution {
    public static void main(String[] args) {
        A a = new B();
//        B b = new A();
        System.out.println(a.m1());
//        System.out.println(b.m1());
        Employee employee = new Employee("1", "e1", 33, 999999);
        List<Employee> employeeList = List.of(
                employee,
                new Employee("2", "e2", 30, 99876),
                new Employee("3", "e3", 25, 98745),
                new Employee("4", "e4", 24, 98765));
        Employee emp = employeeList.get(0);
        employee = null;
        emp = null;
        System.gc();
        System.out.println(employeeList.get(0));
        System.out.println(List.of("saferreerwe", "rwer", "rwerwerwer", "ere", "weroierwerwrnmrw").stream()
                .sorted(Comparator.comparing(String::length).reversed()).findFirst().get());
    }
}

public class GeneralProblem {
    public static void main(String[] args) {
        User u1 = new User("abc", 1);
        User u2 = new User("abc", 1);
        System.out.println(u1 == u2);
        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());

//        appendZeroesToEnd(new int[]{4, 0, 1, 0, 3, 12, 0});
//        mapDemo();
//        listDemo();
//        findJourney();
//        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
//        System.out.println(decodeString("2[abc]3[cd]ef"));
//        System.out.println(minAddToMakeValid("()))(("));
//        System.out.println(buddyStrings("ab", "ba"));
//        System.out.println(Arrays.toString(canSeePersonsCount(new int[]{10, 6, 8, 5, 11, 9})));
//        System.out.println(getFrontPerson(5, 1));
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(tripletSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(countCollisions("LLRLRLLSLRLLSLSSSS"));
//        System.out.println(timeRequiredToBuy(new int[]{84,49,5,24,70,77,87,8}, 3));
//        System.out.println(Arrays.toString(sortStrings(new String[]{"New York", "New Jersey", "New Town"})));
        System.out.println(getGroupedStringsCount("aaabbaanjkkllaaabbnjbb"));
    }

    private static Map<String, Integer> getGroupedStringsCount(String s) {
        Map<String, Integer> map = new HashMap<>();

        char prev = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (prev == s.charAt(i)) {
                count++;
            } else {
                String key = String.valueOf(prev).repeat(count);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
                prev = s.charAt(i);
                count = 1;
            }
        }

        return map;
    }

    private static String[] sortStrings(String[] arr) {

        sortStringsUsingMergeSort(arr, 0, arr.length - 1);
        return arr;

    }

    private static void sortStringsUsingMergeSort(String[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        sortStringsUsingMergeSort(arr, start, mid);
        sortStringsUsingMergeSort(arr, mid + 1, end);
        mergeTwoSortedArrays(arr, start, mid, end);
    }

    private static void mergeTwoSortedArrays(String[] arr, int start, int mid, int end) {
        String[] temp = new String[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        for (int l = start; l <= end; l++) {
            arr[l] = temp[l - start];
        }
    }

    public static int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        int noOfTickets = tickets[k];
        for (int i = 0; i < noOfTickets; i++) {
            for (int j = 0; j < tickets.length; j++) {
                if (tickets[j] != 0) {
                    time += 1;
                    tickets[j] = tickets[j] - 1;
                    if (j == k && tickets[j] == 0) {
                        break;
                    }
                }
            }
        }

        return time;
    }

    public static int countCollisions(String directions) {
        String[] arr = directions.split("");
        Stack<String> stack = new Stack();
        int count = 0;
        for (String s : arr) {
            if (stack.isEmpty()) {
                stack.push(s);
            } else {
                if (s.equals("L") && stack.peek().equals("R")) {
                    stack.pop();
                    count += 2;
                } else if (s.equals("L") && stack.peek().equals("S") ||
                        s.equals("S") && stack.peek().equals("R")) {
                    stack.pop();
                    stack.push("S");
                    count += 1;
                } else {
                    stack.push(s);
                }

            }
        }

        return count;
    }


    public static List<List<Integer>> tripletSum(int[] nums) {
        Set<List<Integer>> temp = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int j = i + 1;
            while (j < nums.length) {
                int val = nums[i] + nums[j];
                if (map.containsKey(-val)) {
                    temp.add(List.of(nums[i], nums[j], -val));
                } else {
                    map.put(nums[j], j);
                }
                j++;
            }
        }
        return new ArrayList<>(temp);
        //Another approach with sorted array and two pointers
        //int k = nums.length - 1;
        // if(nums[i] + nums[j] + nums[k] == 0){
        // if(!res.contains(List.of(nums[i], nums[j], nums[k]))){
        // res.add(List.of(nums[i], nums[j], nums[k]));
        // }
        // j++;
        // k--;
        // } else if(nums[i] + nums[j] + nums[k] > 0){
        // k--;
        // } else {
        // j++;
        // }
    }

    public static int lengthOfLongestSubstring(String s) {
        String[] arr = s.split("");
        StringBuilder sb = new StringBuilder();
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (sb.indexOf(arr[i]) != -1) {
                while (sb.indexOf(arr[i]) != -1) {
                    sb.deleteCharAt(0);
                }
            }
            sb.append(arr[i]);
            length = Math.max(length, sb.length());
        }
        return length;
    }

    public static int getFrontPerson(int n, int operations) {
        Deque<Integer> queue = new ArrayDeque<>();
        Node head = new Node(1, null);
        Node temp = head;
        for (int i = 2; i <= n; i++) {
            Node newNode = new Node(i, null);
            temp.setNext(newNode);
            temp = newNode;
        }
        for (int i = 0; i < operations; i++) {
            int first = head.getData();
            head = head.getNext();
            temp.setNext(new Node(first, null));
            temp = temp.getNext();
            temp.setNext(new Node(first, null));
            temp = temp.getNext();

        }


//        for(int i = 0; i < operations; i++){
//            int num = queue.poll();
//            queue.add(num);
//            queue.add(num);
//        }
        return head.getData();
    }

    public static int[] canSeePersonsCount(int[] heights) {
        int[] result = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int count = 0;
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[i] >= heights[j]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    public static boolean buddyStrings(String s, String goal) {

        for (int i = 0; i < s.length(); i++) {
            char[] arr = s.toCharArray();
            for (int j = i + 1; j < s.length(); j++) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                if (new String(arr).equals(goal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack();
        int count = 0;
        int open = 0;
        int close = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(' && !stack.isEmpty() && stack.peek() == ')') {
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        open++;
                    } else {
                        close++;
                    }
                    stack.pop();
                }
                count += Math.abs(open - close);
                open = 0;
                close = 0;
                stack.push(ch);
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                open++;
            } else {
                close++;
            }
            stack.pop();
        }
        count += Math.abs(open - close);
        return count;
    }

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (String s1 : s.split("")) {
            if (stack.isEmpty()) {
                stack.push(s1);
            } else {
                if (Objects.equals(s1, "]")) {
                    List<String> list = new ArrayList<>();
                    list.add(1, "a");
                    while (!stack.isEmpty() && !Objects.equals(stack.peek(), "[")) {
                        list.add(stack.pop());
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int j = list.size() - 1; j >= 0; j--) {
                        sb.append(list.get(j));
                    }

                    if (!stack.isEmpty() && Objects.equals(stack.peek(), "[")) {
                        stack.pop();
                        List<String> list1 = new ArrayList<>();
                        while (!stack.isEmpty() && stack.peek().matches("[0-9]")) {
                            list1.add(stack.pop());
                        }
                        StringBuilder sb1 = new StringBuilder();
                        for (int j = list1.size() - 1; j >= 0; j--) {
                            sb1.append(list1.get(j));
                        }
                        int times = Integer.parseInt(sb1.toString());

                        stack.push(sb.toString().repeat(times));
                    }

                } else {
                    stack.push(s1);
                }
            }
        }
        List<String> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int j = list.size() - 1; j >= 0; j--) {
            sb.append(list.get(j));
        }
        return sb.toString();
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        String min = "";
        for (String val : dictionary) {
            int s1 = 0;
            int val1 = 0;
            while (s1 < s.length() && val1 < val.length()) {
                if (val1 == val.length()) {
                    min = min.compareTo(val) <= 0 && !min.isEmpty() ? min : val;
                    break;
                } else if (s.charAt(s1) == val.charAt(val1)) {
                    s1++;
                    val1++;
                } else {
                    s1++;
                }
            }
        }

        return min;
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.matches("-?[0-9]+")) {
                stack.push(Integer.parseInt(s));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(getResult(num1, num2, s));
            }
        }
        return stack.pop();
    }

    private static int getResult(int num1, int num2, String symbol) {

        return switch (symbol) {
            case "*" -> num1 * num2;
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "/" -> num1 / num2;
            default -> 0;
        };
    }

    private static void findJourney() {
        List<String> list = List.of("HYD-Chennai", "Bom-Bangalore", "Chennai-Bom", "Bangalore-Kolkata");
        Map<String, String> map = new HashMap<>();
        for (String s : list) {
            String[] str = s.split("-");
            map.put(str[0], str[1]);
        }
        StringBuilder sb = new StringBuilder();
        String key = "HYD";
        sb.append(key).append(" -> ");
        while (map.get(key) != null) {
            sb.append(map.get(key)).append(" -> ");
            key = map.get(key);
        }
        sb.delete(sb.length() - 4, sb.length());
        System.out.println(sb);
    }

    private static void mapDemo() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("A", "Apple");
        map.put("B", "Banana");
        map.put("C", "Cherry");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("B")) {
                map.put("D", "Durian");
            }
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private static void listDemo() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("item1");
        list.add("item2");
        Iterator<String> iterator = list.iterator();
        list.add("item3"); // this will not cause a ConcurrentModificationException
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }
    }

    private static void appendZeroesToEnd(int[] ints) {
        int j = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                int temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
                j++;
            }
        }
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}
