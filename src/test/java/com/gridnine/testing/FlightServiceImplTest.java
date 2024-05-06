package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FlightServiceImplTest {
    @Test
    void getFlightsWithDepartureInFuture() {
        //Подготовка входных данных
        FlightService flightService = new FlightServiceImpl();
        List<Flight> flights = new ArrayList<>(FlightGenerator.getFlights());

        //Подготовка ожидаемого результата
        flights.remove(2);
        List<Flight> expectedList = flights;


        //Начало теста
        List<Flight> actualList = flightService.getFlightsWithDepartureInFuture(FlightGenerator.getFlights());
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void getFlightsWithCorrectSegments() {
        //Подготовка входных данных
        FlightService flightService = new FlightServiceImpl();
        List<Flight> flights = new ArrayList<>(FlightGenerator.getFlights());

        //Подготовка ожидаемого результата
        flights.remove(3);
        List<Flight> expectedList = flights;

        //Начало теста
        List<Flight> actualList = flightService.getFlightsWithCorrectSegments(FlightGenerator.getFlights());
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void getFlightsWithGroundTimeLessTwoHours() {
        //Подготовка входных данных
        FlightService flightService = new FlightServiceImpl();
        List<Flight> flights = new ArrayList<>(FlightGenerator.getFlights());

        //Подготовка ожидаемого результата
        flights.remove(5);
        flights.remove(4);
        List<Flight> expectedList = flights;

        //Начало теста
        List<Flight> actualList = flightService.getFlightsWithGroundTimeLessTwoHours(FlightGenerator.getFlights());
        assertEquals(expectedList.toString(), actualList.toString());
    }
    @Test
    void getFlightsWithGroundTimeLessTwoHours_WithImpossibleFlightException() {
        //Подготовка входных данных
        FlightService flightService = new FlightServiceImpl();
        List<Flight> flights = new ArrayList<>(FlightGenerator.getFlights());
        flights.add(FlightGenerator.getFlightX());

        //Подготовка ожидаемого результата
        String expectedMessage = "Impossible flight: Flight " + FlightGenerator.getFlightX() + " has crossed segments (next departure is before current arrival):\n" +
                "segment: " + FlightGenerator.getFlightX().getSegments().get(0) + " crossed with " +
                "segment: " + FlightGenerator.getFlightX().getSegments().get(1);

        //Начало теста
        Exception exception = assertThrows(
                ImpossibleFlightException.class,
                () -> flightService.getFlightsWithGroundTimeLessTwoHours(flights)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}