package p0ellipse;

import processing.core.PApplet;

public class P0Ellipse extends PApplet
{	
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
				if (mouseY > 10 && mouseY < 30 && mouseX > 10)
				{
					int x = (mouseX - 10)/22;
					
					if (mouseButton == LEFT)				
						fill(255,0,0);
					else
						fill(255, 255, 255);
					
					if (x>=0 && x<8)
						rect(10+x*22, 10, 20, 20);		
				}
			}
			//ellipse(mouseX, mouseY, 80, 80);		
		}

}
