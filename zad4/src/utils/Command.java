package utils;



import interfaces.ClientInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Command {

	private ClientInterface client;

	public Command(ClientInterface client) {
		super();
		this.client = client;
	}

	public void start() throws IOException {
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
//		if(subcommands[0].equals("subscribe")){
//			client.subscribe(subcommands[1]);
//			return;
//		}
		System.out.println("Wrong command !");
		printCommands();
	}



	public void printCommands() {
		System.out.println("Available commands: ");
		System.out.println("  create EVENT_NAME EVENT_DESCRIPTION DAYS_FROM_NOW");
		System.out.println("  subscribe EVENT_NAME");
		System.out.println("  list");
		System.out.println("  modify EVENT_NAME EVENT_DESCRIPTION DAYS_FROM_NOW");
	}

}
