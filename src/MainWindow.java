import java.util.Vector;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class MainWindow {
	private Timer timer=new Timer();
	private Vector<Quad> quadList=new Vector<Quad>();
	private int deltaSpawn;
	private int mod;
	private int counter;
	public MainWindow() {
		deltaSpawn=0;
		mod=2000;
		counter=1900;
	}
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		timer.getDelta();
		timer.setLastFPS(timer.getTime());
		while(!Display.isCloseRequested()){
			
			//Objekte rendern
			spawnTheQuads();
			timer.updateFPS();
			for(Quad quad:quadList)
				quad.drawQuad();
			pollInput();
			Display.update();
			Display.sync(60);
			
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
							quadList.remove(i);
							increaseSpawnRate();
							tmpQuad.killQuad();
						}
				}
			}
		}
	}
	
	private void increaseSpawnRate(){
		final int maxMod = 500;
		final int maxCounter = 400;
		final int decreaseAmount = 100;
		if (mod > maxMod) {
			mod -= decreaseAmount;
			counter -= decreaseAmount;
		}
		if (mod == maxMod) {
			mod = maxMod;
			counter = maxCounter;
		}
		System.out.println("Mod:" +mod +"counter: "+counter);
	}
	
	
	private void spawnTheQuads() {
		// System.out.println("Delta: " + delta);
		/*
		 * Timer schneller werden lassen
		 */
		deltaSpawn+=timer.getDelta();
		if (deltaSpawn % mod >= counter) {
			deltaSpawn=0;
			Boolean contact = true;
			Quad tmpQuad = null;
			while (contact) {
				tmpQuad = new Quad();
				if (quadList.isEmpty()) {
					contact = false;
					break;
				}
				for (int i = 0; i < quadList.size(); i++) {
					if (quadList.elementAt(i).getRect()
							.intersects(tmpQuad.getRect())) {
						contact = true;
						break;
					} else if (i < quadList.size()) {
						contact = false;
					}
				}
			}
			quadList.add(tmpQuad);
		}
	}
}
