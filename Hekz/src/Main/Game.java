package Main;

import javax.swing.JFrame;
public class Game extends JFrame{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public static void main(String[] args)
		{
		JFrame window = new JFrame("Lun");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		}

	}