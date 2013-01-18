package agh.sr.ws.simplest;

import javax.jws.WebService;

import agh.sr.ws.simplest.jaxws.SimplestHello;

@WebService(endpointInterface = "agh.sr.ws.simplest.jaxws.SimplestHello") // changed
public class SimplestHelloImpl implements SimplestHello {

	@Override
	public String sayHello(String sender, int age, float salary) {
		String ret = "Saying hello to: " + sender + " at age: " + age
				+ " with salary: " + salary;
		System.out.println("Processing request with the following result:"
				+ ret);
		return ret;
	}

}
