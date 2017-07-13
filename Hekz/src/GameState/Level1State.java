package GameState;

import Main.GamePanel;
import TileMap.*;
import Timer.LevelTimer;
import Entity.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	private Portal portal;
	private LavaBlock lavaBlock;
	private LavaBlock lavaBlock2;
	private LavaBlock lavaBlock3;
	private Color titleColor;
	private Font titleFont;
	private int startingX;
	private int startingY;
	private ArrayList<LavaBlock> lavablocks;
	private LevelTimer timer;
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/blocktileset.gif");
		tileMap.loadMap("/Maps/levelone.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		lavablocks = new ArrayList<LavaBlock>();
		bg = new Background("/Backgrounds/night_sky_bg.gif", 0.1);
		bg.setVector(-0.1, 0);
		titleColor = new Color(255,255,255);
		titleFont = new Font("pixelmix", Font.PLAIN,14);
		startingX = 80;
		startingY = 350;
		player = new Player(tileMap);
		player.setPosition(startingX, startingY);
		
		portal = new Portal(tileMap);
		portal.setPosition(585,44);
		
		lavaBlock = new LavaBlock(tileMap);
		lavaBlock.setPosition(285, 465);
		
		lavaBlock2 = new LavaBlock(tileMap);
		lavaBlock2.setPosition(315.1,465);
		
		lavaBlock3 = new LavaBlock(tileMap);
		lavaBlock3.setPosition(345.2,465);
		
		lavablocks.add(lavaBlock);
		lavablocks.add(lavaBlock2);
		lavablocks.add(lavaBlock3);
	//	timer = new LevelTimer();
	}
	
	
	public void update() {
		bg.update();
		// update player
		if(player.isDead()==false)
		{
			player.update();
			tileMap.setPosition((GamePanel.WIDTH) / 2 - player.getX(),(GamePanel.HEIGHT)/ 2 - player.getY());
		}
		else
		{
			player.setPosition(startingX, startingY);
			tileMap.setPosition((GamePanel.WIDTH) / 2 - player.getX(),(GamePanel.HEIGHT)/ 2 - player.getY());
			player.setLiving(true);
		}
		/*if(timer.getSeconds()>=50)
		{
			gsm.setState(GameStateManager.LEVEL1STATE);
		}*/
		if(player.intersects(portal))
		{
			gsm.setState(GameStateManager.LEVELTWOSTATE);
		}
		for(int i = 0;i<lavablocks.size();i++)
		{
			if(player.intersects(lavablocks.get(i)))
			{
				player.setGliding(true);			//Looks like he's slowly falling down the lava
				player.setLiving(false);
				player.setGliding(false);			//resets for respawn	
				i=lavablocks.size();
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		portal.draw(g);
		
		lavaBlock.draw(g);
		lavaBlock2.draw(g);
		lavaBlock3.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Deaths:"+player.getDeaths(), 200, 20);
		//g.drawString(timer.tostring(), 140,20);
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setUp(true);
		if(k == KeyEvent.VK_S) player.setDown(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_SHIFT) player.setGliding(true);
		if(k == KeyEvent.VK_ESCAPE)
		{
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setUp(false);
		if(k == KeyEvent.VK_S) player.setDown(false);
		if(k == KeyEvent.VK_SPACE) player.setJumping(false);
		if(k == KeyEvent.VK_SHIFT) player.setGliding(false);
	}
	
}












