package dtu.ws.travelgood.SOAPTest;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import flightlist.travelgood.ws.Travel;
import flightlist.travelgood.ws.TravelOptions;
import hotellist.travelgood.ws.AccommodationOptions;
import hotellist.travelgood.ws.Stay;
import itinerary.ws.Itinerary;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
    public CreditCardInfoType[] creditCardInfo;
    List<Hotel> hotels;

    public T() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        loadFlightOptions();
        loadPersonOptions();
        loadHotelOptions();
        loadCreditCardInfos();
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
    
    private void loadCreditCardInfos()
    {
        creditCardInfo = new CreditCardInfoType[10];
        for (int i = 0; i < person.length; i++)
        {
            creditCardInfo[i] = new CreditCardInfoType();
            ExpirationDateType expDate = new ExpirationDateType();
            expDate.setMonth(person[i].expMonth);
            expDate.setYear(person[i].expYear);
            
            creditCardInfo[i].setExpirationDate(expDate);
            creditCardInfo[i].setName(person[i].name);
            creditCardInfo[i].setNumber(Integer.toString(person[i].number));
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

    private void loadHotelOptions() 
    {
        hotels = new ArrayList<>();
        hotels.add(new Hotel("Paragon Inn", "22 Moo. 15, Tambun Rachatheva, Bangplee, Soi Latk",true, 243,"http://www.theparagoninn.com/ReservationService?wsdl", "Bangkok" ));
        hotels.add(new Hotel("Suphan Lake Hometel", "106/999 Klongsongtonnun Latkrabang",false, 108,"http://www.suphanlakehometel.com/ReservationService?wsdl", "Bangkok" ));
        hotels.add(new Hotel("Grand Inn Come Hotel", "99 Moo 6 Kingkaew Rd., Rachathewa, Samutprakan",true, 292,"http://www.grandinncome-hotel.com/ReservationService?wsdl", "Bangkok" ));
        hotels.add(new Hotel("Millwood Inn & Suites", "1375 El Camino RealMillbrae, CA 94030",true, 1035,"http://www.millwoodinn.com/ReservationService?wsdl", "San Francisco" ));
        hotels.add(new Hotel("Regency Inn", "411 San Bruno Ave E San Bruno, CA 94066",false, 743,"http://www.regencyinnsfo.com/ReservationService?wsdl", "San Francisco" ));
        hotels.add(new Hotel("The Opal", "1050 Van Ness Ave",true, 2604,"http://www.theopalsf.com/ReservationService?wsdl", "San Francisco" ));
        hotels.add(new Hotel("Dormero Hotel Frankfurt", "Lissabonner Straße 2",true, 662,"http://www.dormero.de/ReservationService?wsdl", "Frankfurt" ));
        hotels.add(new Hotel("Ramada Frankfurt Messe", "Oeserstraße 180",false, 435,"http://www.h-hotels.com/ReservationService?wsdl", "Frankfurt" ));
        hotels.add(new Hotel("Le Montclair Montmartre", "62 Rue Ramey",true, 490,"http://www.montclair-hostel.com/ReservationService?wsdl", "Paris" ));
        hotels.add(new Hotel("Hôtel Verlain", "97 Rue Saint-Maur",false, 705,"http://www.verlain.fr/ReservationService?wsdl", "Paris" ));
        hotels.add(new Hotel("Sheraton Vancouver Airport Hotel", "7551 Westminster Highway",true, 902,"http://www.sheratonvancouverairport.com/ReservationService?wsdl", "Vancouver" ));
        hotels.add(new Hotel("Holiday Inn Express & Suites Riverport Richmond", "10688 Number 6 Rd Richmond, BC V6W 1B1",false, 554,"http://www.ihg.com/ReservationService?wsdl", "Vancouver" ));
        hotels.add(new Hotel("InterContinental Amstel Amsterdam", "Professor Tulpplein 1",true, 3005,"http://www.ihg.com/ReservationService?wsdl", "Amsterdam" ));
        hotels.add(new Hotel("Southern Sun Dar Es Salaam", "Garden Ave",false, 302,"http://www.tsogosunhotels.com/ReservationService?wsdl", "Dar es Salaam" ));
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
            

            DatatypeFactory df = DatatypeFactory.newInstance();
            while (in.hasNext()) {
                FlightOption fo = new FlightOption();
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
                int departureMonth = Integer.parseInt(departureDate.substring(2, 4)) - 1;
                String departureYear = departureDate.substring(4, 8);
                String departureHour = departureTime.substring(0, 2);
                String departureMinute = departureTime.substring(2, 4);
                GregorianCalendar departure = new GregorianCalendar(Integer.parseInt(departureYear), departureMonth, Integer.parseInt(departureDay), Integer.parseInt(departureHour), Integer.parseInt(departureMinute));
                fo.departure = df.newXMLGregorianCalendar(departure);

                String arrivalDay = departureDate.substring(0, 2);
                int arrivalMonth = Integer.parseInt(departureDate.substring(2, 4)) - 1;
                String arrivalYear = departureDate.substring(4, 8);
                String arrivalHour = arrivalDate.substring(0, 2);
                String arrivalMinute = arrivalDate.substring(2, 4);
                GregorianCalendar arrival = new GregorianCalendar(Integer.parseInt(arrivalYear), arrivalMonth, Integer.parseInt(arrivalDay), Integer.parseInt(arrivalHour), Integer.parseInt(arrivalMinute));
                fo.arrival = df.newXMLGregorianCalendar(arrival);
                //System.out.println(fo);
                if ("Disney".equals(fo.airline))
                    unbookableList.add(fo);
                else if ("Mordor".equals(fo.airline))
                    nonCancelableList.add(fo);
                else
                    bookableList.add(fo);

            }
            bookable = bookableList.toArray(bookable);
            unbookable = unbookableList.toArray(unbookable);
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

    public boolean bookItinerary(java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.bookItinerary(itineraryID, creditCardInfo);
    }

    public boolean cancelItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(itineraryID);
    }
}