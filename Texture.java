import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Texture{
	

	SpriteSheet bs;
	SpriteSheet ps;
	SpriteSheet ms;
	SpriteSheet cs;

	private BufferedImage block_sheet = null;
	public BufferedImage player_sheet = null;
	public BufferedImage monster_sheet = null;
	private BufferedImage chest_sheet = null;

	public BufferedImage block[] = new BufferedImage[8];
	public BufferedImage player[] = new BufferedImage[1];
	public BufferedImage monster[] = new BufferedImage[1];
	public BufferedImage chest[] = new BufferedImage[1];


	public Texture(){

	BufferedImageLoader loader = new BufferedImageLoader();

	try{
		block_sheet = loader.loadImage("/backgroundImages/tileset.png");
		player_sheet = loader.loadImage("characterSheet.png");
		monster_sheet = loader.loadImage("/slime-Sheet.png");
		chest_sheet = loader.loadImage("/gameObjects/chest.png");
	
	}catch(Exception e){
		e.printStackTrace();
	}

	bs = new SpriteSheet(block_sheet);
	ps = new SpriteSheet(player_sheet);
	ms = new SpriteSheet(monster_sheet);
	cs = new SpriteSheet(chest_sheet);

	getTextures();
	}

	private void getTextures(){


		block[0] = bs.grabImage(2, 2, 32,32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		block[2] = bs.grabImage(3, 1, 32, 32);
		block[3] = bs.grabImage(1, 1, 32, 32);

		block[4] = bs.grabImage(10, 2, 32,32);
		block[5] = bs.grabImage(10, 1, 32,32);
		block[6] = bs.grabImage(11, 1, 32,32);

		player[0] = ps.grabImage(1, 1, 50, 40);

		monster[0] = ms.grabImage(1, 1, 32, 25);

		chest[0] = cs.grabImage(1, 1, 48, 48);


	}
}