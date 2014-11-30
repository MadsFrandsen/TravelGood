package dtu.ws.travelgood.SOAPTest;

import itinerary.ws.Booking;
import itinerary.ws.Itinerary;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * C2 (cancelling fails) Create an itinerary with three bookings and book it.
 * Make sure that the booking status is confirmed for each entry. During
 * cancelling of the trip, the cancellation of the second booking should fail.
 * Check that the cancelling resulted in an error condition (e.g. value of
 * status variable, exception, HTTP status code). Get the itinerary and check
 * that the returned itinerary has cancelled as the first and third booking and
 * confirmed for the second booking.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class C2 extends T {

    /**
     *
     */
    public C2() throws DatatypeConfigurationException {
    }

    /**
     *
     */
    @Test
    public void testC2() {
        int bookingNumber;

        itinearyID = createItinerary(person[0].name);

        //Search and add a flight
        bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);

        //Search and add a flight that cannot be chancled
        bookingNumber = getFlights(itinearyID, nonCancelable[0].source, nonCancelable[0].departure, nonCancelable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);

        //Search and add a flight
        bookingNumber = getFlights(itinearyID, bookable[1].source, bookable[1].departure, bookable[1].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
    
        //Book itinarary
        bookItinerary(itinearyID, creditCardInfo[2]);
        
        //Check that status is confirmed for each entry
        List<Booking> itineraryBooked = getItinerary(itinearyID).getBookings();
        assertEquals("confirmed", itineraryBooked.get(0).getStatus());
        assertEquals("confirmed", itineraryBooked.get(1).getStatus());
        assertEquals("confirmed", itineraryBooked.get(2).getStatus());

        //Chancel itinarary
        cancelBookedItinerary(itinearyID);
        
        //Check status of bookings
        List<Booking> itineraryCanceled = getItinerary(itinearyID).getBookings();
        assertEquals("canceled", itineraryCanceled.get(0).getStatus());
        assertEquals("confirmed", itineraryCanceled.get(1).getStatus());
        assertEquals("canceled", itineraryCanceled.get(2).getStatus());
    }
}