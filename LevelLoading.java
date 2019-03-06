import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Point;

public class LevelLoading{

	private Handler handler;
	private Camera cam;
	private BufferedImage image;
	private Player player;
	private Texture tex = Game.getTextureInstance();
	private Point cords [];

	private Random rand = new Random();
	private int xx = 0, yy = 0;
	private int w, h;

	private int loop = 0;
	

	public LevelLoading(Handler handler, BufferedImage image, Camera cam){

		this.handler = handler;
		this.image = image;
		this.cam = cam;

		this.w = image.getWidth();
		this.h = image.getHeight();

		player = new Player(450, 50, ObjectId.Player,tex.player[0], handler);
		handler.addObject(player);
		loadLevel();
	}

	public void tick(){

		if(loop == 20){
			loop = 0;
		}else{
			loop++;
		}
	}

	public void updateLevel(){

		System.out.println("updateLevel");
		for( xx = 0; xx < w; xx++){
			for( yy = 0; yy < h; yy++){
				if(player.renderBounds().contains(xx*32,yy*32)){
					if(!player.renderBounds().contains(cords[yy])){

					int pixel = image.getRGB(xx,yy);

					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;


					if(red == 255 && green == 255 && blue == 255){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[0], 0));
					}

					if(red == 200 && green == 255 && blue == 255){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[1], 1));
					}

					if(red == 100 && green == 255 && blue == 255){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[2], 2));
					}

					if(red == 50 && green == 255 && blue == 255){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[3], 3));
					}

					if(red == 50 && green == 50 && blue == 0){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[4], 4));
					}

					if(red == 25 && green == 25 && blue == 0){
						handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[5], 5));
					}

					if(red == 255 && green == 255 && blue == 0){
						handler.addObject(new TreasureChest(xx *32, yy*32, ObjectId.TreasureChest,tex.chest[0], handler));
					}

					if(red == 0 && green == 0 && blue == 255){
						handler.addObject(new Monster(xx *32, yy*32, ObjectId.Monster,tex.monster[0], handler));
					}
				}
				}			
			}
		}		
	}

	public void loadLevel(){

		 w = image.getWidth();
		 h = image.getHeight();
		 cords = new Point[50];

		for( xx = 0; xx < w; xx++){
			for( yy = 0; yy < h; yy++){
				if(player.renderBounds().contains(xx*32,yy*32)){
					int pixel = image.getRGB(xx,yy);
					cords[yy] = new Point(xx, yy);
					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;

				
					if(red == 255 && green == 255 && blue == 255){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[0], 0));
						}

						if(red == 200 && green == 255 && blue == 255){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[1], 1));
						}

						if(red == 100 && green == 255 && blue == 255){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[2], 2));
						}

						if(red == 50 && green == 255 && blue == 255){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[3], 3));
						}

						if(red == 50 && green == 50 && blue == 0){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[4], 4));
						}

						if(red == 25 && green == 25 && blue == 0){
							handler.addObject(new Block(xx *32, yy*32, ObjectId.Block, tex.block[5], 5));
						}

						if(red == 255 && green == 255 && blue == 0){
							handler.addObject(new TreasureChest(xx *32, yy*32, ObjectId.TreasureChest,tex.chest[0], handler));
						}

						/**if(red == 255 && green == 0 && blue == 0){
							handler.addObject(new Player(xx *32, yy*32, ObjectId.Player,tex.player[0], handler));
						}**/

						if(red == 0 && green == 0 && blue == 255){
							handler.addObject(new Monster(xx *32, yy*32, ObjectId.Monster,tex.monster[0], handler));
						}
					}else{
					}	
				}	
		}
	}
}
