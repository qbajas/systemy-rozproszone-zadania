package main;

import interfaces.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.TextMessage;

public class Command {

	private Client client;

	public Command(Client client) {
		super();
		this.client = client;
	}

	public void start() throws IOException {
		printTopics();
		printCommands();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Type a command: ");
			String msg = br.readLine();
			interpret(msg);
		}
	}

	public void interpret(String command) {
		String[] subcommands = command.split(" ");
//		ifs instead of switch to comply with java 6 :(
		if(subcommands[0].equals("subscribe")){
			client.subscribe(subcommands[1]);
			return;
		}
		if(subcommands[0].equals("publish")){
			client.publish(subcommands[1],subcommands[2],subcommands[3],subcommands[4],subcommands[5]);
			return;
		}
		if(subcommands[0].equals("bid")){
			client.bid(subcommands[1],subcommands[2],subcommands[3]);
			return;
		}
		System.out.println("Wrong command !");
		printCommands();
	}

	public void printTopics() {
		System.out.println("Available categories: ");
		for (int i = 0; i < client.getTopics().length; i++) {
			System.out.println("  " + client.getTopics()[i]);
		}
	}

	public void printCommands() {
		System.out.println("Available commands: ");
		System.out.println("  subscribe CATEGORY_NAME");
		System.out.println("  publish CATEGORY_NAME AUCTION_NAME STARTING_PRICE DESCRIPTION SECONDS_TO_END");
		System.out.println("  bid CATEGORY_NAME AUCTION_NAME PRICE");
	}

}
