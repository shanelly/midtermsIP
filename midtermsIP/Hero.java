import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Hero{

	private BufferedImage image;
	private BufferedImage backgroundImage;
	public URL resource = getClass().getResource("run0.png");

	public int x = 30;
	public int y = 30;

	public int state = 0;


	public Hero(Draw comp){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		animate(comp);
	}

	public void animate(Draw compPass){
		Thread heroThread = new Thread(new Runnable(){
			public void run(){
				while(idle){
					for(int ctr = 0; ctr < 5; ctr++){
						try {
							if(ctr==4){
								resource = getClass().getResource("idle0.png");
							}
							else{
								resource = getClass().getResource("idle"+ctr+".png");
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
		heroThread.start();
	}


	public void reloadImage(){
		state++;

		if(state == 0){
			resource = getClass().getResource("run0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("run1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("run2.png");
		}
		else if(state == 3){
			resource = getClass().getResource("run3.png");
		}
		else if(state == 4){
			resource = getClass().getResource("run4.png");
		}
		else if(state == 5){
			resource = getClass().getResource("run5.png");
			state = 0;
		}

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 6; ctr++){
					try {
						if(ctr==5){
							resource = getClass().getResource("idle.png");
						}
						else{
							resource = getClass().getResource("slash"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				
			}
		});
		thread1.start();
	}

	public void jumpAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==5){
							resource = getClass().getResource("idle.png");
						}
						else{
							resource = getClass().getResource("jump"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				
			}
		});
		thread2.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void jump(){
		jumpAnimation();
	}

	public void moveUp(){
		y = y - 5;
		reloadImage();
		repaint();
	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();
	}

	public void moveLeft(){
		x = x - 5;
		reloadImage();
		repaint();
	}

	public void moveRight(){
		x = x + 5;
		reloadImage();
		repaint();
	}
	

}