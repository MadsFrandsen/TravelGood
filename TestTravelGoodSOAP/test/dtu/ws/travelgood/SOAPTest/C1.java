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
        String itineraryID = createItinerary(person[2].name);
        //get a booking number and book a flight 
        int bookingNumber = getFlights(itineraryID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itineraryID, bookingNumber);
        //get another  booking number and book a flight
        bookingNumber = getFlights(itineraryID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0).getBookingNumber();
        addFlight(itineraryID, bookingNumber);
        //add a hotel
        addHotel(itineraryID, getHotels(itineraryID, hotels.get(0).getFromDate(), hotels.get(0).getToDate(), hotels.get(0).getCity()).getStays().get(0).getBookingNumber());

        bookItinerary(itineraryID, creditCardInfo[2]);
        
        for (Booking booking : getItinerary(itineraryID).getBookings())
            assertEquals("confirmed", booking.getStatus());
        
        boolean canceled = cancelBookedItinerary(itineraryID);
        
        assertEquals(true, canceled);
        
        for (Booking booking : getItinerary(itineraryID).getBookings())
            assertEquals("canceled", booking.getStatus());
    }
}