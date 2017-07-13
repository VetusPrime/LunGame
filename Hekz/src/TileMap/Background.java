package TileMap;

import java.awt.Graphics2D;
import java.awt.image.*;

import javax.imageio.*;

import Main.GamePanel;
public class Background {

	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms)
	{
		try{
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setPosition(double x, double y)
	{
		//so background doesn't move off screen. always stays in boundaries of the screen
		this.x = (x*moveScale)%GamePanel.WIDTH;
		this.y = (y*moveScale)%GamePanel.HEIGHT;
	}
	//have background auto scroll
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	public void update()
	{
		x+=dx;
		y+=dy;
	}
	public void draw(Graphics2D g)
	{
		g.drawImage(image, (int)x, (int)y, null);
		//to draw scrolling background on right side
		if(x<0)
		{
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y,null);
		}
		// to draw scrolling background on left side
		if(x>0)
		{
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y,null);
		}
	}
}
