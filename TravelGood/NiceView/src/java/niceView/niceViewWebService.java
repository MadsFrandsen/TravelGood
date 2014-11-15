/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebFault;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author planaspa
 */
@WebService(serviceName = "NiceViewService")
@WebFault(name="NiceViewFault")
public class niceViewWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

    private ArrayList <Reservation> reservations;   //Done-Reservations
    private int bookingNumberCount;
    private ArrayList <Hotel> hotels;   //Hotels available in the service
    private ArrayList <Reservation> possibleHotels;
    
    /**
     * Constructor
     */
    public niceViewWebService (){
        reservations = new ArrayList <Reservation> ();
        bookingNumberCount = 0;
        hotels = new ArrayList <Hotel> ();
        possibleHotels = new ArrayList <Reservation> ();
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
                reservation = new Reservation (hotel,bookingNumberCount,(int)(days * hotel.getPrice()));
                possibleHotels.add(reservation);
            }
        }
        return possibleHotels;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(@WebParam(name = "bookingNumber") int bookingNumber, @WebParam(name = "creditCardInfo") dk.dtu.imm.fastmoney.CreditCardInfoType creditCardInfo, @WebParam(name = "account")dk.dtu.imm.fastmoney.AccountType account) throws NiceViewFault {

        boolean booked = false;
        
        for (Reservation reservation : possibleHotels){
            if (reservation.getBookingNumber() == bookingNumber){
                try{
                if (reservation.isCreditCardGuarantee()){  
                    validateCreditCard(0, creditCardInfo, reservation.getTotalPrice());
                    reservation.setAccount(account);
                    reservation.setCreditCardInfo(creditCardInfo);
                    chargeCreditCard(0,creditCardInfo,reservation.getTotalPrice(),account);
                }
                
                reservations.add(reservation);
                possibleHotels.remove(reservation);
                booked = true;
                }
                catch (CreditCardFaultMessage er){
                    throw new NiceViewFault();
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
        for (Reservation reservation : reservations){
            if (reservation.getBookingNumber() == bookingNumber){
                //Cancellation
                try{
                    refundCreditCard(0,reservation.getCreditCardInfo(),reservation.getTotalPrice(),reservation.getAccount());
                    reservations.remove(reservation);
                    found = true;
                }
                catch (CreditCardFaultMessage er){
                    throw new NiceViewFault();
                }
            }
        }
        if (!found) throw new NiceViewFault();
    }

    /*
     * BankService Methods
     */

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

}
