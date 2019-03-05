import java.awt.Rectangle;

public class Camera{
	
	public int x;
	public int y;
	public int width;
	public int height;

	public Camera(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject player){

		width =-player.getX() + Game.WIDTH/2;
		height = -player.getY()+ Game.HEIGHT/2;


		if(player.getX() >= 406 && player.getX() <= 6046){
			x = -player.getX() + Game.WIDTH/2;
		
		} 
		if(player.getY() >= 300 && player.getY()< 880){
			y = -player.getY() + Game.HEIGHT/2;
		}
	}

	public void render(){


	}

	public Rectangle getBounds(){
		return (new Rectangle(x, y, Game.WIDTH/2, Game.HEIGHT/2));
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

}