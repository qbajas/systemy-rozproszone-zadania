package com.hascode.tutorial.ws.service;

import java.net.MalformedURLException;

import javax.xml.ws.BindingProvider;


public class Client {

	public static void main(String[] args) throws MalformedURLException {

		BookServicePortType client = new BookService().getBookServicePort();
		BookServiceRequestType bookServiceRequest = new BookServiceRequestType();
		bookServiceRequest.setLimit(4);

		((BindingProvider) client).getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://localhost:8888/bookService");
		System.out.println("Books: " + client.fetchBooks(bookServiceRequest));
	}

}
