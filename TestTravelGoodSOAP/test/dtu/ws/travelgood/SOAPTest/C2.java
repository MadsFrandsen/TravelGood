package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travelgood.xml.FlightBooking;

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
        int bookingNumber;

        itinearyID = createItinerary(person[0].name);

        //Search and add a flight
//        bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);

        //Search and add a flight that cannot be chancled
//        bookingNumber = getFlights(itinearyID, nonCancelable[0].source, nonCancelable[0].departure, nonCancelable[0].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, nonCancelable[0].source, nonCancelable[0].departure, nonCancelable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);

        //Search and add a flight
//        bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0).getBookingNumber();
        bookingNumber = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getReturn().get(0).getBookingNumber();
        addFlight(itinearyID, bookingNumber);
    
        //Book itinarary
        bookItinerary(itinearyID, person[2].number, person[2].name);
        
        //Check taht status is conformied for each entry
        itinearyTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();
        for (int i = 0; i < itinearyTravels.length; i++) {
            assertEquals("confirmed", itinearyTravels[i].getStatus());
        }
        assertEquals("confirmed", getItinerary(itinearyID).getHotelbookings().get(0).getStatus());

        //Chancel itinarary
        cancelItinerary(itinearyID);

        //Check status of bookings
        itinearyTravels = (FlightBooking[]) getItinerary(itinearyID).getFlightBookings().toArray();

            assertEquals("cancelled", itinearyTravels[0].getStatus());
            assertEquals("confirmed", itinearyTravels[1].getStatus());
            assertEquals("cancelled", itinearyTravels[2].getStatus());
      
    }

    @Test
    public void testC2() {
    }
}