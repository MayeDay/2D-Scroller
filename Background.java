import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Background{

	private BufferedImage image;
	private int x, y;
	private int width, height;

	public BufferedImage loadImage(String path){


		try{
			image = ImageIO.read(getClass().getResource(path));

		}catch(IOException e){
			e.printStackTrace();
		}
		
		this.width = image.getWidth();
		this.height = image.getHeight();
		return image;
	}
}