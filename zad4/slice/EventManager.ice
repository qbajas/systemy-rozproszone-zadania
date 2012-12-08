module generated
{

	struct User{
		string nick;
	};	

	["java:type:java.util.LinkedList<User>"] sequence<User> users;

	class Event{
		string name;
		string description;
		int daysFromNow;
		User createdBy;
		users subscribedUsers;
	};
	
	["java:type:java.util.LinkedList<Event>"] sequence<Event> events;
	
	interface EventManager
	{			
		string createEvent(string eventName, string eventDesc, int daysFromNow, User u);
		string subscribe(string eventName, User u);
		events listEvents();
		string modify(string eventName, string eventDesc, int daysFromNow, User u);		
	};
	
};
