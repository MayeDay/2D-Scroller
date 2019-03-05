
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
	
	public Texture tex = Game.getTextureInstance();
	private int type;



	public Block(int x, int y, ObjectId id, BufferedImage image, int type){
		super(x, y, id, image);

		this.type = type;
	}

	public Rectangle getBounds(){
		return (new Rectangle(x, y, 32, 32));
	}


	public void tick(LinkedList<GameObject> object){

	}

	public void render(Graphics g){
		
		if(type == 0){
			g.drawImage(tex.block[0], x, y,null);

		}else if(type == 1){
			g.drawImage(tex.block[1], x, y, null);
		
		}else if(type == 2){
			g.drawImage(tex.block[2], x, y, null);
		
		}else if(type == 3){
			g.drawImage(tex.block[3], x, y, null);

		}else if(type == 4){
			g.drawImage(tex.block[4], x, y, null);

		}else if(type == 5){
			g.drawImage(tex.block[5], x, y, null);

		}else if(type == 6){
			g.drawImage(tex.block[6], x, y, null);
		}
	}	
}