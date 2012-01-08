package dotMatrix;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class BarDemo extends PApplet
{
	private static final long serialVersionUID = -2935931867398743145L;
	
	private DotMatrix dm;
	private DotMatrixDisplay dmd;	
	private DotMatrixSerial sp;
	private Bar bars[];
	
	private int dotWidth = 20;
	private int margin = 10;
	
	Minim minim;
	AudioPlayer ap;
	FFT fft;

	public void setup()
	{
		dm = new DotMatrix(48, 7);
		
		dmd = new DotMatrixDisplay(this, dm, dotWidth, margin);
		dmd.setColor(0xffff0000, 0xffffffff);
		
		sp = new DotMatrixSerial(this, "COM3", dm);
		bars = new Bar[48];
		
		dmd.display();
		sp.send();
		
		
		minim = new Minim(this);  
		ap = minim.loadFile("sample.mp3", 512);
		ap.loop();	
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());
		fft.logAverages(100, 3);
		rectMode(CORNERS);
		
		
		for(int i=0; i<bars.length; i++)
		{	
			bars[i] = new Bar(dm, i);
			bars[i].setHeight(i%(dm.getRowCount()+1));
			bars[i].show();			
		}
		
		dmd.display();
		sp.send();
	}
	
	public void draw()
	{
		dm.clear(false);
		fft.forward(ap.mix);
		
		for(int i = 0; i < 16; i++)
		{
			int h =  constrain((int)(2.5* log(fft.getAvg(i))), 0, 7);
			bars[3*i].setHeight(h);
			bars[3*i+1].setHeight(h);
			bars[3*i].show();
			bars[3*i+1].show();
	    }
		
		dmd.display();
		sp.send();
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
		ap.close();
		minim.stop();
		super.stop();
	}
}
