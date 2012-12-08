package models;

import java.util.List;

import Ice.Current;
import generated.Event;
import generated._EventManagerDisp;

public class EventManagerI extends _EventManagerDisp {

	@Override
	public String createEvent(String eventName, String eventDesc,
			int daysFromNow, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean subscribe(String eventName, Current __current) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String modify(String eventName, String eventDesc, int daysFromNow,
			Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> listEvents(Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

}
