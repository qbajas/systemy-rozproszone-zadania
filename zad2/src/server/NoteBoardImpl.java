package server;

import gui.Board;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import client.INoteBoardListener;
import client.User;

public class NoteBoardImpl implements INoteBoard {

	private Set<User> userList = new LinkedHashSet<User>();
	private List<Game> games;

	public NoteBoardImpl() {
		games = new LinkedList<>();
	}

	public synchronized void appendText(String newNote, User user)
			throws RemoteException {
		INoteBoardListener listener = findOpponentListener(user);
		if (listener != null) {
			listener.onNewText(newNote);
		}else{
			System.out.println("Listener not found");
		}
	}

	@Override
	public synchronized boolean register(User user, INoteBoardListener nbl,
			boolean withOpponent) throws RemoteException {
		if (userList.add(user)) {

			Game g = addToAvailableGame(user, nbl, withOpponent);
			g.setOpponentName();

			return true;
		}
		return false;
	}

	
	
	private Game addToAvailableGame(User user, INoteBoardListener nbl, 
			boolean withOpponent) {

		Game g;
		try {
			nbl.setWithOpponent(withOpponent);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (withOpponent) {

			if (games.size() == 0
					|| games.get(games.size() - 1).getSecondUser() != null) {
				g = new Game();
				g.setFirstUser(user);
				g.setFirstListener(nbl);

			} else {
				g = games.get(games.size() - 1);
				g.setSecondUser(user);
				g.setSecondListener(nbl);
			}

		} else {			
			g = new Game();
			g.setFirstUser(user);
			g.setFirstListener(nbl);
			g.setSecondUser(new User("Computer"));
			g.setSecondListener(nbl);
		}
		games.add(g);
		return g;
	}
	
	

	private INoteBoardListener findOpponentListener(User user) {
		for (Game g : games) {
			if (g.getFirstUser().equals(user)) {
				return g.getSecondListener();
			}
			if (g.getSecondUser()!=null && g.getSecondUser().equals(user)) {
				return g.getFirstListener();
			}
		}
		return null;
	}
}
