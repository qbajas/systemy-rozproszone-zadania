package utils;

import generated.EventManager;
import generated.EventManagerPrx;
import generated.User;
import interfaces.Caller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import models.EventManagerImpl;

public class Command {

	private Caller caller;

	public Command(Caller client) {
		super();
		this.caller = client;
	}

	public void start() throws IOException {
		printCommands();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println();
			System.out.println("Type a command: ");
			String msg = br.readLine();
			interpret(msg);
		}
	}
	
	public static User login() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your name: ");
		String msg = br.readLine();
		return new User(msg);
	}

	
	private void interpret(String command) {
		String[] subcommands = command.split(" ");
		if (subcommands[0].equals("list")) {
			caller.listEvents();
			return;
		}
		if (subcommands[0].equals("create")) {
			caller.createEvent(subcommands[1],subcommands[2]);
			return;
		}
		if (subcommands[0].equals("subscribe")) {
			caller.subscribe(subcommands[1]);
			return;
		}
		if (subcommands[0].equals("modify")) {
			caller.modify(subcommands[1],subcommands[2],subcommands[3]);
			return;
		}
		if (subcommands[0].equals("delete")) {
			caller.delete(subcommands[1]);
			return;
		}
		System.out.println("Wrong command !");
		printCommands();
	}

	private void printCommands() {
		System.out.println("Available commands: ");
		System.out.println("  create EVENT_NAME EVENT_DESCRIPTION");
		System.out.println("  subscribe EVENT_NUMBER");
		System.out.println("  list");
		System.out.println("  modify EVENT_NUMBER EVENT_NAME EVENT_DESCRIPTION");
		System.out.println("  delete EVENT_NUMBER");
	}


}
