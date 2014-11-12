/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mads
 */
public class Route {
    
    private Location origin;
    private Location destination;
    
    public Route(Location origin, Location destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
