package src.main.com.concept;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeExample {
    public static void main(String[] args) throws ParseException {
        //yyyy-MM-dd
        LocalDate date = LocalDate.now();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        //MM -> 07(for July), MMMM -> July, MMM -> Jul
        //dd -> 09, d-> 9
        //yyyy -> 2024
        //H -> hour in the day (0-23), k -> hour in the day(1-24), h -> (1-12) am/pm, K -> (0-11) am/pm
        //m -> minute
        //s -> second
        //S -> millisecond

        //SimpleDateFormat is not thread safe
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        System.out.println(formatter1.format(new Date())); //returns string
        System.out.println(formatter.parse("2015-09-12")); //returns Date

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.parse("2024-05-10", dateTimeFormatter)); //string to date
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        System.out.println(LocalDateTime.now().format(dateTimeFormatter1)); //Date to string
    }
}
