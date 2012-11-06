package client;

import gui.Board;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import server.INoteBoard;

public class NoteBoardClient {
	
	static INoteBoardListener nbli;

	public static void start(String login, Board b, boolean withOpponent) {
		try {
			nbli = new NoteBoardListenerImpl(b);
			UnicastRemoteObject.exportObject(nbli, 0);
			INoteBoard nb = (INoteBoard) Naming.lookup("rmi://127.0.0.1:1099/note");
			System.out.println("Lookup OK");
			System.out.println("Dodajemy tekst");
			User u = new User(login);
			if(nb.register(u, nbli, withOpponent)){
				System.out.println("Udana rejestracja klienta "+login);
				b.setNb(nb);		
			}else{
				System.out.println("Nieudana rejestracja klienta "+login);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
