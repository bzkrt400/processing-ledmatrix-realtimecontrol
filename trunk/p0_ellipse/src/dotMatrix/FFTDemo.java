package dotMatrix;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import processing.core.PApplet;

public class FFTDemo extends PApplet
{
	private static final long serialVersionUID = -9093215355069297598L;

	Minim minim;
	AudioPlayer ap;
	FFT fft;
	
	float fftMax = 0;
	
	public void setup()
	{
		minim = new Minim(this);	
		background(0x00);
  
		ap = minim.loadFile("sample.mp3", 1024);
		ap.loop();	
		
		size(600, 100);
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());		  
		
		  fft.logAverages(100, 3);
		  rectMode(CORNERS);
		/*
		for (int i=0; i<fft.specSize();i++)
			TestHelper.PrintText(this, Integer.toString(i) + " ^ "+ Float.toString(fft.indexToFreq(i)), 0xff, 2 + 100* ( i/50), 12+ 12*(i% 50));
		*/
	}
	
	public void draw()
	{
		
		 fill(255);
		background(0x00);
		TestHelper.PrintText(this, Integer.toString(fft.avgSize()));
		TestHelper.PrintText(this, Float.toString(fftMax), 0xff, 2, 48);
		//fft.inverse(ap.mix);
		fft.forward(ap.mix);
		
		
		 int w = (int)(width/fft.avgSize());
		  for(int i = 0; i < fft.avgSize(); i++)
		  {
			  float f = (int)(2* log(fft.getAvg(i)))*10;
			  if (fftMax < f) fftMax =  f;
			  // draw a rectangle for each average, multiply the value by 5 so we can see it better
		    rect(i*w, height, i*w + w, height -f);
		  }
		  //delay(50);
		/*
		//TestHelper.fade(this, 10);
		
		  background(0);
		  TestHelper.PrintText(this, Integer.toString(fft.specSize()));
		  stroke(255);
		 fft.forward(ap.mix);
		  for(int i = 0; i < fft.specSize(); i++)
		  {
			  if (fftMax < fft.getBand(i)) fftMax =fft.getBand(i); 
		    // draw the line for frequency band i, scaling it by 4 so we can see it a bit better
		    line(i, height, i, height - fft.getBand(i)*4);
		  }
		  */
	}
}
