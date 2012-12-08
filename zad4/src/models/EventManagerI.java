package models;

import java.util.List;

import Ice.Current;
import generated.Event;
import generated.User;
import generated._EventManagerDisp;

public class EventManagerI extends _EventManagerDisp {
	
	List<Event> events;
	
	@Override
	public List<Event> listEvents(Current __current) {
		System.out.println("Listing events for client " + __current.toString());
		return events;
	}

	@Override
	public String createEvent(String eventName, String eventDesc,
			int daysFromNow, User u, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean subscribe(String eventName, User u, Current __current) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String modify(String eventName, String eventDesc, int daysFromNow,
			User u, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

}
