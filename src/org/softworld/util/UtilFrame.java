package org.softworld.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

public class UtilFrame {
	private static boolean isSkinSet = true;

	public static void setSkin() {
		if (isSkinSet)
			return;
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			// SmartLookAndFeel.setTheme("blue");
			// SmartLookAndFeel lookFeel = new SmartLookAndFeel();
			AluminiumLookAndFeel.setTheme("blue");
			AluminiumLookAndFeel lookFeel = new AluminiumLookAndFeel();
			javax.swing.UIManager.setLookAndFeel(lookFeel);
			isSkinSet = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initGeneralFrame(JFrame gFrame) {
		// Display the window.
		gFrame.setSize(1000, 700);
		gFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gFrame.pack();
		// gFrame.setResizable(false);
		gFrame.setVisible(true);

		centreWindow(gFrame);
	}

	public static void centreWindow(Window frame) {
		setSkin();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dime = frame.getSize();
		int x = (int) ((dimension.getWidth() - frame.getSize().width) / 2);
		int y = (int) ((dimension.getHeight() - frame.getSize().height) / 2);
		frame.setLocation(x, y);
	}

	public static Object[] getTitle(Object[][] data) {
		Object[] title = new Object[data[0].length];
		for (int i = 0; i < data[0].length; i++)
			title[i] = data[0][i];
		return title;
	}

	public static Object[][] getData(Object[][] mat) {
		Object[][] data = new Object[mat.length - 1][];
		for (int i = 0; i < mat.length - 1; i++)
			data[i] = mat[i + 1];
		return data;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
