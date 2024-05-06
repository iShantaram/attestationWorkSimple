package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightService fs = new FlightServiceImpl();

        System.out.println(fs.printFlights(flights));

        System.out.println("\nFlights with departure in future:");
        System.out.println(fs.printFlights(fs.getFlightsWithDepartureInFuture(flights)));

        System.out.println("\nFlights with correct segments:");
        System.out.println(fs.printFlights(fs.getFlightsWithCorrectSegments(flights)));

        System.out.println("\nFlights with ground time less than two hours:");
        System.out.println(fs.printFlights(fs.getFlightsWithGroundTimeLessTwoHours(flights)));
    }
}