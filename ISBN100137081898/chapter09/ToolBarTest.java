package chapter09;  // toolBar

import java.awt.*;

/**
 * ToolBarTest class listing 9.9
 * @version 1.13 2007-06-12
 * @author Cay Horstmann
 */
public class ToolBarTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ToolBarFrame frame = new ToolBarFrame();
				frame.setTitle("Tool Bar Test");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
