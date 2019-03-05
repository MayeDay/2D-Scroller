import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
	
	public Texture tex = Game.getTextureInstance();

	private int width = 32;
	private int height = 64;

	private Handler handler;

	private int loop;
	private int jumpLoop;
	private int moveLoop;
	private int counter;
	private int count = 1;
	private int count2 = 16;
	private int tickLoop = 0;
	private int state = 1;

	public Player(int x, int y, ObjectId id, BufferedImage image, Handler handler){
		super(x, y, id, image);
		this.handler = handler;

		if(animation == null){
			animation = new Animation();
		}
	}

	public Rectangle getBounds(){
		return (new Rectangle(x + width/2 + width/4, y + 2, (width/2) / 2, height - 4));
	}

	public Rectangle getBoundsUp(){
		return (new Rectangle(x + width/4, y, width / 2, height/2));
	}

	public Rectangle getBoundsLeft(){
		return (new Rectangle(x ,y + 2 , (width/2) / 2, height - 4));
	}

	public Rectangle getBoundsDown(){
		return (new Rectangle(x + width/4, y + height/2, width/2, height/2));
	}

	public Rectangle fullBounds(){
		return(new Rectangle(x, y + 5, 32, height));
	}


	public void tick(LinkedList<GameObject> object){

	
		gravity(object);
		//level Collision
		collision(object, handler, height);
		//Enemy Collision
		monsterCollision(object);	
		
	}

	private void monsterCollision(LinkedList<GameObject> object){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Monster){
				
				if(getBoundsUp().intersects(tempObject.getBoundsDown())){
					velY = 0;
					y = tempObject.getY() + height/2;
				}
				
				if(getBoundsDown().intersects(tempObject.getBoundsUp())){
					velY = 0;
					y = tempObject.getY() - height + 6;
					isFalling = false;
					isJumping = false;
					isIdle = true;
				}else{
					isFalling = true;
				}

				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + height/2;	
				}

				if(getBounds().intersects(tempObject.getBoundsLeft())){
					x = tempObject.getX() - height/2;	
				}
			}
		}
	}

	public void idleAnimation(){
		//Make new varibale for counter
		if(isIdle == true && !isMoving == true){
			if(loop == 3){
				loop = 0;

				if(isRight == true){
					counter++;
					image = tex.ps.grabImage(counter, 1, 50, 40);
				}else{
					
					count2++;
					image = tex.ps.grabImage(count2, 1, 50, 40);
				}

				if(counter >= 4 || count2 >= 20){
					counter = 1;
					count2 = 17;
				}
			}else{
				loop++;
			}
		}else{
			
		}
	}

	public void jumpAnimation(){

		if(isJumping == true){
			if(jumpLoop == 5){
				jumpLoop = 0;

				if(isRight == true){
				count++;
				image = tex.ps.grabImage(count, 3, 50, 40);

				}else{
					count2++;
					image = tex.ps.grabImage(count2, 3, 50, 40);
				}
				if(count > 9 || count2 > 25){
					count = 8;	
					count2 = 24;
				}
			}else{
				jumpLoop++;
			}
		}else{
			count = 1;
		}
	}

	public void movingRight(){
		isIdle = false;

		if(isMoving == true && !isJumping == true){
			if(moveLoop == 5){
				moveLoop = 0;
					if(isRight == true){
						if(state < 6){
							image = tex.ps.grabImage(state, 2, 50, 40);
							state++;
						}
						else{
							state = 1;
						}
					}
			}else{
				moveLoop++;
			}
		}
	}

	//Finish animation for left movement
	public void movingLeft(){
		isIdle = false;

		if(isMoving == true && !isJumping == true){
			if(moveLoop == 5){
				moveLoop = 0;
					if(isRight == false){
						if(state < 17){
							state = 17;
						}

						if(state < 23){
							image = tex.ps.grabImage(state, 2, 50, 40);
							state++;
						}
						else{
							state = 17;
						}
					}
			}else{
				moveLoop++;
			}
		}
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		idleAnimation();
		jumpAnimation();
		if(isRight){
			movingRight();
		}else{
			movingLeft();
		}
	
		g.drawImage(image, x - 12, y + 5, 64, height, null);	
		g.setColor(Color.RED);
		g.drawRect(x, y + 5, 32, height);
	}	
}