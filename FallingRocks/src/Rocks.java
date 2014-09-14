import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Rocks extends Canvas implements Runnable {

	private Thread rocksThread;
	public LinkedList<Box> rocks;
	private Graphics rockGraphics;
	public int rockCounter = 0;
	public Box b;
	

	public boolean rockFall = false;

	public void paint(Graphics g) {
		rockGraphics = g.create();

		if (rocksThread == null) {
			rocksThread = new Thread(this);
			rocksThread.start();
		}

	}

	public Rocks() {
		rockFall = true;
		rocks = new LinkedList<>();
		Random rand = new Random();
		int n = rand.nextInt(19);
		Collections.addAll(rocks, new Box(5, 25));

	}

	public void tick() {

		for (Box a : rocks) {
			if((a.x == Game.ship.getTopX() && a.y == Game.ship.getTopY()) ||
					(a.x == Game.ship.getBottomX() && a.y == Game.ship.getBottomY())){
				//Game.threadSpeed = 123123;
				 JOptionPane.showMessageDialog(null, "GameOver");
				 Game.setRunning(false);
				 
			}
		}
		

		int counter = 0;
		this.rockCounter++;
		if (this.rockCounter > 0) {
			Random rand = new Random();
			int n = rand.nextInt(19);
			rocks.add(new Box(n, 0));
			this.rockCounter = 0;

		}
		for (Box box : rocks) {
			rocks.set(counter, new Box(box.x, box.y + 1));
			counter++;
		}
	}

	public void run() {
		while (true) {
			// drawRocks(rockGraphics);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void drawRocks(Graphics a) {
		for (Box box : rocks) {
			a.setColor(Color.black);
			a.fillRect(box.x * Box.BOX_SIZE, box.y * Box.BOX_SIZE,
					Box.BOX_SIZE, Box.BOX_SIZE);
		}
	}
}
