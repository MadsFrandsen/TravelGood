package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;
import ws.travelgood.xml.TravelOptions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jonas
 */
public class T {

    public DatatypeFactory df;
    public String[] personNames, destinations, unbookableDestinations;
    public XMLGregorianCalendar[] dates;
    public int[] creditCardNumbers;
    public Travel[] travels, itinearyTravels, bookableTravels;
  
    public Stay[] stays, itinearyStays;
    public String itinearyID;
    public Itinerary itineary; 

    public T() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        personNames = new String[3];
        personNames[0] = "Poul Thomsen";
        personNames[1] = "Søren Frederiksen";
        personNames[2] = "Peter Pan";
        
        bookableTravels=new Travel[6];
        

        destinations = new String[4];
        destinations[0] = "";
        destinations[1] = "";
        destinations[2] = "";
        destinations[3] = "";
        
        unbookableDestinations = new String[3];
        unbookableDestinations[0] = "Kiev";
        unbookableDestinations[1] = "Hamburg";
        
        dates = new XMLGregorianCalendar[4];
        dates[0] = df.newXMLGregorianCalendar("2015-09-15");
        dates[1] = df.newXMLGregorianCalendar("2015-09-16");
        dates[2] = df.newXMLGregorianCalendar("2015-09-17");
        dates[3] = df.newXMLGregorianCalendar("2015-09-18");

        creditCardNumbers = new int[2];
        creditCardNumbers[0] = 1121222224;
        creditCardNumbers[1] = 1121222224;

    }

    public boolean addFlight(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addFlight(itineraryID, bookingNumber);
    }

    public boolean addHotel(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addHotel(itineraryID, bookingNumber);
    }

    public boolean bookItinerary(java.lang.String itineraryID, int creditCardNumber, java.lang.String cardOwner) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.bookItinerary(itineraryID, creditCardNumber, cardOwner);
    }

    public boolean cancelItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(itineraryID);
    }

    public String createItinerary(java.lang.String personName) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.createItinerary(personName);
    }

    public Itinerary getItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getItinerary(itineraryID);
    }

    public TravelOptions getFlights(java.lang.String itineraryID, java.lang.String arrivalDestination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departureDestination) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getFlights(itineraryID, arrivalDestination, time, departureDestination);
    }

    public AccommodationOptions getHotels(java.lang.String itineraryID, javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getHotels(itineraryID, start, end, city);
    }
    
    
    class Travel {
    public String airline;
    public String departure;
    public String distination;
    public XMLGregorianCalendar arrivalDate;
    public String arrivalTime;
    public String departureDate;
    
    }
}

//Airline;      Source; Departure_Date; Departure_Time; Destination; Arrival_Date; Arrival_Time
//SAS;          CPH;    24122014;       1430;           BKK;        25122014;       600
//SAS;          CPH;    24122014;       1630;           BKK;        25122014;       800
//SAS;          CPH;    24122014;       1830;           BKK;        25122014;       1000
//SAS;          BKK;    31122014;       130;            CPH;        1012015;        730
//SAS;          BKK;    31122015;       230;            CPH;        1012016;        830
//SAS;          BKK;    31122016;       330;            CPH;        1012017;        930
//Thai;         BKK;    27122014;       1530;           SFO;        28122014;       700
//Thai;         BKK;    27122015;       1700;           SFO;        28122014;       930
//Thai;         SFO;    2022015;        1100;           BKK;        3022015;        800
//Thai;         SFO;    2022016;        1500;           BKK;        3022015;        1200
//Lufthansa;    FRA;    26122014;       1200;           SFO;        26122014;       2000
//Lufthansa;    FRA;    26122014;       1400;           SFO;        26122014;       2200
//Lufthansa;    FRA;    26122014;       1600;           SFO;        26122014;       2330
//Lufthansa;    SFO;    5012015;        1200;           FRA;        5012015;        1830
//Lufthansa;    SFO;    5012016;        1430;           FRA;        5012016;        2000
//Lufthansa;    SFO;    5012017;        1645;           FRA;        5012017;        2245
//Air France;   CDG;    10012015;       930;            YVR;        10012015;       1430
//Air France;   CDG;    10012015;       1130;           YVR;        10012015;       1630
//Air France;   YVR;    15012015;       1030;           CDG;        15012015;       1830
//Air France;   YVR;    15012015;       1330;           CDG;        15012015;       2130
//KLM;          AMS;    12122014;       1730;           DAR;        12122014;       2300
//KLM;          DAR;    15122014;       1500;           AMS;        15122014;       2100
//Disney;       Andeby; 1012015;        1200;           Moon;       1012015;        2300