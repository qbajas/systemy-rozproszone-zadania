package client;

import gui.Board;

import java.rmi.RemoteException;

import javax.swing.Icon;
import javax.swing.JLabel;

public class NoteBoardListenerImpl implements INoteBoardListener {

	private Board board;
	private boolean withOpponent = true;

	public NoteBoardListenerImpl(Board b) {
		super();
		this.board = b;
	}

	public boolean isWithOpponent() {
		return withOpponent;
	}

	public void setWithOpponent(boolean withOpponent) {
		this.withOpponent = withOpponent;
	}

	@Override
	public void onNewText(String text) throws RemoteException {
		if (withOpponent) {
			System.out.println("Making opponent move");
			board.makeMove(text);
		} else {

			System.out.println("Making computer move");
			(new Thread() {
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					board.makeComputerMove();
				}
			}).start();
		}
	}

	@Override
	public void notifyNewPlayer(String pseudo) {
		board.setOpponent(pseudo);
	}

	@Override
	public void setOpponentAvatar(INoteBoardListener firstListener)
			throws RemoteException {
		board.setOpponentAvatar(firstListener.getAvatar());		
	}

	@Override
	public Icon getAvatar() throws RemoteException {
		return board.getMyAvatar().getIcon();
	}

}
