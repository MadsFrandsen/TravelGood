/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mads
 */
@XmlRootElement
public class Itinerary {
    
    @XmlElement
    private String id;
    
    @XmlElement
    private Flight[] flights;
    
    @XmlElement
    private Hotel[] hotels;
    
    public Itinerary() {
        
    }
    
    public Itinerary(String id) {
        this.id = id;
    }
    
    public Flight[] getFlights() {
        return flights;
    }
    
    public Hotel[] getHotels() {
        return hotels;
    }
}
