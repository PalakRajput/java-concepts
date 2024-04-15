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

    public static void isomorphicStrings() {
        String s = "foo";
        String t = "egg";
        if (s.length() != t.length()) {
            System.out.println("Strings are not isomorphic");
        } else {
            String result = "Yes";
            Map<Character, Character> isomorphicMap = new HashMap<>();
            for(int i=0; i<s.length(); i++){
                if(!isomorphicMap.containsKey(s.charAt(i))){
                    isomorphicMap.put(s.charAt(i), t.charAt(i));
                } else {
                    if (isomorphicMap.get(s.charAt(i)) != t.charAt(i)){
                        result = "No";
                        break;
                    }
                }
            }
            System.out.println("Are Strings isomorphic? " + result);
        }
    }
}
