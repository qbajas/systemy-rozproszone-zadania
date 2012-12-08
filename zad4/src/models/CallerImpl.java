package models;

import generated.Event;
import generated.EventManagerPrx;
import generated.User;
import interfaces.Caller;

public class CallerImpl implements Caller {

	private EventManagerPrx eventManagerPrx;
	private User user;

	
	public CallerImpl(EventManagerPrx eventManagerPrx, User user) {
		super();
		this.eventManagerPrx = eventManagerPrx;
		this.user = user;
	}

	@Override
	public void listEvents() {
		System.out.println("Events in the system: ");
		for (Event event : eventManagerPrx.listEvents()) {
			System.out.println(event.toString());
		}
	}


}
