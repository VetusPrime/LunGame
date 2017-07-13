package TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

import java.io.*;
public class TileMap {


	//position
	private double x;
	private double y;
	
	//bounds 
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	//for smooth camera scrolling
	private double tween;
	
	//map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	//drawing only want to draw tiles that are on screen
	//tells what row to start drawing
	private int rowOffset;
	//tells what columns to start drawing
	private int colOffset;
	//how many rows to draw
	private int numRowsToDraw;
	//hpow many cols to draw
	private int numColsToDraw;
	
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		//number of rows to draw is height/tileSize add two to pad the window
		numRowsToDraw = (GamePanel.HEIGHT /tileSize) +2;
		numColsToDraw = (GamePanel.WIDTH / tileSize) +2;
		//smooth camera movement(beta)
		tween = 0.07;
		
	}
	public int getNumRows(){
		return numRows;
	}
	public int getNumCols()
	{
		return numCols;
	}
	//loads tileset into memory
	public void loadTiles(String s)
	{
		try
		{
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			//2 rows and numTilesAcross columns
			//update when you add a row to spritesheet
			tiles = new Tile[2][numTilesAcross];
			
			
			BufferedImage subimage;
			//for every col and row
			for(int col = 0;col <numTilesAcross;col++)
			{
				
				subimage = tileset.getSubimage(col*tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				
				subimage = tileset.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//loads map into memory
	public void loadMap(String s)
	{
		//first line is number of cols
		//second line is number of rows
		
		try
		{
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//first line is cols
			numCols = Integer.parseInt(br.readLine());
			//second line is rows
			numRows = Integer.parseInt(br.readLine());
			//rest of map file is just the map
			
			map = new int[numRows][numCols];
			//width in pixels
			width = numCols*tileSize;
			// height in pixels
			height = numRows*tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			//start reading in map
			
			//white space
			String delims = "\\s+";
			
			for(int row = 0;row<numRows;row++)
			{
				String line = br.readLine();
				String[] tokens  = line.split(delims);
				for(int col = 0;col<numCols;col++)
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int getTileSize()
	{
		return tileSize;
	}
	public int getX()
	{
		return (int)x;
		
	}
	public int getY()
	{
		return (int)y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public int getType(int row, int col)                                                                                              
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	public void setTween(double d)
	{
		tween = d;
	}
	public void setPosition(double x, double y)          
	{
		/*makes sure camera follows player exactly
		this.x= x;
		this.y= y;*/
		//makes sure camera follows player smoothly(beta)
		this.x+=(x-this.x)*tween;
		this.y+=(y-this.y)*tween;
		
		fixBounds();
		
		colOffset = (int)-this.x/tileSize;
		rowOffset = (int)-this.y/tileSize;
	}
	public void draw(Graphics2D g)
	{
		for(int row=rowOffset;row<rowOffset+numRowsToDraw;row++)
		{
			if(row>=numRows)
			{
				break;
			}
			for(int col=colOffset;col<colOffset+numColsToDraw;col++)
			{
				if(col>=numCols)
				{
					break;
				}
				if(map[row][col]==0)
				{
					continue;
				}
				//which tile to draw
				int rc = map[row][col];
				int r = rc/numTilesAcross;
				int c = rc%numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x+col*tileSize,(int)y+row*tileSize,null);
			}
		}
	}
	//makes sure you don't go out of bounds
	private void fixBounds()
	{
		if(x<xmin)
		{
			x=xmin;
		}
		if(y<ymin)
		{
			y=ymin;
		}
		if(x>xmax)
		{
			x=xmax;
		}
		if(y>ymax)
		{
			y=ymax;
		}
	}
}
