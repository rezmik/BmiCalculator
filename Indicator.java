import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Indicator extends JFrame implements ActionListener {
	private JPanel leftPanel, rightPanel;
	private JButton calcBtn, historyBtn;
	private JTextField weightField, heightField;
	private FileSupport fsup;
	
	protected JLabel weightLabel, heightLabel, infoText, bmiValue, msgText;
	protected double weight, height;
	protected String inField;

	public Indicator() {
		setTitle("BMI Calculator");
		setSize(500,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel cp = (JPanel)getContentPane();
		cp.setLayout(new GridLayout(1,2,5,5));
		initComponent();
		
		leftPanel = createLeftPanel();
		rightPanel = createRightPanel();
		
		cp.add(leftPanel);
		cp.add(rightPanel);
	}
	
	public void initComponent() {
		calcBtn = new JButton("Przelicz");
		historyBtn = new JButton("Historia");
		weightLabel = new JLabel("Waga");
		heightLabel = new JLabel("Wzrost");
		weightField = new JTextField(20);
		heightField = new JTextField(20);
		
		infoText = new JLabel("Twoj wskaznik BMI wynosi:");
		infoText.setFont(new Font("Calibri", Font.BOLD, 18));
		infoText.setHorizontalAlignment(JLabel.CENTER);
		bmiValue = new JLabel();
		bmiValue.setFont(new Font("Calibri", Font.BOLD, 18));
		bmiValue.setHorizontalAlignment(JLabel.CENTER);
		msgText = new JLabel();
		msgText.setFont(new Font("Calibri", Font.BOLD, 18));
		msgText.setHorizontalAlignment(JLabel.CENTER);
		
		calcBtn.addActionListener(this);
		historyBtn.addActionListener(this);
	}

	public JPanel createLeftPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2,5,5));
		p.add(weightLabel);
		p.add(weightField);
		p.add(heightLabel);
		p.add(heightField);
		p.add(calcBtn);
		p.add(historyBtn);
		return p;
	}
	
	public JPanel createRightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,1,5,5));
		p.add(infoText);
		p.add(bmiValue);
		p.add(msgText);
		return p;
	}

	public static void main(String[] args) {
		Indicator wsk = new Indicator();
		wsk.setVisible(true);
	}
	
	public double getWeightValue() {
		 inField = weightField.getText();
		 return weight = Double.parseDouble(inField);
	}
	
	public double getHeightValue() {
		inField = heightField.getText();
		return height = Double.parseDouble(inField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == calcBtn) {
			weight = getWeightValue();
			height = getHeightValue();
			IndicatorLogic il = new IndicatorLogic(height,weight);
			double bmiVal = il.bmiCalculation();
			inField = String.valueOf(bmiVal);
			bmiValue.setText(inField);
			String text = il.setMessageText(bmiVal);
			msgText.setText(text);
			LocalDate today = LocalDate.now();
			LocalTime timeNow = LocalTime.now();
			int hours = timeNow.getHour();
			int minutes = timeNow.getMinute();
			String raport = today + " " + parseTime(hours) + ":" + parseTime(minutes) + " " + weight + " " + height + " " + bmiVal + " ";
			fsup = new FileSupport();
			try {
				fsup.saveDataInFile(raport);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}

		} else if (source == historyBtn) {
			/* NewWindow historyWindow = new NewWindow("Historia", 450,300);
			historyWindow.setVisible(true); */
			try {
				URL myURL = new URL("https://google.com");
				openWebpage(myURL);
			} catch (MalformedURLException evn) {
				System.err.println(evn.getMessage());
			}
		}
	}
	
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}

	public String parseTime(int value) {
		String time;
		if (value < 10) {
			time = "0" + value;
		} else {
			time = "" + value;
		}
		return time;
	}
}
