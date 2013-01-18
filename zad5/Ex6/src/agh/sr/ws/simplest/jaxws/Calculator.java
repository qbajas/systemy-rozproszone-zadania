
package agh.sr.ws.simplest.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Calculator", targetNamespace = "http://www.example.org/Calculator/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Calculator {


    /**
     * 
     * @param b
     * @param c
     * @param a
     * @return
     *     returns int
     */
    @WebMethod(operationName = "AddOperation", action = "http://www.example.org/Calculator/AddOperation")
    @WebResult(name = "result", partName = "result")
    public int addOperation(
        @WebParam(name = "a", partName = "a")
        int a,
        @WebParam(name = "b", partName = "b")
        int b,
        @WebParam(name = "c", partName = "c")
        int c);

    /**
     * 
     * @param dividend
     * @param divisor
     * @return
     *     returns double
     */
    @WebMethod(operationName = "DivideOperation", action = "http://www.example.org/Calculator/DivideOperation")
    @WebResult(name = "result", partName = "result")
    public double divideOperation(
        @WebParam(name = "dividend", partName = "dividend")
        int dividend,
        @WebParam(name = "divisor", partName = "divisor")
        int divisor);

}
