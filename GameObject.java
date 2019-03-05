import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject{
	
	protected int x;
	protected int y;

	protected int velX = 0;
	protected int velY = 0;

	protected ObjectId id;

	protected boolean isFalling = true;
	protected boolean isJumping = false;
	protected boolean isRight = true;
	protected boolean isIdle = true;
	protected boolean isAttacking = false;
	protected boolean isMoving = false;

	protected final int MAX_SPEED = 10;
	protected final int gravity = 1;
	protected BufferedImage image;

	protected Animation animation;

	public GameObject(int x, int y, ObjectId id, BufferedImage image){

		this.x = x;
		this.y = y;
		this.id = id;
		this.image = image;
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	
	public  Rectangle getBounds(){
		return null;

	}
	public  Rectangle getBoundsUp(){
		return null;


	}
	public  Rectangle getBoundsLeft(){
		return null;


	}
	public  Rectangle getBoundsDown(){
		return null;


	}
	public  Rectangle fullBounds(){
		return null;


	}


	public void gravity(LinkedList<GameObject> object){

		x+=velX;
		y+=velY;

		if(isFalling || isJumping){
			velY += gravity;

			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}

	}

	public void collision(LinkedList<GameObject> object, Handler handler, int height){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Block){

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

				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + height/2;	
				}

				if(getBounds().intersects(tempObject.getBounds())){
					x = tempObject.getX() - height/2;	
				}
				if(tempObject.getId() == ObjectId.Monster){

				

				}
			}
		}


	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getVelX(){
		return velX;
	}

	public int getVelY(){
		return velY;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}

	public boolean getIsFalling(){
		return isFalling;
	}

	public void setIsFalling(boolean isFalling){
		this.isFalling = isFalling;
	}

	public boolean getIsJumping(){
		return isJumping;
	}

	public void setIsJumping(boolean isJumping){
		this.isJumping = isJumping;
	}

	public ObjectId getId(){
		return this.id;
	}

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public BufferedImage getImage(){
		return this.image;
	}
}