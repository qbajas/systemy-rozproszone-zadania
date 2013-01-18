package agh.sr.ws.simplest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import agh.sr.ws.simplest.jaxws.SimplestHello;
import agh.sr.ws.simplest.jaxws.SimplestHelloImplService;

public class Client {

	public static void main(String[] args) throws MalformedURLException {
//		Service service = Service.create(new URL(
//				"http://localhost:8181/SimplestHelloService"), new QName(
//				"http://simplest.ws.sr.agh/", "SimplestHelloImplService"));
//		SimplestHello client = service.getPort(SimplestHello.class);
		
		SimplestHello client = new SimplestHelloImplService().getSimplestHelloImplPort();
		
		((BindingProvider) client).getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://localhost:9191/SimplestHelloService");
		System.out.println(client.sayHello("The Client", 23, 100f));
	}

}
