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
        for(char ch: allowed.toCharArray()){
            arr[ch - 'a'] = 1;
        }
        for (String word : words) {
            // if (word.replaceAll("[" + allowed + "]*", "").isEmpty()) {
            //     consistentString++;
            // }
            boolean isInconsistent = false;
            for(char ch: word.toCharArray()){
                if(arr[ch - 'a'] != 1){
                    isInconsistent = true;
                    break;
                }
            }
            if(!isInconsistent){
                consistentString++;
            }

        }
        return consistentString;
    }

    public static void main(String[] args) {
        int consistentString = countConsistentStrings("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"});
        System.out.println("Consistent strings count = " + consistentString);

        int consistentString2 = countConsistentStrings2("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"});
        System.out.println("Consistent strings count = " + consistentString2);
    }
}
