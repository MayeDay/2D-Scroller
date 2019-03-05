import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Monster extends GameObject{

public Texture tex = Game.getTextureInstance();

	private int width = 32;
	private int height = 25;

	private Handler handler;

	private int loop;
	private int jumpLoop;
	private int counter;
	private int count = 1;
	private int tickLoop = 0;

	public Monster(int x, int y, ObjectId id, BufferedImage image, Handler handler){
		super(x, y, id, image);
		this.handler = handler;

	}

	public Rectangle getBounds(){
		return (new Rectangle(x +2 + width/2 + width/4, y + 5, ((width/2) / 2)/2, height - 10));
	}

	public Rectangle getBoundsUp(){
		return (new Rectangle(x + width/4, y + 3, width / 2, height/2));
	}

	public Rectangle getBoundsLeft(){
		return (new Rectangle(x +3 ,y + 5 , ((width/2) / 2)/2, height - 10));
	}

	public Rectangle getBoundsDown(){
		return (new Rectangle(x + width/4, y + height/2 + 2, width/2, height/2));
	}
	public Rectangle fullBounds(){
		return (new Rectangle(x - width *3, y - height*3, width *7, height *7));
	}


	public void tick(LinkedList<GameObject> object){


		if(tickLoop == 2){
			gravity(object);
			collision(object);	
			movement();

			tickLoop = 0;
		}else{
			tickLoop++;
		}
	}

	public void collision(LinkedList<GameObject> object){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.Monster){

				if(getBoundsUp().intersects(tempObject.getBounds())){
					velY = 0;
					y = tempObject.getY() + height/2;
				}
				
				if(getBoundsDown().intersects(tempObject.getBounds())){
					velY = 0;
					y = tempObject.getY() - height;
					isFalling = false;
					isJumping = false;
					isIdle = true;
				}else{
					isFalling = true;
				}

				
				if(tempObject.getId() == ObjectId.Player){

				

				}
			}
		}

	}

	public void idleAnimation(){

		if(isIdle == true){
			if(loop == 4){
				loop = 0;
				counter++;
					tex.monster[0] = tex.ms.grabImage(counter, 1, 32, 25);

				if(counter > 3){
					counter = 0;
				}
			}else{
				loop++;
			}
		}
	}

	public void movement(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(fullBounds().intersects(tempObject.fullBounds()) && tempObject.getX() > x){
					x+=3;
				}else if(fullBounds().intersects(tempObject.fullBounds()) && tempObject.getX() < x){
					x-=3;
				}

			}
		}
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		idleAnimation();

	
		g.drawImage(tex.monster[0], x, y + 2, 32, height, null);
		g.setColor(Color.RED);
		g2d.draw(fullBounds());
		
	}	
}	


