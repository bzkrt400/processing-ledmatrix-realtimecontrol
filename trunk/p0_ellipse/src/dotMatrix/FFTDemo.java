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
	
	public void setup()
	{
		size(512, 200, P2D);	
		minim = new Minim(this);		
  
		ap = minim.loadFile("sample.mp3", 2048);

		ap.loop();
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());
		// use 128 averages.
		// the maximum number of averages we could ask for is half the spectrum size. 
		fft.logAverages(22,3);
		
		rectMode(CORNERS);
	}
	
	public void draw()
	{
		/*
		 background(0);
		  fill(255);
		  fft.forward(ap.mix);
		  int w = (int)(width/fft.avgSize());
		  for(int i = 0; i < fft.avgSize(); i++)
		  {
			  // draw a rectangle for each average, multiply the value by 5 so we can see it better
			  rect(i*w, height, i*w + w, height - fft.getAvg(i));
		  }
		  */
		  //background(0);
			
		  stroke(255);
		  // we draw the waveform by connecting neighbor values with a line
		  // we multiply each of the values by 50 
		  // because the values in the buffers are normalized
		  // this means that they have values between -1 and 1. 
		  // If we don't scale them up our waveform 
		  // will look more or less like a straight line.
		  for(int i = 0; i < ap.bufferSize() - 1; i++)
		  {
		    line(i, 50 + ap.left.get(i)*50, i+1, 50 + ap.left.get(i+1)*50);
		    line(i, 150 + ap.right.get(i)*50, i+1, 150 + ap.right.get(i+1)*50);
		  }
	}
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
	  ap.close();
	  minim.stop();
	  
	  super.stop();
	}
}
