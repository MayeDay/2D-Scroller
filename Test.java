
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;

public class Test extends GameObject{
	


	public Test(int x, int y, ObjectId id){
		super(x, y, id);
	}

	public Rectangle getBounds(){
		
	}

	public void tick(LinkedList<GameObject> object){

	}

	public void render(Graphics g){

		g.setColor(Color.RED);
		g.fillRect(x, y, 10, 10);
	}	
}