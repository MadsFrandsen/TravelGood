/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LameDuck;

import javax.xml.ws.WebFault;

/**
 *
 * @author Nygaard
 */

@WebFault(name = "LameDuckException" )
class LameDuckException extends Exception {

    public LameDuckException(){
        
    }
    
    public LameDuckException(String message){
        super(message);
    }
}
