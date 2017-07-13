package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class OptionState extends GameState{
	
	private Background bg;
	
	private Color titleColor;
	private Color color;
	private Font titleFont;
	private Font font;
	public OptionState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try{
			
			bg = new Background("/Backgrounds/night_sky_bg.gif",1);
			//background moving to the left at 0.1 pixels
			bg.setVector(-0.1, 0);
			//black
			titleColor = new Color(255,255,255);
			color = new Color(0,0,0);
			titleFont = new Font("pixelmix", Font.PLAIN,12);
			font = new Font("pixelmix", Font.PLAIN, 8);
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
		bg.draw(g);
		g.setFont(titleFont);
		g.setColor(titleColor);
		
		g.drawString("WASD to move your character", 20,40);
		g.drawString("SPACE to Jump", 20, 120);
		g.drawString("SHIFT to Float", 20, 200);
		g.setFont(font);
		g.setColor(color);
		g.drawString("Press ESC to return to the Title Screen",85,230);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_ESCAPE)
		{
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
	

}
