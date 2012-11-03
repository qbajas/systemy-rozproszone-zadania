package client;

import gui.Board;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INoteBoardListener extends Remote  {

	public void onNewText(String text) throws RemoteException;

	public void notifyNewPlayer(String pseudo) throws RemoteException;
	
	
}
