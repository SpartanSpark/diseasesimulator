package data;

import static helpers.Artist.*;

import org.lwjgl.opengl.Display;

public class Start {
	
	public Start() {
//		Makes screen and sets it up
		BeginSession("Disease Simulator | Made by: Michael Marzouca and not really Harrisen Richards");
		World world = new World(175000,1108);
//		World world = new World(9000,1);
//		Update everything repeatedly until you close the window
		while(!Display.isCloseRequested()) {
			DrawQuadTex(black,0,0,(int)(Width*1.6),(int)(Height*1.1));
			world.update();
			UpdateDisplay();
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Start();
	}
}