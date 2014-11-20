package dtu.ws.travelgood.SOAPTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;
import ws.travelgood.xml.Travel;
import ws.travelgood.xml.TravelOptions;

/*
 * Class containing test data and methods for interacting 
 * with the webservices. All tests enherit from this class.
 */
/**
 *
 * @author jonas
 */
public class T {

    FlightOption[] bookable, unbookable, nonCancelable;
    public DatatypeFactory df;
    public Travel[] travels, itinearyTravels, bookableTravels;
    public Stay[] stays, itinearyStays;
    public String itinearyID;
    public Itinerary itineary;
    public Person[] person;
    HotelOption[] hotelOption;

    public T() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        loadFlightOptions();
        loadPersonOptions();
        loadHotelOptions();
        bookableTravels = new Travel[6];
    }

    class FlightOption {

        @Override
        public String toString() {
            return "FlightOption{" + "airline=" + airline + ", source=" + source + ", destination=" + destination + ", arrival=" + arrival + ", departure=" + departure + '}';
        }
        public String airline, source, destination;
        XMLGregorianCalendar arrival, departure;
    }

    class Person {

        public String name;
        public int number, expMonth, expYear, ccLimit;

        @Override
        public String toString() {
            return "Person{" + "name=" + name + ", number=" + number + ", expMonth=" + expMonth + ", expYear=" + expYear + '}';
        }
    }

    class HotelOption {

        public String name, address, city;
        public int price;
        public boolean validatesCreditcards;
        public XMLGregorianCalendar fromDate, toDate;

        public HotelOption() {
            fromDate = df.newXMLGregorianCalendar("2015-02-01");
            toDate = df.newXMLGregorianCalendar("2015-02-04");
        }
    }

    private void loadPersonOptions() {
        person = new Person[10];
        for (int i = 0; i < person.length; i++) {
            person[i] = new Person();

        }
        person[0].name = "Anne Strandberg";
        person[0].number = 50408816;
        person[0].expMonth = 5;
        person[0].expYear = 9;
        person[0].ccLimit = 0;
        person[1].name = "Klinkby Poul";
        person[1].number = 50408817;
        person[1].expMonth = 3;
        person[1].expYear = 10;
        person[1].ccLimit = 0;
        person[2].name = "Donovan Jasper";
        person[2].number = 50408818;
        person[2].expMonth = 6;
        person[2].expYear = 9;
        person[2].ccLimit = 0;
        person[3].name = "Dirach Anne-Louise";
        person[3].number = 50408819;
        person[3].expMonth = 1;
        person[3].expYear = 10;
        person[3].ccLimit = 0;
        person[4].name = "Brorson Bodil";
        person[4].number = 50408820;
        person[4].expMonth = 7;
        person[4].expYear = 11;
        person[4].ccLimit = 0;
        person[5].name = "Bruhn Brigitte";
        person[5].number = 50408821;
        person[5].expMonth = 2;
        person[5].expYear = 10;
        person[5].ccLimit = 0;
        person[6].name = "Bech Camilla";
        person[6].number = 50408822;
        person[6].expMonth = 7;
        person[6].expYear = 9;
        person[6].ccLimit = 1000;
        person[7].name = "Tobiasen Inge";
        person[7].number = 50408823;
        person[7].expMonth = 9;
        person[7].expYear = 10;
        person[7].ccLimit = 1000;
        person[8].name = "Tick Joachim";
        person[8].number = 50408824;
        person[8].expMonth = 2;
        person[8].expYear = 11;
        person[8].ccLimit = 10000;
        person[9].name = "Thor-Jensen Claus";
        person[9].number = 50408825;
        person[9].expMonth = 5;
        person[9].expYear = 9;
        person[9].ccLimit = 10000;
    }

    private void loadHotelOptions() {
        hotelOption = new HotelOption[21];
        for (int i = 0; i < hotelOption.length; i++) {
            hotelOption[i] = new HotelOption();
        }
        hotelOption[0].name = "Hotel 0";
        hotelOption[0].address = "Main Street 0";
        hotelOption[0].validatesCreditcards = true;
        hotelOption[0].price = 10;
        hotelOption[0].city = "City 0";
        hotelOption[1].name = "Hotel 1";
        hotelOption[1].address = "Main Street 1";
        hotelOption[1].validatesCreditcards = false;
        hotelOption[1].price = 1010;
        hotelOption[1].city = "City 1";
        hotelOption[2].name = "Hotel 2";
        hotelOption[2].address = "Main Street 2";
        hotelOption[2].validatesCreditcards = true;
        hotelOption[2].price = 2010;
        hotelOption[2].city = "City 2";
        hotelOption[3].name = "Hotel 3";
        hotelOption[3].address = "Main Street 3";
        hotelOption[3].validatesCreditcards = false;
        hotelOption[3].price = 3010;
        hotelOption[3].city = "City 3";
        hotelOption[4].name = "Hotel 4";
        hotelOption[4].address = "Main Street 4";
        hotelOption[4].validatesCreditcards = true;
        hotelOption[4].price = 4010;
        hotelOption[4].city = "City 4";
        hotelOption[5].name = "Hotel 5";
        hotelOption[5].address = "Main Street 5";
        hotelOption[5].validatesCreditcards = false;
        hotelOption[5].price = 5010;
        hotelOption[5].city = "City 0";
        hotelOption[6].name = "Hotel 6";
        hotelOption[6].address = "Main Street 6";
        hotelOption[6].validatesCreditcards = true;
        hotelOption[6].price = 6010;
        hotelOption[6].city = "City 1";
        hotelOption[7].name = "Hotel 7";
        hotelOption[7].address = "Main Street 7";
        hotelOption[7].validatesCreditcards = false;
        hotelOption[7].price = 7010;
        hotelOption[7].city = "City 2";
        hotelOption[8].name = "Hotel 8";
        hotelOption[8].address = "Main Street 8";
        hotelOption[8].validatesCreditcards = true;
        hotelOption[8].price = 8010;
        hotelOption[8].city = "City 3";
        hotelOption[9].name = "Hotel 9";
        hotelOption[9].address = "Main Street 9";
        hotelOption[9].validatesCreditcards = false;
        hotelOption[9].price = 9010;
        hotelOption[9].city = "City 4";
        hotelOption[10].name = "Hotel 10";
        hotelOption[10].address = "Main Street 10 ";
        hotelOption[10].validatesCreditcards = true;
        hotelOption[10].price = 10010;
        hotelOption[10].city = "City 0";
        hotelOption[11].name = "Hotel 11";
        hotelOption[11].address = "Main Street 11 ";
        hotelOption[11].validatesCreditcards = false;
        hotelOption[11].price = 11010;
        hotelOption[11].city = "City 1";
        hotelOption[12].name = "Hotel 12";
        hotelOption[12].address = "Main Street 12 ";
        hotelOption[12].validatesCreditcards = true;
        hotelOption[12].price = 12010;
        hotelOption[12].city = "City 2";
        hotelOption[13].name = "Hotel 13";
        hotelOption[13].address = "Main Street 13 ";
        hotelOption[13].validatesCreditcards = false;
        hotelOption[13].price = 13010;
        hotelOption[13].city = "City 3";
        hotelOption[14].name = "Hotel 14";
        hotelOption[14].address = "Main Street 14 ";
        hotelOption[14].validatesCreditcards = true;
        hotelOption[14].price = 14010;
        hotelOption[14].city = "City 4";
        hotelOption[15].name = "Hotel 15";
        hotelOption[15].address = "Main Street 15 ";
        hotelOption[15].validatesCreditcards = false;
        hotelOption[15].price = 15010;
        hotelOption[15].city = "City 0";
        hotelOption[16].name = "Hotel 16";
        hotelOption[16].address = "Main Street 16 ";
        hotelOption[16].validatesCreditcards = true;
        hotelOption[16].price = 16010;
        hotelOption[16].city = "City 1";
        hotelOption[17].name = "Hotel 17";
        hotelOption[17].address = "Main Street 17 ";
        hotelOption[17].validatesCreditcards = false;
        hotelOption[17].price = 17010;
        hotelOption[17].city = "City 2";
        hotelOption[18].name = "Hotel 18";
        hotelOption[18].address = "Main Street 18 ";
        hotelOption[18].validatesCreditcards = true;
        hotelOption[18].price = 18010;
        hotelOption[18].city = "City 3";
        hotelOption[19].name = "Hotel 19";
        hotelOption[19].address = "Main Street 19 ";
        hotelOption[19].validatesCreditcards = false;
        hotelOption[19].price = 19010;
        hotelOption[19].city = "City 4";
        hotelOption[20].name = "Expensive";
        hotelOption[20].address = "5th Avenue";
        hotelOption[20].validatesCreditcards = true;
        hotelOption[20].price = 1000000;
        hotelOption[20].city = "NYC";

    }

    private void loadFlightOptions() {
        bookable = new FlightOption[1];
        unbookable = new FlightOption[1];
        nonCancelable = new FlightOption[1];


        String flightData = "../LameDuck/flightsdata.csv";
        ArrayList<FlightOption> bookableList = new ArrayList<>();
        ArrayList<FlightOption> unbookableList = new ArrayList<>();
        ArrayList<FlightOption> nonCancelableList = new ArrayList<>();

        try {
            File f = new File(flightData);

            Scanner in = new Scanner(f);
            String headers = in.nextLine(); // skip first line as it is only headers
            FlightOption fo = new FlightOption();

            DatatypeFactory df = DatatypeFactory.newInstance();
            while (in.hasNext()) {
                String[] flightInfo = in.nextLine().split(";"); // Excel data is seperated by ;

                fo.airline = flightInfo[0];
                fo.source = flightInfo[1];
                String departureDate = flightInfo[2];
                String departureTime = flightInfo[3];
                fo.destination = flightInfo[4];
                String arrivalDate = flightInfo[5];
                String arrivalTime = flightInfo[6];

                /* Convert string date to Gregorian Calendar */
                String departureDay = departureDate.substring(0, 2);
                String departureMonth = departureDate.substring(2, 4);
                String departureYear = departureDate.substring(4, 8);
                String departureHour = departureTime.substring(0, 2);
                String departureMinute = departureTime.substring(2, 4);
                GregorianCalendar departure = new GregorianCalendar(Integer.parseInt(departureYear), Integer.parseInt(departureMonth), Integer.parseInt(departureDay), Integer.parseInt(departureHour), Integer.parseInt(departureMinute));
                fo.departure = df.newXMLGregorianCalendar(departure);

                String arrivalDay = departureDate.substring(0, 2);
                String arrivalMonth = departureDate.substring(2, 4);
                String arrivalYear = departureDate.substring(4, 8);
                String arrivalHour = arrivalDate.substring(0, 2);
                String arrivalMinute = arrivalDate.substring(2, 4);
                GregorianCalendar arrival = new GregorianCalendar(Integer.parseInt(arrivalYear), Integer.parseInt(arrivalMonth), Integer.parseInt(arrivalDay), Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
                fo.arrival = df.newXMLGregorianCalendar(arrival);
                //System.out.println(fo);
                if ("Disney".equals(fo.airline)) {
                    unbookableList.add(fo);
                } else if ("Mordor".equals(fo.airline)) {
                    nonCancelableList.add(fo);
                } else {
                    bookableList.add(fo);

                }

            }
            bookable = bookableList.toArray(bookable);
            unbookable = unbookableList.toArray(bookable);
            nonCancelable = nonCancelableList.toArray(nonCancelable);
        } catch (Exception e) {
            System.err.println("File not found");
        }


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
}
