package client;

import gui.Board;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.Icon;
import javax.swing.JLabel;


public interface INoteBoardListener extends Remote  {

	public void onNewText(String text) throws RemoteException;

	public void notifyNewPlayer(String pseudo) throws RemoteException;
	
	public void setWithOpponent(boolean withOpponent) throws RemoteException;
	
	public void setOpponentAvatar(INoteBoardListener firstListener) throws RemoteException;
	
	public Icon getAvatar() throws RemoteException;
}
