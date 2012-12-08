package interfaces;

public interface Caller {

	void listEvents();

	void createEvent(String eventName, String eventDesc, String daysFromNow);

}
