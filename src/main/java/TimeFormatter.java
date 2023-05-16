import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeFormatter {

    // "a year is 365 days and a day is 24 hours."

    public static String formatDuration(int seconds) {
        System.out.println("seconds: " + seconds);
        if (seconds == 0) {
            return "now";
        }

        Duration inputSeconds = Duration.ofSeconds(seconds);
        long years1 = inputSeconds.toDays();
        long years = years1 / 365;
        System.out.println("years: " + years);

        // days
        long days = inputSeconds.toDays();
        System.out.println("days: " + days);
        Duration inputInDays = Duration.ofDays(years1);
        long remainDays = (inputInDays.minus(Duration.ofDays(365 * years))).toDays();
        System.out.println("remainDays: " + remainDays);

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
        System.out.println("minutes: " + minutes);
        Duration inputInMinutes = Duration.ofMinutes(minutes);
        long remainMinutes = inputInMinutes.minus(Duration.ofMinutes(Duration.ofHours(hours).toMinutes())).toMinutes();
        System.out.println("remainMinutes: " + remainMinutes);

        //seconds
        long remainSeconds = (inputSeconds.minus(Duration.ofMinutes(minutes))).toSeconds();
        System.out.println("remainSeconds: " + remainSeconds);


        return timeAdjuster(years, remainDays, remainHours, remainMinutes, remainSeconds);

    }

    // "1 hour, 1 minute and 2 seconds"
    // but "1 minute and 2 seconds"

    public static String timeAdjuster(long years, long days, long hours, long minutes, long seconds) {
        List<String> position = position(years, days, hours, minutes, seconds);
        StringBuilder sb = new StringBuilder();
        //int length = length(years, days, hours, minutes, seconds);
        // years egyedi vagy van vagy nincs, egyes, többes
        if (years != 0) {
            if (position.size() == 1 && years == 1) {
                sb.append(years).append(" year");
            }
            if (position.size() == 1 && years != 1) {
                sb.append(years).append(" years");
            }
            if (position.size() == 2 && years == 1) {
                sb.append(years).append(" year and ");
            }
            if (position.size() == 2 && years != 1) {
                sb.append(years).append(" years and ");
            }
            if (position.size() > 2 && years == 1) {
                sb.append(years).append(" year, ");
            }
            if (position.size() > 2 && years != 1) {
                sb.append(years).append(" years, ");
            }
        }
        // days egyedi, csak az 1 vagy a 2. helyen állhat
        if (days != 0) { // EZ és a position.size()== 1 jelenti azt hogy csak egy tag van és az az adott tag (itt a days)

            if (position.size() == 1 && days == 1) {
                sb.append(days).append(" day");
            }
            if (position.size() == 1 && days != 1) {
                sb.append(days).append(" days");
            }
            if (position.indexOf("days") == position.size() - 1 - 1 && days == 1) {
                sb.append(days).append(" day and ");
            }
            if (position.indexOf("days") == position.size() - 1 - 1 && days != 1) {
                sb.append(days).append(" days and ");
            }
            // Comma
            if (position.indexOf("days") != position.size() - 1 - 1 && days == 1 && position.size() > 2) {
                sb.append(days).append(" day, ");
            }
            if (position.indexOf("days") != position.size() - 1 - 1 && days != 1 && position.size() > 2) {
                sb.append(days).append(" days, ");
            }

            if (position.indexOf("days") == position.size() - 1 && days == 1 && position.size() == 2) {
                sb.append(days).append(" day");
            }
            if (position.indexOf("days") == position.size() - 1 && days != 1 && position.size() == 2) {
                sb.append(days).append(" days");
            }


        }
        // hour--> comma, and, and plural, TÖBBFÉLE
        if (hours != 0) {
            if (position.size() == 1 && hours == 1) {
                sb.append(hours).append(" hour");
            }
            if (position.size() == 1 && hours != 1) {
                sb.append(hours).append(" hours");
            }
            if (position.indexOf("hours") == position.size() - 1 - 1 && hours == 1) {
                sb.append(hours).append(" hour and ");
            }
            if (position.indexOf("hours") == position.size() - 1 - 1 && hours != 1) {
                sb.append(hours).append(" hours and ");
            }
            // Comma
            if (position.indexOf("hours") != position.size() - 1 - 1 && hours == 1 && position.size() > 2) {
                sb.append(hours).append(" hour, ");
            }
            if (position.indexOf("hours") != position.size() - 1 - 1 && hours != 1 && position.size() > 2) {
                sb.append(hours).append(" hours, ");
            }

            if (position.indexOf("hours") == position.size() - 1 && hours == 1 && position.size() == 2) {
                sb.append(hours).append(" hour");
            }
            if (position.indexOf("hours") == position.size() - 1 && hours != 1 && position.size() == 2) {
                sb.append(hours).append(" hours");
            }


        }
        // minutes--> comma, and, and plural, TÖBBFÉLE
        if (minutes != 0) { // "1 hour, 1 minute and 2 seconds"  de // "1 hour and 1 minute"
            if (position.contains("seconds")) {
                if (minutes != 1) {
                    sb.append(minutes).append(" minutes and ");
                }
                if (minutes == 1) {
                    sb.append(minutes).append(" minute and ");
                }
            } else {
                if (minutes != 1) {
                    sb.append(minutes).append(" minutes");
                }
                if (minutes == 1) {
                    sb.append(minutes).append(" minute");
                }
            }


        }
        // second egyedi
        if (seconds != 0) { // 0 --> not write
            if (seconds == 1) {
                sb.append(seconds).append(" second");
            }
            if (seconds != 1) {
                sb.append(seconds).append(" seconds");
            }

        }

        return sb.toString();
    }


    public static List<String> position(long a, long b, long c, long d, long e) {
        List<String> members = new LinkedList<>();
        // position(years, days, hours, minutes, seconds);
        if (a != 0) {
            members.add("years");
        }
        if (b != 0) {
            members.add("days");
        }
        if (c != 0) {
            members.add("hours");
        }
        if (d != 0) {
            members.add("minutes");
        }
        if (e != 0) {
            members.add("seconds");
        }
        return members;
    }

}
