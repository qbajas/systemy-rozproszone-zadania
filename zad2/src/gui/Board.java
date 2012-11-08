package gui;

import java.awt.EventQueue;
import java.awt.Label;
import java.rmi.Remote;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import server.INoteBoard;
import client.FileChooser;

import client.NoteBoardClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class Board implements Remote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JLabel opponentName;
	
	private JLabel myAvatar;
	private JLabel opponentAvatar;
	


	private INoteBoard nb;
	private String username;
	
	private List<Cell> cells;

	
	
	public void setOpponentAvatar(Icon opponentAvatar) {
		this.opponentAvatar.setIcon(opponentAvatar);
	}

	public String getUsername() {
		return username;
	}

	public INoteBoard getNb() {
		return nb;
	}

	public void setNb(INoteBoard nb) {
		this.nb = nb;
	}
	
	public JLabel getMyAvatar() {
		return myAvatar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board window = new Board();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Board() {
		cells = new ArrayList<Cell>(10);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 492, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final Cell panel = new Cell(1,this);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setBackground(Color.RED);
			}
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(45, 26, 106, 90);
		frame.getContentPane().add(panel);
		cells.add(0, panel);
		cells.add(1, panel);
		
		Cell cell = new Cell(2,this);
		cell.setBackground(Color.WHITE);
		cell.setBounds(173, 26, 106, 90);
		frame.getContentPane().add(cell);
		cells.add(2, cell);
		
		Cell cell_1 = new Cell(3,this);
		cell_1.setBackground(Color.WHITE);
		cell_1.setBounds(301, 26, 106, 90);
		frame.getContentPane().add(cell_1);
		cells.add(3, cell_1);
		
		Cell cell_2 = new Cell(4,this);
		cell_2.setBackground(Color.WHITE);
		cell_2.setBounds(45, 127, 106, 90);
		frame.getContentPane().add(cell_2);
		cells.add(4, cell_2);
		
		Cell cell_3 = new Cell(5,this);
		cell_3.setBackground(Color.WHITE);
		cell_3.setBounds(173, 127, 106, 90);
		frame.getContentPane().add(cell_3);
		cells.add(5, cell_3);
		
		Cell cell_4 = new Cell(6,this);
		cell_4.setBackground(Color.WHITE);
		cell_4.setBounds(301, 127, 106, 90);
		frame.getContentPane().add(cell_4);
		cells.add(6, cell_4);
		
		Cell cell_5 = new Cell(7,this);
		cell_5.setBackground(Color.WHITE);
		cell_5.setBounds(45, 224, 106, 90);
		frame.getContentPane().add(cell_5);
		cells.add(7, cell_5);
		
		Cell cell_6 = new Cell(8,this);
		cell_6.setBackground(Color.WHITE);
		cell_6.setBounds(173, 224, 106, 90);
		frame.getContentPane().add(cell_6);
		cells.add(8, cell_6);
		
		Cell cell_7 = new Cell(9,this);
		cell_7.setBackground(Color.WHITE);
		cell_7.setBounds(301, 224, 106, 90);
		frame.getContentPane().add(cell_7);
		cells.add(9, cell_7);
		
		textField = new JTextField();
		textField.setBounds(77, 325, 137, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JLabel lblYourNick = new JLabel("Your nick:");
		lblYourNick.setBounds(10, 325, 86, 14);
		frame.getContentPane().add(lblYourNick);
		
		final Board me = this;
		JButton btnStartTheGame = new JButton("Join the game with opponent");
		btnStartTheGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username = textField.getText();
				NoteBoardClient.start(username,me,true);
			}
		});
		btnStartTheGame.setBounds(45, 475, 362, 23);
		frame.getContentPane().add(btnStartTheGame);
		
		JLabel lblYourOpponentIs = new JLabel("Your opponent is:");
		lblYourOpponentIs.setBounds(224, 325, 137, 14);
		frame.getContentPane().add(lblYourOpponentIs);
		
		opponentName = new JLabel("");
		opponentName.setFont(new Font("Tahoma", Font.BOLD, 11));
		opponentName.setBounds(341, 325, 125, 14);
		frame.getContentPane().add(opponentName);
		
		JButton btnStartTheGame_1 = new JButton("Start the game with computer");
		btnStartTheGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username = textField.getText();
				NoteBoardClient.start(username,me,false);
				opponentName.setText("Computer");
			}
		});
		btnStartTheGame_1.setBounds(45, 509, 362, 23);
		frame.getContentPane().add(btnStartTheGame_1);
		
		JPanel opponentAvatarPanel = new JPanel();
		opponentAvatarPanel.setBackground(Color.WHITE);
		opponentAvatarPanel.setBounds(301, 360, 106, 90);
		frame.getContentPane().add(opponentAvatarPanel);
		
		opponentAvatar = new JLabel("");
		opponentAvatar.setBackground(Color.WHITE);
		opponentAvatarPanel.add(opponentAvatar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 356, 106, 90);
		frame.getContentPane().add(panel_1);
		
		myAvatar = new JLabel("");
		panel_1.add(myAvatar);
		myAvatar.setBackground(Color.WHITE);
		
		JButton btnLoadAvatar = new JButton("<html>Load<br> avatar</html>");
		btnLoadAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser fc = new FileChooser();
				File f = fc.LoadFile();

				try {
					BufferedImage loadedPicture = ImageIO.read(f);
					myAvatar.setIcon(new ImageIcon(loadedPicture));
					System.out.println("Image " + f.getName() + " loaded.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Image not recognized");
				}
			}
		});
		btnLoadAvatar.setBounds(125, 389, 65, 61);
		frame.getContentPane().add(btnLoadAvatar);
		

		

		

	}

	public boolean makeMove(String text) {		
		return cells.get(Integer.parseInt(text)).makeOpponentMove();
	}

	public void setOpponent(String pseudo) {
		if (!pseudo.equals(username)){
			opponentName.setText(pseudo);
			System.out.println("setting opponent " + pseudo);
		}		
	}
	
	public void checkGameFinished(){
		if(isGameFinished()){
			 JOptionPane.showMessageDialog(frame, "The end of the game!");
		}		
	}
	
	private boolean isGameFinished(){
		boolean finished = true;
		for(Cell c : cells){
			if(c.isEmpty()){
				finished=false;
				break;
			}
		}
		return finished;
	}

	public void makeComputerMove() {
		for(int i=1;i<=9;i++){
			if(makeMove(Integer.toString(i))){
				return;
			}
		}
	}
}
