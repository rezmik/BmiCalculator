import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSupport {
	private FileReader fileRead = null;
	private BufferedReader bufforRead;
	private String source ="data.txt",
			   lineToRead = "";
	
	public int howManyLines() {
		int counter = 0;
		try {
			fileRead = new FileReader(source);
		} catch (FileNotFoundException fnte) {
			System.err.println("Blad przy otwieraniu pliku!");
		}
		bufforRead = new BufferedReader(fileRead);
		try {
			while ((lineToRead = bufforRead.readLine()) != null) {
				counter++;
			}
		} catch (IOException rff) {
			System.err.println("Blad przy odczycie z pliku!");
		}
		try {
			fileRead.close();
		} catch (IOException fc) {
			System.err.println("Blad przy zamykaniu pliku!");
		}

		return counter;
	}

	public Object[][] getDataFromFile() throws FileNotFoundException {
		int counter = 0;
		int count = howManyLines();
		Object[][] data = new Object[count][5];
		try {
			fileRead = new FileReader(source);
		} catch (FileNotFoundException fnte) {
			System.err.println("Blad przy otwieraniu pliku!");
		}
		
		bufforRead = new BufferedReader(fileRead);
		try {
			for (int i = 0; (lineToRead = bufforRead.readLine()) != null; i++) {
				data[i][0] = lineToRead.substring(0,10);
				data[i][1] = lineToRead.substring(11,16);
				data[i][2] = lineToRead.substring(17,21);
				data[i][3] = lineToRead.substring(22,25);
				data[i][4] = lineToRead.substring(28,33);
			}
		} catch (IOException rff) {
			System.err.println("Blad przy odczycie z pliku!");
		}
		try {
			fileRead.close();
		} catch (IOException fc) {
			System.err.println("Blad przy zamykaniu pliku!");
		}

		return data;
	}

	public void saveDataInFile(String textToWrite) throws IOException {
		FileWriter draw = new FileWriter(source, true);
		PrintWriter printLine = new PrintWriter(draw);
		printLine.printf("%s" + "%n", textToWrite);
		printLine.close();
	}
}
