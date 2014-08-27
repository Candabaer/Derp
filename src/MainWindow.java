import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class MainWindow {
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Quad quad=new Quad();
		
		while(!Display.isCloseRequested()){
			//Objekte rendern
			
			quad.drawQuad();
			pollInput();
			Display.update();
		}
		Display.destroy();
	}

	private void pollInput() {
		while(Mouse.next()){
			if(Mouse.isButtonDown(0)){
				System.out.println("X :"+Mouse.getEventX());
				System.out.println("Y :"+Mouse.getEventY());
			}
		}
	}
}

