/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mads
 */
public class Itinerary {
    
    private String id;
    private Map<String, Flight> flights = new HashMap<String, Flight>();
    private Map<String, Hotel> hotels = new HashMap<String, Hotel>();
    private boolean changeable = true;
    
    public Itinerary(String id) {
        this.id = id;
    }
    
    public Map<String, Flight> getFlights() {
        return flights;
    }
    
    public Map<String, Hotel> getHotels() {
        return hotels;
    }
    
    public String getId() {
        return id;
    }
    
    public void setChangeable(boolean value) {
        changeable = value;
    }
    
    public boolean isChangeable() {
        return changeable;
    }
}
