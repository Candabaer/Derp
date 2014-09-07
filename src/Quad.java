import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

public class Quad {
	private int positionX;
	private int positionY;
	static final int size=50;
	private Rectangle rect;
	
	public Quad(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		positionX = (int) (Math.random() * 800 + 1);
		positionY = (int) (Math.random() * 600 + 1);
		if(positionX>=775&&positionX<=800){
			positionX-=50;
		}
		if(positionY>=575&&positionY<=600){
			positionY-=50;
		}
		rect = new Rectangle(
				positionX,positionY,size,size);
	}  
	public Rectangle getRect(){
		return rect;
	}
	public void drawQuad(){	
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(positionX,positionY);
		GL11.glVertex2f(positionX+size,positionY);
		GL11.glVertex2f(positionX+size,positionY+size);
		GL11.glVertex2f(positionX,positionY+size);
		GL11.glEnd();
	}
	
	public void killQuad(){
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	public int getPositionY(){
		return positionY;
	}
	public int getPositionX(){
		return positionX;
	}
}
