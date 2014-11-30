package dtu.ws.travelgood.SOAPTest;

import flightlist.travelgood.ws.Travel;
import hotellist.travelgood.ws.Stay;
import itinerary.ws.Booking;
import javax.xml.datatype.DatatypeConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * P1 (planning and booking) Plan a trip by first planning a flight (i.e.
 * getting a list of flights and then adding a flight to the itinerary), then by
 * planning a hotel, another flight, a third flight, and finally a hotel. Ask
 * for the itinerary and check that it is correct using JUnit’s assert
 * statements – i.e. assertEquals, assertTrue, . . . – in particular, that the
 * booking status for each item is unconfirmed. Book the itinerary and ask again
 * for the itinerary. Check that each booking status is now confirmed
 *
 * @author Jonas Karlsson (S143341)
 * @return
 */
public class P1 extends T {

    
    
    public P1() throws DatatypeConfigurationException {
        travels = new Travel[3];
        itinearyTravels = new Travel[3];

        stays = new Stay[2];
        itinearyStays = new Stay[2];
    }

    /**
     *
     */
    @Test
    public void testP1()  {

        itinearyID = createItinerary(person[0].name);

        //Plan first flight
        travels[0] = getFlights(itinearyID, bookable[0].source, bookable[0].departure, bookable[0].destination).getTravels().get(0);
        addFlight(itinearyID, travels[0].getBookingNumber());

//        /* TEMPORARY TESTING CODE */
//        
//        String id2 = createItinerary(person[1].name);
//        
//        travels[2] = getFlights(id2, bookable[3].source, bookable[3].departure, bookable[3].destination).getTravels().get(0);
//        addFlight(id2, travels[2].getBookingNumber());
//        
//        travels[1] = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0);
//        addFlight(itinearyID, travels[1].getBookingNumber());
//        
//        Itinerary it = getItinerary(itinearyID);
//        Itinerary it2 = getItinerary(id2);
//        
//        /* END OF TEMPORARY TESTING CODE */
        
        //Plan a hotol
        stays[0] = getHotels(itinearyID, hotels.get(0).getFromDate(), hotels.get(0).getToDate(), hotels.get(0).getCity()).getStays().get(0);
        addHotel(itinearyID, stays[0].getBookingNumber());

        //Plan another flight
        travels[1] = getFlights(itinearyID,bookable[1].source, bookable[1].departure, bookable[1].destination).getTravels().get(0);
        addFlight(itinearyID, travels[1].getBookingNumber());

        //Plan a third flight
        travels[2] = getFlights(itinearyID, bookable[2].source, bookable[2].departure, bookable[2].destination).getTravels().get(0);
        addFlight(itinearyID, travels[2].getBookingNumber());


        //Plan a 2nd hotel
        stays[1] = getHotels(itinearyID, hotels.get(1).getFromDate(), hotels.get(1).getToDate(), hotels.get(1).getCity()).getStays().get(0);
        addHotel(itinearyID, stays[1].getBookingNumber());


        //Assertations

        //Booking unconfirmed;
        for (Booking booking : getItinerary(itinearyID).getBookings())
            assertEquals("unconfirmed", booking.getStatus());
//        assertEquals("unconfirmed", getItinerary(itinearyID).getStatus());

        //Do booking 
        bookItinerary(itinearyID, creditCardInfo[0]);
//        bookItinerary(itinearyID, person[0].number, person[0].name);
        
        // bookings should now be "confirmed"
        itinerary.ws.Itinerary it = getItinerary(itinearyID);
        for (Booking booking : getItinerary(itinearyID).getBookings())
            assertEquals("confirmed", booking.getStatus());
//        assertEquals("confirmed", itinerary.getStatus());

        // compare travel information
//        itinearyTravels = (Travel[]) itinerary.getFlightIbookings().toArray();
//        itinearyStays = (Stay[]) itinerary.getHotelbookings().toArray();

//        Arrays.sort(stays);
//        Arrays.sort(itinearyStays);
//        Arrays.sort(travels);
//        Arrays.sort(itinearyTravels);
//        
//        assertEquals(itinearyTravels.length,travels.length);
        
//        for (int i = 0; i < itinearyTravels.length; i++) {
//            assertEquals(itinearyTravels[i].getBookingNumber(),travels[i].getBookingNumber());
////            assertEquals(itinearyTravels[i].getFlight(),travels[i].getFlight());
//            assertEquals(itinearyTravels[i].getReservationService(),travels[i].getReservationService());
//            assertEquals(itinearyTravels[i].getStatus(),"confirmed");
//            
//        }
//        
//        assertEquals(itinearyStays.length,stays.length);
//        
//        for (int i = 0; i < itinearyStays.length; i++) {
//            assertEquals(itinearyStays[i].getAddress(),stays[i].getAddress());
//            assertEquals(itinearyStays[i].getBookingNumber(),stays[i].getBookingNumber());
//            assertEquals(itinearyStays[i].getName(),stays[i].getName());
//            assertEquals(itinearyStays[i].getReservationServiceName(),stays[i].getReservationServiceName());
//            assertEquals(itinearyStays[i].isCreditCardRequired(),stays[i].isCreditCardRequired());
//            assertEquals(itinearyStays[i],"confirmed");
//            
//        }

        
    }

    
}