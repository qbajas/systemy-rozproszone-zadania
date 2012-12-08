package main;


import java.util.List;

import models.CallerImpl;

import utils.Command;

import generated.Event;
import generated.EventManagerPrx;
import generated.EventManagerPrxHelper;
import generated.User;
import interfaces.Caller;

public class Client {
	
	

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("EventManager:default -p 10001");
			EventManagerPrx eventManagerPrx = EventManagerPrxHelper.checkedCast(base);
			if (eventManagerPrx == null)
				throw new Error("Invalid proxy");		
			
			// login
			User user = Command.login();
			// create caller, which calls server in the name of client
			Caller caller = new CallerImpl(eventManagerPrx, user);
			// set caller for command
			Command command = new Command(caller);
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

	


}
