package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.INoteBoardListener;
import client.User;

public interface INoteBoard extends Remote {

	public void appendText(String newNote) throws RemoteException;
	
	public boolean register(User user, INoteBoardListener nbl) throws RemoteException;
}
