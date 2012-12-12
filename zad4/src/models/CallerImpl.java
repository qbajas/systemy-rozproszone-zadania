package models;

import java.util.Map;

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
		for (Map.Entry<Integer, Event> entry : eventManagerPrx.listEvents()
				.entrySet()) {
			System.out.print(" " + entry.getKey() + ". name:"
					+ entry.getValue().name + " createdBy:"
					+ entry.getValue().createdBy.nick + " description:"
					+ entry.getValue().description + " subscribed:");
			for (User user : entry.getValue().subscribedUsers) {
				System.out.print(user.nick + ",");
			}
			System.out.println();
		}
	}

	@Override
	public void createEvent(String eventName, String eventDesc,
			String daysFromNow) {
		eventManagerPrx.createEvent(eventName, eventDesc, user);
		System.out.println("Event created. ");
	}

	@Override
	public void subscribe(String eventId) {
		String response = eventManagerPrx.subscribe(Integer.parseInt(eventId),
				user);
		if (response.isEmpty()) {
			System.out.println("Subscribed to event.");
		} else {
			System.out.println(response);
		}
	}

	@Override
	public void modify(String eventId, String eventName, String eventDesc,
			String daysFromNow) {
		String response = eventManagerPrx.modify(Integer.parseInt(eventId),
				eventName, eventDesc, user);
		if (response.isEmpty()) {
			System.out.println("Event modified.");
		} else {
			System.out.println(response);
		}
	}

}
