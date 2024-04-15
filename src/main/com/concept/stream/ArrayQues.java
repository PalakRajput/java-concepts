package src.main.com.concept.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayQues {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        System.out.println(addOneToArray(arr));
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

}
