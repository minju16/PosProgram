package PosProject;

import javax.swing.*;;

public class Pos_Frame extends JFrame {
	public Pos_Frame() {
		super("Pos Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new Pos_Panel());

		setBounds(350, 0, 1090, 1000);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Pos_Frame();
	}
}