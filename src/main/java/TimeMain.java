import java.time.Duration;

public class TimeMain {

    public static void main(String[] args) {
        //int seconds = (int) (2* 365*24*60*60L+ 24*60*60*1.5+ 3600+ 62);
        //int seconds = (int) (132030240); //4 years, 68 days, 3 hours and 4 minutes,
        int seconds = (int) (5838542); //4 years, 68 days, 3 hours and 4 minutes,
        Duration inputSeconds = Duration.ofSeconds(seconds);

        long years1 = inputSeconds.toDays();
        long years = years1/365;
        System.out.println("years: " + years);

        // days
        long days = inputSeconds.toDays();
        System.out.println("days: " + days);
        Duration inputInDays = Duration.ofDays(years1);
        long remainDays = (inputInDays.minus(Duration.ofDays(365*years))).toDays();
        System.out.println("remainDays: "+remainDays);

        //hours
        long hours = inputSeconds.toHours();
        System.out.println("hours: " + hours);
        Duration inputInHours = Duration.ofHours(hours);
        long hours2 = inputInHours.toHours();
        System.out.println("hours2: " + hours2);
        long remainHours = inputInHours.minus(Duration.ofHours(Duration.ofDays(days).toHours())).toHours();
        System.out.println("remainHours: " + remainHours);

        //minutes
        long minutes = inputSeconds.toMinutes();
        System.out.println("minutes: "+minutes);
        Duration inputInMinutes = Duration.ofMinutes(minutes);
        long remainMinutes = inputInMinutes.minus(Duration.ofMinutes(Duration.ofHours(hours).toMinutes())).toMinutes();
        System.out.println("remainMinutes: " + remainMinutes);

        //seconds
        long remainSeconds = (inputSeconds.minus(Duration.ofMinutes(minutes))).toSeconds();
        System.out.println("remainSeconds: "+remainSeconds);
    }
}
