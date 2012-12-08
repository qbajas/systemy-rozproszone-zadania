package main;

import java.util.List;
import java.util.Set;
import models.EventManagerI;


import Ice.ObjectPrx;

public class Server {
	

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"EventManagerAdapter", "default -p 10001");
			Ice.Object object = new EventManagerI();
			ObjectPrx op = adapter.add(object, ic.stringToIdentity("EventManager"));
//			adapter.addFacet(new DirectoryV2I(), op.ice_getIdentity(), "DirectoryV2");
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
