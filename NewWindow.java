import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.io.*;

public class NewWindow extends JFrame {
	private Object[][] data;
	public NewWindow(String title, int width, int height) {
		setTitle(title);
		setSize(width,height);
		setLocationRelativeTo(null);

		String[] columnNames = {"Data",
                                "Godzina",
                                "Waga [kg]",
                                "Wzrost [cm]",
                                "BMI"};

        FileSupport fsup = new FileSupport();
        try {
        	data = fsup.getDataFromFile();
        } catch (FileNotFoundException cgo) {
        	System.err.println("Blad pobrania obiektu!");
        }
        

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);      
	}
}