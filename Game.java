import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.*;


public class Game extends Canvas implements Runnable{

	private boolean running = false;
	private Thread thread;

	public static int WIDTH;
	public static int HEIGHT;

	//Handler Object

	Handler handler;
	Camera cam;
	static LevelLoading lvlLoad;

	static Texture tex;

	private BufferedImage level = null;
	private BufferedImage background = null;
	private BufferedImage underground = null;
	private AudioInputStream backgroundMusicFile;
	private Clip backgroundMusic;


	public synchronized void start(){
		
		if(running)
			return;

		running = true;
		thread = new Thread(this);
		initializeBackground();
		thread.start();
	}

	public void initializeBackground(){

		
	}


	public void init(){

		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/backgroundImages/stage1.png");
		background = loader.loadImage("backgroundImages/clouds.png");
		underground = loader.loadImage("backgroundImages/underground.png");


		handler = new Handler();
		cam = new Camera(0,0);
		lvlLoad = new LevelLoading(handler, level, cam);

		//handler.addObject(new Player(100, 100, ObjectId.Player, handler));
		//handler.createLevel();

		this.addKeyListener(new KeyInput(handler));

	}
	
	public void run(){

		//Game Loop Variables
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSec = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		//Game Looop
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSec;
			lastTime = now;
			
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " +frames+ " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick(){

		handler.tick();

		for(int i = 0; i <handler.object.size(); i++){
			if(handler.object.get(i).getId() == ObjectId.Player){
				cam.tick(handler.object.get(i));
			}
		}

	}

	private void render(){

		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

	

		//////////////////////////////////////
		//Draw Here

		

		g2d.translate(cam.getX(), cam.getY());

		for(int i = 0; i < background.getWidth()* 3; i+=background.getWidth()-8){
			g.drawImage(background, i, 0, null);
			if(i < background.getWidth()*2){
				g.drawImage(underground, i, background.getHeight(), null);	
			}
		}

		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());

		/////////////////////////////////////
		g.dispose();
		bs.show();

	}

	private Rectangle pixelBounds(int x, int y){
		return (new Rectangle(x, y, 32, 32));
	}

	public static LevelLoading getLevel(){
		return lvlLoad;
	}
		
	public static Texture getTextureInstance(){
		return tex;
	}

	public static void main(String args[]){

		new Frame(800, 600,"Fantasy Hero", new Game());
	}
}