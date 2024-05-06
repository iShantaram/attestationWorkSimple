package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FlightGenerator {
    public static LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
    public static final Segment s1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
    public static final Segment s2 = new Segment(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(4));
    public static final Segment s3 = new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(7));
    // Segment in past
    public static final Segment s4 = new Segment(threeDaysFromNow.minusDays(4).plusHours(2), threeDaysFromNow.minusDays(3));
    public static final Segment s5 = new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.minusHours(4));
    public static final Segment s6 = new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6));
    public static final Segment s7 = new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4));
    public static final Segment s8 = new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7));
    // Incorrect segment
    public static final Segment sX = new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.plusHours(4));

    // 0 - A normal flight with two hour duration
    public static Flight getFlight1() { return new Flight(List.of(s1)); }

    // 1 - A normal multi segment flight
    public static Flight getFlight2() { return new Flight(Arrays.asList(s2, s3)); }

    // 2 - A flight departing in the past
    public static Flight getFlight3() { return new Flight(List.of(s4)); }

    // 3 - A flight that departs before it arrives
    public static Flight getFlight4() { return new Flight(List.of(s5)); }

    // 4 - A flight with more than two hours ground time
    public static Flight getFlight5() { return new Flight(Arrays.asList(s1, s6)); }

    // 5 - Another flight with more than two hours ground time
    public static Flight getFlight6() { return new Flight(Arrays.asList(s1, s7, s8)); }

    // X - A flight with crossed two segments
    public static Flight getFlightX() { return new Flight(Arrays.asList(s1, sX)); }

    public static List<Flight> getFlights() {
        return Arrays.asList(getFlight1(), getFlight2(), getFlight3(), getFlight4(), getFlight5(), getFlight6());
    }
}
