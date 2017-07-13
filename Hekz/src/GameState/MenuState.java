package GameState;

import TileMap.Background;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	
	//array of the possible menu screens or "states"
	private String[] options = {"Start","Help","Controls","Quit",};
	
	private Color titleColor;
	
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try{
			
			bg = new Background("/Backgrounds/night_sky_bg.gif",1);
			//background moving to the left at 0.1 pixels
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(255,255,255);
			titleFont = new Font("pixelmix", Font.PLAIN,28);
			
			font = new Font("pixelmix", Font.PLAIN, 12);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		//draw background
		bg.draw(g);
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Lun", 115, 70);
		
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Created by: Vetus Prime", 100, 220);
		
		//draw menu options
		g.setFont(font);
		for(int i =0;i<options.length;i++)
		{
			if(i == currentChoice)
			{
				g.setColor(Color.BLACK);
				
			}
			else
			{
				g.setColor(Color.WHITE);
			}
			//draws menu items in cascading order
			g.drawString(options[i],  125, 120+i*15);
			
		}
	}
	//selects a menu option
	private void select()
	{
		if(currentChoice ==0)
		{
			//start
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if(currentChoice==1)
		{
			//help
			gsm.setState(GameStateManager.HELPSTATE);
		}
		if(currentChoice==2)
		{
			//options
			gsm.setState(GameStateManager.CONTROLSTATE);
		}
		if(currentChoice==3)
		{
			//quit(closes screen)
			System.exit(0);
		}
	}
	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		if(k == KeyEvent.VK_UP)
		{
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentChoice--;
			if(currentChoice==-1)
			{
				currentChoice = options.length-1;
			}
		}
		if(k == KeyEvent.VK_DOWN)
		{
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentChoice++;
			if(currentChoice==options.length)
			{
				currentChoice =0;
			}
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
	

}
