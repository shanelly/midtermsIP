import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Monster{
	
	public int xPos = 150;
	public int yPos = 300;
	
	public BufferedImage image;
	public URL resource = getClass().getResource("slime/idle0.png");


	public Monster(){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		animate(comp);
	}

	public Monster(int xPass, int yPass){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();
		animate(comp);
	}

	public void animate(Draw compPass){
		Thread monThread = new Thread(new Runnable(){
			public void run(){
				while(idle){
					for(int ctr = 0; ctr < 5; ctr++){
						try {
							if(ctr==4){
								resource = getClass().getResource("slime/idle0.png");
							}
							else{
								resource = getClass().getResource("slime/idle"+ctr+".png");
							}
							
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){
								e.printStackTrace();
							}

					        compPass.repaint();
					        Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		});
		monThread.start();
	}

	public void moveTo(int toX, int toY){
		if(xPos<toX){
			xPos++;
		}
		else if(xPos>toX){
			xPos--;
		}
	

	}

}
