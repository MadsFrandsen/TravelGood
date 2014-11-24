package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * P2 (cancel planning) Plan a trip by first getting a list of flights and then
 * adding a flight to the itinerary. Then cancel planning.
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class P2 extends T {

 
    /**
     *
     */
    
    public P2() throws DatatypeConfigurationException  {
       

    }

    /**
     *
     */
    @Test
    public void testP2() {
        itinearyID = createItinerary(person[0].name);
       
//        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        int bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
        bookItinerary(itinearyID, person[0].number, person[0].name);
        cancelItinerary(itinearyID);
        assertEquals("canceled", getItinerary(itinearyID).getStatus());
    }

    
}