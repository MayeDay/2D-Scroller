import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter{

	Handler handler;
	public static Animation animation = null;
	private LinkedList<Integer> keys = new LinkedList<Integer>();
	private LevelLoading lvlLoad = Game.getLevel();

	int loop = 0;



	public KeyInput(Handler handler){
		this.handler = handler;
		
		if(animation == null){
			animation = new Animation();
		}

		registerKeys();
	}

	public void registerKeys(){

		if(keys.isEmpty()){
			keys.add(KeyEvent.VK_UP);
			keys.add(KeyEvent.VK_DOWN);
			keys.add(KeyEvent.VK_LEFT);
			keys.add(KeyEvent.VK_RIGHT);
		}
	}
	

	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Player){
				tempObject.isIdle = false;


				if(code == KeyEvent.VK_RIGHT){
					tempObject.isRight = true;
					tempObject.setVelX(3);

					if(!tempObject.isJumping){
						tempObject.isMoving = true;
					}
				}else if(code == KeyEvent.VK_LEFT){
					tempObject.isRight = false;
					tempObject.setVelX(-3);

					if(!tempObject.isJumping){
						tempObject.isMoving = true;

					}
				}else if(code == KeyEvent.VK_UP && !tempObject.isJumping){
					tempObject.isJumping = true;
					tempObject.setVelY(-20);

				}else if(code == KeyEvent.VK_SPACE ){
					System.out.println("GOOD");
					tempObject.isAttacking = true;
				}
			}
		}
		

		if(code == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}

	}

	public void keyReleased(KeyEvent e){
		int code = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player){
				
				if(code == KeyEvent.VK_RIGHT){
					tempObject.isMoving = false;
					tempObject.setVelX(0);

				}else if(code == KeyEvent.VK_LEFT){
					tempObject.isMoving = false;
					tempObject.setVelX(0);

				}else if(code == KeyEvent.VK_UP && !tempObject.isJumping){
					tempObject.setVelY(0);

				}else if(code == KeyEvent.VK_SPACE ){
					System.out.println("GOOD");
					tempObject.isAttacking = false;
				}
			}
		}
	}
}