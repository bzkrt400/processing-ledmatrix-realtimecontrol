package dotMatrix;

import processing.core.PApplet;
import processing.core.PFont;

import ddf.minim.*;
import ddf.minim.analysis.*;

public class BuffDemo extends PApplet
{
	private static final long serialVersionUID = 8598839469711789068L;
	Minim minim;
	AudioPlayer ap;
	FFT fft;
	float r =0f;
	PFont font;
	
	int halfWidth, halfHeight;
	float sectionWidth;
	
	public void setup()
	{
		font = this.loadFont("Calibri-24.vlw");
		size(600, 600, P2D);	
		
		halfWidth = width / 2;
		halfHeight = height/ 2;
		
		minim = new Minim(this);	
		background(0x00);
  
		ap = minim.loadFile("sample.mp3", 512);
		
		sectionWidth = (float)width / ap.bufferSize();

		ap.loop();
		

		
		  textFont(font);
		  fill(0);
		  text(ap.bufferSize(), 0, 24);
	}
	
	public void draw()
	{
		r+=0.02;
		fade(20);
		 //background(0);
		  //fill(255);
		  /*
		  fft.forward(ap.mix);
		  int w = (int)(width/fft.avgSize());
		  for(int i = 0; i < fft.avgSize(); i++)
		  {
			  // draw a rectangle for each average, multiply the value by 5 so we can see it better
			  rect(i*w, height, i*w + w, height - fft.getAvg(i));
		  }
		  */
		  //background(0);
			
		  stroke(0xffffffff);
		  strokeWeight(2);  
		  // we draw the waveform by connecting neighbor values with a line
		  // we multiply each of the values by 50 
		  // because the values in the buffers are normalized
		  // this means that they have values between -1 and 1. 
		  // If we don't scale them up our waveform 
		  // will look more or less like a straight line.
		  
		  pushMatrix();
			
		translate(halfWidth, halfHeight);
			rotate(r);
			scale(1.4f);
					    
		  
		  for(int i = 1; i < ap.bufferSize(); i++)
		  {
		    line((i-1)*sectionWidth - halfWidth, ap.left.get(i-1)*250, i*sectionWidth - halfWidth, ap.left.get(i)*250);
		    		    
		  }
		  popMatrix();
  
		  pushMatrix();
			
			translate(halfWidth, halfHeight);
				rotate(r+PI/2);
				scale(1.4f);		    
			  
			  for(int i = 1; i < ap.bufferSize(); i++)
			  {
			   
			    line((i-1)*sectionWidth - halfWidth,ap.left.get(i-1)*250, i*sectionWidth - halfWidth, ap.left.get(i)*250);		    
			  }
			  popMatrix();
  		delay(50);
	}
	
	private void fade(int fadeValue)
	{
		fill(0x00, fadeValue);
		this.rect(0, 0, width, height);
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
	  ap.close();
	  minim.stop();
	  
	  super.stop();
	}
}
