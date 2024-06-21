package src.main.com.concept.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayQues {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 2, 1};
//        System.out.println(addOneToArray(arr));
        printLeaders(new int[]{16, 17, 4, 3, 5, 2});
        System.out.println(Arrays.toString(moveZeroToLeft(new int[]{1, 0, 1, 0, 1, 1, 0, 0})));
    }

    private static int[] moveZeroToLeft(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            //for one to left just change the condition to arr[i] != 0
            if(arr[i] == 0){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        return arr;
    }

    private static List<Integer> addOneToArray(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int carry = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int sum = carry + arr[i];
            if (String.valueOf(sum).length() > 1) {
                while (sum > 0) {
                    carry = sum % 10;
                    sum /= 10;
                }
            } else {
                carry = 0;
            }
            list.add(sum);
        }
        if (carry > 0) {
            list.add(carry);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * Write a program to print all the LEADERS in the array.
     * An element is a leader if it is greater than all the elements to its right side.
     * And the rightmost element is always a leader.
     * For example:
     * Input: arr[] = {16, 17, 4, 3, 5, 2},
     * Output: 17, 5, 2
     * Input: arr[] = {1, 2, 3, 4, 5, 2},
     * Output: 5, 2
     *
     * @param arr input arr
     */
    private static void printLeaders(int[] arr) {
        int length = arr.length;
        int prev = Integer.MIN_VALUE;
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i] > prev) {
                prev = arr[i];
                System.out.println(arr[i]);
            }
        }
    }
}
