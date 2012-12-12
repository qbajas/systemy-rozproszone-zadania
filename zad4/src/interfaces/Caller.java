package interfaces;

public interface Caller {

	void listEvents();

	void createEvent(String eventName, String eventDesc);

	void subscribe(String eventId);

	void modify(String eventId, String eventName, String eventDesc);

	void delete(String eventId);

}
