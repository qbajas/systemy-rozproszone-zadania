package main;

import java.util.List;
import java.util.Set;
import models.EventManagerImpl;


import Ice.ObjectPrx;

public class Server {
	

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"EventManagerAdapter", "default -p 10001");
			Ice.Object object = new EventManagerImpl();
			ObjectPrx op = adapter.add(object, ic.stringToIdentity("EventManager"));
			adapter.activate();
			System.out.println("The server started.");
			ic.waitForShutdown();
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
