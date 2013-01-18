package agh.sr.ws.simplest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import agh.sr.ws.simplest.jaxws.Calculator;
import agh.sr.ws.simplest.jaxws.Calculator_Service;

public class Client {

	public static void main(String[] args) throws MalformedURLException {
		// Service service = Service.create(new URL(
		// "http://localhost:8181/SimplestHelloService"), new QName(
		// "http://simplest.ws.sr.agh/", "SimplestHelloImplService"));
		// SimplestHello client = service.getPort(SimplestHello.class);

		Calculator client = new Calculator_Service().getCalculatorSOAP();

		((BindingProvider) client).getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://localhost:9191/CalculatorService");
		System.out.println("Result of add operation: "
				+ client.addOperation(1, 2, 3));
		System.out.println("Result of divide operation: "
				+ client.divideOperation(10, 3));
	}

}
