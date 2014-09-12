import java.util.Vector;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class MainWindow {
	private Vector<Quad> quadList=new Vector<Quad>();
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		while(!Display.isCloseRequested()){
			//Objekte rendern
			spawnTheQuads();
			for(Quad quad:quadList)
				quad.drawQuad();
			pollInput();
			Display.update();
		}
		Display.destroy();
	}

	private void pollInput() {
		int posX=0;
		int posY=0;
		while(Mouse.next()){
			if(Mouse.isButtonDown(0)){
				posX=Mouse.getEventX();
				posY=Mouse.getEventY();
				for(int i = 0;i<quadList.size();i++){
					Quad tmpQuad=quadList.elementAt(i);
						if(tmpQuad.getRect().contains(posX, posY)){
							System.out.println("Shits deleted");
							quadList.remove(i);
							quadList.get(0).killQuad();
						}
				}
			}
		}
	}
	
	private void spawnTheQuads() {
		Boolean contact = true;
		Quad tmpQuad = null;
		while (contact) {
			//System.out.println("Contact: "+contact);
			tmpQuad = new Quad();
			if (quadList.isEmpty()) {
				contact=false;
				break;
			}
			for (int i=0;i<quadList.size();i++) {
				if (quadList.elementAt(i).getRect().intersects(tmpQuad.getRect())) {
					contact=true;
					break;
				}else if (i < quadList.size()) {
					contact = false;
				}
			}
		}
		quadList.add(tmpQuad);
	}
}