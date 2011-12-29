package p0ellipse;

import processing.core.PApplet;


public class P0Ellipse extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1596293397312990840L;

	public void setup() 
	{
		size(480, 240);
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
		
		ellipse(mouseX, mouseY, 80, 80);
		
		rect(mouseX, mouseY, 100, 120);
	}
}
