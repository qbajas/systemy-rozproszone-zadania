package server;

import gui.Board;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.INoteBoardListener;
import client.User;

public interface INoteBoard extends Remote {

	public void appendText(String newNote, User user) throws RemoteException;
	
	public boolean register(User user, INoteBoardListener nbl, boolean withOpponent) throws RemoteException;
}
