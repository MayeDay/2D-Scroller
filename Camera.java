import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Camera{
	
	public int x;
	public int y;
	public int width;
	public int height;
	private GameObject player;

	public Camera(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject player){
		this.player = player;

		if(player.getX() >= 406 && player.getX() <= 6046){
			x = -player.getX() + Game.WIDTH/2;
		
		} 
		if(player.getY() >= 300 && player.getY()< 880){
			y = -player.getY() + Game.HEIGHT/2;
		}
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;


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