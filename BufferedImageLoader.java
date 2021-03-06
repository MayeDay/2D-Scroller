import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader{

	private BufferedImage image;

	public BufferedImage loadImage(String path){

		try{
			image = ImageIO.read(getClass().getResource(path));

		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
}