package p0ellipse;

import processing.core.PApplet;


public class P0Ellipse extends PApplet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1596293397312990840L;

	public void setup() 
	{
		size(200, 40);
		for (int i=0; i<8; i++)
			rect(10+i*22, 10, 20, 20);	
	}

	public void draw()
	{
		
		
		if (mousePressed) 
		{
			fill(0);
		}
		else
		{
			fill(255);
		}		
		
		//ellipse(mouseX, mouseY, 80, 80);		
	}

}
