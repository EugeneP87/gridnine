import com.gridnine.testing.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightFilterTests {

    @Test
    void testDepartureBeforeCurrentTimeFilter() {
        FlightFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = departureBeforeCurrentTimeFilter.filterFlights(flights);

        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                assertTrue(segment.getDepartureDate().isAfter(LocalDateTime.now()));
            }
        }
    }

    @Test
    void testArrivalBeforeDepartureFilter() {
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = arrivalBeforeDepartureFilter.filterFlights(flights);

        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                assertTrue(segment.getArrivalDate().isAfter(segment.getDepartureDate()));
            }
        }
    }

    @Test
    void testMoreThanTwoHoursGroundTimeFilter() {
        FlightFilter moreThanTwoHoursGroundTimeFilter = new MoreThanTwoHoursGroundTimeFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = moreThanTwoHoursGroundTimeFilter.filterFlights(flights);

        for (Flight flight : filteredFlights) {
            assertTrue(calculateGroundTime(flight) > 2);
        }
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