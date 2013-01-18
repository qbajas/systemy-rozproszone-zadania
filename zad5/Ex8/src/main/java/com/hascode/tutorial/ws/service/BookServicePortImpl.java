package com.hascode.tutorial.ws.service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(endpointInterface = "com.hascode.tutorial.ws.service.BookServicePortType")
public class BookServicePortImpl implements BookServicePortType {

	
	@Resource
    WebServiceContext wsctx;
	
	@Override
	public BookServiceResponseType fetchBooks(
			BookServiceRequestType bookServiceRequest) {

		MessageContext mctx = wsctx.getMessageContext();

		// get detail from request headers
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		List userList = (List) http_headers.get("Username");
		List passList = (List) http_headers.get("Password");

		String username = "";
		String password = "";

		if (userList != null) {
			// get username
			username = userList.get(0).toString();
		}

		if (passList != null) {
			// get password
			password = passList.get(0).toString();
		}

		final BookServiceResponseType response = new BookServiceResponseType();
		// Should validate username and password with database
		if (username.equals("mkyong") && password.equals("password")) {

			for (int i = 0; i < bookServiceRequest.getLimit(); i++) {
				final BookType book = new BookType();
				book.setAuthor("Elvis " + i);
				try {
					book.setPublished(DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(
									new GregorianCalendar(2011, 8, 14)));
				} catch (DatatypeConfigurationException e) {
				}
				book.setTitle("Programming Java Edition #" + i);
				response.getBook().add(book);
			}
			return response;
		}
		return response;

	}
}