package src.main.com.concept.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringQues {
    public static void main(String[] args) {
        reverseString();
        isomorphicStrings();
    }

    public static void reverseString() {
        String s = "hello";
        String[] arr = s.split("");
        for (int i = 0; i < s.length() / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[s.length() - 1 - i];
            arr[s.length() - 1 - i] = temp;
        }
        System.out.println("Reversed value: " + Arrays.stream(arr).collect(Collectors.joining("")));
    }

    /**
     * Given two strings s and t, determine if they are isomorphic.
     * <p>
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     * <p>
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character, but a character may map to itself.
     *
     *
     * Example 1:
     *
     * Input: s = "egg", t = "add"
     * Output: true
     * Example 2:
     *
     * Input: s = "foo", t = "bar"
     * Output: false
     * Example 3:
     *
     * Input: s = "paper", t = "title"
     * Output: true
     */
    public static void isomorphicStrings() {
        //Isomorphic: paper -> title, add -> egg
        //Not isomorphic: badc -> baba, foo -> bar
        String s = "foo";
        String t = "bar";
        if (s.length() != t.length()) {
            System.out.println("Strings are not isomorphic");
        } else {
            String result = "Yes";
            Map<Character, Character> isomorphicMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (!isomorphicMap.containsKey(s.charAt(i)) && !isomorphicMap.containsValue(t.charAt(i))) {
                    isomorphicMap.put(s.charAt(i), t.charAt(i));
                } else if (!isomorphicMap.containsKey(s.charAt(i)) && isomorphicMap.containsValue(t.charAt(i))) {
                    result = "No";
                    break;
                } else if (isomorphicMap.containsKey(s.charAt(i)) && isomorphicMap.get(s.charAt(i)) != t.charAt(i)) {
                    result = "No";
                    break;
                }
            }
            System.out.println("Are Strings isomorphic? " + result);
        }
    }
}
