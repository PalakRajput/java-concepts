package src.main.com.concept;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateTimeExample {
    public static void main(String[] args) throws ParseException {
        //yyyy-MM-dd
        LocalDate date = LocalDate.now();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        //MM -> 07(for July), MMMM -> July, MMM -> Jul
        //dd -> 09, d-> 9
        //D -> Day of year
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

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2024, Month.JUNE, 2);
        //month starts from 1 instead of 0(java.util.Date)
        LocalDate ld3 = LocalDate.of(2024, 6, 2);

        LocalTime lt1 = LocalTime.now();
        LocalTime lt2 = LocalTime.of(7, 50, 54);

        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt1 = LocalDateTime.of(ld1, lt1);
        LocalDateTime ldt2 = LocalDateTime.of(2024, 6, 4, 10, 50, 45);

        MonthDay md = MonthDay.of(6, 4);
        LocalDate ld4 = md.atYear(2024);

        YearMonth ym = YearMonth.of(2024, 6);
        LocalDate ld5 = ym.atDay(4);

        Year year = Year.of(2024);
        LocalDate ld6 = year.atMonth(6).atDay(4);

        //Zoned date time
//        for(String zoneId: ZoneId.getAvailableZoneIds()){
//            System.out.println(zoneId);
//        }

        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        System.out.println(zoneId.getDisplayName(TextStyle.FULL, Locale.US));

        ZonedDateTime zonedDateTime = ZonedDateTime.of(ldt, zoneId);
        System.out.println(zonedDateTime);

        ZonedDateTime zdt1 = ldt.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
        ZonedDateTime zdt = ldt.atZone(zoneId);
        System.out.println(zdt);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println("Instant: " + clock.instant());

        //Comparing dates
        System.out.println("Is date after: " + ld1.isAfter(ld2));
        System.out.println("Is date before: " + ld1.isBefore(ld2));
        System.out.println("Is date equal: " + ld1.isEqual(ld2));

        //formatting dates
        DateTimeFormatter dtf = DateTimeFormatter.ISO_WEEK_DATE;
        System.out.println(dtf.format(LocalDateTime.now()));
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        System.out.println(dtf1.format(ldt));

        //Period -> represents time in year, months and days
        Period p1 = Period.of(2024, 6, 4);
        System.out.println(p1);
        Period p2 = Period.between(ld2, ld6);
        System.out.println(p2);
        long noOfDaysBwTwoDates = ChronoUnit.DAYS.between(ldt, ldt2);
        System.out.println(noOfDaysBwTwoDates);

        //Durations -> represent the time in seconds or nanoseconds or days,
        // but if you use month here you will get exception as it is a large unit to specify duration.
        Duration d1 = Duration.between(ldt, ldt2);
        System.out.println(d1);
        Duration d2 = Duration.of(1, ChronoUnit.DAYS);
        Duration d3 = Duration.ofDays(4);
        System.out.println(d3);

        //Altering date & time
        //LocalDate, LocalTime, LocalDateTime are immutable.
        LocalDateTime ldt10 = LocalDateTime.now();
        LocalDateTime ldt11 = ldt10.plusHours(3);
        System.out.println(ldt11);

        LocalDateTime ldt12 = ldt10.withMonth(10);
        LocalDateTime ldt13 = ldt10.withHour(10);
        LocalDateTime ldt14 = ldt10.withYear(2025);


    }
}
