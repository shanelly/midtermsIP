import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Draw extends JComponent{

	Monster monster1;
	Monster monster2;
	Monster monster3;

	public Hero hero;

	public int enemyCount;
	Monster[] monsters = new Monster[5];

	public Draw(){
		spawnEnemy();
		monster1 = new Monster(200, 200);
		monster2 = new Monster(300, 200);
		monster3 = new Monster(250, 350);
		
		try{
			image = ImageIO.read(resource);
			backgroundImage = ImageIO.read(getClass().getResource("background.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();

		startGame();

	}

	public void startGame(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < monsters.length; c++){
							if(monsters[c]!=null){
								monsters[c].moveTo(x,y);
								repaint();
							}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(enemyCount < 10){
			monsters[enemyCount] = new Monster(700,380, this);
			enemyCount++;
		}
	}


	
	public void attack(){
		hero.attack();
	}

	public void jump(){
		hero.jump();
	}

	public void moveUp(){
		hero.moveUp();
	}

	public void moveDown(){
		hero.moveDown();
	
	}

	public void moveLeft(){
		hero.moveLeft();
		
	}

	public void moveRight(){
		hero.moveRight();
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(image, x, y, this);

		g.drawImage(monster1.image, monster1.xPos, monster1.yPos, this);
		g.drawImage(monster2.image, monster2.xPos, monster2.yPos, this);
		g.drawImage(monster3.image, monster3.xPos, monster3.yPos, this);
	}
}