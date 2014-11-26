/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebFault;

@WebService(serviceName = "NiceViewService")
@WebFault(name="NiceViewFault")
public class niceViewWebService {
    private final AccountType NICE_VIEW_ACCOUNT;
    private BankService service;
    private ArrayList <FinalReservation> reservations;   //Done-Reservations
    private int bookingNumberCount;
    private ArrayList <Hotel> hotels;   //Hotels available in the service
    private ArrayList <Reservation> possibleHotels;
    
    /**
     * Constructor
     */
    public niceViewWebService (){
        NICE_VIEW_ACCOUNT = new AccountType();
        NICE_VIEW_ACCOUNT.setName("NiceView");
        NICE_VIEW_ACCOUNT.setNumber("50308815");
        reservations = new ArrayList <FinalReservation> ();
        bookingNumberCount = 0;
        hotels = new ArrayList <Hotel> ();
        possibleHotels = new ArrayList <Reservation> ();
        loadData();
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotels")
    public ArrayList <Reservation> getHotels(@WebParam(name = "city") String city, @WebParam(name = "arrival") GregorianCalendar arrival, @WebParam(name = "departure") GregorianCalendar departure) {
                
        for (Hotel hotel : hotels){
            if (hotel.getCity().equals(city)){
                Date arrivalDate = arrival.getTime();
                Date departureDate = departure.getTime();
                long days = (departureDate.getTime() - arrivalDate.getTime())
                             / (1000 * 60 * 60 * 24);
                bookingNumberCount++;
                Reservation reservation;
                reservation = new Reservation (bookingNumberCount,(int)(days * hotel.getPrice()),hotel.getName(), hotel.getAddress(), hotel.isCreditCardGuarantee(), hotel.getHotelReservationService());
                possibleHotels.add(reservation);
            }
        }
        return possibleHotels;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "creditCardInfo") @XmlElement(required=false) dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws NiceViewFault {

        boolean booked = false;
        
        for (Iterator <Reservation> it = possibleHotels.iterator(); it.hasNext(); ){
            Reservation reservation = it.next();
            if (reservation.getBookingNumber() == bookingNumber){
                try{
                if (reservation.isCreditCardGuarantee()){ 
                    if (validateCreditCard(5, creditCardInfo, reservation.getTotalPrice()))
                        chargeCreditCard(5,creditCardInfo,reservation.getTotalPrice(),NICE_VIEW_ACCOUNT);
                }
                FinalReservation fReservation = new FinalReservation(reservation.getBookingNumber(), reservation.getTotalPrice(), creditCardInfo, reservation.isCreditCardGuarantee());
                reservations.add(fReservation);
                it.remove();
                booked = true;
                }
                catch (CreditCardFaultMessage er){
                    throw new NiceViewFault(er.getMessage());
                }
            }
        }
        return booked;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cancelHotel")
    public void cancelHotel(@WebParam(name = "bookingNumber") int bookingNumber) throws NiceViewFault{
        
        boolean found = false;
        for (Iterator <FinalReservation> it = reservations.iterator(); it.hasNext();){
            FinalReservation reservation = it.next();
            if (reservation.getBookingNumber() == bookingNumber){
                //Cancellation
                try{
                    if (reservation.isIsCreditCardGuarantee())
                        refundCreditCard(5,reservation.getCreditCardInfo(),reservation.getTotalPrice(),NICE_VIEW_ACCOUNT);
                    it.remove();
                    found = true;
                }
                catch (CreditCardFaultMessage er){
                    throw new NiceViewFault(er.getMessage());
                }
            }
        }
        if (!found) throw new NiceViewFault("Booking Number Not Found");
    }
     
    private void loadData (){
        
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
    
    /*
     * BankService Methods
     */
    
    private static boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    
   /* public static void main (String [] args){
            dk.dtu.imm.fastmoney.types.CreditCardInfoType cc = 
                new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
  
            cc.setName("Thor-Jensen Claus");
            cc.setNumber("50408825");
            ExpirationDateType expirationDate = new ExpirationDateType();
            expirationDate.setMonth(5);
            expirationDate.setYear(9);
            cc.setExpirationDate(expirationDate);
        try {            
            validateCreditCard(5, cc, 200);
        } catch (CreditCardFaultMessage ex) {
            Logger.getLogger(niceViewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
