package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class DeadState extends GameState{
	
	private Background bg;
	
	private boolean isPressed;
	public DeadState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		isPressed = false;
		try{
			bg = new Background("/Backgrounds/GameOverBg.png",1);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
	}
	

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	
	
}
