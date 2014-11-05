/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fastmoney.imm.dtu.dk;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import javax.jws.WebService;

/**
 *
 * @author Nygaard
 */
@WebService(serviceName = "BankService", portName = "BankPort", endpointInterface = "dk.dtu.imm.fastmoney.BankPortType", targetNamespace = "urn://fastmoney.imm.dtu.dk", wsdlLocation = "WEB-INF/wsdl/BankService/BankService.wsdl")
public class BankService {

    public boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
