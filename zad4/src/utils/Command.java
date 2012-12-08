package utils;

import generated.EventManager;
import generated.EventManagerPrx;
import generated.User;
import interfaces.ClientInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import models.EventManagerI;

public class Command {

	private ClientInterface client;
	private User user;

	public Command(ClientInterface client) {
		super();
		this.client = client;
	}

	public void start() throws IOException {
		login();
		printCommands();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Type a command: ");
			String msg = br.readLine();
			interpret(msg);
		}
	}
	

	private void login() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your name: ");
		String msg = br.readLine();
		user = new User(msg);
	}

	private void interpret(String command) {
		String[] subcommands = command.split(" ");
		if (subcommands[0].equals("list")) {
			client.listEvents();
			return;
		}
		System.out.println("Wrong command !");
		printCommands();
	}

	private void printCommands() {
		System.out.println("Available commands: ");
		System.out.println("  create EVENT_NAME EVENT_DESCRIPTION DAYS_FROM_NOW");
		System.out.println("  subscribe EVENT_NAME");
		System.out.println("  list");
		System.out.println("  modify EVENT_NAME EVENT_DESCRIPTION DAYS_FROM_NOW");
	}

}
