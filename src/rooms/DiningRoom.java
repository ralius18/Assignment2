package rooms;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import locations.Room;

public class DiningRoom extends Room {
	
	private final String imageString = "/assets/diningCard.png";
	private BufferedImage image = null;

	@Override
	public void drawCard(Graphics g, int x, int y) {
		try{
			if (image == null){
				image = ImageIO.read(getClass().getResource(imageString));
			}
			g.drawImage(image, x, y, null);
		} catch (IOException e){
			System.err.println("IOException while drawing card");
		}
	}

	@Override
	public String cardName() {
		return "Dining Room";
	}

}
