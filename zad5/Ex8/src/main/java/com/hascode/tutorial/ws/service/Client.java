package com.hascode.tutorial.ws.service;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

public class Client {

	public static void main(String[] args) throws MalformedURLException {

		BookServicePortType client = new BookService().getBookServicePort();
		BookServiceRequestType bookServiceRequest = new BookServiceRequestType();
		bookServiceRequest.setLimit(4);

		Map<String, Object> req_ctx = ((BindingProvider) client)
				.getRequestContext();

		req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://localhost:8888/bookService");

		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Username", Collections.singletonList("mkyong"));
		headers.put("Password", Collections.singletonList("password"));
		req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

		System.out.println("Books: " + client.fetchBooks(bookServiceRequest));
	}

}
