package agh.sr.ws.simplest;

import javax.xml.ws.Endpoint;

public class Server {
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8181/CalculatorService", new CalculatorImpl());
	}

}