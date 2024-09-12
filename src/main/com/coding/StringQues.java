package src.main.com.coding;

public class StringQues {
    public static int countConsistentStrings(String allowed, String[] words) {
        int consistentString = 0;
        for (String word : words) {
            if (word.replaceAll("[" + allowed + "]*", "").isEmpty()) {
                consistentString++;
            }
        }


        return consistentString;
    }

    public static int countConsistentStrings2(String allowed, String[] words) {
        int consistentString = 0;
        char[] arr = new char[26];
        for (char ch : allowed.toCharArray()) {
            arr[ch - 'a'] = 1;
        }
        for (String word : words) {
            // if (word.replaceAll("[" + allowed + "]*", "").isEmpty()) {
            //     consistentString++;
            // }
            boolean isInconsistent = false;
            for (char ch : word.toCharArray()) {
                if (arr[ch - 'a'] != 1) {
                    isInconsistent = true;
                    break;
                }
            }
            if (!isInconsistent) {
                consistentString++;
            }

        }
        return consistentString;
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(11);
        for (int i = 2; i < n; i++) {
            sb = countFreqAndGetNextInput(sb);
            System.out.println("i = " + i + " sb = " + sb.toString());
        }
        return sb.toString();

    }

    //LC 38
    private static StringBuilder countFreqAndGetNextInput(StringBuilder sb) {
        StringBuilder sb1 = new StringBuilder();
        char prev = sb.charAt(0);
        int count = 1;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == prev) {
                count++;
            } else {
                sb1.append(count).append(prev);
                prev = sb.charAt(i);
                count = 1;
            }
        }
        sb1.append(count).append(prev);
        return sb1;
    }

    public static void main(String[] args) {
        int consistentString = countConsistentStrings("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"});
        System.out.println("Consistent strings count = " + consistentString);

        int consistentString2 = countConsistentStrings2("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"});
        System.out.println("Consistent strings count = " + consistentString2);

        String str = countAndSay(7);
        System.out.println("Output of count and say: " + str);

    }
}
