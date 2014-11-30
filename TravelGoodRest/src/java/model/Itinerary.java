/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mads
 */
@XmlRootElement
public class Itinerary {
    
    @XmlElement
    private String id;
    
    @XmlElement
    private List<Flight> flights = new ArrayList<Flight>();
    
    @XmlElement
    private List<Hotel> hotels = new ArrayList<Hotel>();
    
    @XmlTransient
    private CreditCardInfoType creditCard;
    
    public Itinerary() {
        
    }
    
    public Itinerary(String id) {
        this.id = id;
    }
    
    @XmlTransient
    public void setCreditCard(CreditCardInfoType creditCard) {
        this.creditCard = creditCard;
    }
    
    public CreditCardInfoType getCreditCard() {
        return creditCard;
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public List<Hotel> getHotels() {
        return hotels;
    }
}
