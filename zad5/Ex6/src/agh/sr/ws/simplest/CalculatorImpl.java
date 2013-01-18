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
	@WebResult(name = "result", partName = "result")
	public int addOperation(@WebParam(name = "a", partName = "a") int a,
			@WebParam(name = "b", partName = "b") int b,
			@WebParam(name = "c", partName = "c") int c) {
		// TODO Auto-generated method stub
		return a+b+c;
	}

	@Override
	@WebMethod(operationName = "DivideOperation", action = "http://www.example.org/Calculator/DivideOperation")
	@WebResult(name = "result", partName = "result")
	public double divideOperation(
			@WebParam(name = "dividend", partName = "dividend") int dividend,
			@WebParam(name = "divisor", partName = "divisor") int divisor) {
		// TODO Auto-generated method stub
		if(divisor!=0){
			return dividend/divisor;
		}
		return 0;
	}


}
