package main;


import java.util.List;

import generated.Event;
import generated.EventManagerPrx;
import generated.EventManagerPrxHelper;
import interfaces.ClientInterface;

public class Client implements ClientInterface {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("EventManager:default -p 10000");
			EventManagerPrx eventManagerPrx = EventManagerPrxHelper.checkedCast(base);
//			ice.DirectoryV2Prx dirPrxV2 = DirectoryV2PrxHelper.checkedCast(dirPrx, "DirectoryV2");
			
			if (eventManagerPrx == null)
				throw new Error("Invalid proxy");

			List<Event> events = eventManagerPrx.listEvents();
			
			System.out.println("List of events: ");
			for(Event event:events){
				System.out.println(event.name);
			}
			
			
//			entries = dirPrxV2.listFiles("/");
//			System.out.println("-------- FILES ONLY : ------------");
//			for (String entry : entries)
//				System.out.println(entry);
//			
			
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

}
