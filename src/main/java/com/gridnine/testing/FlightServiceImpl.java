package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService{
    @Override
    public List<Flight> getFlightsWithDepartureInFuture(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                .toList();
    }

    @Override
    public List<Flight> getFlightsWithCorrectSegments(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();
    }

    @Override
    public List<Flight> getFlightsWithGroundTimeLessTwoHours(List<Flight> flights) {
        List<Flight> resultFlights = new ArrayList<>();
        for (Flight f:flights) {
            if(calculateGroundTime(f) <= 120) resultFlights.add(f);
        }
        return resultFlights;
    }

    @Override
    public String printFlights(List<Flight> flights) {
        return flights.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

}
