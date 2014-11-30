package dtu.ws.travelgood.SOAPTest;

import itinerary.ws.Booking;
import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * B (booking fails) Plan an itinerary with three bookings (mixed flights and
 * hotels). Get the itinerary and make sure that the booking status is
 * unconfirmed for each entry. Then book the itinerary. During booking, the
 * second booking should fail. Get the itinerary and check that the result of
 * the book Trip operation records a failure and that the returned itinerary has
 * cancelled as the booking status of the first booking and unconfirmed for the
 * status of the second and third booking.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class B extends T {

    /**
     *
     */
    public B() throws DatatypeConfigurationException {
    }

    /**
     *
     */
    @Test
    public void testB() {
                //Create itinerary
        itinearyID = createItinerary(person[8].name); // this person has a CC limit on 10.000
        //Get a flight and book it.
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookingNumber = getFlights(itinearyID, unbookable[0].source, unbookable[0].departure, unbookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookingNumber = getFlights(itinearyID, bookable[1].source, bookable[1].departure, bookable[1].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        
        for (Booking booking : getItinerary(itinearyID).getBookings())
            assertEquals("unconfirmed", booking.getStatus());
        
        //Assert that booking fail
        assertEquals(false, bookItinerary(itinearyID, creditCardInfo[8]));
        itineary = getItinerary(itinearyID);
        
        assertEquals("canceled", itineary.getBookings().get(0).getStatus());
        assertEquals("unconfirmed", itineary.getBookings().get(1).getStatus());
        assertEquals("unconfirmed", itineary.getBookings().get(2).getStatus());
        
        
        

    }
}