package client;

import gui.Board;

import java.rmi.RemoteException;

public class NoteBoardListenerImpl implements INoteBoardListener {

	private Board board;
	private boolean withOpponent;

	public NoteBoardListenerImpl(Board board, boolean withOpponent) {
		super();
		this.board = board;
		this.withOpponent = withOpponent;
	}

	@Override
	public void onNewText(String text) throws RemoteException {
		board.makeMove(text);
		if (!withOpponent) {

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

}
