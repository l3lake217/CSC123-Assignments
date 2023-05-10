
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class DirectoryInfo 
{
	String stringDirectory;
	File fileDirectory;
	String[] fileList;
	ArrayList<String> fileContent = new ArrayList<>();
	int count=0;
	int count2=0;
	int count3=0;
	int totalAlphaChars = 0;
	int totalNumericChars = 0;
	int totalSpacesChars = 0;
	int totalFiles=0;
	int totalDiskSize=0;


	void runAnalyszer() 
	{
		setFile(getDirectoryString());
		isitDirectory(); 
		setColumnHeaders();
		FileandCharCounter();
		totalResults();
	}
	
	public String getDirectoryString() 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter a directory: ");
		String stringDirectory = scanner.nextLine();
		scanner.close();
		this.stringDirectory=stringDirectory;
		return stringDirectory;
	}

	public void setFile(String df) 
	{
		this.fileDirectory=new File(df);
	}
	
	public void isitDirectory() 
	{
		if(fileDirectory.isDirectory()) {
			String[] Stringnames = fileDirectory.list();
			fileList=Stringnames;
		}
		else {
			System.out.println("Not a Directory.");
			System.exit(0);
		}
	}
	public void setColumnHeaders() {
		System.out.print("File Name\t\tSize\t\tAlphaChars\tNumericChars\t\tSpaces\n");
	}
	
	public void FileandCharCounter() 
	{
		for(int c=0;c<fileList.length;c++){
			totalFiles++;
			String textfile = stringDirectory+"\\"+fileList[c];
			File dataFile = new File(textfile);
			totalDiskSize += dataFile.length();
			try{
				BufferedReader reader = new BufferedReader(new FileReader(dataFile));
				String line;
				while((line = reader.readLine())!=null){
					fileContent.add(line);
					}
					reader.close();
					for(int b=0;b<fileContent.size();b++){
						//System.out.println(fileContent);
						for(int i=0;i<fileContent.get(b).length();i++){
							if(fileContent.get(b).charAt(i)>='a' && fileContent.get(b).charAt(i)<='z' 
									|| fileContent.get(b).charAt(i) >= 'A' && fileContent.get(b).charAt(i) <= 'Z') {
								count++;
								totalAlphaChars++;
								}
							if(fileContent.get(b).charAt(i)>='0' && fileContent.get(b).charAt(i)<='9') {
								count2++;
								totalNumericChars++;
								}
							if(fileContent.get(b).charAt(i)== ' ') {
								count3++;
								totalSpacesChars++;
								}
							
					}
					}
					ArrayList<String> fileStats = new ArrayList<>();
					fileStats.add(dataFile.length()+" bytes"+"\t"+count+"\t\t"+count2+"\t\t\t"+count3);
					if(fileList[c].length()>15) {
						String shortname =fileList[c].substring(0, 7)+".txt";
						fileList[c]=shortname;

					}
					System.out.print(fileList[c]);
					System.out.println("\t\t"+fileStats);
					count=0;
					count2=0;
					count3=0;
					fileContent.clear();
				} catch (IOException e) {
					System.out.println("Source File could not be read.");
				}
			}
	}
	
	public void totalResults() 
	{
		System.out.println("\nTotal Files: "+totalFiles);
		System.out.println("Total Alpha Chars: "+totalAlphaChars);
		System.out.println("Total Numeric Chars: "+totalNumericChars);
		System.out.println("Total Space Chars: "+totalSpacesChars);
			if(totalDiskSize<1024) {
				System.out.println("\nTotal Size Disk: "+totalDiskSize+" bytes");
			}
			if(totalDiskSize>=1024&&(totalDiskSize/1024)<1024) {
				totalDiskSize/=1024;
				System.out.println("\nTotal Size Disk: "+totalDiskSize+"KB");
			}
			if((totalDiskSize/1024)>=1024&&((totalDiskSize/1024)/1024)<1024) {
				totalDiskSize=(totalDiskSize/1024)/1024;
				System.out.println("\nTotal Size Disk: "+totalDiskSize+"MB");
			}
			if((totalDiskSize/1024)/1024>=1024) {
				totalDiskSize=((totalDiskSize/1024)/1024)/1024;
				System.out.println("\nTotal Size Disk: "+totalDiskSize+"GB");
			}
	}
	
}	


