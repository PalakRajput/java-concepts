package src.main.com.concept.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodingQues {
    public static void main(String[] args) {
        convertIntListToSting();
        countOccurrence();
    }

    public static void convertIntListToSting() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        integers.stream().map(Object::toString).forEach(System.out::println);
    }

    public static void countOccurrence() {
        int i = 234567631;
        String s = String.valueOf(i);
        Map<String, Long> occurrenceMap = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<String, Long> entry : occurrenceMap.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }


}
