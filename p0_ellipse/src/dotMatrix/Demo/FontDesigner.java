package dotMatrix.Demo;

import java.awt.TextField;
import java.awt.TextArea;

import processing.core.PApplet;

public class FontDesigner extends PApplet
{

	private static final long serialVersionUID = 2480290622011449578L;

	TextField inputLine = new TextField("inputLine", 15);
	TextArea inputArea = new TextArea("inputArea", 5, 20);

	public void setup()
	{
		size(640, 480);

		// setBounds(0,0,100,100);
		add(inputLine);
		add(inputArea);
	}

	public void mousePressed()
	{
		String iLine = inputLine.getText();
		String iArea = inputArea.getText();
		println("inputLine: " + iLine);
		println("inputArea: " + iArea);
	}

}
