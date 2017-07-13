package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class HelpState extends GameState{
	
	private Background bg;
	
	private Font titleFont;
	private Font font;
	
	private Color titleColor;
	private Color color;
	public HelpState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try{
			
			bg = new Background("/Backgrounds/night_sky_bg.gif",1);
			bg.setVector(0.1,0);
			
			titleColor = new Color(255,255,255);
			color = new Color(0,0,0);
			titleFont = new Font("pixelmix",Font.PLAIN,8);
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
		g.setColor(titleColor);
		g.setFont(titleFont);
		
		g.drawString("Jump and Glide your way through each level", 40, 40);
		g.drawString("in 50 seconds or less!", 100,60);
		g.setFont(font);
		g.setColor(color);
		g.drawString("Press ESC to return to the Title Screen", 85, 230);
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
