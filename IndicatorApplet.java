import java.io.FileNotFoundException;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Graphics;

public class IndicatorApplet extends JApplet {
	
	public void paint(Graphics graphics) {
		FileSupport fsup = new FileSupport();
		double[] bmiArray = new double[10];
		String bmiVal = "";
		try {
			Object[][] data = fsup.getDataFromFile();
			for (int i = 0; i < data.length; i++) {
				bmiVal = (data[i][4]).toString();
				bmiArray[i] = Double.parseDouble(bmiVal);
				bmiArray[i] *= 8;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	    graphics.drawString("40", 25, 54);
	    graphics.drawLine(45, 50, 55, 50);
	    graphics.drawString("BMI", 38, 35);
	    graphics.drawLine(45, 175, 460, 175);
		graphics.drawLine(50, 38, 50, 370);
		graphics.drawLine(50, 370, 460, 370);
		int j = 0;
		for (int i = 0; i < 10; i ++) {
			int weight = (int) bmiArray[i];
			int startPoint = 50 + (320 - weight);
			graphics.setColor(Color.green);
			graphics.fillRect(70 + j, startPoint, 20, weight);
			j += 40;
		}
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSupport fsup = new FileSupport();
		double[] bmiArray = new double[10];
		String bmiVal = "";
		try {
			Object[][] data = fsup.getDataFromFile();
			for (int i = 0; i < data.length; i++) {
				bmiVal = (data[i][4]).toString();
				bmiArray[i] = Double.parseDouble(bmiVal);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("BMI: " + bmiArray[0]);
	}*/

}
