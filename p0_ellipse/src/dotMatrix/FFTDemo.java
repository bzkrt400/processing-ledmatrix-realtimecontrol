package dotMatrix;

import processing.core.PApplet;

import ddf.minim.*;
import ddf.minim.analysis.*;

public class FFTDemo extends PApplet
{
	private static final long serialVersionUID = 8598839469711789068L;
	Minim minim;
	AudioPlayer jingle;
	FFT fft;
	
	public void setup()
	{
		size(512, 200, P2D);	
		minim = new Minim(this);
		
  
		jingle = minim.loadFile("sample.mp3", 2048);

		jingle.loop();
		
		fft = new FFT(jingle.bufferSize(), jingle.sampleRate());
		// use 128 averages.
		// the maximum number of averages we could ask for is half the spectrum size. 
		  fft.logAverages(22,3);

		
		rectMode(CORNERS);
	}
	
	public void draw()
	{
		 background(0);
		  fill(255);
		  fft.forward(jingle.mix);
		  int w = (int)(width/fft.avgSize());
		  for(int i = 0; i < fft.avgSize(); i++)
		  {
		    // draw a rectangle for each average, multiply the value by 5 so we can see it better
		    rect(i*w, height, i*w + w, height - fft.getAvg(i));
		  }
	}
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
	  jingle.close();
	  minim.stop();
	  
	  super.stop();
	}
}
