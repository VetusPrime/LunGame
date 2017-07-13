package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Portal extends MapObject{


	
	private ArrayList<BufferedImage[]> sprites;
	
	private final int[] numFrames={1};
	
	private static final int IDLE = 0;
	public Portal(TileMap tm) {
		super(tm);
		
		width = 32;
		height = 32;
		
		cwidth =22;
		cheight = 22;
		try{
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/black_hole.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			//now extract each individual animation frame
			//the number of times we run loop is the number of rows in sprite sheet
			for(int i = 0;i<1;i++)
			{
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0;j<numFrames[i];j++)
				{
						bi[j] = spritesheet.getSubimage(j*width, i*height, width, height);
				}
				sprites.add(bi);
			}		
			animation = new Animation();
			currentAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(500);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void update()
	{
		//update position
			checkTileMapCollision();
			setPosition(xtemp, ytemp);
	}
	public void draw(Graphics2D g)
	{
		setMapPosition();
		g.drawImage(animation.getImage(),(int)(x+xmap - width/2+width),(int)(y+ymap-height/2),-width,height,null);
	}
}
