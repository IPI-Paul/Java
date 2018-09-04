package chapter10;

import java.awt.Image;

import javax.swing.*;
import ipi.ResourcePaths;

/**
 * {@code Planet} class <br />
 * {@link SplitPaneFrame} class extends JFrame Listing 10.29 <br />
 * @author Paul I Ighofose
 */
public class Planet {
	private String name;
	private int size;
	private int moons;

	public Planet(String name, int size, int moons) {
		this.name = name;
		this.size = size;
		this.moons = moons;
	}

	public ImageIcon getImage(int width) {
		Image img = new ImageIcon(ResourcePaths.getResource("img", name + ".jpg")).getImage()
				.getScaledInstance((int) width, -1, Image.SCALE_SMOOTH); 
		return new ImageIcon(img);
	}

	public String getDescription() {
		return "Radius: " + size + "\n" + "Moons: " + moons;
	}
	
	public String toString() {
		return name;
	}
}
