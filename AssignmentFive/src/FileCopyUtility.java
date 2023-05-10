import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCopyUtility {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> fileContent = new ArrayList<>();
		
		System.out.println("Enter a source file: ");
		String sourceFile = sc.nextLine();
		System.out.println("Enter a taget file: ");
		String targetFile = sc.nextLine();
		
		// Test Target and Source files.
		File testSF = new File(sourceFile);
		if(testSF.isDirectory()) {
			System.out.println("\nSource is a directory, Ending Program.");
			System.exit(0);
		}
		File testTF = new File(targetFile);
		if(testTF.isDirectory()) {
			System.out.println("\nTarget is a directory, Ending Program.");
			System.exit(0);
		}
		if(testTF.exists()) {
			System.out.println("\nTarget already exists, Ending Program.");
			System.exit(0);
		}
		
		// Copy the Source file.
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
			String line;
			while((line = reader.readLine())!=null) {
				fileContent.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Source File could not be found, Ending Program.");
			System.exit(0);
		}
		
		// Create the Target file, and paste source file in target file.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
			for(int c=0;c<fileContent.size();c++){
				writer.write(fileContent.get(c));
				writer.write("\n");
			}
			long bytes = new File(sourceFile).length();
			System.out.println("\n"+bytes+" bytes successfully copied from '"+sourceFile+"' to '"+targetFile+"'");
			writer.close();
		}
		catch(IOException | IndexOutOfBoundsException e) {
			System.out.println("\nTarget File could not be found, Ending Program.");
		}
	sc.close();
	}

}
