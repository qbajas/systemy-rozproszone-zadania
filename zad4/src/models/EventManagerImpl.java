package models;

import java.util.LinkedList;
import java.util.List;

import Ice.Current;
import generated.Event;
import generated.User;
import generated._EventManagerDisp;

public class EventManagerImpl extends _EventManagerDisp {
	
	List<Event> events;
	
	
	public EventManagerImpl() {
		events = new LinkedList<Event>();
	}

	@Override
	public List<Event> listEvents(Current __current) {
		System.out.println("Listing events for client " + __current.toString());
		return events;
	}

	@Override
	public String createEvent(String eventName, String eventDesc,
			int daysFromNow, User u, Current __current) {
		Event event = new Event(eventName, eventDesc, daysFromNow, u, new LinkedList<User>());
		events.add(event);
		return null;
	}

	@Override
	public String subscribe(String eventName, User u, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(String eventName, String eventDesc, int daysFromNow,
			User u, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

}
