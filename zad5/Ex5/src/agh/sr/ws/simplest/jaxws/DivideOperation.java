
package agh.sr.ws.simplest.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dividend" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="divisor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dividend",
    "divisor"
})
@XmlRootElement(name = "DivideOperation")
public class DivideOperation {

    protected int dividend;
    protected int divisor;

    /**
     * Gets the value of the dividend property.
     * 
     */
    public int getDividend() {
        return dividend;
    }

    /**
     * Sets the value of the dividend property.
     * 
     */
    public void setDividend(int value) {
        this.dividend = value;
    }

    /**
     * Gets the value of the divisor property.
     * 
     */
    public int getDivisor() {
        return divisor;
    }

    /**
     * Sets the value of the divisor property.
     * 
     */
    public void setDivisor(int value) {
        this.divisor = value;
    }

}
