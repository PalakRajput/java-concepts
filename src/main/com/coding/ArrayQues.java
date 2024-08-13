package src.main.com.coding;

import java.util.Arrays;
import java.util.Random;

public class ArrayQues {
    public static void main(String[] args) {
        int[] arr = mergeArrayIntoSingleArray(new int[]{9, 10, 11, 0, 0, 0}, new int[]{2, 5, 6});
        System.out.println(Arrays.toString(arr));
        int[] randomizedArr = getRandomizedArray(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(randomizedArr));
    }

    private static int[] getRandomizedArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int idx = random.nextInt(arr.length);
            int temp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    //Better in terms of time complexity as we are not shifting elements. TC: O(n1+n2)
    public static void merge(int[] nums1, int[] nums2) {
        int i = nums1.length - 1; // Last initialized element of nums1
        int j = nums2.length - 1; // Last element of nums2
        int k = nums1.length + nums2.length - 1; // Last position in nums1

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Copy remaining elements from nums2 if any
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    //TC: O(n1*n2) when all elements of arr2 are smaller than arr1 then we need to shift the elements
    private static int[] mergeArrayIntoSingleArray(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        //n11 holds the element in arr1 and rest space is filled with 0.
        int n11 = n1 - n2;
        int i = 0;
        int j = 0;
        //if i becomes equal to n11 that means i is traversed fully and also since we are shifting element
        while (i < n1 && j < n2 && i != n11) {
            if (arr1[i] <= arr2[j]) {
                i++;
            } else {
                int k = n11;
                while (k > i) {
                    arr1[k] = arr1[k - 1];
                    k--;
                }
                n11++;
                arr1[i++] = arr2[j++];
            }
        }
        while (j < n2) {
            arr1[i++] = arr2[j++];
        }
        return arr1;
    }
}
