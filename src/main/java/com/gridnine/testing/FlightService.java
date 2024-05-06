package com.gridnine.testing;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

public interface FlightService {

    /**
     * Return the filtered list of flights.
     * The result list contains only flights that start not before now.
     *
     * @return the filtered list of flights.
     */
    List<Flight> getFlightsWithDepartureInFuture(List<Flight> flights);

    /**
     * Return the filtered list of flights.
     * The result list contains only flights that have correct segments. It means that every segment has departure date before arrival date.
     *
     * @return the filtered list of flights.
     */
    List<Flight> getFlightsWithCorrectSegments(List<Flight> flights);

    /**
     * Return the filtered list of flights.
     * The result list contains only flights that have total ground time into flight (sum of time between adjacent segments) less than two hours.
     *
     * @return the filtered list of flights.
     */
    List<Flight> getFlightsWithGroundTimeLessTwoHours(List<Flight> flights);

    /**
     * Return a string which contains all flights.
     * Every flight starts with new line.
     *
     * @return a string representation of the list of flights.
     */
    String printFlights(List<Flight> flights);

    /**
     * Returns the number of minutes for ground time into flight.
     * Calculate time in minutes between each adjacent segments in flight and sums it up.
     *
     * @return the number of minutes for ground time in this flight.
     * @throws ImpossibleFlightException if flight has pair adjacent segments with negative
     * time between arrival time of previous segment and departure time of next segment.
     */
    default long calculateGroundTime(Flight flight) {
        long flightGroundTime = 0;
        List<Segment> sortedSegments = flight.getSegments().stream().sorted(Comparator.comparing(Segment::getDepartureDate)).toList();
        for (int i = 1; i < sortedSegments.size(); i++) {
            long groundTime = Duration.between(sortedSegments.get(i-1).getArrivalDate(), sortedSegments.get(i).getDepartureDate()).toMinutes();
            if (groundTime < 0) throw new ImpossibleFlightException(
                    "Impossible flight: Flight " + flight + " has crossed segments (next departure is before current arrival):\n" +
                    "segment: " + sortedSegments.get(i-1) + " crossed with segment: " + sortedSegments.get(i));
            flightGroundTime += groundTime;
        }
        return flightGroundTime;
    }
}
