package server;

import java.rmi.RemoteException;

import client.INoteBoardListener;
import client.User;

public class Game {
	
	private boolean withOpponent;
	
	private User firstUser;
	private INoteBoardListener firstListener;
	
	private User secondUser;
	private INoteBoardListener secondListener;
	
	
	
	public boolean isWithOpponent() {
		return withOpponent;
	}
	public void setWithOpponent(boolean withOpponent) {
		this.withOpponent = withOpponent;
	}
	public User getFirstUser() {
		return firstUser;
	}
	public void setFirstUser(User firstUser) {
		this.firstUser = firstUser;
	}
	public INoteBoardListener getFirstListener() {
		return firstListener;
	}
	public void setFirstListener(INoteBoardListener firstListener) {
		this.firstListener = firstListener;
	}
	public User getSecondUser() {
		return secondUser;
	}
	public void setSecondUser(User secondUser) {
		this.secondUser = secondUser;
	}
	public INoteBoardListener getSecondListener() {
		return secondListener;
	}
	public void setSecondListener(INoteBoardListener secondListener) {
		this.secondListener = secondListener;
	}
	
	
	public void setOpponentName(){
		try {
			if(secondListener!=null){
				firstListener.notifyNewPlayer(secondUser.getPseudo());			
				secondListener.notifyNewPlayer(firstUser.getPseudo());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
