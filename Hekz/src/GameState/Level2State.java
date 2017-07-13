package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.LavaBlock;
import Entity.Player;
import Entity.Portal;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import Timer.LevelTimer;

public class Level2State extends GameState{
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	private Portal portal;
	
	private int startingX;
	private int startingY;
	
	private LavaBlock lb1;
	private LavaBlock lb2;
	private LavaBlock lb3;
	private LavaBlock lb4;
	private LavaBlock lb5;
	private LavaBlock lb6;
	private LavaBlock lb7;
	private LavaBlock lb8;
	private LavaBlock lb9;
	private LavaBlock lb10;
	private LavaBlock lb11;
	private LavaBlock lb12;
	private LavaBlock lb13;
	private LavaBlock lb14;
	private LavaBlock lb15;
	private Color titleColor;
	private Font titleFont;
	private LevelTimer timer;
	private ArrayList<LavaBlock> lavablocks;
	public Level2State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/blocktileset.gif");
		tileMap.loadMap("/Maps/leveltwo.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		timer = new LevelTimer();
		lavablocks = new ArrayList<LavaBlock>();
		
		bg = new Background("/Backgrounds/night_sky_bg.gif", 0.1);
		bg.setVector(-0.1, 0);
		
		titleColor = new Color(255,255,255);
		titleFont = new Font("pixelmix", Font.PLAIN,14);
		
		startingX = 51;			//players starting positions (Abstraction.... woooo)
		startingY = 325;
		
		player = new Player(tileMap);
		player.setPosition(startingX, startingY);
		
		portal = new Portal(tileMap);
		portal.setPosition(1175,90);
		
		lb1 = new LavaBlock(tileMap);
		lb1.setPosition(225,438);
		lb2 = new LavaBlock(tileMap);
		lb2.setPosition(255,438);
		lb3 = new LavaBlock(tileMap);
		lb3.setPosition(345,438);
		lb4 = new LavaBlock(tileMap);
		lb4.setPosition(375,438);
		lb5 = new LavaBlock(tileMap);
		lb5.setPosition(435,438);
		lb6 = new LavaBlock(tileMap);
		lb6.setPosition(465,438);
		lb7 = new LavaBlock(tileMap);
		lb7.setPosition(495,438);
		lb8 = new LavaBlock(tileMap);
		lb8.setPosition(525,438);
		lb9 = new LavaBlock(tileMap);
		lb9.setPosition(705,438);
		lb10 = new LavaBlock(tileMap);
		lb10.setPosition(765,438);
		lb11 = new LavaBlock(tileMap);
		lb11.setPosition(825,438);
		lb12 = new LavaBlock(tileMap);
		lb12.setPosition(885,438);
		lb13 = new LavaBlock(tileMap);
		lb13.setPosition(945,438);
		lb14 = new LavaBlock(tileMap);
		lb14.setPosition(1005,438);
		lb15 = new LavaBlock(tileMap);
		lb15.setPosition(1035, 438);
		lavablocks.add(lb1);lavablocks.add(lb2);lavablocks.add(lb3);lavablocks.add(lb4);lavablocks.add(lb5);
		lavablocks.add(lb6);lavablocks.add(lb7);lavablocks.add(lb8);lavablocks.add(lb9);lavablocks.add(lb10);
		lavablocks.add(lb11);lavablocks.add(lb12);lavablocks.add(lb13);lavablocks.add(lb14);lavablocks.add(lb15);
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
			player.update();
		}
		if(timer.getSeconds()>=50)
		{
			gsm.setState(GameStateManager.LEVELTWOSTATE);
		}
		for(int i = 0;i<lavablocks.size();i++)
		{
			if(player.intersects(lavablocks.get(i)))
			{
				player.setGliding(true);
				player.setLiving(false);
				player.setGliding(false);
				i=lavablocks.size();
			}
		}
		if(player.intersects(portal))
		{
			gsm.setState(GameStateManager.VICTORYSTATE);
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
		
		for(int i = 0;i<lavablocks.size();i++)
		{
			lavablocks.get(i).draw(g);
		}
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Deaths:"+player.getDeaths(), 200, 20);
		g.drawString(timer.tostring(), 140,20);
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
