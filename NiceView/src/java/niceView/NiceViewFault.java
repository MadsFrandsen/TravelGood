/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView;

import javax.xml.ws.WebFault;

/**
 *
 * @author planaspa
 */
@WebFault(name="NiceViewFault")
public class NiceViewFault extends Exception {
    
    public NiceViewFault(){
        
    }
    
    public NiceViewFault (String message){
        super (message);
    }
}
