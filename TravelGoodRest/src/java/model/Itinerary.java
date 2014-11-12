/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Mads
 */
public class Itinerary {
    
    private String id;
    private Flight[] flights;
    private Hotel[] hotels;
    
    public Itinerary() {
        
    }
    
    public Flight[] getFlights() {
        return flights;
    }
    
    public Hotel[] getHotels() {
        return hotels;
    }
}
