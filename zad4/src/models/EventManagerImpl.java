package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Ice.Current;
import generated.Event;
import generated.User;
import generated._EventManagerDisp;

public class EventManagerImpl extends _EventManagerDisp {

	Map<Integer, Event> events;
	static int lastId = 1; // TODO synchronize (read write lock, readers-writers problem), AtomicInteger

	public EventManagerImpl() {
		events = new TreeMap<Integer, Event>();
	}

	@Override
	public Map<Integer, Event> listEvents(Current __current) {
		return events;
	}

	@Override
	public String createEvent(String eventName, String eventDesc, User u,
			Current __current) {
		int id = generateId();
		Event event = new Event(eventName, eventDesc, u, new LinkedList<User>());
		events.put(id, event);
		return null;
	}

	@Override
	public String subscribe(int eventId, User u, Current __current) {
		Event event = events.get(eventId);
		if (event == null) {
			return "There is no such event!";
		}
		if (event.subscribedUsers.contains(u)) {
			return "You already subscribed to this event!";
		}
		event.subscribedUsers.add(u);
		return null;
	}

	@Override
	public String modify(int eventId, String eventName, String eventDesc,
			User u, Current __current) {
		Event event = events.get(eventId);
		if (event == null) {
			return "There is no such event!";
		}
		if (!event.createdBy.equals(u)) {
			return "You do not own this event - You cannot modify it!";
		}
		event.name = eventName;
		event.description = eventDesc;
		return null;
	}

	private static synchronized int generateId() {
		return lastId++;
	}

	@Override
	public String delete(int eventId, User u, Current __current) {
		Event event = events.get(eventId);
		if (event == null) {
			return "There is no such event!";
		}
		if (!event.createdBy.equals(u)) {
			return "You do not own this event - You cannot delete it!";
		}
		events.remove(eventId);
		return null;
	}

}
