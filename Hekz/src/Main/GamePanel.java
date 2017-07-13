package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	//game thread
	private Thread thread;
	
	private boolean running;
	
	private int FPS = 60;
	
	private long targetTime = 1000/FPS;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
	private GameStateManager gsm;
	
	public GamePanel()
	{
		super();
		super.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		super.setFocusable(true);
		super.requestFocus();
	}
	public void addNotify()
	{
		//game is done running
		super.addNotify();
		if(thread==null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		gsm.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		gsm.keyReleased(key.getKeyCode());
	}
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
	
	public void init()
	{
		//declares variables at start of game
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D)image.getGraphics();
		//lets the computer know the game is now ready to start
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void update()
	{
		gsm.update();
	}
	public void drawToScreen()
	{
		//Game Panel's graphics object
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		//gets rid of graphics when you are done using to prevent a memory leak
		g2.dispose();
	}
	public void draw()
	{
		gsm.draw(g);
	}
	public int getFPS()
	{
		return FPS;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		
		float start;
		float elapsed;
		float wait;
		//game loop
		while(running)
		{
			start = System.nanoTime();
			update();
			drawToScreen();
			draw();
			elapsed = System.nanoTime() - start;
			//targetTime is in milliseconds and elapsed is in nanoseconds, hence the divison by 1m
			wait = targetTime - elapsed/10000000;
			if(wait <0)
			{
				wait=5;
			}
			try{
				Thread.sleep((long) wait);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
