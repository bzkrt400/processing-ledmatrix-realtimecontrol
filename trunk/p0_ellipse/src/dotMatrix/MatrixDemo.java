package dotMatrix;

import processing.core.PApplet;;

public class MatrixDemo extends PApplet
{
	int x,y,r;
	private static final long serialVersionUID = 2900145680934847177L;

	public void setup()
	{
		this.size(400, 300);
		this.background(0xee);
		
		x = 0;
		y = 0;
		r=0;
		
		fill(0x00);
		//rect(30, 40, 60, 80);
	}
	
	public void draw()
	{
		x+=4;
		y+=3;
		r++;
		fade();
		pushMatrix();
		
		translate(x, y);
		rotate(radians(r));
		
	    fill(0x00);
		rect(30, 40, 60, 80);
	    popMatrix();

	}
	
	private void fade()
	{
		fill(0xff, 10);
		this.rect(0, 0, width, height);
	}
	
}
