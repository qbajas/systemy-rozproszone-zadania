package agh.sr.ws.simplest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import agh.sr.ws.simplest.jaxws.Calculator;

@WebService(endpointInterface = "agh.sr.ws.simplest.jaxws.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	@WebMethod(operationName = "AddOperation", action = "http://www.example.org/Calculator/AddOperation")
	@WebResult(name = "out", targetNamespace = "")
	@RequestWrapper(localName = "AddOperation", targetNamespace = "http://www.example.org/Calculator/", className = "agh.sr.ws.simplest.jaxws.AddOperation")
	@ResponseWrapper(localName = "AddOperationResponse", targetNamespace = "http://www.example.org/Calculator/", className = "agh.sr.ws.simplest.jaxws.AddOperationResponse")
	public int addOperation(@WebParam(name = "a", targetNamespace = "") int a,
			@WebParam(name = "b", targetNamespace = "") int b,
			@WebParam(name = "c", targetNamespace = "") int c) {

		return a+b+c;
	}

	@Override
	@WebMethod(operationName = "DivideOperation", action = "http://www.example.org/Calculator/DivideOperation")
	@WebResult(name = "out", targetNamespace = "")
	@RequestWrapper(localName = "DivideOperation", targetNamespace = "http://www.example.org/Calculator/", className = "agh.sr.ws.simplest.jaxws.DivideOperation")
	@ResponseWrapper(localName = "DivideOperationResponse", targetNamespace = "http://www.example.org/Calculator/", className = "agh.sr.ws.simplest.jaxws.DivideOperationResponse")
	public double divideOperation(
			@WebParam(name = "dividend", targetNamespace = "") int dividend,
			@WebParam(name = "divisor", targetNamespace = "") int divisor) {
		// TODO Auto-generated method stub
		if(divisor!=0){
			return dividend/divisor;
		}
		return 0;
	}

}
