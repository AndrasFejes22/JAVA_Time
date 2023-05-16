import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void formatDuration() {
        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("2 seconds", TimeFormatter.formatDuration(2));
        //assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("1 minute", TimeFormatter.formatDuration(60));
    }

    @Test
    void timeAdjusterSecondsTest() {
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 1, 1,2));
        assertEquals("5 years, 1 hour, 1 minute and 2 seconds", TimeFormatter.timeAdjuster(5, 0, 1, 1,2));
        assertEquals("5 years, 1 day, 1 hour, 1 minute and 2 seconds", TimeFormatter.timeAdjuster(5, 1, 1, 1,2));
        //assertEquals("1 minute and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 1, 2));
        //assertEquals("1 minute and 1 second", TimeFormatter.timeAdjuster(0, 0, 0, 1,1));
        //assertEquals("1 minute", TimeFormatter.timeAdjuster(0, 0, 0, 1,0));
        //assertEquals("2 minute", TimeFormatter.timeAdjuster(0, 0, 0, 2,0));
        //assertEquals("2 seconds", TimeFormatter.timeAdjuster(0, 0, 0, 0,2));
        //assertEquals("1 second", TimeFormatter.timeAdjuster(0, 0, 0, 0,1));
        //assertEquals("1 hour and 1 second", TimeFormatter.timeAdjuster(0, 0, 1, 0, 1));
    }

    @Test
    void timeAdjusterMinutesTest() {
        assertEquals("1 hour and 1 minute", TimeFormatter.timeAdjuster(0, 0, 1, 1,0));
        assertEquals("1 hour and 2 minutes", TimeFormatter.timeAdjuster(0, 0, 1, 2,0));
        assertEquals("1 day and 2 hours", TimeFormatter.timeAdjuster(0, 1, 2, 0,0));
        assertEquals("1 day and 2 minutes", TimeFormatter.timeAdjuster(0, 1, 0, 2,0));
        assertEquals("1 year and 2 days", TimeFormatter.timeAdjuster(1, 2, 0, 0,0));
        assertEquals("1 hour, 5 minutes and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 1, 5,2));
        assertEquals("11 hours, 1 minute and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 11, 1,2));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 1, 1,2)); //nem jo
        assertEquals("1 hour, 5 minutes and 2 seconds", TimeFormatter.timeAdjuster(0, 0, 1, 5,2)); // jo
        assertEquals("1 hour, 1 minute and 1 second", TimeFormatter.timeAdjuster(0, 0, 1, 1,1));
        assertEquals("2 hours, 1 minute and 1 second", TimeFormatter.timeAdjuster(0, 0, 2, 1,1));
    }

    @Test
    void timeAdjusterHoursTest() {
        assertEquals("1 year, 2 hours, 8 minutes and 1 second", TimeFormatter.timeAdjuster(1, 0, 2, 8,1));
        assertEquals("2 years, 2 hours, 1 minute and 1 second", TimeFormatter.timeAdjuster(2, 0, 2, 1,1));
        assertEquals("1 year", TimeFormatter.timeAdjuster(1, 0, 0, 0,0));
        assertEquals("2 years", TimeFormatter.timeAdjuster(2, 0, 0, 0,0));

    }

    @Test
    public void exampleTests() {
        /*
        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("1 hour", TimeFormatter.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        */
        //assertEquals("4 years, 68 days, 3 hours and 4 minutes", TimeFormatter.formatDuration(132030240)); //4 years, 68 days, 3 hours and 4 minutes,
        assertEquals("67 days, 13 hours, 49 minutes and 2 seconds", TimeFormatter.formatDuration(5838542)); //4 years, 68 days, 3 hours and 4 minutes,
        assertEquals("2 minutes", TimeFormatter.formatDuration(120)); //4 years, 68 days, 3 hours and 4 minutes,
        assertEquals("4 years, 68 days, 3 hours and 4 minutes", TimeFormatter.formatDuration(132030240)); //4 years, 68 days, 3 hours and 4 minutes,
    }

    /*
    @Test
    void lengthTest() {
        assertEquals(5, TimeFormatter.length(1,1,1,1, 1));
        assertEquals(3, TimeFormatter.length(1,1,0,1,0));
        assertEquals(0, TimeFormatter.length(0,0,0,0,0));
    }
    */

    @Test
    void positionTest() {
        List<Long> members = new LinkedList<>();
        members.add(1L);
        members.add(1L);
        members.add(1L);
        assertEquals(members, TimeFormatter.position(1,1,1, 0, 0));
    }
}