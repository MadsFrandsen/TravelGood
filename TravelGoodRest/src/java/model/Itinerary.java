/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
    private List<Flight> flights = new ArrayList<Flight>();
    
    @XmlElement
    private List<Hotel> hotels = new ArrayList<Hotel>();
    
    public Itinerary() {
        
    }
    
    public Itinerary(String id) {
        this.id = id;
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public List<Hotel> getHotels() {
        return hotels;
    }
}
