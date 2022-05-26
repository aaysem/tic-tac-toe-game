import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeGame   {

	JFrame frame = new JFrame();
	JLabel textfield = new JLabel();
	JPanel titlepanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	JButton[] buttons = new JButton[9];
	boolean playerturn = false;
	Random random = new Random();
	JButton theButton = new JButton("Move");
	protected boolean isFull = false;
	String x = "X";
	String o = "O";
	boolean gameOver = false;
	boolean xWins = false;
	boolean oWins = false;
	int returnValue = 0;



	TicTacToeGame(){
		frame.setSize(480, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setTitle("Tic Tac Toe");
		frame.setVisible(true);

		textfield.setBackground(new Color(0,0,0));
		textfield.setForeground(new Color(45, 0, 255));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setFont(new Font("Ink Free", Font.BOLD, 35));
		textfield.setText("welcome");
		textfield.setOpaque(true);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBounds(0, 0, 410, 50);

		theButton.setSize(30, 20);
		theButton.setBounds(411, 8, 40, 25);
		theButton.setBackground(new Color(40, 255, 255));
		theButton.setForeground(new Color(0, 0, 250));
		theButton.setName("Move");

		buttonpanel.setLayout(new GridLayout(3, 3));
		buttonpanel.setBackground(new Color(150, 150, 150));



		for( int i=0; i<9; i++) {

			buttons[i] = new JButton();
			buttonpanel.add(buttons[i]);
			buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 45));
			buttons[i].setFocusable(false);


		}



		titlepanel.add(textfield);
		frame.add(titlepanel, BorderLayout.NORTH);

		frame.add(buttonpanel);


		turn();

		if(((buttons[0].getText()==x)||(buttons[0].getText()==o))&&
		   ((buttons[1].getText()==x)||(buttons[1].getText()==o))&&
		   ((buttons[2].getText()==x)||(buttons[2].getText()==o))&&
		   ((buttons[3].getText()==x)||(buttons[3].getText()==o))&&
		   ((buttons[4].getText()==x)||(buttons[4].getText()==o))&&
		   ((buttons[5].getText()==x)||(buttons[5].getText()==o))&&
		   ((buttons[6].getText()==x)||(buttons[6].getText()==o))&&
		   ((buttons[7].getText()==x)||(buttons[7].getText()==o))&&
		   ((buttons[8].getText()==x)||(buttons[8].getText()==o))) {
			isFull = true;
		}

		do {
		for(int i=0; i<9; i++) {
			final int is = i;
			if(playerturn) {

				buttons[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

							if(e.getSource()==buttons[is]) {
								if(playerturn) {
									if(buttons[is].getText()=="") {

										buttons[is].setForeground(new Color(100, 0, 150));
										buttons[is].setText(x);
										playerturn = false;
										try {
											check();
										} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}


									}
								}
							}


					}
				  });
				}else {

					while(playerturn==false) {
						cpuMove();
					}
				}
		}

		}while(isFull==false);
	}




	public 		void turn() {



		if(textfield.getText() == "welcome") {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			playerturn = true;
			textfield.setText("Your Turn");
		}else{
			playerturn = false;

		}
	}

	public void cpuMove() {
		int r = random.nextInt(8);



		if(buttons[r].getText().equals("")) {

			buttons[r].setForeground(new Color(150, 0, 100));
			buttons[r].setText(o);

			final int final_r = r;
			theButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					buttons[final_r].doClick();
				}


			});

			playerturn = true;

		    	try {
					check();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			textfield.setText("Your Turn");

		}

	}

	public void check() throws InterruptedException {

		for(int t=0; t<7; t+=3) {

			if((buttons[t].getText().equals(buttons[t+1].getText()))&&(buttons[t].getText().equals(buttons[t+2].getText()))) {
				if((buttons[t].getText().equals(x))) {
				  returnValue =	JOptionPane.showConfirmDialog(frame, null, "You win! Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					xWins = true;
					if(returnValue ==JOptionPane.YES_OPTION) {
						new Main().join();
						new Main().start();
					}else {
						System.exit(0);
					}

				}else if((buttons[t].getText().equals(o))) {
				  returnValue = JOptionPane.showConfirmDialog(frame, null, "Cpu wins:( Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					oWins = true;
					if(returnValue ==JOptionPane.YES_OPTION) {
						new Main().join();
						new Main().start();
					}else {
						System.exit(0);
					}
			    }
			}
		}

		for(int t1=0; t1<3; t1++) {
			if((buttons[t1].getText().equals(buttons[t1+3].getText()))&&(buttons[t1].getText().equals(buttons[t1+6].getText()))) {
				if((buttons[t1].getText().equals(x))) {
				  returnValue =	JOptionPane.showConfirmDialog(frame, null, "You win! Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					xWins = true;
					if(returnValue ==JOptionPane.YES_OPTION) {
						new Main().join();
						new Main().start();
					}else {
						System.exit(0);
					}
				}else if((buttons[t1].getText().equals(o))) {
				  returnValue =	JOptionPane.showConfirmDialog(frame, null, "Cpu wins:( Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					oWins = true;
					if(returnValue ==JOptionPane.YES_OPTION) {
						new Main().join();
						new Main().start();
					}else {
						System.exit(0);
					}
				}
			}
		}

		if((buttons[0].getText().equals(buttons[4].getText()))&&(buttons[0].getText().equals(buttons[8].getText()))) {
			if((buttons[0].getText().equals(x))) {
			  returnValue =	JOptionPane.showConfirmDialog(frame, null, "You win! Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				xWins = true;
				if(returnValue ==JOptionPane.YES_OPTION) {
					new Main().join();
					new Main().start();
				}else {
					System.exit(0);
				}
			}else if((buttons[0].getText().equals(o))) {
			  returnValue =	JOptionPane.showConfirmDialog(frame, null, "Cpu wins:( Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				oWins = true;
				if(returnValue ==JOptionPane.YES_OPTION) {
					new Main().join();
					new Main().start();
				}else {
					System.exit(0);
				}
			}
		}

		if((buttons[2].getText().equals(buttons[4].getText()))&&(buttons[2].getText().equals(buttons[6].getText()))) {
			if((buttons[2].getText().equals(x))) {
			  returnValue =	JOptionPane.showConfirmDialog(frame, null, "You win! Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				xWins = true;
				if(returnValue ==JOptionPane.YES_OPTION) {
					new Main().join();
					new Main().start();
				}else {
					System.exit(0);
				}
			}else if((buttons[2].getText().equals(o))) {
			  returnValue =	JOptionPane.showConfirmDialog(frame, null, "Cpu wins:( Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				oWins = true;
				if(returnValue ==JOptionPane.YES_OPTION) {
					new Main().join();
					new Main().start();
				}else {
					System.exit(0);
				}
			}
		}

		if(((buttons[0].getText()==x)||(buttons[0].getText()==o))&&((buttons[1].getText()==x)||(buttons[1].getText()==o))&&((buttons[2].getText()==x)||(buttons[2].getText()==o))&&((buttons[3].getText()==x)||(buttons[3].getText()==o))&&((buttons[4].getText()==x)||(buttons[4].getText()==o))&&((buttons[5].getText()==x)||(buttons[5].getText()==o))&&((buttons[6].getText()==x)||(buttons[6].getText()==o))&&((buttons[7].getText()==x)||(buttons[7].getText()==o))&&((buttons[8].getText()==x)||(buttons[8].getText()==o))) {
			isFull = true;
		}

		while(isFull) {
			if((xWins==false)&&(oWins==false)) {
				returnValue =	JOptionPane.showConfirmDialog(frame, null, "Draw! Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

				}
			if(returnValue ==JOptionPane.YES_OPTION) {
				new Main().join();
				new Main().start();
			}else {
				System.exit(0);

			}

			break;

		}







	}






}
