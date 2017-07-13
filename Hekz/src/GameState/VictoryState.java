package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class VictoryState extends GameState{
	
	private Background bg;
	private Font titleFont;
	private Font font;
	
	private Color titleColor;
	private Color color;
	private boolean isPressed;
	public VictoryState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		bg = new Background("/Backgrounds/night_sky_bg.gif", 0.1);
		bg.setVector(-0.1, 0);
		titleFont = new Font("pixelmix", Font.PLAIN,28);
		font = new Font("pixelmix", Font.PLAIN,8);
		
		titleColor = new Color(255,255,255);
		color = new Color(0,0,0);
		
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		isPressed = false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
		if(isPressed==true)
		{
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_ENTER)
		{
			isPressed = true;
		}
		
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		g.setFont(titleFont);
		g.setColor(titleColor);
		g.drawString("CONGLATURATION!", 20,40);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString("Thanks for playing! Created by Anthony Fortuna", 40, 120);
	
	}
	

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	
	
}
