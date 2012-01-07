package dotMatrix;

import processing.core.PApplet;

import ddf.minim.*;
import ddf.minim.analysis.*;

public class FFTDemo extends PApplet
{
	private static final long serialVersionUID = 8598839469711789068L;
	Minim minim;
	AudioPlayer ap;
	FFT fft;
	int r =0;
	
	int halfWidth, halfHeight;
	float sectionWidth;
	
	public void setup()
	{
		size(600, 600, P2D);	
		
		halfWidth = width / 2;
		halfHeight = height / 2;
		
		minim = new Minim(this);	
		background(0xcc);
  
		ap = minim.loadFile("sample.mp3", 2048);
		
		sectionWidth = width / 256;

		ap.loop();
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());
		// use 128 averages.
		// the maximum number of averages we could ask for is half the spectrum size. 
		fft.logAverages(22,3);
		
		rectMode(CORNERS);
	}
	
	public void draw()
	{
		r++;
		fade();
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
			
		  stroke(0xff0000ff);
		  // we draw the waveform by connecting neighbor values with a line
		  // we multiply each of the values by 50 
		  // because the values in the buffers are normalized
		  // this means that they have values between -1 and 1. 
		  // If we don't scale them up our waveform 
		  // will look more or less like a straight line.
		  
		  pushMatrix();
			
			translate(halfWidth, halfHeight);
			rotate(radians(r));
			scale(0.125f);

		    
		  
		  for(int i = 0; i < ap.bufferSize() - 1; i++)
		  {
		    line((-ap.bufferSize() / 2 + i)*sectionWidth, -100 + ap.left.get(i)*250, (-ap.bufferSize() / 2 + i+1)*sectionWidth, -100 + ap.left.get(i+1)*250);
		    line((-ap.bufferSize() / 2 + i)*sectionWidth, +100 + ap.left.get(i)*250, (-ap.bufferSize() / 2 + i+1)*sectionWidth, +100 + ap.left.get(i+1)*250);
		  }
		  popMatrix();
	}
	
	private void fade()
	{
		fill(0xff, 10);
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
