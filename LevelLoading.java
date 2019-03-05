import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class LevelLoading{

	private Handler handler;
	private Camera cam;
	private BufferedImage image;
	private Texture tex = Game.getTextureInstance();

	private Random rand = new Random();
	private int xx = 0, yy = 0;

	

	public LevelLoading(Handler handler, BufferedImage image, Camera cam){

		this.handler = handler;
		this.image = image;
		this.cam = cam;

		load();
	}

	public void tick(int x, int y){

		updateLevel(x, y);
	}

	private void updateLevel(int x, int y){


		for( xx = x; xx < cam.getX(); xx++){
			for( yy = 1; yy < 100; yy++){

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

					if(red == 255 && green == 0 && blue == 0){
						handler.addObject(new Player(xx *32, yy*32, ObjectId.Player,tex.player[0], handler));
					}

					if(red == 0 && green == 0 && blue == 255){
						handler.addObject(new Monster(xx *32, yy*32, ObjectId.Monster,tex.monster[0], handler));
					}
			
		
			}
		}
	}

	private void load(){

		int w = image.getWidth()/ 15;
		int h = image.getHeight() / 20;
			
			for(int i = 0; i < 5; i++){
				int monsterSpawner = rand.nextInt(200);
				image.setRGB(monsterSpawner, rand.nextInt(16), new Color(0, 0, 255, 255).getRGB());
				System.out.println("Monster Position = " + monsterSpawner);
			}

		

		for( xx = 0; xx < w; xx++){
			for( yy = 0; yy < h; yy++){

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

					if(red == 255 && green == 0 && blue == 0){
						handler.addObject(new Player(xx *32, yy*32, ObjectId.Player,tex.player[0], handler));
					}

					if(red == 0 && green == 0 && blue == 255){
						handler.addObject(new Monster(xx *32, yy*32, ObjectId.Monster,tex.monster[0], handler));
					}
				}
		}
	}
}
