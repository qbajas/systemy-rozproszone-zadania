#include <BuiltinSequences.ice>

module ice
{
	
	interface EventManager
	{			
		string createEvent(string eventName, string eventDesc, int daysFromNow);
		bool subscribe(string eventName);
		sequence<Event> listEvents();
		string modify(string eventName, string eventDesc, int daysFromNow);		
	}
	
};
