
package agh.sr.ws.simplest.jaxws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the agh.sr.ws.simplest.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: agh.sr.ws.simplest.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DivideOperation }
     * 
     */
    public DivideOperation createDivideOperation() {
        return new DivideOperation();
    }

    /**
     * Create an instance of {@link DivideOperationResponse }
     * 
     */
    public DivideOperationResponse createDivideOperationResponse() {
        return new DivideOperationResponse();
    }

    /**
     * Create an instance of {@link AddOperation }
     * 
     */
    public AddOperation createAddOperation() {
        return new AddOperation();
    }

    /**
     * Create an instance of {@link AddOperationResponse }
     * 
     */
    public AddOperationResponse createAddOperationResponse() {
        return new AddOperationResponse();
    }

}
