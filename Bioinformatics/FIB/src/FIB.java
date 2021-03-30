import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FIB {
	
	//function reads input parameters from file and split it
	public static String[] readInputFile(String fileName)
	{
		Path fin = Path.of(fileName);
		String data = null;
		try 
		{
			data = Files.readString(fin);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return data.split(" ");
	}
	
	//function counts reproduction-age rabbits by given parameters
	public static long countRabbits(String[] params)
	{
		long oldRabbits = 0;
		long youngRabbits = 1;
		long newRabbits;
		for (int month = 1; month < Integer.parseInt(params[0]) + 1; month++)
		{
			newRabbits = oldRabbits * Integer.parseInt(params[1]);
			oldRabbits += youngRabbits;
			youngRabbits = newRabbits;
		}
		return oldRabbits;
	}
	
	//function writes number of reproduction-age rabbits in a file
	public static void writeInFile(String fileName, long oldRabbits)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, Long.toString(oldRabbits));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//reading of input data
		String[] params = readInputFile("data.txt");
		
		//count reproduction-age rabbits
		long resRabbits = countRabbits(params);
				
		//write result number of rabbits in a file
		writeInFile("out.txt", resRabbits);
	}
}
