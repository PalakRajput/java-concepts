package src.main.com.coding;

import java.util.Arrays;

public class Recursion {
    public static void main(String[] args) {
        System.out.println(isStringPalindrome("madam", 0));
        int[] arr = {1, 2, 3, 4, 5};
        reverseArray(arr, 0);
        System.out.println(Arrays.toString(arr));
        
    }

    private static void reverseArray(int[] arr, int idx) {
        if (idx >= arr.length / 2) {
            return;
        }
        int temp = arr[idx];
        arr[idx] = arr[arr.length - 1 - idx];
        arr[arr.length - 1 - idx] = temp;
        reverseArray(arr, idx + 1);
    }

    private static boolean isStringPalindrome(String str, int idx) {
        if (idx >= str.length() / 2) {
            return true;
        } else if (str.charAt(idx) != str.charAt(str.length() - 1 - idx)) {
            return false;
        }
        return isStringPalindrome(str, idx + 1);
    }
}
