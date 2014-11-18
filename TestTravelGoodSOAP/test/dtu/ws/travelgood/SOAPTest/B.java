package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.Travel;

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
        itinearyID = createItinerary(itinearyID);
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookingNumber = getFlights(itinearyID, unbookable[0].source, unbookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookingNumber = getFlights(itinearyID, bookable[1].source, bookable[1].departure, bookable[1].destination).getTravels().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        
        travels = (Travel[]) getItinerary(itinearyID).getFlightIbookings().toArray();

        for (int i = 0; i < travels.length; i++) {
            assertEquals("unconfirmed", travels[i].getStatus());

        }
        
        //Asser that booking fail
        assertEquals(false, bookItinerary(itinearyID, creditCardNumbers[0], personNames[0]));
        itineary = getItinerary(itinearyID);
        
        assertEquals(itineary.getFlightIbookings().get(0).getStatus(),"cancelled");
        assertEquals(itineary.getFlightIbookings().get(1).getStatus(),"unconfirmed");
        assertEquals(itineary.getFlightIbookings().get(2).getStatus(),"unconfirmed");
        
        
        

    }
}