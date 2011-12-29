package p0ellipse;

import com.sun.xml.internal.ws.util.xml.CDATA;

import processing.core.PApplet;
import processing.serial.Serial;

public class P0Ellipse extends PApplet
{	
	private static final long serialVersionUID = -1596293397312990840L;	
	
	byte _cData = 0x00;
	Serial sp;
	
	public void setup() 
	{
	  sp = new Serial(this, "COM3", 9600);
	  sp.write(_cData);
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
				{
					fill(255,0,0);
					_cData |= 0x01 << (7-x);
				}
				else
				{
					fill(255, 255, 255);
					_cData &= ~(0x01 << (7-x));
				}
				
				if (x>=0 && x<8)
				{
					rect(10+x*22, 10, 20, 20);					
					sp.write(_cData);
				}
			}
		}
		//ellipse(mouseX, mouseY, 80, 80);		
	}

}
