package Entity;

import TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Player extends MapObject{

	//player stuff
	private boolean isLiving;
	private int health;
	private int maxHealth;
	//think of this as any projectile, so bullets, powers, etc.
	private int fire;
	private int maxFire;
	private boolean flinching;
	private long flinchTime;
	
	//lives
	private int deaths;
	//gliding(some kind of flying effect(don't really need, but maybe implement a jetpack?)
	
	private boolean gliding;
	
	//animation stuff
	
	//An arraylist of bufferedImage arrays
	private ArrayList<BufferedImage[]> sprites;
	//each animation has different amount of frames, each corresponds to the array(idle,2|walking,8)
	private final int[] numFrames={2,4};
	//animation actions, used to determine the index of our animations, similar to gamestates
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	
	public Player(TileMap tm) {
		super(tm);
		//for reading in the tilesheet
		width = 32;
		height = 32;
		//real height
		cwidth = 22;
		cheight = 22;
		
		//physics 
		moveSpeed = 0.3;
		maxSpeed = 1.9;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		//speed and jumpstart correspond
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		isLiving = true;
		deaths = 0;
		//load sprites
		try{
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/hekzsheet.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			//now extract each individual animation frame
			//the number of times we run loop is the number of rows in sprite sheet
			for(int i = 0;i<2;i++)
			{
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0;j<numFrames[i];j++)
				{
						bi[j] = spritesheet.getSubimage(j*width, i*height, width, height);
				}
				sprites.add(bi);
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(500);
	}
	public int getDeaths()
	{
		return deaths;
	} 
	public int getHealth(){
		return health;
	}
	public int getMaxHealth()
	{
		return maxHealth;
	}
	public int getFire()
	{
		return fire;
	}
	public int getMaxFire()
	{
		return maxFire;
	}
	//we can stop gliding at any time, so we dont always just set it to true
	public void setGliding(boolean b)
	{
		gliding = b;
	}
	
	private void getNextPosition()
	{
		//movement
		if(left)
		{
			dx-=moveSpeed;
			if(dx< -maxSpeed)
			{
				dx = -maxSpeed;
			}
		}
		else if(right)
		{
			dx+=moveSpeed;
			if(dx>maxSpeed)
			{
				dx=maxSpeed;
			}
		}
		else
		{
			if(dx>0)
			{
				dx-=stopSpeed;
				if(dx<0)
				{
					dx=0;
				}
			}
			else if(dx<0)
			{
				dx+=stopSpeed;
				if(dx>0)
				{
					dx=0;
				}
			}
		}
		//cannot attack while moving, unless in the air
		if(currentAction == SCRATCHING || currentAction ==FIREBALL && !(jumping ||falling))
		{
			dx = 0;
		}
		//jumping
		if(jumping && !falling)
		{
			dy = jumpStart;
			falling = true;
		}
		//falling
		if(falling)
		{
			if(dy>0&&gliding)
			{
				//if gliding, reduce fall speed by a tenth
				dy+=fallSpeed*.10;
			}
			else
			{
				dy+= fallSpeed;
			}
			if(dy>0)
			{
				jumping = false;
			}
			if(dy<0 && !jumping)
			{
				//mario jumping, longer you hold jump, higher you go
				dy+=stopJumpSpeed;
			}
			if(dy>maxFallSpeed)
			{
				dy=maxFallSpeed;
			}
		}
	}
	public void update()
	{
		//update position
		try{
			getNextPosition();
			checkTileMapCollision();
			setPosition(xtemp, ytemp);
		}catch(Exception e)
		{
			isLiving = false;
			deaths++;
		}
		
		//set animation frames, each row in spritesheet corresponds to array of numFrames
		/*
		if(scratching)
		{
			if(currentAction!=SCRATCHING)
			{
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if(firing)
		{
			if(currentAction!=FIREBALL)
			{
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width = 30; 
						
			}
		}
		//falling
		else if(dy>0)
		{
			if(gliding)
			{
				if(currentAction!=GLIDING)
				{
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width =30;
				}
			}
			else if(currentAction!=FALLING)
				{
					currentAction = FALLING;
					animation.setFrames(sprites.get(FALLING));
					animation.setDelay(100);
					width = 30;
				}
		}
		//jumping
		else if(dy<0)
		{
			if(currentAction!=JUMPING)
			{
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				//we set it to -1 because their is only one animation frame
				animation.setDelay(-1);
				width = 30;
			}
		}*/
		//change this back to else if when using all of them
		if(left||right)
		{
			if(currentAction!=WALKING)
			{
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(150);
				width = 32;
			}
		}
		
		else
		{
			if(currentAction!=IDLE)
			{
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 32;
			}
		}
		animation.update();
		//set dirction
		// we dont want character moving while attacking, so check for that
		if(currentAction!=SCRATCHING && currentAction!=FIREBALL)
		{
			if(right)
			{
				facingRight = true;
			}
			if(left)
			{
				facingRight = false;
			}
		}
		 
	}
	public boolean isDead()
	{
		if(isLiving==false)
		{
			return true;
		}
		else return false;
	}
	public void setLiving(Boolean a)
	{
		isLiving=a;
		//when we declare him dead
		if(a==false)
		{
			deaths++;
		}
	}
	public void draw(Graphics2D g)
	{
		//first thing that should be called in any MapObject
		setMapPosition();
		
		//draw player
		if(flinching)
		{
			//figure out how long you've been flinching
			long elapsed = (System.nanoTime() - flinchTime)/1000000;
			//only draw every 100 miliseoonds, so it looks like its blinking
			if(elapsed / 100 % 2 == 0)
			{
				return;
			}
		}
			if(facingRight)
			{
				g.drawImage(animation.getImage(),(int)(x+xmap - width/2),(int)(y+ymap-height/2),null);
			}
			else
			{
				//draw sprite flipped
				g.drawImage(animation.getImage(),(int)(x+xmap - width/2+width),(int)(y+ymap-height/2),-width,height,null);
			}
		}
	}
