import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class TreasureChest extends GameObject{
		
	public Texture tex = Game.getTextureInstance();
	private Handler handler;
	private boolean check = false;
	private int loop = 1;
	private int tick = 0;
	private int opening = 1;




	public TreasureChest(int x, int y, ObjectId id, BufferedImage image, Handler handler){
		super(x, y, id, image);
		this.handler = handler;

	}

	public Rectangle getBounds(){
		return (new Rectangle(x, y, 48, 48));
	}

	public Rectangle chestBounds(){
		return (new Rectangle(x, y, 48, 48));
	}


	public void tick(LinkedList<GameObject> object){

		if(tick == 3){
			collision(object);
			tick = 0;
		}else{
			tick++;
		}
	}

	public void openAnimation(){
		for(int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(tempObject.isAttacking){

					if(loop == 4){
						if(check == true){
							image = tex.cs.grabImage(1, opening, 48, 48);
							loop = 0;
							opening++;
						}
						if(opening > 4){
							opening = 4;
						}
					}else{
						loop++;
					}
				}else{
					tempObject.isAttacking = false;
				}
			}

		}

	}

	public void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(tempObject.fullBounds().intersects(chestBounds())){
					check = true;
				}
			}
		}
	}

	public void render(Graphics g){

		openAnimation();

		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(getImage(), x, y -12, null);
		
	}	
}