package helpers;

import java.io.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;

public class Artist {

	public static int Width = 1260, Height = 960;
	public static Texture 
	black,country,town,city,
	male,maleSick,maleChild,maleChildSick,maleOld,maleOldSick,
	female,femaleSick,femaleChild,femaleChildSick,femaleOld,femaleOldSick,
	sneeze,
	birthday,concert,
	rat,mosquito,
	money,food,
	skyscraper,house,hut,fishpond,grocer,restaurant,farm,bank,factory,
	hospital;
	
	public static void BeginSession(String name) {

		Display.setTitle(name);
		try {
			Display.setDisplayMode(new DisplayMode(Width, Height));
			Display.create();
		} catch (LWJGLException e) {
			
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Width, Height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		black = QuickLoad("black");
		country = QuickLoad("country");
		town = QuickLoad("town");
		city = QuickLoad("city");
		male = QuickLoad("male");
		maleChild = QuickLoad("maleChild");
		maleOld = QuickLoad("maleOld");
		maleSick = QuickLoad("maleSick");
		maleChildSick = QuickLoad("maleChildSick");
		maleOldSick = QuickLoad("maleOldSick");
		female = QuickLoad("female");
		femaleChild = QuickLoad("femaleChild");
		femaleOld = QuickLoad("femaleOld");
		femaleSick = QuickLoad("femaleSick");
		femaleChildSick = QuickLoad("femaleChildSick");
		femaleOldSick = QuickLoad("femaleOldSick");
		sneeze = QuickLoad("sneeze");
		birthday = QuickLoad("birthday");
		concert = QuickLoad("concert");
		rat = QuickLoad("rat");
		mosquito = QuickLoad("mosquito");
		money = QuickLoad("money");
		food = QuickLoad("food");
		skyscraper = QuickLoad("skyscraper");
		house = QuickLoad("house");
		hut = QuickLoad("hut");
		fishpond = QuickLoad("fishpond");
		grocer = QuickLoad("grocer");
		restaurant = QuickLoad("restaurant");
		farm = QuickLoad("farm");
		bank = QuickLoad("bank");
		factory = QuickLoad("factory");
		hospital = QuickLoad("hospital");
	}
	
	public static void UpdateDisplay() {
		Display.update();
		Display.sync(120);
//		Display.sync(240);
	}
	
	public static boolean CheckCollision(float x1, float y1, float width1, float height1,
			float x2, float y2, float width2, float height2) {
		if(x1 + width1 > x2 && x1 < x2 + width2 &&
				y1 + height1 > y2 && y1 < y2 + height2) {
			return true;
		}
		return false;
	}
	
	public static void DrawLine(float x1, float y1, float x2, float y2) {
		glBegin(GL_LINE);
		glVertex2f(x1,y1);
		glVertex2f(x2,y2);
		glEnd();
	}
	
	public static void DrawQuad(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		glVertex2f(x,y); //Top left corner
		glVertex2f(x + width, y); //Top right corner
		glVertex2f(x + width, y + height); //Bottom right corner
		glVertex2f(x, y + height); //Bottom left corner
		glEnd();
	}
	
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height) {
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle) {
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(- width / 2, - height / 2, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Texture Failed");
		}
		return tex;
	}
	
	public static Texture QuickLoad(String name) {
		Texture tex = null;
		tex = LoadTexture("!res/" + name + ".png", "PNG");
		return tex;
	}
	
	public static void text(String text) {
		System.out.println(text);
	}
}
