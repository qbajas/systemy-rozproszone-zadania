package server;
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
	private List<INoteBoardListener> listenerList = new LinkedList<INoteBoardListener>();


	
	public synchronized void appendText(String newNote) throws RemoteException {
		for(INoteBoardListener listener : listenerList){
			listener.onNewText(newNote);
		}
	}

	@Override
	public synchronized boolean register(User user, INoteBoardListener nbl)
			throws RemoteException {
		if(userList.add(user)){
			listenerList.add(nbl);
			for(INoteBoardListener listener : listenerList){
				listener.notifyNewPlayer(user.getPseudo());
			}
			for(User u : userList){
				nbl.notifyNewPlayer(u.getPseudo());
			}
			return true;
		}
		return false;
	}
}
