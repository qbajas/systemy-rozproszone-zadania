package main;

import java.util.List;
import java.util.Set;

import domain.Event;

import ice.DirectoryI;
import ice.DirectoryV2I;
import Ice.ObjectPrx;

public class Server {
	

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"DirectoryAdapter", "default -p 10000");
			Ice.Object object = new DirectoryI();
			ObjectPrx op = adapter.add(object, ic.stringToIdentity("Directory"));
			adapter.addFacet(new DirectoryV2I(), op.ice_getIdentity(), "DirectoryV2");
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
