import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RNA {
	
	//function reads a DNA string from a file
	public static String readInputFile(String fileName)
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
		return data;
	}
	
	//function writes result string in a file
	public static void writeInFile(String fileName, String res)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, res);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)
	{
		//reading of input data
		String data = readInputFile("data.txt");
		
		//make RNA from DNA
		data = data.replace('T', 'U');
		
		//write RNA in a file
		writeInFile("out.txt", data);
	}

}
