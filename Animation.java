import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animation {
	
	public int speed;
	private int frames;

	private int index = 1;
	private int count = 1;
	private int num = 1;
	public Texture tex;
	public boolean direction;


	public BufferedImage image;
	public BufferedImage movingImage;


	public Animation(){

		tex = Game.getTextureInstance();
	}

	public void walkingRight(GameObject object, int amount){

			if(count < amount){
				object.setImage(tex.ps.grabImage(count, 2, 50, 40)); 
				count++;	

			}else{
				count = 1;
			}
			
		
	}
	public void walkingLeft(GameObject object, int start, int amount){
		
			if(index < start){
				index = start;
			}

			if(index < amount){
				object.setImage(tex.ps.grabImage(index, 2, 50, 40)); 
				index++;

			}else{
				index = 1;
			}
	}
}