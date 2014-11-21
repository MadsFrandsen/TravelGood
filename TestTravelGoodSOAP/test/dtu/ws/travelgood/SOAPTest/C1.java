package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import lameduck.FlightOption;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.FlightBooking;

/**
 * C1 (cancel booking) Create an itinerary with three bookings (mixed flights
 * and hotels) and book it. Get the itinerary and make sure that the booking
 * status is confirmed for each entry. Cancel the trip and check that now the
 * booking status is cancelled for all bookings of the itinerary.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class C1 extends T {

    /**
     *
     */
    public C1() throws DatatypeConfigurationException {
    }

    /**
     *
     */
    @Test
    public void testC1() {
        itinearyID = createItinerary(itinearyID);
        int bookingNumber = getFlights(itinearyID, destinations[0], dates[0], destinations[1]).getReturn().get(0).getBookingNumber(); // .getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookingNumber = getFlights(itinearyID, destinations[1], dates[1], destinations[0]).getReturn().get(0).getBookingNumber(); // .getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        addHotel(itinearyID, getHotels(itinearyID, dates[0], dates[1], destinations[0]).getStays().get(0).getBookingNumber());


        itineraryTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();

        for (int i = 0; i < travels.length; i++) {
            assertEquals("confirmed", itineraryTravels[i].getStatus());
        }
        assertEquals("confirmed", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());

        cancelItinerary(itinearyID);

        itineraryTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();

        for (int i = 0; i < travels.length; i++) {
            assertEquals("cancelled", itineraryTravels[i].getStatus());
        }
        assertEquals("cancelled", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());
        
        

    }
}