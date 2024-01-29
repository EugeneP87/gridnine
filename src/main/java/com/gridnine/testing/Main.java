package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter moreThanTwoHoursGroundTimeFilter = new MoreThanTwoHoursGroundTimeFilter();

        List<Flight> departureBeforeCurrentTimeList = departureBeforeCurrentTimeFilter.filterFlights(flights);
        List<Flight> arrivalBeforeDepartureList = arrivalBeforeDepartureFilter.filterFlights(flights);
        List<Flight> moreThanTwoHoursGroundTimeList = moreThanTwoHoursGroundTimeFilter.filterFlights(flights);

        System.out.println("Flights filtered by departure before current time:");
        departureBeforeCurrentTimeList.forEach(System.out::println);

        System.out.println("\nFlights filtered by arrival before departure:");
        arrivalBeforeDepartureList.forEach(System.out::println);

        System.out.println("\nFlights filtered by more than two hours ground time:");
        moreThanTwoHoursGroundTimeList.forEach(System.out::println);
    }

}