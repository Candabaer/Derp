import org.lwjgl.opengl.GL11;

public class Quad {
	private int positionX;
	private int positionY;
	
	public Quad(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		while (!(positionY > 25 || positionY < 575 && positionX > 25
				|| positionX < 775)) {
			positionX = (int) (Math.random() * 800 + 1);
			positionY = (int) (Math.random() * 600 + 1);
		}
	}
	
	public void drawQuad(){
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(positionX,positionY);
		GL11.glVertex2f(positionX+50,positionY);
		GL11.glVertex2f(positionX+50,positionY+50);
		GL11.glVertex2f(positionX,positionY+50);
		GL11.glEnd();
	}
	
	public void growQuad(){
		
	}
}
