package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MoreThanTwoHoursGroundTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream().filter(flight -> calculateGroundTime(flight) > 2).collect(Collectors.toList());
    }

    private long calculateGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long groundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            groundTime += Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate()).toHours();
        }
        return groundTime;
    }

}