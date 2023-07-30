package com.rungame.running;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Constants {
	// Constructor
	GameFrame() throws Exception{
		Board board = new Board();
		this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My Game - 2022");
		setLocationRelativeTo(null);
		setResizable(false);
		add(board);
		setVisible(true);

	}
	
	
	
	// Timer (Internally Thread)

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread());
		
		
		
		GameFrame obj = new GameFrame();
		
	}

}
