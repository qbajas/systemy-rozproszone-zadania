package main;


import java.util.List;

import utils.Command;

import generated.Event;
import generated.EventManagerPrx;
import generated.EventManagerPrxHelper;
import interfaces.ClientInterface;

public class Client implements ClientInterface {
	
	EventManagerPrx eventManagerPrx;

	public Client(EventManagerPrx eventManagerPrx) {
		this.eventManagerPrx = eventManagerPrx;		
	}
	

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("EventManager:default -p 10001");
			EventManagerPrx eventManagerPrx = EventManagerPrxHelper.checkedCast(base);
			if (eventManagerPrx == null)
				throw new Error("Invalid proxy");
		
			Command command = new Command(new Client(eventManagerPrx));
			command.start();
			
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}				
		
		if (ic != null) {
			try {
				ic.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

	
	@Override
	public void listEvents() {
		System.out.println("Events in the system: ");
		for(Event event:eventManagerPrx.listEvents()){
			System.out.println(event.toString());
		}		
	}

}
