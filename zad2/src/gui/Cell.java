package gui;

import javax.swing.JPanel;

import server.INoteBoard;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;


public class Cell extends JPanel{
	
	private Board board;
	private int number;
	private boolean empty=true;

	public boolean isEmpty() {
		return empty;
	}

	public Cell(final int number, Board b) {
		super();
		this.number = number;
		this.board = b;
	 	addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(empty){
					setBackground(Color.RED);
					
					empty=false;
					INoteBoard nb = board.getNb();
					try {
						nb.appendText(Integer.toString(number));
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					board.checkGameFinished();
				}
			}
		});
	}
	
	/**
	 * 
	 * @return true if move was possible, else false
	 */
	public boolean makeOpponentMove(){
		if(empty){
			setBackground(Color.YELLOW);
			empty=false;
			board.checkGameFinished();
			return true;
		}
		return false;
	}

	
	
}
