package dtu.ws.travelgood.SOAPTest;

import dtu.ws.travelgood.client.FlightInformationType;
import dtu.ws.travelgood.client.FlightList;
import dtu.ws.travelgood.client.HotelList;
import dtu.ws.travelgood.client.ItineraryType;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
//For date
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
public class P1 {

    /**
     *
     */
    public P1() {
       
    }

    
    /**
     *
     */
    @Test
    public void testP1() throws DatatypeConfigurationException {
        //Input data
        String name="Poul Thomsen";
        String destination="Berlin";
        String departure="Copenhagen";
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar date1 = df.newXMLGregorianCalendar("2015-09-15");
        XMLGregorianCalendar date2 = df.newXMLGregorianCalendar("2015-09-16");
        XMLGregorianCalendar date3 = df.newXMLGregorianCalendar("2015-09-17");
        
        
        //Output data
        String itenaryId;
        ItineraryType itenary;
        FlightList flightList1,flightList2,flightList3;
        int [] flightBookingNumbers=new int[3];
        int [] hotelBookingNumbers = new int[2];
        int [] itenaryFlightBookingNumbers=new int[3];
        int [] itenaryHotelBookingNumbers = new int[2];
        
                
        itenaryId=createItinerary(name);
        
<<<<<<< HEAD
        //get a list if flights and choose the first
        flightBookingNumbers[0]=getFlights(destination, date1, departure).getFlight().get(0).getBookingNumber();
        // add the flight to itenary
        addFlight(name, flightBookingNumbers[0]);
        
        //get list of avaviable hotels
        hotelBookingNumbers[0]=getHotels(date1, date2, destination).getHotel().get(0).getBookingNumber();
=======
        //get a list if fights and choose the first
        flightBookingNumber1=getFlights(itenaryId, destination, date1, departure).getFlight().get(0).getBookingNumber();
        // add the first flight to itenary
        addFlight(name, flightBookingNumber1);
        
        //get list of avaviable hotels
        hotelBookingNumber1=getHotels(itenaryId, date1, date2, destination).getHotel().get(0).getBookingNumber();
>>>>>>> fa85f96ab20f36ea4451f661aaf30cbb72388aae
        //book the first hotel
        addHotelStay(name, hotelBookingNumbers[0]);
        
        //plan another flight
<<<<<<< HEAD
        flightBookingNumbers[1]=getFlights(destination, date2, departure).getFlight().get(0).getBookingNumber();
        addFlight(name, flightBookingNumbers[1]);
        
        //plan a third flight
        flightBookingNumbers[2]=getFlights(destination, date3, departure).getFlight().get(0).getBookingNumber();
        addFlight(name, flightBookingNumbers[2]);
        
        //finally add a hotel
        hotelBookingNumbers[1]=getHotels(date1, date3, destination).getHotel().get(0).getBookingNumber();
=======
        flightBookingNumber2=getFlights(itenaryId, destination, date2, departure).getFlight().get(0).getBookingNumber();
        addFlight(name, flightBookingNumber2);
        
        //plan a third flight
        flightBookingNumber2=getFlights(itenaryId, destination, date3, departure).getFlight().get(0).getBookingNumber();
        addFlight(name, flightBookingNumber2);
        
        //finally add a hotel
        hotelBookingNumber2=getHotels(itenaryId, date1, date3, destination).getHotel().get(0).getBookingNumber();
>>>>>>> fa85f96ab20f36ea4451f661aaf30cbb72388aae
        //book the first hotel
        addHotelStay(name, hotelBookingNumbers[1]);
        
        itenary=getItinerary(name);
        
        int flightIndex=0;
        int hotelIndex=0;
                
        
        for (Object order : itenary.getFlightInformationAndHotelinformation()) {
            if (order instanceof FlightInformationType){
                
                FlightInformationType fit=(FlightInformationType) order;
                itenaryFlightBookingNumbers[flightIndex]=fit.getBookingNumber();
                flightIndex++;
            
            }
            
            if (order instanceof HotelList){
                HotelList hl=(HotelList) order;
                hl.getHotel();
            
            } 
        }
        
        Arrays.sort(flightBookingNumbers);
        Arrays.sort(hotelBookingNumbers);
        
        
    }

    private static boolean addFlight(java.lang.String id, int bookingNumber) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addFlight(id, bookingNumber);
    }

    private static boolean addHotelStay(java.lang.String id, int bookingNumber) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addHotelStay(id, bookingNumber);
    }

    private static boolean cancelItinerary(java.lang.String id) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(id);
    }

    private static String createItinerary(java.lang.String name) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.createItinerary(name);
    }

    private static ItineraryType getItinerary(java.lang.String id) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getItinerary(id);
    }

    private static FlightList getFlights(java.lang.String id, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departure) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getFlights(id, destination, time, departure);
    }

    private static HotelList getHotels(java.lang.String id, javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        dtu.ws.travelgood.client.TravelGoodService service = new dtu.ws.travelgood.client.TravelGoodService();
        dtu.ws.travelgood.client.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getHotels(id, start, end, city);
    }
}