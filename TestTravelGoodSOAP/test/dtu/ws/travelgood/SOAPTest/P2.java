package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;
import ws.travelgood.xml.TravelOptions;

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
        itinearyID = createItinerary(personNames[0]);
        int bookingNumber = getFlights(itinearyID, destinations[0], dates[0], destinations[1]).getTravels().get(0).getBookingNumber();
        bookItinerary(itinearyID, creditCardNumbers[0], personNames[1]);
        cancelItinerary(itinearyID);
        assertEquals("canceled", getItinerary(itinearyID).getStatus());
    }

    
}