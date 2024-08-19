package src.main.com.coding;

import java.util.*;

public class ArrayQues {
    public static void main(String[] args) {
        int[] arr = mergeArrayIntoSingleArray(new int[]{9, 10, 11, 0, 0, 0}, new int[]{2, 5, 6});
        System.out.println(Arrays.toString(arr));
        int[] randomizedArr = getRandomizedArray(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(randomizedArr));
        String[] strings1 = new String[]{"a/*/b//*c", "blank", "d//*e/*/f"};
        String[] strings = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println(removeComments(strings1));
        char[] chars = new char[]{'a', 'b', 'c'};
        System.out.println(compress(chars));
        System.out.println("Num pairs divisible by 60 are: " + numPairsDivisibleBy60(new int[]{15,63,451,213,37,209,343,319}));
    }


    //Ques 1010 on LC
    public static int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : time) {
            if (map.containsKey((60 - t % 60))) {
                int val = map.get((60 - t % 60));
                int pVal = val;
                if((60 - t % 60) == t){
                    val++;
                    map.put(t, val);
                    count = count > 0 ? count - pVal : count;
                }
                count += val;
            } else {
                map.put(t, 1);
            }

        }
        return count;
    }

    public static int compress(char[] chars) {
        int j = 0;
        if (chars.length == 1) {
            return 1;
        }
        for(int i = 0; i < chars.length; ){
            char curr = chars[i];
            int count = 0;
            while(i < chars.length && curr == chars[i]){
                count++;
                i++;
            }
            chars[j++] = curr;
            if(count > 1){
                StringBuilder sb = new StringBuilder();
                sb.append(count);
                for(char ch: sb.toString().toCharArray()){
                    chars[j++] = ch;
                }
            }

        }
        System.out.println(Arrays.toString(chars));
        return j;
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

    //https://leetcode.com/problems/remove-comments/
    //this is not yet correct
    public static List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean isBlockOpen = false;
        StringBuilder sb = new StringBuilder();
        for (String line : source) {
            if (line.contains("//") && !isBlockOpen && line.indexOf("/*") > line.indexOf("//")) {
                String val = line.substring(0, line.indexOf("//"));
                if (!val.isEmpty()) {
                    result.add(val);
                }
            } else {
                if (line.contains("/*") && !isBlockOpen) {
                    String val = line.substring(0, line.indexOf("/*"));
                    sb.append(val);
                    isBlockOpen = true;
                    if (line.contains("*/") && line.indexOf("*/") + 1 != line.lastIndexOf("*/")) {
                        String str = line.substring(line.lastIndexOf("*/") + 2);
                        sb.append(str);
                        isBlockOpen = false;
                        if (!sb.isEmpty()) {
                            result.add(sb.toString());
                        }
                        sb = new StringBuilder();
                    }
                } else if (line.contains("*/") && isBlockOpen) {
                    String str = line.substring(line.lastIndexOf("*/") + 2);
                    sb.append(str);
                    isBlockOpen = false;
                    if (!sb.isEmpty()) {
                        result.add(sb.toString());
                    }
                    sb = new StringBuilder();
                } else if (!isBlockOpen) {
                    result.add(line);
                }
            }
        }
        return result;
    }
}
