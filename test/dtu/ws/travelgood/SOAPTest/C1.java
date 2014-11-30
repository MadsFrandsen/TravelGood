package dtu.ws.travelgood.SOAPTest;

import itinerary.ws.Booking;
import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;

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
        //Create itinerary
        String itinearyID = createItinerary(person[2].name);
        //get a booking number and book a flight 
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //get another  booking number and book a flight
        bookingNumber = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        //add a hotel
        addHotel(itinearyID, getHotels(itinearyID, hotels.get(0).getFromDate(), hotels.get(0).getToDate(), hotels.get(0).getCity()).getStays().get(0).getBookingNumber());

        bookItinerary(itinearyID, creditCardInfo[2]);
        
        for (Booking booking : getItinerary(itinearyID).getBookings())
            assertEquals("confirmed", booking.getStatus());
        
        boolean canceled = cancelItinerary(itinearyID);
        
        assertEquals(true, canceled);
        
        for (Booking booking : getItinerary(itinearyID).getBookings())
            assertEquals("canceled", booking.getStatus());
    }
}